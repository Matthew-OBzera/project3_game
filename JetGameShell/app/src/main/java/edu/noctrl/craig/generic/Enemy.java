package edu.noctrl.craig.generic;

import android.graphics.Rect;

public abstract class Enemy extends GameSprite {

    protected int health;
    protected double timeToAdd;
    protected int pointWorth;
    final Stage stage;

    public Enemy(Stage stage) {
        super(stage);
        this.stage = stage;
        this.substance = GameObject.Collision.SolidAI;
        this.collidesWith = Collision.SolidAI;
    }

    public abstract void move(Point3F p);

    public abstract void fire();

    @Override
    public void cull() {

    }

    @Override
    public void collision(GameObject object) {
        if (object instanceof Enemy3){
            Point3F newVel = this.baseVelocity.mult(-1);
            //this.changeBaseVelocity(newVel);
            //GET BACK TO THIS
        }else if(object instanceof PlayerBullet){

            PlayerBullet bullet = (PlayerBullet) object;
            bullet.kill();
            this.health -= bullet.damage;
            stage.recordEnemyHit();
            if(this.health <= 0)
            {
                this.kill();
                stage.recordEnemyKilled(this);
            }
        }
    }

    @Override
    public abstract Rect getSource();

    @Override
    public abstract Point3F getScale();
}
