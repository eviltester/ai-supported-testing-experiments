export default class SyllabusExpander {
  /**
   * @param {HTMLElement} container The div that contains the list items.
   */
  constructor(container) {
    this.container = container;
  }

  /** Scan all `<li>` elements and add “ask more.” where needed */
  expand() {
    const liElements = this.container.querySelectorAll('li');
    
    liElements.forEach(li => {
      const text = li.textContent.trim();

      const prefix_context = this.getContextPrefixQuestion();

      if (text.endsWith('?')) {
        const questionToAskAI = prefix_context + text;
        const copyButton = this.createCopyButton("copy", questionToAskAI)
        li.appendChild(copyButton);

        this.addSearchLinks(li, questionToAskAI);
      }
    });
  }

  refresh(){
    // delete all question ol's and copy buttons
    const buttons = document.querySelectorAll(".ai-question-copy-button");
    buttons.forEach(button => {button.remove()})
    const lists = document.querySelectorAll(".chat-questions");
    lists.forEach(list => {list.remove()})

    this.expand();
  }

  addQuestionSearchLinks(parentElement, question){

    const encodedQuestion = encodeURIComponent(question);

    let grokUrl = "https://x.com/i/grok?text=";
    // if(grokConversationId && grokConversationId!=""){
    //     grokUrl = "https://x.com/i/grok?conversation=" + grokConversationId + "&text="
    // }

    const searchUrls = [
        ["openai", "https://chat.openai.com/?q="],
        ["perplexity", "https://www.perplexity.ai/search/new?q="],
        //["claude", "https://claude.ai/new?q="],
        ["grok", grokUrl],
        ["google ai", "https://www.google.com/search?udm=50&aep=11&q="],
        ["bing", "https://www.bing.com/chat?sendquery=1&q="]
    ]

    // search urls with existing conversation ids add conversation=id into the param list e.g.
    // https://x.com/i/grok?conversation=12345&text=
    // openai ? not sure supports this via GET
    // perplexity ? don't think it supports this via GET
    // bing? does not seem to support it

    searchUrls.forEach(search => {
        const searchUrl = `${search[1]}${encodedQuestion}`;
        const link = document.createElement('a');
        
        link.href = searchUrl;
        link.textContent = `[${search[0]}] `;
        link.target = '_blank';
        
        parentElement.appendChild(link);
    })
  }

  addSearchLinks(liElem, question) {

    if(!liElem){return;}

    // Skip if already has a nested list
    if (liElem.querySelector('ul.chat-questions')) return;
        
    const nestedList = document.createElement('ul');
    nestedList.className='chat-questions';
    const nestedLi = document.createElement('li');

    this.addQuestionSearchLinks(nestedLi, question);

    nestedList.appendChild(nestedLi);
    liElem.appendChild(nestedList);

  }

  getContextPrefixQuestion(){
    var contextPrefix = "";
    try {
      const prefixElem = document.getElementById("context-prefix");
      if(prefixElem){
        const val = prefixElem.value.trim();
        return "Given the context that I am " + val + ". "
      }

    } catch (error) {
      
    }
    return "";
  }

  createCopyButton(text,textToCopy){
    const copyButton = document.createElement('button');
    copyButton.className = "ai-question-copy-button";
    copyButton.textContent = text;
    copyButton.style.marginLeft = "2em";

    copyButton.addEventListener('click', async () => {
        try {
            await navigator.clipboard.writeText(textToCopy);
            console.log('Text copied to clipboard');
        } catch (err) {
            console.error('Failed to copy text: ', err);
        }
    });

    return copyButton;
  }
}