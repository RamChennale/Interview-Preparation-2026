package com.example.stepdefs;

import com.example.pojo.PostPOJO;
import com.example.utils.JsonUtils;
import io.cucumber.java.Before;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

/**
 * Step definitions for src/test/resources/features/posts.feature
 *
 * All steps for the feature live in one class (no DI framework needed).
 * State is shared between Given -> When -> Then using simple instance
 * fields, since Cucumber-TestNG re-instantiates step classes per scenario
 * only when a DI module is configured; here a plain instance works fine
 * because each scenario runs against a fresh instance of this class.
 *
 * No authentication is required: jsonplaceholder.typicode.com is a public,
 * unauthenticated fake REST API.
 */
public class PostSteps {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    private PostPOJO requestPostPOJO;
    private Response response;
    private PostPOJO responsePostPOJO;

    //@Before
    public void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Given("the Posts API base URL is set")
    public void the_posts_api_base_url_is_set() {
        RestAssured.baseURI = BASE_URL;
    }

    @Given("I have a new post with title {string}, body {string} and userId {int}")
    public void i_have_a_new_post(String title, String body, int userId) {
        requestPostPOJO = new PostPOJO();
        requestPostPOJO.setTitle(title);
        requestPostPOJO.setBody(body);
        requestPostPOJO.setUserId(userId);
    }

    @Given("a post with id {int} already exists")
    public void a_post_with_id_already_exists(int id) {
        // jsonplaceholder is pre-seeded with posts 1-100, nothing to create here.
        requestPostPOJO = new PostPOJO();
        requestPostPOJO.setId(id);
    }

    @Given("I have updated post data with id {int}, title {string}, body {string} and userId {int}")
    public void i_have_updated_post_data(int id, String title, String body, int userId) {
        requestPostPOJO = new PostPOJO();
        requestPostPOJO.setId(id);
        requestPostPOJO.setTitle(title);
        requestPostPOJO.setBody(body);
        requestPostPOJO.setUserId(userId);
    }

    // ---------------------------------------------------------------
    // WHEN
    // ---------------------------------------------------------------

    @When("I send a POST request to {string}")
    public void i_send_a_post_request_to(String endpoint) {
        // Serialize POJO -> JSON using Jackson ObjectMapper
        String requestBody = JsonUtils.toJson(requestPostPOJO);

        response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post(endpoint);

        deserializeResponse();
    }

    @When("I send a GET request to {string}")
    public void i_send_a_get_request_to(String endpoint) {
        response = given()
                .when()
                .get(endpoint);

        deserializeResponse();
    }

    @When("I send a PUT request to {string}")
    public void i_send_a_put_request_to(String endpoint) {
        // Serialize POJO -> JSON using Jackson ObjectMapper
        String requestBody = JsonUtils.toJson(requestPostPOJO);

        response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put(endpoint);

        deserializeResponse();
    }

    @When("I send a DELETE request to {string}")
    public void i_send_a_delete_request_to(String endpoint) {
        response = given()
                .when()
                .delete(endpoint);
    }

    private void deserializeResponse() {
        // Deserialize JSON response -> POJO using Jackson ObjectMapper
        String body = response.asString();
        if (body != null && !body.isBlank()) {
            responsePostPOJO = JsonUtils.fromJson(body, PostPOJO.class);
        }
    }

    // ---------------------------------------------------------------
    // THEN
    // ---------------------------------------------------------------

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        Assert.assertEquals(response.getStatusCode(), expectedStatusCode,
                "Unexpected status code");
    }

    @Then("the response post title should be {string}")
    public void the_response_post_title_should_be(String expectedTitle) {
        Assert.assertEquals(responsePostPOJO.getTitle(), expectedTitle);
    }

    @Then("the response post body should be {string}")
    public void the_response_post_body_should_be(String expectedBody) {
        Assert.assertEquals(responsePostPOJO.getBody(), expectedBody);
    }

    @And("the response post userId should be {int}")
    public void the_response_post_userid_should_be(int expectedUserId) {
        Assert.assertEquals(responsePostPOJO.getUserId(), Integer.valueOf(expectedUserId));
    }

    @And("the response post id should be {int}")
    public void the_response_post_id_should_be(int expectedId) {
        Assert.assertEquals(responsePostPOJO.getId(), Integer.valueOf(expectedId));
    }

}
