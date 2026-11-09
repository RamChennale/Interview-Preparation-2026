import {test, expect} from '@playwright/test';
//const {test, expect} = require('@playwright/test');

test('locators test', async({page})=>
{
    await page.goto("https://www.google.com/");
    await expect(page).toHaveTitle(/Google/);

    // property, css xpath 

    
    await page.locator('#ti6dpd').fill('ram chennale');
    await page.click('#ti6dpd');
});

test('login test', async({page})=>{

    await page.goto('https://www.saucedemo.com/');

    await page.locator('#user-name').fill('standard_user');
    await page.locator('#password').fill('secret_sauce');
    await page.locator('#login-button').click();


})