**7. How do you handle iframes and multiple windows/tabs?**
For iframes: `driver.switchTo().frame(index/name/WebElement)`, then `driver.switchTo().defaultContent()` to return. 
For windows: `driver.getWindowHandles()` (a `Set<String>`) before triggering the new window, then iterate and `switchTo().window(handle)` 
for the one not matching the original handle.
