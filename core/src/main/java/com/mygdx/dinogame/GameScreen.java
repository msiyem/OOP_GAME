package com.mygdx.dinogame;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.Gdx;

public class GameScreen implements Screen {
    private final DinoGame game;
    private World world;

    public GameScreen(DinoGame game) {
        this.game = game;
        world = new World(game);
    }

    @Override
    public void show() {}

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        world.update(delta);
        world.render();
    }

    @Override
    public void resize(int width, int height) {

    }
    @Override
    public void pause() {}
    @Override
    public void resume() {}
    @Override
    public void hide() {}

    @Override
    public void dispose() {
        world.dispose();
    }
}
