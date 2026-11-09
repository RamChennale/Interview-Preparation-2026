const {test, expect} = require('@playwright/test');
test('context test', async({browser})=>
{
    const context = await browser.newContext();
    const page = await context.newPage();
    await page.goto("https://www.google.com/");
    await expect(page).toHaveTitle(/Google/);
    await context.close();
});

const { test1 } = require('@playwright/test');
test('page context test', async ({ browser }) => {
  const context = await browser.newContext();
  const page = await context.newPage();

  await page.goto('https://www.saucedemo.com/');
  await expect(page).toHaveTitle(/Swag Labs/);

  await context.close();
});