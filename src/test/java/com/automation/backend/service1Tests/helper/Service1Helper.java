package com.automation.backend.service1Tests.helper;

import com.automation.backend.constants.Constants;
import com.automation.backend.service1Tests.data.requests.CreateUser;
import com.automation.backend.service1Tests.data.responses.CreateUserResponse;
import com.automation.backend.service1Tests.data.responses.GetUser;
import com.automation.backend.utils.DataUtils.PropertyLoader;
import com.automation.backend.utils.TestUtils.TestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.mortbay.io.WriterOutputStream;
import org.testng.Assert;

import java.io.PrintStream;
import java.io.StringWriter;

import static com.automation.backend.constants.Constants.WIREMOCK_SERVER;
import static io.restassured.RestAssured.given;
import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@Slf4j
public class Service1Helper {

    Faker faker = new Faker();
    ObjectMapper mapper = new ObjectMapper();
    StringWriter errorWriter = new StringWriter();
    PrintStream errorCapture = new PrintStream(new WriterOutputStream(errorWriter), true);
    String isMockResponse = PropertyLoader.getProperty("isMockResponse");


    public Response createUser() {

        if (isMockResponse.equalsIgnoreCase("true")) {
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
            return response;

        } else {
            Response response = null;
            try {
                JSONObject payload = TestUtils.getJsonObject(Constants.PAYLOAD_BASEPATH.concat("saveEmployee.json"));
                payload.put("email", faker.internet().emailAddress());
                payload.put("status", "Active");
                payload.put("name", faker.address().firstName());
                payload.put("gender", "Male");
                log.info("api request is {} ", payload);

                response = given().spec(BaseTest.requestSpec()).filter(new ErrorLoggingFilter(errorCapture)).contentType(ContentType.JSON).body(payload.toString()).log().everything().when().post(Constants.BASEPATH + Constants.CREATE_USER);
                log.error(errorWriter.toString().toUpperCase());
                log.info("response is {}", response.prettyPrint());
                Assert.assertEquals(response.statusCode(), 200);

            } catch (Exception e) {
                log.error("exception is {} ", e.getStackTrace());
            }
            return response;
        }
    }

    public Response getUser(String id) {
        Response response = null;
        try {
            response = given().queryParam("id", id).spec(BaseTest.requestSpec()).filter(new ErrorLoggingFilter(errorCapture)).log().everything().when().get(Constants.BASEPATH + Constants.CREATE_USER);
            log.error(errorWriter.toString().toUpperCase());
            GetUser getUserResponse = mapper.readValue(response.asString(), GetUser.class);
            log.info("GetUserResponse response is {}", getUserResponse);
            assertEquals(getUserResponse.getCode(), 200);
            assertTrue(getUserResponse.getData().size() > 0);
        } catch (Exception e) {
            log.error("exception is {} ", e.getStackTrace());
        }
        return response;
    }

    public Response createUserDP(String name, String job) {
        Response response = null;
        try {
            JSONObject payload = TestUtils.getJsonObject(Constants.PAYLOAD_BASEPATH.concat("saveEmployee.json"));
            payload.put("name", name);
            payload.put("job", job);
            log.info("api request is {} ", payload);

            response = given().auth().none().filter(new ErrorLoggingFilter(errorCapture)).contentType(ContentType.JSON).body(payload.toString()).log().everything().when().post(Constants.BASEPATH + "api/users");
            log.error(errorWriter.toString().toUpperCase());
            log.info("response is {}", response.prettyPrint());
            Assert.assertEquals(response.statusCode(), 201);

        } catch (Exception e) {
            log.error("exception is {} ", e.getStackTrace());
        }
        return response;
    }

    public Response completeuserJourneyFlow() {
        Response response = null;
        try {
            JSONObject payload = TestUtils.getJsonObject(Constants.PAYLOAD_BASEPATH.concat("saveEmployee.json"));
            payload.put("name", faker.name().lastName());
            payload.put("job", faker.job().field());
            log.info("api request is {} ", payload);

            response = given().auth().none().filter(new ErrorLoggingFilter(errorCapture)).contentType(ContentType.JSON).body(payload.toString()).log().everything().when().post(Constants.BASEPATH + "api/users");
            log.error(errorWriter.toString().toUpperCase());
            log.info("response is {}", response.prettyPrint());
            Assert.assertEquals(response.statusCode(), 201);

        } catch (Exception e) {
            log.error("exception is {} ", e.getStackTrace());
        }
        return response;
    }

    public void createUserJourney() {
        try {
            Response response = null;
            CreateUser createUser = null;
            /*CREATE USER */

            if (isMockResponse.equalsIgnoreCase("true")) {
                JSONObject payload = TestUtils.getJsonObject(Constants.PAYLOAD_BASEPATH.concat("saveEmployee.json"));
                payload.put("email", "Stehr@gmail.com");
                payload.put("status", "Active");
                payload.put("name", "Carl");
                payload.put("gender", "Male");

                response = given().
                        log().all().
                        body(payload.toString()).
                        when().
                        post(WIREMOCK_SERVER.concat("/public-api/users"));
                System.out.println("response is {}" + response.asString());

            } else {
                createUser = createCreateUserRequest();
                log.info("request is {} ", createUser);
                CreateUserResponse createUserResponse = null;
                response = given().spec(BaseTest.requestSpec()).filter(new ErrorLoggingFilter(errorCapture)).body(mapper.writeValueAsString(createUser)).log().everything().when().post(Constants.BASEPATH.concat(Constants.CREATE_USER));
                createUserResponse = mapper.readValue(response.asString(), CreateUserResponse.class);
                log.error(errorWriter.toString().toUpperCase());
                log.info("createUser response is {}", response.jsonPath().prettyPrint());
                Assert.assertEquals(response.statusCode(), 200);
            }
            /*GET USER */
            String id = response.jsonPath().getString("data.id");
            log.info("id is {}", id);

            Response getUserResponse = given().params("id", id).spec(BaseTest.requestSpec()).filter(new ErrorLoggingFilter(errorCapture)).log().everything().when().get(Constants.BASEPATH.concat(Constants.CREATE_USER));
            log.info("getUserResponse is {}", getUserResponse.jsonPath().prettyPrint());
            Assert.assertEquals(response.statusCode(), 200);

            /*UPDATE USER */
            CreateUser updateUser = createCreateUserRequest();
            updateUser.setStatus("Inactive");

            log.info("UPDATE user request is {} ", updateUser);
            CreateUserResponse createUserResponse1 = null;
            Response updatedUserResponse = given().spec(BaseTest.requestSpec()).with().pathParam("id", id).filter(new ErrorLoggingFilter(errorCapture)).body(mapper.writeValueAsString(createUser)).log().everything().when().put(Constants.BASEPATH.concat(Constants.CREATE_USER).concat("/{id}"));
            createUserResponse1 = mapper.readValue(updatedUserResponse.asString(), CreateUserResponse.class);
            log.error(errorWriter.toString().toUpperCase());
            log.info("createUser response is {}", updatedUserResponse.jsonPath().prettyPrint());
            Assert.assertEquals(updatedUserResponse.statusCode(), 200);

            /*DELETE USER */
            Response deleteUserResponse = given().spec(BaseTest.requestSpec()).filter(new ErrorLoggingFilter(errorCapture)).log().everything().when().delete(Constants.BASEPATH.concat(Constants.CREATE_USER) + "/" + id);
            String code = deleteUserResponse.jsonPath().getString("code");
            String responseBody = deleteUserResponse.getBody().asString();
            Assert.assertTrue(responseBody.contains("204"));
            Assert.assertEquals("204", code);
            log.info("deleteUserResponse response is {}", updatedUserResponse.jsonPath().prettyPrint());
        } catch (Exception e) {
            log.error("exception is {} ", e.getStackTrace());
        }
    }

    private CreateUser createCreateUserRequest() {
        CreateUser createUser = new CreateUser();
        createUser.setEmail(faker.name().lastName() + "@gmail.com");
        createUser.setGender("Male");
        createUser.setName(faker.name().firstName());
        createUser.setStatus("Active");
        return createUser;
    }

}
