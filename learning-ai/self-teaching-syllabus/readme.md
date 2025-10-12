# What if the AI could generate a syllabus?

I quite liked the general 'topic' experiment, so wondered what would happen if I asked AI to generate a full syllabus.

Initially I used a number of prompts in sequence:

> what would be a suggested book outline for a test automation with playwright java book which taught concepts in terms of their real world functional application, order chapters from simplest to most complex

then

> expand outline to 3 levels deep

and

> adapt into a publisher ready table of contents layout with summary under each chapter

then

> convert the following text into a list of questions that I could ask an LLM to self teach me Playwright with java:

I pasted in one of the generated outlines, and that worked so...

> do this for each chapter

And this generated an outline with questions.

I combined this into the playwright-syllabus.md

I used https://markdowntohtml.com/ to convert this into HTML and amended my previous topic_x_experiment.html javascript code to add questions to the syllabus.

This, actually works quite well.

I a can amend the context so that it isn't "learning to automate using playwright with java" but instead reflects level of experience:

- learning java and test automation for the first time and want to learn to automate using playwright with java
- an expert developer and want to understand how to automate using playwright with java

The LLM responses will be different (depending which LLM is used).

The copy button is useful for a Chat interface not listed, or if you want to keep all the answers in a single interface.

