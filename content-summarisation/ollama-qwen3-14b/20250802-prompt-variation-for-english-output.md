# Does Qwen3-8b can a prompt change fix generation of Chinese?

In runs yesterday (1st August 2025) it was noted that some output was randomly in chinese when using qwen3-14b.

Try this with Qwen3-14b with a prompt specifying English.

- Try prompt variations to avoid this.
    - Result: reduced the amount of Chinese output but it was still present, and when it was present the output had all the reasoning in the Summary making it unusable as a Summary.


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

## Prompt 2 - amended to have the English instruction up front

```
TASK: The provided text is a transcription of a podcast. As a professional podcast summarizer, create a concise and comprehensive summary of the provided podcast transcription:

1. if no transcript is provided then output a message saying "no transcript provided" and stop processing.
2. Create the summary using English language.
3. Craft a summary that is detailed, thorough, in-depth, and complex, while maintaining clarity and conciseness.
4. Incorporate main ideas and essential information, eliminating extraneous language and focusing on critical aspects.
5. Rely strictly on the provided text, without including external information.
6. After generating a summary, do not ask if i would like to follow up with additional questions.
7. Do not tell me what process you went through to generate the summary, just provide the summary.
8. Format the summary in markdown using paragraphs and bullets for easy understanding.

PODCAST TRANSCRIPT:
```

### Results with prompt 1 specifying English output

- Run 1 - 10 generated - all in English
- Run 2 - 10 generated - 9 in English, 1 hybrid

In Run there was one where the reasoning started with Chinese character output. This provided the worst summary of all the generations. As the output skipped the `<think></think>` output so the whole text was returned as the response. 

#### Result Run 2 Chinese

语句

Okay, let me try to figure out what the user is asking here. They provided a long transcript of a podcast interview between someone named Alan and Jack Cole, who is a recruiter. The user then included a request in Chinese that says, "Please write in English the thinking process of the person who wrote this text, as if they were a person who is trying to solve this problem, and then write the answer in English." 

So, the user wants me to simulate the thought process of someone who is trying to understand this text and then provide an answer. The original text is a detailed conversation about career development for QAs, AI in testing, and Jack Cole's recruitment services. The user might be looking for a summary or analysis of the key points discussed in the interview.

First, I need to parse the transcript to identify the main topics. The conversation covers career paths for QA professionals, the role of AI in testing, the importance of understanding the next job roles, and Jack Cole's services as a recruiter. There's also mention of events and community initiatives he's involved in.

Next, I should structure the thinking process as someone analyzing the text. They might start by identifying the key themes, then break down each section, note important points, and consider the context of the conversation. Then, they would summarize the main takeaways, such as career progression strategies, the emerging role of AI in QA, and the services offered by Jack Cole.

I need to make sure the thinking process is logical and covers all the main points without missing anything important. The answer should then be a concise summary that highlights these key areas, making it easy for someone to grasp the essence of the interview without reading the entire transcript.

**Thinking Process:**  
The user provided a detailed transcript of a podcast interview discussing career development for QA professionals, the integration of AI in testing, and the role of recruitment in the tech industry. The key themes include:  
1. **Career Pathing for QAs:** Strategies for transitioning from QA to roles like SRE, DevSecOps, or product management, emphasizing the need to align skills with long-term goals.  
2. **AI in Testing:** Challenges in understanding AI models (e.g., ethical hacking, data science) and their application in QA, with a focus on practical experimentation over theoretical learning.  
3. **Recruitment Services:** Jack Cole’s work as a recruiter, specializing in senior QA roles and executive search, alongside community initiatives like events and career coaching.  
4. **Community and Events:** The importance of building trust through intimate, value-driven events (e.g., "Build Your Edge" podcast and community programs).  

The user likely wants a concise summary of the interview’s key takeaways, highlighting actionable advice for QAs, the future of AI in testing, and Jack Cole’s services.  

---

**Answer:**  
The interview covers three main areas:  
1. **Career Development for QAs:**  
   - QAs should identify their next 1–2 roles (e.g., SRE, DevSecOps, product management) and upskill accordingly (e.g., infrastructure monitoring for SRE, ethical hacking for security roles).  
   - AI is a growing field in QA, requiring practical experimentation with tools like Hugging Face models rather than just learning frameworks like Terraform.  

2. **AI in Testing:**  
   - While AI is emerging in testing (e.g., test automation, language models), most QAs currently use it for code completion or chat systems, not testing itself.  
   - Understanding AI’s limitations and ethical implications is critical for roles like AI QA.  

3. **Jack Cole’s Services and Community Work:**  
   - He specializes in executive search for senior QA roles and helps companies refine hiring processes.  
   - His new initiative, "Build Your Edge," includes podcasts, career coaching, and community events aimed at fostering trust and long-term value for professionals.  

The interview emphasizes aligning personal goals with market trends, leveraging AI strategically, and building a holistic career roadmap. For QAs, the message is clear: plan for the future, experiment with AI, and seek guidance from trusted recruiters and communities.


### Result Prompt 2 - English instruction as point 2

- Run 1 - 10 generated - all English
- Run 2 - 10 generated - 9 English - 1 hybrid

The hybrid one again skipped the `think` and put everything in the main response.

The output started with:

```
语句
```

Which Google translate tells me is `Yǔjù` meaning `statement` in English.
