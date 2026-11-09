const { test, expect } = require('@playwright/test')
//npx playwright test TC32_AnnotationTags.spec.js --grep '@sanity'
test('test1', async ({ page }) => {
    console.log('this is test1.....')
});

test('test2 @sanity', async ({ page }) => {
    console.log('this is test2.....')
});


test('test3 @sanity', async ({ page }) => {
    console.log('this is test3.....')
});


test('test4@reg', async ({ page }) => {
    console.log('this is test4.....')
});


test('test5@reg', async ({ page }) => {
    console.log('this is test5.....')
});

test('test6@reg@sanity', async ({ page }) => {
    console.log('this is test6.....')
});
