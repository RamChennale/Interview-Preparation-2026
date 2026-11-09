package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Loads key/value pairs from src/test/resources/config.properties.
 * A system property of the same name (e.g. -Dbrowser=firefox) always
 * takes precedence, which is how the Jenkins pipeline overrides the browser.
 */
public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try (FileInputStream fis = new FileInputStream("src/test/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load config.properties: " + e.getMessage(), e);
        }
    }

    public static String getProperty(String key) {
        String systemOverride = System.getProperty(key);
        if (systemOverride != null && !systemOverride.isEmpty()) {
            return systemOverride;
        }
        return properties.getProperty(key);
    }
}
