**2. Explain the Selenium WebDriver architecture.**
Four components: 
	(1) Selenium Client Library — language bindings (Java, Python, etc.) that convert your code into commands; 
	(2) JSON Wire Protocol / W3C Protocol — standardized communication format sent over HTTP; 
	(3) Browser Drivers (ChromeDriver, GeckoDriver, EdgeDriver) — translate commands into browser-native calls; 
	(4) Browsers — actually execute the actions. Each driver runs an HTTP server that listens for and executes commands.
