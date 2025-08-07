## 20250806 Repeat

Mostly Repeat of experiment but for video recording.

Using https://testpages.eviltester.com/styled/apps/7charval/simple7charvalidation.html

Prompts are listed below.

Results can be seen in video.

- Noticed that - code used by the AI is not always the same as the code output in the copy past section.
- AI did make a mistake during it's coding process and amended it to get code working.
- It is quite slow to review all the code each time.
- Would need to copy code into a Version Control to properly compare differences between prompts each time.
- It did identify combinatorial explosion.
- It did not analyze the root cause of found issue effectively.

TODO: Add video link when ready

---

Create code that will run in the Javascript console to enter the string "AaBbCcd" into the input field names "characters", then click the input button with value "Check Input" and validate that the value shown in the input field named "validation_message" is "Valid Value"

---

Amend the code so that it checks multiple input strings. Following the same process of: inputing the string, clicking the "Check Input" button, then checking that the validation_message is "Valid Value". Use the following input strings: "AZaz09*", "BBBBBBB", "1234567"

---

Create code to generate the list of testStrings. The test strings should cover every combination of 7 character strings where characters can be A-Z, a-z, 0-9 and *

Then check all the input strings. Following the same process of: inputing the string, clicking the "Check Input" button, then checking that the validation_message is "Valid Value".

---

Create code to generate the list of testStrings. The test strings should be 7 characters long and each of the valid characters should be used at least once. The valid characters are A-Z, a-z, 0-9 and *

Then check all the input strings. Following the same process of: inputing the string, clicking the "Check Input" button, then checking that the validation_message is "Valid Value".

---

Create code to generate the list of testStrings. The test strings should be 7 characters long. Generate enough test strings such that each of the valid characters should be used at least once in a generated test string. The valid characters are A-Z, a-z, 0-9 and *

Then check all the input strings. Following the same process of: inputing the string, clicking the "Check Input" button, then checking that the validation_message is "Valid Value".

---

Analyze the Failing Strings: Look for common patterns or specific characters in the strings that returned "Invalid Value". This might give clues about the validation criteria (e.g., are certain character types required in specific positions, are there restrictions on character sequences, etc.).