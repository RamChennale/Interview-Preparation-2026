
#Provide the list scenarios based interview question and answer in Financial Domain Capital Market, Trading, FixedIncome securities for Bonds and Repo for 
#Senior test Engineer having 2 to 4 years experience in Financial domain from Capital Market to  Trading, FixedIncome securities for Bonds and Repo ?

===============================================================================================================================================================
BR BondGPT: related 

**Q36. How do you test an AI/LLM-based assistant that searches or explains bonds?**

- **Grounding:** answers must match the source data (yield, maturity, rating) with no invented figures.
- **Golden question set** with expected facts, scored automatically, and a human review sample.
- **Adversarial tests:** ambiguous queries, wrong ISIN, out-of-scope questions, and prompt injection.
- **Regression** on each model, prompt, or data update, since outputs are non-deterministic and need tolerance-based checks.
- **Compliance:** no investment advice without disclaimers, no leakage of other clients' data, and logging for audit.

---

# Part 1: Financial Domain Glossary (50 Key Terms)

## Bonds and Pricing

| # | Term | One-line definition |
|---|---|---|
| 1 | **Bond** | A loan you give to an issuer, who pays you interest and returns your money at maturity. |
| 2 | **Issuer** | The government or company that borrows money by selling the bond. |
| 3 | **Face value (par)** | The amount the issuer repays at maturity, usually 100 per 100 or 1,000 per bond. |
| 4 | **Coupon** | The periodic interest payment, quoted as a percentage of face value. |
| 5 | **Maturity date** | The date the issuer repays the face value and the bond ends. |
| 6 | **ISIN** | The 12-character global ID of a security, used to find the right bond in every system. |
| 7 | **Clean price** | The quoted bond price without accrued interest. |
| 8 | **Accrued interest** | The coupon earned since the last coupon date up to the settlement date. |
| 9 | **Dirty price** | Clean price plus accrued interest, which is what the buyer actually pays. |
| 10 | **Yield to maturity (YTM)** | The total annual return if you buy at today's price and hold to maturity. |
| 11 | **Day count convention** | The rule for counting days in interest math (30/360, Actual/360, Actual/Actual). |
| 12 | **Premium / Discount / Par** | Price above, below, or equal to face value. |
| 13 | **Duration** | How sensitive a bond's price is to interest rate changes, expressed in years. |
| 14 | **DV01** | The money gained or lost when the yield moves by one basis point (0.01%). |
| 15 | **Callable bond** | A bond the issuer can repay early at a set call price. |
| 16 | **Floating rate note (FRN)** | A bond whose coupon resets periodically as a reference rate (e.g., SOFR) plus a spread. |
| 17 | **Zero-coupon bond** | A bond that pays no coupon and is sold at a discount to face value. |
| 18 | **Security master** | The reference database holding each security's static details (coupon, dates, currency). |

## Repo

| # | Term | One-line definition |
|---|---|---|
| 19 | **Repo** | Selling securities now with an agreement to buy them back later at a higher price, effectively a collateralized loan. |
| 20 | **Reverse repo** | The same trade seen from the cash lender, who buys the securities and agrees to sell them back. |
| 21 | **Repo rate** | The interest rate charged on the cash borrowed in a repo. |
| 22 | **Haircut** | The discount applied to collateral value so the lender stays protected. |
| 23 | **Collateral** | The securities pledged to secure the cash loan. |
| 24 | **Margin call** | A request for extra collateral or cash when the collateral value falls below the required level. |
| 25 | **Tri-party repo** | A repo where a third-party agent handles collateral selection, valuation, and settlement. |
| 26 | **Near leg / Far leg** | The opening exchange (cash for securities) and the closing exchange (cash plus interest for securities back). |

## Trading and Orders

| # | Term | One-line definition |
|---|---|---|
| 27 | **Order** | An instruction to buy or sell a quantity of a security on given terms. |
| 28 | **Market order** | An order to trade immediately at the best available price. |
| 29 | **Limit order** | An order to trade only at your price or better. |
| 30 | **Bid / Ask** | The price a buyer will pay (bid) and the price a seller wants (ask). |
| 31 | **RFQ (Request for Quote)** | A client asking one or more dealers for prices before trading, common in bond markets. |
| 32 | **Execution (fill)** | The moment an order is matched and the trade actually happens. |
| 33 | **Partial fill** | Only part of the order quantity is executed. |
| 34 | **Allocation** | Splitting one executed block trade across several client accounts. |
| 35 | **FIX protocol** | The standard message format that trading systems use to send orders and executions. |
| 36 | **Counterparty** | The other side of your trade. |
| 37 | **Position** | How much of a security you currently hold (long) or owe (short). |

## Post-Trade and Settlement

| # | Term | One-line definition |
|---|---|---|
| 38 | **Trade date (T)** | The day the trade is agreed and executed. |
| 39 | **Settlement date** | The day securities and cash actually change hands. |
| 40 | **T+1** | Settlement one business day after the trade date. |
| 41 | **DVP (Delivery versus Payment)** | Securities and cash exchange at the same time, so neither side is exposed. |
| 42 | **FOP (Free of Payment)** | Securities move without cash, such as collateral moves between your own accounts. |
| 43 | **Confirmation / Affirmation** | Both sides agreeing that the trade details (quantity, price, date) match. |
| 44 | **SSI (Standing Settlement Instructions)** | Pre-agreed account details telling where to deliver securities and cash. |
| 45 | **Custodian** | The bank that safekeeps securities and carries out settlement for you. |
| 46 | **CSD (Central Securities Depository)** | The institution that holds securities electronically and records ownership changes (DTC, Euroclear). |
| 47 | **CCP (clearing house)** | A central party that stands between buyer and seller to guarantee the trade. |
| 48 | **Settlement fail** | The securities or cash did not arrive on the due date. |
| 49 | **Reconciliation** | Comparing your records with the custodian's or counterparty's to find differences (breaks). |
| 50 | **Corporate action** | An issuer event that affects holders, such as a coupon payment, call, or redemption. |

---

# Part 2: For a 2-4 Year Test Engineer (Happy Path, Plain Language)

## The big picture in simple words

Think of buying a bond like buying a house:

| House purchase | Bond trade | Test focus |
|---|---|---|
| You make an offer | **Order** | Is the order captured correctly? |
| Seller accepts | **Execution (fill)** | Did the trade happen at the right price and quantity? |
| Both sign the contract | **Confirmation** | Do both sides agree on details? |
| Keys and money exchanged on closing day | **Settlement (DVP)** | Did securities and cash move correctly on the settlement date? |
| Bank statement matches | **Reconciliation** | Do our records match the custodian's? |

**Happy path** means everything goes right: valid data, enough position or limit, and no rejection, delay, or failure. Always check three things: **the status, the numbers, and the downstream systems**.

---

## 1. Order (Transaction) Happy Path

| # | Scenario | What you do | What you should see |
|---|---|---|---|
| O1 | **Buy limit order** | Place a buy for 1,000,000 face of a bond at limit price 99.00 | Order accepted, status **New**, quantity, side (Buy), price, ISIN, currency, and trade date all correct |
| O2 | **Sell order with enough position** | Sell 500,000 from a position of 1,000,000 | Order accepted, status **New**, and the position check passes (no short-sell block) |
| O3 | **Market order** | Place a buy market order | Order accepted and routed for immediate execution, with no price entered |
| O4 | **Amend before fill** | Change the quantity from 1,000,000 to 800,000 on an unfilled order | Order updated, version/audit trail shows old and new values, status stays open |
| O5 | **Cancel unfilled order** | Cancel an order that has no fills | Status moves to **Cancelled**, no trade or position impact |
| O6 | **Pre-trade checks pass** | Place an order within credit and position limits | Order passes compliance and limit checks, and the limit usage increases by the order value |
| O7 | **Correct counterparty and account** | Book the order for a specific client account | Account, counterparty, and SSI are linked to the order correctly |

---

## 2. Fulfilment (Execution and Confirmation) Happy Path

| # | Scenario | What you do | What you should see |
|---|---|---|---|
| F1 | **Full fill** | Order for 1,000,000 gets fully executed at 99.20 | Status **Filled**, filled quantity = 1,000,000, remaining = 0, execution price 99.20 |
| F2 | **Partial fills then full** | 600,000 filled at 99.00, then 400,000 at 99.50 | Status **Partially Filled**, then **Filled**. Average price = (600,000×99.00 + 400,000×99.50) ÷ 1,000,000 = **99.20** |
| F3 | **Trade booking** | After the fill, check the trade record | A trade ID exists, and ISIN, quantity, price, trade date, settlement date, and counterparty match the order |
| F4 | **Position update** | Check holdings after the buy | Position increases by 1,000,000 face, and the cost/average price updates |
| F5 | **Allocation of a block trade** | Allocate the 1,000,000 trade to two accounts: 600,000 and 400,000 | Allocated total = filled total, and each account has its own trade record |
| F6 | **Confirmation matched** | Counterparty confirms the same details | Status moves to **Matched/Confirmed**, with no mismatch on price, quantity, or date |
| F7 | **Settlement amount** | Check the money due on the buy (clean 99.20, accrued 1.25 per 100) | Clean amount = 1,000,000 × 99.20 ÷ 100 = 992,000. Accrued = 12,500. **Total to pay = 1,004,500** |

---

## 3. Settlement of Securities Happy Path

| # | Scenario | What you do | What you should see |
|---|---|---|---|
| S1 | **Settlement date is correct** | Trade on Monday under T+1 | Settlement date = **Tuesday**. A Friday trade settles **Monday**, skipping the weekend, and holidays are respected |
| S2 | **Instruction created (DVP)** | After confirmation, check the instruction sent to the custodian | A **DVP** instruction exists with the correct ISIN, quantity, amount, settlement date, and SSI/safekeeping account |
| S3 | **Instruction matched** | Custodian and counterparty instructions are paired | Status moves to **Matched** before settlement day |
| S4 | **Settled on due date** | Run to the settlement date | Status **Settled**. Securities are delivered or received, cash is paid or received, and both happen together (DVP) |
| S5 | **Position and cash update** | Check balances after settlement | Settled position increases by 1,000,000 face and the cash balance decreases by 1,004,500 |
| S6 | **Reconciliation clean** | Compare our records to the custodian statement | No breaks on securities or cash |
| S7 | **Repo opening and closing leg** | Repo: borrow 10,000,000 cash at 5.30% for 7 days against bond collateral | Near leg: collateral delivered, cash received. Far leg: interest = 10,000,000 × 5.30% × 7 ÷ 360 = **10,305.56**, repayment = **10,010,305.56**, and collateral returned |
| S8 | **Coupon or maturity payment** | A held bond reaches a coupon date | Coupon cash is credited on the right date for the right amount, and the accrual resets |

---

## Simple Checklist for Every Happy Path Test

1. **Status** moves through the right steps: New → Filled → Confirmed → Settled.
2. **Numbers** match your own calculation: quantity, price, accrued interest, settlement amount, interest.
3. **Dates** are right: trade date, settlement date, coupon date, repo far-leg date.
4. **Downstream systems** agree: position, cash, reports, and custodian statement.
5. **Audit trail** shows who did what and when.

## Interview Tip for This Level

Explain with one flow and one calculation: "I placed a buy order for 1,000,000 face, it filled in two parts at an average of 99.20, the settlement amount was 1,004,500 including accrued interest, and I verified the DVP instruction, the T+1 settlement date, and the custodian reconciliation."

I can also turn this into a **PDF or Word study sheet**, or run a **mock interview** at the 2-4 year level with negative scenarios (rejects, fails, mismatches) as the next step.

===============================================================================================================================================================
***********************************************************************************************************************************************************

#Provide the list scenarios based interview question and answer in Financial Domain Capital Market, Trading, FixedIncome securities for Bonds and Repo for 
#Senior test Engineer having 10 years experience in Financial domain from Capital Market to  Trading, FixedIncome securities for Bonds and Repo ?


# Capital Markets, Trading, Bonds and Repo: Scenario-Based Interview Q&A (Senior Test Engineer, 10 Years)

Answers are written in first person so you can adapt them. Confirm regulatory dates and market conventions against your own firm's setup before quoting them.

---

## A. Trade Lifecycle and Capital Markets Fundamentals

**Q1. Walk me through the trade lifecycle and where you would test at each stage.**

Pre-trade → execution → trade capture → confirmation/affirmation → allocation → clearing → settlement → reconciliation and accounting → regulatory reporting.

| Stage | What I test |
|---|---|
| Pre-trade | Compliance, credit and position limits, fat-finger checks, restricted lists, eligibility |
| Execution | Order routing, fills (partial and full), amend/cancel, price and quantity validation |
| Trade capture | Booking accuracy, reference data, counterparty, settlement date and currency |
| Confirmation | Matching with the counterparty, mismatch handling, status transitions |
| Settlement | DVP/FOP instructions, SSI (standing settlement instructions), fails, status messages |
| Post-trade | Position and cash reconciliation, P&L, accruals, GL postings |
| Reporting | Regulatory fields, timeliness, rejects and resubmission |

Interviewers want to hear that testing is a flow across systems, not one screen. I trace one trade end to end through every downstream system.

**Q2. A trade is booked correctly in the front office but shows a different settlement date in the back office. How do you investigate?**

1. Compare the trade date, security type, market, and currency in both systems.
2. Check the **settlement cycle rule** for that security (T+0/T+1/T+2 varies by instrument and market).
3. Check the **holiday calendar**: the settlement currency calendar and the market calendar can differ (a US bond trade settling in USD vs a GBP trade).
4. Check for a manual override of the settlement date on the booking.
5. Look at the interface message and the transformation or mapping layer.

Typical root causes are a wrong calendar, a hardcoded cycle, or a security-master default that overrides the trade-level value. I add boundary tests around month-end, year-end, and public holidays.

**Q3. Your firm is moving from T+2 to T+1 settlement. How do you plan testing?**

- **Impact analysis:** settlement date logic, cut-off times, affirmation/confirmation deadlines, funding and cash forecasting, FX settlement for cross-currency trades, fails and penalties, and reports.
- **Scenarios:** trades before and after the cutover date, trades spanning weekends and holidays, cross-border trades with different cycles, and amendments after cutover.
- **Timing:** compressed windows mean confirmation and allocation must happen the same day, so I test batch and interface timings under load.
- **Rollback:** I verify behavior for trades in flight during the switchover.

**Q4. How do you handle equity versus fixed income differences when designing a common test suite?**

Equities are mostly exchange-traded and quantity-driven with a price-time priority order book. Bonds are mostly OTC and quote-driven (RFQ, dealer axes), with accrued interest, yield, day counts, and clean/dirty price. I use a shared core suite for lifecycle states, workflows, and permissions, and asset-specific suites for pricing, settlement, and corporate actions. Parameterized test data keeps the framework common.

---

## B. Bonds: Pricing, Yield, Accrued Interest

**Q5. Explain clean price, dirty price, and accrued interest, and how you would test them.**

- **Clean price:** quoted price without accrued interest.
- **Accrued interest:** coupon earned since the last coupon date up to settlement (not trade date).
- **Dirty (invoice) price** = clean price + accrued interest. This is what the buyer pays.

Example: $1,000,000 face, clean price 98.50, accrued 1.25 per 100. Clean value = 985,000, accrued = 12,500, settlement amount = 997,500.

I test with an independent calculation (Excel `ACCRINT`/`PRICE`/`YIELD` or a QuantLib script) as the oracle, with a small tolerance, and verify that the settlement amount uses the **settlement date**, not the trade date.

**Q6. Two bonds have the same coupon and dates but different day-count conventions. How does that affect testing?**

Example: 6% semiannual coupon, $1,000,000 face, coupon dates Jan 15 / Jul 15, settlement Mar 15 (non-leap year):

- **30/360:** 60 days → 1,000,000 × 6% × 60/360 = **10,000.00**
- **Actual/Actual:** 59 days of a 181-day period → 30,000 × 59/181 = **9,779.01**

Common conventions: 30/360 (corporates, agencies), Actual/Actual (US Treasuries), Actual/360 (money market, USD repo), Actual/365 (GBP markets). I build the test matrix from convention × coupon frequency × leap year × end-of-month dates, because defects hide at month-end (Feb 28/29, the 31st) and in stub periods.

**Q7. Price and yield are shown inconsistently on the screen. How do you validate?**

I check the relationships rather than only the numbers:

- Price and yield move inversely.
- Coupon = yield → price ≈ par. Coupon > yield → premium. Coupon < yield → discount.
- Zero-coupon bond: price = face / (1 + y)^t (using the correct compounding).
- For callable bonds, **yield-to-worst** is the lowest of yield-to-maturity and the yield to each call date.

I then reconcile screen values against the pricing engine's API output and my reference calculator, and test boundaries: yield at 0%, negative yields, a bond one day from maturity, and a bond on its coupon date.

**Q8. How do you test duration, DV01, and risk numbers?**

DV01 ≈ modified duration × market value × 0.0001. For a $1M position with a modified duration of 7, DV01 ≈ $700 per basis point. I validate by **bump-and-reprice**: shift the yield by 1 bp, reprice, and compare the P&L change with the reported DV01. I also check aggregation at portfolio level (sum of position DV01s), sign conventions for long and short positions, and currency conversion.

**Q9. A floating rate note (FRN) coupon is wrong after a rate reset. What do you check?**

Coupon = reference rate (e.g., SOFR) + spread, applied for the reset period.

- Was the **reset date** and observation/lookback rule applied correctly?
- Did the rate feed (market data) update and map to the right index and tenor?
- Was the day-count applied to the new period?
- Are caps, floors, and minimum rates respected?
- Was accrued interest recalculated from the reset date?

I test with a rate feed file containing boundary values (zero, negative, missing, duplicate) and verify the system fails safe on a missing rate instead of silently using the previous one.

**Q10. A callable bond is called early (partial call). What are the test scenarios?**

- Position reduces pro-rata or by lottery per the issuer's terms.
- Redemption cash = called face × call price, plus accrued to the call date.
- Remaining position continues to accrue on the reduced face.
- Cost basis, P&L, and tax lot updates.
- Notifications to clients and accounting entries.
- Corporate action mandatory event: **record date / ex-date / payment date** handling.
- A trade in flight at the time of the call (settlement against the old face).

**Q11. How do you test bond reference data (security master)?**

Security master errors cascade into everything. I check ISIN/CUSIP/SEDOL validity (check digits), issue and maturity dates, coupon rate and frequency, day-count, business-day convention, currency, call/put schedules, rating, and issuer. I reconcile against a vendor feed (e.g., Bloomberg/Refinitiv), test new-issue creation, amendments, and the behavior when a mandatory field is missing. Negative tests include maturity before issue date and invalid frequency.

---

## C. Repo (Repurchase Agreements)

**Q12. Explain a repo to me and how you would structure testing.**

A repo is a sale of securities with an agreement to repurchase them at a later date at a set price. Economically it is **collateralized borrowing**. The **seller** of the collateral receives cash and pays the repo rate. The **buyer** is the cash lender. For the buyer, the same trade is a **reverse repo**.

- **Near (opening) leg:** securities delivered, cash received.
- **Far (closing) leg:** cash plus interest returned, securities returned.

Testing areas: trade booking (both legs), interest calculation, collateral valuation and haircuts, margining, settlement of both legs, substitution, rollover or termination, coupon handling during the term, and reporting.

**Q13. How do you verify repo interest and repurchase price?**

Repo interest = cash × repo rate × days / day-count basis (USD typically Actual/360).

Example: $10,000,000 at 5.30%:

- Overnight: 10,000,000 × 0.053 × 1/360 = **1,472.22**
- 7 days: 10,000,000 × 0.053 × 7/360 = **10,305.56**, repurchase price = **10,010,305.56**

I test with different day counts (Actual/365 for GBP), term dates across weekends and holidays, and early termination (interest recalculated for actual days).

**Q14. What are haircuts and margin, and what test cases do you design?**

The haircut is the discount applied to the collateral's market value so the lender is over-collateralized. With 102% margin on $10M cash, collateral value is about $10.2M. Conventions vary between firms: some define a haircut percentage, others a margin ratio, so I confirm which one the system uses.

Test cases:
- Haircut by asset class, rating, and maturity bucket.
- Collateral price change triggers re-valuation.
- **Margin call** is generated only when the shortfall exceeds the threshold or minimum transfer amount.
- Margin call direction: collateral falls → cash borrower posts more; collateral rises → the borrower can request a return.
- Zero haircut, 100% haircut edge cases, and rounding of margin amounts.

**Q15. A bond used as repo collateral pays a coupon during the repo term. What should happen?**

The coupon economically belongs to the original owner (the repo seller), even though the buyer holds the bond on the coupon date. The buyer must pass the equivalent amount back through a **manufactured payment**. Tests: correct amount (net of tax rules if configured), correct payment date, correct counterparty direction, accounting entries, and no double-counting in P&L or accruals. I also test a coupon date that falls on the far-leg date.

**Q16. Collateral substitution is requested mid-term. What do you test?**

- The replacement security meets eligibility (rating, maturity, currency, issuer concentration).
- Market value of the new collateral covers the required amount after haircut.
- Both settlement movements occur (return old, deliver new), ideally atomically (DVP).
- Interest and accruals are unaffected, and the audit trail is complete.
- Rejection path: an ineligible bond is rejected with a clear message and no partial movement.
- Concurrent margin call and substitution on the same day.

**Q17. How is tri-party repo different, and how does it change your test approach?**

In tri-party repo, a custodian or agent bank handles collateral selection, valuation, margining, and settlement according to an eligibility schedule. Testing focuses on:

- Interface files/messages with the agent (instructions, allocation reports, statements).
- Allocation against the eligibility schedule and concentration limits.
- Reconciliation of our positions and exposures with the agent's reports.
- Handling agent-side rejections, late reports, and cut-off times.

Bilateral repo has more manual collateral and settlement steps. Tri-party has more integration risk.

**Q18. An open (evergreen) repo's rate needs to be repriced. What are the scenarios?**

Open repos have no fixed maturity and roll daily, so I test daily rollover, rate change effective date, interest accrual split before and after the rate change, termination on notice, and a holiday or weekend roll. I also verify that statements show the right rate for each period.

**Q19. A repo settles but the collateral is not delivered. What is your test strategy for fails?**

I simulate a settlement fail through the counterparty simulator or status message and verify:

- Status moves to **failed/pending** and does not show as settled.
- Cash is not released (DVP protection).
- Fails charges or penalties apply per market rules.
- Operations alerts and aging of fails.
- Retry on the next day, partial delivery, and **buy-in** or cancellation procedures.
- Interest calculation is adjusted for the actual settlement date.

---

## D. Trading Systems, FIX, and Market Data

**Q20. How do you test order flow using FIX?**

I use a FIX simulator or QuickFIX/J-based test harness to act as the client and the venue. Key messages: `35=D` NewOrderSingle, `35=8` ExecutionReport, `35=F` cancel request, `35=G` cancel/replace, `35=9` OrderCancelReject. Key tags: `11` ClOrdID, `37` OrderID, `55` Symbol, `54` Side, `38` OrderQty, `44` Price, `40` OrdType, `59` TimeInForce, `39` OrdStatus, `150` ExecType, `34` MsgSeqNum.

Scenarios: new → ack → partial fill → fill, rejects (bad symbol, price outside limits), amend, cancel, and session-level behavior (logon, heartbeat, sequence gap, resend request, `PossDup`).

**Q21. A cancel request arrives after the order was filled. What should happen?**

The system should respond with an OrderCancelReject (`35=9`) with a "too late to cancel" reason, and the order status must remain **Filled**. I test the race with different timing: cancel before, during (partial fill), and after the fill. I also verify no duplicate or negative position results and that the audit log is consistent.

**Q22. A FIX session drops and reconnects with a sequence number gap. How do you test recovery?**

Test the resend request (`35=2`), duplicate handling (`43=Y` PossDup), a sequence reset (`35=4`) and when it is allowed, and the behavior with a too-low sequence number (disconnect). The key checks are no lost executions, no duplicate fills applied to the position, and orders in flight are reconciled after reconnect.

**Q23. How do you test partial fills and allocations?**

A block order of 1,000,000 bonds is filled in three parts at different prices and allocated to five accounts. I verify the **average price** (weighted), allocation by pro-rata or specified ratios, rounding (the sum of allocations must equal the filled total, and leftovers go by a defined rule), minimum denomination and lot-size constraints for bonds, and that each account's confirmation and settlement amount is correct.

**Q24. How do you test pre-trade risk and limit checks?**

- Credit limit per counterparty, notional limit per desk, concentration limits, and short-sell restrictions.
- Fat-finger checks (price or quantity far outside normal ranges).
- Boundary tests: exactly at the limit, one unit over, and just after a limit reset.
- Concurrent orders racing to consume the last of a limit.
- Limit changes at runtime, kill switch behavior, and override approvals with audit trail.

**Q25. How do you test market data handling?**

Stale quotes, crossed or locked markets, bad ticks (price spikes), missing fields, out-of-order sequence, and feed failover. For bonds I also test evaluated or composite prices, the fallback when no live quote exists, and the cut-off for end-of-day price capture. For performance I replay recorded market data at high rates to test throughput and latency.

**Q26. A trader complains the system accepted a bond order with a price 10 times higher than market. What do you do?**

First, reproduce it and determine whether the check exists (e.g., a price collar against last or reference price), whether it was misconfigured for that instrument, or whether the reference price was missing so the check was skipped. Fix the rule, then add regression tests: no reference price, price exactly on the collar, a stale reference price, and different quote conventions (price vs yield vs 32nds). Finally, I raise a risk note for similar instruments.

---

## E. Post-Trade, Settlement, and Reconciliation

**Q27. Which settlement messages and systems should a senior tester know?**

- **Instruction types:** DVP (delivery versus payment) and FOP (free of payment).
- **SWIFT:** MT541/MT543 (receive/deliver against payment instructions), MT545/MT547 (confirmations), MT548 (status), MT940 (cash statement). ISO 20022 equivalents (`sese.*`) are replacing them in many places.
- **Infrastructure:** DTC, Fedwire Securities, Euroclear, Clearstream, and CCPs such as FICC.

Tests: field mapping (ISIN, quantity, amount, settlement date, SSI, safekeeping account), status-driven workflows (matched, unmatched, pending, settled, failed), and the mapping of rejection reason codes.

**Q28. Daily reconciliation shows breaks. How do you triage?**

I classify the breaks first:

1. **Timing** (trade booked late, settled after cut-off)
2. **Quantity/amount** (partial settlement, rounding, accrued difference)
3. **Reference data** (wrong ISIN mapping, wrong account)
4. **Missing/duplicate** (message lost or replayed)
5. **Valuation** (different price source or FX rate)

Then I trace one break end to end through logs and messages. To prevent recurrence, I automate the reconciliation tests with tolerance rules and age the breaks by category.

**Q29. How do you test regulatory reporting (TRACE, MiFID II, SFTR)?**

- **TRACE (US corporate bonds):** reporting within the deadline (15 minutes for most corporate trades), correct capacity and contra-party fields, and handling of cancels and corrections.
- **MiFID II transaction reporting:** ISIN, LEI, venue MIC, timestamps, buyer/seller identifiers, and flags.
- **SFTR (EU securities financing, includes repo):** trade, collateral, and margin reports with UTI, LEI, and lifecycle events; reconciliation with the repository.

Tests include schema validation, mandatory-field checks, timing SLAs, reject handling and resubmission, and amended/cancelled trade reporting. The US Treasury clearing mandate affects Treasury repo workflows, and its timelines have been phased, so confirm the current dates before quoting them.

**Q30. End-of-day processing failed halfway. How do you test restart and recovery?**

I verify idempotency (rerunning a step does not duplicate postings or P&L), checkpoint restart from the failed step, no partial commits, and correct dependency ordering of jobs (prices → valuation → accruals → P&L → reports). I also test the consequences of delayed prices and the correct handling of a business date rollover when the batch is late.

---

## F. Test Strategy, Data, and Automation

**Q31. How do you create realistic test data for bonds and repos?**

- Use anonymized or masked production-like data for reference data and positions (no client PII).
- Synthetic generators for trades: coupon frequencies, day counts, currencies, call schedules, and odd first/last coupon periods.
- Fixed **market data snapshots** so results are repeatable.
- Dedicated counterparties and accounts per test run for parallel execution.
- A reusable API or DB layer to create data in seconds instead of through the UI.

**Q32. Which calculations must never use floating-point types, and why?**

Money, prices, quantities, accruals, and FX rates should use `BigDecimal` (or the DB `DECIMAL`) with explicit rounding rules. With `double`, binary representation errors accumulate and cause one-cent breaks in settlement amounts, which then fail reconciliation. In tests, I assert with explicit scale and rounding mode and use a tolerance only when the business rule defines one.

**Q33. How do you design an automation strategy for a trading and post-trade platform?**

Following the test pyramid:

- **Unit/component:** pricing, accrual, and day-count functions with large data tables.
- **API/message layer (largest):** REST, FIX, MQ/Kafka payload validation, and DB assertions with RestAssured/TestNG or similar.
- **UI (thin):** critical journeys such as booking, amending, and approving a trade.
- **Contract tests** for interfaces between front office, middle office, back office, and the custodian.
- **Non-functional:** performance (order throughput, market data bursts), resilience (failover), and security (entitlements, four-eyes approvals).

I tag tests by risk and run smoke on every commit and full regression nightly.

**Q34. How do you prioritize testing when the release window is short?**

Risk-based: first the money-moving and regulatory paths (settlement amounts, accruals, reporting), then high-volume flows, then recently changed and defect-prone areas, then the rest. I agree on exit criteria with stakeholders, run a focused smoke plus a changed-area regression, and communicate residual risk explicitly with a go/no-go recommendation.

**Q35. How would you test entitlements and four-eyes (maker-checker) controls?**

Verify a trader cannot approve their own trade, an approver cannot edit, role changes take effect, and unauthorized access to restricted instruments, desks, or client data is blocked. Also test audit-trail completeness (who, what, when, old and new values) and session timeout behavior.

**Q36. How do you test an AI/LLM-based assistant that searches or explains bonds?**

- **Grounding:** answers must match the source data (yield, maturity, rating) with no invented figures.
- **Golden question set** with expected facts, scored automatically, and a human review sample.
- **Adversarial tests:** ambiguous queries, wrong ISIN, out-of-scope questions, and prompt injection.
- **Regression** on each model, prompt, or data update, since outputs are non-deterministic and need tolerance-based checks.
- **Compliance:** no investment advice without disclaimers, no leakage of other clients' data, and logging for audit.

---

## G. Leadership and Production Scenarios

**Q37. After a release, trades are stuck in "Pending Confirmation." How do you handle it?**

1. Assess the impact: count of trades, value, and settlement dates at risk (trades settling today are the priority).
2. Check the logs and messages: was the confirmation message sent, was it acknowledged, and did a status mapping change in the release?
3. Compare with the previous version's behavior to isolate the change.
4. Agree on a fix or workaround with dev and operations (manual confirmation for the urgent trades).
5. Add a regression test and a monitoring alert for trades in the pending state beyond a threshold.
6. Hold a blameless post-mortem and fix the gap in the test coverage.

**Q38. A defect was found in production that your regression missed. What do you do?**

I own it: identify the escape's root cause (missing scenario, wrong test data, environment difference, or a requirement gap), add the test at the right layer, and check for similar gaps nearby. I share the finding with the team so it improves the shared checklist, not just my test.

**Q39. How do you work with business analysts, traders, and operations when requirements are unclear?**

I clarify with examples: concrete trades with expected amounts, which turn ambiguity into testable cases. I ask the BA or trader to sign off on a few worked examples (e.g., the accrued interest calculation) before development finishes, and I involve operations early because they know the real exceptions and manual workarounds.

**Q40. What are the biggest risks in a fixed income platform, and how do you mitigate them?**

1. **Calculation errors** (accrued, yield, repo interest): independent oracle plus boundary data.
2. **Reference data errors:** validation rules plus vendor reconciliation.
3. **Settlement and cut-off timing:** time-based tests, holiday calendars, and time zones.
4. **Integration breaks** between systems: contract and end-to-end tests with message-level checks.
5. **Regulatory misses:** automated validation of report fields and SLAs.
6. **Operational resilience:** failover, restart, and idempotency tests.

---

## Quick Reference: Worked Numbers to Memorize

| Item | Example |
|---|---|
| Dirty price | Clean 98.50 + accrued 1.25 = 99.75 per 100 |
| Accrued, 30/360 | 1M × 6% × 60/360 = 10,000.00 |
| Accrued, Act/Act | 30,000 × 59/181 = 9,779.01 |
| Repo interest, 1 day | 10M × 5.30% × 1/360 = 1,472.22 |
| Repo interest, 7 days | 10M × 5.30% × 7/360 = 10,305.56 |
| DV01 | MV 1M × mod. duration 7 × 0.0001 ≈ $700 |

## Interview Tips

- Open every answer with the **business risk** (money, regulation, or settlement), then the test approach.
- Use **one worked example with numbers**. It shows you can verify a calculation, not just describe it.
- Map answers to a real defect or project from your own broker/dealer platform work (onboarding, Terms/Bonds management, portfolio views) and quantify the impact.
- Say where you would **not** automate (one-off regulatory changes, visual judgment) and explain why.

I can also run a **mock interview** where I ask these one at a time and review your answers, or prepare a **domain glossary** (50 key terms with one-line definitions) for quick revision.



