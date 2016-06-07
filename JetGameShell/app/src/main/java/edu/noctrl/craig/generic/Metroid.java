package edu.noctrl.craig.generic;

import android.graphics.Rect;

public class Metroid extends Enemy {

    private final Rect rect = new Rect(12,300,100,379);
    public Metroid(Stage stage) {
        super(stage);
        this.speed = 50;
        this.health = 20;
        this.timeToAdd = .5;
        this.pointWorth = 5;

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
        EnemyBullet eBullet = new EnergyBullet(stage);
        eBullet.position.X = this.position.X;
        eBullet.position.Y = this.position.Y;
        stage.addObject(eBullet);
        stage.sounds.playSound(SoundManager.IMPACT_ONE);
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
        return new Point3F(.4F,.4F,1);
    }

    @Override
    public void cull() {

    }
}
