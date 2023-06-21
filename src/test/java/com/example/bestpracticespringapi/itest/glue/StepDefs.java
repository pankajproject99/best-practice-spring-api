package com.example.bestpracticespringapi.itest.glue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@CucumberContextConfiguration
@Slf4j
public class StepDefs {


    final private Object baseUrl = "http://localhost:8080";

    private void callApiMethod(final String method, final String service) throws IOException {
        final CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = baseUrl + service;

        log.info("[{}] Request URL : {}", method, url);
        HttpUriRequest request;
        if("POST".equalsIgnoreCase(method)) {
            final HttpPost post = new HttpPost(url);
            post.setHeader("Content-Type", "application/json");
            request = post;
        } else {
            request = new HttpGet(url);
        }

        final CloseableHttpResponse response = httpClient.execute(request);
    }

    @When("I send a {string} request to {string}")
    public void apiRequest(String method, String service) throws IOException {
        callApiMethod(method, service);
    }

    @Then("I get a response code {int}")
    public void apiResponseCode(Integer code) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals(java.util.Optional.of(200), code);
    }
}
