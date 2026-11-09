**14. How do you handle CAPTCHA, OTP, or third-party auth in automation?**
Best practice: request the dev/QA team to disable CAPTCHA in test environments, or use test-specific bypass tokens/API hooks. 
For OTP, use test accounts with a fixed OTP or intercept via an email/SMS testing API (e.g., Mailosaur, Twilio test credentials). 
Never try to "solve" CAPTCHA programmatically — that's both fragile and against most CAPTCHA providers' terms.
