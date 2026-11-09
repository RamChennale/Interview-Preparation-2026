package com.framework.base;

import com.framework.auth.IAuthenticationStrategy;
import com.framework.utils.RequestSpecBuilderUtil;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Abstract base class for all API clients.
 *
 * Demonstrates:
 *  - Abstraction: hides low-level RestAssured plumbing behind get/post/put/delete methods
 *  - Inheritance: concrete clients (UserApiClient, etc.) extend this class
 *  - Composition (HAS-A): every client is composed with an IAuthenticationStrategy,
 *    chosen at construction time (constructor/dependency injection), rather than
 *    hard-coded - so behavior can be swapped without touching this class.
 */
public abstract class BaseApiClient {

    protected static final Logger logger = LogManager.getLogger(BaseApiClient.class);

    protected final IAuthenticationStrategy authenticationStrategy;

    protected BaseApiClient(IAuthenticationStrategy authenticationStrategy) {
        this.authenticationStrategy = authenticationStrategy;
    }

    /**
     * Builds a fresh, authenticated request spec for every call - avoids state leakage
     * between test scenarios (important for parallel execution).
     */
    protected RequestSpecification authenticatedRequest() {
        RequestSpecification spec = io.restassured.RestAssured.given()
                .spec(RequestSpecBuilderUtil.getBaseRequestSpec());
        return authenticationStrategy.applyAuthentication(spec);
    }

    protected Response get(String endpoint) {
        logger.info("GET {} using [{}] auth", endpoint, authenticationStrategy.getAuthType());
        return authenticatedRequest().when().get(endpoint);
    }

    protected Response post(String endpoint, Object body) {
        logger.info("POST {} using [{}] auth", endpoint, authenticationStrategy.getAuthType());
        return authenticatedRequest().body(body).when().post(endpoint);
    }

    protected Response put(String endpoint, Object body) {
        logger.info("PUT {} using [{}] auth", endpoint, authenticationStrategy.getAuthType());
        return authenticatedRequest().body(body).when().put(endpoint);
    }

    protected Response delete(String endpoint) {
        logger.info("DELETE {} using [{}] auth", endpoint, authenticationStrategy.getAuthType());
        return authenticatedRequest().when().delete(endpoint);
    }
}
