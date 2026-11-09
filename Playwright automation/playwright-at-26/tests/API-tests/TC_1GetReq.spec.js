const { test, expect } = require('@playwright/test');

var userid;

test("Get users", async ({request}) => {
  const response = await request.get('https://reqres.in/api/users?page=2');
  console.log(await response.json());
  expect(response.status()).toBe(200);
   console.log(await response.json());
  var res=await response.json()
  userid=res.id;
  console.log('User ID', userid); 
 })

