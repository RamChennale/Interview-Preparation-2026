const { test, expect, chromium } = require('@playwright/test');

test.skip('Handle Pages/Windows', async () => {
    // Launch the browser
    const browser = await chromium.launch();
  
    // Create a new context (a single browsing session)
    const context = await browser.newContext();
  
    // Create two pages
    const page1 = await context.newPage();
    await page1.goto("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login")
    console.log(await page1.title());
    await expect(page1).toHaveTitle('OrangeHRM')

    // Start waiting for new page before clicking. 
    const pagePromise = context.waitForEvent('page');
    await page1.locator('//a[normalize-space()="OrangeHRM, Inc"]').click();

    const newPage = await pagePromise;
    console.log(await newPage.title());
    await expect(newPage).toHaveTitle('OrangeHRM: All in One HR Software for Businesses | OrangeHRM')

    await page1.waitForTimeout(2000);
    await newPage.waitForTimeout(2000);

    // Get pages of a browser context
    const allPages = context.pages();
    console.log("No.OF pages created.....",allPages.length)

    await browser.close();
        
  })

  test('test2', async({page})=>{
      
    await page.goto('https://the-internet.herokuapp.com/windows');

    await expect(page.locator('h3')).toHaveText('Opening a new window');

    //array destructuring - take the first elemtn form array and store 
    const [childPage] = await Promise.all([ //operation-1

    //st2
    page.context().waitForEvent('page'), page.locator('text=Click Here').click()]);

    // st-3
    await childPage.waitForLoadState()
    console.log('child windw', await childPage.title());

  //st4 verify new child wind 
  await expect(childPage.locator('h3')).toHaveText('New Window')

  //st5
  page.bringToFront();
  console.log('back to parent page', page.title());
  
  //st6
  await childPage.close();
  });