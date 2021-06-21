package com.automation.backend.service1Tests.helper;

import io.restassured.response.Response;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

public class DbAssist {

    public void responseValidation(Response response) {
        assertEquals(response.statusCode(), 200);

        String name = response.jsonPath().get("data.name");
        assertNotNull(name);

        System.out.println("name is " + name);
    }

    public void responseValidation(Response response, String name, String job) {
        assertEquals(response.statusCode(), 201);

        assertEquals(name, response.jsonPath().get("name"));
        assertEquals(job, response.jsonPath().get("job"));
    }

}
