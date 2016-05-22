package edu.noctrl.craig.generic;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by mobze on 5/22/2016.
 */
public abstract class Bullet extends GameSprite {

    protected int damage;
    protected int penetration;

    public Bullet(World theWorld) {
        super(theWorld);
    }

    @Override
    public abstract Rect getSource();

    @Override
    public abstract Point3F getScale();

    @Override
    public abstract void cull();
}
