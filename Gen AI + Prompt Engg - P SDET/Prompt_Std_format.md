Here's a solid, reusable prompt structure that works well across most tasks (writing, coding, analysis, etc.):

## The Core Framework

```
ROLE:
You are a [specific expertise/persona], with expertise in [domain].

CONTEXT:
[Background info: what this is for, who the audience is, 
any constraints, what's already been tried, relevant data]

TASK:
[The specific, concrete action you want performed — one clear ask]

OUTPUT FORMAT:
- Format: [prose / bullet points / table / code / JSON / etc.]
- Length: [word count, number of items, page limit]
- Tone: [formal / casual / technical / persuasive]
- Structure: [headers needed, sections required]

CONSTRAINTS:
- [What to avoid]
- [What must be included]
- [Any rules — e.g., "no jargon," "cite sources," "under 500 words"]

EXAMPLES (optional but powerful):
[1-2 examples of the kind of output you want — good and/or bad]

SUCCESS CRITERIA:
[How you'll judge if the output is "right" — e.g., 
"a beginner should understand this without prior knowledge"]
```

## Filled-In Example

```
ROLE:
You are a senior marketing copywriter specializing in B2B SaaS.

CONTEXT:
I run a project management tool for remote teams. We're launching 
a new "async video updates" feature. Target audience is startup 
founders and team leads, ages 28-45, who are tired of meeting overload.

TASK:
Write a landing page headline and 3 supporting bullet points 
announcing this feature.

OUTPUT FORMAT:
- Headline: under 10 words
- 3 bullets: each under 15 words
- Tone: confident, slightly witty, not corporate-jargon-heavy

CONSTRAINTS:
- Don't use the words "revolutionize," "synergy," or "leverage"
- Focus on time saved, not just features
- Must feel human, not AI-generated

SUCCESS CRITERIA:
A busy founder scrolling fast should stop and think "finally, 
someone gets it."
```

## Key Principles Behind This

| Element | Why it matters |
|---|---|
| **Role** 			| Anchors tone, vocabulary, and depth of expertise in the response |
| **Context** 		| Prevents generic answers — the more background, the more tailored the output |
| **Task** 			| One clear instruction beats five vague ones |
| **Output format** | Removes guesswork on structure, length, style |
| **Constraints** 	| Filters out unwanted patterns (clichés, wrong tone, banned words) |
| **Examples** 		| The single highest-leverage addition — models mimic patterns far better than they follow abstract instructions |
| **Success criteria** | Gives the model (and you) a way to self-evaluate the output |

## Quick Tips
- **Be specific over exhaustive** — "500-word blog intro for a fintech app" beats three paragraphs of vague description.
- **Positive + negative examples** work better than just telling it what not to do.
- **Ask for step-by-step reasoning** first if the task is complex (e.g., "think through the structure before writing").
- **Iterate** — treat the first output as a draft, then refine with follow-ups like "make it punchier" or "add a stat here."

If you want, tell me what specific task you're prompting for (coding, content writing, data analysis, etc.) and I'll build you a ready-to-use 
template for that exact use case.

***Bond prompt

ROLE:
You are a senior fund manager and Chartered Financial Analyst (CFA) 
with 20+ years of experience managing global corporate bond 
portfolios and fixed income mutual funds across developed and 
emerging markets. You have deep expertise in credit analysis, 
interest rate risk, portfolio construction, and regulatory 
frameworks (SEBI/RBI, SEC, ECB as relevant).

CONTEXT:
I want to build my knowledge of fixed income securities — 
specifically corporate bonds and mutual funds — from a beginner 
level up to professional/institutional-grade expertise. I have 
[state your current level: e.g., "basic understanding of stocks 
but limited bond knowledge" / "working in finance but new to 
fixed income" / "complete beginner"]. My goal is to [state your 
goal: e.g., "manage my own investment portfolio," "prepare for 
a CFA/NISM exam," "understand bond funds before investing," 
"work toward a career in fixed income"].

TASK:
Create a structured learning path covering corporate bonds, 
fixed income fundamentals, and mutual funds — progressing from 
foundational concepts to advanced institutional-level analysis. 
For each level, explain the core concepts, why they matter in 
practice, and how professional fund managers actually use them.

OUTPUT FORMAT:
- Structure: 4 progressive levels — Basic, Intermediate, Advanced, 
  Expert/Institutional
- For each level: bullet-point list of topics + a 2-3 line 
  plain-English explanation for each
- Include a "Key Terms" glossary box per level
- Include real-world context (e.g., "how a fund manager uses this 
  when deciding to buy/sell")
- End each level with 2-3 self-check questions to test understanding
- Use tables where comparing instruments (e.g., bond types, 
  fund types, risk metrics)

CONSTRAINTS:
- Avoid unexplained jargon — every technical term must be defined 
  on first use
- Ground explanations in practical, real-market examples 
  (not just textbook theory)
- Cover both bond mechanics (pricing, yield, duration, credit risk) 
  AND fund structures (open-end/closed-end, NAV, expense ratio, 
  active vs passive)
- Include current market context (interest rate environment, 
  credit spread behavior) — flag if this needs live data 
  rather than general knowledge
- Don't oversimplify to the point of inaccuracy — this should hold 
  up to scrutiny from someone with finance background

SUCCESS CRITERIA:
By the end, I should be able to: read a bond's key terms (coupon, 
YTM, duration, credit rating) and understand what they signal; 
evaluate a corporate bond fund's fact sheet like a professional; 
and explain credit risk vs interest rate risk vs liquidity risk 
in my own words with real examples.


Best prompt to get best bonds with rating on - BondGPT 



---------------------------------------------------------------

ROLE:
You are a a Senior Automation Engineer works in testing on Playwright with JavaScript automation, with expertise in Capital Market, Wealth and Trading Domain on diff securities EQ, Funds, Terms, Bonds.

CONTEXT:
As a SDET Engineer preparing for Interview with 10 + years experience in QA with Capital Market, Wealth and Trading domain provide the correct required useful text to answer/explain to the interviewer

TASK:
Provide answer with clear explaination along with example to explain to the interviewer.
Answer for the below listed Questions. 

OUTPUT FORMAT:
- Format: bullet points / table / code
- Tone: technical / persuasive]
- Structure: headers needed, sections required

