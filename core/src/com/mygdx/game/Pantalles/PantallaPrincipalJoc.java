package com.mygdx.game.Pantalles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.mygdx.game.Actors.Amiga;
import com.mygdx.game.Actors.BalaAmiga;
import com.mygdx.game.Actors.BalaEnemiga;
import com.mygdx.game.Actors.Enemiga;
import com.mygdx.game.Actors.Puntuacio;
import com.mygdx.game.Actors.Vides;
import com.mygdx.game.Joc;

import java.util.ArrayList;
import java.util.Random;

public class PantallaPrincipalJoc extends PantallaBase {

    public static final float segons = (float) 2.5;
    private static final int numNaus=7;
    private Stage escenari;
    private Amiga nauAmiga;
    private ArrayList<BalaAmiga> balesamigues;
    private ArrayList<BalaEnemiga> balesenemigues;
    private Enemiga[] nausEnemiga = new Enemiga[numNaus];
    private int width = Gdx.graphics.getWidth(),height = Gdx.graphics.getHeight();
    private float time;
    private float tempsbala;
    private float tempsbalaenemiga;
    private Puntuacio punts;
    private Vides vida;
    private static final float posxFont = 20;
    private static final float posYFont = 15;



    public PantallaPrincipalJoc(Joc joc) {
        super(joc);
        balesamigues = new ArrayList<BalaAmiga>();
        balesenemigues = new ArrayList<BalaEnemiga>();
        escenari = new Stage(new ExtendViewport(640,480));
        nauAmiga = new Amiga();
        carregarAmiga(nauAmiga);
        for (int i=0;i<numNaus;i++){
            nausEnemiga[i] = new Enemiga();
            float posx = generarnumRandom(nausEnemiga[0].getWidth(), width-nausEnemiga[0].getWidth());
            float posy = generarnumRandom(40, height-nausEnemiga[0].getHeight());
            //float posx = (int) ((int) (Math.random() * ((width - nausEnemiga[0].getWidth()) - nausEnemiga[0].getWidth())) + nausEnemiga[0].getWidth());
            //float posy = (int) ((int) (Math.random() * ((height - nausEnemiga[0].getHeight()) - nausEnemiga[0].getHeight())) +  (height/2));
            if (i<1) {
                carregarEnemiga(nausEnemiga[i]);
                escenari.addActor(nausEnemiga[i]);
            }
            else {
                for (int j=0;j<numNaus;j++){
                    if (i!=j && nauAmiga.isViu() && nausEnemiga[i].isViu()){
                        if (XocDreta(nausEnemiga[i].getX(),nausEnemiga[i].getWidth(),nausEnemiga[j].getX(),nausEnemiga[j].getWidth())){
                            nausEnemiga[i].setPosition(nausEnemiga[i].getX()-1, nausEnemiga[i].getY());
                        }
                        if (XocEsquerra(nausEnemiga[i].getX(),nausEnemiga[i].getWidth(),nausEnemiga[j].getX(),nausEnemiga[j].getWidth())){
                            nausEnemiga[i].setPosition(nausEnemiga[i].getX()+1, nausEnemiga[i].getY());
                        }
                        if (XocAmunt(nausEnemiga[i].getY(),nausEnemiga[i].getHeight(),nausEnemiga[j].getY(),nausEnemiga[j].getHeight())){
                            nausEnemiga[i].setPosition(nausEnemiga[i].getX(), nausEnemiga[i].getY()-1);
                        }
                        if (XocAbaix(nausEnemiga[i].getY(),nausEnemiga[i].getHeight(),nausEnemiga[j].getY(),nausEnemiga[j].getHeight())){
                            nausEnemiga[i].setPosition(nausEnemiga[i].getX(), nausEnemiga[i].getY()+1);
                        }
                    }
                }
                /*if (Xoc(nausEnemiga[i].getX(),nausEnemiga[i].getY(), nausEnemiga[i].getWidth(), nausEnemiga[i].getHeight(),nausEnemiga[i-1].getX(),nausEnemiga[i-1].getY(), nausEnemiga[i-1].getWidth(), nausEnemiga[i-1].getHeight())){
                    nausEnemiga[i].setPosition(posx,posy);
                }*/
            }
        }
        time = segons;
        punts = new Puntuacio(new BitmapFont());
        vida = new Vides(new BitmapFont());
        punts.setPosition(posxFont,posYFont);
        vida.setPosition(posxFont*25, posYFont);
        punts.cops=nauAmiga.getPuntsVida();
        punts.punts=0;
        vida.vides=4;
        escenari.addActor(punts);
        escenari.addActor(vida);
    }


    @Override
    public void render(float delta) {
        time =  formatearDecimales(time,2);
        time -= delta;
        Gdx.gl.glClearColor(0, 0, 0, 1); //color negre
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        carregarBalaAmiga(nauAmiga);
        for (int i=0;i<numNaus;i++){
            if (nauAmiga.isViu()){
                if (nausEnemiga[i].isViu()){
                    carregarBalaEnemiga(nausEnemiga[i]);
                }
                if (time<=0){
                    carregarEnemiga(nausEnemiga[i]);
                    //System.out.println("Posicio x nau " + i + ": " + nausEnemiga[i].getX());
                    //time = segons;
                }
            }
        }
        escenari.act();
        comprobarBalesAmigues();
        comprobarBalesEnemigues();
        comprobarColisions();
        escenari.draw();
        nauAmiga.entradaTeclat();
    }
    private boolean Xoc(float posX, float posY, float width, float height, float posXX, float posYY, float widthX, float heightY){
        boolean colisio = false;
        if (XocDreta(posX, width, posXX, widthX) || XocEsquerra(posX,width,posXX,widthX)){ //horizontal
            if (XocAmunt(posY,height,posYY,heightY) || XocAbaix(posY,height,posYY,heightY)){ //vertical
                colisio = true;
            }
        }
        else {
            colisio = false;
        }
        return colisio;
    }
    private boolean XocDreta(float posX, float width, float posXX, float widthX){
        boolean dreta;
        if (posXX<=posX+width && (posXX+widthX>=posX+width)){
            dreta = true;
        }
        else {
            dreta = false;
        }
        return dreta;
    }
    private boolean XocEsquerra(float posX, float width, float posXX, float widthX){
        boolean esquerra;
        if ((posX<=posXX+widthX) && (posX+width>=posXX+widthX)){
            esquerra = true;
        }
        else {
            esquerra = false;
        }
        return esquerra;
    }

    private boolean XocAmunt(float posY, float height, float posYY, float heightY){
        boolean amunt;
        if (posYY<=posY+height && (posYY+heightY>=posY+height)){
            amunt = true;
        }
        else {
            amunt = false;
        }
        return amunt;
    }
    private boolean XocAbaix(float posY, float height, float posYY, float heightY){
        boolean abaix;
        if ((posY<=posYY+heightY) && (posY+height>=posYY+heightY)){
            abaix = true;
        }
        else {
            abaix = false;
        }
        return abaix;
    }

    public static float formatearDecimales(float numero, Integer numeroDecimales) {
        return (float) (Math.round(numero * Math.pow(10, numeroDecimales)) / Math.pow(10, numeroDecimales));
    }

    private void comprobarBalesAmigues(){

        for (int i=0; i<balesamigues.size();i++){
            if (balesamigues.get(i).getY()>= escenari.getHeight()){
                balesamigues.get(i).remove();
                balesamigues.remove(i);
            }
        }
    }

    private void comprobarBalesEnemigues(){

        for (int i=0; i<balesenemigues.size();i++){
            if (balesenemigues.get(i).getY() < 40 || balesenemigues.get(i).getY()>escenari.getHeight()){
                balesenemigues.get(i).remove();
                balesenemigues.remove(i);
            }
        }
    }

    private void comprobarColisions(){
        int vides = nauAmiga.getVidesRestants();
        for (int i=0;i<numNaus;i++){
            for (int j=0;j<numNaus;j++){
                if (i!=j && nauAmiga.isViu() && nausEnemiga[i].isViu()){
                    if (XocDreta(nausEnemiga[i].getX(),nausEnemiga[i].getWidth(),nausEnemiga[j].getX(),nausEnemiga[j].getWidth())){
                        nausEnemiga[i].setPosition(nausEnemiga[i].getX()-1, nausEnemiga[i].getY());
                    }
                    if (XocEsquerra(nausEnemiga[i].getX(),nausEnemiga[i].getWidth(),nausEnemiga[j].getX(),nausEnemiga[j].getWidth())){
                        nausEnemiga[i].setPosition(nausEnemiga[i].getX()+1, nausEnemiga[i].getY());
                    }
                    if (XocAmunt(nausEnemiga[i].getY(),nausEnemiga[i].getHeight(),nausEnemiga[j].getY(),nausEnemiga[j].getHeight())){
                        nausEnemiga[i].setPosition(nausEnemiga[i].getX(), nausEnemiga[i].getY()-1);
                    }
                    if (XocAbaix(nausEnemiga[i].getY(),nausEnemiga[i].getHeight(),nausEnemiga[j].getY(),nausEnemiga[j].getHeight())){
                        nausEnemiga[i].setPosition(nausEnemiga[i].getX(), nausEnemiga[i].getY()+1);
                    }
                }
            }
            if (nauAmiga.isViu() && nausEnemiga[i].isViu() && Xoc(nauAmiga.getX(),nauAmiga.getY(),nauAmiga.getWidth(),nauAmiga.getHeight(),
                    nausEnemiga[i].getX(),nausEnemiga[i].getY(), nausEnemiga[i].getWidth(), nausEnemiga[i].getHeight()) ){
                vides--;
                vida.vides = vides;
                nauAmiga.setVidesRestants(vides);
                float posx = (int) ((int) (Math.random() * ((width - (nauAmiga.getWidth()/2)) - (nauAmiga.getWidth()/2))) + (nauAmiga.getWidth()/2));
                nauAmiga.setX(posx);
                nauAmiga.setY(100);
                nauAmiga.setPuntsVida(100);
                punts.cops = 0;
                //System.out.println("Vides restants: " + vides);
                if (vides==0){
                    //System.out.println("Puntuació total:" + total);
                    nauAmiga.setViu(false);
                    //System.out.println("Game Over");
                    nauAmiga.setVisible(false);
                    nausEnemiga[i].remove();
                    for (int a = 0; a<7;a++){
                        nausEnemiga[a].remove();
                    }
                    for (int b=0;b<balesamigues.size();b++){
                        balesamigues.get(b).remove();
                        balesamigues.remove(b);
                    }
                    for (int c=0;c<balesenemigues.size();c++){
                        balesenemigues.get(c).remove();
                        balesenemigues.remove(c);
                    }
                    //nauAmiga.remove();
                }
            }
            else {
                int copsEnemic = nausEnemiga[i].getPuntsVida();
                int copsAmic = nauAmiga.getPuntsVida();
                for (int j=0;j<balesamigues.size();j++){
                    if (nausEnemiga[i].isViu() && Xoc(nausEnemiga[i].getX(),nausEnemiga[i].getY(), nausEnemiga[i].getWidth(), nausEnemiga[i].getHeight(),
                            balesamigues.get(j).getX(),balesamigues.get(j).getY(),balesamigues.get(j).getWidth(),balesamigues.get(j).getHeight())){
                        balesamigues.get(j).remove();
                        balesamigues.remove(j);
                        copsEnemic--;
                        nausEnemiga[i].setPuntsVida(copsEnemic);
                        //System.out.println("Vida de la nau " + i + " : " + copsEnemic);
                        if (nauAmiga.isViu()){
                            punts.punts++;
                            //System.out.println("Punts: " + puntuacio);
                        }
                        if (copsEnemic==0){
                            nausEnemiga[i].setViu(false);
                            nausEnemiga[i].remove();
                            if (time<0){
                                nausEnemiga[i] = new Enemiga();
                                carregarEnemiga(nausEnemiga[i]);
                            }

                        }
                    }
                    else if (!nauAmiga.isViu()) {
                        balesamigues.get(j).remove();
                        balesamigues.remove(j);
                    }
                }
                for (int k=0;k<balesenemigues.size();k++){
                    if (nauAmiga.isViu() && Xoc(nauAmiga.getX(),nauAmiga.getY(), nauAmiga.getWidth(), nauAmiga.getHeight(),
                            balesenemigues.get(k).getX(),balesenemigues.get(k).getY(),balesenemigues.get(k).getWidth(),balesenemigues.get(k).getHeight())){
                        balesenemigues.get(k).remove();
                        balesenemigues.remove(k);
                        copsAmic--;
                        punts.cops = copsAmic;
                        nauAmiga.setPuntsVida(copsAmic);
                        //System.out.println("Vida de la nostra nau: " + copsAmic);
                        if (copsAmic==0){
                            vides--;
                            vida.vides = vides;
                            nauAmiga.setVidesRestants(vides);
                            //System.out.println("Vides restants: " + vides);
                            carregarAmiga(nauAmiga);
                            if (vides==0){
                                //System.out.println("Puntuació total:" + total);
                                nauAmiga.setViu(false);
                                //System.out.println("Game Over");
                                nauAmiga.setVisible(false);
                                punts.cops = 0;
                                nausEnemiga[i].remove();
                                for (int a = 0; a<7;a++){
                                    nausEnemiga[a].remove();
                                }
                                for (int b=0;b<balesamigues.size();b++){
                                    balesamigues.get(b).remove();
                                    balesamigues.remove(b);
                                }
                                for (int c=0;c<balesenemigues.size();c++){
                                    balesenemigues.get(c).remove();
                                    balesenemigues.remove(c);
                                }
                                //nauAmiga.remove();
                            }
                        }
                    }
                }
            }
        }
    }

    private void carregarAmiga(Amiga a){
        //float posx = generarnumRandom(a.getWidth(), width-a.getWidth());
        //float posx = (int) ((int) (Math.random() * ((width - (a.getWidth()/2)) - (a.getWidth()/2))) + (a.getWidth()/2));
        float posx = generarnumRandom(a.getWidth(),width-a.getWidth());
        a.setViu(true);
        a.setPuntsVida(100);
        a.setPosition(posx,100);
        //a.setPosition(0,20);
        escenari.addActor(a);
    }
    private void carregarEnemiga(Enemiga e){
        if (!e.isViu()) {
            e.setViu(true);
            e.setPuntsVida(10);
            escenari.addActor(e);
            float posx = generarnumRandom(e.getWidth(),width-e.getWidth());
            float posy = generarnumRandom(height/2,height-e.getHeight());
            //float posx = (int) ((int) (Math.random() * ((width - (e.getWidth()/2)) - (e.getWidth()/2))) + (e.getWidth()/2));
            //float posy = (int) ((int) (Math.random() * ((height - (e.getHeight()/2)) - (e.getHeight()/2))) + posYFont + (height/2 + (e.getHeight()/2)));
            e.setPosition(posx,posy);
            //e.start();
            time = segons;
        }
    }

    private void carregarBalaAmiga(Amiga a){
        tempsbala += Gdx.app.getGraphics().getDeltaTime();
        if (tempsbala>=a.getFrequencia()){
            BalaAmiga bala = new BalaAmiga();
            bala.setPosition(a.getX() + a.getWidth()/2, a.getY() + a.getHeight()/2);
            escenari.addActor(bala);
            balesamigues.add(bala);
            tempsbala = 0;
        }
    }

    private void carregarBalaEnemiga(Enemiga e){
        tempsbalaenemiga+=Gdx.app.getGraphics().getDeltaTime();
        if (tempsbalaenemiga>=e.getFrequencia()){
            BalaEnemiga bala = new BalaEnemiga();
            escenari.addActor(bala);
            balesenemigues.add(bala);
            bala.setPosition(e.getX()+e.getWidth()/2, e.getY() + e.getHeight()/2);
            tempsbalaenemiga = 0;
        }
    }

    public static float generarnumRandom(float min, float max){
        Random random = new Random();
        float valor = random.nextInt((int) (max-min+1)) + min;
        return valor;
    }
}
