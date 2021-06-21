package com.automation.backend.service1Tests.tests.IntegrationTests;

import com.automation.backend.service1Tests.helper.BaseTest;
import com.automation.backend.service1Tests.helper.DbAssist;
import com.automation.backend.service1Tests.helper.RegressionClient;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import io.restassured.response.Response;
import org.junit.Rule;
import org.testng.annotations.Test;

import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;

public class UserJourneyTests extends BaseTest {
    DbAssist dbAssist = new DbAssist();
    RegressionClient regressionClient = new RegressionClient();

    /*
     * 1. Complete Integration test flow with POJOs
     */

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(options().port(9876));

    @org.junit.Test
    public void createUserJourney() {
        regressionClient.createUserJourney();
//      dbAssist.responseValidation(response);
    }

    /*
     * 2. Complete Integration test flow with .json
     */
    @Test()
    public void userJourneyWithCompleteIntegrationtests() {
        Response response = regressionClient.userJourey();
//        dbAssist.responseValidation(response, name, job);
    }
}
