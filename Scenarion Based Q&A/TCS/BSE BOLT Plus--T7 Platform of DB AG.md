Great find — and this is actually a really important detail for your interview: **BSE's own trading system runs on T7 technology**, 
so you have a direct, home-turf connection to bring up.

## Indian Exchange Trading Technology — Who Uses What

| Exchange | Platform Name | Underlying Technology | Vendor / Origin |
|---|---|---|---|
| **BSE** (Bombay Stock Exchange) | **BOLT Plus** | **Built on Deutsche Börse's T7 architecture** | Licensed/partnered with Deutsche Börse |
| **NSE** (National Stock Exchange) | **NEAT** (National Exchange for Automated Trading) | In-house developed system | Built by NSE itself (with early-stage vendor CMC Ltd. involvement in the 1990s), continuously enhanced internally |

## BSE — Direct T7 Connection

This is the standout point for you: BSE operates on BOLT Plus, a trading platform built on Deutsche Börse's T7 architecture, matching buy and sell orders automatically without human intervention and strictly enforcing price-time priority.

The history here:
- BSE upgraded its technology platform to BOLT+ in November 2013, based on T7, the global trading architecture of Deutsche Börse AG.
- The trading engine of BSE is based on architecture provided by Deutsche Börse, which also holds roughly a 5% stake in BSE, and the engine is described as among the fastest in India and in the top percentile globally.
- At launch, the platform offered around 200 microsecond order execution speed with capacity to handle roughly 500,000 orders per second, with plans to push speed further and scale capacity to around 5 million orders per second by 2016.
- BOLT Plus back-end runs on the T7 platform on top of open-source infrastructure — Linux Red Hat OS and MySQL as the core database, later upgraded to MySQL Enterprise Edition for better performance as trading volumes grew.

**Why this matters for your interview:** If the interviewer asks "have you worked with anything like T7 before," and you have any exposure to BSE/BOLT Plus testing, that's a legitimate, direct answer — same underlying matching engine architecture family, just a different deployment/licensee. Even without direct BOLT Plus experience, you can credibly say "T7 already powers a major Indian exchange, so the concepts — price-time priority matching, low-latency execution, throttling — map directly to systems I'm familiar with in the Indian market."

## NSE — Independent, Home-Grown System

NSE went a different route:
- NSE's NEAT platform generates and maintains an audit trail for every order via a unique order number, and today almost 100% of equity trading in India happens through this electronic order matching system.
- Order matching works by comparing the best buy order (highest price) against the best sell order (lowest price); unmatched orders sit in the order book sequenced by price-time priority, with partial fills allowed until the order is fully executed.
- NSE continuously scales hardware on-demand to support rising trading volumes while maintaining high uptime and low latency, and conducts periodic testing and capacity enhancements as load increases.
- Architecturally, NSE's system is a 3-tier setup with a "NEAT Adapter" middleware layer sitting between the front-end (NEAT/NEATPlus software) and the exchange's core host trading system.

NSE has kept NEAT as a proprietary, internally-owned and evolved system rather than licensing an external matching engine — it was originally built with early technology partner CMC Ltd. in the 1990s but has since been developed and maintained in-house.

## Quick Interview Soundbite

If this comes up: *"T7 isn't just a European system — it's already live in the Indian market. BSE's BOLT Plus is built directly on Deutsche Börse's T7 architecture, so the core concepts I'd be testing here — price-time priority matching, low-latency order handling, throttle limits — are the same fundamentals underlying part of India's own exchange infrastructure. NSE, by contrast, runs its own independently-built NEAT system, so the two major Indian exchanges actually represent two different technology philosophies — license a proven global platform (BSE/T7) versus build and own it in-house (NSE/NEAT)."*

That's a strong, well-informed answer that shows you've done real homework connecting the JD to the Indian market context — good move for round 3.