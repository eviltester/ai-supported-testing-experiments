**Session-Based Exploratory Testing Prompt for 7 Char Val Application**

**Objective:**  
Perform comprehensive session-based exploratory testing on the 7 Char Val application (http://localhost:4567/apps/7-char-val/) to identify bugs in input validation logic, assess character and length validation, and evaluate overall functionality through systematic exploration of diverse input patterns.

**Scope:**  
- Input validation for 7-character strings  
- Character restrictions (A-Z, a-z, 0-9, *)  
- Length validation (exactly 7 characters)  
- Edge cases and boundary conditions  
- Invalid character handling  
- User interface behavior  
- Focus on input diversity: varied starting characters, patterns, positions  

**Approach:**  
1. **Session Planning (15-20 minutes):**  
   - Review application requirements and expected behavior  
   - Design diverse test input categories  
   - Set up testing environment and tools  

2. **Test Execution (30-45 minutes):**  
   - Use automated browser testing (Playwright/Chrome DevTools)  
   - Execute systematic test combinations  
   - Document inputs, expected outputs, actual outputs  
   - Capture screenshots for all failure cases  

3. **Result Analysis (20-30 minutes):**  
   - Analyze patterns in failures  
   - Identify bug categories  
   - Verify findings through repeated testing  

4. **Reporting (20-30 minutes):**  
   - Document session charter and approach  
   - Create comprehensive test results table  
   - Summarize findings and recommendations  

**Tools Required:**  
- Browser automation: Playwright  
- Screenshot capture capability  
- Markdown editor for report writing  
- Access to application at http://localhost:4567/apps/7-char-val/  

**Test Categories to Cover:**  

1. **Valid 7-Char Inputs (Varied Patterns):**  
   - Different starting characters (A-Z, a-z)  
   - Mixed case combinations  
   - Keyboard layout patterns (QWERTY, ASDF, ZXCVBN)  
   - Digits-first, letters-first arrangements  
   - Asterisk in different positions  

2. **Invalid Character Testing:**  
   - Invalid chars at start, middle, end positions  
   - All special characters (!@#$%^&*()[]{}|;:,.<>?/~` etc.)  
   - Invalid chars in different positions within valid strings  

3. **Length Testing:**  
   - 0-6 characters (too short)  
   - 8-10+ characters (too long)  
   - Boundary testing with varied content  

4. **Edge Cases:**  
   - Whitespace characters (space, tab, newline, CR)  
   - Unicode characters  
   - Control characters  
   - Empty strings  

5. **First Character Validation:**  
   - Test all invalid characters as first character  
   - Verify first character validation bug  

**Execution Steps:**  

0. **THINK**
   - is the coverage listed in the instructions enough, if you can think of new conditions to try then do so
   - as you are testing, if you identify new risks or conditions to cover then make a note of them and be sure to try them during te session

1. **Setup:**  
   ```bash
   # Navigate to application
   # Set up browser automation tool
   ```

2. **Automated Testing Script:**  
   Create a script that tests multiple input combinations:  
   ```javascript
   // Example test execution pattern
   const testCases = [
     // Define comprehensive test cases here
     { input: 'Abc123*', expected: 'Valid Value', desc: 'Basic valid' },
     // Add 50-100 varied test cases
   ];
   
   // Execute tests and collect results
   // Capture screenshots for failures
   ```

3. **Screenshot Naming Convention:**  
   - `{bug_type}_{input_description}_{pass/fail}.png`  
   - Examples: `length_bug_8chars_fail.png`, `first_char_bug_varied_fail.png`  

4. **Result Documentation:**  
   - Input string  
   - Description of test case  
   - Expected output  
   - Actual output  
   - Pass/Fail status  
   - Screenshot filename (if failed)  

**Report Structure:**  

```markdown
# Session-Based Exploratory Testing Report: 7 Char Val Application

Date: YYYYMMDD_HHMMSS

## Session Charter
[Objective, scope, approach]

## Test Environment
[Tools, URLs, setup]

## Test Notes
[Execution summary, observations]

## Bugs Found
[Categorized bugs with examples]

## Issues
[Technical and functional issues]

## Test Coverage
[Input domains covered]

## Session Summary
[Results overview, time breakdown]

## Final Findings Summary
[Key conclusions, recommendations]

## Complete Test Results Table
| Input | Description | Expected | Actual | Result | Screenshot |
|-------|-------------|----------|--------|--------|------------|
[All test cases in table format]
```

**Success Criteria:**  
- Execute 50-100 test cases with diverse inputs  
- Identify all major validation bugs  
- Capture screenshots for each failure type  
- Create comprehensive report with full traceability  
- Provide actionable recommendations for fixes  

**Expected Outcomes:**  
- Complete mapping of validation behavior  
- Identification of critical bugs (length, character, first-char)  
- Evidence through screenshots  
- Clear recommendations for code fixes  

Use this prompt to replicate the testing approach and ensure consistent, thorough exploration of the application's validation logic.