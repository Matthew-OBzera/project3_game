package edu.noctrl.craig.generic;

import android.graphics.Rect;

public class Rasenshuriken extends EnemyBullet {

    private final Rect rect = new Rect(406,279,471,377);

    public Rasenshuriken(Stage stage) {
        super(stage);
        damage = 2;
        this.penetration = 1;
        this.collidesWith = Collision.SolidPlayer;
        this.substance = GameObject.Collision.SolidAI;
        this.speed = 700;
    }

    @Override
    public Rect getSource() {
        return rect;
    }

    @Override
    public Point3F getScale() {
        return new Point3F(.5f,.5f,1);
    }

    @Override
    public void cull() {

    }
}
