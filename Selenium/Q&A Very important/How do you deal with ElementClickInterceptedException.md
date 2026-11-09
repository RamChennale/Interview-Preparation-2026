#ElementClickInterceptedException 

“This happens when Selenium finds the element and it’s visible, but another element is sitting on top of it at the click point. 
Typical culprits are loading overlays, modal backdrops, sticky headers, cookie banners, or toast notifications.

The important thing is to diagnose what’s intercepting. The exception message names the element that received the click. 
My approach is to wait for the blocker to go away, for example the spinner to become invisible, then scroll the target into the center of the viewport, 
so a sticky header doesn’t cover it, and then click. I use a JavaScript click only as a last resort, because it bypasses real user behavior. 
If a real user can’t click that button, the test shouldn’t pass.

import static com.selenium.A1_seleniumInterviewEx.selenium26.base.DriverManager.wait;

public class ElementClickInterceptedExceptionHandling extends TestBase_1 {

    protected void safeClick(By locator) {
        By overlay = By.cssSelector(".loading-overlay, .modal-backdrop");
        wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));

        WebElement el = wait.until(ExpectedConditions.elementToBeClickable(locator));
        try {
            el.click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView({block:'center'});", el);
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        }
    }
}



## ExpectedConditions.invisibilityOfElementLocated(overlay) ->
An expectation for checking that an element is either invisible or not present on the DOM.
Params:
locator – used to find the element
Returns:
true if the element is not displayed or the element doesn't exist or stale element
