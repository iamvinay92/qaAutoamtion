package com.automation.actions;


/*
 * This class is a separate layer to validate the API responses in terms of status code, body, headers etc.
 * */

import io.restassured.response.Response;

import static org.testng.AssertJUnit.assertEquals;

public class APIAssertion {

    public static void verifyStatusCode(Response response) {
        assertEquals(response.getStatusCode(), 200);
    }

    public static void verifyStatusCode(Response response, int expected) {
        assertEquals(response.getStatusCode(), expected);
    }

    public static void verifyResponseBody(Response response, String code) {
        System.out.println("code is " + response.jsonPath().getString("code"));
        assertEquals(response.jsonPath().getString("code"), code);
    }
}
