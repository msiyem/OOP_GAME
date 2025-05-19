package com.mygdx.dinogame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;

public class Player {
    private Texture textureRun1, textureRun2, textureJump;
    private float x, y;
    private float velocityY = 0;
    private final float gravity = -1000;
    private final float jumpVelocity = 500;
    private Rectangle bounds;
    private final float width = 64, height = 64;
    private boolean isAlive = true;
    private DinoGame game;
    private float animationTimer = 0;
    private Sound jumpSound;
    private boolean hasPlayedDeathSound = false;

    public Player(DinoGame game) {
        this.game = game;
        textureRun1 = new Texture("dino_run1.png");
        textureRun2 = new Texture("dino_run2.png");
        textureJump = new Texture("dino_jump.png");
        x = 100;
        y = 100;
        bounds = new Rectangle(x, y, width, height);
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));

    }

    public void update(float delta) {

        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE) && y <= 100) {
            velocityY = jumpVelocity;
            jumpSound.play(0.15f);
        }

        velocityY += gravity * delta;
        y += velocityY * delta;

        if (y < 100) {
            y = 100;
            velocityY = 0;
        }

        bounds.setPosition(x, y);
        animationTimer += delta;
    }

    public void render() {
        Texture currentFrame;
        if (y > 100) {
            currentFrame = textureJump;
        } else {
            currentFrame = (int)(animationTimer * 10) % 2 == 0 ? textureRun1 : textureRun2;
        }
        game.batch.draw(currentFrame, x, y, width, height);
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
        if (!alive) {
            hasPlayedDeathSound = false;
        }
    }

    public void dispose() {
        textureRun1.dispose();
        textureRun2.dispose();
        textureJump.dispose();
        jumpSound.dispose();

    }
}
