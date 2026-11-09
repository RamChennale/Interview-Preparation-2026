import {test, expect} from '@playwright/test';
//const {test, expect} = require('@playwright/test');

test('login test', async({page})=>{

    await page.goto('https://www.saucedemo.com/');

    await page.locator('#user-name').fill('standard_user');
    await page.locator('#password').fill('secret_sauce');
    await page.locator('#login-button').click();

    //const elements =  await page.$$('button');
    page.waitForSelector("//div[@class='inventory_list']//div//div//div//a"); // optional wait to loadd all elements 
    
    const names = await page.$$("//div[@class='inventory_list']//div//div//div//a");

    for(const link of names){
        const linkText = await link.textContent();
        console.log(linkText);
    }

});