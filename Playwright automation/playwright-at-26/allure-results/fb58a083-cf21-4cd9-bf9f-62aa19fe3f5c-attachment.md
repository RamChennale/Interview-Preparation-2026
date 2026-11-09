# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: TC_28Hooks_less.spec.js >> Home Page Test
- Location: tests\TC_28Hooks_less.spec.js:3:5

# Error details

```
Error: expect(received).toHaveLength(expected)

Expected length: 9
Received length: 0
Received array:  []
```

# Page snapshot

```yaml
- generic [ref=e1]:
  - dialog [ref=e2]:
    - document [ref=e3]:
      - generic [ref=e4]:
        - generic [ref=e5]:
          - heading "Log in" [level=5] [ref=e6]
          - button "Close" [ref=e7] [cursor=pointer]: ×
        - generic [ref=e9]:
          - generic [ref=e10]:
            - generic [ref=e11]: "Username:"
            - textbox [ref=e12]: pavanol
          - generic [ref=e13]:
            - generic [ref=e14]: "Password:"
            - textbox [ref=e15]: test@123
        - generic [ref=e17]:
          - button "Close" [ref=e18]
          - button "Log in" [active] [ref=e19]
  - navigation [ref=e20]:
    - link "PRODUCT STORE" [ref=e21] [cursor=pointer]:
      - /url: index.html
    - list [ref=e24]:
      - listitem [ref=e25]:
        - link "Home (current)" [ref=e26] [cursor=pointer]:
          - /url: index.html
          - text: Home
          - generic [ref=e27]: (current)
      - listitem [ref=e28]:
        - link "Contact" [ref=e29] [cursor=pointer]:
          - /url: "#"
      - listitem [ref=e30]:
        - link "About us" [ref=e31] [cursor=pointer]:
          - /url: "#"
      - listitem [ref=e32]:
        - link "Cart" [ref=e33] [cursor=pointer]:
          - /url: cart.html
      - listitem [ref=e34]:
        - link "Log in" [ref=e35] [cursor=pointer]:
          - /url: "#"
      - listitem
      - listitem
      - listitem [ref=e36]:
        - link "Sign up" [ref=e37] [cursor=pointer]:
          - /url: "#"
    - generic [ref=e39]:
      - list [ref=e40]:
        - listitem [ref=e41] [cursor=pointer]
        - listitem [ref=e42] [cursor=pointer]
        - listitem [ref=e43] [cursor=pointer]
      - img "First slide" [ref=e46]
      - button "Previous" [ref=e47] [cursor=pointer]
      - button "Next" [ref=e50] [cursor=pointer]
  - generic [ref=e54]:
    - generic [ref=e56]:
      - link "CATEGORIES" [ref=e57] [cursor=pointer]:
        - /url: ""
      - link "Phones" [ref=e58] [cursor=pointer]:
        - /url: "#"
      - link "Laptops" [ref=e59] [cursor=pointer]:
        - /url: "#"
      - link "Monitors" [ref=e60] [cursor=pointer]:
        - /url: "#"
    - list [ref=e63]:
      - listitem [ref=e64]:
        - button "Previous" [ref=e65]
      - listitem [ref=e66]:
        - button "Next" [ref=e67] [cursor=pointer]
  - generic [ref=e69]:
    - generic [ref=e72]:
      - heading "About Us" [level=4] [ref=e73]
      - paragraph [ref=e74]: We believe performance needs to be validated at every stage of the software development cycle and our open source compatible, massively scalable platform makes that a reality.
    - generic [ref=e77]:
      - heading "Get in Touch" [level=4] [ref=e78]
      - paragraph [ref=e79]: "Address: 2390 El Camino Real"
      - paragraph [ref=e80]: "Phone: +440 123456"
      - paragraph [ref=e81]: "Email: demo@blazemeter.com"
    - heading "PRODUCT STORE" [level=4] [ref=e85]
  - contentinfo [ref=e87]:
    - paragraph [ref=e88]: Copyright © Product Store
```

# Test source

```ts
  1  | import { test, expect } from '@playwright/test';
  2  | 
  3  | test('Home Page Test', async ({ page }) => {
  4  |   await page.goto('https://www.demoblaze.com/index.html');
  5  | 
  6  |   //Login
  7  |   await page.locator('#login2').click();
  8  |   await page.locator('#loginusername').fill('pavanol');
  9  |   await page.locator('#loginpassword').fill('test@123');
  10 |   await page.locator('//button[normalize-space()="Log in"]').click();
  11 | 
  12 |   //Home Page - Number of products 
  13 |   const products=await page.$$('#tbodyid .hrefch')
> 14 |   expect(products).toHaveLength(9)
     |                    ^ Error: expect(received).toHaveLength(expected)
  15 | 
  16 |  //Logout
  17 |    await page.locator('#logout2').click() // logout
  18 | 
  19 | });
  20 | 
  21 | 
  22 | test('Add Product to cart Test', async ({ page }) => {
  23 |   await page.goto('https://www.demoblaze.com/index.html');
  24 | 
  25 |   //Login
  26 |   await page.getByRole('link', { name: 'Log in' }).click();
  27 |   await page.locator('#loginusername').fill('pavanol');
  28 |   await page.locator('#loginpassword').fill('test@123');
  29 |   await page.getByRole('button', { name: 'Log in' }).click();
  30 | 
  31 |   //Add product to cart
  32 |   await page.locator('//a[normalize-space()="Samsung galaxy s6"]').click()
  33 |   await page.locator('.btn.btn-success.btn-lg').click()
  34 |  
  35 |      //handle alert
  36 |    page.on('dialog', async dialog=>{
  37 |      expect(dialog.message()).toContain('Product added. ')
  38 |     await dialog.accept();
  39 |       })
  40 | 
  41 |  //Logout
  42 |    await page.locator('#logout2').click() // logout
  43 | 
  44 | });
  45 | 
  46 | 
```