import { test, expect } from '@playwright/test';
test('context test', async({browser})=>
{
    const context = await browser.newContext();
    const page = await context.newPage();
    await page.goto("https://www.google.com/");
    await expect(page).toHaveTitle(/Google/);
    await context.close();
});