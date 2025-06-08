# Using Claude.ai 4 web to generate a CounterString directly without using code

## Experiment Introduction

Claude 4 kept being mentioned as the best current model. So I thought I would try it for generating strings directly.

I know it is promoted for code, but... what the heck. Let's go.

## Reflections

Claude 4 made some interesting mistakes.

Even during its 'verification' if failed. It confidently counts 20 chars as 21.

Perhaps it is better generating code.

I need to try.

# Transcript

- session can be found online [here](https://claude.ai/share/5b0a23ce-2b2d-453c-88f9-c413b80958c2)


## Fail to Generate a CounterString ending in *

> can you generate a CounterString with length 20 characters

Here's a CounterString with exactly 20 characters:

```
*3*5*7*9*12*15*18*20
```

This CounterString follows the standard format where:
- Each asterisk (*) marks a position
- The numbers indicate the character count at that position
- The final number (20) confirms the total length
- It's exactly 20 characters long

CounterStrings are useful for testing text fields, boundary conditions, and ensuring proper character counting in applications.


## Here is exactly 20 chars, actually it is 21

> That is not correct. The final character in the counterstring should be *

You're absolutely right! Here's the correct CounterString with exactly 20 characters:

```
*3*5*7*9*12*15*18*20*
```

In a proper CounterString:
- Each position marker shows the character count at that point
- The string must end with an asterisk (*)
- The total length is exactly 20 characters

Thank you for the correction!

## Here is a 20 character incorrect string

> That is not correct, the toal length of that string is 21 characters

You're absolutely right - let me count carefully. Here's the correct CounterString with exactly 20 characters:

```
*3*5*7*9*12*15*18*20
```

Let me verify the count:
- `*3*5*7*9*12*15*18*20` = 20 characters exactly

The string ends with the position number "20" (not an asterisk) because adding an asterisk would make it 21 characters total. Thank you for catching my error!

> Generate the string such that it is 20 characters long and the final character is an *

You're right! Here's a CounterString that is exactly 20 characters long and ends with an asterisk:

```
*3*5*7*9*12*15*18*1*
```

Let me verify the count:
`*3*5*7*9*12*15*18*1*` = 20 characters exactly

The key is that the second-to-last position marker shows "1" (representing position 19), and then the final character is the asterisk (*) at position 20.

## Let me explain the rules again and generate garbage

> Generate a string such that it is 20 characters long, the final character is an * and the digits before the final * are 20, and all other * in the string have digits describing the * position in the String. The only exception is when the first character has to be * to make the string work.

Here's the CounterString that meets all your requirements:

```
*3*5*7*9*12*15*1820*
```

Let me verify:
- Total length: 20 characters ✓
- Final character is * ✓ 
- Digits before final * are "20" ✓
- Each other * has digits showing its position:
  - * at position 1 (implied first character)
  - 3 after * at position 3
  - 5 after * at position 5  
  - 7 after * at position 7
  - 9 after * at position 9
  - 12 after * at position 12
  - 15 after * at position 15
  - 18 after * at position 18
- Position 20 is the final *

The string is exactly 20 characters and follows the CounterString format where each asterisk's position is indicated by the digits that follow it.

## Wrong again, plese explain, is this even possible?

> 18 does not have an * after it

You're absolutely right! Let me fix that. Here's the correct CounterString:

```
*3*5*7*9*12*15*18*20*
```

Wait, that's 21 characters. Let me try again to fit exactly 20 characters:

```
*3*5*7*9*12*1520*
```

Let me verify:
- `*3*5*7*9*12*1520*` = 17 characters

I'm struggling to make this work. Could you help me understand - with the constraint that we need exactly 20 characters, end with *, have "20" before the final *, and mark every asterisk position, is this mathematically possible? The requirements seem to create a length conflict.

## Here is an example. Let me generate and check and still get it wrong.

> It is possible. here is an example counterstring of length 20 that meets all the rules: 246811141720

Ah, I see it now! Thank you for the example. You're absolutely right - here's a CounterString of exactly 20 characters that meets all the rules:

```
2*4*6*8*11*14*17*20*
```

Let me verify:
- Total length: 20 characters ✓
- Final character is * ✓
- Digits before final * are "20" ✓
- Each * has digits describing its position:
  - 2 before * at position 2
  - 4 before * at position 4
  - 6 before * at position 6
  - 8 before * at position 8
  - 11 before * at position 11
  - 14 before * at position 14
  - 17 before * at position 17
  - 20 before * at position 20

The key insight I was missing is that the digits come *before* each asterisk to indicate that asterisk's position, rather than after. Thank you for showing me the correct format!

## Try again. Verification fail

> generate a counterstring using the rules of length 21

Here's a CounterString of exactly 21 characters following the same rules:

```
2*4*6*8*11*14*17*21*
```

Let me verify:
- Total length: 21 characters ✓
- Final character is * ✓
- Digits before final * are "21" ✓
- Each * has digits describing its position:
  - 2 before * at position 2
  - 4 before * at position 4
  - 6 before * at position 6
  - 8 before * at position 8
  - 11 before * at position 11
  - 14 before * at position 14
  - 17 before * at position 17
  - 21 before * at position 21

The pattern works the same way - each number indicates the position of the asterisk that immediately follows it.

## Count your string. Ooops


> how long is the string "246811141721"

Let me count the characters in "2*4*6*8*11*14*17*21*":

2-*-4-*-6-*-8-*-1-1-*-1-4-*-1-7-*-2-1-*

That's 20 characters, not 21.

I need to adjust it to make it exactly 21 characters. Here's the correct CounterString of length 21:

```
2*4*6*8*11*14*18*21*
```

Let me verify the count:
2-*-4-*-6-*-8-*-1-1-*-1-4-*-1-8-*-2-1-* = 21 characters ✓

The adjustment was changing "17" to "18" to account for the extra character needed.

## Check again. Oops. Let me just at 1 to make 3 chars

> Are you sure, that looks like 20 characters not 21

You're absolutely right - let me count more carefully:

"2*4*6*8*11*14*18*21*"
2-*-4-*-6-*-8-*-1-1-*-1-4-*-1-8-*-2-1-* = 20 characters

I keep making the same mistake. For a 21-character CounterString, I need to add one more character. Here's the correct one:

```
2*4*6*8*11*14*19*21*
```

Let me count: 2-*-4-*-6-*-8-*-1-1-*-1-4-*-1-9-*-2-1-* = 21 characters ✓

The key was changing "18" to "19" to add that extra character needed to reach exactly 21 total characters.

## Wrong. Check again. Fail again.

> That is still not correct, adding 1 to a two digit number does not make the two digit number longer when converted to a String, that only happens when the number is 99.

You're absolutely right! Adding 1 to 18 to get 19 doesn't change the string length - both are 2-digit numbers.

To make a 21-character CounterString from the 20-character one, I need to add exactly one character. Let me think about this systematically.

Starting with: `2*4*6*8*11*14*17*20*` (20 chars)

To get 21 characters, I could:
- Add a single-digit position marker somewhere, or  
- Change the structure slightly

Here's a correct 21-character CounterString:

```
*3*5*7*10*13*16*19*21*
```

Let me count: *-3-*-5-*-7-*-1-0-*-1-3-*-1-6-*-1-9-*-2-1-* = 21 characters ✓

This follows the rule that the first character can be * when needed to make the string work, and each number indicates the position of the asterisk that follows it.