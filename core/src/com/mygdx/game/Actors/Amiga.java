package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;

public class Amiga extends Nau {

    private Texture textureAmiga;
    private Sprite spriteAmiga;

    public Amiga(){
        super();
        textureAmiga = new Texture("nau1.png");
        TextureRegion textureRegionAmiga = new TextureRegion(textureAmiga, 64, 32);
        spriteAmiga = new Sprite(textureRegionAmiga,0,0, textureRegionAmiga.getRegionWidth(), textureRegionAmiga.getRegionHeight());
        setSize(spriteAmiga.getWidth(),spriteAmiga.getHeight());
        videsRestants = 4;
        puntsVida = 100;
        frequencia = (float) 0.1;
        viu = true;
    }

    public void dispose(){
        textureAmiga.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        spriteAmiga.setPosition(getX(),getY());
        spriteAmiga.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }
    public void entradaTeclat() {

        int pos=0;
        int posx = 0, posy=0;
        int velocitat = 5;

        boolean dreta = Gdx.input.isKeyPressed(Input.Keys.D)
                || Gdx.input.isKeyPressed(Input.Keys.RIGHT);
        boolean esquerra = Gdx.input.isKeyPressed(Input.Keys.A)
                || Gdx.input.isKeyPressed(Input.Keys.LEFT);
        boolean amunt = Gdx.input.isKeyPressed(Input.Keys.UP);
        boolean abaix = Gdx.input.isKeyPressed(Input.Keys.DOWN);

        //float posicio = getX();
        //heightnau = getHeight();
        //posynau = getY();
        float posxnau = getX();
        float widthnau = getWidth();


        if ((posxnau >0 && (widthnau + posxnau)<width)){
            if (dreta && !esquerra){ //si pulsa dreta
                setVelocitat(velocitat);
                pos = velocitat;
            }
            else if (esquerra && !dreta){ //si pulsa esquerra
                setVelocitat(velocitat*-1);
                pos = velocitat*-1;
            }
        }
        else if (posxnau <=0){
            if (dreta && !esquerra){ //si pulsa dreta
                setVelocitat(velocitat);
                pos = velocitat;
            }
        }
        else if (widthnau + posxnau >= width){
            if (esquerra && !dreta){ //si pulsa esquerra
                setVelocitat(velocitat*-1);
                pos = velocitat*-1;
            }
        }
        addAction(Actions.moveBy(pos,0));
    }

}
