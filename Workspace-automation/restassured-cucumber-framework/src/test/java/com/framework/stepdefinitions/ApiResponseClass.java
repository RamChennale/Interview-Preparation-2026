package com.framework.stepdefinitions;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class ApiResponseClass {
    // ---------------------------------------------------------------
    // Response object
    // ---------------------------------------------------------------
    /*public static class ApiResponse {
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
    private ApiResponse execute(Method method, ApiRequestClass.ApiRequest request) {
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
    public ApiResponse get(ApiRequestClass.ApiRequest request) {
        return execute(Method.GET, request);
    }

    public ApiResponse post(ApiRequestClass.ApiRequest request) {
        return execute(Method.POST, request);
    }

    public ApiResponse put(ApiRequestClass.ApiRequest request) {
        return execute(Method.PUT, request);
    }

    public ApiResponse delete(ApiRequestClass.ApiRequest request) {
        return execute(Method.DELETE, request);
    }*/
}
