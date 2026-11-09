what diff storing element in By locators rather than WebElement fields?

## How to say it in an interview

> "A `By` is a locator strategy, a recipe. 
A `WebElement` is a live reference to one DOM node at one moment. If I store the `WebElement`, any re-render invalidates it. 

If I store the `By`, I resolve it at the point of use, so I always get the current element, and 
it also works naturally with explicit waits since they can re-find the element on every poll."

## Diff between DOM re-renders & Refreshing  the DOM 
A page refresh (F5) and a DOM re-render are different things, though both can make an element stale.

#What “re-render” actually means
The DOM is the browser’s tree of page elements. A re-render means the app deletes the old nodes and creates new ones in their place, even if they look identical on screen.

Refresh = the whole house is rebuilt.
Re-render = someone swapped out the furniture in one room, while the house stays standing.

Page refresh vs. DOM re-render
Page refresh / navigation											DOM re-render
What happens				Browser reloads the whole page			JavaScript updates part of the page, no reload
URL / page load	Reloads,	 new document							Same page stays open
=====================================================================================================

A `By` is just a description of how to find an element (a recipe). 
A `WebElement` is a live handle to one specific node in the browser's DOM right now. That one distinction explains everything.

## The core difference

| 								| `By` locator									| `WebElement` field |
|---|---|---|
| What it holds 				| The search query (e.g., CSS selector, XPath) 	| A reference to an actual DOM node |
| When lookup happens 			| Every time you use it 						| Once, when the field is initialized |
| After DOM re-render 			| Still valid, finds the new node 				| Stale, throws `StaleElementReferenceException` |
| Can be created before page loads | Yes 										| No (needs the page and element to exist) |
| Reusable across waits 		| Yes, waits re-find on each poll 				| No, waits can't re-find it |

## Example

**Using `WebElement` fields (fragile):**

```java
public class PortfolioPage {
    private WebElement exportButton;

    public PortfolioPage(WebDriver driver) {
        exportButton = driver.findElement(By.id("export"));  // found ONCE
    }

    public void export() {
        exportButton.click();  // if grid re-rendered since constructor, this is stale
    }
}
```

**Using `By` locators (robust):**

```java
public class PortfolioPage extends BasePage {
    private final By exportButton = By.id("export");  // just a recipe

    public void export() {
        wait.until(ExpectedConditions.elementToBeClickable(exportButton)).click();
        // finds the element fresh at this exact moment
    }
}
```

In the first version, the element is located at construction time. If the page refreshes between construction and `export()`, the reference is dead. In the second, the lookup happens right when you need it, so it always gets the current node.

## Why waits need `By`

Explicit waits like `ExpectedConditions.elementToBeClickable(By)` and `refreshed(...)` can re-run the lookup on every poll, because they have the recipe. If you pass a `WebElement`, the wait can only check the same possibly-dead object again and again.

## Where this connects to PageFactory

`@FindBy` fields look like `WebElement` fields, but PageFactory wraps them in a **lazy proxy** that re-runs the lookup on every method call. So they're mostly safe from staleness, until you add `@CacheLookup`, which stores the first result and brings the stale problem back.

## How to say it in an interview

> "A `By` is a locator strategy, a recipe. A `WebElement` is a live reference to one DOM node at one moment. If I store the `WebElement`, any re-render invalidates it. If I store the `By`, I resolve it at the point of use, so I always get the current element, and it also works naturally with explicit waits since they can re-find the element on every poll."

## One trade-off worth mentioning

Storing `By` means a lookup on every interaction, which is a tiny performance cost. In practice it's negligible compared to the reliability gain. And for a stable element that never changes (a static page header, say), caching a `WebElement` within a single method is fine. The rule is: **never hold a `WebElement` across actions that might re-render the page.**