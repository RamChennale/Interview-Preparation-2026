package com.framework.stepdefinitions;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

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
public class ApiClient {

    private final String baseUri;

    public ApiClient(String baseUri) {
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
    // Response object
    // ---------------------------------------------------------------
    public static class ApiResponse {
        private final int statusCode;
        private final String bodyAsString;
        private final Map<String, String> headers;
        private final boolean statusVerified;
        private final Response rawResponse;

        public ApiResponse(Response response, int expectedStatusCode) {
            this.rawResponse = response;
            this.statusCode = response.getStatusCode();
            this.bodyAsString = response.getBody().asString();
            this.headers = new HashMap<>();
            response.getHeaders().forEach(h -> headers.put(h.getName(), h.getValue()));
            this.statusVerified = (expectedStatusCode == -1) || (expectedStatusCode == statusCode);
        }

        public int getStatusCode() { return statusCode; }
        public String getBody() { return bodyAsString; }
        public Map<String, String> getHeaders() { return headers; }
        public boolean isStatusVerified() { return statusVerified; }
        public Response getRawResponse() { return rawResponse; }

        public <T> T as(Class<T> clazz) {
            return rawResponse.as(clazz);
        }

        @Override
        public String toString() {
            return "ApiResponse{statusCode=" + statusCode
                    + ", statusVerified=" + statusVerified
                    + ", body=" + bodyAsString + '}';
        }
    }

    // ---------------------------------------------------------------
    // Core execution
    // ---------------------------------------------------------------
    private ApiResponse execute(Method method, ApiRequest request) {
        RequestSpecification spec = RestAssured.given()
                .headers(request.getHeaders())
                .queryParams(request.getQueryParams());

        if (request.getBody() != null) {
            spec = spec.contentType("application/json").body(request.getBody());
        }

        Response response = spec.request(method, request.getEndpoint());

        ApiResponse apiResponse = new ApiResponse(response, request.getExpectedStatusCode());

        if (request.getExpectedStatusCode() != -1 && !apiResponse.isStatusVerified()) {
            System.err.printf(
                    "[WARNING] %s %s -> expected status %d but got %d%n",
                    method, request.getEndpoint(),
                    request.getExpectedStatusCode(), apiResponse.getStatusCode());
        }

        return apiResponse;
    }

    // ---------------------------------------------------------------
    // Public HTTP verb methods
    // ---------------------------------------------------------------
    public ApiResponse get(ApiRequest request) {
        return execute(Method.GET, request);
    }

    public ApiResponse post(ApiRequest request) {
        return execute(Method.POST, request);
    }

    public ApiResponse put(ApiRequest request) {
        return execute(Method.PUT, request);
    }

    public ApiResponse delete(ApiRequest request) {
        return execute(Method.DELETE, request);
    }

    // ---------------------------------------------------------------
    // Demo usage
    // ---------------------------------------------------------------
    public static void main(String[] args) {
        ApiClient client = new ApiClient("https://jsonplaceholder.typicode.com");

        // GET
        ApiResponse getResponse = client.get(
                new ApiRequest("/posts/1").expectStatus(200));
        System.out.println("GET  -> " + getResponse);

        // POST
        Map<String, Object> newPost = new HashMap<>();
        newPost.put("title", "foo");
        newPost.put("body", "bar");
        newPost.put("userId", 1);

        ApiResponse postResponse = client.post(
                new ApiRequest("/posts").body(newPost).expectStatus(201));
        System.out.println("POST -> " + postResponse);

        // PUT
        Map<String, Object> updatedPost = new HashMap<>();
        updatedPost.put("id", 1);
        updatedPost.put("title", "updated title");
        updatedPost.put("body", "updated body");
        updatedPost.put("userId", 1);

        ApiResponse putResponse = client.put(
                new ApiRequest("/posts/1").body(updatedPost).expectStatus(200));
        System.out.println("PUT  -> " + putResponse);

        // DELETE
        ApiResponse deleteResponse = client.delete(
                new ApiRequest("/posts/1").expectStatus(200));
        System.out.println("DELETE -> " + deleteResponse);
    }
}