import {test, expect} from '@playwright/test';
//const {test, expect} = require('@playwright/test');

test('built in locators', async({page})=>{

    await page.goto('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login');

   
    await expect(page).toHaveURL('https://opensource-demo.orangehrmlive.com/web/index.php/auth/login');

    await expect(page).toHaveTitle('');
    

    //num of op in DD
    const  options = await page.$$('#country options');
    console.log('num of op', options.length());

    await expect(options.length).toBe(10);

    //3 presence of element or valu ein DD - approch-1
    const allDDValues  =    await page.locator('#country').textContent(); 
    await expect(allDDValues.includes('India')).toBeTruthy(); // assertion to verify value exist in DD returns true / false    

    // or by loop presence of element or valu in DD - approch-2
    const options1 = await page.$$('#country options');
    let status  = false;

    for(const option of  options1){
       // console.log(await option.textContent()); printing all values 
       let value = await option.textContent(); 
       if(value.includes('France')){
        status = true;
        break;
       } 
       expect(status).toBeTruthy();
    }

    //5 th select option from DD using loop

     const options2 = await page.$$('#country options');
    for(const option of  options2){
       let value = await option.textContent(); 
       if(value.includes('France')){
       await page.selectOption("#ccountry", value);
        break;
    }
    }

});