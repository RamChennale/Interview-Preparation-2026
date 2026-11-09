package com.saucedemo.hooks;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.saucedemo.utils.WaitToLoadElement;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.OperatingSystem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Properties;

public class TestNGBaseTest {

    public static WebDriver driver;
    public static WebDriverWait wait;
    public static Properties properties;
    public static String url;
    public static FileInputStream fis;

    public static ExtentSparkReporter extentHtmlReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;

    //=========LINUX Server==========

    @BeforeSuite(enabled = true)
    public void init() {

        WebDriverManager.chromedriver().operatingSystem(OperatingSystem.WIN).setup();
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage");

        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        properties= new Properties();
        try {
            fis  = new FileInputStream(
                    System.getProperty("user.dir") + "/src/test/resources/config.properties");
            properties.load(fis);
        } catch (FileNotFoundException fnfe) {
            fnfe.printStackTrace();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }

        url = properties.getProperty("mtuaturl");
        String username=properties.getProperty("mtuatusername");
        String password=properties.getProperty("mtuatpassword");
        driver.manage().window().maximize();
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

        WebElement userElement= WaitToLoadElement.waitToLoadElement(driver.findElement(By.id("user-name")));
        WebElement passwordElement=WaitToLoadElement.waitToLoadElement(driver.findElement(By.id("password")));
        WebElement login=WaitToLoadElement.waitToLoadElement(driver.findElement(By.id("login-button")));
        userElement.sendKeys(username);
        passwordElement.sendKeys(password);
        login.click();

    }

    @BeforeTest(enabled = true)
    public void reportSetUp() {
        //extentHtmlReporter= new ExtentHtmlReporter(System.getProperty("user.dir")+"/reports/report.html");
        ExtentSparkReporter extentHtmlReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/report.html");
        extentHtmlReporter.config().setReportName("Test Report");
        extentHtmlReporter.config().setTheme(Theme.STANDARD);
       // extentHtmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
        extentReports= new ExtentReports();
        extentReports.attachReporter(extentHtmlReporter);
        extentReports.setSystemInfo("OS", "Windows");
        extentReports.setSystemInfo("Test Type", "Regression Test");
    }

    @BeforeMethod(enabled = true)
    public void getTestCaseName(Method method) {
        String testName=method.getName();
        extentTest=extentReports.createTest(testName);
    }

    @AfterMethod(enabled = true)
    public void testCaseResult(ITestResult result) {
        if(result.getStatus()==ITestResult.SUCCESS) {
            extentTest.log(Status.PASS,"Test case name is : "+result.getName()+" passed.");
        }else if(result.getStatus()==ITestResult.FAILURE) {
            extentTest.log(Status.FAIL, "Test case name is : "+result.getName()+"failed");
            extentTest.log(Status.FAIL, "Test case name is : "+result.getThrowable()+"failed");
        }else if (result.getStatus()==ITestResult.SKIP) {
            extentTest.log(Status.SKIP, "Test case name is : "+result.getName()+"skipped");
        }
    }

    @AfterTest(enabled = true)
    public void generateReport() {
        extentReports.flush();
    }

    @AfterSuite(enabled = true)
    public void tearDown() {
        driver.quit();
    }

    protected WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
/*
    // Oops - Abstraction usage using BasePage 'waitUntilVisible' method
    public void clickCheckoutButton() {
        WebElement checkoutButton = waitUntilVisible(wait.until(ExpectedConditions.presenceOfElementLocated((By.id("1"))));
        waitUntilClickable(checkoutButton).click();
    }
    */

}
