package com.mygdx.game.Actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.game.Pantalles.PantallaPrincipalJoc.generarnumRandom;


public class Enemiga extends Nau implements Runnable {

    private Texture textureEnemiga;
    private static final float velocitat = 2, numrandom = 4, tempsmoviment = (float) 0.1;
    private float time;
    private float widthnau;
    private float heightnau;
    private float random;
    private Sprite spriteEnemiga;
    Thread t;

    public Enemiga(){
        t = new Thread(this);
        t.start();
        textureEnemiga = new Texture("nau2.png");
        TextureRegion textureRegionEnemiga = new TextureRegion(textureEnemiga, 64, 64);
        spriteEnemiga = new Sprite(textureRegionEnemiga,0,0, textureRegionEnemiga.getRegionWidth(), textureRegionEnemiga.getRegionHeight());
        setSize(spriteEnemiga.getWidth(),spriteEnemiga.getHeight());
        videsRestants = 1;
        puntsVida = 10;
        frequencia = (float) 0.3;
        viu = false;
        widthnau = getWidth();
        heightnau = getHeight();
        random = (int) (Math.random() * (numrandom)) + 1;
        float posiciox = generarnumRandom(widthnau, width-widthnau);
        float posicioy = generarnumRandom(40, height-heightnau);
        setPosition(posiciox,posicioy);
    }


    public void dispose(){
        textureEnemiga.dispose();
    }

    public void start(){
        this.t.start();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        spriteEnemiga.setPosition(getX(),getY());
        spriteEnemiga.draw(batch);
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        run();
    }

    @Override
    public void run() {
        try {
            moviment();
            /*time += Gdx.app.getGraphics().getDeltaTime();
            if (time>=tempsmoviment){
                movimentX();
                movimentY();
                time = 0;
            }*/

        } catch (Exception e){
            System.out.println(e.toString());
        }
    }

    private void moviment(){
        time += Gdx.app.getGraphics().getDeltaTime();
        if (time>=tempsmoviment){
            float posx = 0, posy = 0, random = (int) (Math.random() * (numrandom)) + 1;
            float heightnau = getHeight();
            float posynau = getY();
            float posxnau = getX();
            float widthnau = getWidth();

            if (random == 1){
                if ((posxnau > 0 || widthnau + posxnau < width) || posxnau <= 0){
                    posx = velocitat;
                }
            }
            else if (random == 2){
                if ((posxnau > 0 && widthnau + posxnau < width) || widthnau + posxnau >= width){
                    posx = velocitat * -1;
                }
            }
            else if (random == 3){
                if ((posynau > 40 && heightnau + posynau < height) || posynau <= 40){
                    posy = velocitat;
                }
            }
            else if (random == 4){
                if ((posynau > 40 && heightnau + posynau < height) || heightnau + posynau >= height){
                    posy = velocitat * -1;
                }
            }
            setX(posxnau + posx);
            setY(posynau + posy);
            time = 0;
        }
    }

    /*private void moviment(){
        time += Gdx.app.getGraphics().getDeltaTime();
        if (time>=tempsmoviment){
            float posx = 0, posy = 0, random = (int) (Math.random() * (numrandom)) + 1;
            float heightnau = getHeight();
            float posynau = getY();
            float posxnau = getX();
            float widthnau = getWidth();

            if (posxnau >0  && widthnau + posxnau <width){
                if (random == 1){
                    posx = velocitat;
                }
                else if (random == 2){
                    posx = velocitat * -1;
                }
                else{
                    if (posynau >40 && heightnau+ posynau <height) {
                        if (random == 3) {
                            posy = velocitat;
                        } else if (random == 4) {
                            posy = velocitat * -1;
                        }
                    }
                    else if (posynau <=40){
                            posy = velocitat;
                    }
                    else if (heightnau+ posynau >=height){
                            posy = velocitat * -1;
                    }
                }
            }
            else if (posxnau <=0){
                if (random == 1){
                    posx = velocitat;
                }
                else {
                    if (posynau >40 && heightnau+ posynau <height){
                        if (random == 3){
                            posy = velocitat;
                        }
                        else if (random == 4){
                            posy = velocitat * -1;
                        }
                    }
                    else if (posynau <=40){
                            posy = velocitat;
                    }
                    else if (heightnau+ posynau >=height){
                            posy = velocitat * -1;
                    }
                }
            }
            else if (widthnau + posxnau >=width){
                if (random == 2){
                    posx = velocitat*-1;
                }
                else {
                    if (posynau >40 && heightnau+ posynau <height){
                        if (random == 3){
                            posy = velocitat;
                        }
                        else if (random == 4){
                            posy = velocitat * -1;
                        }
                    }
                    else if (posynau <=40){
                        if (random == 3){
                            posy = velocitat;
                        }
                    }
                    else if (heightnau+ posynau >=height){
                        if (random == 4){
                            posy = velocitat * -1;
                        }
                    }
                }
            }
            setX(posxnau + posx);
            setY(posynau + posy);
            time = 0;
        }
    }*/

    private void movimentX(){
        //time += Gdx.app.getGraphics().getDeltaTime();
        //if (time>=tempsmoviment){
            float pos = 0;
            float posicio = getX();
        //System.out.println("Posicio x: " + posicio);
            //float random;
            //random = (int) (Math.random() * (numrandom)) + 1;
            if (posicio>0 && posicio <width - widthnau){ //si la posició de la nau es dins la pantalla
                if (random == 1){
                    pos = velocitat;
                }
                else if (random == 2){
                    pos = velocitat*-1;
                }
            }
            else if (posicio <=0){ //si la posició de la nau es fora per la part esquerra de la pantalla
                pos = velocitat;
            }
            else if (posicio>=width - widthnau){ //si la posició de la nau es fora per la part dreta de la pantalla
                pos = velocitat*-1;
            }
            setX(posicio + pos);
        //time=0;
        //}
    }

    private void movimentY(){
        //time += Gdx.app.getGraphics().getDeltaTime();
        //if (time>=tempsmoviment){
            float pos = 0;
            float posicio = getY();
            //float random;
            //random = (int) (Math.random() * (numrandom)) + 1;
            if (posicio >40 && (heightnau +posicio)<height){ //si la posició de la nau es dins la pantalla
                if (random == 3){
                    pos = velocitat;
                }
                else if (random == 4){
                    pos = velocitat*-1;
                }
            }
            else if (posicio<=40){ //si la posició de la nau es fora per la part esquerra de la pantalla
                    pos = velocitat;
            }
            else if (heightnau + posicio>=height){ //si la posició de la nau es fora per la part dreta de la pantalla
                    pos = velocitat*-1;
            }
            setY(posicio + pos);
           // time=0;
        //}
    }

}
