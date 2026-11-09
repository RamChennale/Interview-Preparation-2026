#How do you handle file upload/download in Selenium
***upload - input type=file
For upload, if there’s an ***input type=file*** I use sendKeys with the absolute path, with a JS unhide if it’s hidden and LocalFileDetector on a grid. 
I avoid Robot or AutoIT because they break in headless and CI. 

***download - Chrome prefs
For download, I set Chrome prefs for a fixed directory with no prompt, poll with a fluent wait until the file is complete, then validate existence, size, and content. 
I use a unique folder per test so parallel runs don’t clash.


Examples:

// Upload
WebElement input = driver.findElement(By.cssSelector("input[type='file']"));
input.sendKeys("/absolute/path/report.csv");

// Hidden input: unhide first
((JavascriptExecutor) driver).executeScript(
    "arguments[0].style.display='block';", input);

// Remote grid
((RemoteWebDriver) driver).setFileDetector(new LocalFileDetector());


// Download setup
Map<String, Object> prefs = Map.of(
    "download.default_directory", downloadDir.toString(),
    "download.prompt_for_download", false,
    "safebrowsing.enabled", true);
ChromeOptions options = new ChromeOptions();
options.setExperimentalOption("prefs", prefs);

// Wait for the file
new FluentWait<>(downloadDir)
    .withTimeout(Duration.ofSeconds(30))
    .pollingEvery(Duration.ofMillis(500))
    .until(dir -> {
        try (var files = Files.list(dir)) {
            return files.anyMatch(f -> f.toString().endsWith(".csv"));
        } catch (IOException e) { return false; }
    });
