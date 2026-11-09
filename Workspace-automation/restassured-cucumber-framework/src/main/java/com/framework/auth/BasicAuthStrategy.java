package com.framework.auth;

import com.framework.config.ConfigManager;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Concrete implementation of IAuthenticationStrategy for HTTP Basic Authentication.
 *
 * Maps to the DELETE /api/users/2 request:
 *   --header 'Authorization: Basic UmFtOnNlY3JldA==' (Base64 of Ram:secret)
 *   --auth-basic-username 'Ram' --auth-basic-password 'secret'
 *
 * Demonstrates: Encapsulation (private fields, constructor injection),
 * Inheritance-by-interface (implements IAuthenticationStrategy), Polymorphism.
 */
public class BasicAuthStrategy implements IAuthenticationStrategy {

    private static final Logger logger = LogManager.getLogger(BasicAuthStrategy.class);

    private final String username;
    private final String password;

    // Overloaded constructor #1 - pulls credentials from config.properties
    public BasicAuthStrategy() {
        this(ConfigManager.getInstance().getBasicUsername(),
                ConfigManager.getInstance().getBasicPassword());
    }

    // Overloaded constructor #2 - explicit credentials (method overloading = compile-time polymorphism)
    public BasicAuthStrategy(String username, String password) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username must not be null/blank for Basic Auth");
        }
        this.username = username;
        this.password = password;
    }

    @Override
    public RequestSpecification applyAuthentication(RequestSpecification requestSpecification) {
        logger.info("Applying Basic Authentication for user [{}]", username);
        // preemptive() sends Authorization header on the very first request
        // instead of waiting for a 401 challenge - required by reqres.in mock server.
        return requestSpecification.auth().preemptive().basic(username, password);
    }

    @Override
    public String getAuthType() {
        return "BASIC";
    }
}
