package com.framework.base;

import com.framework.clients.UserApiClient;
import com.framework.models.User;
import io.restassured.response.Response;

/**
 * Shared scenario-scoped state, injected by Cucumber's PicoContainer into every
 * step-definition class constructor that declares it. Each Cucumber scenario gets
 * its own fresh instance - guarantees test isolation, including in parallel runs.
 *
 * Demonstrates Encapsulation: all fields private, exposed via getters/setters only.
 */
public class BaseClass {

    private UserApiClient userApiClient;
    private Response response;
    private User requestUser;
    private String createdUserId;

    /*

     * Response response = get("/lotto");
     * String body = response.getBody().asString();
     * String headerValue = response.getHeader("headerName");
     * String cookieValue = response.getCookie("cookieName");

*/
    public UserApiClient getUserApiClient() {
        return userApiClient;
    }

    public void setUserApiClient(UserApiClient userApiClient) {
        this.userApiClient = userApiClient;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public User getRequestUser() {
        return requestUser;
    }

    public void setRequestUser(User requestUser) {
        this.requestUser = requestUser;
    }

    public String getCreatedUserId() {
        return createdUserId;
    }

    public void setCreatedUserId(String createdUserId) {
        this.createdUserId = createdUserId;
    }
}
