package com.mygdx.game.Actors;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;

public abstract class Bala extends Actor {

    private Texture textureBala;
    private TextureRegion textureRegionBala;
    private Sprite spriteBala;
    protected static final int velocitat = 5;
    //private Thread t;
    //private Sprite spriteAmiga;

    public Bala(){
        //t = new Thread();
        //t.start();
        textureBala = new Texture("tret.png");
        textureRegionBala = new TextureRegion(textureBala,4,4);
        spriteBala = new Sprite(textureRegionBala,0,0,textureRegionBala.getRegionWidth(),textureRegionBala.getRegionHeight());
        setSize(spriteBala.getWidth(),spriteBala.getHeight());
    }

    /*public void start(){
        this.t.start();
    }*/

    @Override
    public void draw(Batch batch, float parentAlpha) {
        spriteBala.setPosition(getX(),getY());
        spriteBala.draw(batch);
        //batch.draw(textureRegionBala,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),getScaleX(),getScaleY(),getRotation());
    }

    @Override
    public void act(float delta) {
    }

    public abstract void start();


}
