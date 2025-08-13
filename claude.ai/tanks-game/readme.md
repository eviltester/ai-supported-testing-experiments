# Tank Game

I once started writing a game like this for the Game Boy.

I didn't get much further than showing the tank on screen, moving around, and shooting bullets.

The code has long vanished into the old computer code void.

Since claude is promoted as being good for code generation. Let's try it.

On the free plan.

https://claude.ai/share/77ad5213-83b6-43f0-8295-c401598caee6

- **20250812 15:20 - Session 1 - create basic game**
   - Could only use 5 prompts in the free version before running out of tokens
   - It created 21 versions of the code - saved out the full code for each prompt finish
   - Sadly prompt 5 completed with non-working code, but I coded a manual fix to get it running  [005/tank_game-005-manual-fix.html](005/tank_game-005-manual-fix.html)
   - there are additional bugs in this version in that the title screen does not fully render
   - But... I'm quite impressed to have got this far in about 20 minutes
   - [Playable - 001](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/001/tank_game.html)
   - [Playable - 002](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/002/tank_game-002.html)
   - [Playable - 003](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/003/tank_game-003.html)
   - [Playable - 004](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/004/tank_game-004.html)
   - [Broken - 005](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/005/tank_game-005.html)
   - [Playable with manual fix - 005](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/005/tank_game-005-manual-fix.html)
- **20250812 20:30 - Sesssion 2 - try to fix intro sreen**
   - my free plan refreshed, but every request triggered a 500 error and is seemed like there are too many people using claude so the free plan is limited.
   - I'd prefer a better experience on the free plan. The message says that this wouldn't happen on the paid plan, but I currently don't trust it.
   - I was not able to do anything in this session
- **20250813 09:00 - Session 3 - fix intro screen**
   - Took 6 repeated prompts
   - retried 005, then 006-010
   - 006 - 010 were broken
   - fortunately 010 worked. Claude claimed to have rewritten the code from scratch to fix this.
   - [Playable - 010](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/010/tank_game-010.html)
   - rewriting from scratch is not a normal thing for a programmer to do - is this how AI tools write code? Throw it away and rewrite.
- TODO:
   - add a game over screen
   - enhance


hint: when viewing any of the html files on Github prefix them with `https://htmlpreview.github.io/?` to use it in a browser.

## 001 - create a basic game

> Create a Javacript game where a 2d vector tank can move around a square arena. Randomly generate enemy tanks every 10 to 15 seconds. The player an move the main vector tank by pressing the cursor keys: up to go forwards, down to go backwards, left to rotate anti-clockwise by 5 degress, right to rotate clockwise by 5 degrees. Space should shoot a bullet that will travel in the direction the player is facing.

[Playable](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/001/tank_game.html)

---

I've created a complete 2D vector tank arena game with all the features you requested! Here's what the game includes:

... _for full chat see the [shared claude.ai chat session](https://claude.ai/share/77ad5213-83b6-43f0-8295-c401598caee6)_

The game starts immediately when loaded. Try to survive as long as possible while destroying enemy tanks to rack up points!

## 002 - more enemies, shoot each other

> Amend the game so that it starts with 4 enemy tanks generated on screen. And the enemy tanks should generate with different colours. And the enemy tanks will shoot at each other as well as the player.

[Playable](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/002/tank_game-002.html)

---

Perfect! I've updated the tank game with all your requested changes:

---

The game is now much more dynamic with colorful enemy tanks battling each other while also trying to take you down. This creates interesting tactical situations where you can sometimes let enemies fight each other or get caught in crossfire!

---

When the game starts the tanks die. I think they are shooting themselves

## 003 - No shoot self, no trails

> Tanks should not be able to shoot themselves. The trails left behind by the tanks should completely fade away. Player should only get points when they shoot an enemy tank.

[Playable](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/003/tank_game-003.html)

---

Perfect! I've made all the requested changes:

...

The game now has cleaner mechanics and visuals while maintaining the chaotic multi-faction combat!

## 004 - slower, shorter, explosion

> Bullets should only have a range of  a quarter of the screen width. Bullets should travel at half their current speed. When a bullet reaches the end of the range or hits something it should explode in a coloured particle storm.

[Playable](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/004/tank_game-004.html)

---

Perfect! I've implemented all the requested bullet improvements:

...

The game now has much more satisfying combat with beautiful particle effects that make every shot feel impactful!

### 005 - Intro screen

> Create an intro screen which shows the game name as "Angry Tank Arena".  Explain the game basics and show keys with explanations of what they do. The player should press Space to start the game.

[Playable](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/005/tank_game-005.html)

The AI generated version is broken - not sure if that is because I ran out of tokens?

[Manually Fixed - Playable](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/005/tank_game-005-manual-fix.html)

---

Perfect! I've created a comprehensive intro screen for "Angry Tank Arena" with all the requested features:

...

The intro screen provides all essential information while maintaining the game's vector graphics aesthetic. Players will now have a clear understanding of the chaotic multi-faction combat before diving into the action!

---

**Actually it is now broken - but perhaps this is because I ran out of free tokens?**

I manually did a quick fix of the game in [005/tank_game-005-manual-fix.html](005/tank_game-005-manual-fix.html). It was about 5 lines of code (and upon review I can see I made it harder than I needed to, but hey... it worked)

TODO (to fix this iteration with vibing):

- The intro screen does not show all the details.
- The game over alert should be a game over animation for 10 seconds and then switch to the intro screen.

## 005 - ??? fix intro screen

I had to go through multiple prompts to fix the intro screen.

I retried prompt 005 because it died in the middle of creating it.

> Create an intro screen which shows the game name as "Angry Tank Arena".  Explain the game basics and show keys with explanations of what they do. The player should press Space to start the game.

final version 21

But this resulted in the same problem.

Then I had to issue a bunch of "fix it please" prompts.

## 006 - fix intro screen again

> The intro screen is not visible and the game does not start when space is pressed, please fix this.

final version 23

[Broken](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/006/tank_game-006.html)

Claude.ai confidently said that it knew what the issue was, edited the code, proclaimed it fixed. And then it didn't work.

---

The issue was that the game loop was continuing to run the game logic even when showing the intro screen, and there was a conflict with the Space key handling. I've fixed both issues:

...

The intro screen should now be visible when you load the page, and pressing Space should properly start the game!

**But it wasn't**

## 007 - fix intro screen again again

> The intro screen does not display immediately when the page loads. Pressing space does not start the game. Please fix.

final version 25

[Broken](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/007/tank_game-007.html)

Claude.ai confidently said that it knew what the issue was, edited the code, proclaimed it fixed. And then it didn't work.

---

I've fixed the issue! The problem was that the game loop wasn't properly starting with requestAnimationFrame.

...

The intro screen should now be visible immediately when you load the page, and pressing Space should start the game. The requestAnimationFrame ensures the intro screen renders properly and the game loop continues running smoothly.


**But it wasn't**


## 008 - fix intro screen again again again

> When the page loads, the intro screen is not displayed and the space bar does not start the game.

final version 28

[Broken](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/008/tank_game-008.html)


Claude.ai confidently said that it knew what the issue was, edited the code, proclaimed it fixed. And then it didn't work.

---

I see the issue! Let me debug this step by step. The problem is likely in the game loop logic or the intro screen drawing function. Let me fix this:

...

Now when you load the page:

Check the browser console (F12) to see if "Drawing intro screen" appears
Press Space and check if you see "Key pressed: Space" and "Starting game!" in the console

This will help us identify exactly where the issue is. The intro screen should now display properly, and the debug logs will show us what's happening when you press Space.

**So console logs were used to debug the code, and based on my response it would fix it? That suggests that Claude is unable to debug the code. Code is a 'side-effect' and in the Chat UI it is not possible to edit the code, so this is a deficiency in the current tooling. Perhaps Claude needs to add a debugging and defect identification Agent?**



## 009 - fix intro screen again again again again

> The intro screen is not visible to the player. All the player sees when the game starts is the blue screen. Pressing space does not start the game but it does write messages to the console that the game has started. But the game does not start and the intro screen is not visible. Fix this so that the intro screen is displayed when the page is opened and the game starts when the player presses space.

final version 30

[Broken](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/009/tank_game-009.html)

Claude.ai confidently knows what the problem is and will fix it. But stating "The issue was likely that" does not fill me with confidence that Claude had identified the issue. **And it had not.**

---

I see the issue! The problem is that the intro screen drawing function is being called but something is interfering with it. Let me fix this by simplifying and ensuring the intro screen displays properly:

...

The issue was likely that something was drawing over the intro screen or the canvas wasn't being cleared properly before drawing the intro content.
Now when you load the page:

You should see the intro screen with "ANGRY TANK ARENA" title and all the controls/instructions
Pressing Space should start the game and show the actual gameplay

The intro screen should now be clearly visible with white text on the blue background!

## 010 - fix intro screen again again again again again

> The intro screen is not visible and the game does not start when the player presses space. please fix.

final version 31

[Playable - 010](https://htmlpreview.github.io/?https://github.com/eviltester/ai-supported-testing-experiments/blob/main/claude.ai/tanks-game/010/tank_game-010.html)

And again with the "the problem is likely". More likely that Claude is hacking about. But there is some hope "Let me completely rewrite this with a simpler, more reliable approach".

I have, as a programmer, given up, thrown code away and started again using the knowledge I've gained. But never so much code, and given that this was a simple bug to fix I don't think it needs a rewrite. Oh well, the AI is in charge here because I'm 'vibing'. And this was my last prompt for this free session.

Fortunately it did create working code.

---

I see the issue! The problem is likely that the game loop isn't starting properly or there's a fundamental issue with the setup. Let me completely rewrite this with a simpler, more reliable approach:

