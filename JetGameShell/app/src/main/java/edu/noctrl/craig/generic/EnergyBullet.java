package edu.noctrl.craig.generic;

import android.graphics.Rect;

public class EnergyBullet extends EnemyBullet {

    private final Rect rect = new Rect(199,332,234,367);

    public EnergyBullet(Stage stage) {
        super(stage);
        damage = 1;
        this.penetration = 1;
        this.speed = 550;

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
