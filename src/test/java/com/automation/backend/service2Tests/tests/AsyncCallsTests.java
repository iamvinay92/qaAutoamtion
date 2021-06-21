package com.automation.backend.service2Tests.tests;


import com.automation.backend.service1Tests.helper.BaseTest;
import org.asynchttpclient.Request;
import org.asynchttpclient.Response;
import org.testng.annotations.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import static org.asynchttpclient.Dsl.get;
import static org.testng.AssertJUnit.assertEquals;

public class AsyncCallsTests extends BaseTest {

    /*
     * This is a test example for making a Async call to API.
     * Here, we used AsyncHttpClient for making a async api call.
     * */

    @Test
    public void asyncExampleTest() {
        Request request = get("https://reqres.in/api/users?delay=5").build();
        Future<Response> response = asyncHttpClient.executeRequest(request);

        try {
            System.out.println("response is " + response.get());
            assertEquals(response.get().getStatusCode(), 200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
