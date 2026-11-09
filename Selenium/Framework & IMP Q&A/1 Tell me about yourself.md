#Tell me about yourself Structure your answer in 4 parts (keep it 60–90 seconds):

Hi My name is name: Ram chennale

1. **Current role** – "I amhaving 9+ years of experience Software testing and from last 5 and half years 
					worked in Broadridge."
					

2. **Tech stack** – "Currently I'm working on Functional testing along with UI and  API Automation and 
	AI- tools:Claude GitHub Copilot to speed up test design, requirement analysis and automation development

						- using 
						Java, Selenium WebDriver,TestNG, 
						REST Assured for API automation
						CI/CD tools Maven and Jenkins , and 
						Version control using Git and SVN
						Built a custom AI agent using GitHub Copilot in IntelliJ that converts Playwright Codegen recordings into 
						framework-standard artifacts (POM classes, locators, utilities, POJO), cutting script development time by 70%.
						
					
3. **What you do day-to-day** – "I design TC / Test plan/ Test strategy, defect reporting, and adding test scripts for regression suites for 
						the completed functionality to automation frameworks at the end of the sprint"
						
				
4. **Wrap-up/why this role** – "I'm looking for an opportunity where I can apply my skillset of financial capital market, trading and Wealth management.





# Tell Me About Yourself (60-90 seconds)

**1. Introduction and current role**
"Good morning. My name is Ram Chennale. I'm a Senior Test Engineer with 9+ years of experience in software testing, 
	the last five and a half of them at Broadridge, where I work on capital markets, trading, and wealth management applications."

**2. Tech stack**
"My work combines functional testing with UI and API automation. On the automation side, I use Java, Selenium WebDriver, 
	TestNG, and REST Assured, with Maven and Jenkins for CI/CD and Git and SVN for version control. 
	I also use AI tools like Claude and GitHub Copilot to speed up requirement analysis, test design, and automation development. 
	As part of that, I built a custom AI agent using GitHub Copilot in IntelliJ that converts Playwright Codegen recordings into our framework-standard 
	artifacts: POM classes, locators, utilities, and POJOs. It cut script development time by 70%."

**3. Day-to-day responsibilities**
"Day to day, I create test plans, test strategies, and test cases, report and track defects, and analyze requirements with the team. 
At the end of each sprint, I add automation scripts for the completed functionality to our regression suites, so the framework grows with the product."

**4. Wrap-up and why this role**
"I'm now looking for a senior role where I can apply my domain knowledge of financial capital markets, trading, and wealth management, 
along with my automation and AI-driven testing experience, to improve quality and delivery speed. That's why this opportunity interests me."


**************************************************************************************************************************************************
#Hardware + Software Lotus EPS:


**ANSWER **
At Lotus Wireless Technologies, I worked on Eagle Power Suite (EPS), a smart grid solution that gives utilities a smart meter interface for residential, 
		urban, suburban, rural and industrial consumers. Meters communicated through Data Concentrator Units (DCUs) over RF, GPRS and PLC, and were managed through 
		four applications: GEMS, LCM, AMC and HES. Before meters could be deployed, each one had to be verified for accuracy and for reliable communication with the whole stack.

- **10+ years of QA**, with API testing (RestAssured + TestNG), SQL validation, and a framework.
- **Financial transaction domain.** Trading and funds experience maps well to fare transactions: accuracy, reconciliation, and audit trails.
- **Working with AI tooling** in test automation, Frmework build Agent who converted codeGen recorded JS script to Framework fomrat 
		(Mention it briefly as a modern-practice plus. Don't let it take over the interview).

- Formal **test procedure writing** (step, expected result, pass/fail criteria, traceability).
- **Test equipment and tools** (multimeters, SM, DCU, Auto-transformer(to control the output voltage from 0 up to the required value) 
- **Postman and SoapUI** specifically. You're strong in RestAssured, so close this gap in a day.


**Task**
I was responsible for testing and onboarding electric smart meters so they worked correctly end to end: accurate measurement, stable communication, and 
	correct data showing up in the EPS applications. I also had to support field engineers during deployment.

**Action**
- Set up meters on the test bench or single meter and used Auto-Transformaer power source with a reference standard meter to vary voltage and current, 
	simulating different load conditions and checking measurement accuracy.

- Onboarded each meter into the EPS applications (GEMS, LCM, AMC, HES) and validated that readings, commands and events flowed correctly.

- Tested communication over RF & GPRS (NOT PLC, which was in progress) through the DCU, including behavior under weak signal or dropped connections moving mode mode to GPRS.
	(RF: I don’t know the exact frequency EPS used,  sub-GHz band, around 865 to 867 MHz, as per Indian regulations)

- Worked with field engineers on site to deploy meters, troubleshoot communication failures, and confirm data reached the head-end system.

- Logged defects, retested fixes and gave feedback to development.

**Result**
Meters were deployed with verified accuracy and working communication, and field issues were resolved faster because of the testing and the close coordination with field teams. 
	Add a metric here if you can, for example the number of meters onboarded, the reduction in field communication failures, or the defect leakage you prevented.

**How a smart meter works (the short version, if they ask)**
1. The meter measures voltage and current and calculates energy consumption digitally.
2. It stores the data along with events such as tamper alerts and outages.
3. It sends data to a DCU (or directly) over RF, PLC or GPRS.
4. The DCU forwards it to the head-end system (HES), which feeds applications like billing and monitoring.
5. The utility can also send commands back, such as remote disconnect or tariff updates.


#Num of SM ->DCU

1. NOT Tested - PLC (power line carrier): often 200 to 1,000 meters per DCU, since a DCU usually sits at a distribution transformer and serves the meters downstream of it

2. RF mesh: 500 SM we can connect but checked with 100 + meters in HISAR and ICF chennai factory
	(commonly 300 to 1,000 meters, and some designs go higher, because meters relay each other’s data.
3. GPRS (meter direct to server): no DCU needed in many designs. Where a DCU is used, it is usually for aggregation.


