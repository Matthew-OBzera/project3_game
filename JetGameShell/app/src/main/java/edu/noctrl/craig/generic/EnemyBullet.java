package edu.noctrl.craig.generic;

import android.graphics.Rect;

/**
 * Created by gerardopaleo on 5/25/16.
 */
public class EnemyBullet extends Bullet {

    private final Rect rect = new Rect(118,310,182,370);
    Stage stage;
    public EnemyBullet(Stage stage) {
        super(stage);
        damage = 2;
        this.penetration = 1;
        //this.collidesWith = GameObject.Collision.;
        this.substance = GameObject.Collision.SolidAI;
        this.speed = 600;
        this.stage = stage;

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

    @Override
    public void collision(GameObject object){
        if (object instanceof PlayerBullet){
            object.kill();
            this.kill();
        }else if( object instanceof Player){
            this.kill();
            --stage.player.health;
            if (stage.player.health <= 0){
                stage.spawnTimer.cancel();
                stage.listener.onGameOver(true);
            }
        }

    }
}
