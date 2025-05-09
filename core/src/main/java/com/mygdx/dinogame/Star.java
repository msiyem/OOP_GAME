package com.mygdx.dinogame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;
import java.util.Random;

public class Star {
    private Texture texture;
    private float x, y;
    private float speed;
    private float scale;
    private static final Random random = new Random();

    public Star(String texturePath, float speed) {
        this.texture = new Texture(texturePath);
        this.speed = speed;
        initPositionAndScale();
    }

    private void initPositionAndScale() {
        this.x = Gdx.graphics.getWidth(); // right edge
        this.y = 250 + random.nextInt(300); // always above moon
        this.scale = 0.05f + random.nextFloat() * 0.15f; // random scale (0.2 - 0.5)
    }

    public void update(float delta) {
        x -= speed * delta;
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y, texture.getWidth() * scale, texture.getHeight() * scale);
    }

    public void dispose() {
        texture.dispose();
    }

    public float getX() {
        return x;
    }

    public float getWidth() {
        return texture.getWidth() * scale;
    }
}
