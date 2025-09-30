# Use Goose and Ollama

I wanted an easy to install and setup mcp server, Goose seemed reasonable. Goose also has support for Ollama and defaults to the qwen2.5 model so it all seems like it is supported.

I wanted to try the Playwright MCP and see if it is useful for the real worl - because the Playwright extension can use logged in sessions making it easier to automate real world scenarios rather than scenarios for functional coverage.

## Install

use the chrome extension to work with the real world

https://github.com/microsoft/playwright-mcp/releases

Had to install this as a side-loaded extension but that worked fine.

- Install goose
- connect to ollama
- launch ollama
- added the playwright extension
- it picked qwen2.5 as the model to use
- install qwen2.5 into ollama `ollama run qwen2.5`

I tried using qwen2.5 and the results were a disaster - nothing worked, literally.

But Ollama now offer free cloud based LLMs so I configured Goose to use Ollama with gpt-oss:120b-cloud

## Working without --extension

### Simple Google Search

Tried a simple google search first "what are the best software testing podcasts listed on google search"

And the browser opened using Playwright and all was well with the world.

#### Results: Top 10 Software‑Testing Podcasts (as of the latest Google search)

| # | Podcast | Focus / Highlights | Where to Listen |
|---|---------|-------------------|-----------------|
| 1 | **TestGuild Automation Podcast** | Test‑automation trends, AI‑assisted testing, tools & frameworks. Hosted by **Joe Colantonio**. | <https://open.spotify.com/show/0gWSftuQabA1WFBD5SA7ya> |
| 2 | **Test & Code** | Python‑centric automation, interview‑style deep dives with industry experts. | <https://www.testandcode.com/> |
| 3 | **The Testing Show** | Functional‑testing best practices, QA processes, guest interviews. | <https://thetestingshow.com/> |
| 4 | **The Evil Tester Show** | Broad software‑testing discussion with a fun, opinionated style. | <https://www.eviltester.com/podcast/> |
| 5 | **Quality Sense Podcast** | Quality‑assurance strategies, metrics, and continuous‑improvement. | <https://qualitysense.io/podcast> |
| 6 | **PerfBytes** | Performance‑testing deep dives – load, stress, scalability, and tooling. | <https://perfbytes.com/podcast> |
| 7 | **TestGuild DevOps Toolchain Podcast** | DevOps tooling for testers, CI/CD pipelines, automation integration. | <https://testguild.com/devops-toolchain-podcast> |
| 8 | **Testing Peers** | Community‑driven conversations, career‑growth tips, and real‑world testing stories. | <https://testingpeers.com/> |
| 9 | **Testing Podcast** (generic) | General software‑testing news, trends, and episode rundowns. | <https://open.spotify.com/show/6UO3XQclSuNnGxB39QdAnL> |
|10| **AB Testing** | Focus on A/B‑testing methodology, experiment design, and analytics. | <https://abtestingpodcast.com/> |

### Quick‑look at why these make the list
- **Relevance:** All appear in Google’s “AI overview” of the top‑ranked results for “best software testing podcasts.”
- **Authority:** Hosted by recognized testers (e.g., Joe Colantonio, Brian Okken) or reputable testing communities (TestGuild, Ministry of Testing).
- **Variety:** Covers automation, functional testing, performance, DevOps, QA strategy, and experimental design so you can pick what matches your interests.
- **Accessibility:** Each podcast is available on major platforms (Spotify, Apple Podcasts, dedicated web feeds) and many provide transcript or show‑notes.

Feel free to click the links to start listening or subscribe on your favorite podcast app!


---

### Web Application Usage

> Use the calculator at https://testpages.eviltester.com/styled/calculator to add together 3 and 8, check that the correct result is shown on screen.

This actually worked.

And in comparison with qwen2.5 I could see logs from the progress.

At this point I discovered the limits with the Ollama free plan as my requests failed with 402 Payment Required, but it was a good start.

### Double check again with qwen2.5

Now that I know that Goose and an LLM can work, I'll try again with qwen2.5

Switched to Ollama with qwen2.5 and repeated the same prompt

what are the best software testing podcasts listed on google search

Goose went into a doom loop of "Manage Extensions action, extension name.

So I stopped the action.

This... failed.

I increased the context length in Ollama to 128K as I assume the context needs to be larger.

> perform an internet search and tell me what are the best software testing podcasts listed on google search

This time it generated code which wouldn't work

> using playwright tell me what are the best software testing podcasts listed on google search

This generated code that looked like it might work, but I didn't see any browser open.

> using playwright tell me what are the best software testing podcasts listed on google search. create a screenshot of the resulting google search page

With qwen2.5 Goose seems to be generating code rather than using the extension

> using goose playwright extension tell me what are the best software testing podcasts listed on google search. create a screenshot of the resulting google search page

Again - with qwen2.5 it wrote code.

Let me try the same initial calculator prompt:

> Use the calculator at https://testpages.eviltester.com/styled/calculator to add together 3 and 8, check that the correct result is shown on screen.


> Use the calculator at https://testpages.eviltester.com/styled/calculator to add together 15 and 19, check that the correct result is shown on screen.

It seemed like it might have been running, but I don't think it was using the extension

### Qwen2.5 playwright --extension

I configured the extension in Goose to have `--extension` parameter to force goose playwright extension to use the installed chrome extension

> Use chrome and the playwright extension to use the calculator at https://testpages.eviltester.com/styled/calculator to add together 15 and 19, check that the correct result is shown on screen.

Since I haven't configured the playwright extension with an environment variable I expect to see the extension in Chrome sas for permissions, if Goose is using the Chrome playwright extension... and it didn't


### Exit Goose and try in a new chat

> Use chrome and the playwright extension to use the calculator at https://testpages.eviltester.com/styled/calculator to add together 15 and 19, check that the correct result is shown on screen.

And that started up playwright - I approved each action, but Goose 'timed out' or something saying there was an issue so I configured the playwright extension in goose with the environment variable to access extension without human approval:

PLAYWRIGHT_MCP_EXTENSION_TOKEN= value found in the extension

It still said "It seems there was an error when trying to navigate to the website using the Playwright extension. This might be due to an issue with the browser context or target being closed. Let's try navigating to the calculator webpage again."


 ### Exit Goose and try in a new chat

 > Use chrome and the playwright extension to use the calculator at https://testpages.eviltester.com/styled/calculator to add together 15 and 19, check that the correct result is shown on screen.

 It opened the page multiple times but kept saying the playwright instance was repeatedly closed.

 I remain unconvinced that this combination of Goose, Ollama, Playwright and Qwen2.5 works.

 But, using a different llm has more promise.


 ### Try Goose with standard scraping

 > get the sitemap.xml from https://eviltester.com and count the number of pages listed

Goose downloaded the file but then tried to use commands that are not installed on Windows

```
cat C:\Users\mr_ri\AppData\Local\Block\goose\cache\computer_controller\web_20250930_102007.txt | grep -oP '<loc>\K.*?(</loc>)' | wc -l
```

This command fails on Windows, Goose did not report any errors.

> get the sitemap.xml from https://eviltester.com and count the number of pages listed - any shell commands should be written for windows

It generated the correct command

```
type C:\Users\mr_ri\AppData\Local\Block\goose\cache\computer_controller\web_20250930_102007.txt | find /c "<url>"
```

Said that it executed it but did not return the result.

Running it in a cmd window did show the correct result.

I'm going to assume that this is qwen2.5 issue for the moment.
