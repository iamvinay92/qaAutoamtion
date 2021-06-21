package com.automation.backend.service1Tests.helper;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RegressionClient {

    Service1Helper helper = new Service1Helper();

    public Response getUser(String id) {
        return helper.getUser(id);
    }

    public Response createUser() {
        return helper.createUser();
    }

    public void createUserJourney() {
        helper.createUserJourney();
    }

    public Response createUser(String name, String job) {
        return helper.createUserDP(name, job);
    }

    public Response userJourey() {
        return helper.completeuserJourneyFlow();
    }

}
