package edu.noctrl.craig.generic;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.deitel.cannongame.JetGameFragment;
import com.deitel.cannongame.JetGameView;

import java.util.concurrent.TimeUnit;

/**
 * Matthew OBzera
 */
public class Player extends GameSprite {

    static final Rect stationaryStanding = new Rect(65,397,104,450);
    static final Rect firePose = new Rect(185,463,240,510);
    private Rect toPass;
    protected int bulletCount = 3;

    public Player(World theWorld) {
        super(theWorld);
        this.speed = 200;
        this.position.X = 128;
        this.position.Y = theWorld.TARGET_HEIGHT/2;
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
