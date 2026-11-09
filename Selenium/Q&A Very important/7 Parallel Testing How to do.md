## 7. Parallel Testing (How to)

**Purpose**: Run multiple test classes/methods simultaneously to reduce execution time.

**Using TestNG `testng.xml`:**
```xml
<suite name="ParallelSuite" parallel="methods" thread-count="3">
    <test name="Test1">
        <classes>
            <class name="tests.LoginTest"/>
            <class name="tests.SearchTest"/>
        </classes>
    </test>
</suite>
```
- `parallel="methods"` → each `@Test` method runs in a separate thread
- `parallel="classes"` → each class runs in a separate thread
- `parallel="tests"` → each `<test>` each tag runs in parallel from TestNG.xml file 
- `thread-count` → number of threads/instances to use

**Key requirement**: WebDriver must be made **thread-safe** using `ThreadLocal<WebDriver>`:
```java

EXAMPLE ThreadLocal IMPELEMENTATION

public class DriverManager {
	
 // 1. Declare the ThreadLocal WebDriver container
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    @BeforeMethod
    public void setUp(){
        // 2. Initialize a new ChromeDriver instance for the current thread
        WebDriver driver = new ChromeDriver();

        // 3. Store the instance inside the ThreadLocal container
        driverThreadLocal.set(driver);
    }

    // 4. Create a public getter to access the driver instance safely
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    @AfterMethod
    public void tearDown() {
        // 5. Retrieve the thread-specific driver instance and close it
        if (getDriver() != null) {
            getDriver().quit();
        }
        // 6. Prevent memory leaks by removing the reference from the current thread
        driverThreadLocal.remove();
    }
}
    @Test
    public void Test_1() {
        // Use getDriver() to launch the application
        getDriver().get("https://www.google.com");
        System.out.println("Thread 1 Title: " + getDriver().getTitle());
    }



```
This ensures each thread gets its own driver instance, avoiding conflicts.

Also mention: parallel execution across browsers/machines is achieved via **Selenium Grid** or cloud tools (BrowserStack/Sauce Labs).

---
