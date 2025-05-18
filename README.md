# 🦖 Dino Runner Game (LibGDX)

A 2D infinite runner game inspired by the classic Chrome Dino game. Built using **LibGDX**, this version includes enhancements like dynamic background (moon, stars), road marks, moving clouds, score tracking, and multiple obstacles.

## 🚀 Features

- 🕹️ Player jump logic  
- 🌵 Obstacle spawning (multiple cacti types)  
- 🌙 Dynamic moon & stars animation  
- 🛣️ Moving road marks and scrolling background  
- ☁️ Randomly spawning clouds  
- 🎮 Game restart with score and high score  
- 📈 Scoring and persistent high score tracking  
- 🎨 Optimized for smooth performance and modular design  

## 🎮 How to Play

- Press **SPACE** or tap to make the Dino jump.  
- Avoid hitting obstacles like cacti and birds.  
- Survive as long as possible to increase your score.  
- After game over, press **SPACE** to restart.  

## 🧱 Project Structure

```plaintext
com.mygdx.game
│
├── MainGame.java          # Launches the game and sets screen
├── GameScreen.java        # Manages rendering and update loop
├── World.java             # Handles game logic, spawning, collision
│
├── entities/
│   ├── Player.java        # Player control and animation
│   ├── Obstacle.java      # Cactus and bird logic
│   ├── Cloud.java         # Cloud movement logic
│   ├── Moon.java          # Moon movement and rendering
│   ├── Star.java          # Star twinkling effect
│   ├── RoadMark.java      # Road mark scrolling effect
│
├── utils/
│   ├── ScoreManager.java  # Score and high score management
│   └── Assets.java        # Asset loading and disposal
📊 Mermaid.js Class Diagram
classDiagram
    class MainGame {
        +void create()
        +void render()
        +void dispose()
    }

    class GameScreen {
        -World world
        +void render(float delta)
        +void resize(int width, int height)
        +void dispose()
    }

    class World {
        -int speedMultiplier
        -Player player
        -ArrayList~Obstacle~ obstacles
        -ArrayList~Cloud~ clouds
        -Moon moon
        -Star star
        -ScoreManager scoreManager
        +void update(float delta)
        +void spawnObstacle()
        +boolean checkCollision()
        +void reset()
    }

    class Player {
        -Vector2 position
        -Texture texture
        -boolean isJumping
        +void jump()
        +void update(float delta)
        +void render(SpriteBatch batch)
    }

    class Obstacle {
        -Vector2 position
        -Texture texture
        +void update(float delta)
        +void render(SpriteBatch batch)
    }

    class Cloud {
        -Vector2 position
        -Texture texture
        +void update(float delta)
        +void render(SpriteBatch batch)
    }

    class Moon {
        -Vector2 position
        -Texture texture
        +void update(float delta)
        +void render(SpriteBatch batch)
    }

    class Star {
        -Vector2 position
        -Texture texture
        +void update(float delta)
        +void render(SpriteBatch batch)
    }

    class RoadMark {
        -Vector2 position
        -Texture texture
        +void update(float delta)
        +void render(SpriteBatch batch)
    }

    class ScoreManager {
        -int score
        -int highScore
        +void increaseScore(int amount)
        +void reset()
        +int getScore()
        +int getHighScore()
    }

    MainGame --> GameScreen
    GameScreen --> World
    World --> Player
    World --> Obstacle
    World --> Cloud
    World --> Moon
    World --> Star
    World --> ScoreManager
