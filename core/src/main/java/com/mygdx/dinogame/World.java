package com.mygdx.dinogame;

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

    // ✅ NEW: Speed multiplier logic
    private float speedMultiplier = 1.0f;
    private final float SPEED_INCREASE_RATE = 0.05f;

    public World(DinoGame game) {
        this.game = game;
        player = new Player(game);
        obstacles = new Array<>();
        ground = new Texture("ground.png");
        shapeRenderer = new ShapeRenderer();
        font = new BitmapFont();
        startTime = TimeUtils.millis();
        prefs = Gdx.app.getPreferences("DinoPrefs");
        highScore = prefs.getInteger("highScore", 0);
    }

    public void update(float delta) {
        if (!player.isAlive()) {
            if (Gdx.input.isKeyJustPressed(Input.Keys.R)) {
                game.setScreen(new GameScreen(game)); // Restart game
            }
            return;
        }

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

        game.batch.draw(ground, groundOffsetX, 0);
        game.batch.draw(ground, groundOffsetX + ground.getWidth(), 0);

        for (Obstacle obs : obstacles) {
            obs.render();
        }

        player.render();

        font.draw(game.batch, "Score: " + score, 20, 460);
        font.draw(game.batch, "High Score: " + highScore, 20, 440);

        if (!player.isAlive()) {
            font.draw(game.batch, "Game Over", 350, 300);
            font.draw(game.batch, "Press R to Restart", 320, 270);
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
        for (Obstacle obs : obstacles) {
            obs.dispose();
        }
        font.dispose();
    }
}
