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
        this.x = Gdx.graphics.getWidth();
        this.y = 250 + random.nextInt(300);
        this.scale = 0.08f + random.nextFloat() * 0.2f;

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
