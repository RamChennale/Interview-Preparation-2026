package com.framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ConfigManager - Singleton design pattern.
 * Loads config.properties exactly once and exposes typed accessors.
 * Encapsulation: the Properties object is private; access is only via getters.
 */
public final class ConfigManager {

    private static volatile ConfigManager instance;
    private final Properties properties;

    // Private constructor -> prevents external instantiation (Singleton)
    private ConfigManager() {
        properties = new Properties();
        loadProperties();
    }

    // Thread-safe double-checked locking singleton accessor
    public static ConfigManager getInstance() {
        if (instance == null) {
            synchronized (ConfigManager.class) {
                if (instance == null) {
                    instance = new ConfigManager();
                }
            }
        }
        return instance;
    }

    private void loadProperties() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties")) {
            if (input == null) {
                throw new RuntimeException("config.properties not found in classpath");
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public String get(String key) {
        // system property override allows Jenkins to pass -Dkey=value at runtime
        String override = System.getProperty(key);
        return override != null ? override : properties.getProperty(key);
    }

    public String getBaseUrl() {
        return get("base.url");
    }

    public String getJwtToken() {
        return get("auth.jwt.token");
    }

    public String getBasicUsername() {
        return get("auth.basic.username");
    }

    public String getBasicPassword() {
        return get("auth.basic.password");
    }

    public String getApiKeyName() {
        return get("auth.apikey.key");
    }

    public String getApiKeyValue() {
        return get("auth.apikey.value");
    }

    public String getApiKeyLocation() {
        return get("auth.apikey.in"); // "query" or "header"
    }

    public int getTimeoutSeconds() {
        String val = get("request.timeout.seconds");
        return val != null ? Integer.parseInt(val) : 30;
    }
}
