# 20250808 GPT OSS 20b

- https://cookbook.openai.com/articles/gpt-oss/run-locally-ollama

My machine is not really suitable for running this.

I get 35% on GPU and 65% on CPU.

It loads slowly.

Once loaded it does run, but can feel very slow.

I tried using it from the API for summarisation.

I used my transcript from - https://www.eviltester.com/show/024-strategy-vs-plan/

And... it hallucinated more than I am happy with. So given the speed on my local machine and the hallucinations I wouldn't use it. I'll stick to qwen3:14b

Hallucinations:
- It really latched onto the concept of 'buckets' which I thought was a minor part of the podcast
- moving stuff to the appendix of a strategy document, that are actually important. "Keep the first two buckets front‑loaded in any document; the latter four can live in appendices." Perhaps I was just unclear in the podcast
- Resources & Further Reading  - none of these exist - and why add links if they don't exist

| Resource                                      | Link        |
| --------------------------------------------- | ----------- |
| EvilTester – Strategy & Planning Insights     |             |
| “The Art of War” parallels in test management | [PDF Guide] |
| CI/CD best practices for testing teams        |             |

I could edit the output, but I wanted an automated briefing doc and, while it is nicely written, it took too long to create on my machine.

If you have a powerful enough machine then this is very capable because it supoprts tool use.

Also through the Ollama chat interface you can enable the Web functionality and it can perform web searches and create responses augmented by the results.

I would only use this through the Ollama Chat UI, but it does take a lot of time to load into memory for me.

- Processor	13th Gen Intel(R) Core(TM) i7-13700, 2100 Mhz, 16 Core(s), 24 Logical Processor(s)
- 32GB Memory
- Name	NVIDIA GeForce RTX 4070 Ti

---



# Briefing Document  
## “Strategy, Planning & Execution – A Quick‑Reference Guide”  

---

### 1. Purpose of This Briefing  
This briefing distills the most critical insights from the source material into a concise, action‑oriented format.  
* It is intended for product owners, test leads, and stakeholders who need a rapid understanding of how to craft, communicate, and execute an effective test strategy.  
* The emphasis is on **conceptual clarity** and **practical application** – not on producing another template for the sake of a template.  

---

### 2. Core Concepts – The Three Pillars  

| Pillar | What It Means | Focus |
|--------|---------------|-------|
| **Strategy** | High‑level decision‑making framework that answers **why** we test. | Vision, goals, risk appetite |
| **Approach** | The chosen method to fulfill the strategy. | “How” we test (techniques, tools, cadence) |
| **Plan** | Detailed, step‑by‑step logistics – who, when, what. | “When” and “who” execute the approach |

> *Remember*: a **plan** can contain strategic elements when it’s the only viable bucket early in a project; a **strategy** becomes richer once logistics are clear.

---

### 3. Strategy Hierarchy – 4 Levels  

1. **Strategy** – The north star.  
2. **Approach** – The tactical “how.”  
3. **Plan** – The operational “who, when, what.”  
4. **Execution** – The real‑world implementation that feeds back into the hierarchy.  

> **Key takeaway**: Strategy and tactics only separate at the *execution* stage; the first three levels are essentially a nested decision tree.

---

### 4. Categorising Ideas – 6 Essential Buckets  

| # | Category | Why It Matters |
|---|----------|----------------|
| **1** | **Test Strategy** | High‑level justification & stakeholder alignment. |
| **2** | **Test Plan** | Logistics, timelines, resources, tools, costs. |
| **3** | **Beliefs** | Assumptions about the product, requirements, customers. |
| **4** | **Goals** | Concrete objectives to keep everyone on the same track. |
| **5** | **Models** | Coverage & risk models to validate that goals are met. |
| **6** | **Logistics** | Constraints, time scales, personnel, environment. |

> **Tip**: Keep the first two buckets front‑loaded in any document; the latter four can live in appendices.

---

### 5. Guiding Questions – 6 Must‑Ask Prompts  

| # | Question | Purpose |
|---|----------|---------|
| **1** | **What** needs to be done? | Clarifies scope. |
| **2** | **Who** is involved? | Defines ownership & accountability. |
| **3** | **Why** is it being done? | Links back to strategy. |
| **4** | **Where** does it apply? | Determines environment & context. |
| **5** | **When** will it happen? | Sets timelines & milestones. |
| **6** | **How** will it be done? | Outlines techniques & tools. |

> Using these questions keeps the thinking *structured* and *easily transferable* into the 6‑bucket model.

---

### 6. Military‑inspired Principles – 3 Pillars  

| # | Principle | How It Maps to Testing |
|---|-----------|------------------------|
| **1** | **Moral Law** (alignment of goals & buy‑in) | Stakeholder engagement & shared vision. |
| **2** | **Heaven & Earth** (timing & constraints) | Managing project cadence and environmental limits. |
| **3** | **Commander, Method, Discipline** (team skill, tools, PM) | Execution discipline, tooling stack, and process rigor. |

> *Bottom line*: Adapt the “simpler, objective‑focused” military mindset to testing, allowing quick pivoting when plans fail.

---

### 7. Practical Take‑Aways – Numbers You Can Use  

| # | Actionable Insight | Why It Matters |
|---|--------------------|----------------|
| **1** | **Avoid boilerplate** – keep documents to **2–3 pages** for the core strategy; the rest can be appendices. | Reduces noise, speeds stakeholder review. |
| **2** | **Prioritise clarity over completeness** – aim for a **one‑page summary** plus a **detail appendix**. | Stakeholders skim; detail is available for deep dives. |
| **3** | **Use early feedback loops** – target **2–3 rounds** of stakeholder review before lock‑down. | Catches objections early, reduces surprise at execution. |
| **4** | **Document “why” first** – put the strategic rationale up front, then cascade into approach, plan, execution. | Keeps all parties focused on the high‑level goal. |
| **5** | **Iterative refinement** – treat strategy as a **living document** updated after every sprint or major milestone. | Keeps the strategy relevant and aligned with reality. |

---

### 8. Execution Matters – 3 Rules of Thumb  

1. **Execution fuels the strategy** – only a live implementation provides the feedback needed to refine strategy.  
2. **Tactics without strategy = noise** – every test case or bug report must map back to a strategic objective.  
3. **Adaptive exploratory testing** – use it as a *safety net* that lets you adjust the plan in real time.

---

### 9. Resources & Further Reading  

| Resource                                      | Link        |
| --------------------------------------------- | ----------- |
| EvilTester – Strategy & Planning Insights     |             |
| “The Art of War” parallels in test management | [PDF Guide] |
| CI/CD best practices for testing teams        |             |
|                                               |             |

---

### 10. Closing Summary  

* **Strategy** is the *why*; **approach** is the *how*; **plan** is the *who/when/what*.  
* Use the **6‑bucket model** to structure ideas and the **6 guiding questions** to capture them.  
* Keep documents lean, but embed a **living strategy** that evolves with each sprint.  
* Align all tactical actions back to the strategic objectives – otherwise you’re just moving pieces without a direction.  

By following this framework, you’ll transform strategy documents from paperwork into a living, breathing roadmap that drives clear, stakeholder‑aligned testing decisions.