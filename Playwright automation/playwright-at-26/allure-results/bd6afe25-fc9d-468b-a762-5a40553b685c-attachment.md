# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: TC_27UploadFiles.spec.js >> Multiple Files
- Location: tests\TC_27UploadFiles.spec.js:17:6

# Error details

```
Error: ENOENT: no such file or directory, stat 'C:\at-practice-26\playwright-at-26\tests\uploadFiles\testfile1.pdf'
```

# Page snapshot

```yaml
- generic [active] [ref=e1]:
  - list [ref=e2]:
    - listitem [ref=e3]:
      - link "Home" [ref=e4] [cursor=pointer]:
        - /url: /
    - listitem [ref=e5]:
      - link "Main Content" [ref=e6] [cursor=pointer]:
        - /url: "#main"
  - banner [ref=e7]:
    - generic [ref=e8]:
      - generic [ref=e9]:
        - link "DWB" [ref=e10] [cursor=pointer]:
          - /url: /
          - text: DWB
          - generic [aria-hidden] [ref=e11]: 
        - text: 
      - generic [ref=e12]:
        - list [ref=e13]:
          - listitem [ref=e14]:
            - link [ref=e15] [cursor=pointer]:
              - /url: /feed
              - generic [aria-hidden] [ref=e16]: 
          - listitem [ref=e17]:
            - link [ref=e18] [cursor=pointer]:
              - /url: //twitter.com/davidwalshblog
              - generic [aria-hidden] [ref=e19]: 
          - listitem [ref=e20]:
            - link [ref=e21] [cursor=pointer]:
              - /url: //facebook.com/davidwalshblog
              - generic [aria-hidden] [ref=e22]: 
          - listitem [ref=e23]:
            - link [ref=e24] [cursor=pointer]:
              - /url: //github.com/darkwing
              - generic [aria-hidden] [ref=e25]: 
          - listitem [ref=e26]:
            - link [ref=e27] [cursor=pointer]:
              - /url: //linkedin.com/in/davidjameswalsh
              - generic [aria-hidden] [ref=e28]: 
          - listitem: 
        - generic [ref=e31]:
          - generic [ref=e32]: Search
          - generic [aria-hidden] [ref=e33]: 
          - searchbox "Search" [ref=e34]
  - generic [ref=e36]:
    - strong [ref=e37]: "Popular:"
    - list [ref=e38]:
      - listitem [ref=e39]:
        - link "JavaScript Promises" [ref=e40] [cursor=pointer]:
          - /url: /promises
      - listitem [ref=e41]:
        - link "fetch API" [ref=e42] [cursor=pointer]:
          - /url: /fetch
      - listitem [ref=e43]:
        - link "React.js" [ref=e44] [cursor=pointer]:
          - /url: /tutorials/react
      - listitem [ref=e45]:
        - link "Cache API" [ref=e46] [cursor=pointer]:
          - /url: /cache
      - listitem [ref=e47]:
        - link "ES6 Features" [ref=e48] [cursor=pointer]:
          - /url: /es6-features
      - listitem [ref=e49]:
        - link "Node.js" [ref=e50] [cursor=pointer]:
          - /url: /tutorials/nodejs
      - listitem [ref=e51]:
        - link "JavaScript" [ref=e52] [cursor=pointer]:
          - /url: /tutorials/javascript
      - listitem [ref=e53]:
        - link "jQuery" [ref=e54] [cursor=pointer]:
          - /url: /tutorials/jquery
  - main [ref=e57]:
    - generic [ref=e58]:
      - 'heading "Demo: Multiple File Upload Input" [level=1] [ref=e59]'
      - generic [ref=e60]:
        - text: Read
        - link "Multiple File Upload Input" [ref=e61] [cursor=pointer]:
          - /url: https://davidwalsh.name/multiple-file-upload
      - generic [ref=e62]:
        - generic:
          - link:
            - /url: //twitter.com/share
        - iframe [ref=e64]
      - paragraph [ref=e65]: Description...
      - paragraph [ref=e66]:
        - strong [ref=e67]: "Upload Files:"
        - button "Choose File" [ref=e68]
      - paragraph [ref=e69]:
        - strong [ref=e70]: "Files You Selected:"
      - list [ref=e71]:
        - listitem [ref=e72]: No Files Selected
    - paragraph [ref=e73]:
      - text: "Back to:"
      - link "Multiple File Upload Input" [ref=e74] [cursor=pointer]:
        - /url: https://davidwalsh.name/multiple-file-upload
```

# Test source

```ts
  1  | const {test, expect}=require('@playwright/test')
  2  | 
  3  | test.skip('Single File',async ({page})=>{
  4  | 
  5  |     await page.goto('https://davidwalsh.name/demo/multiple-file-upload.php');
  6  | 
  7  |     await page.waitForSelector('#filesToUpload');
  8  |     await page.locator('#filesToUpload').click()
  9  |     
  10 |     //Upload file
  11 |     await page.locator('#filesToUpload').setInputFiles('tests/uploadFiles/file1.txt');
  12 |     
  13 |     await page.waitForTimeout(5000); 
  14 | 
  15 | })
  16 | 
  17 | test.only('Multiple Files',async ({page})=>{
  18 | 
  19 |     await page.goto('https://davidwalsh.name/demo/multiple-file-upload.php');
  20 |        
  21 |     //upload multiple files
> 22 |     await page.locator('#filesToUpload')
     |     ^ Error: ENOENT: no such file or directory, stat 'C:\at-practice-26\playwright-at-26\tests\uploadFiles\testfile1.pdf'
  23 |                  .setInputFiles(['tests/uploadFiles/testfile1.pdf', 
  24 |                     'tests/uploadFiles/testfile2.pdf']);
  25 | 
  26 |     await page.waitForTimeout(3000); 
  27 |        
  28 |     expect(await page.locator('#fileList li:nth-child(1)')).toHaveText('file1.txt')
  29 |     expect(await page.locator('#fileList li:nth-child(2)')).toHaveText('file1.txt')
  30 |    
  31 |     // Remove all the selected files
  32 |     await page.locator('#filesToUpload').setInputFiles([]);
  33 |     await page.waitForTimeout(3000);
  34 |     expect(await page.locator('#fileList li:nth-child(1)')).toHaveText('No Files Selected')
  35 |     await page.waitForTimeout(5000);
  36 | })
  37 | 
```