package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by gerardopaleo on 5/25/16.
 */
public class Enemy3 extends Enemy {
    private final Rect rect = new Rect(12,300,100,379);
    public Enemy3(Stage stage) {
        super(stage);

        //Temporary move this
        this.speed = 20;
        //this.baseVelocity = new Point3F((float) Math.random() * 3,(float) Math.random() * 3,0);
        //this.updateVelocity();

        this.health = 20;
        this.timeToAdd = .5;
        this.pointWorth = 5;
        this.substance = GameObject.Collision.SolidAI;
        this.collidesWith = Collision.SolidAI;
    }

    public void shoot(){
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

    public void changeBaseVelocity(Point3F newVelocity) {

        this.baseVelocity = newVelocity;
        this.updateVelocity();
    }

    @Override
    public void collision(GameObject object){
        if (object instanceof Enemy3){
            Point3F newVel = this.baseVelocity.mult(-1);
            this.changeBaseVelocity(newVel);
        }else if(object instanceof PlayerBullet){
            PlayerBullet bullet = (PlayerBullet) object;
            this.health -= bullet.damage;
            bullet.kill();

            if(this.health <= 0)
            {
                this.kill();
                stage.enemy_count--;
                stage.score += this.pointWorth;
                stage.kills++;

                if(stage.enemy_count <= 0){
                    stage.spawnTimer.cancel();
                    stage.listener.onGameOver(false);
                }
            }
        }
    }
}
