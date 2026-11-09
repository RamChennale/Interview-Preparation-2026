const {test, expect}=require('@playwright/test')

test('Auto suggest dropdown', async ({page}) =>{
    await page.goto('https://www.redbus.in/')

    await page.locator("//label[normalize-space()='From']").fill('Delhi');

    await page.waitForSelector("//*[@id='srcinput' and @value='Delhi']");

    // select options from dropdown
    const fromCityOptions=await page.$$("//*[@id='srcinput' and @value='Delhi']")
    for(let option of fromCityOptions)
    {
        const value=await option.textContent();
       // console.log("value is",value)
        /*if(value.includes('Anand Vihar'))
        {
            await option.click();
            break
        }*/

    }

    await page.waitForTimeout(5000);
})