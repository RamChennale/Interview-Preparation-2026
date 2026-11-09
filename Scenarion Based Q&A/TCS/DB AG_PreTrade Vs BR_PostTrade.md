Good context now — but first an important clarification: **T7 and these Broadridge platforms aren't really competitors or comparable in the same category.** T7 is an **exchange matching engine** (front-office, order execution). Gloss, Impact, BPS, and RPM are **post-trade / back-office processing platforms** used by brokers, banks, and prime brokers — they sit *after* a trade has already been matched somewhere (possibly on T7 itself). Here's the breakdown:

## What Each Platform Actually Does

| Platform | Owner | Category | Core Function |
|---|---|---|---|
| **T7** | Deutsche Börse Group | **Exchange matching engine** (front-office) | Matches buy/sell orders for derivatives and cash trading on exchanges like Eurex, EEX, Xetra, Nodal |
| **Gloss** | Broadridge | **Post-trade settlement & custody** | Multi-currency trade processing and settlement, real-time custody and financing for prime brokers, with FX confirmation/matching added via City Networks |
| **Impact** | Broadridge | **Fixed-income post-trade processing** | Componentized value-added solutions for fixed-income firms, including triparty collateral processing and settlement instruction automation |
| **BPS** | Broadridge | **Broker-dealer back-office** | Core back-office system supporting real-time processing of equity and option transactions in North America — order management through clearance, settlement, and custody, plus regulatory reporting, on a SaaS basis |
| **RPM** | Broadridge (acquired 2019) | **Wealth management platform (Canada)** | 
Enterprise wealth management software for the Canadian market, 
supporting over 15 million customer accounts, with mutual fund and deposit manufacturing capabilities |

## Key Structural Differences

**1. Where they sit in the trade lifecycle**
- **T7** = the exchange itself — where the actual buy and sell orders meet and get matched. It's the *source of truth* for execution.
- **Gloss/Impact/BPS/RPM** = downstream systems that take an already-executed trade and process settlement, custody, accounting, reporting, and client-facing back-office functions. Broadridge's capital markets technology delivers simplification across the trade lifecycle, from order initiation to settlement, through front-office execution management and post-trade product suites.

**2. Who uses them**
- T7 is used by exchanges and their direct trading participants (banks, prop firms, brokers submitting orders to the market).
- Broadridge's suite is used by broker-dealers, prime brokers, and wealth managers to manage *their own* operations and client relationships after trades happen — not to trade on an exchange themselves.

**3. Latency profile**
- T7 is engineered for microsecond-level matching speed delivering ultra-low latency, robustness, and safe handling of very high throughput, because it's competing on execution speed for trading firms.
- Broadridge's platforms operate on a completely different time horizon — settlement cycles (T+1/T+2), end-of-day batch processing, and real-time-but-not-microsecond record-keeping. Speed isn't the differentiator; accuracy, compliance, and multi-asset/multi-currency coverage are.

**4. Asset/market scope**
- T7 covers derivatives and cash instruments traded specifically on the exchanges it powers (Eurex, Xetra, EEX, etc.)
- Gloss and Impact are explicitly multi-asset and multi-currency — Gloss covers equities, fixed income, FX, CFDs, exchange-traded futures, forward rate agreements, and interest rate swaps across multiple markets globally, not tied to one exchange.
- BPS is North America-focused for equities, options, fixed income, and mutual funds.
- RPM is Canada-specific, focused on retail wealth management, mutual funds, and deposit products — not institutional trading at all.

**5. "Newly acquired" — worth double-checking**
Only **RPM** was truly a recent acquisition (announced May 2019, ~$300 million, adding Canadian wealth management capabilities). Gloss, Impact, and BPS are long-standing, organically-built or older-acquired Broadridge products (Gloss dates back to at least the early 2010s in Broadridge's portfolio, BPS is core legacy back-office tech). If your interviewer says these were "newly acquired," it may be worth gently clarifying in conversation — or they may be referring to Broadridge's broader recent M&A wave (they've also recently acquired CQG, adding futures/options execution and connectivity capabilities, and earlier Shadow Financial Systems for exchange-traded derivatives and crypto post-trade capabilities).

## Why This Comparison Matters for Your Interview

If this comes up in round 3, the sharpest answer is: **T7 is what these platforms plug into, not what they compete with.**
 A bank might route an order to Eurex via T7 for matching, then use Gloss or BPS to settle, custody, and report on that same trade afterward. Testing T7 means testing *order matching correctness* (price-time priority, throttling, market data accuracy); testing something like BPS or Gloss means testing *settlement accuracy, reconciliation, and regulatory reporting* — a very different functional domain, even though both are "trading system testing" in a loose sense.

If you want, I can help you frame a strong answer for "how would your experience with [X] transfer to T7 testing" if one of these platforms is actually part of your background.