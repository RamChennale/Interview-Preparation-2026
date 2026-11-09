Shadow DOM
`ShadowDomSearchContextExample` impelemented class in practice


**Interview tip:** If asked how to handle Shadow DOM in Selenium 4, say: 
 #Shadow DOM in Selenium 4
Find the shadow host, call `getShadowRoot()` to get a `SearchContext`, then locate elements with CSS selectors, repeating for nested roots.


## Shadow DOM

Shadow DOM is a browser feature where a component (a custom web element) hides its internal HTML/CSS in a separate, 
encapsulated tree attached to a **shadow host**. A normal `driver.findElement(...)` can't see inside it.

```
<my-component>            ← shadow host
   #shadow-root (open)
       <input id="name">  ← hidden from normal locators
</my-component>
```

## SearchContext

`SearchContext` is the Selenium interface that has `findElement()` and `findElements()`. 
Both `WebDriver` and `WebElement` implement it. Since Selenium 4, `getShadowRoot()` returns a `SearchContext`, so you can search inside the shadow tree.

## Typical example

```java
WebElement host = driver.findElement(By.cssSelector("my-component"));
SearchContext shadowRoot = host.getShadowRoot();

WebElement input = shadowRoot.findElement(By.cssSelector("#name"));
input.sendKeys("Ram");
```

For nested shadow roots, repeat the step:

```java
SearchContext outer = driver.findElement(By.cssSelector("outer-host")).getShadowRoot();
SearchContext inner = outer.findElement(By.cssSelector("inner-host")).getShadowRoot();
inner.findElement(By.cssSelector("button")).click();
```

## Things to remember

- Inside a shadow root, use **CSS selectors only**. XPath doesn't work there.
- It works only for **open** shadow roots. Closed ones can't be accessed through Selenium.
- Older workaround (Selenium 3): `JavascriptExecutor` with `arguments[0].shadowRoot`.
- **Playwright** pierces open shadow DOM automatically with normal CSS locators, so it's much easier there.

**Interview tip:** If asked how to handle Shadow DOM in Selenium 4, say: find the shadow host, call `getShadowRoot()` to get a `SearchContext`, then locate elements with CSS selectors, repeating for nested roots.