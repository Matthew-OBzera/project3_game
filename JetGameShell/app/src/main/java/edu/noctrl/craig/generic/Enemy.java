package edu.noctrl.craig.generic;

import android.graphics.Canvas;
import android.graphics.Rect;

/**
 * Created by mobze on 5/22/2016.
 */
public class Enemy extends GameSprite {

    protected int health;
    protected double timeToAdd;
    protected int pointWorth;
    final Stage stage;
    public Enemy(Stage stage) {
        super(stage);
        this.stage = stage;
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
            stage.enemy_count--;
            stage.gameTimer.increaseTimeRemaining(this.timeToAdd);
            stage.score += this.pointWorth;
            stage.kills++;
            if(stage.enemiesRemaining > 0)
            {
                stage.enemiesRemaining--;
            }
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
