const {test, expect}=require('@playwright/test')

test("simple table",async ({page})=>{

    await page.goto('https://testautomationpractice.blogspot.com/');

    const table = await page.locator("#productTable");
    

//////1) count total rows & columns
    const coloums= await table.locator('thead tr th')
    const rows = await table.locator("tbody tr ")
    console.log('Number of columns:',await coloums.count()) //4
    console.log('Number of rows:',await rows.count()) //5
    
    expect(await coloums.count()).toBe(4);
    expect(await rows.count()).toBe(5);
    
})

////// 2) Select check box for Product 4
    //Returns row where Product4 is exist
     /* const machedRow=rows.filter({
        has: page.locator('td'),
        hasText: 'Product 4'
       }) 
       await machedRow.locator('input').check()
    */


////// 3) Select multiple products  by using re-usable function
    //await selectProduct(rows,page,'Product 1')
    //await selectProduct(rows,page,'Product 3')
    //await selectProduct(rows,page,'Product 5')
    


///// 4) Print all product details using loop
  /* for(let i = 0; i < await rows.count(); i++) 
   {
    const row = rows.nth(i);
    const tds = row.locator("td");
    for (let j = 0; j < await tds.count()-1; j++) 
        {
               console.log(await tds.nth(j).textContent());
          }
    }
*/



///// 5) Select all products using loop in all pages
  
/*
const pages=await page.locator('.pagination li a')
    console.log('Number of Page in table', await pages.count())
    for(let p=0;p<await pages.count();p++) //4
    {
       console.log("Page Number------>",(p+1)) 
       if(p>0){
        await pages.nth(p).click();
        }
      
       for(let i = 0; i < await rows.count(); i++) 
        {
        const row = rows.nth(i);
        const tds = row.locator("td");
        for (let j = 0; j < await tds.count()-1; j++) 
            {
                console.log(await tds.nth(j).textContent());
            }
      }
      await page.waitForTimeout(2000);
    }
    await page.waitForTimeout(3000);
})

///  --------

async function selectProduct(rows, page, name){
const machedRow=rows.filter({
    has: page.locator('td'),
    hasText: name
   }) 
   await machedRow.locator('input').check()
}

*/