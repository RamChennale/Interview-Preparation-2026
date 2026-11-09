import {test, expect} from '@playwright/test';

test('built in locators', async({page})=>{

    await page.goto('https://www.htmlelements.com/demos/dropdownlist/multiple-selection/index.htm');
    page.getByText("Select items:");
    const options =  await page.locator('ul>li label input');
    await expect(options).toHaveCount(11);


   //slect multiple values 
   for(let option of  options){
    const  value = await option.textContent();

    if(value.includes('java') || value.includes('C')){
        await option.click();
    }
   }

});