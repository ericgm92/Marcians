package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.mygdx.game.Pantalles.PantallaPrincipalJoc;

public class Joc extends Game {
    private BitmapFont font;

    @Override
    public void create() {
        setScreen(new PantallaPrincipalJoc(this));

    }
}
