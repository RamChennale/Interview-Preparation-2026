package com.framework.stepdefinitions;

import io.restassured.RestAssured;

import java.util.HashMap;
import java.util.Map;

/**
 * Single-class REST API client supporting GET, POST, PUT, DELETE.
 * Wraps each call with a request object (endpoint, headers, body, expected status)
 * and a response object (status code, body, headers), and verifies the status code.
 *
 * Dependency (Maven):
 * <dependency>
 *   <groupId>io.rest-assured</groupId>
 *   <artifactId>rest-assured</artifactId>
 *   <version>5.4.0</version>
 * </dependency>
 */
public class ApiRequestClass {

    /*private final String baseUri;

    public ApiRequestClass(String baseUri) {
        this.baseUri = baseUri;
        RestAssured.baseURI = baseUri;
    }

    // ---------------------------------------------------------------
    // Request object
    // ---------------------------------------------------------------
    public static class ApiRequest {
        private String endpoint;
        private Map<String, String> headers = new HashMap<>();
        private Map<String, Object> queryParams = new HashMap<>();
        private Object body;
        private int expectedStatusCode = -1; // -1 = no verification

        public ApiRequest(String endpoint) {
            this.endpoint = endpoint;
        }

        public ApiRequest header(String key, String value) {
            this.headers.put(key, value);
            return this;
        }

        public ApiRequest queryParam(String key, Object value) {
            this.queryParams.put(key, value);
            return this;
        }

        public ApiRequest body(Object body) {
            this.body = body;
            return this;
        }

        public ApiRequest expectStatus(int statusCode) {
            this.expectedStatusCode = statusCode;
            return this;
        }

        public String getEndpoint() { return endpoint; }
        public Map<String, String> getHeaders() { return headers; }
        public Map<String, Object> getQueryParams() { return queryParams; }
        public Object getBody() { return body; }
        public int getExpectedStatusCode() { return expectedStatusCode; }
    }



    // ---------------------------------------------------------------
    // Demo usage
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        ApiRequestClass client = new ApiRequestClass("https://jsonplaceholder.typicode.com");

        // GET
        ApiResponseClass getResponse = client.get(
                new ApiRequest("/posts/1").expectStatus(200));
        System.out.println("GET  -> " + getResponse);

        // POST
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "foo");
        newPost.put("body", "bar");
        newPost.put("userId", 1);

        ApiResponseClass.ApiResponse postResponse = client.post(
                new ApiRequest("/posts").body(newPost).expectStatus(201));
        System.out.println("POST -> " + postResponse);

        // PUT
        Map<String, Object> updatedPost = new HashMap<>();
        updatedPost.put("id", 1);
        updatedPost.put("title", "updated title");
        updatedPost.put("body", "updated body");
        updatedPost.put("userId", 1);

        ApiResponseClass.ApiResponse putResponse = client.put(
                new ApiRequest("/posts/1").body(updatedPost).expectStatus(200));
        System.out.println("PUT  -> " + putResponse);

        // DELETE
        ApiResponseClass.ApiResponse deleteResponse = client.delete(
                new ApiRequest("/posts/1").expectStatus(200));
        System.out.println("DELETE -> " + deleteResponse);
    }*/
}