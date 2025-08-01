# Improved Transcript Generation

## 2025/07/20

When I add a YouTube video as a source, I think it grabs the auto generated subtitle file.

When viewed in the tool the transcript is shown as a wall of text. This is hard to read.

Can I get a better output from the tool.

### Translate English into English?

A Youtube video in English.

- `translate source into English`

Sometimes:

- this converts the transcript into a formatted text,
- it summarises it,
- the response is `the text is already written in English`,
- it says the source is too long, and prompts to summarize it

### Reformat

`reformat the source to be well formatted with Paragraphs, keep the actual wording of the source.`

Tends to provide a summary, but is a more naturally readable version than the briefing notes.

Could be viewed as a blog post.


`reformat the transcript to be well formatted with Paragraphs, keep the actual wording of the transcript.`

> Certainly, here is the transcript reformatted into well-structured paragraphs, with the original wording preserved and sources cited:

Provides much longer response, with long paragraphs, but text is not the same when diffed.


`reformat the transcript to be well formatted with Paragraphs, keep the actual wording of the transcript.`

> Certainly, here is the transcript reformatted into well-structured paragraphs, with the original wording preserved and sources cited:

Resulted in long output, but less well formatted than before.


### Transcript prompts

#### Off the shelf prompt

The prompt here https://docsbot.ai/prompts/writing/transcript-formatter

```
Transform a raw video transcript into a well-organized, easily readable document.

Ensure the content flows logically and is formatted for clarity. Remove filler words and phrases that do not add value to the content.

# Steps

1. **Read and Understand**: Begin by reading the entire transcript to understand the main topics and flow of the content.
2. **Remove Fillers**: Identify and remove any filler words such as 'um', 'uh', 'like', 'you know', etc.
3. **Segment Content**: Break down the content into clear, logical sections or paragraphs, using headings where appropriate to indicate shifts in topics or themes.
4. **Punctuation and Grammar**: Correct any punctuation and grammatical errors to enhance readability.
5. **Enhance Clarity**: Rephrase sentences for clarity where necessary, ensuring the transcript still reflects the original intent.
6. **Add Speaker Labels**: If there are multiple speakers, label each section with the speaker's name or role.
7. **Review and Edit**: Carefully review the final formatted document for flow and coherence, making any final edits required.

# Output Format

Provide the final formatted transcript as a cleanly organized text document, with clear paragraphs and speaker labels (if applicable).
```

Takes much longer to process. The tool may well be using each step as a pass through the content and using the result as a source?

For a conversation between two people it did create a pretty good facsimile of the converstion, it identified the two speakers and split the transcript into 'who said what'. It did also skip sentences so was not an accurate transcription. But the prompt did suggest that was allowed.

Pretty good output.

#### Amend for more accuracy


```
Transform the source raw video transcript into a well-organized, easily readable document.

Ensure the content is formatted for improved grammar while leaving all words in the text in the order they are in the source.

# Steps

1. **Read and Understand**: Begin by reading the entire transcript to understand the main topics and flow of the content.
2. **Segment Content**: Break down the content into clear, logical sections or paragraphs, using headings where appropriate to indicate shifts in topics or themes.
3. **Punctuation and Grammar**: Correct any punctuation errors to enhance readability.
4. **Add Speaker Labels**: If there are multiple speakers, label each section with the speaker's name or role.

# Output Format

Provide the final formatted transcript as a cleanly organized text document, with clear paragraphs and speaker labels (if applicable).
```

This still missed out some actual content from the transcript, but a lot of the transcript was present and in the correct order. This provided a pretty good output.

Readable and Usable.


### Readable output for single speaker

If there is a single speaker I don't like the speaker labels so do not prompt for those.


#### Amend for more accuracy


```
Transform the source raw video transcript into a well-organized, easily readable document.

Ensure the content is formatted for improved grammar while leaving all words in the text in the order they are in the source.

# Steps

1. **Read and Understand**: Begin by reading the entire transcript to understand the main topics and flow of the content.
2. **Segment Content**: Break down the content into clear, logical sections or paragraphs, using headings where appropriate to indicate shifts in topics or themes.
3. **Punctuation and Grammar**: Correct any punctuation errors to enhance readability.

# Output Format

Provide the final formatted transcript as a cleanly organized text document, with clear paragraphs.
```

Much better. Very readable. Sampling of text suggests its accurate enough for my purposes.

## Video showing this in action

<iframe width="560" height="315" src="https://www.youtube.com/embed/to8oGvlDmbY?si=iATCaD8AMgLLFAFa" title="YouTube video player" frameborder="0" allow="accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share" referrerpolicy="strict-origin-when-cross-origin" allowfullscreen></iframe>

## Other Youtube Tools

### In-built subtitles

- visit the video page
- open up the description
- click to show the transcript
- read through the transcript and click on points that seemed interesting

### Tactiq.io

https://tactiq.io/tools/youtube-transcript

Paste in link to video and shows easy to read transcript

### Kome.ai

https://kome.ai/tools/youtube-transcript-generator

Shows transcript without times. No video shown.

### YouTubeTranscript.com

https://youtubetotranscript.com/transcript

Can be a bit ad heavy, but shows transcript text without the times.


