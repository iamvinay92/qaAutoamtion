package com.automation.backend.service1Tests.helper;

import com.automation.actions.DBAssertion;
import com.automation.backend.data.DbConnectionDetails;
import com.automation.backend.utils.DataUtils.DBUtils;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import lombok.extern.slf4j.Slf4j;
import org.asynchttpclient.AsyncHttpClient;
import org.mortbay.io.WriterOutputStream;
import org.testng.annotations.BeforeMethod;

import java.io.PrintStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.asynchttpclient.Dsl.asyncHttpClient;

@Slf4j
public class BaseTest {

    public AsyncHttpClient asyncHttpClient = asyncHttpClient();
    public DBAssertion dbAssert = new DBAssertion();

    public StringWriter errorWriter;
    public PrintStream errorCapture;

  /*  public static final String HOST = "localhost";
    public static final int PORT = 7777;
    public static WireMockServer wireMockServer = new WireMockServer(PORT); */

//    public RequestSpecification requestSpec;

    @BeforeMethod
    public void setUp() {
        errorWriter = new StringWriter();
        errorCapture = new PrintStream(new WriterOutputStream(errorWriter), true);
    }

    /*
     * This is for making a DB connection pool to different database hosts
     */
//    @BeforeSuite
    public void setup() {
        DbConnectionDetails connectionDetailsPPS = DbConnectionDetails.builder().host("mdb.stage.myntra.com").dbPort("3306").userName("ScmAutomation").password("B33C3333PR0D").dbName("myntra_pps").build();
        DbConnectionDetails connectionDetailsPayments = DbConnectionDetails.builder().host("mdb.stage.myntra.com").dbPort("3306").userName("ScmAutomation").password("B33C3333PR0D").dbName("payments").build();
        DbConnectionDetails connectionDetailsMyntraCredit = DbConnectionDetails.builder().host("mdb.stage.myntra.com").dbPort("3306").userName("ScmAutomation").password("B33C3333PR0D").dbName("myntra_giftcard").build();
        DbConnectionDetails connectionDetailsLP = DbConnectionDetails.builder().host("mdb.stage.myntra.com").dbPort("3306").userName("ScmAutomation").password("B33C3333PR0D").dbName("myntra_lp").build();
        DbConnectionDetails connectionDetailsWallet = DbConnectionDetails.builder().host("mdb.stage.myntra.com").dbPort("3306").userName("ScmAutomation").password("B33C3333PR0D").dbName("myntra_wallets").build();
        DbConnectionDetails connectionDetailsOms = DbConnectionDetails.builder().host("mdb.stage.myntra.com").dbPort("3307").userName("ScmAutomation").password("B33C3333PR0D").dbName("myntra_oms").build();
        List<DbConnectionDetails> connectionDetails = new ArrayList<>();
        Collections.addAll(connectionDetails, connectionDetailsPPS, connectionDetailsPayments, connectionDetailsMyntraCredit, connectionDetailsLP, connectionDetailsWallet, connectionDetailsOms);
        new DBUtils(connectionDetails);
    }

    public static ResponseSpecification responseSpec() {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(200);
        responseSpecBuilder.expectHeader("Content-Type", "application/json; charset=utf-8");
        return responseSpecBuilder.build();
    }

    /*
     * This is a Request Specification which would be common across APIs
     * */
    public static RequestSpecification requestSpec() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.addHeader("Content-Type", "application/json; charset=utf-8");
        requestSpecBuilder.addHeader("Authorization", "Bearer e85170d9b952bb8a12603f87d6c8f525946b4906c311b83eeec703bd0c1cbea0");
        return requestSpecBuilder.build();
    }
}
