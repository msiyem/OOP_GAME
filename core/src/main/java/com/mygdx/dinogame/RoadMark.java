package com.mygdx.dinogame;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import java.util.Random;

public class RoadMark {
    private float x, y;
    private float speed;
    private float scale;
    private static final String SYMBOL = "-";
    private BitmapFont font;
    private static final Random random = new Random();

    public RoadMark(float screenWidth, float speed) {
        this.x = screenWidth;
        this.y = 10 + random.nextInt(80);
        this.scale = 0.8f + random.nextFloat() * 2f;
        this.speed = speed;
        this.font = new BitmapFont();
        font.getData().setScale(scale);
    }

    public void update(float delta) {
        x -= speed * delta;
    }

    public void render(SpriteBatch batch) {
        font.draw(batch, SYMBOL, x, y);
    }

    public boolean isOffScreen() {
        return x < -20;
    }

    public void dispose() {
        font.dispose();
    }
}
