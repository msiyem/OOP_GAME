# 🦖 Dino Runner Game (LibGDX)

A 2D infinite runner game inspired by the classic Chrome Dino game. Built using **LibGDX**, this version includes enhancements like dynamic background (moon, stars), road marks, moving clouds, score tracking, and multiple obstacles.
![DinoGame Image](https://i.postimg.cc/j2SnnFL2/Screenshot-2025-05-18-210207.png)

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
- After game over, press **R** to restart.  

## 🧱 Project Structure

```plaintext
## 📦 Project Structure

```plaintext
com.mygdx.dinogame/
├── DinoGame.java         # Main Game class
├── GameScreen.java       # Manages screen lifecycle
├── World.java            # Core game loop (update/render logic)
├── Player.java           # Dino player logic
├── Obstacle.java         # Cactus obstacle logic
├── Moon.java             # Background moon rendering
├── Star.java             # Animated background stars
├── RoadMark.java         # Moving road dashes
```

##📊 Mermaid.js Class Diagram
```mermaid
classDiagram
    class DinoGame {
        +SpriteBatch batch
        +create()
        +dispose()
    }

    class GameScreen {
        -DinoGame game
        -World world
        +show()
        +render(float delta)
        +resize(int width, int height)
        +pause()
        +resume()
        +hide()
        +dispose()
    }

    class World {
        -DinoGame game
        -Player player
        -Array~Obstacle~ obstacles
        -Array~Star~ stars
        -Array~RoadMark~ roadMarks
        -Moon moon
        -float obstacleTimer
        -float speedMultiplier
        -int score
        -int highScore
        +update(float delta)
        +render()
        +dispose()
    }

    class Player {
        -Texture textureRun1
        -Texture textureRun2
        -Texture textureJump
        -float x, y, velocityY
        -Rectangle bounds
        -boolean isAlive
        +update(float delta)
        +render()
        +getBounds()
        +isAlive()
        +setAlive(boolean alive)
        +dispose()
    }

    class Obstacle {
        -Texture texture
        -float x, y, width, height
        -Rectangle bounds
        +update(float delta)
        +render()
        +getBounds()
        +dispose()
    }

    class Moon {
        -Texture texture
        -float x, y, speed
        +update(float delta)
        +render(SpriteBatch batch)
        +dispose()
    }

    class Star {
        -Texture texture
        -float x, y, speed, scale
        +update(float delta)
        +render(SpriteBatch batch)
        +dispose()
        +getX()
        +getWidth()
    }

    class RoadMark {
        -float x, y, speed, scale
        -BitmapFont font
        +update(float delta)
        +render(SpriteBatch batch)
        +isOffScreen()
        +dispose()
    }

    DinoGame --> GameScreen
    GameScreen --> World
    World --> Player
    World --> Obstacle
    World --> Moon
    World --> Star
    World --> RoadMark
    Obstacle --> DinoGame
    Player --> DinoGame
    GameScreen --> AssetLoader
