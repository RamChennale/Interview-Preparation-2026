package com.saucedemo.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Resolves configuration values with the following precedence:
 *   1) JVM system property (e.g. -Dbrowser=firefox passed via Maven/Jenkins)
 *   2) config.properties on the classpath (src/test/resources/config.properties)
 *   3) the caller-supplied default value
 */
public final class ConfigReader {

    private static final Properties PROPERTIES = new Properties();

    static {
        try (InputStream inputStream =
                     ConfigReader.class.getClassLoader().getResourceAsStream("config.properties")) {
            if (inputStream != null) {
                PROPERTIES.load(inputStream);
            }
        } catch (IOException e) {
            System.err.println("Warning: could not load config.properties - " + e.getMessage());
        }
    }

    private ConfigReader() {
        // utility class
    }

    public static String get(String key, String defaultValue) {
        String systemProperty = System.getProperty(key);
        if (systemProperty != null && !systemProperty.isBlank()) {
            return systemProperty;
        }
        return PROPERTIES.getProperty(key, defaultValue);
    }

    public static String getReportConfigPath(){
        String reportConfigPath = PROPERTIES.getProperty("reportConfigPath");
        if(reportConfigPath!= null) return reportConfigPath;
        else throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }
}
