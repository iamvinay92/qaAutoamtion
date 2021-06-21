package com.automation.backend.utils.DataUtils;

/*
 * Fillo maven dependancy to read from EXCEL files
 * With FILLO library we can make a sql type of query to fetch data from excel
 * */

import com.codoid.products.exception.FilloException;
import com.codoid.products.fillo.Connection;
import com.codoid.products.fillo.Fillo;
import com.codoid.products.fillo.Recordset;

import java.io.File;

public class ReadExcelUtility {

    public static String fetchDataFromDataFile(String sheet, String id, String field) {

        String result = null;

        try {
            Fillo fillo = new Fillo();

            Connection connection = fillo.getConnection(System.getProperty("user.dir").concat("/src/test/resources/testdata/TestData.xlsx"));
            String strQuery = "Select * from " + sheet + " where ID='" + id + "'";
            Recordset recordset = connection.executeQuery(strQuery);

            while (recordset.next()) {
                result = recordset.getField(field);
            }

            recordset.close();
            connection.close();
        } catch (FilloException e) {
            e.printStackTrace();
        }
        return result;
    }
}
