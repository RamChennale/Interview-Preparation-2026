import {test, expect} from '@playwright/test';
import{SloginPage} from './SloginPage.js';

test('login Test', async({page})=>{
    const login = new SloginPage(page);
    await login.goToLoginPage();
    await login.login('standard_user','secret_sauce');
     await expect(page).toHaveTitle('Swag Labs');
})