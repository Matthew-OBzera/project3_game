package edu.noctrl.craig.generic;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by mobze on 5/22/2016.
 */
public class Enemy extends GameSprite {

    protected int health;
    protected double timeToAdd;

    public Enemy(World theWorld) {
        super(theWorld);
    }

    @Override
    public void cull() {

    }

    @Override
    public void collision(GameObject other) {
        PlayerBullet bullet = (PlayerBullet) other;
        this.health -= bullet.damage;
        bullet.kill();

        /*if(bullet.penetration > 0)
        {
            bullet.penetration--;
            this.health = 0;
        }
        if(bullet.penetration == 0)
        {
            bullet.kill();
        }*/
        if(this.health <= 0)
        {
            this.kill();
            Stage.gameTimer.increaseTimeRemaining(this.timeToAdd);
        }
    }

    @Override
    public Rect getSource() {
        return null;
    }

    @Override
    public Point3F getScale() {
        return null;
    }
}
