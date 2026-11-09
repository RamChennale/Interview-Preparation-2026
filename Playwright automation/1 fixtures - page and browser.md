
#what is the diff fixtures 
test('page test', async({page})=>{});

test('page test', async({browser})=>{});


Both are Playwright fixtures, which are objects the test runner creates and hands to your test. The difference is how much setup Playwright does for you.

{ page }													{ browser }
What you receive	A ready-to-use browser tab				The browser instance itself
Context	Created automatically	You create it: 				browser.newContext()
Page	Created automatically	You create it: 				context.newPage()
Cleanup	Automatic after the test							You close the context yourself (best practice)
Config applied	baseURL, storageState, trace, video, screenshot, viewport from playwright.config.js	Not applied unless you pass options to newContext()
Typical use	95% of tests	Special scenarios



#Rule of thumb: 
use { page } by default, 
use { context } for multiple tabs, and 
use { browser } only when you need multiple isolated sessions or custom context options.

#Under the hierarchy
browser  →  context (isolated session)  →  page (tab)

Using page (simple):

test('page test', async ({ page }) => {
  await page.goto('https://www.google.com/');
  await expect(page).toHaveTitle(/Google/);
});


Using browser (manual):

test('browser test', async ({ browser }) => {
  const context = await browser.newContext();
  const page = await context.newPage();

  await page.goto('https://www.google.com/');
  await expect(page).toHaveTitle(/Google/);

  await context.close();
});