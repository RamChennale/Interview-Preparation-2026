### Q3. How do you handle `StaleElementReferenceException` in a framework used by 50+ testers?
**Answer:** Build a retry wrapper around element actions rather than asking every author to handle it individually.

```java
public void clickWithRetry(By locator) {
    int attempts = 0;
    while (attempts < 3) {
        try {
            driver.findElement(locator).click();
            return;
        } catch (StaleElementReferenceException e) {
            attempts++;
        }
    }
    throw new FrameworkException("Element still stale after 3 retries: " + locator);
}
```
**Real use:** This came from a real production issue where a Single Page App (React) re-rendered the DOM mid-test and caused 15% flaky failures in the nightly regression suite — this pattern brought it down to <1%.

---

