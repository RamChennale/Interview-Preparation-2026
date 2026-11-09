const { test, expect } = require('@playwright/test');

var userid;

 test("Create user", async ({ request}) => {
  const response = await request.post('https://reqres.in/api/users', {
      data: {
          "name": "kumar",
          "job": "trainer"
      }, headers: {
          "Accept": "application/json"
      }
  });
  expect(response.status()).toBe(201);
  expect(response.ok()).toBeTruthy();
 console.log(await response.json());
  var res=await response.json()
  userid=res.id // storing id in a variable to use in another test
  console.log('Post req', userid)
 })


 test("Delete user", async ({ request}) => {
    console.log('Delete req', userid)
  const response = await request.delete('https://reqres.in/api/users/'+userid);
  expect(response.status()).toBe(204);
  })