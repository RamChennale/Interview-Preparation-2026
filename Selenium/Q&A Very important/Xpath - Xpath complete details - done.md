***XPath Priority:
`data-testid` or id with CSS selectors is usually more stable than XPath

1. ***data-testid*** 	Does it have a data-testid? Use it.
2. ***unique id*** 		Does it have a static, unique id? Use it.
3. ***name or aria-label*** Is there a name or aria-label? Use it.
4. ***CSS selector*** 	Can a short CSS selector make it unique? Use CSS.
5. ***relative XPath*** Need text, parent, or sibling navigation? Use relative XPath.
6. ***absolute XPath*** Never use absolute XPath.

## 1. What is XPath?
XPath (XML Path Language) locates elements in an HTML/XML document. Use it when ID, name, or class aren't available or aren't unique.

## 2. Types

| Type | Example | Notes |
|---|---|---|
| Absolute | `/html/body/div/form/input` 	| Starts from root. Fragile, breaks on any structure change. |
| Relative | `//input[@id='username']` 		| Starts anywhere. Flexible, preferred for automation. |

## 3. Common syntax

| Technique 	| Syntax 			| Example 													| Use case |
|---			|---				|
| Attribute 	| `//tag[@attribute='value']` 		    | `//input[@name='username']` 			| Element has a unique attribute |
| contains()	| `//tag[contains(@attribute,'value')]` | `//button[contains(@class,'login')]` 	| Attribute value is partial or dynamic |
| starts-with() | `//tag[starts-with(@attribute,'value')]` | `//input[starts-with(@id,'user')]` | Value starts with specific text |
| text() 		| `//tag[text()='value']`               | `//button[text()='Login']` 			| Exact visible text |
| Partial text	| `//tag[contains(text(),'value')]` 	| `//a[contains(text(),'Forgot')]` | Partial visible text |
| AND 			| `//tag[@attr1='v1' and @attr2='v2']` 	| `//input[@type='text' and @name='username']` | Multiple attributes available |
| OR 			| `//tag[@attr1='v1' or @attr2='v2']` 	| `//input[@id='username' or @name='user']` | Any one attribute can be used |

## 4. Xpath  Axes (commonly asked in interviews):**

| Axis | Example | Meaning |
|---				|---|---|
| `parent::` 		| `//input[@id='user']/parent::div`| Parent element |
| `ancestor::` 		| `//input/ancestor::form` 	| Any ancestor above |
| `child::` 		| `//div/child::input` 		| Direct children |
| `descendant::` 	| `//div/descendant::input` | All nested children |
| `following-sibling::` | `//label[text()='Name']/following-sibling::input` | Siblings after the node |
| `preceding-sibling::` | `//input/preceding-sibling::label` | Siblings before the node |


## 5. Axes and path symbols

| Symbol| Meaning |
|---	|---|
| `//` 	| Selects element anywhere in the document |
| `/` 	| Selects direct child |
| `.` 	| Current node |
| `..` 	| Parent node |
| `@` 	| Selects attribute |
| `*` 	| Matches any element |

Examples:
- `//div//input`: all inputs under a div (any depth)
- `//div/input`: direct child inputs of a div
- `//input/..`: parent of an input

## 6. Real-world examples

| Scenario | XPath |
|---|---|
| Login: username field 	| `//input[@id='username']` |
| Login: password field 	| `//input[@name='password']` |
| Login button with contains() | `//button[contains(@class,'login')]` |
| Link with exact text 		| `//a[text()='Forgot Password?']` |
| Link with partial text 	| `//a[contains(text(),'Forgot')]` |
| Input with starts-with() 	| `//input[starts-with(@id,'user')]` |
| Multiple conditions 		| `//input[@type='text' and @name='username']` |
| Using OR 					| `//input[@id='username' or @name='user']` |

## 7. Tips for good XPath
- Use unique and stable attributes.
- Prefer relative over absolute XPath.
- Use `contains()` or `starts-with()` for dynamic values.
- Avoid too many indexes like `[1]`, `[2]`.
- Keep it simple and readable.
- Verify in the browser DevTools (F12 or Ctrl+Shift+I).

*A good XPath is unique, stable, readable, and less likely to break.*

## 8. Key takeaways
- XPath is a powerful locator when simple locators aren't available.
- Relative XPath is more flexible and reliable.
- `contains()`, `starts-with()`, `text()` are very useful.
- Logical operators (`and`/`or`) help write advanced locators.
- Practice is the key to stable XPath.

---

**Other useful functions:**
- `normalize-space()`: ignores extra whitespace, e.g. `//button[normalize-space()='Login']`
- `last()` and `position()`: `(//input)[last()]`, `//tr[position()=2]`
- Grouped index: `(//input[@type='text'])[2]` picks the 2nd match across the whole page
