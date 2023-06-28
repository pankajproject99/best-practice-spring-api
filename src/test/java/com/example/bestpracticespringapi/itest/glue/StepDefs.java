package com.example.bestpracticespringapi.itest.glue;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
//import org.apache.http.client.methods.CloseableHttpResponse;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.methods.HttpUriRequest;
//import org.apache.http.entity.StringEntity;
//import org.apache.http.impl.client.CloseableHttpClient;
//import org.apache.http.impl.client.HttpClients;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.Assert.assertEquals;

@CucumberContextConfiguration
@Slf4j
public class StepDefs {


    final private String baseUrl = "http://localhost:8080";
    private int actResponseCode;

    private void callApiMethod(String method, String service, String requestPayLoad) throws IOException, URISyntaxException, InterruptedException {
        //        final CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpClient httpClient = HttpClient.newHttpClient();
        String url = baseUrl + service;

        log.info("[{}] Request URL : {}", method, url);
        HttpRequest request;
        if("POST".equalsIgnoreCase(method)) {
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .POST(HttpRequest.BodyPublishers.ofString(requestPayLoad))
                    .setHeader("Content-Type", MediaType.APPLICATION_JSON.toString())
                    .build();

//            HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
//                    .uri(new URI(url));
//
//            if (requestPayLoad!=null) {
//                requestBuilder.POST(HttpRequest.BodyPublishers.ofString(requestPayLoad));
//            } else {
//                requestBuilder.POST(null);
//            }
//
//            request = requestBuilder.build();

//            final HttpPost post = new HttpPost(url);
//            post.setHeader("Content-Type", "application/json");
//            if(requestPayLoad!=null){
//                post.setEntity(new StringEntity(requestPayLoad));
//            }
//            request = post;
        } else {
//            request = new HttpGet(url);
            request = HttpRequest.newBuilder()
                    .uri(new URI(url))
                    .GET()
                    .build();
        }

        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        actResponseCode = response.statusCode();
    }

    private void callApiMethod(final String method, final String service) throws IOException, URISyntaxException, InterruptedException {
        this.callApiMethod(method, service, null);
    }

    @When("I send a {string} request to {string}")
    public void apiRequest(String method, String service) throws IOException, URISyntaxException, InterruptedException {
        callApiMethod(method, service);
    }

    @Then("I get a response code {int}")
    public void apiResponseCode(final int code) {
        // Write code here that turns the phrase above into concrete actions
        assertEquals("Wrong response code returned", code, actResponseCode);
    }

    @When("I POST this json request to {string}")
    public void postJsonToApiMethod(final String service, final String json) throws IOException, URISyntaxException, InterruptedException {
        callApiMethod("POST", service, json);
    }
}
