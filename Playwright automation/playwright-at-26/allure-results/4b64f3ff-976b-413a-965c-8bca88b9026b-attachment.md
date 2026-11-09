# Instructions

- Following Playwright test failed.
- Explain why, be concise, respect Playwright best practices.
- Provide a snippet of code with the fix, if possible.

# Test info

- Name: TC_27UploadFiles.spec.js >> Single File
- Location: tests\TC_27UploadFiles.spec.js:3:6

# Error details

```
Test timeout of 30000ms exceeded.
```

```
Error: page.waitForSelector: Test timeout of 30000ms exceeded.
Call log:
  - waiting for locator('.mqfihd-upload') to be visible
    - waiting for "https://www.foundit.in/career-services/boost" navigation to finish...
    - navigated to "https://www.foundit.in/career-services/boost"
    - waiting for "https://www.foundit.in/career-services" navigation to finish...
    - navigated to "https://www.foundit.in/career-services"

```

# Page snapshot

```yaml
- generic [active] [ref=f26e1]:
  - main [ref=f26e2]:
    - generic [ref=f26e7]:
      - generic [ref=f26e8]:
        - img "foundit Logo" [ref=f26e10] [cursor=pointer]
        - link "Services" [ref=f26e15] [cursor=pointer]:
          - /url: /career-services
      - generic [ref=f26e19]:
        - generic [ref=f26e21]:
          - textbox "Search by Skills, Company or Job Title" [ref=f26e25]
          - generic: Please enter a job title/role/skill to search
        - generic [ref=f26e28]:
          - textbox "Location" [ref=f26e32]
          - generic: Please enter a job title/role/skill to search
        - generic [ref=f26e35]:
          - textbox "Experience" [ref=f26e39]
          - generic: Please enter a job title/role/skill to search
        - button [ref=f26e44] [cursor=pointer]
      - generic [ref=f26e49]:
        - button "Login" [ref=f26e50] [cursor=pointer]
        - button "Register" [ref=f26e53] [cursor=pointer]
    - main [ref=f26e56]:
      - generic [ref=f26e57]:
        - generic [ref=f26e60]:
          - generic [ref=f26e61]:
            - generic [ref=f26e62]:
              - heading "Explore Our Expert Career Services" [level=1] [ref=f26e63]
              - paragraph [ref=f26e64]: Stand out from other jobseekers! Enhance your profile with industry-standard guidance. Choose from our tailored career services to revolutionise your job search.
            - generic [ref=f26e66]:
              - generic [ref=f26e67]: Trusted by 75M
              - text: users across Asia & the Gulf
          - generic [ref=f26e69]:
            - link "Highlight Highlight" [ref=f26e70] [cursor=pointer]:
              - /url: /career-services/boost
              - img "Highlight" [ref=f26e72]
              - paragraph [ref=f26e73]:
                - generic [ref=f26e74]: Highlight
            - link "Prep Prep" [ref=f26e75] [cursor=pointer]:
              - /url: /career-services/prep
              - img "Prep" [ref=f26e77]
              - paragraph [ref=f26e78]:
                - generic [ref=f26e79]: Prep
            - link "Learn Learn" [ref=f26e80] [cursor=pointer]:
              - /url: /career-services/learn
              - img "Learn" [ref=f26e82]
              - paragraph [ref=f26e83]:
                - generic [ref=f26e84]: Learn
            - link "Network Network" [ref=f26e85] [cursor=pointer]:
              - /url: /career-services/network
              - img "Network" [ref=f26e87]
              - paragraph [ref=f26e88]:
                - generic [ref=f26e89]: Network
        - generic [ref=f26e90]:
          - generic [ref=f26e92]:
            - generic [ref=f26e93]:
              - generic [ref=f26e95]:
                - img "highlight" [ref=f26e96]
                - heading "highlight" [level=1] [ref=f26e97]
              - paragraph [ref=f26e98]: Get 10x visibility & leave a lasting impression on recruiters.
            - generic [ref=f26e101]:
              - text: ←
              - generic [ref=f26e103]:
                - link "icon Featured Profile Rank higher, plus stand out in search results – the best way to make a mark. Starting @ ₹699" [ref=f26e107] [cursor=pointer]:
                  - /url: https://www.foundit.in/career-services/featured-profile-services
                  - img "icon" [ref=f26e110]
                  - generic [ref=f26e111]:
                    - heading "Featured Profile" [level=4] [ref=f26e112]
                    - paragraph [ref=f26e113]: Rank higher, plus stand out in search results – the best way to make a mark.
                  - generic [ref=f26e114]:
                    - generic [ref=f26e115]: Starting @
                    - generic [ref=f26e117]: ₹699
                - link "icon AI Resume Studio Build an ATS-ready resume with live AI coaching and guidance Starting @ ₹399 20% off ₹319" [ref=f26e121] [cursor=pointer]:
                  - /url: https://www.foundit.in/career-services/ai-resume-studio
                  - img "icon" [ref=f26e124]
                  - generic [ref=f26e125]:
                    - heading "AI Resume Studio" [level=4] [ref=f26e126]
                    - paragraph [ref=f26e127]: Build an ATS-ready resume with live AI coaching and guidance
                  - generic [ref=f26e128]:
                    - generic [ref=f26e129]:
                      - generic [ref=f26e130]: Starting @
                      - generic [ref=f26e131]: ₹399
                      - generic [ref=f26e132]: 20% off
                    - generic [ref=f26e133]: ₹319
                - link [ref=f26e137] [cursor=pointer]:
                  - /url: https://www.foundit.in/career-services/career-success-suite
                  - generic [ref=f26e141]:
                    - heading [level=4] [ref=f26e142]: Career Success Suite
                    - paragraph [ref=f26e143]: Your all-in-one AI toolkit to go from application to interview
                  - generic [ref=f26e144]:
                    - generic [ref=f26e145]:
                      - generic [ref=f26e146]: Starting @
                      - generic [ref=f26e147]: ₹899
                      - generic [ref=f26e148]: 20% off
                    - generic [ref=f26e149]: ₹719
                - link [ref=f26e153] [cursor=pointer]:
                  - /url: https://www.foundit.in/career-services/career-booster
                  - generic [ref=f26e157]:
                    - heading [level=4] [ref=f26e158]: Career Booster
                    - paragraph [ref=f26e159]: Boost your job search using Featured Profile & Resume Writing
                  - generic [ref=f26e160]:
                    - generic [ref=f26e161]: Starting @
                    - generic [ref=f26e163]: ₹4599
                - link [ref=f26e167] [cursor=pointer]:
                  - /url: https://www.foundit.in/career-services/profile-highlighter
                  - generic [ref=f26e171]:
                    - heading [level=4] [ref=f26e172]: Profile Highlighter
                    - paragraph [ref=f26e173]: Get your profile highlighted to ensure immediate attention
                  - generic [ref=f26e174]:
                    - generic [ref=f26e175]: Starting @
                    - generic [ref=f26e177]: ₹1499
                - link [ref=f26e181] [cursor=pointer]:
                  - /url: https://www.foundit.in/career-services/ai-profile-edge
                  - generic [ref=f26e185]:
                    - heading [level=4] [ref=f26e186]: AI Profile Edge
                    - paragraph [ref=f26e187]: Build a stronger professional profile with Resume, Cover Letter & LinkedIn
                  - generic [ref=f26e188]:
                    - generic [ref=f26e189]:
                      - generic [ref=f26e190]: Starting @
                      - generic [ref=f26e191]: ₹499
                      - generic [ref=f26e192]: 20% off
                    - generic [ref=f26e193]: ₹399
                - link [ref=f26e197] [cursor=pointer]:
                  - /url: https://www.foundit.in/career-services/linkedin-profile-makeover
                  - generic [ref=f26e201]:
                    - heading [level=4] [ref=f26e202]: LinkedIn Makeover
                    - paragraph [ref=f26e203]: Revamp your LinkedIn profile & make it a magnet for job opportunities.
                  - generic [ref=f26e204]:
                    - generic [ref=f26e205]: Starting @
                    - generic [ref=f26e207]: ₹1999
                - link [ref=f26e211] [cursor=pointer]:
                  - /url: https://www.foundit.in/career-services/ai-interview-coach
                  - generic [ref=f26e215]:
                    - heading [level=4] [ref=f26e216]: AI Interview Coach
                    - paragraph [ref=f26e217]: Practice realistic interviews with AI and prepare to perform with confidence
                  - generic [ref=f26e218]:
                    - generic [ref=f26e219]:
                      - generic [ref=f26e220]: Starting @
                      - generic [ref=f26e221]: ₹399
                      - generic [ref=f26e222]: 20% off
                    - generic [ref=f26e223]: ₹319
                - link [ref=f26e227] [cursor=pointer]:
                  - /url: https://www.foundit.in/career-services/resume-services
                  - generic [ref=f26e231]:
                    - heading [level=4] [ref=f26e232]: Resume Writing
                    - paragraph [ref=f26e233]: Craft a compelling professional story to put your best foot forward.
                  - generic [ref=f26e234]:
                    - generic [ref=f26e235]: Starting @
                    - generic [ref=f26e237]: ₹3999
              - button "→" [ref=f26e238] [cursor=pointer]
          - generic [ref=f26e242]:
            - generic [ref=f26e243]:
              - generic [ref=f26e245]:
                - img "prep" [ref=f26e246]
                - heading "prep" [level=1] [ref=f26e247]
              - paragraph [ref=f26e248]: Get your own rehearsal space to prep and ace every interview that comes your way
            - link "icon Interview Preparation Master your next job interview with our detailed Q&A guide. Starting @ ₹499" [ref=f26e257] [cursor=pointer]:
              - /url: https://www.foundit.in/career-services/interview-preparation
              - img "icon" [ref=f26e260]
              - generic [ref=f26e261]:
                - heading "Interview Preparation" [level=4] [ref=f26e262]
                - paragraph [ref=f26e263]: Master your next job interview with our detailed Q&A guide.
              - generic [ref=f26e264]:
                - generic [ref=f26e265]: Starting @
                - generic [ref=f26e267]: ₹499
        - generic [ref=f26e272]:
          - generic [ref=f26e273]:
            - img "top phone" [ref=f26e275]
            - img "bottom phone" [ref=f26e277]
          - generic [ref=f26e278]:
            - heading "Talk to our experts!" [level=3] [ref=f26e279]
            - paragraph [ref=f26e280]: Want to pick the service that works for you? Reach out to our experts
          - generic [ref=f26e282]:
            - generic [ref=f26e283]:
              - generic [ref=f26e285]:
                - paragraph [ref=f26e287]: Name *
                - textbox "Enter your full name" [ref=f26e290]
              - generic [ref=f26e292]:
                - paragraph [ref=f26e294]: Email ID *
                - textbox "Enter your email id" [ref=f26e297]
              - generic [ref=f26e299]:
                - paragraph [ref=f26e301]: Mobile Number *
                - generic [ref=f26e304]:
                  - generic [ref=f26e306]:
                    - textbox [ref=f26e309] [cursor=pointer]
                    - generic [ref=f26e311] [cursor=pointer]
                  - textbox "Enter your mobile number" [ref=f26e316]
              - button "Talk to an Expert" [disabled] [ref=f26e317]
            - paragraph [ref=f26e321]: "Toll Free No: 1-800-4196666 Toll No: +91 - 40 - 66116611"
        - generic [ref=f26e323]:
          - heading "What our users have to say" [level=1] [ref=f26e327]
          - generic [ref=f26e328]:
            - text: ←
            - generic [ref=f26e330]:
              - generic [ref=f26e333]:
                - img "Yogesh" [ref=f26e335]
                - generic [ref=f26e336]:
                  - heading "Yogesh" [level=5] [ref=f26e337]
                  - paragraph [ref=f26e338]: Consultant @ANZ - Contract
                  - paragraph [ref=f26e339]: "Product bought: Career Booster"
                - generic [ref=f26e340]: Very detailed, crisp, and timely.
              - generic [ref=f26e343]:
                - img "Vipulkumar" [ref=f26e345]
                - generic [ref=f26e346]:
                  - heading "Vipulkumar" [level=5] [ref=f26e347]
                  - paragraph [ref=f26e348]: Product Development Specialist @3M India
                  - paragraph [ref=f26e349]: "Product bought: Featured profile"
                - generic [ref=f26e350]: Had effective interactions throughout the process. Satisfied with the outcome!
              - generic [ref=f26e353]:
                - img "Molby Dsouza" [ref=f26e355]
                - generic [ref=f26e356]:
                  - heading "Molby Dsouza" [level=5] [ref=f26e357]
                  - paragraph [ref=f26e358]: Front Desk Manager @celebrity Cruises
                  - paragraph [ref=f26e359]: "Product bought: Resume Writing"
                - generic [ref=f26e360]: Was formal, well-constructed and worded in a proper way. Overall communication was good between the writer and myself.
              - generic [ref=f26e363]:
                - generic [ref=f26e366]:
                  - heading [level=5] [ref=f26e367]: Hakimuddin Shakir
                  - paragraph [ref=f26e368]: Senior Analyst @The Boston Consulting Group (India) Private Limited
                  - paragraph [ref=f26e369]: "Product bought: Featured profile"
                - generic [ref=f26e370]: The response time was quick and all the queries were responded to accurately.
              - generic [ref=f26e373]:
                - generic [ref=f26e376]:
                  - heading [level=5] [ref=f26e377]: Swathi Varma
                  - paragraph [ref=f26e378]: Software Quality Lead @Smarterhealth
                  - paragraph [ref=f26e379]: "Product bought: Resume Writing"
                - generic [ref=f26e380]: Overall the resume service was good, quick, and prompt!
              - generic [ref=f26e383]:
                - generic [ref=f26e386]:
                  - heading [level=5] [ref=f26e387]: Biju Abraham P
                  - paragraph [ref=f26e388]: Location Head @Bharat petroleum Corp
                  - paragraph [ref=f26e389]: "Product bought: Resume Writing"
                - generic [ref=f26e390]: Excellent job done in building a new resume. Keep up the good work!
              - generic [ref=f26e393]:
                - generic [ref=f26e396]:
                  - heading [level=5] [ref=f26e397]: Kishor Karanth
                  - paragraph [ref=f26e398]: Business Finance Manager @Alorica India
                  - paragraph [ref=f26e399]: "Product bought: Featured profile"
                - generic [ref=f26e400]: I'm satisfied with the service and it was done professionally!
              - generic [ref=f26e403]:
                - generic [ref=f26e406]:
                  - heading [level=5] [ref=f26e407]: Kamal
                  - paragraph [ref=f26e408]: Sr.Territory Executive @Jubilant agri and consumers product ltd
                  - paragraph [ref=f26e409]: "Product bought: Resume Writing"
                - generic [ref=f26e410]: I liked your way of presenting my skills!
              - generic [ref=f26e413]:
                - generic [ref=f26e416]:
                  - heading [level=5] [ref=f26e417]: Iman Shoukry
                  - paragraph [ref=f26e418]: IT computer Software @Mathnasium Learning Center
                  - paragraph [ref=f26e419]: "Product bought: Featured profile"
                - generic [ref=f26e420]: It is of high quality and I am extremely pleased with the results. Thank you
            - button "→" [ref=f26e421] [cursor=pointer]
            - list [ref=f26e425]:
              - listitem [ref=f26e426]:
                - generic [ref=f26e427] [cursor=pointer]
              - listitem [ref=f26e428]:
                - generic [ref=f26e429] [cursor=pointer]
              - listitem [ref=f26e430]:
                - generic [ref=f26e431] [cursor=pointer]
              - listitem [ref=f26e432]:
                - generic [ref=f26e433] [cursor=pointer]
        - generic [ref=f26e436]:
          - heading "Frequently Asked Questions" [level=1] [ref=f26e439]
          - generic [ref=f26e441]:
            - generic [ref=f26e442]:
              - heading "What career services does foundit offer?" [level=3] [ref=f26e444]
              - paragraph [ref=f26e448]: foundit offers various services to help job seekers in their career development. These include the Featured Profile feature, Resume Writing Services, LinkedInMakeover, Resume Builder, Profile Highlighter, Mock Interview services, Interview Preparation assistance, Online Courses, Assessments and MentorConnect.
            - heading "Are these services suitable for recent graduates or experienced professionals?" [level=3] [ref=f26e451] [cursor=pointer]
            - heading "How is Career Booster different from other services?" [level=3] [ref=f26e456] [cursor=pointer]
            - heading "What e-learning services are available on foundit? Does it offer certification?" [level=3] [ref=f26e461] [cursor=pointer]
            - heading "What is foundit's Online Degree Program?" [level=3] [ref=f26e466] [cursor=pointer]
    - generic [ref=f26e470]:
      - generic [ref=f26e471]:
        - generic [ref=f26e472]:
          - generic [ref=f26e473]: Beware of Scammers
          - paragraph [ref=f26e477]: We don’t charge money for job offers
        - link "Know More" [ref=f26e479] [cursor=pointer]:
          - /url: /info/besafe
      - generic [ref=f26e482]:
        - generic [ref=f26e484]:
          - tablist [ref=f26e486]:
            - tab "Browse Jobs by" [selected] [ref=f26e487] [cursor=pointer]
            - tab "Trending Jobs" [ref=f26e488] [cursor=pointer]
            - tab "Services" [ref=f26e489] [cursor=pointer]
            - tab "Trending Skills" [ref=f26e490] [cursor=pointer]
            - tab "Trending Jobs by Role" [ref=f26e491] [cursor=pointer]
            - tab "Top Companies" [ref=f26e492] [cursor=pointer]
            - tab "Career Advice" [ref=f26e493] [cursor=pointer]
            - tab "Jobs by Location" [ref=f26e494] [cursor=pointer]
            - tab "Career Courses" [ref=f26e495] [cursor=pointer]
          - tabpanel "Browse Jobs by" [ref=f26e496]:
            - list [ref=f26e497]:
              - listitem [ref=f26e498]:
                - link "Jobs By Skill" [ref=f26e499] [cursor=pointer]:
                  - /url: https://www.foundit.in/search/jobs-by-skill
              - listitem [ref=f26e500]:
                - link "Jobs By Education" [ref=f26e501] [cursor=pointer]:
                  - /url: https://www.foundit.in/search/jobs-by-education
              - listitem [ref=f26e502]:
                - link "Jobs By Location" [ref=f26e503] [cursor=pointer]:
                  - /url: https://www.foundit.in/search/jobs-by-location
              - listitem [ref=f26e504]:
                - link "Jobs By Function" [ref=f26e505] [cursor=pointer]:
                  - /url: https://www.foundit.in/search/jobs-by-function
              - listitem [ref=f26e506]:
                - link "Jobs By Industry" [ref=f26e507] [cursor=pointer]:
                  - /url: https://www.foundit.in/search/jobs-by-industry
              - listitem [ref=f26e508]:
                - link "Jobs By Title" [ref=f26e509] [cursor=pointer]:
                  - /url: https://www.foundit.in/search/jobs-by-title
              - listitem [ref=f26e510]:
                - link "Jobs By Other" [ref=f26e511] [cursor=pointer]:
                  - /url: https://www.foundit.in/search/jobs-by-other
        - generic [ref=f26e515]:
          - generic [ref=f26e516]:
            - generic [ref=f26e517]:
              - generic [ref=f26e518]: Company Info
              - generic [ref=f26e519]:
                - link "company-info" [ref=f26e520] [cursor=pointer]:
                  - /url: https://www.foundit.in/info/about-us
                  - text: About Us
                - link "company-info" [ref=f26e521] [cursor=pointer]:
                  - /url: https://www.foundit.in/info/feedback
                  - text: Send Feedback
                - link "company-info" [ref=f26e522] [cursor=pointer]:
                  - /url: https://www.foundit.in/info/sitemap
                  - text: Sitemap
            - generic [ref=f26e523]:
              - generic [ref=f26e524]:
                - generic [ref=f26e525]: Selected Country
                - combobox "Selected Country" [ref=f26e527]:
                  - option "India" [selected]
                  - option "Gulf"
                  - option "Hong Kong"
                  - option "Singapore"
                  - option "Philippines"
                  - option "Thailand"
                  - option "Vietnam"
                  - option "Indonesia"
                  - option "Malaysia"
              - generic [ref=f26e528]:
                - generic [ref=f26e529]: Stay Connected
                - generic [ref=f26e530]:
                  - link "stay-connected" [ref=f26e531] [cursor=pointer]:
                    - /url: https://www.facebook.com/founditIN/
                  - link "stay-connected" [ref=f26e533] [cursor=pointer]:
                    - /url: https://twitter.com/foundit_India
                  - link "stay-connected" [ref=f26e535] [cursor=pointer]:
                    - /url: https://www.linkedin.com/company/foundit-jobs/
                  - link "stay-connected" [ref=f26e537] [cursor=pointer]:
                    - /url: https://www.instagram.com/foundit_india/
                  - link "stay-connected" [ref=f26e539] [cursor=pointer]:
                    - /url: https://www.youtube.com/@foundit_IN/featured
            - generic [ref=f26e541]:
              - generic [ref=f26e542]: 24/7 Support
              - generic [ref=f26e544]:
                - generic [ref=f26e545]:
                  - generic [ref=f26e546]: "Toll No:"
                  - link "contact-info" [ref=f26e547] [cursor=pointer]:
                    - /url: tel:+91 80 6985 7811
                    - text: +91 80 6985 7811
                - generic [ref=f26e548]:
                  - generic [ref=f26e549]: "Toll Free No:"
                  - link "contact-info" [ref=f26e550] [cursor=pointer]:
                    - /url: tel:1800-419-6666
                    - text: 1800-419-6666
              - link "mail" [ref=f26e553] [cursor=pointer]:
                - /url: mailto:info@foundit.in
                - text: info@foundit.in
              - link "contact-us" [ref=f26e557] [cursor=pointer]:
                - /url: https://www.foundit.in/info/contact-us
                - text: Contact Us
            - generic [ref=f26e558]:
              - generic [ref=f26e559]: Download the App
              - generic [ref=f26e560]:
                - generic [ref=f26e561]:
                  - link "Get it on Google Play" [ref=f26e562] [cursor=pointer]:
                    - /url: https://play.google.com/store/apps/details?id=com.monsterindia.seeker.views
                    - generic [ref=f26e565]:
                      - generic [ref=f26e566]: Get it on
                      - generic [ref=f26e567]: Google Play
                  - link "Download on the App Store" [ref=f26e568] [cursor=pointer]:
                    - /url: https://apps.apple.com/in/app/monster-jobs/id525775161
                    - generic [ref=f26e571]:
                      - generic [ref=f26e572]: Download on the
                      - generic [ref=f26e573]: App Store
                - generic [ref=f26e574]:
                  - img "foundit app scanner" [ref=f26e575]
                  - generic [ref=f26e576]: Scan to download App
          - generic [ref=f26e577]:
            - generic [ref=f26e578]:
              - generic [ref=f26e579]:
                - link "legal" [ref=f26e580] [cursor=pointer]:
                  - /url: https://www.foundit.in/info/help
                  - text: Help
                - text: "|"
              - generic [ref=f26e581]:
                - link "legal" [ref=f26e582] [cursor=pointer]:
                  - /url: https://www.foundit.in/info/security-center
                  - text: Security & Fraud
                - text: "|"
              - generic [ref=f26e583]:
                - link "legal" [ref=f26e584] [cursor=pointer]:
                  - /url: https://www.foundit.in/info/privacy
                  - text: Privacy Policy
                - text: "|"
              - generic [ref=f26e585]:
                - link "legal" [ref=f26e586] [cursor=pointer]:
                  - /url: https://www.foundit.in/info/terms-of-use
                  - text: Terms of Use
                - text: "|"
              - generic [ref=f26e587]:
                - link "legal" [ref=f26e588] [cursor=pointer]:
                  - /url: https://www.foundit.in/info/besafe
                  - text: Be Safe
                - text: "|"
              - generic [ref=f26e589]:
                - link "legal" [ref=f26e590] [cursor=pointer]:
                  - /url: https://www.foundit.in/info/complaints
                  - text: Complaints
                - text: "|"
              - link "legal" [ref=f26e592] [cursor=pointer]:
                - /url: https://www.foundit.in/info/beware-of-fraudsters
                - text: Beware of Fraudsters
            - generic [ref=f26e593]:
              - generic [ref=f26e594]: © 2026 foundit
              - generic [ref=f26e595]: "|"
              - generic [ref=f26e596]: All rights Reserved
  - alert [ref=f26e597]
```

# Test source

```ts
  1  | const {test, expect}=require('@playwright/test')
  2  | 
  3  | test.only('Single File',async ({page})=>{
  4  | 
  5  |     await page.goto('https://www.foundit.in/');
  6  | 
> 7  |     await page.waitForSelector('.mqfihd-upload');
     |                ^ Error: page.waitForSelector: Test timeout of 30000ms exceeded.
  8  |     await page.locator('.mqfihd-upload').click()
  9  |     
  10 |     //Upload file
  11 |     await page.locator('#file-upload').setInputFiles('tests/uploadFiles/file1.txt');
  12 |     
  13 |     await page.waitForTimeout(5000); 
  14 | 
  15 | })
  16 | 
  17 | test.skip('Multiple Files',async ({page})=>{
  18 | 
  19 |     await page.goto('https://davidwalsh.name/demo/multiple-file-upload.php');
  20 |        
  21 |     //upload multiple files
  22 |     await page.locator('#filesToUpload')
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