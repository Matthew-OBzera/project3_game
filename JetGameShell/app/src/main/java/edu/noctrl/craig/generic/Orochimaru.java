package edu.noctrl.craig.generic;

import android.graphics.Rect;

public class Orochimaru extends Enemy {

    private final Rect rect = new Rect(333, 270, 398, 386);
    public Orochimaru(Stage stage) {
        super(stage);
        this.speed = 60;
        this.health = 60;
        this.timeToAdd = 1;
        this.pointWorth = 15;

        if(stage.callsForEnemyMovement)
        {
            new EnemyMovementController(this, stage);
        }
        if(stage.callsForEnemyFire)
        {
            new EnemyFireController(this);
        }
    }

    @Override
    public void move(Point3F p) {
        this.baseVelocity = p;
        this.updateVelocity();
    }

    @Override
    public void fire() {
        EnemyBullet eBullet = new Rasenshuriken(stage);
        eBullet.position.X = this.position.X;
        eBullet.position.Y = this.position.Y;
        stage.addObject(eBullet);
        stage.sounds.playSound(SoundManager.RASENSHURIKEN);
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
        return new Point3F(.42F,.42F,1);
    }

    @Override
    public void cull() {

    }
}
