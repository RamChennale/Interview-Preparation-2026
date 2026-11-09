const {test, expect}=require('@playwright/test')

test.skip('Single File',async ({page})=>{

    await page.goto('https://davidwalsh.name/demo/multiple-file-upload.php');

    await page.waitForSelector('#filesToUpload');
    await page.locator('#filesToUpload').click()
    
    //Upload file
    await page.locator('#filesToUpload').setInputFiles('tests/uploadFiles/file1.txt');
    
    await page.waitForTimeout(5000); 

})

test.only('Multiple Files',async ({page})=>{

    await page.goto('https://davidwalsh.name/demo/multiple-file-upload.php');
       
    //upload multiple files
    await page.locator('#filesToUpload')
                 .setInputFiles(['tests/uploadFiles/file1.txt', 
                    'tests/uploadFiles/file1.txt']);

    await page.waitForTimeout(3000); 
       
    expect(await page.locator('#fileList li:nth-child(1)')).toHaveText('file1.txt')
    expect(await page.locator('#fileList li:nth-child(2)')).toHaveText('file1.txt')
   
    // Remove all the selected files
    await page.locator('#filesToUpload').setInputFiles([]);
    await page.waitForTimeout(3000);
    expect(await page.locator('#fileList li:nth-child(1)')).toHaveText('No Files Selected')
    await page.waitForTimeout(5000);
})
