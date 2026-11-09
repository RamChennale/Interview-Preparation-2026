import { test, expect } from '@playwright/test';
// screenshot: 'on' in config file

test('page screenshot', async ({ page }) => {
  await page.goto('https://demo.opencart.com/');
  await page.screenshot({ path: 'tests/screenshots/'+Date.now()+'HomePage.png' });
});

test('Full page screenshot', async ({ page }) => {
  await page.goto('https://demo.opencart.com/');
  await page.screenshot({ path: 'tests/screenshots/'+Date.now()+'FullHomePage.png',fullPage: true});
});

test('Element screenshot', async ({ page }) => {
  await page.goto('https://demo.opencart.com/');
  await page.locator('//*[@id="content"]/div[2]').screenshot({ path: 'tests/screenshots/'+Date.now()+'FeaturedProducts.png'});
});


test('test', async ({ page }) => {
  await page.goto('https://www.demoblaze.com/index.html');
  await page.getByRole('link', { name: 'Log in' }).click();
  await page.locator('#loginusername').fill('pavanol');
  await page.locator('#loginpassword').fill('test@123');
  await page.getByRole('button', { name: 'Log in' }).click();
});

/* 
    video:"retain-on-failure",
*/

test('test video', async ({ page }) => {
  
    await page.goto('https://www.demoblaze.com/index.html');
     await page.getByRole('link', { name: 'Log in' }).click();
    await page.locator('#loginusername').fill('pavanol');
    await page.locator('#loginpassword').fill('test@123');
    await page.getByRole('button', { name: 'Log in' }).click();

    await expect(page.locator('#logout2')).toBeVisible()
});