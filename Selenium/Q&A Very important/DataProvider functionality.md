#DataProvider 
# Q how to pass headless in DataProvider and use it in test ??

mode = "headless" is the problem (the red ! icon). @DataProvider has no mode attribute. Its valid attributes are name, parallel and indices. To pass headless, add it as a second column in the Object[][], then accept it as a second parameter in the test.


#How to say it in the interview ?

“I use a DataProvider to drive browser and headless as parameters, so one test method covers the matrix. 
In CI the headless flag comes from a system property, so the same code runs headed locally and headless in the pipeline.”

***From Maven:bash
mvn test -Dheadless=false
# or simply
mvn test

***For CI (headless):bash
mvn test -Dheadless=true

----------------------------------------------------------
IMPLEMENTATION:


@DataProvider(name = "browsers")
public Object[][] browsers() {
    return new Object[][] {
        {"chrome",  true},
        {"firefox", true},
        {"edge",    true}
    };
}

@Test(dataProvider = "browsers")
public void testCrossBrowser(String browser, boolean headless) {
    WebDriver driver = createDriver(browser, headless);
    try {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        String heading = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector("h6.oxd-topbar-header-breadcrumb-module"))).getText();
        Assert.assertTrue(heading.contains("Dashboard"),
                "Expected 'Dashboard' heading but found: " + heading);
    } finally {
        driver.quit();   // always close the browser, pass or fail
    }
}

The number of values in each row must match the test’s parameters, in the same order and types. {"chrome", true} maps to (String browser, boolean headless).

Option 2: run every browser in both modes
java
@DataProvider(name = "browsers", parallel = true)
public Object[][] browsers() {
    return new Object[][] {
        {"chrome", false}, {"chrome", true},
        {"firefox", false}, {"firefox", true},
        {"edge", false}, {"edge", true}
    };
}


Option 3 (best for CI): control it from the command line

Hardcoding true means CI can’t change it. Read the value from a system property inside the provider:

java
@DataProvider(name = "browsers")
public Object[][] browsers() {
    boolean headless = Boolean.parseBoolean(System.getProperty("headless", "false"));
    return new Object[][] {
        {"chrome", headless}, {"firefox", headless}, {"edge", headless}
    };
}

Then run mvn test -Dheadless=true in CI and plain mvn test locally.

