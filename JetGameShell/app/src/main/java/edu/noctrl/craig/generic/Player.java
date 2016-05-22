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

/**
 * Matthew OBzera
 */
public class Player extends GameSprite {

    static final Rect rect = new Rect(0,0,64,64);


    public Player(World theWorld) {
        super(theWorld);
        this.speed = 200;
        this.position.X = 128;
        this.position.Y = theWorld.TARGET_HEIGHT/2;
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
