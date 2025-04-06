package com.mygdx.dinogame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

public class Obstacle {
    private Texture texture;
    private float x, y;
    private float width, height;
    private float speed;
    private Rectangle bounds;
    private DinoGame game;

    public Obstacle(DinoGame game, float speedMultiplier) {
        this.game = game;
        Random rand = new Random();
        int type = rand.nextInt(4); // 0 to 3 for 4 types

        switch (type) {
            case 0: // Single small cactus
                texture = new Texture("cactus_single.png");
                width = 40;
                height = 60;
                break;
            case 1: // Double small cactus
                texture = new Texture("cactus_double.png");
                width = 60;
                height = 60;
                break;
            case 2: // Triple small cactus
                texture = new Texture("cactus_triple.png");
                width = 80;
                height = 60;
                break;
            case 3: // Big cactus
                texture = new Texture("cactus_big.png");
                width = 60;
                height = 80;
                break;
        }

        x = 800;
        y = 101; // ✅ matches ground line
        speed = 200 * speedMultiplier;

        // ✅ slightly smaller hitbox
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
