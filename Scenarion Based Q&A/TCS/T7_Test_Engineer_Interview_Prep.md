# Interview Prep Guide
## Test Engineer – Trading Functional System Test (T7), Deutsche Börse Group

---

## 1. Skills Checklist to Prepare

### A. Trading Domain Knowledge (Highest Priority)
- [ ] Order lifecycle: new, modify, cancel, execution, rejection
- [ ] Order types: market, limit, stop, iceberg, fill-or-kill, immediate-or-cancel
- [ ] On-book vs off-book trading (what each means, examples of off-book: block trades, negotiated trades)
- [ ] Cash instruments vs derivatives (equities, bonds vs futures, options)
- [ ] Basics of a matching engine — price-time priority, order book depth
- [ ] Quote management (market maker quotes vs regular orders)
- [ ] T7 architecture basics — trading system components, interfaces (ETI – Enhanced Trading Interface, EOBI – Enhanced Order Book Interface, EMDI – Enhanced Market Data Interface) if you can find public info
- [ ] Awareness of Eurex/Xetra as T7-based markets

### B. Test Methodology
- [ ] STLC (Software Testing Life Cycle) phases
- [ ] Functional testing vs regression vs smoke testing — definitions and when each applies
- [ ] Test case design techniques (equivalence partitioning, boundary value analysis, decision tables)
- [ ] Defect lifecycle and severity/priority classification
- [ ] Agile/Scrum testing — role of QA in sprints, Definition of Done
- [ ] ISTQB terminology (even without certification, know the vocabulary)

### C. Automation & Technical Tools
- [ ] Python scripting — functions, data structures, file I/O, basic OOP
- [ ] Test automation frameworks (pytest or similar) — fixtures, parametrization, assertions
- [ ] Data-driven / keyword-driven testing concepts
- [ ] Linux fundamentals — navigating filesystem, permissions, grep/awk/sed basics, process monitoring
- [ ] Git/GitHub — branching, pull requests, merge conflict resolution
- [ ] Jira — creating/tracking defects, linking test cases to stories
- [ ] CI/CD — how automated tests plug into a pipeline (Jenkins, GitLab CI, etc.), triggering test suites on build

### D. Soft Skills / Ways of Working
- [ ] Self-organization and ownership stories
- [ ] Communicating with distributed/cross-functional teams (specification, build, acceptance teams)
- [ ] Driving process improvement
- [ ] Handling ambiguity and shifting priorities in a fast-changing domain

---

## 2. Technical Mock Interview Questions

### Trading Domain
1. Walk me through the lifecycle of an order from submission to execution.
2. What's the difference between on-book and off-book trading? Can you give an example of each?
3. How would you test that price-time priority is correctly enforced in a matching engine?
4. What's the difference between a limit order and a stop order? How would you design test cases for each?
5. How would you approach testing quote management for a market maker?
6. What edge cases would you test around order cancellation (e.g., cancel arriving after a partial fill)?
7. How would derivatives testing differ from cash instrument testing?

### Test Methodology
8. How do you decide what belongs in a smoke test suite vs a full regression suite?
9. Describe your approach to designing test cases for a new feature with only partial documentation.
10. How do you prioritize test execution when release timelines are tight?
11. Walk me through how you'd triage and report a defect found late in a test cycle, right before a production release window.
12. How do you ensure functional test coverage is "high" — what metrics or methods do you use to measure it?
13. Describe how you'd test a system change that touches both new functionality and existing regression-sensitive areas.

### Automation
14. Write (or describe) a Python function to validate that an order book maintains price-time priority after a series of inserts/cancels.
15. How do you structure a test automation framework so it's maintainable as the test suite grows?
16. How do you handle test data setup/teardown in automated functional tests?
17. Describe a time an automated test gave a false positive/negative. How did you investigate and fix it?
18. How would you integrate your automated regression suite into a CI/CD pipeline so it runs on every build?
19. How do you version-control test scripts and manage changes across a distributed team using GitHub?

### Systems/Environment
20. Explain the difference between test, acceptance, simulation, and production environments and what kind of testing belongs in each.
21. What Linux commands would you use to investigate why a test environment service isn't responding?
22. How do you use Jira to link a defect back to its originating test case and requirement?

---

## 3. Behavioral Mock Interview Questions (STAR format expected)

1. Tell me about a time you had to self-organize your testing work with minimal supervision. What was the outcome?
2. Describe a situation where you identified a gap in test coverage and drove improvement without being asked.
3. Tell me about a time you disagreed with a developer or build team about whether something was a defect. How did you resolve it?
4. Give an example of working with a distributed/international team across time zones. What communication practices worked well?
5. Describe a time a release was at risk due to a late-found defect. What did you do?
6. Tell me about a time you had to learn a new domain or technology quickly to do your job effectively.
7. Describe a process improvement you introduced to a testing team. What was the impact?
8. Tell me about a time you had competing priorities (e.g., new feature testing vs regression maintenance). How did you decide what to focus on?
9. Give an example of when your analytical skills helped you find a root cause that wasn't obvious at first.
10. Why are you interested in trading systems / financial market infrastructure specifically?

---

## 4. Tips for the Interview

- Since domain expertise is called out as "essential" in the JD, be ready to speak fluently about trading concepts even if your background is general QA — connect your existing testing experience explicitly to how it would map onto trading system testing.
- Have 2-3 STAR stories ready that you can adapt to multiple behavioral questions (ownership, cross-team collaboration, process improvement).
- If you have Python/automation project examples, be ready to walk through the code structure, not just describe it at a high level.
- Prepare a few thoughtful questions to ask them, e.g., "What does the smoke/regression suite currently look like in terms of automation coverage?" or "How does the team collaborate across the specification, build, and acceptance teams day to day?"
