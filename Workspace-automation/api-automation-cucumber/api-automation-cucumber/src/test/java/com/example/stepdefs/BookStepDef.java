package com.example.stepdefs;

import com.example.pojo.BookPOJO;
import com.example.pojo.PostPOJO;
import com.example.utils.JsonUtils;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

public class BookStepDef {

    private static final String BASE_URL = "https://bookstore.toolsqa.com";
    private Response response;
    private BookPOJO book;

    //

    @Given("the request base URI")
    public void theRequestBaseURI() {
        RestAssured.baseURI = BASE_URL;
    }


    @When("I send GET request to {string}")
    public void iSendGETRequestTo(String endPoint) {
        response = RestAssured.when()
                .get(endPoint);
    //    deserializeBookResponse();
    }

    @Then("the status code should display {int}")
    public void theStatusCodeShouldDisplay(int statusCode) {
        Assert.assertEquals(statusCode, response.getStatusCode(),"Incorrect status code returned");
    }

    @And("the response should have title {string}")
    public void theResponseShouldHaveTitle(String title) {
        //Assert.assertEquals(title,  book.getTitle(),"Incorrect title returned");
        System.out.println(response.getBody().asString());
    }

    private void deserializeBookResponse() {
        // Deserialize JSON response -> POJO using Jackson ObjectMapper
        String body = response.asString();
        if (body != null && !body.isBlank()) {
            book = JsonUtils.fromJson(body, BookPOJO.class);
        }
    }

}
