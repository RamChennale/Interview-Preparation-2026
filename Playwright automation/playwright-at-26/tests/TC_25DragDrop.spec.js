const{test, expect} = require('@playwright/test');

// test template-  test('name', async()=>{});

test('Drag&Drop',async({page})=>{
    page.goto('https://testautomationpractice.blogspot.com/');

    const source = await page.locator("//p[normalize-space()='Drag me to my target']");
    const dest = await page.locator("//div[@id='draggable']");

    await source.dragTo(dest);
});