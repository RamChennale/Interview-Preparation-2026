const { test, expect } = require('@playwright/test');

/*
test('Select checkbox of 2nd row in web table', async ({ page }) => {
  await page.goto('https://testautomationpractice.blogspot.com/');

  // 2nd row of the table body (Laptop)
  const secondRowCheckbox = page.locator('#productTable tbody tr:nth-child(2) input[type="checkbox"]');

  await secondRowCheckbox.check();

  // Verify it is selected
  await expect(secondRowCheckbox).toBeChecked();

  await page.waitForTimeout(2000);
});

*/

test('Select checkbox of 2nd row in web table', async ({ page }) => {
  await page.goto('https://testautomationpractice.blogspot.com/');

  // Using Playwright's nth() (0-based index)
await page.locator('#productTable tbody tr').nth(1).locator('input[type="checkbox"]').check();

// Using the row's text (more stable if row order changes)
await page
  .locator('#productTable tbody tr', { hasText: 'Smartwatch' })
  .locator('input[type="checkbox"]')
  .check();
 
  await page.waitForTimeout(2000);
});


