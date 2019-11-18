package com.mygdx.game.Actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Vides extends Actor {

    public int vides;
    private BitmapFont font;

    public Vides(BitmapFont font) {
        this.font = font;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        font.draw(batch, "Vidas nave: " + vides, getX(), getY());
    }
}
