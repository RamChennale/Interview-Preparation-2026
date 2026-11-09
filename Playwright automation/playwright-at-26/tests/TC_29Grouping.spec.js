import {chromium,test, expect } from '@playwright/test';

//beforeAll - Hooks
test.beforeAll(async () => {
  console.log(" this is beforeAll ......")
});

test.afterAll(async () => {
  console.log(" this is afterAll ......")
});


//beforeEach - Hooks
test.beforeEach(async () => {
  console.log(" this is beforerEach ......")
});

test.afterEach(async () => {
  console.log(" this is afterEach ......")
});


//GROUPing 

 test.describe('Group 1',()=>{
    
    test('Test1', async ({ page }) => {
       console.log(" this is Test1 ......")

    });

    test('Test2', async ({ page }) => {
      console.log(" this is Test2 ......")
    });
  })

  test.describe('Group 2',()=>{
    
    test('Test3', async ({ page }) => {
      console.log(" this is Test3 ......")
    });

    test('Test4', async ({ page }) => {
      console.log(" this is Test4 ......")
    });
  })
