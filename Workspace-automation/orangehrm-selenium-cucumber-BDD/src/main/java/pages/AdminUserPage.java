package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Page Object for the OrangeHRM Admin > User Management screens
 *
 * Add User
 */
public class AdminUserPage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By adminMenu = By.xpath("//span[text()='Admin']");
    private final By addButton = By.xpath("//button[normalize-space()='Add']");
    private final By selectDropdowns = By.className("oxd-select-text-input"); // 0 = User Role, 1 = Status
    private final By employeeNameInput = By.xpath("//label[text()='Employee Name']/following::input[1]");
    private final By usernameInput = By.xpath("//label[text()='Username']/following::input[1]");
    private final By passwordFields = By.cssSelector("input[type='password']"); // 0 = Password, 1 = Confirm Password
    private final By saveButton = By.cssSelector("button[type='submit']");
    private final By successToast = By.xpath("//div[contains(@class,'oxd-toast--success')]");
    private final By usernameSearchInput = By.xpath("(//label[text()='Username']/following::input)[1]");
    private final By searchButton = By.xpath("//button[@type='submit']");
    private final By resultTableRows = By.xpath("//div[@class='oxd-table-body']//div[@role='row']");

    public AdminUserPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private WebElement waitVisible(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private WebElement waitClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //  Navigation
    public void clickAdminMenu() {
        waitClickable(adminMenu).click();
    }

    //  Add User
    public void clickAddButton() {
        waitClickable(addButton).click();
    }

    public void selectUserRole(String roleValue) throws InterruptedException {
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(selectDropdowns, 0));
        List<WebElement> dropdowns = driver.findElements(selectDropdowns);
        System.out.println("--------------dropdowns.size()"+dropdowns.size());
        dropdowns.get(0).click();
        System.out.println(" getText() : " +dropdowns.get(0).getText());
        By optionLocator = By.xpath(String.format(
                "//div[@role='listbox']//span[normalize-space()='%s']", roleValue));

        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        System.out.println(option.getText());
        option.click();
    }

    public void enterEmployeeName(String namePrefix) {
        waitVisible(employeeNameInput).sendKeys(namePrefix);
    }

    public void selectEmployeeFromSuggestion(String employeeText) {
        By optionLocator = By.xpath(String.format(
                "//div[contains(@class,'oxd-autocomplete-option')][contains(.,'%s')]", employeeText));
        waitVisible(optionLocator).click();
    }

    public void selectStatus(String statusValue) {
        List<WebElement> dropdowns = driver.findElements(selectDropdowns);
        dropdowns.get(1).click();

        System.out.println(" getText() : " +dropdowns.get(1).getText());
        By optionLocator = By.xpath(String.format(
                "//div[@role='listbox']//span[normalize-space()='%s']", statusValue));
        WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));
        System.out.println(option.getText());
        option.click();

    }

    public void enterUsername(String username) {
        waitVisible(usernameInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        List<WebElement> fields = driver.findElements(passwordFields);
        fields.get(0).sendKeys(password);
    }

    public void enterConfirmPassword(String confirmPassword) {
        List<WebElement> fields = driver.findElements(passwordFields);
        fields.get(1).sendKeys(confirmPassword);
    }

    public void clickSaveButton() {
        waitClickable(saveButton).click();
    }

    public boolean isUserAddedSuccessfully() {
        try {
            return waitVisible(successToast).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    // Users search
    public void searchByUsername(String username) {
        waitVisible(usernameSearchInput).sendKeys(username);
        waitClickable(searchButton).click();
    }

    public boolean searchResultsContain(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(resultTableRows));
        List<WebElement> rows = driver.findElements(resultTableRows);
        for (WebElement row : rows) {
            if (row.getText().contains(username)) {
                return true;
            }
        }
        return false;
    }
}
