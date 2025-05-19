package com.mygdx.dinogame;

public class BigCactus extends Obstacle {
    public BigCactus(DinoGame game, float speedMultiplier) {
        super(game,speedMultiplier);
        setTextureAndSize("cactus_big.png",60,70);
    }
}
