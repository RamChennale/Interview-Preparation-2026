import {test, expect } from '@playwright/test';

let page;

test.beforeEach(async({browser}) => {
  page = await browser.newPage();
  await page.goto('https://www.demoblaze.com/index.html');
  await page.locator('#login2').click();
  await page.locator('#loginusername').fill('pavanol');
  await page.locator('#loginpassword').fill('test@123');
  await page.locator('//button[normalize-space()="Log in"]').click();
});


test.afterEach(async() => {
  await page.locator('#logout2').click() // logout
});

 test.only('Home Page Test No Of products', async () => {
    const products=await page.$$('#tbodyid .hrefch')
    console.log('Number of products:', await products.length);
  //console.log('Number of products:', products.length)
  expect(products).toHaveLength(9)
  });

  test.skip('Add Product to cart Test', async () => {
    await page.locator('//a[normalize-space()="Samsung galaxy s6"]').click()
   await page.locator('.btn.btn-success.btn-lg').click()
   //handle alert
   page.on('dialog', async dialog=>{
     expect(dialog.message()).toContain('Product added. ')
    await dialog.accept();
      })
 });
