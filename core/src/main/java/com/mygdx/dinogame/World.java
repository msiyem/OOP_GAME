package com.mygdx.dinogame;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class World {
    private DinoGame game;
    private Player player;
    private Array<Obstacle> obstacles;
    private Moon moon;
    private float obstacleTimer = 0;
    private final float OBSTACLE_INTERVAL = 2f;
    private Texture ground;
    private float groundOffsetX = 0;
    private ShapeRenderer shapeRenderer;
    private int score = 0;
    private long startTime;
    private BitmapFont font;
    private Preferences prefs;
    private int highScore;
    private Sound highScoreSound;
    private Sound gameOverSound;

    // ✅ NEW: Speed multiplier logic
    private float speedMultiplier = 1.0f;
    private final float SPEED_INCREASE_RATE = 0.05f;

    public World(DinoGame game) {
        this.game = game;
        player = new Player(game);
        moon = new Moon("moon.png",250,20f,2f);
        obstacles = new Array<>();
        ground = new Texture("ground.png");
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        startTime = TimeUtils.millis();
        prefs = Gdx.app.getPreferences("DinoPrefs");
        highScore = prefs.getInteger("highScore", 0);
//        highScoreSound= Gdx.audio.newSound(Gdx.files.internal("highscoreSound.wav"));
        gameOverSound= Gdx.audio.newSound(Gdx.files.internal("death.wav"));
    }

    public void update(float delta) {
        if (!player.isAlive()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                game.setScreen(new GameScreen(game)); // Restart game
            }
            return;
        }
        moon.update(delta);
        player.update(delta);
        obstacleTimer += delta;

        if (obstacleTimer > OBSTACLE_INTERVAL) {
            obstacles.add(new Obstacle(game, speedMultiplier)); // ✅ Pass current speed multiplier
            obstacleTimer = 0;
        }

        for (Obstacle obs : obstacles) {
            obs.update(delta);
            if (obs.getBounds().overlaps(player.getBounds()) && player.isAlive()) {
                player.setAlive(false);
                gameOverSound.play(1.0f);

                if (score > highScore) {
                    highScore = score;
                    prefs.putInteger("highScore", highScore);
                    prefs.flush();
                }
            }
        }

        // Clean up off-screen obstacles
        for (int i = obstacles.size - 1; i >= 0; i--) {
            if (obstacles.get(i).getBounds().x + 64 < 0) {
                obstacles.removeIndex(i);
            }
        }

        // ✅ Move ground with speed multiplier
        groundOffsetX -= 200 * delta * speedMultiplier;
        if (groundOffsetX <= -ground.getWidth()) {
            groundOffsetX = 0;
        }

        if (player.isAlive()) {
            score = (int)((TimeUtils.millis() - startTime) / 100);
            speedMultiplier += SPEED_INCREASE_RATE * delta; // ✅ Increase speed over time
        }
    }

    public void render() {
        game.batch.begin();
        moon.render(game.batch);

        game.batch.draw(ground, groundOffsetX, 0);
        game.batch.draw(ground, groundOffsetX + ground.getWidth(), 0);

        for (Obstacle obs : obstacles) {
            obs.render();
        }

        player.render();
        font.setColor(0,0,0,0.5f);
        font.draw(game.batch, "Score: " + score, 20, 560);
        font.draw(game.batch, "High Score: " + highScore, 20, 540);

        if (!player.isAlive()) {
            font.getData().setScale(2f);
            font.draw(game.batch, "Game Over", 350, 400);
            font.draw(game.batch, "Press R to Restart", 320, 350);
            font.getData().setScale(1f);

        }

        game.batch.end();

        // ✅ Slightly raise road line for visual alignment
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.2f, 0.2f, 0.2f, 1);
        shapeRenderer.rect(0, 99, Gdx.graphics.getWidth(), 6); // visually lines up with feet
        shapeRenderer.end();
    }

    public void dispose() {
        ground.dispose();
        shapeRenderer.dispose();
        player.dispose();
        moon.dispose();
        for (Obstacle obs : obstacles) {
            obs.dispose();
        }
        font.dispose();
    }
}
