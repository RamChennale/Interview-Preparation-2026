package com.framework.auth;

import io.restassured.specification.RequestSpecification;

/**
 * Null Object pattern - used when an endpoint requires no authentication.
 * Avoids null checks scattered across client code.
 */
public class NoAuthStrategy implements IAuthenticationStrategy {

    @Override
    public RequestSpecification applyAuthentication(RequestSpecification requestSpecification) {
        return requestSpecification;
    }

    @Override
    public String getAuthType() {
        return "NONE";
    }
}
