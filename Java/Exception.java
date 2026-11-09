
## 16. Exceptions

**Exception hierarchy:**
```
Throwable
 ├── Error (JVM-level, not meant to be handled — e.g., OutOfMemoryError)
 └── Exception
      ├── Checked Exception (must be handled/declared — e.g., IOException, SQLException)
      └── Unchecked/Runtime Exception (not enforced at compile time — e.g., NullPointerException, ArrayIndexOutOfBoundsException)
```

**Checked vs Unchecked:**
- **Checked**: Compiler forces you to handle with try-catch or `throws` (e.g., `IOException`, `InterruptedException`).
- **Unchecked**: Occur at runtime, not checked at compile time (e.g., `NullPointerException`, `ArithmeticException`, `ClassCastException`).

**Common Selenium exceptions asked about:**
- `NoSuchElementException` — element not found in DOM
- `ElementNotInteractableException` — element present but not interactable (hidden/disabled)
- `StaleElementReferenceException` — element reference is no longer attached to the DOM (page was refreshed/reloaded)
- `TimeoutException` — explicit wait condition not met within given time
- `NoSuchWindowException` / `NoAlertPresentException`

**Try-Catch-Finally example:**
```java
try {
    driver.findElement(By.id("nonexistent")).click();
} catch (NoSuchElementException e) {
    System.out.println("Element not found: " + e.getMessage());
} finally {
    System.out.println("This block always executes");
}
```

**Custom Exception example:**
```java
public class InvalidLoginException extends Exception {
    public InvalidLoginException(String message) {
        super(message);
    }
}
```
