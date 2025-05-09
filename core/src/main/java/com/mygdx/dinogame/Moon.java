package com.mygdx.dinogame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Gdx;

public class Moon {
    private Texture texture;
    private float x, y;
    private float speed;
    private boolean offScreen;
    private float delay;
    private float delayTimer;

    public Moon(String texturePath, float startY, float speed, float resetDelay) {
        this.texture = new Texture(texturePath);
        this.x = Gdx.graphics.getWidth()-100;
        this.y = startY;
        this.speed = speed;
        this.delay = resetDelay;
        this.delayTimer = 0;
        this.offScreen = false;
    }

    public void update(float delta) {
        if (!offScreen) {
            x -= speed * delta;
            if (x + texture.getWidth() < 0) {
                offScreen = true;
                delayTimer = 0;
            }
        } else {
            delayTimer += delta;
            if (delayTimer >= delay) {
                x = Gdx.graphics.getWidth();
                offScreen = false;
            }
        }
    }

    public void render(SpriteBatch batch) {
        batch.setColor(1,1,1,0.2f);
        batch.draw(texture, x, y);
        batch.setColor(1,1,1,1);
    }

    public void dispose() {
        texture.dispose();
    }
}
