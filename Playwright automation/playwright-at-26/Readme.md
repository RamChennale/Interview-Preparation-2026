all sanity:
npx playwright test tests/Tags.spec.js --project chromium --grep @sanity

all regression:
npx playwright test tests/Tags.spec.js --project chromium --grep @reg 


both sanity & regression:
npx playwright test tests/Tags.spec.js --project chromium --grep @reg@sanity 


only sanity but not regression:
npx playwright test tests/Tags.spec.js --project chromium --grep @sanity --grep-invert @reg


only regression but not sanity:
npx playwright test tests/Tags.spec.js --project chromium --grep @reg --grep-invert @sanity




 Line reporter
List reporter
Dot reporter
HTML reporter
JSON reporter
JUnit reporter

Third Party reports - Allure

=====================
HTML Report
===========
playwright.config.js file:
	reporter: 'html'

(or)

npx playwright test --reporter=html

to see report:
	npx playwright show-report      ----> playwright-report folder 



======
Line reporter
===========
reporter: 'line',

(or)
npx playwright test --reporter=line

(or)

npx playwright test --reporter line


=======
List reporter
========
 reporter: 'list',

 (or)

 npx playwright test --reporter=list





==========
Dot reporter
===========

reporter: 'dot',

(Or)

npx playwright test --reporter=dot



========
JSON reporter
===========
reporter: [['json', { outputFile: 'results.json' }]],

(OR)

$env:PLAYWRIGHT_JSON_OUTPUT_NAME="results.json"
npx playwright test --reporter=json


======
JUnit reporter
=====
reporter: [['junit', { outputFile: 'results.xml' }]],


(or)

set PLAYWRIGHT_JUNIT_OUTPUT_NAME=results.xml
npx playwright test --reporter=junit




All Reporters
------
reporter: 'html',

reporter: 'dot',

reporter: 'list',

reporter: [['json', { outputFile: 'results.json' }]],

reporter: [['junit', { outputFile: 'results.xml' }]],


reporter: [
    ['list'],
    ['json', {  outputFile: 'test-results.json' }],
    ['junit', { outputFile: 'results.xml' }]
    ],





API Testing 
-----------

Request Type	URI	Request Payload
GET	https://reqres.in/api/users?page=2	NA

POST	https://reqres.in/api/users	    "{
                                            ""name"": ""pavan"",
                                            ""job"": ""trainer""
                                        }"
PUT	https://reqres.in/api/users/411	    
                                        "{
                                            ""name"": ""pavan"",
                                            ""job"": ""engineer""
                                        }"
DELETE	https://reqres.in/api/users/411	na














