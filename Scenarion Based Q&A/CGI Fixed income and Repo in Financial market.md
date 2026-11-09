
Fixed income and Repo# in Financial market 

## Fixed Income

**Fixed income** refers to investments that pay a **fixed, predictable stream of interest** and return the principal at maturity. You are essentially lending money to a borrower (government or company) and getting paid interest for it.

**Common instruments:**
- **Government bonds / T-bills:** issued by governments (e.g., US Treasuries, Indian G-Secs)
- **Corporate bonds:** issued by companies
- **Municipal bonds:** issued by local governments
- **Debentures, CDs, commercial paper:** short and medium-term debt

**Key terms:**
- **Coupon:** the fixed interest rate paid periodically
- **Maturity:** the date the principal is repaid
- **Yield:** the effective return based on current price
- **Price vs yield:** when interest rates go up, bond prices go down (inverse relationship)
- **Credit rating:** the borrower's creditworthiness (AAA, BBB, etc.)

## Repo (Repurchase Agreement)

A **repo** is a **short-term secured loan**. One party sells a security (usually a bond) and agrees to **buy it back later at a slightly higher price**. The difference is the interest, called the **repo rate**.

**Example:**
1. Bank A needs cash today and has government bonds.
2. It sells the bonds to Bank B for ₹100 crore, with an agreement to repurchase them tomorrow for ₹100.01 crore.
3. Bank B holds the bonds as **collateral** and earns the small difference.
4. Next day, Bank A pays back and gets its bonds returned.

**Two sides of the same trade:**
- **Repo:** the party borrowing cash (selling the security)
- **Reverse repo:** the party lending cash (buying the security)

**Why it matters:**
- Banks and dealers use repos for **short-term funding and liquidity**
- Central banks (like the RBI) use the **repo rate** as a key policy tool to control money supply and inflation

## How they connect

Fixed income securities (bonds) are the **collateral** used in repo transactions. Repo markets are how dealers finance their bond inventory.

## Testing angle (for your domain)

In capital markets apps, QA typically validates things like:
- Trade booking for bonds and repos (trade date, settlement date, maturity date)
- Accrued interest and coupon calculations
- Price/yield conversions
- Repo interest and haircut calculations
- Settlement and confirmation workflows

**Interview tip:** If asked, say: *"Fixed income is debt securities paying regular interest, and a repo is a short-term collateralized loan where a security is sold with an agreement to repurchase it at a higher price, the difference being the repo rate."*