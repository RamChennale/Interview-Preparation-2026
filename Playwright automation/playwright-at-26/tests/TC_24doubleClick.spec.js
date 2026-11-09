const{test, expect} = require('@playwright/test');

// test template-  test('name', async()=>{});

test.skip('double click', async({page})=>{
    await page.goto('https://testautomationpractice.blogspot.com/');
    const dblocator = await page.locator('//button[normalize-space()="Copy Text"]');
    dblocator.dblclick();
    const f2= await page.locator('#field2')
    await expect(f2).toHaveValue('Hello World!');
    await page.waitForTimeout(5000)
});