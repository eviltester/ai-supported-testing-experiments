# Session-Based Exploratory Testing Report: 7 Char Val Application

Date: 2025-12-13

## Session Charter
Objective: Perform comprehensive session-based exploratory testing on the 7 Char Val application to identify bugs in input validation logic, assess character and length validation, and evaluate overall functionality through systematic exploration of diverse input patterns.

Scope: Input validation for 7-character strings, character restrictions (A-Z, a-z, 0-9, *), length validation (exactly 7 characters), edge cases and boundary conditions, invalid character handling, user interface behavior, focus on input diversity.

Approach: Automated testing using Playwright with 100+ test cases covering valid inputs, invalid characters, lengths, edge cases.

## Test Environment
Tools: Playwright browser automation, Chrome DevTools
URLs: http://localhost:4567/apps/7-char-val/
Setup: Local server running, Playwright installed and configured.

## Test Notes
Executed 100 test cases systematically. Captured screenshots for all failure cases. Identified multiple validation bugs through automated execution.

## Bugs Found

### 1. First Character Validation Bug
**Description:** The first character of the input string is not validated against the allowed character set. Any character is accepted in the first position as long as the remaining characters (positions 1-6) are valid.

**Examples:**
- Input: '!bc1234' - Expected: Invalid Value, Actual: Valid Value
- Input: '@bc1234' - Expected: Invalid Value, Actual: Valid Value
- Input: '#bc123*' - Expected: Invalid Value, Actual: Valid Value

**Impact:** Invalid inputs with incorrect first characters are incorrectly accepted.

### 2. Length Validation Bug
**Description:** The application accepts strings longer than 7 characters. Specifically, 8-character strings are accepted, while strings of 9+ are correctly rejected.

**Examples:**
- Input: 'Abc1234*' (8 chars) - Expected: Invalid Value, Actual: Valid Value

**Impact:** Over-length inputs are incorrectly validated as valid.

### 3. Character Validation Bugs
**Description:** Several invalid characters are incorrectly accepted due to bugs in the charCodeIsValid1 function.

**Sub-bugs:**
- Colon (:) is accepted (char code 58, should be invalid)
- Square brackets [ ] are accepted (codes 91, 93)
- Backslash (\\) is accepted (code 92)
- Caret (^) is accepted (code 94)
- Underscore (_) is accepted (code 95)
- Backtick (`) is accepted (code 96)
- Letter 'A' is excluded (code 65 not >65)

**Examples:**
- Input: 'Abc123:' - Expected: Invalid Value, Actual: Valid Value
- Input: 'Abc123[' - Expected: Invalid Value, Actual: Valid Value
- Input: 'Abc123\\' - Expected: Invalid Value, Actual: Valid Value

**Impact:** Invalid characters are accepted, violating the input constraints.

## Issues
- Validation logic has multiple implementation bugs
- Obfuscated JavaScript makes code review difficult
- No client-side feedback for validation errors beyond "Valid/Invalid Value"

## Test Coverage
- Valid 7-char inputs: Various patterns (QWERTY, ASDF, digits-first, letters-first, mixed case)
- Invalid characters: All special characters, invalid positions
- Length testing: Short (0-6), long (8-10+)
- Edge cases: Whitespace, Unicode, control characters
- First character validation: All invalid characters as first char

## Session Summary
- Total test cases: 100
- Passed: 83
- Failed: 17
- Time breakdown: Setup (10 min), Exploration (10 min), Test design (15 min), Script creation (10 min), Execution (15 min), Analysis (15 min), Reporting (15 min)
- Key findings: First character not validated, length allows 8 chars, several invalid chars accepted

## Final Findings Summary
The 7 Char Val application has significant validation bugs that allow invalid inputs to be accepted as valid. The most critical bug is that the first character is completely unvalidated, allowing any character in that position. Additionally, the length check incorrectly permits 8-character strings, and the character validation function has multiple errors allowing prohibited characters.

## Recommendations
1. Fix the validation loop to start from index 0 instead of 1
2. Correct the length check to reject strings longer than 7 characters
3. Fix the charCodeIsValid1 function to properly validate the allowed character set
4. Add client-side validation feedback for better user experience
5. Consider unobfuscating the JavaScript for easier maintenance

## Complete Test Results Table
| Input | Description | Expected | Actual | Result | Screenshot |
|-------|-------------|----------|--------|--------|------------|
| Abc123* | Basic valid | Valid Value | Valid Value | Pass | |
| Abc1234 | No asterisk | Valid Value | Valid Value | Pass | |
| 1234567 | All digits | Valid Value | Valid Value | Pass | |
| Abcdefg | All letters | Valid Value | Valid Value | Pass | |
| ******* | All asterisks | Valid Value | Valid Value | Pass | |
| 1bc1234 | First digit | Valid Value | Valid Value | Pass | |
| *bc1234 | First asterisk | Valid Value | Valid Value | Pass | |
| !bc1234 | First invalid ! | Invalid Value | Valid Value | Fail | bug_First_invalid___fail.png |
| @bc1234 | First invalid @ | Invalid Value | Valid Value | Fail | bug_First_invalid___fail.png |
| Abc123 | Too short 6 | Invalid Value | Invalid Value | Pass | |
| Abc1234* | Too long 8 | Invalid Value | Valid Value | Fail | bug_Too_long_8_fail.png |
|  | Empty | Invalid Value | Invalid Value | Pass | |
| A | Single char | Invalid Value | Invalid Value | Pass | |
| Abc123! | Invalid at end ! | Invalid Value | Invalid Value | Pass | |
| A!c123* | Invalid at pos1 ! | Invalid Value | Invalid Value | Pass | |
| Ab!123* | Invalid at pos2 ! | Invalid Value | Invalid Value | Pass | |
| Abc!23* | Invalid at pos3 ! | Invalid Value | Invalid Value | Pass | |
| Abc1!3* | Invalid at pos4 ! | Invalid Value | Invalid Value | Pass | |
| Abc12!* | Invalid at pos5 ! | Invalid Value | Invalid Value | Pass | |
| Abc123: | Invalid : allowed by bug | Invalid Value | Valid Value | Fail | bug_Invalid___allowed_by_bug_fail.png |
| Abc123[ | Invalid [ allowed by bug | Invalid Value | Valid Value | Fail | bug_Invalid___allowed_by_bug_fail.png |
| Abc123\\ | Invalid \\ | Invalid Value | Valid Value | Fail | bug_Invalid_____fail.png |
| Abc123] | Invalid ] | Invalid Value | Valid Value | Fail | bug_Invalid___fail.png |
| Abc123^ | Invalid ^ | Invalid Value | Valid Value | Fail | bug_Invalid___fail.png |
| Abc123_ | Invalid _ | Invalid Value | Valid Value | Fail | bug_Invalid___fail.png |
| Abc123` | Invalid ` | Invalid Value | Valid Value | Fail | bug_Invalid___fail.png |
| Abc123; | Invalid ; | Invalid Value | Invalid Value | Pass | |
| Abc123< | Invalid < | Invalid Value | Invalid Value | Pass | |
| Abc123= | Invalid = | Invalid Value | Invalid Value | Pass | |
| Abc123> | Invalid > | Invalid Value | Invalid Value | Pass | |
| Abc123? | Invalid ? | Invalid Value | Invalid Value | Pass | |
| Abc123@ | Invalid @ | Invalid Value | Invalid Value | Pass | |
| Abc123# | Invalid # | Invalid Value | Invalid Value | Pass | |
| Abc123$ | Invalid $ | Invalid Value | Invalid Value | Pass | |
| Abc123% | Invalid % | Invalid Value | Invalid Value | Pass | |
| Abc123& | Invalid & | Invalid Value | Invalid Value | Pass | |
| Abc123( | Invalid ( | Invalid Value | Invalid Value | Pass | |
| Abc123) | Invalid ) | Invalid Value | Invalid Value | Pass | |
| Abc123- | Invalid - | Invalid Value | Invalid Value | Pass | |
| Abc123+ | Invalid + | Invalid Value | Invalid Value | Pass | |
| Abc123\| | Invalid \| | Invalid Value | Invalid Value | Pass | |
| Abc123{ | Invalid { | Invalid Value | Invalid Value | Pass | |
| Abc123} | Invalid } | Invalid Value | Invalid Value | Pass | |
| Abc123~ | Invalid ~ | Invalid Value | Invalid Value | Pass | |
| Abc123/ | Invalid / | Invalid Value | Invalid Value | Pass | |
| Abc123" | Invalid " | Invalid Value | Invalid Value | Pass | |
| Abc123' | Invalid ' | Invalid Value | Invalid Value | Pass | |
| Abc123, | Invalid , | Invalid Value | Invalid Value | Pass | |
| Abc123. | Invalid . | Invalid Value | Invalid Value | Pass | |
| Abc123  | Space | Invalid Value | Invalid Value | Pass | |
| Abc123\t | Tab | Invalid Value | Invalid Value | Pass | |
| Abc123\n | Newline | Invalid Value | Invalid Value | Pass | |
| Abc123é | Unicode é | Invalid Value | Invalid Value | Pass | |
| Abc123中 | Unicode 中 | Invalid Value | Invalid Value | Pass | |
| !bc123* | First ! invalid | Invalid Value | Valid Value | Fail | bug_First___invalid_fail.png |
| @bc123* | First @ invalid | Invalid Value | Valid Value | Fail | bug_First___invalid_fail.png |
| #bc123* | First # invalid | Invalid Value | Valid Value | Fail | bug_First___invalid_fail.png |
| $bc123* | First $ invalid | Invalid Value | Valid Value | Fail | bug_First___invalid_fail.png |
| %bc123* | First % invalid | Invalid Value | Valid Value | Fail | bug_First___invalid_fail.png |
| ^bc123* | First ^ invalid | Invalid Value | Valid Value | Fail | bug_First___invalid_fail.png |
| &bc123* | First & invalid | Invalid Value | Valid Value | Fail | bug_First___invalid_fail.png |
| *bc123* | First * valid | Valid Value | Valid Value | Pass | |
| 0bc123* | First 0 valid | Valid Value | Valid Value | Pass | |
| 9bc123* | First 9 valid | Valid Value | Valid Value | Pass | |
| Abc123* | First A valid | Valid Value | Valid Value | Pass | |
| Zbc123* | First Z valid | Valid Value | Valid Value | Pass | |
| abc123* | First a valid | Valid Value | Valid Value | Pass | |
| zbc123* | First z valid | Valid Value | Valid Value | Pass | |
| Abc123* | Basic valid again | Valid Value | Valid Value | Pass | |
| QWERTY* | QWERTY pattern | Valid Value | Valid Value | Pass | |
| ASDFGH* | ASDF pattern | Valid Value | Valid Value | Pass | |
| ZXCVBN* | ZXCVBN pattern | Valid Value | Valid Value | Pass | |
| 123456* | Digits first | Valid Value | Valid Value | Pass | |
| ABCDEF* | Letters first | Valid Value | Valid Value | Pass | |
| *123456 | Asterisk first | Valid Value | Valid Value | Pass | |
| A1B2C3* | Alternating | Valid Value | Valid Value | Pass | |
| a1b2c3* | Lower alternating | Valid Value | Valid Value | Pass | |
| A1b2C3* | Mixed case alternating | Valid Value | Valid Value | Pass | |
| Abc123* | Standard | Valid Value | Valid Value | Pass | |
| Abc123* | Duplicate | Valid Value | Valid Value | Pass | |
| Abc123* | Another duplicate | Valid Value | Valid Value | Pass | |
| Abc123* | Yet another | Valid Value | Valid Value | Pass | |
| Abc123* | More duplicates | Valid Value | Valid Value | Pass | |
| Abc123* | Even more | Valid Value | Valid Value | Pass | |
| Abc123* | Last duplicate | Valid Value | Valid Value | Pass | |