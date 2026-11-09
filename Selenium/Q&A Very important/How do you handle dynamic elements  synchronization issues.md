**3. How do you handle dynamic elements / synchronization issues?**
Never use `Thread.sleep()`. 
	Use **Explicit Waits** (`WebDriverWait` with `ExpectedConditions` like `visibilityOfElementLocated`, `elementToBeClickable`) 
	for element-specific conditions, and 
	
	**Fluent Wait** when you need custom polling intervals and to ignore specific exceptions (e.g., `NoSuchElementException`) during retries. 
	
	For SPA apps with AJAX, also consider waiting on JavaScript `document.readyState` or a custom condition polling a network-idle state via CDP.

##SPA apps with AJAX
A Single-Page Application (SPA)-  loads one HTML page and updates its content dynamically using AJAX (Asynchronous JavaScript and XML). 
	Instead of reloading the whole page for new data, AJAX fetches data behind the scenes so the app can change parts of the screen instantly.


Note:	
Set a global **Implicit Wait** conservatively (or avoid mixing it with explicit waits — mixing causes unpredictable timeouts). 


