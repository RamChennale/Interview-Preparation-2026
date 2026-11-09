#diff between HTTPS and HTTP?


**HTTP** (HyperText Transfer Protocol) sends data between your browser and a server in plain text. 
**HTTPS** (HTTP Secure) is the same protocol, but wrapped in encryption using TLS (formerly SSL).

| | HTTP | HTTPS |
|---|---|---|
| **Full form** | HyperText Transfer Protocol | HyperText Transfer Protocol Secure |
| **Encryption** | None, data travels as readable text | Encrypted with TLS |
| **Default port** | 80 | 443 |
| **Certificate** | Not needed | Needs an SSL/TLS certificate from a Certificate Authority |
| **Security** | Anyone on the network (Wi-Fi, ISP) can read or alter the data | Data is protected from eavesdropping and tampering |
| **URL** | Starts with `http://` | Starts with `https://`, usually with a padlock icon |
| **Speed** | Slightly less overhead | Small handshake overhead, but negligible with modern TLS and HTTP/2 |
| **SEO / trust** | Browsers flag it as "Not secure" | Preferred by search engines and browsers |

**What HTTPS gives you:**
1. **Confidentiality:** data is encrypted, so passwords, card details and personal info can't be read in transit.
2. **Integrity:** data can't be modified on the way without detection.
3. **Authentication:** the certificate proves you're talking to the real server, not an imposter.

**How it works in brief (TLS handshake):**
1. The browser connects and asks the server to prove its identity.
2. The server sends its certificate (with its public key).
3. The browser verifies the certificate against trusted authorities.
4. Both sides agree on a session key, and all further traffic is encrypted with it.

**Testing angle:** since you're in QA, this matters for API and web testing. Tools like Postman, RestAssured and Selenium may throw certificate errors on self-signed or expired certs in test environments. In RestAssured you can use `relaxedHTTPSValidation()` for test environments, but never in production code.

=====================================================================
#To use HTTPS, do we need to pay >

No, HTTPS doesn’t have to cost anything. You need an SSL/TLS certificate, and there are both free and paid options.

Free options

Let’s Encrypt: a free, automated Certificate Authority trusted by all major browsers. Certificates last 90 days and renew automatically with tools like Certbot.
Cloudflare: free SSL when you route your domain through them.
Hosting platforms: GitHub Pages, Netlify, Vercel, Firebase Hosting and many shared hosts give you HTTPS at no extra cost.
Cloud providers: AWS Certificate Manager gives free certificates for use with their services (load balancers, CloudFront).
Paid options
Paid certificates (from DigiCert, Sectigo, GlobalSign and others) typically cost from a few hundred to several lakh rupees per year, depending on type:

DV (Domain Validated): proves domain ownership only. Free ones from Let’s Encrypt are this type.
OV (Organization Validated): the CA also verifies your company.
EV (Extended Validation): strictest verification of the business.
Wildcard: covers all subdomains (*.example.com). Let’s Encrypt offers this free too, but paid ones come with support.
=====================================================================

#SSL/TLS stands for Secure Sockets Layer and Transport Layer Security.
#• SSL (Secure Sockets Layer): 
An older cryptographic protocol developed by Netscape in 1995 to secure internet communications. It is now deprecated due to security vulnerabilities.

#• TLS (Transport Layer Security): 
The modern, upgraded successor to SSL, standardized in 1999 and actively used today to encrypt data between web browsers and servers (powering HTTPS).
