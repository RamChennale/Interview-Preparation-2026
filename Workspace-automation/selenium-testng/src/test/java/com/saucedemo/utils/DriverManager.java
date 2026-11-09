package com.saucedemo.utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

/**
 * Central, thread-safe WebDriver factory.
 * Uses a ThreadLocal so parallel TestNG execution (parallel="methods"/"tests" in testng.xml)
 * gets one independent WebDriver instance per thread.
 */
public final class DriverManager {

    private static final ThreadLocal<WebDriver> DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    private DriverManager() {
        // utility class
    }

    public static WebDriver getDriver() {
        return DRIVER_THREAD_LOCAL.get();
    }

    public static void initDriver() {
        String browser = ConfigReader.get("browser", "chrome").toLowerCase();
        //boolean headless = Boolean.parseBoolean(ConfigReader.get("headless", "false"));

        WebDriver driver;
        switch (browser) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions firefoxOptions = new FirefoxOptions();
/*                if (headless) {
                    firefoxOptions.addArguments("--headless");
                }*/
                driver = new FirefoxDriver(firefoxOptions);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();

                // 1. Define complete profile preferences to turn off all password manager flags
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("credentials_enable_service", false);         // Prevents offering to save passwords
                prefs.put("profile.password_manager_enabled", false);     // Disables the password manager completely
                prefs.put("profile.password_manager_leak_detection", false); // Disables the data breach popup
                chromeOptions.setExperimentalOption("prefs", prefs);

                // 2. Add argument switches to kill backend automated security checks
                chromeOptions.addArguments("--disable-features=AutofillServerCommunication");
                chromeOptions.addArguments("--disable-features=PasswordManagerOnboarding");
        /*        if (headless) {
                    chromeOptions.addArguments("--headless=new");
                }*/
                chromeOptions.addArguments(
                        "--start-maximized",
                        "--disable-infobars",
                        "--disable-gpu",
                        "--disable-extensions",
                        "--no-sandbox",
                        "--disable-dev-shm-usage",
                        "--remote-allow-origins=*"
                );
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().deleteAllCookies();
        DRIVER_THREAD_LOCAL.set(driver);
    }

    public static void quitDriver() {
        WebDriver driver = DRIVER_THREAD_LOCAL.get();
        if (driver != null) {
            driver.quit();
            DRIVER_THREAD_LOCAL.remove();
        }
    }
}
