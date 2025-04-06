package com.mygdx.dinogame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

public class Obstacle {
    private Texture texture;
    private float x, y;
    private final float width = 48, height = 64; // ✅ smaller cactus size
    private float speed;
    private Rectangle bounds;
    private DinoGame game;

    public Obstacle(DinoGame game, float speedMultiplier) {
        this.game = game;
        Random rand = new Random();
        int type = rand.nextInt(2);
        texture = new Texture(type == 0 ? "cactus_small.png" : "cactus_large.png");

        x = 800;
        y = 101; // ✅ lines up with dino and ground

        speed = 200 * speedMultiplier; // ✅ speed increases with time

        // ✅ smaller hitbox for easier jumping
        bounds = new Rectangle(x + 8, y, width - 16, height);
    }

    public void update(float delta) {
        x -= speed * delta;
        bounds.setPosition(x + 8, y);
    }

    public void render() {
        game.batch.draw(texture, x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
