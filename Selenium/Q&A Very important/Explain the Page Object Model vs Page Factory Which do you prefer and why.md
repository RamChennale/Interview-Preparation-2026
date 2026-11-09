**10. Explain the Page Object Model vs Page Factory. Which do you prefer and why?**
	**POM structures** each page as a class with locators + methods, decoupling test logic from UI structure. 
	**Page Factory** (`@FindBy` annotations + `PageFactory.initElements()`) adds lazy initialization of elements via Java proxies. 
	In 2026, many senior engineers actually **avoid Page Factory** because `@FindBy` elements are cached by default(causing `StaleElementReferenceException` 
	on SPA re-renders) unless you wrap the driver in an `AjaxElementLocatorFactory`. 
	Many teams prefer plain POM with `By` locators resolved fresh on each interaction, or move to the **Screenplay Pattern** 
	