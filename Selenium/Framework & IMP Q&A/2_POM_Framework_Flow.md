# POM Framework (Without PageFactory): Complete Flow

## 1. The idea in one paragraph

Tests extend `TestBase`, which owns the browser lifecycle: it creates a Chrome driver before each test method and quits it afterwards, through `DriverManager`, which stores the driver in a `ThreadLocal`. Page classes such as `LoginPage_1` extend `BasePage_1`, which owns the reusable interactions: explicit waits, `enterInput`, `click` and `getText`. A page class holds only `By` locators and business-level actions like `login(user, pass)`. The test holds only the steps and the assertions. Because locators are `By` objects rather than `WebElement` fields, the element is found fresh at the moment of use, so there is no `PageFactory.initElements` and no held reference that can go stale.

## 2. Who owns what

| Class | Responsibility | Knows about |
|---|---|---|
| `DriverManager_1` | Create, store and quit the driver (`ThreadLocal`) | Chrome options, WebDriverManager |
| `TestBase_1` | Test lifecycle: `@BeforeMethod` setup, `@AfterMethod` teardown | `DriverManager_1` |
| `BasePage_1` | Generic actions and waits (10s explicit wait) | `WebDriver`, `By` |
| `LoginPage_1` | Login page locators and actions | `BasePage_1` |
| `LoginTest_1` | Test steps and assertions only | `TestBase_1`, `LoginPage_1` |

## 3. Structure

```
LoginTest_1  ──extends──▶  TestBase_1  ──calls──▶  DriverManager_1
     │                                                   (ThreadLocal<WebDriver>)
     │ creates new LoginPage_1(driver)
     ▼
LoginPage_1  ──extends──▶  BasePage_1
 (By locators,              (waitVisible, waitClickable,
  login(), getErrorMessage)  enterInput, click, getText)
```

## 4. Step-by-step: what happens when `validLoginShouldOpenDashboard` runs

1. **TestNG creates `LoginTest_1`.** It needs a no-argument constructor, and the class has an implicit one.
2. **`@BeforeMethod setUp()` runs** (inherited from `TestBase_1`). It calls `DriverManager_1.initDriver()`.
3. **`initDriver()` builds the browser.** It sets up chromedriver, applies Chrome options (password manager popups off, download folder), launches `ChromeDriver`, maximizes the window, saves the driver in the `ThreadLocal` and returns it.
4. **`TestBase_1` stores the driver** in its `driver` field, so the test can use it.
5. **The test body starts.** `driver.get(URL)` opens the OrangeHRM login page.
6. **`new LoginPage_1(driver)`** calls `super(driver)`, so `BasePage_1` saves the driver and creates a `WebDriverWait` of 10 seconds.
7. **`loginPage.login("Admin", "admin123")`** runs three actions:
   - `enterInput(usernameField, ...)`: `waitVisible(By)` waits for the field, then `clear()` and `sendKeys()`.
   - `enterInput(passwordField, ...)`: same flow for the password.
   - `click(loginButton)`: `waitClickable(By)` waits until the button is clickable, then clicks.
8. **`isLoginSuccessful()`** waits up to 10 seconds for the URL to contain `dashboard` and returns `true`, or `false` on timeout.
9. **`Assert.assertTrue(...)`** passes or fails the test. The assertion lives in the test, not in the page.
10. **`@AfterMethod tearDown()` runs**, even if the test failed (`alwaysRun = true`). `DriverManager_1.quitDriver()` quits the browser and removes the `ThreadLocal` entry, so nothing leaks into the next test.

The invalid-login test follows the same flow, except step 8 becomes `getErrorMessage()`, which waits for the red alert text, and the assertion compares it with `"Invalid credentials"`.

## 5. Is this the right approach?

**Yes, the structure is right.** It is a clean, standard POM, and a good interview answer:

- Clear separation: tests hold no locators, pages hold no assertions.
- `By` locators with explicit waits avoid stale references and support dynamic locators.
- Fresh browser per test (`@BeforeMethod`/`@AfterMethod`) gives test isolation.
- `ThreadLocal` driver in `DriverManager_1` is the correct basis for parallel runs.
- No implicit wait, so there is no mixing with explicit waits.

## 7. Interview answer (30 seconds)

> "My framework has three layers. `DriverManager` creates the browser and keeps it in a `ThreadLocal` so it is thread-safe. `TestBase` runs setup and teardown per test method. Page classes extend `BasePage`, which wraps explicit waits and actions, and they hold only `By` locators and business methods like `login`. Tests contain just the steps and assertions. I use `By` instead of `@FindBy` so elements are located fresh each time, which avoids stale references in dynamic apps and lets me build parameterized locators. If I scaled it, I'd add config files, reporting, screenshots on failure, and retry handling for stale elements in `BasePage`."

## 8. One-line summary

**Test → TestBase (browser lifecycle) → Page (locators + business actions) → BasePage (waits + interactions) → WebDriver.**
