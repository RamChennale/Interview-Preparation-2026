const { test, expect } = require('@playwright/test');

test('Keyboard actions', async ({ page }) => {
    await page.goto('https://gotranscript.com/text-compare');
    
    await page.type('[name="text1"]', 'Welcome to automation');

    // Ctrl + A - Select
    await page.keyboard.press('Control+A');// on Windows and Linux
    //await page.keyboard.press('Meta+A'); // on macOS
    
    //Ctrl + V  - Copy
    await page.keyboard.press('Control+C'); // on macOS
    
    //key down  - Press Tab key
    await page.keyboard.down('Tab')
    await page.keyboard.up('Tab')

    //Ctrl + V  - Paste
    await page.keyboard.press('Control+V'); // on macOS
    
    await page.waitForTimeout(5000);

});


