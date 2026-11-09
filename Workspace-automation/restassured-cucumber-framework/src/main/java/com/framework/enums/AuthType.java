package com.framework.enums;

/**
 * Enumerates the supported authentication mechanisms.
 * Used by AuthenticationFactory to decide which strategy to instantiate.
 */
public enum AuthType {
    BASIC,
    JWT,
    API_KEY,
    NONE
}
