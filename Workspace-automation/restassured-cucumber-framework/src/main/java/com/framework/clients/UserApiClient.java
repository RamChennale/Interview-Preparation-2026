package com.framework.clients;

import com.framework.auth.IAuthenticationStrategy;
import com.framework.base.BaseApiClient;
import com.framework.models.User;
import io.restassured.response.Response;

/**
 * Concrete API client for the /users resource on reqres.in.
 * Extends BaseApiClient (inheritance) and is agnostic to *which* auth
 * strategy is used - that decision is injected by the caller (step definitions),
 * keeping this class single-responsibility (SRP).
 */
public class UserApiClient extends BaseApiClient {

    private static final String USERS_ENDPOINT = "/posts/";

    public UserApiClient(IAuthenticationStrategy authenticationStrategy) {
        super(authenticationStrategy);
    }

    /** 1. Create - POST /api/users (JWT) */
    public Response createUser(User user) {
        return post(USERS_ENDPOINT, user);
    }

    /** 2. Update - PUT /api/users/{id} (JWT) */
    public Response updateUser(String userId, User user) {
        return put(USERS_ENDPOINT + "/" + userId, user);
    }

    /** 3. Get all users - GET /api/users?page=1 (JWT) */
    public Response getAllUsers(int page) {
        return authenticatedRequestWithQueryParam("page", String.valueOf(page));
    }

    private Response authenticatedRequestWithQueryParam(String key, String value) {
        logger.info("GET {} with query param {}={} using [{}] auth",
                USERS_ENDPOINT, key, value, authenticationStrategy.getAuthType());
        return authenticatedRequest().queryParam(key, value).when().get(USERS_ENDPOINT);
    }

    /** 4. Get single user - GET /api/users/{id} (API Key) */
    public Response getSingleUser(String userId) {
        return get(USERS_ENDPOINT + "/" + userId);
    }

    /** 5. Delete user - DELETE /api/users/{id} (Basic) */
    public Response deleteUser(String userId) {
        return delete(USERS_ENDPOINT + "/" + userId);
    }
}
