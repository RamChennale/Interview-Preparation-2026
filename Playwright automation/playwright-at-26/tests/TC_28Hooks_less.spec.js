import { test, expect } from '@playwright/test';

test('Home Page Test', async ({ page }) => {
  await page.goto('https://www.demoblaze.com/index.html');

  //Login
  await page.locator('#login2').click();
  await page.locator('#loginusername').fill('pavanol');
  await page.locator('#loginpassword').fill('test@123');
  await page.locator('//button[normalize-space()="Log in"]').click();

  //Home Page - Number of products 
  const products=await page.$$('#tbodyid .hrefch')
  expect(products).toHaveLength(9)

 //Logout
   await page.locator('#logout2').click() // logout

});


test('Add Product to cart Test', async ({ page }) => {
  await page.goto('https://www.demoblaze.com/index.html');

  //Login
  await page.getByRole('link', { name: 'Log in' }).click();
  await page.locator('#loginusername').fill('pavanol');
  await page.locator('#loginpassword').fill('test@123');
  await page.getByRole('button', { name: 'Log in' }).click();

  //Add product to cart
  await page.locator('//a[normalize-space()="Samsung galaxy s6"]').click()
  await page.locator('.btn.btn-success.btn-lg').click()
 
     //handle alert
   page.on('dialog', async dialog=>{
     expect(dialog.message()).toContain('Product added. ')
    await dialog.accept();
      })

 //Logout
   await page.locator('#logout2').click() // logout

});

