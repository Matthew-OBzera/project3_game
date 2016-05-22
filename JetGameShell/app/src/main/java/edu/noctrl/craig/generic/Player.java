package edu.noctrl.craig.generic;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by mobze on 5/21/2016.
 */
public class Player extends GameSprite {

    static final Rect rect = new Rect(0,0,64,64);


    public Player(World theWorld) {
        super(theWorld);
        this.speed = 100;
        this.baseVelocity = new Point3F(1,1,0);
        this.updateVelocity();
    }

    @Override
    public Rect getSource() {
        return rect;
    }

    @Override
    public Point3F getScale() {
        return new Point3F(2,2,2);
    }

    @Override
    public void cull() {

    }
}
