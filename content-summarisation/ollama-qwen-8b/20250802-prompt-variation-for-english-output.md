# Does Qwen3-8b can a prompt change fix generation of Chinese?

In runs yesterday (1st August 2025) it was noted that some output was randomly in chinese when using qwen3-14b.

Try this with Qwen3-8b with a prompt specifying English.

- Try prompt variations to avoid this.
    - Result: 20 generations of a prompt specifying English generated no Chinese output
- Try the Qwen3-14b prompt and see if we can recreate this Chinese output
    - Result: Did recreate situation with Qwen3-8b

## Prompt 1 - ask for english exclusively

```
TASK: The provided text is a transcription of a podcast. As a professional podcast summarizer, create a concise and comprehensive summary of the provided podcast transcription:

1. if no transcript is provided then output a message saying "no transcript provided" and stop processing.
2. Craft a summary that is detailed, thorough, in-depth, and complex, while maintaining clarity and conciseness.
3. Incorporate main ideas and essential information, eliminating extraneous language and focusing on critical aspects.
4. Rely strictly on the provided text, without including external information.
5. After generating a summary, do not ask if i would like to follow up with additional questions.
6. Do not tell me what process you went through to generate the summary, just provide the summary.
7. Create the summary using English language.
8. Format the summary in markdown using paragraphs and bullets for easy understanding.

PODCAST TRANSCRIPT:
```

### Prompt 2 - recreate condition for qwen3-14b Chinese output

```
TASK: Summarize the main topics discussed in the following podcast transcript. Provide a concise summary with key points for the reader. Format the summary using bullets and headings.

PODCAST TRANSCRIPT: 
```

The above prompt generated occasional Chinese output with qwen3-14b, does it do so for qwen3-8b?


### Results with prompt 1 specifying English output

- Run 1 - 10 generated - all in English
- Run 2 - 10 generated - all in English

### Results with prompt 2 not specifying English output

- Run 1 - 10 generated - 1 in Chinese

Reasoning:

Okay, let me try to unpack this. The user provided a lengthy conversation between someone (probably a podcast host) and Jack Cole, a recruiter in the QA and tech space. The conversation covers various topics like career progression for QA professionals, the impact of AI on the field, recruitment strategies, and Jack's upcoming initiatives.

The user's query is in Chinese, asking for a summary of the conversation. The assistant needs to generate a summary in Chinese, highlighting key points like career paths for QA professionals, AI's role in QA, recruitment insights, and Jack's new initiatives. The summary should be concise, structured, and include bullet points for clarity. Also, it should mention the resources like the podcast, website, and social media links.

First, I'll identify the main topics from the conversation. Career progression includes moving to roles like SRE, DevSecOps, product management, or AI QA. AI's role involves understanding models, ethical hacking, and using tools like Hugging Face. Recruitment insights focus on relationship-building, senior appointments, and holistic services. Jack's initiatives include a podcast, community events, career coaching, and CV services.

I need to structure this into sections: career paths, AI impact, recruitment strategies, and new initiatives. Use bullet points for each section. Also, include the resources mentioned at the end. Make sure the summary is in Chinese and flows naturally, avoiding markdown as per the user's request.
