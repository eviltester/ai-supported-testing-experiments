# Using CoPilot and ChatGPT to generate a CounterString from code

## Experiment Introduction

I wanted to see if it was the version of ChatGPT that was the issue.

To gain access to an improved version of ChatGPT I signed up for a 30 day free trial of copilot so this was conducted in IntelliJ in the Chat interface rather than online.

Mixing copilot and an up to date version of Chat GPT unfortunately makes it hard to assess if the improvements in this session were a result of the ChatGPT upgrade or the addition of copilot as well - what are the differences?

## Results Summary

On 29th May 2025 I tried ChatGPT-4.1 using Co-Pilot in IntelliJ.

This was still a chat session, rather than a code completion session.

I started by asking for code, since this was the failure last time.

Eventually I did manage to get working ChatGPT generated code.

It was frustrating, but it did get there.

``` 

function generateCounterString(length) { 
    let result = ''; 
    let n = length; 
    while (n > 0) { 
        let chunk = n + '*'; 
        if (chunk.length <= n) { 
            result = chunk + result; 
            n -= chunk.length; 
        } else { 
            result = '*' + result; 
            n -= 1; 
        } 
    } 
    return result; 
} 

```


# Transcript

A copy and pasted transcript is below.

# Prompt 1

> Can you create a function to generate a CounterString which is a string of a specified length e.g. generateCounterString(25) to generate a string of length 25 characters where the last character in the string is an * with the number 25 to the left of it. The rest of the string is an * with a number before it, and the number is the * position in the string. 

 

Here is a function that generates a CounterString as described. It builds the string from the end, prepending each "*n" where n is the current length of the string plus the length of the next "*n". 

``` 

public static String generateCounterString(int length) { 
    StringBuilder sb = new StringBuilder(); 
    int current = length; 
    while (current > 0) { 
        String chunk = "*" + current; 
        if (chunk.length() > current) { 
            chunk = "*"; 
        } 
        sb.insert(0, chunk); 
        current -= chunk.length(); 
    } 
    return sb.toString(); 
} 

``` 
  

This function ensures the resulting string is exactly the specified length, with the last number matching the total length. 


# Prompt 2 

> can you generate it in JavaScript 

 

Here is a JavaScript version of the generateCounterString function. It builds the string from the end, prepending each *n or * as needed to ensure the exact length. 

``` 

function generateCounterString(length) { 
    let result = ''; 
    let current = length; 
    while (current > 0) { 
        let chunk = '*' + current; 
        if (chunk.length > current) { 
            chunk = '*'; 
        } 
        result = chunk + result; 
        current -= chunk.length; 
    } 
    return result; 
} 

``` 
  

This function returns a CounterString of the specified length, following the same logic as the Java version. 

 
# Prompt 3
 

> That is incorrect. The string should end with 25* when it is of length 25 

 

Thank you for clarifying. The correct approach is to build the string from left to right, appending n* where n is the next position, and stopping when the total length would exceed the target. The last chunk should ensure the string ends with 25* for length 25. 

Here is the corrected Java implementation: 

``` 

public static String generateCounterString(int length) { 
    StringBuilder sb = new StringBuilder(); 
    int pos = 1; 
    while (true) { 
        String chunk = pos + "*"; 
        if (sb.length() + chunk.length() > length) { 
            int remaining = length - sb.length(); 
            if (remaining > 0) { 
                sb.append("*".repeat(remaining)); 
            } 
            break; 
        } 
        sb.append(chunk); 
        pos = sb.length() + 1; 
    } 
    return sb.toString(); 
} 

``` 
  

This function ensures the string ends with length* and is exactly the specified length. 

 
# Prompt 4
 

> generate it in javascript 

 

Here is a corrected JavaScript implementation. It builds the CounterString from left to right, ensuring the string ends with length* and is exactly the specified length. 

``` 

function generateCounterString(length) { 
    let result = ''; 
    let pos = 1; 
    while (true) { 
        let chunk = pos + '*'; 
        if (result.length + chunk.length > length) { 
            // Fill remaining space with asterisks if needed 
            result += '*'.repeat(length - result.length); 
            break; 
        } 
        result += chunk; 
        pos = result.length + 1; 
    } 
    return result; 
} 

``` 
  

This function returns a CounterString of the specified length, ending with length*. 

# Prompt 5

> that is incorrect, this code generates '*1*3*5*7*9*11*14*17*20*23' which is 25 characters in length but the final 23* should be 25* because the * goes after the number. The number tells us the position of the * which follows it. 

 

Thank you for clarifying. The correct approach is to build the string so that each number indicates the position of the following *, and the string ends with length*. Here is a corrected Java implementation: 

Start from the target length and prepend each chunk (number*) so that the * lands at the correct position. 

If the next chunk would exceed the remaining space, prepend only *. 

 

``` 

public static String generateCounterString(int length) { 
    StringBuilder sb = new StringBuilder(); 
    int n = length; 
    while (n > 0) { 
        String chunk = n + "*"; 
        if (chunk.length() <= n) { 
            sb.insert(0, chunk); 
            n -= chunk.length(); 
        } else { 
            sb.insert(0, "*"); 
            n -= 1; 
        } 
    } 
    return sb.toString(); 
} 
``` 

This ensures the string ends with length* and each * is at the position indicated by the number before it. 

 
# Prompt 6
 

> in javascript? 

Here is the corrected JavaScript implementation. It builds the string from the end, prepending each number* so that the * lands at the position indicated by the number, and the string ends with length*. 

``` 

function generateCounterString(length) { 
    let result = ''; 
    let n = length; 
    while (n > 0) { 
        let chunk = n + '*'; 
        if (chunk.length <= n) { 
            result = chunk + result; 
            n -= chunk.length; 
        } else { 
            result = '*' + result; 
            n -= 1; 
        } 
    } 
    return result; 
} 

``` 
  

This ensures the string ends with length* and each * is at the position indicated by the number before it. 

# Prompt 7

> create a function that will check that a string validates as a counterstring 

 

A CounterString is valid if every asterisk (*) is immediately preceded by a number, and that number indicates the position of the asterisk in the string (1-based). 

Here is a JavaScript function that checks if a string is a valid CounterString: 

``` 

function isValidCounterString(str) { 
    let i = 0; 
    while (i < str.length) { 
        // Find the next asterisk 
        let starIdx = str.indexOf('*', i); 
        if (starIdx === -1) return false; // No more asterisks, invalid 
        // The number before the asterisk 
        let numStr = str.slice(i, starIdx); 
        if (!/^\d+$/.test(numStr)) return false; // Not a valid number 
        let num = parseInt(numStr, 10); 
        if (starIdx + 1 !== num) return false; // Position mismatch 
        i = starIdx + 1; 
    } 
    return i === str.length; 
} 

``` 
  

This function returns true if the string is a valid CounterString, otherwise false. 

# Prompt 8 

> this function does not work when the first character is * can you fix it please 

 

The issue is that the function expects a number before every *, but a valid CounterString can start with * (which means position 1 is a *). 

Here is a fixed version that handles this case: 

``` 

function isValidCounterString(str) { 
    let i = 0; 
    while (i < str.length) { 
        let starIdx = str.indexOf('*', i); 
        if (starIdx === -1) return false; 
        let numStr = str.slice(i, starIdx); 
        let num = numStr === '' ? 1 : parseInt(numStr, 10); 
        if (isNaN(num)) return false; 
        if (starIdx + 1 !== num) return false; 
        i = starIdx + 1; 
    } 
    return i === str.length; 
} 

``` 
  

This version treats a leading * as position 1 and validates accordingly. 

 
# Human Checking

At this point I had enough code to try testing and checking the output.
 

I used the previously generated validateCounterString function and the above validation to generate and check a few CounterString output items

Validation code from previous session.

``` 

function validateCounterString(cs) { 

    const regex = /(\d+)\*/g; 

    let match; 

  

    while ((match = regex.exec(cs)) !== null) { 

        const number = parseInt(match[1], 10); 

        const asteriskPos = match.index + match[0].length; // 1-based 

  

        if (number !== asteriskPos) { 

            console.error(`❌ Error: '${match[0]}' says asterisk is at ${number}, but it's at ${asteriskPos}`); 

            return false; 

        } 

    } 

  

    return true; 

} 

``` 

A funcgtion I wrote to call from the console to check a lot of counterStrings e.g. `checkLotsOfCounterStrings(1,2000)` would check from length 1 to length 2000
 

``` 

function checkLotsOfCounterStrings(start, end) { 

    for(let cslen = start; cslen<=end; cslen++){ 

        const cs = generateCounterString(cslen); 

        console.log(`🔍 ${cslen}-char counterstring:\n`); //, cs); 

        console.assert(cs.length === cslen, `❌ Length should be ${cslen}, got ${cs.length}`); 

        console.assert(validateCounterString(cs), `❌ Validation failed for ${cslen}-char string`); 

        console.assert(isValidCounterString(cs), `❌ 2nd Validation failed for ${cslen}-char string`); 

    } 

} 

``` 

# End Notes 

So we finally got there. 

 

A simple right to left algorithm that creates a counterString with some code to help me check that it generates what I was expecting. 

 

I don’t know if it was the combination of copilot and GPT-4.1 that helped or if it was just GPT-4.1 

Because of the iterations and false starts I find myself distrusting the code, even though I’ve run quite a few counterstrings through it and compared the output with my own generator. 

I do like the simplicity of this code and was able to generate quite large CounterStrings with it. 

I think it probably took me about as long, if not longer, to use AI to generate this as it took me to create my earliest counterString code – which I think looked much like this. 

I found the ‘chat’ process quite a poor experience. 

Ideally I want a proper dev environment, with tests running. So for code generation a full dev environment works better – creating the Unit Test Code, then generating code automatically to fulfil the tests. 

The chat interface experience is not good. 

The code doesn’t work 

The chat system does not run or test the code 

The chat system does not spot its own issues 

One of the interesting things is that I didn’t start my experiment by tryng to generate code. 

I started my experiment by trying to generate counterStrings. 

It was the chat GPT system that started generating code to generate counterstrings. 

This is unfortunate because it doesn’t do this well without guidance. 

 


 

 

 

 

 

 

 

 