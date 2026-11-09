#### Interview Questions & Answers (Automation Testing)
linux home path:
\\wsl.localhost\Ubuntu\home\ram_chennale
============================================================================================================================================================

============================================================================================================================================================

============================================================================================================================================================
***DONE 
## Difference between get and navigate in Selenium

Both load a URL and wait for the page to load (per page load strategy).
- driver.get() is a simple method on WebDriver to open a page.
- driver.navigate() returns the Navigation interface, which also provides back(), forward(), and refresh().

Feature            | driver.get("url")             | driver.navigate().to("url")
Interface          | Method on WebDriver           | Method on Navigation
Page load waiting  | Waits for page load           | Same behavior
Parameter types    | String only                   | String or URL object
History            | Page added to history         | Same
Extra commands     | None                          | back(), forward(), refresh()

============================================================================================================================================================
***DONE 
#1. Normalizing Text in Locators (XPath)

If an HTML element has leading/trailing whitespaces, tabs, or newlines, a standard text match will fail. 
You can use the XPath function normalize-space(). This function strips leading and trailing whitespaces and converts multiple consecutive internal spaces into a single space
ex:

<button>   Click   Me!   </button>

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

// Matches "Click Me!" by stripping extra spaces
By buttonLocator = By.xpath("//button[normalize-space()='Click Me!']");
WebElement button = driver.findElement(buttonLocator);


============================================================================================================================================================
***DONE 
##What are challenges faced during selenium automation?

1. Report: No reporting API/capabilities available need to use 3rd party tools like TestNG and Extent reporting
2. Dealing with pop-up never know what kind of pop-up will be (
	java script, 
	window pop-up, 
	model pop-up or 
	Browser pop-up)
3. *** Time out resulting from synchronization problem: 
	When to use implicit wait, explicit wait sometime explicit wait also does not works need to use JavaScriptExecutor also need to check element 
	is present, is enabled and is selected we will get StaleElementException, ElementNotFoudException, ElementNotVisibleException where
We must know how to use implicit, explicit, fluent wait then JavaScriptExecutor is the solution where we should never use Thread.sleep().
4. Handling Ajax Components: Asynchronous call 
	Example: on home page 2 dropdown
	Country: USA
	State: California
	On changing country INDIA then a valid wait need to reset the state dropdown and Indian state sometime the USA state only displayed.



============================================================================================================================================================
***DONE 
##What are disadvantages of selenium/ what are all we cannot automate?

1. Images: Images or text overlapping issue cannot verified.
2. Capatch: No facility to capture capatch and Bar code.
3. Non web: Does not support non web application.
4
5. Report: No direct reporting API/capabilities available need to use 3rd party tools like TestNG and Extent reporting
6. API testing - No: Selenium does not natively support API testing. It is strictly a browser automation tool built to replicate user actions on a web UI
7. Android Mobile: does not support testing native or hybrid Android mobile apps. 
	To automate native Android apps, you must use a Selenium-derived framework like Appium or Selendroid, which use the same WebDriver protocol



============================================================================================================================================================
***DONE 
## Q. How do you manage cross-browser and headless execution in CI?
**Answer:** Abstract driver creation behind a `DriverFactory` that reads browser type from config/CLI param, never hardcoded.

```java
public final class DriverFactory {

    private DriverFactory() {}

    public static WebDriver createDriver(String browser, boolean headless) {
        switch (browser.toLowerCase()) {
            case "chrome": {
                ChromeOptions options = new ChromeOptions();
                if (headless) {
                    options.addArguments("--headless=new", "--window-size=1920,1080");
                }
                return new ChromeDriver(options);
            }
            case "firefox": {
                return new FirefoxDriver();
            }
            case "edge": {
                return new EdgeDriver();
            }
            default: {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
    }
}
```
CI (Jenkins/GitHub Actions) passes `-Dbrowser=chrome -Dheadless=true`; local devs run headed for debugging.



============================================================================================================================================================
### Q. How do you handle multiple browser tabs/windows and iframes?
**Answer:**
```java 
String parentWindow = driver.getWindowHandle();
driver.switchTo().newWindow(WindowType.TAB);
// ... actions on new tab
driver.switchTo().window(parentWindow);

// iframe
driver.switchTo().frame("paymentFrame");
driver.findElement(By.id("cardNumber")).sendKeys("4111111111111111");
driver.switchTo().defaultContent();
```
**Real use:** Payment gateway pages (Stripe/PayPal iframes), SSO popups (Okta/Azure AD login windows) — very common in e-commerce/fintech automation.

---


============================================================================================================================================================
### Q7. Page Object Model vs Page Factory — differences, and why you picked one.
**Answer:** 
**POM** 		is the design pattern (page = class, elements = fields, actions = methods). 
**PageFactory** is Selenium's utility (`@FindBy` + `initElements()`) that implements lazy initialization of elements using a proxy.

```java
public class LoginPage {
    WebDriver driver;

    @FindBy(id = "username") WebElement usernameField;
    @FindBy(id = "password") WebElement passwordField;
    @FindBy(css = "button[type='submit']") WebElement loginBtn;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public HomePage login(String user, String pass) {
        usernameField.sendKeys(user);
        passwordField.sendKeys(pass);
        loginBtn.click();
        return new HomePage(driver);
    }
}
```
**Real trade-off discussed in interviews:** PageFactory's `@FindBy` elements are lazily located but **not automatically re-located** 
after a stale reference in some Selenium versions unless you use `@CacheLookup` carefully (avoid on dynamic elements). 
Many senior engineers now prefer plain `driver.findElement()` inside methods (no PageFactory) precisely to sidestep stale element quirks in SPA
 apps — **this is a strong, real answer that shows experience**, not just textbook knowledge.

---


============================================================================================================================================================

### Q9. How do you achieve thread-safe parallel execution across 50+ tests on Grid?
**Answer:** The #1 mistake juniors make is a single static `WebDriver driver` field — this breaks the moment you run tests in parallel. 
The fix is `ThreadLocal`.

```java
public class DriverManager {
    private static ThreadLocal<WebDriver> driverThread = new ThreadLocal<>();

    public static WebDriver getDriver() { return driverThread.get(); }

    public static void setDriver(WebDriver driver) { driverThread.set(driver); }

    public static void unload() { driverThread.remove(); }
}
```

```java
@BeforeMethod
public void setUp() {
    WebDriver driver = DriverFactory.createDriver(System.getProperty("browser"), false);
    DriverManager.setDriver(driver);
}

@AfterMethod
public void tearDown() {
    DriverManager.getDriver().quit();
    DriverManager.unload(); // prevents memory leak / thread bleed between tests
}
```
**Real impact:** This exact change took a nightly regression suite from 3.5 hours (sequential) to 25 minutes across a 5-node Selenium Grid running 8 threads/node 
	— a number I'd actually quote in an interview to show real impact.



============================================================================================================================================================

### Q10. How do you handle test data management for a Data-Driven framework?
**Answer:** External data sources (Excel via Apache POI, JSON via Jackson, or DB) feeding TestNG's `@DataProvider`, never hardcoded in test methods.

```java
@DataProvider(name = "loginData", parallel = true)
public Object[][] getLoginData() throws IOException {
    return ExcelUtils.getTestData("LoginData.xlsx", "Sheet1");
}

@Test(dataProvider = "loginData")
public void testLogin(String username, String password, String expectedResult) {
    LoginPage loginPage = new LoginPage(DriverManager.getDriver());
    loginPage.login(username, password);
    Assert.assertEquals(HomePage.getWelcomeMessage(), expectedResult);
}
```
**Real use:** Banking app regression — 40 combinations of valid/invalid login (locked account, expired password, wrong OTP, SSO redirect) 
	driven from one Excel sheet, maintained by manual QA without touching Java code.



============================================================================================================================================================
Rapid-Fire Concept Checks (expect these as warm-ups)

| Question | One-line answer |
|---|---|
| `findElement` vs `findElements` 	| Throws `NoSuchElementException` vs returns empty list |
| `close()` vs `quit()` 			| Closes current window vs kills entire driver session + all windows |
| Absolute vs relative XPath		| `/html/body/div[1]` (brittle) vs `//div[@class='x']` (resilient) |
| `@SoftAssert` vs `Assert` 		| Soft collects all failures, reports at `assertAll()`; hard assert stops test immediately |
| `@Factory` in TestNG 				| Creates multiple instances of a test class dynamically with different data, unlike `@DataProvider` which reuses one instance |
| Selenium 3 vs 4 					| Selenium 4 is fully W3C-compliant (no JSON Wire translation layer), native relative locators (`above()`, `near()`), improved Grid UI, CDP integration for network/console logs |
| Handling dynamic web tables | Loop `findElements(By.xpath("//table//tr"))`, use dynamic XPath with `contains()`/`text()` matching row/column index at runtime |
| Action class use case | Mouse hover, drag-drop, right-click, keyboard combos (`Keys.CONTROL + "a"`) |
| JavascriptExecutor use case | Scroll into view, click hidden/overlapped elements, set value bypassing readonly, get page load status |

```java
JavascriptExecutor js = (JavascriptExecutor) driver;
js.executeScript("arguments[0].scrollIntoView(true);", element);
js.executeScript("arguments[0].click();", element); // fallback when normal click is intercepted
```

---

## How to Use This in the Interview

- Don't just recite definitions — for every answer, add the **"why"** and a **number/impact** if you have one 
(e.g., "reduced suite time from 3.5h to 25 min," "cut flaky failures from 15% to <1%").
- Be ready to **draw the framework architecture** on a whiteboard/shared doc: 
	Driver Manager → Page Objects → Test Layer → Utils → Data → Config → Reporting → CI/CD.
- Expect a live coding round: writing a `DriverFactory`, a `ThreadLocal` driver manager, a `RetryAnalyzer`, or a `FluentWait` utility from scratch is very common at this level.
- Have 1-2 real "flaky test war stories" and 1 "framework redesign" story ready — these get asked almost every time (see Q20).



============================================================================================================================================================



