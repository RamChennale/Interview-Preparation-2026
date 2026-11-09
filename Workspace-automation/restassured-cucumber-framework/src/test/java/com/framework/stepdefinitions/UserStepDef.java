package com.framework.stepdefinitions;

import com.framework.auth.AuthenticationFactory;
import com.framework.auth.IAuthenticationStrategy;
import com.framework.clients.UserApiClient;
import com.framework.base.BaseClass;
import com.framework.enums.AuthType;
import com.framework.models.User;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class UserStepDef {

    private final BaseClass baseClass;

    // Constructor injection - PicoContainer supplies the SAME TestContext
    // instance used by Hooks for this scenario.
    public UserStepDef(BaseClass baseClass) {
        this.baseClass = baseClass;
    }

    // ---------------------------------------------------------------
    // GIVEN steps - set up client with the requested authentication type
    // ---------------------------------------------------------------

    @Given("the user API client is authenticated using {string}")
    public void the_user_api_client_is_authenticated_using(String authTypeStr) {
        AuthType authType = AuthType.valueOf(authTypeStr.toUpperCase().replace(" ", "_"));
        IAuthenticationStrategy strategy = AuthenticationFactory.getStrategy(authType);
        baseClass.setUserApiClient(new UserApiClient(strategy));
    }

    // ---------------------------------------------------------------
    // CREATE (POST) - JWT
    // ---------------------------------------------------------------

    @Given("I have a new user with name {string} and job {string}")
    public void i_have_a_new_user_with_name_and_job(String name, String job) {
        User user = User.builder().name(name).job(job).build();
        baseClass.setRequestUser(user);
    }

    @When("I send a POST request to create the user")
    public void i_send_a_post_request_to_create_the_user() {
        var response = baseClass.getUserApiClient().createUser(baseClass.getRequestUser());
        baseClass.setResponse(response);
        baseClass.setCreatedUserId(response.jsonPath().getString("id"));
    }

    // ---------------------------------------------------------------
    // UPDATE (PUT) - JWT
    // ---------------------------------------------------------------

    @Given("I have updated details name {string} and job {string} for user id {string}")
    public void i_have_updated_details_for_user_id(String name, String job, String userId) {
        baseClass.setRequestUser(User.builder().name(name).job(job).build());
        baseClass.setCreatedUserId(userId);
    }

    @When("I send a PUT request to update the user")
    public void i_send_a_put_request_to_update_the_user() {
        var response = baseClass.getUserApiClient()
                .updateUser(baseClass.getCreatedUserId(), baseClass.getRequestUser());
        baseClass.setResponse(response);
    }

    // ---------------------------------------------------------------
    // GET ALL - JWT
    // ---------------------------------------------------------------

    @When("I send a GET request to fetch users on page {int}")
    public void i_send_a_get_request_to_fetch_users_on_page(int page) {
        var response = baseClass.getUserApiClient().getAllUsers(page);
        baseClass.setResponse(response);
    }

    // ---------------------------------------------------------------
    // GET SINGLE - API Key
    // ---------------------------------------------------------------

    @When("I send a GET request to fetch user with id {string}")
    public void i_send_a_get_request_to_fetch_user_with_id(String userId) {
        var response = baseClass.getUserApiClient().getSingleUser(userId);
        baseClass.setResponse(response);
    }

    // ---------------------------------------------------------------
    // DELETE - Basic
    // ---------------------------------------------------------------

    @When("I send a DELETE request for user with id {string}")
    public void i_send_a_delete_request_for_user_with_id(String userId) {
        var response = baseClass.getUserApiClient().deleteUser(userId);
        baseClass.setResponse(response);
    }

    // ---------------------------------------------------------------
    // COMMON THEN / AND assertions
    // ---------------------------------------------------------------

    @Then("the response status code should be {int}")
    public void the_response_status_code_should_be(int expectedStatusCode) {
        Assert.assertEquals(baseClass.getResponse().getStatusCode(), expectedStatusCode,
                "Unexpected status code. Response body: " + baseClass.getResponse().getBody().asString());
    }

    @And("the response should contain field {string} with value {string}")
    public void the_response_should_contain_field_with_value(String field, String expectedValue) {
        String actual = baseClass.getResponse().jsonPath().getString(field);
        Assert.assertEquals(actual, expectedValue,
                "Field [" + field + "] mismatch");
    }

    @And("the response field {string} should not be null")
    public void the_response_field_should_not_be_null(String field) {
        Object value = baseClass.getResponse().jsonPath().get(field);
        Assert.assertNotNull(value, "Expected field [" + field + "] to be present");
    }

    @And("the response should contain a list of users")
    public void the_response_should_contain_a_list_of_users() {
        var users = baseClass.getResponse().jsonPath().getList("data");
        Assert.assertNotNull(users, "Expected 'data' array in response");
        Assert.assertFalse(users.isEmpty(), "Expected at least one user in 'data' array");
    }
}
