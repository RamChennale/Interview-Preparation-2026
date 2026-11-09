#WebDriverWait_DefaultWaitTime500ms
**Interview tip:** 
`WebDriverWait` is a subclass of `FluentWait` with a default polling of 500 ms and `NotFoundException` ignored automatically. 
`FluentWait` lets you customize the polling and which exceptions to ignore.




The default polling interval for an explicit wait (`WebDriverWait`) is **500 milliseconds** (half a second).

`WebDriverWait` re-checks your condition every 500 ms until it returns true or the timeout expires.

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
```

Here it checks the condition roughly every 0.5 s, for up to 10 s.

## Changing it

```java
WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
wait.pollingEvery(Duration.ofMillis(200));
```

Or with `FluentWait`, where you set it explicitly:

```java
Wait<WebDriver> wait = new FluentWait<>(driver)
    .withTimeout(Duration.ofSeconds(30))
    .pollingEvery(Duration.ofSeconds(2))
    .ignoring(NoSuchElementException.class);
```

## Related defaults

| Wait type | Default timeout | Default polling |
|---|---|---|
| `WebDriverWait` | none set by you (you pass it) | 500 ms |
| `FluentWait` | you set it | 500 ms |
| Implicit wait | 0 s | handled by the driver |

**Interview tip:** `WebDriverWait` is a subclass of `FluentWait` with a default polling of 500 ms and `NotFoundException` ignored automatically. `FluentWait` lets you customize the polling and which exceptions to ignore.


======================================================================

    public WaitUtils(WebDriver driver){
        TestBase.driver = driver;
    }

    @Test
    public void testWait() {
        driver.get("https://www.google.com/");
        WebElement search = waitForClickable(By.id("ti6dpd"));
        search.sendKeys("selenium");
        search.click();
    }

    public WebElement waitForClickable(By locator){

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(DefaultTimeOut))
                .pollingEvery(Duration.ofMillis(200))
                .ignoring(NoSuchElementException.class)
                .ignoring(StaleElementReferenceException.class);
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

