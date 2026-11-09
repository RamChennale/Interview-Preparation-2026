##Playwright Interview Questions General and Core Concepts


# Playwright (JavaScript) Interview Answers: Capital Markets / Wealth / Trading

Each answer has a **say-it-out-loud** line, a short explanation, and an example tied to the domain (EQ, Funds, Terms, Bonds). Examples use a hypothetical 
broker/dealer portal.

---

# SECTION 1: Fundamentals (Q1–Q10)

## 1. What is Playwright?
- **Say it:** "Playwright is an open-source end-to-end automation framework from Microsoft. One API drives Chromium, Firefox and WebKit, and it has auto-waiting built in."
- It talks to browsers over a persistent WebSocket connection, not through an HTTP driver, so it is fast and less flaky.
- **Domain angle:** Trading and wealth UIs are dynamic, with live blotters, order tickets and portfolio grids. Auto-waiting and web-first assertions make tests on
 those screens far more stable.

## 2. Key features of Playwright

| Feature 				| Why it matters in capital markets |
|--------------------	|---|
| Auto-waiting 			| Order grids and price tiles render asynchronously |
| Cross-browser (Chromium, Firefox, WebKit) | Brokers and dealers use different browsers |
| Browser contexts 		| Test Broker vs Dealer vs Admin roles in isolation |
| Network interception 	| Mock market data and fail pricing APIs |
| Trace Viewer, video, screenshots | Fast root-cause analysis for failures |
| Parallel execution (workers) | Large regression suites finish quickly |
| Codegen 				| Quickly record a flow, then refactor into POM |
| API testing (`request`)| Seed or validate data without the UI |
| Mobile emulation 		| Wealth apps used on tablet or phone |

## 3. Languages supported
- JavaScript / TypeScript (the most feature-rich, with its own `@playwright/test` runner)
- Python, Java and .NET (C#)
- **Say it:** "I use JavaScript/TypeScript because the native test runner gives me fixtures, parallelism, retries and reporting out of the box. I also have Java and RestAssured experience, so I can work in a polyglot team."

## 4. Playwright vs Selenium

| Aspect 		| Playwright 							| Selenium |
|---			|---									|---|
| Architecture 	| WebSocket, direct browser protocol	| HTTP via WebDriver |
| Waiting 		| Auto-wait on actionability 			| Explicit and implicit waits (manual) |
| Speed and flakiness | Faster, fewer flaky tests 		| Slower, more sync issues |
| Isolation 	| Lightweight browser contexts 			| New browser or driver instance |
| Tooling 		| Trace Viewer, codegen, UI mode built in | Needs third-party tools |
| Network Mocking | Native `page.route` 				| Needs a proxy or DevTools |
| Browser support | Chromium, Firefox, WebKit 			| Also supports legacy browsers (IE) |
| Language support | JS/TS, Python, Java, .NET 			| Wider (Ruby, PHP, etc.) |

**Experience line:** "I've built Selenium + TestNG and Cucumber frameworks, and moving to Playwright removed most of our explicit-wait code and reduced flaky regression failures."

## 5. Components of Playwright
```
Playwright → Browser → BrowserContext → Page → Frame → Locator
```
- **Playwright Library** (`playwright`) – automation API
- **Playwright Test** (`@playwright/test`) – runner, fixtures, assertions, reporters
- **Browser** – Chromium / Firefox / WebKit instance
- **BrowserContext** – isolated session
- **Page** – a single tab
- **Locator** – element finder with auto-wait and retry
- **Tools** – Codegen, Trace Viewer, Inspector, UI Mode

## 6. What is a BrowserContext?
- An isolated, incognito-like session with its own cookies, storage and cache. Creating one takes milliseconds.
- **Example:** Test a trade approval flow with two roles at once.

```javascript
const brokerCtx = await browser.newContext({ storageState: 'auth/broker.json' });
const dealerCtx = await browser.newContext({ storageState: 'auth/dealer.json' });
const brokerPage = await brokerCtx.newPage();
const dealerPage = await dealerCtx.newPage();
// Broker places an order, dealer approves it, with no session leakage
```

## 7. What is a Locator?
- A lazy, auto-waiting, auto-retrying way to find elements. It resolves to the element only when an action is performed.
- Preferred order: `getByRole` > `getByLabel` > `getByTestId` > CSS/XPath.

```javascript
const buyBtn   = page.getByRole('button', { name: 'Buy' });
const isinInput = page.getByLabel('ISIN');
const row = page.getByRole('row', { name: /INE002A01018/ });  // Reliance EQ row
await row.getByRole('button', { name: 'Trade' }).click();
```

## 8. How is auto-waiting implemented?
Before every action, Playwright runs **actionability checks**:
- Element is **attached**, **visible**, **stable** (not animating), **enabled**, and **receives events** (not covered by another element).
- It retries until the timeout (default 30s for actions) and then fails with a clear log.
- **Example:** On clicking "Submit Order", Playwright waits until the button is enabled after validation completes, so no `Thread.sleep` is needed.

## 9. Role of `expect`?
- Web-first assertions that **auto-retry** until the condition is met or the timeout (5s default) expires.

```javascript
await expect(page.getByTestId('order-status')).toHaveText('FILLED');
await expect(page.getByRole('row')).toHaveCount(5);
await expect(page).toHaveURL(/portfolio/);
await expect.soft(page.getByTestId('nav')).toBeVisible();   // soft: continue on failure
await expect.poll(async () => (await request.get('/orders/1')).json())
            .toMatchObject({ status: 'EXECUTED' });          // polling API state
```
- **Key point:** Use `expect(locator)` (retrying) instead of `expect(await locator.textContent())` (one-shot, flaky).

## 9a/10. Page vs Context

| Page 						| BrowserContext |
|---						|---|
| A single tab or window 	| A session container |
| Has a URL and DOM 		| Holds cookies, storage, permissions, geolocation |
| Many pages per context 	| Many contexts per browser |
| Shares session with sibling pages | Fully isolated from other contexts |

---

# SECTION 2: Browser and Element Interactions (Q11–Q25)

## 11. Handling a browser
```javascript
const { chromium } = require('@playwright/test');
const browser = await chromium.launch({ headless: false, channel: 'chrome' });
const context = await browser.newContext({ viewport: { width: 1920, height: 1080 } });
const page = await context.newPage();
await page.goto('https://broker-portal.example.com');
await browser.close();
```
In the test runner, the `browser`, `context` and `page` fixtures handle this lifecycle automatically.

## 12. Headless mode
- The browser runs without a UI, so it is faster and uses fewer resources. It is the default on CI.
- Use headed mode or `--debug` locally to investigate failures.
- `npx playwright test --headed`

## 13. Screenshots
```javascript
await page.screenshot({ path: 'portfolio.png', fullPage: true });
await page.getByTestId('holdings-grid').screenshot({ path: 'holdings.png' });
```
Config: `use: { screenshot: 'only-on-failure' }`

## 14. Video recording
```javascript
// playwright.config.js
use: { video: 'retain-on-failure' }   // 'on' | 'off' | 'on-first-retry'
```
Videos are saved in `test-results/` and attached to the HTML report.

## 15. Multiple tabs
```javascript
const [newTab] = await Promise.all([
  context.waitForEvent('page'),
  page.getByRole('link', { name: 'View Factsheet' }).click()   // MF factsheet opens in new tab
]);
await newTab.waitForLoadState();
await expect(newTab.getByRole('heading')).toContainText('Fund Details');
await page.bringToFront();
```

## 16. Popups
```javascript
const [popup] = await Promise.all([
  page.waitForEvent('popup'),
  page.getByText('Terms & Conditions').click()
]);
await popup.getByRole('button', { name: 'Accept' }).click();
```
Browser-native dialogs are covered in Q30.

## 17. Frames
```javascript
const frame = page.frameLocator('#chart-iframe');       // e.g. embedded price chart
await frame.getByRole('button', { name: '1M' }).click();
await expect(frame.locator('.chart-title')).toContainText('NIFTY 50');
```
`frameLocator` is auto-waiting and supports nested frames: `frameLocator('#a').frameLocator('#b')`.

## 18. Parallel execution
```javascript
// playwright.config.js
module.exports = defineConfig({
  fullyParallel: true,
  workers: process.env.CI ? 4 : undefined,
});
```
- Files run in parallel by default. `fullyParallel` also parallelises tests within a file.
- `test.describe.configure({ mode: 'serial' })` for dependent flows such as place order → modify → cancel.
- **Domain tip:** Parallel tests must not share the same client, account or ISIN position. Use unique test data per worker (`testInfo.workerIndex`).

## 19. Retries
```javascript
retries: process.env.CI ? 2 : 0
```
- CLI: `npx playwright test --retries=2`
- Per test: `test.describe.configure({ retries: 2 })`
- **Say it:** "Retries are a safety net, not a fix. I use `trace: 'on-first-retry'` to diagnose, then fix the root cause."

## 20. Capturing API responses
```javascript
const [response] = await Promise.all([
  page.waitForResponse(r => r.url().includes('/api/orders') && r.status() === 201),
  page.getByRole('button', { name: 'Place Order' }).click()
]);
const body = await response.json();
expect(body.orderStatus).toBe('PENDING_APPROVAL');
```
This validates that the UI and the backend agree on the order ID, quantity and price.

## 21. Mocking APIs
```javascript
await page.route('**/api/marketdata/quote*', route =>
  route.fulfill({
    status: 200,
    contentType: 'application/json',
    body: JSON.stringify({ isin: 'INE002A01018', ltp: 2500.50, change: '+1.2%' })
  })
);
await page.goto('/watchlist');
await expect(page.getByTestId('ltp-INE002A01018')).toHaveText('2,500.50');
```
Useful for deterministic prices, circuit-limit scenarios and bond-yield edge cases, and when market data is unavailable.

## 22. Authentication
Log in once in a **setup project** and reuse the `storageState`:
```javascript
// auth.setup.js
setup('login as broker', async ({ page }) => {
  await page.goto('/login');
  await page.getByLabel('User ID').fill(process.env.BROKER_USER);
  await page.getByLabel('Password').fill(process.env.BROKER_PASS);
  await page.getByRole('button', { name: 'Login' }).click();
  await page.context().storageState({ path: 'auth/broker.json' });
});
// config: projects: [{ name:'setup', testMatch:/auth\.setup\.js/ },
//   { name:'chromium', use:{ storageState:'auth/broker.json' }, dependencies:['setup'] }]
```
- Credentials come from environment variables or a secret manager, never hard-coded.
- For MFA or OTP, use a test-env bypass or an API token.

## 23. CI/CD integration
```yaml
# GitHub Actions
- uses: actions/checkout@v4
- uses: actions/setup-node@v4
  with: { node-version: 20 }
- run: npm ci
- run: npx playwright install --with-deps
- run: npx playwright test --shard=${{ matrix.shard }}/4
- uses: actions/upload-artifact@v4
  if: always()
  with: { name: report, path: playwright-report/ }
```
- Works the same in **Jenkins** (`sh 'npx playwright test'` in the Jenkinsfile) and Azure DevOps.
- Use **sharding** for large suites, publish the HTML report and traces as artifacts, and gate PR merges on a smoke tag (`--grep @smoke`).

## 24. Debugging
- `npx playwright test --debug` opens the Inspector with step-through.
- `PWDEBUG=1` or `await page.pause()` to stop at a point.
- **UI Mode:** `npx playwright test --ui` gives a time-travel view with watch mode.
- **Trace Viewer** for post-mortem analysis (Q36).
- VS Code extension: run, set breakpoints, and pick locators live.
- `DEBUG=pw:api` for verbose logs.

## 25. Reports
```javascript
reporter: [['html', { open: 'never' }], ['junit', { outputFile: 'results.xml' }], ['list']]
```
- Built-in: HTML, JUnit, JSON, line, list, dot.
- Third-party: **Allure** (`allure-playwright`) for trend dashboards.
- `npx playwright show-report` opens the HTML report.

---

# SECTION 3: Automation Strategies and Scenarios (Q26–Q40)

## 26. Page Object Model (POM)
```javascript
// pages/OrderTicketPage.js
class OrderTicketPage {
  constructor(page) {
    this.page = page;
    this.isin   = page.getByLabel('ISIN');
    this.qty    = page.getByLabel('Quantity');
    this.price  = page.getByLabel('Limit Price');
    this.submit = page.getByRole('button', { name: 'Submit' });
    this.status = page.getByTestId('order-status');
  }
  async placeLimitOrder(isin, qty, price) {
    await this.isin.fill(isin);
    await this.qty.fill(String(qty));
    await this.price.fill(String(price));
    await this.submit.click();
  }
}
module.exports = { OrderTicketPage };

// test
const ticket = new OrderTicketPage(page);
await ticket.placeLimitOrder('INE002A01018', 100, 2500);
await expect(ticket.status).toHaveText('PENDING');
```
Benefits: reuse, a single place to fix locators, and readable tests. Combine with **custom fixtures** to inject page objects.

## 27. Waiting strategies
- **Auto-wait** (default) handles most cases.
- `await expect(locator).toBeVisible()` for web-first assertions.
- `page.waitForResponse / waitForRequest` for network sync.
- `page.waitForURL('**/confirmation')` for navigation.
- `locator.waitFor({ state: 'hidden' })` for spinners.
- `page.waitForLoadState('networkidle')` as a last resort only.
- `expect.poll` or `expect(...).toPass()` for retrying custom logic.
- **Avoid** `page.waitForTimeout()`.

## 28. File upload
```javascript
await page.getByLabel('Upload KYC').setInputFiles('data/kyc.pdf');
await page.getByLabel('Upload Bulk Orders').setInputFiles(['a.csv', 'b.csv']);
// Non-input (custom button) uploads:
const [chooser] = await Promise.all([
  page.waitForEvent('filechooser'),
  page.getByText('Browse').click()
]);
await chooser.setFiles('data/orders.csv');
```
Use case: bulk trade-file upload, client onboarding documents.

## 29. Scrolling
```javascript
await page.getByRole('row', { name: /Bond-2031/ }).scrollIntoViewIfNeeded();
await page.mouse.wheel(0, 2000);
await page.evaluate(() => window.scrollTo(0, document.body.scrollHeight));
```
For virtualised grids (large holdings), search or filter first, or scroll the container until the row renders. Playwright auto-scrolls to elements before clicking.

## 30. Alerts and dialogs
```javascript
page.once('dialog', async dialog => {
  expect(dialog.message()).toContain('Confirm cancel order?');
  await dialog.accept();      // or dialog.dismiss()
});
await page.getByRole('button', { name: 'Cancel Order' }).click();
```
Register the handler **before** the action that triggers the dialog. Playwright auto-dismisses dialogs if there is no handler.

## 31. Drag and drop
```javascript
await page.getByText('Equity').dragTo(page.getByTestId('portfolio-panel'));
// Manual control for tricky widgets:
await src.hover();
await page.mouse.down();
await target.hover();
await page.mouse.up();
```
Use case: reordering watchlist columns or building a model portfolio allocation.

## 32. Network failures
```javascript
await page.route('**/api/orders', route => route.abort('failed'));
await page.getByRole('button', { name: 'Place Order' }).click();
await expect(page.getByRole('alert')).toContainText('Unable to place order');

await page.route('**/api/orders', route => route.fulfill({ status: 503 }));   // server down
```
Verify error messages, a retry button, no duplicate order submission, and that no partial order is shown on the blotter. Also test `context.setOffline(true)`.

## 33. Mobile testing
```javascript
const { devices } = require('@playwright/test');
projects: [
  { name: 'iPhone', use: { ...devices['iPhone 14'] } },
  { name: 'Pixel',  use: { ...devices['Pixel 7'] } },
]
```
- Emulates viewport, user agent, touch, geolocation and locale. It is **emulation** of mobile browsers, not native apps. For native apps, I use Appium.

## 34. Testing login
Cover these scenarios:
- Valid login → dashboard and the correct role (Broker / Dealer / Admin)
- Invalid password → error message; account lock after N attempts
- Empty fields → validation messages
- Session timeout → redirect to login
- Logout clears the session (`storageState` is empty)
- MFA/OTP via test hooks
- Security checks: password masked, no credentials in URL

```javascript
test('invalid password shows error', async ({ page }) => {
  await page.goto('/login');
  await page.getByLabel('User ID').fill('broker01');
  await page.getByLabel('Password').fill('wrong');
  await page.getByRole('button', { name: 'Login' }).click();
  await expect(page.getByRole('alert')).toHaveText(/invalid credentials/i);
});
```

## 35. Cookies
```javascript
await context.addCookies([{ name: 'session', value: 'abc123', url: 'https://portal.example.com' }]);
const cookies = await context.cookies();
expect(cookies.find(c => c.name === 'session')).toBeDefined();
await context.clearCookies();
```

## 36. Trace Viewer
- A GUI to analyse a test **after** execution. It shows a timeline, DOM snapshots before and after each action, network calls, console logs, source code and screenshots.
- It is the single best tool for debugging CI failures, because you can see exactly what the page looked like at each step.
- `npx playwright show-trace trace.zip` or open it at trace.playwright.dev.

## 37. Generating a trace
```javascript
// config
use: { trace: 'on-first-retry' }   // 'on' | 'off' | 'retain-on-failure'

// manual
await context.tracing.start({ screenshots: true, snapshots: true });
// ... test steps
await context.tracing.stop({ path: 'trace.zip' });
```

## 38. Visual testing
```javascript
await expect(page).toHaveScreenshot('dashboard.png', { maxDiffPixelRatio: 0.01 });
await expect(page.getByTestId('holdings-grid')).toHaveScreenshot();
```
- The first run creates baselines. Update them with `--update-snapshots`.
- For stable results: mask dynamic data (prices, timestamps) with `mask: [page.getByTestId('ltp')]`, disable animations, and run baselines on the same OS or Docker image as CI.
- Good for charts, statements, factsheets and layout regression.

## 39. File download
```javascript
const [download] = await Promise.all([
  page.waitForEvent('download'),
  page.getByRole('button', { name: 'Export Holdings' }).click()
]);
expect(download.suggestedFilename()).toBe('holdings.xlsx');
await download.saveAs('downloads/holdings.xlsx');
// Then validate the content with 'xlsx' or 'csv-parse': row count, totals, and ISIN values match the UI
```
Use case: contract notes, statements, P&L and tax reports. Validating the file content against the UI or DB is the real test.

## 40. WebSockets
```javascript
// Observe live feed frames
page.on('websocket', ws => {
  ws.on('framereceived', f => console.log('Tick:', f.payload));
});

// Mock the WebSocket (Playwright 1.48+)
await page.routeWebSocket('wss://feed.example.com/prices', ws => {
  ws.onMessage(msg => { /* inspect client subscriptions */ });
  ws.send(JSON.stringify({ isin: 'INE002A01018', ltp: 2501.25 }));
});
await expect(page.getByTestId('ltp-INE002A01018')).toHaveText('2,501.25');
```
Use case: live quotes and order-status streams. Mocking gives deterministic ticks without depending on market hours.

---

# SECTION 4: Advanced, Performance and Maintenance (Q41–Q44)

## 41. Slow network
```javascript
const client = await context.newCDPSession(page);   // Chromium only
await client.send('Network.emulateNetworkConditions', {
  offline: false, latency: 400,
  downloadThroughput: 50 * 1024, uploadThroughput: 20 * 1024
});
```
- Alternative (any browser): delay responses with `page.route`, using `await new Promise(r => setTimeout(r, 3000)); route.continue();`.
- Verify loading spinners, timeouts, disabled submit buttons (to prevent double orders), and graceful error messages.

## 42. Accessibility
```javascript
const { default: AxeBuilder } = require('@axe-core/playwright');
const results = await new AxeBuilder({ page }).withTags(['wcag2a', 'wcag2aa']).analyze();
expect(results.violations).toEqual([]);
```
- Also use `getByRole` locators (they implicitly validate ARIA), keyboard navigation checks, and `toHaveAccessibleName`.
- Automated tools catch roughly a third of issues. Manual checks are still needed. This matters for wealth platforms with regulatory accessibility expectations.

## 43. Sessions and storage
```javascript
await context.storageState({ path: 'state.json' });           // save cookies + localStorage
const ctx = await browser.newContext({ storageState: 'state.json' });   // reuse

const token = await page.evaluate(() => localStorage.getItem('authToken'));
await page.addInitScript(() => sessionStorage.setItem('theme', 'dark'));
```
Use separate `storageState` files per role (broker, dealer, admin, ops) to run role-based tests in parallel without re-logging in.

## 44. Testing in different browsers
```javascript
projects: [
  { name: 'chromium', use: { ...devices['Desktop Chrome'] } },
  { name: 'firefox',  use: { ...devices['Desktop Firefox'] } },
  { name: 'webkit',   use: { ...devices['Desktop Safari'] } },
  { name: 'edge',     use: { channel: 'msedge' } },
]
```
- `npx playwright test --project=firefox`
- **Strategy:** Run the full suite on Chromium and a smoke or critical-path suite on Firefox and WebKit, tagged `@smoke` or `@critical`, to balance coverage and run time.

---

# Closing Statement for the Interviewer

> "In my 10+ years in capital markets QA, I've seen that flakiness and data dependency are the biggest risks in trading UIs. With Playwright, I rely on locator-based auto-waiting, API mocking for deterministic prices, storage-state auth for role-based flows, trace-on-retry for fast debugging, and sharded CI runs. I pair this with API validation (RestAssured/TestNG experience) so that UI, API and DB agree on every order, holding and valuation. My framework work cut regression time by about 70%, and I'd bring that same approach here."

Want me to turn this into a **mock-interview round** (follow-up and scenario questions on trade validation, T+1 settlement, bond pricing flows) or a **printable cheat sheet**?



**************************************
PROMPT:
ROLE:
You are a a Senior Automation Engineer works in testing on Playwright with JavaScript automation, with expertise in Capital Market, Wealth and Trading Domain on diff securities EQ, Funds, Terms, Bonds.

CONTEXT:
As a SDET Engineer preparing for Interview with 10 + years experience in QA with Capital Market, Wealth and Trading domain provide the correct required useful text to answer/explain to the interviewer

TASK:
Provide answer with clear explaination along with example to explain to the interviewer.
Answer for the below listed Questions. 

OUTPUT FORMAT:
- Format: bullet points / table / code
- Tone: technical / persuasive]
- Structure: headers needed, sections required


Playwright Interview Questions:

1. What is Playwright?
2. What are the key features of Playwright?
3. What languages does Playwright support?
4. How is Playwright different from Selenium?
5. What are the components of Playwright?
6. What is a BrowserContext in Playwright?
7. What is a Locator?
8. How is auto-waiting implemented in Playwright?
9. What is the role of Expect?
10. What is the difference between Page and Context?
Browser and Element Interactions
11. How do you handle a browser in Playwright?
12. What is the use of Headless mode in Playwright?
13. How do you capture screenshot in Playwright?
14. How do you record video in Playwright?
15. How do you handle multiple tabs in Playwright?
16. How do you handle popups in Playwright?
17. How do you handle frames in Playwright?
18. How do you run tests in parallel?
19. How do you configure test retries?
20. How do you capture API response in Playwright?
21. How do you mock APIs in Playwright?
22. How do you handle authentication?
23. How do you integrate Playwright with CI/CD?
24. How do you debug tests in Playwright?
25. How do you generate test report in Playwright?
Automation Strategies and Scenarios
26. What is Page Object Model (POM) in Playwright?
27. How do you wait in Playwright?
28. How do you handle file upload?
29. How do you handle scrolling in Playwright?
30. How do you handle alerts in Playwright?
31. How do you perform drag and drop?
32. How do you handle network failures?
33. How do you test on mobile devices?
34. How do you test login functionality?
35. How do you handle cookies in Playwright?
36. What is Trace Viewer in Playwright?
37. How do you generate trace?
38. How do you perform visual testing?
39. How do you test file download?
40. How do you test WebSockets?
Advanced Performance and Maintenance
41. How do you handle slow network?
42. How do you test accessibility?
43. How do you handle sessions and storage?
44. How do you test in different browsers?
