package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Matthew OBzera
 */
public class Player extends GameSprite {

    static final Rect stationaryStanding = new Rect(65,397,104,450);
    static final Rect firePose = new Rect(185,463,240,510);
    private Rect toPass;
    protected int bulletCount = 3;

    protected int health = 10;

    public Player(World theWorld) {
        super(theWorld);
        this.speed = 200;

        this.collidesWith = Collision.SolidAI;
        this.substance = Collision.SolidPlayer;

        this.position.X = 128;
        this.position.Y = theWorld.height/2;
        this.toPass = stationaryStanding;
    }

    @Override
    public Rect getSource() {
        return toPass;
    }

    @Override
    public Point3F getScale() {
        return new Point3F(1,1,1);
    }

    protected void fireBullet()
    {
        this.toPass = firePose;
        getSource();
    }

    protected void setStationaryStanding()
    {
        this.toPass = stationaryStanding;
        getSource();
    }

    protected void reload()
    {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        bulletCount = 3;
    }
    @Override
    public void cull() {

    }
}
