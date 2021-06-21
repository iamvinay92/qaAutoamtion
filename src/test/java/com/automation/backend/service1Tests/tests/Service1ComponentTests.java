package com.automation.backend.service1Tests.tests;

import com.automation.actions.APIAssertion;
import com.automation.backend.service1Tests.helper.BaseTest;
import com.automation.backend.constants.Constants;
import com.automation.backend.service1Tests.data.GetUserApi;
import com.automation.backend.service1Tests.helper.DbAssist;
import com.automation.backend.service1Tests.helper.RegressionClient;
import com.automation.backend.service1Tests.helper.Service1DataProvider;
import com.automation.backend.utils.DataUtils.DataProviders;
import com.automation.backend.utils.DataUtils.ReadExcelUtility;
import com.automation.backend.utils.TestUtils.RandomUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.testng.annotations.Test;

import static com.automation.actions.APIAssertion.verifyResponseBody;
import static com.automation.actions.APIAssertion.verifyStatusCode;
import static com.automation.backend.constants.Constants.PAYLOAD_BASEPATH;
import static com.automation.backend.utils.DataUtils.ReadExcelUtility.fetchDataFromDataFile;
import static com.automation.backend.utils.TestUtils.TestUtils.getJsonObject;
import static io.restassured.RestAssured.given;

@Slf4j
public class Service1ComponentTests extends BaseTest {

    ObjectMapper mapper = new ObjectMapper();
    Faker faker = new Faker();
    DbAssist dbAssist = new DbAssist();
    RegressionClient regressionClient = new RegressionClient();

    /*
     * This is the way of reading a API REQUEST from json file and parse it using JSONObject of json
     * For large JSON request body, its always better to use a JSON file to read and parse and pass it to API
     */
    @Test
    public void test1() throws JsonProcessingException {
        JSONObject payload = getJsonObject(PAYLOAD_BASEPATH + "saveEmployee.json");

        payload.put("email", RandomUtils.generateRandomString(5).concat("@gmail.com"));
        payload.put("status", fetchDataFromDataFile("service1Data", "createUser", "Value2"));
        payload.put("name", fetchDataFromDataFile("service1Data", "createUser", "Value3"));
        payload.put("gender", fetchDataFromDataFile("service1Data", "createUser", "Value4"));

        log.info("api request is {} ", payload);
        Response response = given().headers("Authorization", "Bearer e85170d9b952bb8a12603f87d6c8f525946b4906c311b83eeec703bd0c1cbea0").filter(new ErrorLoggingFilter(errorCapture)).contentType(ContentType.JSON).body(payload.toString()).log().everything().when().post(Constants.BASEPATH.concat(Constants.CREATE_USER));
        verifyStatusCode(response);
        verifyResponseBody(response, "201");
//        dbAssist.responseValidation(response);
        log.error("hey {} ", errorWriter.toString().toUpperCase());
        log.info("response is {}", response.prettyPrint());
    }

    /*
     * 2. This is the example of executing API with framework. Where request reading is done via .json
     */
    @Test
    public void createUser() {
        Response response = regressionClient.createUser();
        dbAssist.responseValidation(response);
    }

    /*
     * 3. This is the example of executing API with different data sets through testng DATAPROVIDER
     */
    @org.testng.annotations.Test(dataProvider = "createUser", dataProviderClass = Service1DataProvider.class)
    public void createUserWithDP(String name, String job) {
        Response response = regressionClient.createUser(name, job);
        dbAssist.responseValidation(response, name, job);
    }

    /*
     * 4. This is the example of executing API with different data sets through testng DATAPROVIDER
     */
    @org.testng.annotations.Test(dataProvider = "service1_data_provider", dataProviderClass = DataProviders.class)
    public void getUserTest(GetUserApi getUserApi) {
        Response response = regressionClient.getUser(getUserApi.getId());
//        dbAssist.responseValidation(response, name, job);
    }

    @org.testng.annotations.Test(dataProvider = "service1_data_provider", dataProviderClass = DataProviders.class)
    public void getUserNegativeDataTest(GetUserApi getUserApi) {
        Response response = regressionClient.getUser(getUserApi.getId());
//        dbAssist.responseValidation(response, name, job);
    }

}
