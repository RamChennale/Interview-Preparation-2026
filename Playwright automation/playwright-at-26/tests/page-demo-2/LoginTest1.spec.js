import { test, expect } from "@playwright/test";

test.skip('add to cart', async({page})=>{
    await page.goto('https://www.saucedemo.com/');
    await page.fill('#user-name','standard_user');
    await page.fill('#password','secret_sauce');
    await page.click('#login-button');

    await page.click('#add-to-cart-sauce-labs-backpack');


    await expect(page.locator('.shopping_cart_badge')).toHaveText('1');

     await page.click('#react-burger-menu-btn');
    await page.click('#logout_sidebar_link');


})


test('remove  to cart', async({page})=>{
    await page.goto('https://www.saucedemo.com/');
    await page.fill('#user-name','standard_user');
    await page.fill('#password','secret_sauce');
    await page.click('#login-button');

    await page.click('#add-to-cart-sauce-labs-backpack');

    await page.locator('.shopping_cart_badge').click();
    await page.locator('#remove-sauce-labs-backpack').click();
    
    await expect(page.locator('.shopping_cart_badge')).toHaveCount(0);

     await page.click('#react-burger-menu-btn');
    await page.click('#logout_sidebar_link');


})

