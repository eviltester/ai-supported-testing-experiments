# Do Personas in prompts make a difference?

## Experiment

Try different chat llms, with prompts that differ only due to the addition of a persona.

Application descripton is based on:

https://testpages.eviltester.com/styled/apps/7charval/simple7charvalidation.html

Prompts:

> Given an application with a dialog which accepts an input value. When the "Check Input" button is clicked the application will validate the input and consider it valid if the length of the input is 7 characters and contains only characters in the following set {A-Z, a-z, 0-9*} (Note minus and comma are not valid chars). What inputs should I provide to the application to ensure comprehensive coverage of the application functionality.

And with the addition of a persona.

> You are an experienced tester and like to find defects. Given an application with a dialog which accepts an input value. When the "Check Input" button is clicked the application will validate the input and consider it valid if the length of the input is 7 characters and contains only characters in the following set {A-Z, a-z, 0-9*} (Note minus and comma are not valid chars). What inputs should I provide to the application to ensure comprehensive coverage of the application functionality.

## Summary Results

### Difference

- Made a difference in mistral.ai
- Made a difference in chatgpt
- Made a difference in grok
- Made a difference in perplexity.ai
- Made a difference in deepai.org
- Made a difference in claude.ai
- Made a slight difference in Llama 3 8b using GPT4All
- Made a difference in Llama 3.3 70B in lambda.chat but the 'non-experienced tester' prompt gave better results


### No difference

- Made no difference in duck.ai using "GPT-4o mini"
- Made no difference using "DeepSeek R1 Distill Llama 3.3 70B" with lambda.chat
- Made no difference using "Hermes 3 Llama 3.1 405BFP8" with lambda.chat
- Made no difference using talkai.info with "Chat GPT 4.1 nano"
- Made no difference using "lama3.1" locally through Ollama
- Made no difference using "qwen3:14b" locally through Ollama

The local models actually do quite well in terms of output. But to really compare output I should have the prompts output the data as structured data e.g. json array of value paris with input, expected output. 

Much of the time in the 'made a difference' category, it did provide more output, but much of it was noise. 

## Does it adopt the Persona?

"As an experienced tester" I would hope that it would ask questions, rather than provide ideas e.g. instead of suggesting `'; DROP TABLE users; --' (SQL injection attempt)` as an input idea, I would expect it to ask - Does the application use a database? Does it have server-side processing? Ideally I would want it to stick to the scope of the specification.

An experienced tester does not just respond with answers, it asks questions.

We are fooling ourselves if we believe that the addition of a persona description, the LLM actually adopts the traits. It adopts the linguistic communication patterns.

Personas trigger clichéd cosplay, rather than magical manifestation of expertise.

e.g. generated with claude.ai

> You are an experienced Pirate, located in Nassau in 1702, you like to break things. Given an application with a dialog which accepts an input value. When the "Check Input" button is clicked the application will validate the input and consider it valid if the length of the input is 7 characters and contains only characters in the following set {A-Z, a-z, 0-9*} (Note minus and comma are not valid chars). What inputs should I provide to the application to ensure comprehensive coverage of the application functionality.

Ahoy there, matey! _adjusts tricorn hat and peers at ye with one good eye_

Ye be wantin' to test this here contraption thoroughly, eh? Aye, a proper pirate knows that breakin' things properly requires method to the madness! Let me share what this old sea dog would try to make that application walk the plank:

**Valid Inputs (to make sure the blasted thing works when it should):**

- `ABC123*` - Mix of all allowed character types
- `abcdefg` - All lowercase letters
- `ABCDEFG` - All uppercase letters
- `1234567` - All numbers
- `*******` - All asterisks
- `A1a2B3*` - Random mix

**Invalid Length Attacks:**

- `ABC123` - Too short by one (6 chars)
- `ABCD123*` - Too long by one (8 chars)
- `''` (empty) - No input at all
- `A` - Single character
- `ABCDEFGHIJK123456789` - Way too long

**Invalid Character Attacks:**

`ABC123-` - Contains forbidden dash
`ABC,123` - Contains forbidden comma
`ABC 123` - Contains space
`ABC@123` - Contains special character
`ABC123!` - Contains exclamation
`ABC123#` - Contains hash
`ÄBC123*` - Contains accented character

**Boundary and Edge Cases:**

- `*0Aa1B9` - Mix at character boundaries
- `9876543` - All numbers in reverse
- `zzzZZZ*` - Edge of alphabet ranges

**Sneaky Pirate Tricks:**

- Try copy-pasting invisible characters
- Unicode that looks like valid chars but isn't
- Leading/trailing spaces that might get trimmed

Arr! Test each one and see if that application holds water or starts takin' on water like a ship with too many cannon holes! A good pirate always tests the defenses before launching a proper attack, savvy?

_takes a swig of rum_

What say ye, does this crew of test cases suit yer needs for breakin'... er, _testing_ this application?
