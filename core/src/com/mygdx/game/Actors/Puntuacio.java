package com.mygdx.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Puntuacio extends Actor {

    public int punts;
    public int cops;
    private BitmapFont font;

    public Puntuacio(BitmapFont font) {
        this.font = font;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "Puntos: " + punts, getX(), getY());
        font.draw(batch,"Puntos de vida nave amiga: " + cops, getX() + 150, getY());
    }
}
