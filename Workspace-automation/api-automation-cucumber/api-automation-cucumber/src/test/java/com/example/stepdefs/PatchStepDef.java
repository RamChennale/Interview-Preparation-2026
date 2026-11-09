package com.example.stepdefs;

import com.example.pojo.PostPOJO;
import com.example.utils.JsonUtils;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class PatchStepDef {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private PostPOJO requestPostPOJO;
    private Response response;
    private PostPOJO responsePostPOJO;

    @Before
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }


    @Given("a user input id {int}, title {string}")
    public void aUserInputIdTitle(int id, String title) {
        requestPostPOJO = new PostPOJO();
        requestPostPOJO.setId(id);
        requestPostPOJO.setTitle(title);
    }


    @When("I send a PATCH request to {string}")
    public void iSendAPATCHRequestTo(String endpoint) {
       // Serialize POJO -> JSON using Jackson ObjectMapper
        String requestBody = JsonUtils.toJson(requestPostPOJO);

        response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .patch(endpoint);

        deserializeResponse();


    }

    private void deserializeResponse() {
        // Deserialize JSON response -> POJO using Jackson ObjectMapper
        String body = response.asString();
        if (body != null && !body.isBlank()) {
            responsePostPOJO = JsonUtils.fromJson(body, PostPOJO.class);
        }
    }


    @Then("the http response code should be {int}")
    public void theHttpResponseCodeShouldBe(int expectedResponseCode) {
        //System.out.println("Response of PATHC request : "+ response.toString());
        Assert.assertEquals(response.getStatusCode(), expectedResponseCode,
                "Unexpected status code 200 not received not ");
    }

    @And("the response should be received title as {string}")
    public void theResponseShouldBeReceivedAsTitle(String expectedTitle) {
        Assert.assertEquals(responsePostPOJO.getTitle(), expectedTitle);
    }
}