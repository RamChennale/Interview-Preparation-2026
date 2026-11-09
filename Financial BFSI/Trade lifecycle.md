Here's the complete trade lifecycle, covering both the buy side and sell side through to final settlement in the client's account.

## Key Participants

- **Client/Investor** – places the order
- **Broker/Dealer** – executes the order on the exchange
- **Exchange** – matches buy/sell orders
- **Clearing Corporation (CCP)** – novates and guarantees the trade - LOCKS the MATCHING trades by CCP(NSE Clearing Ltd (NCL/NSCCL), Indian Clearing Corporation Ltd (ICCL – for BSE)
- **Depository (e.g., NSDL/CDSL, DTCC)** – holds securities in electronic form
- **Custodian** – safekeeps assets for institutional clients
- **Clearing Bank** – handles funds settlement


Buy Side:  Order → Execution → Confirmation → Clearing (CCP) → 
           Pay-in (Funds) + Pay-in (Securities from seller) → 
           DVP Settlement → Securities credited to Buyer's account

Sell Side: Order → Execution → Confirmation → Clearing (CCP) → 
           Pay-in (Securities from client) → 
           DVP Settlement → Funds credited to Seller's account
		   
## Phase 1: Pre-Trade

1. **Client onboarding** – KYC, account opening (trading + demat/securities account), risk profiling
2. **Investment decision** – client/fund manager decides to buy a security
3. **Order instruction** – order sent to broker specifying quantity, price, order type (market/limit)

## Phase 2: Trade Execution (Buy Side)

4. **Order routing** – broker routes the order to the exchange
5. **Order matching** – exchange matches the buy order with a sell order at an agreed price
6. **Trade execution** – a trade is generated with details: security, quantity, price, trade date (T)

## Phase 3: Trade Capture & Confirmation

7. **Trade capture** – broker's back office captures trade details in their system
8. **Trade confirmation** – contract note/confirmation sent to the client
9. **Allocation** (for institutional trades) – block trades allocated across underlying client accounts
10. **Affirmation/Matching** – buyer and seller (or their custodians) confirm trade details match (SSI - Standard Settlement Instructions verified)

## Phase 4: Clearing

11. **Trade novation** – the CCP steps in as legal counterparty to both buyer and seller (buyer's counterparty becomes CCP, not the original seller)
12. **Netting** – CCP nets multiple trades to determine net obligations (securities to receive/deliver, funds to pay/receive)
13. **Margin requirements** – initial margin blocked; mark-to-market margins calculated until settlement

## Phase 5: Settlement (Buy Side)

Settlement typically happens on **T+1 or T+2** (trade date plus 1 or 2 business days), depending on the market.

14. **Pay-in** – 
    - **Funds pay-in**: Buyer's broker/custodian ensures funds are available and debited from the client's bank/settlement account, transferred to the clearing bank
    - **Securities pay-in**: Seller's broker delivers securities to the CCP/depository
15. **DVP (Delivery versus Payment)** – exchange of securities and funds happens simultaneously to eliminate settlement risk
16. **Pay-out** –
    - **Securities pay-out**: Securities are credited to the **buyer's demat/client securities account**
    - **Funds pay-out**: Funds are credited to the seller's account
17. **Depository update** – depository updates ownership records; buyer now legally and beneficially owns the securities
18. **Trade confirmation to client** – client statement/contract note reflects the holding

At this point, the **buy trade is fully settled** — securities sit in the buyer's account.

## Phase 6: Sell Trade (Reverse Process)

When the client later decides to sell:

19. **Sell order placement** – client instructs broker to sell the held security
20. **Order execution** – matched on the exchange with a buyer's order
21. **Trade capture & confirmation** – same as buy side
22. **Clearing** – CCP novates the trade; nets obligations
23. **Settlement (T+1/T+2)**:
    - **Securities pay-in**: Securities debited from seller's (client's) demat account, delivered to CCP
    - **Funds pay-out**: Sale proceeds credited to the client's bank/settlement account
    - Simultaneously, the buyer on the other side receives securities into their account (mirroring Phase 5)

## Phase 7: Post-Settlement

24. **Reconciliation** – broker, custodian, depository, and clearing corporation reconcile positions and cash
25. **Corporate actions handling** – dividends, bonus, rights, splits applied while security is held
26. **Regulatory reporting** – trade reporting to regulators (e.g., SEBI in India, SEC in US)
27. **Statement generation** – periodic account/holding statements sent to client

## Quick Visual Summary

```
Buy Side:  Order → Execution → Confirmation → Clearing (CCP) → 
           Pay-in (Funds) + Pay-in (Securities from seller) → 
           DVP Settlement → Securities credited to Buyer's account

Sell Side: Order → Execution → Confirmation → Clearing (CCP) → 
           Pay-in (Securities from client) → 
           DVP Settlement → Funds credited to Seller's account
```

**Settlement cycle note:** Most equity markets today follow **T+1** (India, US since 2024) or **T+2**; derivatives and some fixed income markets may differ.

Want me to also cover trade lifecycle exceptions (fails, buy-ins, corporate action adjustments) or focus on a specific market (India/US/UK) since settlement mechanics differ slightly by jurisdiction?



Here's the mapping of each participant to real institutions in both markets:

| Role 					| India (Example Institutions) | US (Example Institutions) |
|-----------------------|---|---|
| **Client/Investor** 	| You (retail investor) / Mutual Funds (e.g., HDFC MF) / FPIs / Insurance cos (LIC) 
						| You (retail investor) / Mutual Funds (Vanguard, Fidelity) / Pension Funds / Hedge Funds |
						
| **Broker/Dealer**		| Zerodha, Groww, ICICI Direct, HDFC Securities, Angel One, Upstox 
						| Charles Schwab, Fidelity, Robinhood, Interactive Brokers, Merrill Lynch |
						
| **Exchange** 			| NSE (National Stock Exchange), BSE (Bombay Stock Exchange) 
						| NYSE (New York Stock Exchange), Nasdaq |
						
| **Clearing Corporation (CCP)** | NSE Clearing Ltd (NCL – formerly NSCCL), Indian Clearing Corporation Ltd (ICCL – for BSE) 
						| DTCC's subsidiary → NSCC (National Securities Clearing Corporation) for equities; OCC (Options Clearing Corporation) for options |
						
| **Depository** 		| NSDL (National Securities Depository Ltd), CDSL (Central Depository Services Ltd) 
						| DTC (Depository Trust Company) – a subsidiary of DTCC |
						
| **Custodian**			| HDFC Bank Custody Services, ICICI Bank Custodial Services, Deutsche Bank India, SBI-SG Global Securities 
						| State Street, BNY Mellon, JPMorgan Custody Services, Citibank Custody |
						
| **Clearing Bank** 	| HDFC Bank, ICICI Bank, Axis Bank, Yes Bank (RBI-designated clearing banks for exchanges) 
						| Federal Reserve Bank (for cash settlement) / commercial banks like JPMorgan Chase, Bank of America acting as settlement banks |

## A Few Important Notes

**On DTCC (US):** Unlike India where NSDL/CDSL (depository) and NCL/ICCL (clearing corp) are separate entities, in the US, 
	**DTCC (Depository Trust & Clearing Corporation)** is the parent holding company that owns *both*:
- **NSCC** (clearing/netting function — equivalent to India's NCL/ICCL)

- **DTC** (depository function — equivalent to India's NSDL/CDSL)

So DTCC is often loosely used as an umbrella term covering both roles, even though technically they're distinct subsidiaries.

**On your own example:** If you're using **Zerodha**, then:
- Your **Depository Participant (DP)** is technically **Zerodha (via CDSL)** — Zerodha is a DP agent of CDSL, not a depository itself
- Your demat account is a CDSL account, held via Zerodha as the intermediary

**On Custodians in India:** Retail investors like you typically *don't* interact with custodians — custodians are mainly relevant for institutional investors (mutual funds, FIIs/FPIs) who need a separate safekeeping agent distinct from the broker.

Want me to also add a row for **Regulator** (SEBI vs SEC) and **Banker to the Issue / Settlement Bank nuances**, since those often come up alongside this table in interviews or exam contexts?