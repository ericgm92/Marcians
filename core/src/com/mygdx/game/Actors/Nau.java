package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Nau extends Actor {

    protected int puntsVida;
    protected int videsRestants;
    protected float frequencia;
    protected boolean viu;
    protected int velocitat;
    protected float width = Gdx.graphics.getWidth(), height = Gdx.graphics.getHeight();

    public Nau() {
    }


    public void setVelocitat(int velocitat) {
        this.velocitat = velocitat;
    }


    public int getPuntsVida() {
        return puntsVida;
    }

    public boolean isViu() {
        return viu;
    }

    public void setViu(boolean viu) {
        this.viu = viu;
    }

    public void setPuntsVida(int puntsVida) {
        this.puntsVida = puntsVida;
    }

    public int getVidesRestants() {
        return videsRestants;
    }

    public void setVidesRestants(int videsRestants) {
        this.videsRestants = videsRestants;
    }

    public float getFrequencia() {
        return frequencia;
    }

}
