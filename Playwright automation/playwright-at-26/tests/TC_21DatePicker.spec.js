const { test, expect } = require('@playwright/test');

test("Date Picker", async ({ page }) => {
    const year = '2024';
    const month = 'March';
    const date = '20';

    await page.goto("https://testautomationpractice.blogspot.com/");
    //await page.fill('#datepicker','07/15/2023') //direct
    
    await page.click('#datepicker');

    while (true) {
        const currentYear = await page.locator('.ui-datepicker-year').textContent();
        const currentMonth = await page.locator('.ui-datepicker-month').textContent();

        if (currentYear === year && currentMonth === month) {
            break;
        }

          //await page.click("[title='Next']");
          await page.click("[title='Prev']");
         //await page.waitForTimeout(1000);
    }

    //date selection - using loop
    const dates = await page.$$("//a[@class='ui-state-default']");
    for (const dt of dates) {
        if (await dt.textContent() === date) {
            await dt.click();
        }
    }
    

    //date selection - direct - no loop
    //await page.click(`//a[@class='ui-state-default'][text()='${date}']`);

    //await page.waitForTimeout(3000);
});
