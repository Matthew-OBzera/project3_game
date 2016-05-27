package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by Kyle on 5/26/2016.
 */
public class Orochimaru extends Enemy {

    private final Rect rect = new Rect(333, 270, 398, 386);
    public Orochimaru(Stage stage) {
        super(stage);
        this.speed = 500;
        this.health = 60;
        this.timeToAdd = 1;
        this.pointWorth = 15;
        this.substance = Collision.SolidAI;
        if(stage.callsForEnemyMovement)
        {
            new EnemyMovementController(this);
        }
        if(stage.callsForEnemyFire)
        {
            new EnemyFireController(this);
        }
    }

    @Override
    public void move(Point3F p) {

    }

    @Override
    public void fire() {

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
