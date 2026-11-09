# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: page-demo-2\LoginTest1.spec.js >> remove  to cart
- Location: tests\page-demo-2\LoginTest1.spec.js:21:5

# Error details

```
Error: expect(locator).toHaveText(expected) failed

Locator: locator('.shopping_cart_badge')
Expected: "0"
Timeout: 5000ms
Error: element(s) not found

Call log:
  - Expect "toHaveText" locator('.shopping_cart_badge') with timeout 5000ms
  - waiting for locator('.shopping_cart_badge')

```

```yaml
- banner:
  - button "Open Menu"
  - img "Open Menu"
  - text: Swag Labs
  - button "Cart, empty"
  - text: Your Cart
- main:
  - text: QTY Description
  - button "Continue Shopping"
  - button "Checkout"
- contentinfo:
  - list:
    - listitem:
      - link "X":
        - /url: https://x.com/saucelabs
    - listitem:
      - link "Facebook":
        - /url: https://www.facebook.com/saucelabs
    - listitem:
      - link "LinkedIn":
        - /url: https://www.linkedin.com/company/sauce-labs/
  - text: © 2026 Sauce Labs. All Rights Reserved. Terms of Service | Privacy Policy
```

# Test source

```ts
  1  | import { test, expect } from "@playwright/test";
  2  | 
  3  | test.skip('add to cart', async({page})=>{
  4  |     await page.goto('https://www.saucedemo.com/');
  5  |     await page.fill('#user-name','standard_user');
  6  |     await page.fill('#password','secret_sauce');
  7  |     await page.click('#login-button');
  8  | 
  9  |     await page.click('#add-to-cart-sauce-labs-backpack');
  10 | 
  11 | 
  12 |     await expect(page.locator('.shopping_cart_badge')).toHaveText('1');
  13 | 
  14 |      await page.click('#react-burger-menu-btn');
  15 |     await page.click('#logout_sidebar_link');
  16 | 
  17 | 
  18 | })
  19 | 
  20 | 
  21 | test('remove  to cart', async({page})=>{
  22 |     await page.goto('https://www.saucedemo.com/');
  23 |     await page.fill('#user-name','standard_user');
  24 |     await page.fill('#password','secret_sauce');
  25 |     await page.click('#login-button');
  26 | 
  27 |     await page.click('#add-to-cart-sauce-labs-backpack');
  28 | 
  29 |     await page.locator('.shopping_cart_badge').click();
  30 |     await page.locator('#remove-sauce-labs-backpack').click();
  31 |     
> 32 |     await expect(page.locator('.shopping_cart_badge')).toHaveText('0');
     |                                                        ^ Error: expect(locator).toHaveText(expected) failed
  33 | 
  34 |      await page.click('#react-burger-menu-btn');
  35 |     await page.click('#logout_sidebar_link');
  36 | 
  37 | 
  38 | })
  39 | 
  40 | 
```