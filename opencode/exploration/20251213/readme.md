# Experiment with exploration

I was experimenting with:

https://github.com/proffesor-for-testing/agentic-qe/blob/main/docs/AQE-CLI.md

This uses Claude, but I followed the install instructions and then asked opencode to convert the agent, subagents to open code format.

This took a few iterations but eventually I had something that I could use in OpenCode. Nothing with the full power of the agentic-qe system, but using the agents on their own was educational in the power that this provides to the AI tooling.

I used the skills as a prompt for advanced exploratory testing to perform some testing, then after a few iterations asked the llm to generate a prompt that represented the iterative actions taken.

the output was [exploratory_testing_prompt.md](exploratory_testing_prompt.md)

I added the task 0 **THINK** to the prompt.

Then in a clean folder with none of agentic qe material available I ran the prompt.

Results are in the testing_report.md

I'm interpreting this as suggesting that a custom agent description for exploring web applications or REST APIs could represent the prompt in a more generic form and potentially add early insight into web apps.

I used Playwright instead of ChromeDev Tools MCP because Grok kept complaining that the Chrome MCP was not accurately clicking on the button in the form.

NOTE: I don't think the data coverage was good enough from the generated prompt as there were too many inputs starting with A.

It seems to have created screenshots with the same name - lots of tweaking required.