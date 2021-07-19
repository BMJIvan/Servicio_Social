package com.mygdx.prueba;

import com.badlogic.gdx.Game;

public class MainGame extends Game {

    private Box2dScreen Pan1;

    @Override
    public void create() {
        Pan1=new Box2dScreen(this);
        setScreen(Pan1);
    }

    @Override
    public void dispose() {
        Pan1.dispose();
    }

    @Override
    public void resume() {
        setScreen(Pan1);
    }

    @Override
    public void pause() {
        Pan1.dispose();
    }
}