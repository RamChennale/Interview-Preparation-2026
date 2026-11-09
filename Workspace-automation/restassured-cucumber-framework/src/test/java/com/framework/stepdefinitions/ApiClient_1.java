package com.framework.stepdefinitions;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

/**
 * Simple API Client - GET, POST, PUT, DELETE
 * Directly uses RequestSpecification to send request
 * and Response to collect and verify status code.
 */
public class ApiClient_1 {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // ---------------- GET ----------------
        RequestSpecification getRequest = given();
        Response getResponse = getRequest.get("/posts/1");
        int getStatus = getResponse.getStatusCode();
        System.out.println("GET Status Code: " + getStatus);
        System.out.println("GET Response Body: " + getResponse.getBody().asString());
        if (getStatus == 200) {
            System.out.println("GET verified successfully");
        } else {
            System.out.println("GET failed. Expected 200 but got " + getStatus);
        }

        // ---------------- POST ----------------
        String postBody = "{ \"title\": \"foo\", \"body\": \"bar\", \"userId\": 1 }";

        RequestSpecification postRequest = given()
                .header("Content-Type", "application/json")
                .body(postBody);
        Response postResponse = postRequest.post("/posts");
        int postStatus = postResponse.getStatusCode();
        System.out.println("POST Status Code: " + postStatus);
        System.out.println("POST Response Body: " + postResponse.getBody().asString());
        if (postStatus == 201) {
            System.out.println("POST verified successfully");
        } else {
            System.out.println("POST failed. Expected 201 but got " + postStatus);
        }

        // ---------------- PUT ----------------
        String putBody = "{ \"id\": 1, \"title\": \"updated title\", \"body\": \"updated body\", \"userId\": 1 }";

        RequestSpecification putRequest = given()
                .header("Content-Type", "application/json")
                .body(putBody);
        Response putResponse = putRequest.put("/posts/1");
        int putStatus = putResponse.getStatusCode();
        System.out.println("PUT Status Code: " + putStatus);
        System.out.println("PUT Response Body: " + putResponse.getBody().asString());
        if (putStatus == 200) {
            System.out.println("PUT verified successfully");
        } else {
            System.out.println("PUT failed. Expected 200 but got " + putStatus);
        }

        // ---------------- DELETE ----------------
        RequestSpecification deleteRequest = given();
        Response deleteResponse = deleteRequest.delete("/posts/1");
        int deleteStatus = deleteResponse.getStatusCode();
        System.out.println("DELETE Status Code: " + deleteStatus);
        System.out.println("DELETE Response Body: " + deleteResponse.getBody().asString());
        if (deleteStatus == 200) {
            System.out.println("DELETE verified successfully");
        } else {
            System.out.println("DELETE failed. Expected 200 but got " + deleteStatus);
        }
    }
}