**6. How do you handle Shadow DOM elements?**
Selenium 4 provides native support via `WebElement.getShadowRoot()`, which returns a `SearchContext` you can query with `findElement`/`findElements` 
	just like the regular DOM. For nested shadow roots, you traverse them recursively.
