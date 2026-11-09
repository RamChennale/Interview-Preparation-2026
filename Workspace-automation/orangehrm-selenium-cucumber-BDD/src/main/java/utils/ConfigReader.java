package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
    to loads test data (application URL, username, password) from config.properties file
 */
public class ConfigReader {

    private static final Properties properties = new Properties();
    private static final String path = "src/test/resources/config.properties";

    static {
        try (FileInputStream fis = new FileInputStream(path)) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config file at " + path, e);
        }
    }
/*
    private ConfigReader() {

    }*/

    public static String get(String key) {
        String value = properties.getProperty(key);
        if (value == null) {
            throw new RuntimeException("Property '" + key + "' not found in config.properties");
        }
        return value;
    }
}
