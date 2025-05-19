package com.mygdx.dinogame;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import java.util.Random;

public abstract class Obstacle{
    protected Texture texture;
    protected float x,y;
    protected float width,height;
    protected  float speed;
    protected Rectangle bounds;
    protected DinoGame game;
    public Obstacle(DinoGame game, float speedMultiplier){
        this.game=game;
        this.speed=speedMultiplier *200;
        this.x=800;
        this.y=101;
    }
    public void setTextureAndSize(String texturePath, float width, float height){
        this.texture= new Texture(texturePath);
        this.width=width;
        this.height=height;
        this.bounds= new Rectangle(x+7.5f,y,width-15,height);
    }
    public void update(float delta){
        x-=speed*delta;
        bounds.setPosition(x,y);
    }
    public void render(){
        game.batch.draw(texture,x,y,width,height);
    }
    public Rectangle getBounds(){
        return bounds;
    }
    public void dispose(){
        texture.dispose();
    }
}
