const { test, expect, chromium } = require('@playwright/test');

test('Handle Pages/Windows', async () => {
    // Launch the browser
    const browser = await chromium.launch();
  
    // Create a new context (a single browsing session)
    const context = await browser.newContext();
  
    // Create two pages
    const page1 = await context.newPage();
    const page2 = await context.newPage();
  
    // Get pages of a browser context
    const allPages = context.pages();
    console.log("No.OF pages created.....",allPages.length)

    await page1.goto("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
    await expect(page1).toHaveTitle('OrangeHRM')
   
    await page2.goto("https://Google.com")
    await expect(page2).toHaveTitle('Google')

    await page1.waitForTimeout(3000)
    await page2.waitForTimeout(3000)

    await browser.close()
        
  })