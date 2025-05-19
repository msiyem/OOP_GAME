package com.mygdx.dinogame;

public class SmallCactusSingle extends Obstacle {
    public SmallCactusSingle(DinoGame game, float speedMultiplier) {
        super(game, speedMultiplier);
        setTextureAndSize("cactus_single.png",40,60);
    }
}
