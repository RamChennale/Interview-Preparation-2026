package com.framework.auth;

import com.framework.enums.AuthType;

/**
 * Factory design pattern.
 * Decouples client code (API clients / step definitions) from the concrete
 * auth-strategy classes. Adding a new auth type later (e.g. OAuth2) only
 * requires a new case here + a new class implementing IAuthenticationStrategy -
 * Open/Closed Principle in action.
 */
public class AuthenticationFactory {

    // Private constructor - this is a static utility/factory class, never instantiated
    private AuthenticationFactory() {
    }

    public static IAuthenticationStrategy getStrategy(AuthType authType) {
        return switch (authType) {
            case BASIC -> new BasicAuthStrategy();
            case JWT -> new JwtAuthStrategy();
            case API_KEY -> new ApiKeyAuthStrategy();
            case NONE -> new NoAuthStrategy();
        };
    }
}
