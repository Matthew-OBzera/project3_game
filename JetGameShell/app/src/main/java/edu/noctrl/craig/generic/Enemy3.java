package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by gerardopaleo on 5/25/16.
 */
public class Enemy3 extends Enemy {
    private final Rect rect = new Rect(12,300,100,379);
    public Enemy3(Stage stage) {
        super(stage);

        this.speed = 20;
        this.health = 20;
        this.timeToAdd = .5;
        this.pointWorth = 5;
    }

    @Override
    public void move(Point3F p) {

    }

    @Override
    public void fire() {
        EnemyBullet eBullet = new EnemyBullet(stage);
        eBullet.position.X = this.position.X;
        eBullet.position.Y = this.position.Y;
        stage.addObject(eBullet);
        float dx = stage.player.position.X - this.position.X;
        float dy = stage.player.position.Y - this.position.Y;
        float h = (float) Math.sqrt((dx*dx) + (dy*dy));
        eBullet.baseVelocity.X = dx/h;
        eBullet.baseVelocity.Y = dy/h;
        eBullet.updateVelocity();
    }

    @Override
    public Rect getSource() {
        return rect;
    }

    @Override
    public Point3F getScale() {
        return new Point3F(.55F,.55F,1);
    }

    @Override
    public void cull() {

    }
}
