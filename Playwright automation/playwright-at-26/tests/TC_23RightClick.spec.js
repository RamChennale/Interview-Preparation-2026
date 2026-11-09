const{test, expect} = require('@playwright/test');

// test template-  test('name', async()=>{});

test.skip('right click', async({page})=>{

    page.goto('http://swisnl.github.io/jQuery-contextMenu/demo.html');

    const rightClick =  page.getByText('right click me')
    await rightClick.click({button:'right'});
    await page.waitForTimeout(3000);
});



test.skip('double click', async({page})=>{
    await page.goto('https://testautomationpractice.blogspot.com/');
    const dblocator = await page.locator('//button[normalize-space()="Copy Text"]');
    dblocator.dblclick();
    const f2= await page.locator('#field2')
    await expect(f2).toHaveValue('Hello World!');
    await page.waitForTimeout(5000)
});

test('Drag&Drop',async({page})=>{
    page.goto('https://testautomationpractice.blogspot.com/');

    const source = await page.locator("//p[normalize-space()='Drag me to my target']");
    const dest = await page.locator("//div[@id='draggable']");

    await source.dragTo(dest);
});