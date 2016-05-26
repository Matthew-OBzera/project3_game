package edu.noctrl.craig.generic;

import android.graphics.Rect;

public abstract class Bullet extends GameSprite {

    protected int damage;
    protected int penetration;

    public Bullet(Stage stage) {
        super(stage);
    }

    @Override
    public abstract Rect getSource();

    @Override
    public abstract Point3F getScale();

    @Override
    public abstract void cull();
}
