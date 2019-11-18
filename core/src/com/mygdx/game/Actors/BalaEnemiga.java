package com.mygdx.game.Actors;

public class BalaEnemiga extends Bala implements Runnable {

    private Thread t;

    public BalaEnemiga() {
        super();
        t = new Thread();

    }

    public void start(){
        this.t.start();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        run();
    }

    @Override
    public void run() {
        float x = getX(), y = getY();
        setX(x);
        setY(y - velocitat);
    }
}
