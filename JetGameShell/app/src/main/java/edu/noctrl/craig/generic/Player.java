package edu.noctrl.craig.generic;

import android.graphics.Rect;
import android.os.Handler;

public class Player extends GameSprite {

    static final Rect stationaryStanding = new Rect(65, 397, 104, 450);
    static final Rect firePose = new Rect(185, 463, 240, 510);
    private Rect toPass;
    protected boolean canFire = true;

    protected int bulletCount = 3;
    protected int health = 10;

    public Player(World theWorld) {
        super(theWorld);
        this.speed = 200;
        this.substance = Collision.SolidPlayer;
        this.collidesWith = Collision.SolidAI;
        this.position.X = 128;
        this.position.Y = theWorld.height / 2;
        this.toPass = stationaryStanding;
    }

    @Override
    public Rect getSource() {
        return toPass;
    }

    @Override
    public Point3F getScale() {
        return new Point3F(1, 1, 1);
    }

    protected void setFirePose() {
        this.toPass = firePose;
        getSource();
    }

    protected void setStationaryStanding() {
        this.toPass = stationaryStanding;
        getSource();
    }

    protected void reload() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                canFire = true;
            }
        }, 200);
        bulletCount = 3;
    }


    @Override
    public void cull() {
    }
}
