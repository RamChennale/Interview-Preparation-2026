import {test, expect} from '@playwright/test';
//const {test, expect} = require('@playwright/test');

test('built in locators', async({page})=>{

    await page.goto('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login');

    //const logo =  await  page.getByAltText('company-branding');
    //await expect(logo).toBeVisible(); 

    const un = await page.getByPlaceholder("Username").fill('Admin');
    const pwd = await page.getByPlaceholder("Password").fill('admin123');
     const login = await page.getByRole('button',{type:'submit'}).click();
});