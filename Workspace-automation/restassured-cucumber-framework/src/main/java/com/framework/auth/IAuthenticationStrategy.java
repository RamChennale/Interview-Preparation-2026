package com.framework.auth;

import io.restassured.specification.RequestSpecification;

/**
 * Strategy design pattern contract.
 * Every concrete authentication type (Basic / JWT / API Key) implements this
 * interface, allowing the RestAssured request builder to remain agnostic
 * of *how* authentication is applied (polymorphism + abstraction).
 */
public interface IAuthenticationStrategy {

    /**
     * Applies the specific authentication mechanism to the given RequestSpecification.
     *
     * @param requestSpecification the RestAssured request spec being built
     * @return the same specification, decorated with auth details
     */
    RequestSpecification applyAuthentication(RequestSpecification requestSpecification);

    /**
     * Human readable name of the strategy - useful for logging/reporting.
     */
    String getAuthType();
}
