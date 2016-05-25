package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by gerardopaleo on 5/21/16.
 */
public class Enemy2 extends Enemy {

    private final Rect rect = new Rect(12,300,100,379);
    public Enemy2(World theWorld) {
        super(theWorld);
        this.speed = 200;
        this.health = 20;
        this.timeToAdd = .5;
        this.substance = Collision.SolidAI;
    }

    @Override
    public Rect getSource() {
        return rect;
    }

    @Override
    public Point3F getScale() {
        return new Point3F(.55F,.55F,1);
    }

    @Override
    public void cull() {

    }
}
