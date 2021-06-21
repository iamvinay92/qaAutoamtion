package com.automation.backend.service1Tests.tests;

import com.automation.backend.constants.Constants;
import com.automation.backend.utils.TestUtils.TestUtils;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Rule;
import org.junit.Test;
import org.testng.Assert;

import static com.automation.backend.constants.Constants.WIREMOCK_SERVER;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static io.restassured.RestAssured.given;

public class Service1MockedTests {

    //    private static RequestSpecification requestSpec;


    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(9876));


    /*******************************************************
     * Mocked POST request to /public-api/users
     *
     * Verify that the response HTTP status code is equal to 200
     ******************************************************/

    @Test
    public void createUserTest() {
        JSONObject payload = TestUtils.getJsonObject(Constants.PAYLOAD_BASEPATH.concat("saveEmployee.json"));
        payload.put("email", "Stehr@gmail.com");
        payload.put("status", "Active");
        payload.put("name", "Carl");
        payload.put("gender", "Male");

        Response response = given().
                log().all().
                body(payload.toString()).
                when().
                post(WIREMOCK_SERVER.concat("/public-api/users"));

        System.out.println("response is {}" + response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    /*******************************************************
     * Mocked POST request to /public-api/users
     *
     * Verify that the response HTTP status code is equal to 200
     ******************************************************/

   /* @Test
    public void createUserTestForSameEmailId() {

        JSONObject payload = TestUtils.getJsonObject(Constants.PAYLOAD_BASEPATH.concat("saveEmployee.json"));
        payload.put("email", "1Stehr@gmail.com");
        payload.put("status", "Active");
        payload.put("name", "1Carl");
        payload.put("gender", "Male");

        Response response = given().
                spec(requestSpec).log().all().
                body(payload.toString()).
                when().
                post("/public-api/users");

        System.out.println("response is {}" + response.asString());
//        Assert.assertEquals("United States", actualCountry);
    }

    @Test
    public void extractCountryFromResponse() {

        String actualCountry = given().
                spec(requestSpec).log().all().
                when().
                get("/us/12346").
                then().log().all().
                extract().
                path("country");

        Assert.assertEquals("United States", actualCountry);
    }*/
}
