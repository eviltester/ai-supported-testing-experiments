# Experiment - automate 7CharVal from console

2025/August/06

Try and automate the following app from the console.


[testpages.eviltester.com/styled/apps/7charval/simple7charvalidation.html](https://testpages.eviltester.com/styled/apps/7charval/simple7charvalidation.html)


## Summary

**Yes it can.**

At one point, the generated code had extra characters at the top.

Instead of creating the markdown code prefix

```
```js
```

It actually created:

```
``js
```

Making the code not possible to copy and paste directly.

After it did this, the next code generation had a different, but equally invalid value set.

```
``
``js
```


There was a fair bit of back and forth. I think I might have been faster if I'd copied the working code out into an editor and copied in the 'new' parts, rather than trying to generate a full working solution from a single prompt. But, if I didn't know how to do this exercise myself then I might not be able to combine and amend the code.

I treated this experiment as 'what if I do not know how to write code to automate the browser from the console'.

The scope of data and approach did trigger one of the bugs in the application. The AI chat was not able to analyze the root of the problem.

But as a tool to help me... very effective.


Lessons:

- keep amending the prompt because it sometimes gets lost in its context.
- it is less good a analyzing defects.

Code is perfectly acceptable and would be a good starting point for learing to automate within the console.

## Start with AI Assistance

If I open the Chrome Browser on the test pages site and open the browser developer tools, I can now use "AI assistance"


Selecting the `characters` input field in the elements I then asked the AI Assistance for code to automate it.

```
Create code to run in the Javascript console to enter the string "Aabbccd" into the characters field. Then press the "Check Input" button. Then check that the validation_message field shows the value "Valid".
```

First it anallyzed the prompt:

```
* Its selector is `input[type="text"]`
* It doesn't have any child element nodes
* It doesn't have any child text nodes
* It has a next sibling and it is an element node
* It has a previous sibling and it is a non element node
* Its parent's selector is `p`
* Its parent is an element node
* Its parent has 2 child element nodes: `input[type="text"]`, `input[type="text"]`
* Its parent has only 1 child text node
```

Then it thought about `Simulating user input and checking validation message`

```
To answer this query, I need to identify the elements involved: the text input field, the "Check Input" button, and the validation message element. I will need to simulate user input into the text field, simulate a click on the button, and then check the value of the validation message field. I will need to use JavaScript to interact with these elements.
```

Then it generated code:

```
const data = {};
const characterInput = document.querySelector('input[type="text"]');
if (characterInput) {
  characterInput.value = 'Aabbccd';
  data.characterInputExists = true;
} else {
  data.characterInputExists = false;
}

const checkButton = Array.from(document.querySelectorAll('button')).find(button => button.textContent === 'Check Input');
if (checkButton) {
  checkButton.click();
  data.checkButtonExists = true;
} else {
  data.checkButtonExists = false;
}

const validationMessage = document.getElementById('validation_message');
if (validationMessage) {
  data.validationMessageValue = validationMessage.textContent;
  data.validationMessageExists = true;
} else {
  data.validationMessageExists = false;
}
```

But couldn't find the button.

```
I was able to find the text input field and enter the string "Aabbccd". However, I could not find a button with the text "Check Input" or an element with the ID "validation_message". Please ensure that these elements exist on the page and that the button text is exactly "Check Input" and the validation message element has the ID "validation_message".

If you're working with a Shadow DOM, the elements might be inside the shadow root and not directly accessible from the main document. You would need to access the shadow root first to find the elements.
```

### Investigate Button Issue

Looking in the DOM I can see that the button is an `input` of type `button` rather than a `button` HTML Element.

I prompted:

```
The button is an input element with type "button" and the name of the element is "validate", rewrite the code to fix this.
```

This allowed the AI to succeed and it exposed an issue with the initial prompt. I made a mistake, the message shown when the input is valid is "Valid Value".

I reprompted the chat interface accordingly.

```
The validation message input element has the name "validation_message" not the id. Also the correct text in the input sould be "Valid Value"
```

Message:

```
I was able to successfully enter the string "Aabbccd" into the character input field, click the button with type="button" and name="validate", and then locate the input element with name="validation_message". The value of this element is indeed "Valid Value".

Therefore, the code to run in the JavaScript console to enter the string "Aabbccd" into the characters field, press the "Check Input" button, and check that the validation_message field shows the value "Valid Value" is:
```

At this point the AI amended the code and re-ran it.

```
const characterInput = document.querySelector('input[type="text"]');
if (characterInput) {
  characterInput.value = 'Aabbccd';
}

const checkButton = document.querySelector('input[type="button"][name="validate"]');
if (checkButton) {
  checkButton.click();
}

const validationMessage = document.querySelector('input[name="validation_message"]');
if (validationMessage) {
  if (validationMessage.value === 'Valid Value') {
    console.log('Validation message is "Valid Value".');
  } else {
    console.log('Validation message is NOT "Valid Value". It is: ' + validationMessage.value);
  }
} else {
  console.log('Validation message element not found.');
}
```

This is a very good start. I can execute this code from the console.

### Multiple Strings

```
Amend the code so that it checks multiple input strings. Following the same process of: inputing the string, clicking the "Check Input" button, then checking that the validation_message is "Valid Value". Use the following input strings: "AZaz09*", "BBBBBBB", "1234567"
```

And it generated the following code:

```
const testStrings = ["AZaz09*", "BBBBBBB", "1234567"];
const characterInput = document.querySelector('input[type="text"]');
const checkButton = document.querySelector('input[type="button"][name="validate"]');
const validationMessage = document.querySelector('input[name="validation_message"]');

if (!characterInput) {
    console.error("Character input not found.");
} else if (!checkButton) {
    console.error("Check button not found.");
} else if (!validationMessage) {
    console.error("Validation message element not found.");
} else {
    for (const str of testStrings) {
        characterInput.value = str;
        checkButton.click();
        // In some cases, you might need a small delay here for asynchronous updates:
        // await new Promise(resolve => setTimeout(resolve, 50));
        const isValid = validationMessage.value === "Valid Value";
        console.log(`Input string: "${str}"`);
        console.log(`Validation message: "${validationMessage.value}"`);
        console.log(`Is Valid: ${isValid}`);
        console.log('---');
    }
}
```

Which does work.

### Could we perform comprehensive data testing?

```
Create code to generate the list of testStrings. The test strings should cover every combination of 7 character strings where characters can be A-Z, a-z, 0-9 and *
```

And.. the AI has pointed out that that is probably too many strings.

```
Generating every combination of 7-character strings using the characters A-Z, a-z, 0-9, and '*' would result in a massive number of strings.

The character set size is 26 (A-Z) + 26 (a-z) + 10 (0-9) + 1 (*) = 63 characters.

For a 7-character string, the total number of combinations is 63 raised to the power of 7, which is approximately 4.04 x 10<sup>12</sup> (over 4 trillion) strings.

Generating and processing such a large list of strings is computationally infeasible within a browser environment and would likely cause it to crash due to memory exhaustion or excessive processing time.

Instead of generating all combinations, consider these alternative approaches for testing:

- Random Sampling: Generate a large, but manageable, number of random strings using the allowed characters. This provides a good chance of covering a wide variety of inputs without generating every single one.
- Boundary Value Analysis: Focus on testing strings that are at the edges of what might be considered valid or invalid. This includes strings with only one type of character, strings with mixes of different types, strings at the minimum/maximum length (if applicable), and strings with special characters like '*'.
- Equivalence Partitioning: Divide the input possibilities into groups (partitions) that are expected to behave similarly. Then, test one or a few strings from each partition.
- Fuzz Testing: Generate a large number of semi-random strings with the allowed characters and observe how the system handles unexpected or unusual inputs.

If you can provide specific criteria or patterns you are trying to test, I can help you generate a more targeted set of test strings based on one of these methods.
```

OK Then...

```
Create code to generate the list of testStrings. The test strings should be 7 characters long and each of the valid characters should be used at least once. The valid characters are A-Z, a-z, 0-9 and *
```

And we got:

```
The user wants to generate a list of 7-character test strings where each string contains at least one of every valid character type (A-Z, a-z, 0-9, *). Since the valid character set size is 63 and the string length is 7, it's impossible for each of the 63 valid characters to be present in a 7-character string. It seems the user might mean that the types of characters (uppercase letters, lowercase letters, digits, and the asterisk) should each be represented at least once in a 7-character string. I will proceed with this interpretation. I will generate a few example strings that satisfy this condition. Generating all such combinations is still a complex combinatorial problem, but providing a small, representative set is feasible. I will create a function to generate a random character of a given type and then construct strings by ensuring at least one of each type is present and filling the remaining characters randomly.
```

With code:

```
const data = {};

const uppercase = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
const lowercase = 'abcdefghijklmnopqrstuvwxyz';
const digits = '0123456789';
const special = '*';
const allChars = uppercase + lowercase + digits + special;

function getRandomChar(charSet) {
    return charSet[Math.floor(Math.random() * charSet.length)];
}

function generateTestString() {
    let testString = '';
    const requiredTypes = [uppercase, lowercase, digits, special];

    // Ensure at least one of each required type
    const chars = requiredTypes.map(type => getRandomChar(type));

    // Fill the rest of the string with random characters
    while (chars.length < 7) {
        chars.push(getRandomChar(allChars));
    }

    // Shuffle the characters to make the strings more random
    for (let i = chars.length - 1; i > 0; i--) {
        const j = Math.floor(Math.random() * (i + 1));
        [chars[i], chars[j]] = [chars[j], chars[i]];
    }

    testString = chars.join('');
    return testString;
}

// Generate a few example strings
data.testStrings = [];
const numberOfStringsToGenerate = 10; // Generate 10 example strings

for(let i = 0; i < numberOfStringsToGenerate; i++) {
    data.testStrings.push(generateTestString());
}
```

Which is pretty good but not quite what I was looking for.

I'll try clarifying the prompt

```
Create code to generate the list of testStrings. The test strings should be 7 characters long. Generate enough test strings such that each of the valid characters should be used at least once in a generated test string. The valid characters are A-Z, a-z, 0-9 and *
```

Message:

```
The user wants a list of 7-character test strings using characters from A-Z, a-z, 0-9, and *. The constraint is that every valid character must appear at least once across all generated test strings. This is a much more manageable task than generating all combinations. I can create a set of all valid characters and ensure each character appears in at least one string in the generated list. Since the strings must be 7 characters long, I'll need multiple strings to cover all 63 characters. A simple approach is to construct strings that include characters from the set until all characters have been used at least once.
```

Code:

```
const data = {};
const validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*".split('');
const testStrings = [];
let charsUsed = new Set();
let currentString = '';
const stringLength = 7;

while (charsUsed.size < validChars.length) {
    currentString = '';
    for (let i = 0; i < stringLength; i++) {
        const charIndex = Math.floor(Math.random() * validChars.length);
        const char = validChars[charIndex];
        currentString += char;
        charsUsed.add(char);
    }
    testStrings.push(currentString);
}

data.testStrings = testStrings;
```

### Manually combine code

I could combine this code with the earlier code:

```
const data = {};
const validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*".split('');
const testStrings = [];
let charsUsed = new Set();
let currentString = '';
const stringLength = 7;

while (charsUsed.size < validChars.length) {
    currentString = '';
    for (let i = 0; i < stringLength; i++) {
        const charIndex = Math.floor(Math.random() * validChars.length);
        const char = validChars[charIndex];
        currentString += char;
        charsUsed.add(char);
    }
    testStrings.push(currentString);
}


const characterInput = document.querySelector('input[type="text"]');
const checkButton = document.querySelector('input[type="button"][name="validate"]');
const validationMessage = document.querySelector('input[name="validation_message"]');

if (!characterInput) {
    console.error("Character input not found.");
} else if (!checkButton) {
    console.error("Check button not found.");
} else if (!validationMessage) {
    console.error("Validation message element not found.");
} else {
    for (const str of testStrings) {
        characterInput.value = str;
        checkButton.click();
        // In some cases, you might need a small delay here for asynchronous updates:
        // await new Promise(resolve => setTimeout(resolve, 50));
        const isValid = validationMessage.value === "Valid Value";
        console.log(`Input string: "${str}"`);
        console.log(`Validation message: "${validationMessage.value}"`);
        console.log(`Is Valid: ${isValid}`);
        console.log('---');
    }
}
```

I have to manually check the console output to see any errors.

Let's see if we can get the AI to do that.

### AI Combine code and track errors

```
to the data generation code, add the code which iterates through the testStrings, inputs them into the form, clicks the Check Input button and checks validation_message for "Valid Value"
```

Oops.

```
As discussed, generating all possible combinations of 7-character strings from the specified character set is not feasible. However, I have generated a limited set of 100 random strings using the allowed characters (A-Z, a-z, 0-9, *).

The code then iterates through these generated strings, inputs each one into the character field, clicks the "Check Input" button, and checks if the validation_message input field has the value "Valid Value".

Here is the code you can run in the JavaScript console:
```

Code:

```
``js
const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*";
const stringLength = 7;
const numberOfStringsToGenerate = 100; // Adjust this number to generate more or fewer strings
const testStrings = [];

for (let i = 0; i < numberOfStringsToGenerate; i++) {
  let randomString = '';
  for (let j = 0; j < stringLength; j++) {
    const randomIndex = Math.floor(Math.random() * characters.length);
    randomString += characters[randomIndex];
  }
  testStrings.push(randomString);
}

const characterInput = document.querySelector('input[type="text"]');
const checkButton = document.querySelector('input[type="button"][name="validate"]');
const validationMessage = document.querySelector('input[name="validation_message"]');

if (!characterInput) {
    console.error("Character input not found.");
} else if (!checkButton) {
    console.error("Check button not found.");
} else if (!validationMessage) {
    console.error("Validation message element not found.");
} else {
    console.log(`Testing ${testStrings.length} randomly generated strings...`);
    for (const str of testStrings) {
        characterInput.value = str;
        checkButton.click();
        // In some cases, you might need a small delay here for asynchronous updates:
        // await new Promise(resolve => setTimeout(resolve, 50));
        const isValid = validationMessage.value === "Valid Value";
        console.log(`Input: "${str}", Validation: "${validationMessage.value}", Is Valid: ${isValid}`);
    }
    console.log('Testing complete.');
}
```

Let's assume that we need to specify everything in the same prompt.

I'll combine the prompts that worked:

```
Create code to generate the list of testStrings. The test strings should be 7 characters long. Generate enough test strings such that each of the valid characters should be used at least once in a generated test string. The valid characters are A-Z, a-z, 0-9 and *. Then create code to run in the Javascript console to enter each of the generated test strings into the input field with name "characters". Then press the input element of type button with value "Check Input" button. Then check that the validation_message field shows the value "Valid Value".
```

And code:

```
const data = {};
const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*";
const stringLength = 7;
data.testStrings = [];
const usedCharacters = new Set();

// Generate initial strings ensuring each character is used at least once
for (const char of characters) {
    let baseString = '';
    for (let i = 0; i < stringLength - 1; i++) {
        // Fill with random characters, leaving one spot for the required character
        const randomIndex = Math.floor(Math.random() * characters.length);
        baseString += characters[randomIndex];
    }
    // Insert the required character at a random position
    const insertIndex = Math.floor(Math.random() * stringLength);
    const testString = baseString.slice(0, insertIndex) + char + baseString.slice(insertIndex);
    data.testStrings.push(testString);
    usedCharacters.add(char);
}

// Generate additional random strings if needed, until we have a reasonable number or cover more cases
const additionalStringsToGenerate = 50; // Generate some more random strings
for (let i = 0; i < additionalStringsToGenerate; i++) {
    let randomString = '';
    for (let j = 0; j < stringLength; j++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        randomString += characters[randomIndex];
    }
    data.testStrings.push(randomString);
}

// Shuffle the test strings to avoid potential ordering issues
data.testStrings.sort(() => Math.random() - 0.5);


data.results = [];

const characterInput = document.querySelector('input[type="text"]');
const checkButton = document.querySelector('input[type="button"][name="validate"]');
const validationMessage = document.querySelector('input[name="validation_message"]');

if (!characterInput) {
    data.error = "Character input not found.";
} else if (!checkButton) {
    data.error = "Check button not found.";
} else if (!validationMessage) {
    data.error = "Validation message element not found.";
} else {
    data.elementsFound = true;
    for (const str of data.testStrings) {
        characterInput.value = str;
        checkButton.click();
        // Allow a small delay for potential async operations, though not strictly necessary for synchronous checks
        // await new Promise(resolve => setTimeout(resolve, 50));
        data.results.push({
            inputString: str,
            validationMessageValue: validationMessage.value,
            isValid: validationMessage.value === "Valid Value"
        });
    }
}
```

And there was no output?

Looking at the code I can see that all the results are in the `data.results` array so I can just show that in the console by typing `data.results`

And that worked. I can see a few "Invalid Value"s in there.

Let's have the AI generate code so I don't have to manually check.

```
Create code to generate the list of testStrings. The test strings should be 7 characters long. Generate enough test strings such that each of the valid characters should be used at least once in a generated test string. The valid characters are A-Z, a-z, 0-9 and *. Then create code to run in the Javascript console to enter each of the generated test strings into the input field with name "characters". Then press the input element of type button with value "Check Input" button. Then check that the validation_message field shows the value "Valid Value". Add any of the input values that fail to generate validation_message "Valid Values" into an array called invalidInputs and throw an exception.
```

It has generated code

```
const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*";
const stringLength = 7;
const requiredCharacters = new Set(characters);
const testStrings = [];
const generatedCharacters = new Set();
let attempts = 0;
const maxAttempts = 10000; // Limit attempts to avoid infinite loops in case coverage is hard

// Generate strings until all characters are covered or max attempts reached
while (generatedCharacters.size < requiredCharacters.size && attempts < maxAttempts) {
    let randomString = '';
    let stringContainsNewCharacter = false;
    for (let j = 0; j < stringLength; j++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        const char = characters[randomIndex];
        randomString += char;
        if (!generatedCharacters.has(char)) {
            generatedCharacters.add(char);
            stringContainsNewCharacter = true;
        }
    }
    // Only add string if it contains at least one character not seen before, or if we have very few strings
     if (stringContainsNewCharacter || testStrings.length < requiredCharacters.size / stringLength + 5) { // Add some initial strings
        testStrings.push(randomString);
    }
    attempts++;
}

console.log(`Generated ${testStrings.length} test strings covering all required characters (attempted ${attempts} times).`);

const invalidInputs = [];
const characterInput = document.querySelector('input[name="characters"]');
const checkButton = document.querySelector('input[type="button"][value="Check Input"]');
const validationMessage = document.querySelector('input[name="validation_message"]');

if (!characterInput) {
    console.error("Character input with name 'characters' not found.");
} else if (!checkButton) {
    console.error("Check Input button not found.");
} else if (!validationMessage) {
    console.error("Validation message element not found.");
} else {
    console.log("Starting validation tests...");
    for (const str of testStrings) {
        characterInput.value = str;
        checkButton.click();
        // In some cases, you might need a small delay here for asynchronous updates:
        // await new Promise(resolve => setTimeout(resolve, 50));
        if (validationMessage.value !== "Valid Value") {
            invalidInputs.push(str);
            console.warn(`Input "${str}" failed validation. Received: "${validationMessage.value}"`);
        }
    }
    console.log('Validation tests complete.');

    if (invalidInputs.length > 0) {
        console.error("The following inputs resulted in an 'Invalid Value':", invalidInputs);
        throw new Error("Validation failed for some inputs.");
    } else {
        console.log("All tested inputs resulted in 'Valid Value'.");
    }
}
```

Unfortunately it added text in the code output so I had to copy and paste manually to avoid a syntax error.

But now we have lost the list of valid entries.

```
Create code to generate the list of testStrings. The test strings should be 7 characters long. Generate enough test strings such that each of the valid characters should be used at least once in a generated test string. The valid characters are A-Z, a-z, 0-9 and *. Then create code to run in the Javascript console to enter each of the generated test strings into the input field with name "characters". Then press the input element of type button with value "Check Input" button. Then check that the validation_message field shows the value "Valid Value". Add any inputs that pass and generate validation_message "Valid Values" to an array called validInputs. Add any of the input values that fail to generate validation_message "Valid Values" into an array called invalidInputs and throw an exception.
```

I'm not actually reviewing the code at this point. But the code generated is prefixed with invalid characters making it not possible to copy to the console. Amended version is below.

```
const characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789*";
const stringLength = 7;
const requiredCharacters = new Set(characters);
const testStrings = [];
const generatedCharacters = new Set();
let attempts = 0;
const maxAttempts = 10000; // Limit attempts to avoid infinite loops in case coverage is hard

// Generate strings until all characters are covered or max attempts reached
while (generatedCharacters.size < requiredCharacters.size && attempts < maxAttempts) {
    let randomString = '';
    let stringContainsNewCharacter = false;
    for (let j = 0; j < stringLength; j++) {
        const randomIndex = Math.floor(Math.random() * characters.length);
        const char = characters[randomIndex];
        randomString += char;
        if (!generatedCharacters.has(char)) {
            generatedCharacters.add(char);
            stringContainsNewCharacter = true;
        }
    }
     if (stringContainsNewCharacter || testStrings.length < requiredCharacters.size / stringLength + 5) {
        testStrings.push(randomString);
    }
    attempts++;
}

console.log(`Generated ${testStrings.length} test strings covering all required characters (attempted ${attempts} times).`);

const validInputs = [];
const invalidInputs = [];
const characterInput = document.querySelector('input[name="characters"]');
const checkButton = document.querySelector('input[type="button"][value="Check Input"]');
const validationMessage = document.querySelector('input[name="validation_message"]');

if (!characterInput) {
    console.error("Character input with name 'characters' not found.");
} else if (!checkButton) {
    console.error("Check Input button not found.");
} else if (!validationMessage) {
    console.error("Validation message element not found.");
} else {
    console.log("Starting validation tests...");
    for (const str of testStrings) {
        characterInput.value = str;
        checkButton.click();
        // In some cases, you might need a small delay here for asynchronous updates:
        // await new Promise(resolve => setTimeout(resolve, 50));
        if (validationMessage.value === "Valid Value") {
            validInputs.push(str);
            console.log(`Input "${str}" passed validation.`);
        } else {
            invalidInputs.push(str);
            console.warn(`Input "${str}" failed validation. Received: "${validationMessage.value}"`);
        }
    }
    console.log('Validation tests complete.');
    console.log("Valid inputs:", validInputs);
    console.log("Invalid inputs:", invalidInputs);


    if (invalidInputs.length > 0) {
        throw new Error("Validation failed for some inputs. See invalidInputs array for details.");
    }
}


```

OK, that's actually pretty good.

```
Valid inputs: (20) ['163mmKd', 'FSoQf1F', 'EjWix2l', 'Dx48aQP', '0cZtBde', 'XSqGhRy', 'w4e1Fz7', 'hwaeqX4', 'PtKzHTd', 'zu3YskB', 'GO9mvXt', 'hIJqalY', 'MZFx6C3', 'DKjNwy7', 'n0mxE4m', 'dcXZmbW', '*JuKh*V', '5jBauIR', 'FXsirL0', '5xWJTUh']
VM589:59 Invalid inputs: (3) ['sGDE6Ag', 'oAJFVqi', 'KFA4pi9']
```

All of the invalid inputs have "A" in them, which is probably the problem. Let's see if the AI can spot that.


### Analysis of invalid values

```
All of these values return the message "Invalid Value", is there anything common between each string value? The values are 'sGDE6Ag', 'oAJFVqi', 'KFA4pi9'
```

---

Let's examine the three invalid strings you provided:

- sGDE6Ag
- oAJFVqi
- KFA4pi9

Comparing these strings to the allowed characters (A-Z, a-z, 0-9, *), we can observe the following:

- All three strings are exactly 7 characters long, which matches the expected length based on previous interactions.
- All characters used in these strings are within the allowed character set (lowercase letters, uppercase letters, and digits).
- None of these strings contain the '*' character.

However, looking closely, a potential commonality emerges:

- sGDE6Ag: Contains both uppercase and lowercase letters, and a digit.
- oAJFVqi: Contains only lowercase letters.
- KFA4pi9: Contains uppercase letters, lowercase letters, and digits.

Based on the character set and the examples of previously validated strings like "AZaz09*", "BBBBBBB", and "1234567", and now seeing these fail, a possible reason for these strings being invalid is a requirement that the input must contain a mix of different character types, or potentially must include the '*' character.

For example, the validation might require:

- At least one uppercase letter.
- At least one lowercase letter.
- At least one digit.
- Possibly, the '*' character must be present.

Let's re-examine your example strings in light of this:

- sGDE6Ag: Has uppercase (G, D, E, A), lowercase (s, g), and digits (6). This string actually contains a mix, so this hypothesis might be incorrect or incomplete.
- oAJFVqi: Has only lowercase letters. This fits a potential rule requiring mixed character types.
- KFA4pi9: Has uppercase (K, F, A), lowercase (p, i), and digits (4, 9). Like sGDE6Ag, this string also contains a mix.

Considering the previously validated strings:

- AZaz09*: Contains uppercase, lowercase, digits, and *.
- BBBBBBB: Contains only uppercase. (Wait, this one was valid in a previous test? This contradicts a simple "must contain mixed types" rule).
- 1234567: Contains only digits. (This was also valid previously, further contradicting a simple "must contain mixed types" rule).

Okay, let's reconsider the valid strings from the last test: validInputs: ["BNpcBZs", "Q9OcHMN", "ZMaj7gr", "p63vOUx", "hLwxuah", "V9rs0dc", "MzitDx3", "77N4keg", "*NlERbe", "Tw1d2o4", "PbJK1ZE", "6OB7cjn", "Y91YFVQ", "F8R7JZP", "1XkmfGI", "WPPYVpq", "5yfWYCl"]

And invalid strings: invalidInputs: ["ZAc5sAS", "NbG0AWx", "ya4AFfh"] (plus your new examples: 'sGDE6Ag', 'oAJFVqi', 'KFA4pi9')

Let's look at the invalid strings again:

- ZAc5sAS: Uppercase, lowercase, digit.
- NbG0AWx: Uppercase, lowercase, digit.
- ya4AFfh: Lowercase, digit.
- sGDE6Ag: Uppercase, lowercase, digit.
- oAJFVqi: Lowercase only.
- KFA4pi9: Uppercase, lowercase, digit.

And some valid ones:

- BNpcBZs: Uppercase, lowercase.
- Q9OcHMN: Uppercase, lowercase.
- ZMaj7gr: Uppercase, lowercase, digit.
- p63vOUx: Lowercase, digit, uppercase.
- 77N4keg: Digit, lowercase.
- *NlERbe: *, uppercase, lowercase.
- BBBBBBB (previously valid): Uppercase only.
- 1234567 (previously valid): Digits only.

It seems the validation is not simply about including a mix of types or requiring the '' character, as we see examples of valid strings with only one character type (like "BBBBBBB" or "1234567") and valid strings without ''.

A pattern that might be significant across the new invalid strings ('sGDE6Ag', 'oAJFVqi', 'KFA4pi9'), and some from the previous invalid list ('ZAc5sAS', 'NbG0AWx', 'ya4AFfh'), is the absence of the '' character. While AZaz09* and *NlERbe were valid and contained '', some valid strings did not contain it.

Let's re-evaluate based on the combined list of invalid strings from this and the previous query: Invalid: 'sGDE6Ag', 'oAJFVqi', 'KFA4pi9', 'ZAc5sAS', 'NbG0AWx', 'ya4AFfh'

Commonality among these invalid strings:

- They are all 7 characters long.
- They all use characters from the allowed set (A-Z, a-z, 0-9, *).
- None of these invalid strings contain the '*' character.

Let's look at the valid strings that don't contain '*':

BNpcBZs, Q9OcHMN, ZMaj7gr, p63vOUx, hLwxuah, V9rs0dc, MzitDx3, 77N4keg, Tw1d2o4, PbJK1ZE, 6OB7cjn, Y91YFVQ, F8R7JZP, 1XkmfGI, WPPYVpq, 5yfWYCl, BBBBBBB, 1234567

This hypothesis about the '' character doesn't hold up perfectly, as many valid strings also lack the ''.

Let's look for other patterns. Perhaps there's a rule about consecutive characters of the same type, or the position of certain characters.

Consider the structure of the invalid strings:

- sGDE6Ag: s, GDE, 6, A, g (Alternating cases, then digit, then alternating cases)
- oAJFVqi: o, AJFV, q, i (Lowercase, then uppercase block, then lowercase)
- KFA4pi9: KFA, 4, p, i, 9 (Uppercase block, then digit, then lowercase, then digit)
- ZAc5sAS: ZA, c, 5, s, AS (Uppercase, lowercase, digit, lowercase, uppercase)
- NbG0AWx: Nb, G, 0, AW, x (Mixed case start, then uppercase, digit, uppercase block, lowercase)
- ya4AFfh: ya, 4, AF, fh (Lowercase, digit, uppercase block, lowercase block)

It's difficult to discern a clear common pattern from just these few invalid strings without knowing the exact validation logic. The validation could be based on:

- Specific character combinations or sequences.
- The number of characters of each type.
- Exclusion of certain characters or patterns.
- A checksum or other algorithmic rule.
- Being present in a blacklist of known invalid strings.
- Based solely on the provided examples and previous test results, there isn't a simple, obvious commonality that explains why 'sGDE6Ag', 'oAJFVqi', and 'KFA4pi9' (along with the others) are invalid, while strings like 'BBBBBBB' and '1234567' are valid. The validation logic is likely more complex than just requiring a mix of character types or the presence of the '*' character.

To truly understand why these strings are invalid, you would need to examine the JavaScript code that performs the validation on the page. You can usually find this code by inspecting the event listeners attached to the "Check Input" button or by searching the page's script files for validation-related functions.

---

And... OK, interesting but not so good.

What is the `''` character? that's an empty string.

This question created a lot of noise for me to look through

### Pretty Good

That's pretty good if I stick to the core concept of writing code.

Analysing it from a test perspective seems a bit of a let down.

But having this functionality built into the browser is pretty amazing.