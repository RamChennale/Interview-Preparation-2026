**11. How do you deal with `StaleElementReferenceException`?**

“StaleElementReferenceException happens when the element reference I’m holding is no longer attached to the DOM, typically after an AJAX update or re-render. 
The locator is still valid, but the object I’m holding points to a node that no longer exists.

My approach is layered. 

First, 
		in my Page Object design I store By locators rather than WebElement fields, so every interaction does a fresh lookup. 
Second, 
		I use explicit waits like ExpectedConditions.refreshed(...) or elementToBeClickable. 
Third, 
		in the BasePage I have a retry wrapper for known dynamic areas like grids and filtered tables, which retries two or three times on this exception.



// Approach 1: re-locate with refreshed wait
    protected void clickWithRefresh(By locator) {
        wait.until(ExpectedConditions.refreshed(
                        ExpectedConditions.elementToBeClickable(locator)))
                .click();
    }

    // Approach 2: retry wrapper
    protected void clickWithRetry(By locator) {
        int attempts = 0;
        while (attempts < 3) {
            try {
                wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
                return;
            } catch (StaleElementReferenceException e) {
                attempts++;
                if (attempts == 3) throw e;
            }
        }
    }
	
#Q: How is this different from NoSuchElementException?
“NoSuchElementException means the element isn’t there. Stale means it was there, and I held a reference, but that node has been replaced or removed.”	








old- 
	It happens when the DOM element reference becomes invalid (page reload, AJAX re-render, DOM manipulation). 
	Fix by re-locating the element right before interaction rather than caching it, 
	wrapping the action in a small retry loop that catches `StaleElementReferenceException` and re-finds the element, 
	or using explicit waits tied to `ExpectedConditions.refreshed(...)`.


what diff storing element in By locators rather than WebElement fields?