
**16. How do you make your framework's locators resilient to UI changes?**

## 30-second version

"I make locators resilient at three levels. 
***First, Locator strategy: *** I prefer `data-testid` or id, then accessible attributes, and avoid absolute XPath and layout-dependent CSS chains. 
***Second, Framework design:*** all locators live in Page Object classes, so a UI change is a single-point fix, and tests only call page methods. 
***Third, Team process:*** possible I'll work with developers to add test IDs as part of the definition of done. 
	For legacy suites will consider a self-healing tool like Healenium, but only as a reviewed safety net, since it can mask real defects."



Prefer stable attributes (`data-testid`, `id`) over brittle XPath/CSS chains; collaborate with dev teams to add automation-friendly hooks; 
centralize locators in POM classes so a UI change requires a single-point update; 
consider self-healing locator tools (Healenium) for large legacy suites where element churn is high.

