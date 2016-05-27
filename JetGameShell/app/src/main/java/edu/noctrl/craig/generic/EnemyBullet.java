package edu.noctrl.craig.generic;

import android.graphics.Rect;

public abstract class EnemyBullet extends Bullet {

    Stage stage;
    public EnemyBullet(Stage stage) {
        super(stage);
        this.stage = stage;
    }


    @Override
    public abstract Rect getSource();

    @Override
    public abstract Point3F getScale();

    @Override
    public abstract void cull();

    @Override
    public void collision(GameObject object){
        if (object instanceof PlayerBullet){
            object.kill();
            this.kill();
        }else if( object instanceof Player){
            this.kill();
            stage.recordPlayerHit(this);
        }
    }
}
