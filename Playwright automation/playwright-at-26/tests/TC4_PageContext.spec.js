import { test, expect } from '@playwright/test';
// const {test, expect} = require('@playwright/test'); - SAME As above line 

//Destructuring: const { test, expect } = require(...) is 
// plain JavaScript destructuring of the returned object. 
// import { test, expect } is special import syntax, 
// and it can rename with as:

test('browser → context → page test', async({browser})=>
{
    const context = await browser.newContext();
    const page = await context.newPage();

    await page.goto("https://www.google.com/");
    await expect(page).toHaveTitle(/Google/);
    await context.close();
});


test('test page', async({page})=>
    {
      await page.goto("https://www.google.com/");
      await expect(page).toHaveTitle(/Google/);  
      await page.context().close();
    })


test('test page context', async({page, context})=>
    {
      await page.goto("https://www.google.com/");
      await expect(page).toHaveTitle(/Google/);  
      await context.close();
    })


