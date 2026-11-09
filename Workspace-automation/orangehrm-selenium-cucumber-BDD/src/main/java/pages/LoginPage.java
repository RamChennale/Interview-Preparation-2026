package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
Page Object for the OrangeHRM Login page.
 locators & action methods
 */
public class LoginPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By usernameField = By.name("username");
    private final By passwordField = By.name("password");
    private final By loginButton = By.xpath("//button[@type='submit']");
    private final By dashboardHeader = By.xpath("//h6[text()='Dashboard']");
    private final By errorMessage = By.xpath("//p[text()='Invalid credentials']");

    private final By userAccount = By.xpath("//p[@class='oxd-userdropdown-name']");
    private final By logOut = By.xpath("//a[normalize-space()='Logout']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void enterUsername(String username) {
        waitVisible(usernameField).sendKeys(username);
    }

    public void enterPassword(String password) {
        waitVisible(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        waitVisible(loginButton).click();
    }

    public void login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }

    public boolean isDashboardDisplayed() {
        try {
            return waitVisible(dashboardHeader).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getErrorMessage() {
        return waitVisible(errorMessage).getText();
    }

    public void logOut() {
        waitVisible(userAccount).click();
        waitVisible(logOut).click();
    }
}
