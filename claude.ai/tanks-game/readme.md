# Tank Game

I once started writing a game like this for the Game Boy.

I didn't get much further than showing the tank on screen, moving around, and shooting bullets.

The code has long vanished into the old computer code void.

Since claude is promoted as being good for code generation. Let's try it.

On the free plan.

https://claude.ai/share/77ad5213-83b6-43f0-8295-c401598caee6

- 20250812 15:20 - Session 1
   - Could only use 5 prompts in the free version before running out of tokens
   - It created 21 versions of the code - saved out the full code for each prompt finish
   - Sadly prompt 5 completed with non-working code, but I coded a manual fix to get it running  [005/tank_game-005-manual-fix.html](005/tank_game-005-manual-fix.html)
   - there are additional bugs in this version in that the title screen does not fully render
   - But... I'm quite impressed to have got this far in about 20 minutes

## 001 - create a basic game

> Create a Javacript game where a 2d vector tank can move around a square arena. Randomly generate enemy tanks every 10 to 15 seconds. The player an move the main vector tank by pressing the cursor keys: up to go forwards, down to go backwards, left to rotate anti-clockwise by 5 degress, right to rotate clockwise by 5 degrees. Space should shoot a bullet that will travel in the direction the player is facing.

---

I've created a complete 2D vector tank arena game with all the features you requested! Here's what the game includes:
Controls:

Arrow Up: Move forward
Arrow Down: Move backward
Arrow Left: Rotate counterclockwise by 5 degrees
Arrow Right: Rotate clockwise by 5 degrees
Spacebar: Shoot bullets

Game Features:

Square arena with boundaries
Player tank (green) that moves with realistic physics including acceleration and friction
Enemy tanks (red) that spawn every 10-15 seconds at random edges
Enemy AI that makes tanks move toward the player and shoot back
Collision detection between bullets and tanks
Score system (100 points per enemy destroyed)
Game over when hit by enemy bullets or colliding with enemy tanks
Real-time score and enemy counter display

Technical Details:

Uses HTML5 Canvas for smooth 2D vector graphics
60 FPS game loop with requestAnimationFrame
Proper physics with angle-based movement
Bullet system with limited lifespan
Responsive controls with smooth rotation and movement
Visual effects with glowing borders and transparency

The game starts immediately when loaded. Try to survive as long as possible while destroying enemy tanks to rack up points!

## 002 - more enemies, shoot each other

> Amend the game so that it starts with 4 enemy tanks generated on screen. And the enemy tanks should generate with different colours. And the enemy tanks will shoot at each other as well as the player.

---

Perfect! I've updated the tank game with all your requested changes:
New Features Added:

4 Starting Enemy Tanks: The game now begins with 4 enemy tanks already spawned on the battlefield instead of waiting for the first spawn timer.
Different Colored Enemy Tanks: Each enemy tank now gets a random color from a palette of 8 vibrant colors including red, orange, pink, blue, purple, cyan, yellow, and teal.
Enemy vs Enemy Combat:

Enemy tanks now target the nearest threat (either the player or another enemy tank)
They will shoot at other enemy tanks as well as the player
Enemy bullets can destroy other enemy tanks
When enemies destroy each other, you get 50 points (vs 100 for your kills)



Enhanced AI Behavior:

Enemies now use more sophisticated targeting - they find and attack the nearest threat
They maintain tactical distance (approach if far, retreat if too close)
More frequent shooting with slightly randomized timing
Better combat positioning and movement

Updated Scoring:

Player kills enemy: 100 points
Enemy kills enemy: 50 points

The game is now much more dynamic with colorful enemy tanks battling each other while also trying to take you down. This creates interesting tactical situations where you can sometimes let enemies fight each other or get caught in crossfire!

---

When the game starts the tanks die. I think they are shooting themselves

## 003 - No shoot self, no trails

> Tanks should not be able to shoot themselves. The trails left behind by the tanks should completely fade away. Player should only get points when they shoot an enemy tank.

---

Perfect! I've made all the requested changes:
Fixed Issues:

Tanks Can't Shoot Themselves:

Added an owner property to bullets that tracks which tank fired them
Enemy bullets now check enemies[j] !== enemyBullets[i].owner to prevent self-damage
Tanks can no longer accidentally shoot themselves


Tank Trails Completely Fade:

Changed the canvas clearing from semi-transparent overlay (rgba(15, 52, 96, 0.1)) to completely opaque (#0f3460)
Now there are no trails left behind - tanks move cleanly without any ghosting effects


Player Only Gets Points for Their Kills:

Removed the 50-point reward for enemy vs enemy kills
Players now only earn points (100 each) when they personally shoot an enemy tank
Enemy tanks can still fight each other, but it doesn't affect your score



Updated Gameplay:

The battlefield is now cleaner with no visual trails
Enemy tanks will still battle each other strategically, but you won't get "cheap" points from their fights
You must actively engage enemies to build your score
No more accidental self-destruction by enemy tanks

The game now has cleaner mechanics and visuals while maintaining the chaotic multi-faction combat!

## 004 - slower, shorter, explosion

> Bullets should only have a range of  a quarter of the screen width. Bullets should travel at half their current speed. When a bullet reaches the end of the range or hits something it should explode in a coloured particle storm.

---

Perfect! I've implemented all the requested bullet improvements:
New Bullet Mechanics:

Limited Range:

Bullets now have a maximum range of exactly 1/4 of the screen width (200 pixels on an 800px wide canvas)
Each bullet tracks its starting position and distance traveled
Bullets automatically explode when they reach maximum range


Slower Speed:

Bullet speed reduced from 8 to 4 pixels per frame (half the original speed)
This makes combat more tactical and gives players more time to react


Particle Explosion System:

New Particle class creates colorful explosion effects
Particles have physics (velocity, decay, size reduction)
Particles fade out over time with transparency effects
Explosions occur when:

Bullets hit targets (12 particles)
Bullets reach maximum range (8 particles)
Bullets hit screen boundaries (8 particles)





Visual Effects:

Player bullets: Explode in yellow particles
Enemy bullets: Explode in reddish particles matching their bullet color
Particles scatter in random directions with realistic physics
Smooth fade-out with alpha transparency
Different particle counts for different explosion types

Gameplay Impact:

More strategic positioning required due to limited bullet range
Slower bullets make dodging possible but still challenging
Visual feedback makes it clear when and where bullets expire
The battlefield becomes more dynamic with colorful explosions everywhere!

The game now has much more satisfying combat with beautiful particle effects that make every shot feel impactful!

### 005 - Intro screen

Create an intro screen which shows the game name as "Angry Tank Arena".  Explain the game basics and show keys with explanations of what they do. The player should press Space to start the game.

---

Perfect! I've created a comprehensive intro screen for "Angry Tank Arena" with all the requested features:
New Intro Screen Features:

Game Title: "ANGRY TANK ARENA" displayed prominently in bright green
Game Description: Clear explanation of the multi-faction tank warfare concept
Detailed Controls Section:

Arrow keys with specific explanations
Spacebar for shooting
Visual formatting with proper alignment


Scoring Information:

100 points per enemy tank destroyed
Clarification that enemy vs enemy kills don't give points


Visual Polish:

Blinking "PRESS SPACE TO START!" text for attention
Color-coded sections (green title, white text, yellow scoring, red start prompt)
Professional layout with consistent spacing
Game arena border maintained



Game Flow Changes:

Game now starts in intro state instead of immediately playing
Space bar starts the game from intro screen
Game properly resets when starting (clears all arrays, resets player position)
Smooth transition from intro to gameplay

Enhanced User Experience:

Players understand the game mechanics before starting
Clear control explanations prevent confusion
Professional presentation builds excitement
Easy one-key start mechanism

The intro screen provides all essential information while maintaining the game's vector graphics aesthetic. Players will now have a clear understanding of the chaotic multi-faction combat before diving into the action!

---

Actually it is now broken - but perhaps this is because I ran out of free tokens?

I manually did a quick fix of the game in [005/tank_game-005-manual-fix.html](005/tank_game-005-manual-fix.html)

TODO (to fix this iteration):

- The intro screen does not show all the details.
- The game over alert should be a game over animation for 10 seconds and then switch to the intro screen.