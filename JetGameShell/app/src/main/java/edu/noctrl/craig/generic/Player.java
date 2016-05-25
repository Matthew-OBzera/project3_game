package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Matthew OBzera
 */
public class Player extends GameSprite {

    static final Rect rect = new Rect(0,0,64,64);


    public Player(World theWorld) {
        super(theWorld);
        this.speed = 200;
        this.position.X = 128;
        this.position.Y = theWorld.height/2;
    }

    @Override
    public Rect getSource() {
        return rect;
    }

    @Override
    public Point3F getScale() {
        return new Point3F(1,1,1);
    }

    @Override
    public void cull() {

    }
}
