package com.framework.auth;

import com.framework.config.ConfigManager;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Concrete implementation of IAuthenticationStrategy for JWT / Bearer Token Authentication.
 *
 * Maps to POST /api/users and PUT /api/users/2 requests:
 *   --header 'Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...'
 *
 * Demonstrates: Encapsulation, Interface implementation, defensive validation.
 */
public class JwtAuthStrategy implements IAuthenticationStrategy {

    private static final Logger logger = LogManager.getLogger(JwtAuthStrategy.class);
    private static final String BEARER_PREFIX = "Bearer ";

    private final String token;

    public JwtAuthStrategy() {
        this(ConfigManager.getInstance().getJwtToken());
    }

    public JwtAuthStrategy(String token) {
        if (token == null || token.isBlank()) {
            throw new IllegalArgumentException("JWT token must not be null/blank");
        }
        this.token = token;
    }

    @Override
    public RequestSpecification applyAuthentication(RequestSpecification requestSpecification) {
        logger.info("Applying JWT Bearer Authentication");
        return requestSpecification.header("Authorization", BEARER_PREFIX + token);
    }

    @Override
    public String getAuthType() {
        return "JWT";
    }

    /**
     * Utility to decode and expose the raw token, useful for logging/debug
     * without printing the full secret in plain reports.
     */
    public String getMaskedToken() {
        if (token.length() < 10) return "****";
        return token.substring(0, 6) + "..." + token.substring(token.length() - 4);
    }
}
