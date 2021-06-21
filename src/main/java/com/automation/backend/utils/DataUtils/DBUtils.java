package com.automation.backend.utils.DataUtils;

import com.automation.backend.data.DbConnectionDetails;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    public static final Map<String, DBUtils> dataSourceMap = new HashMap<>();
    private static List<DbConnectionDetails> dbConnectionDetails;
    private static final List<HikariDataSource> dataSources = new ArrayList<>();

    private JdbcTemplate jdbcTemplate;

    public DBUtils() {
    }

    public DBUtils(List<DbConnectionDetails> dbConnectionDetails) {
        DBUtils.dbConnectionDetails = dbConnectionDetails;
        initializeDataSource();
    }

    private void initializeDataSource() {
        for (DbConnectionDetails connectionDetail : dbConnectionDetails) {
            if (null == dataSourceMap.get(connectionDetail.getDbName())) {
                DBUtils dbUtils = new DBUtils();
                HikariDataSource dataSource = getDataSource(connectionDetail);
                dataSources.add(dataSource);
                dbUtils.setDataSource(dataSource);
                dataSourceMap.put(connectionDetail.getDbName(), dbUtils);
                System.out.println("successfully done");
            }
        }
    }

    private static HikariDataSource getDataSource(DbConnectionDetails connectionDetails) {
        String jdbcUrl = "jdbc:mysql://" + connectionDetails.getHost() + ":" + connectionDetails.getDbPort() + "/" + connectionDetails.getDbName();
        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(jdbcUrl);
        hikariConfig.setUsername(connectionDetails.getUserName());
        hikariConfig.setPassword(connectionDetails.getPassword());
        hikariConfig.setMaximumPoolSize(100);
        hikariConfig.setAutoCommit(false);
        return new HikariDataSource(hikariConfig);
    }

    private void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
}
