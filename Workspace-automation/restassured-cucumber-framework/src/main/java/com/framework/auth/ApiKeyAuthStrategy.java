package com.framework.auth;

import com.framework.config.ConfigManager;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Concrete implementation of IAuthenticationStrategy for API Key Authentication.
 *
 * Maps to GET /api/users/2?Test=Test12345 request:
 *   --auth-apikey-key 'Test' --auth-apikey-value 'Test12345' --auth-apikey-in 'query'
 *
 * Supports both "query" and "header" placement of the key (Strategy within a Strategy),
 * decided by config.properties -> auth.apikey.in
 */
public class ApiKeyAuthStrategy implements IAuthenticationStrategy {

    private static final Logger logger = LogManager.getLogger(ApiKeyAuthStrategy.class);

    public enum KeyLocation { QUERY, HEADER }

    private final String keyName;
    private final String keyValue;
    private final KeyLocation location;

    public ApiKeyAuthStrategy() {
        this(ConfigManager.getInstance().getApiKeyName(),
                ConfigManager.getInstance().getApiKeyValue(),
                KeyLocation.valueOf(ConfigManager.getInstance().getApiKeyLocation().toUpperCase()));
    }

    public ApiKeyAuthStrategy(String keyName, String keyValue, KeyLocation location) {
        if (keyName == null || keyName.isBlank()) {
            throw new IllegalArgumentException("API key name must not be null/blank");
        }
        this.keyName = keyName;
        this.keyValue = keyValue;
        this.location = location;
    }

    @Override
    public RequestSpecification applyAuthentication(RequestSpecification requestSpecification) {
        logger.info("Applying API Key Authentication via [{}]", location);
        return switch (location) {
            case QUERY -> requestSpecification.queryParam(keyName, keyValue);
            case HEADER -> requestSpecification.header(keyName, keyValue);
        };
    }

    @Override
    public String getAuthType() {
        return "API_KEY";
    }
}
