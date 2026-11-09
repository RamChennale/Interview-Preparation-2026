import { test, expect } from '@playwright/test';

test('testAutoDropDownSearch', async ({ page }) => {
    // 1. Navigate to Google
    await page.goto('https://www.google.com/');

    // 2. Locate the search box and type 'selenium'
    const searchInput = page.locator('textarea[name="q"]');
    await searchInput.fill('selenium');

    // 3. Locate all the list items in the dropdown menu
    const dropdownItems = page.locator('ul[role="listbox"] li');

    // 4. Wait for suggestions to appear (instead of a fixed sleep), then count
    await dropdownItems.first().waitFor({ state: 'visible' });
    const count = await dropdownItems.count();

    // 5. Loop through items to find the exact match and click it
    for (let i = 0; i < count; i++) {
        const currentItem = dropdownItems.nth(i);
        const itemText = await currentItem.innerText();

        if (itemText.trim().toLowerCase() === 'selenium') {
            console.log(itemText);
            await currentItem.click();

            // Verify the search actually happened
            await expect(page).toHaveURL(/q=selenium/);
            break;
        }
    }
});