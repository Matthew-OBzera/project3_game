package edu.noctrl.craig.generic;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class EnemyMovement extends Timer {

    final private Enemy enemy;

    public EnemyMovement(final Enemy enemy) {
        this.enemy = enemy;
        this.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (enemy.isDead() || enemy.offScreen) {
                    this.cancel();
                }
                else
                {
                    Float x = (float)(Math.random() * 5 * (Math.random() > 0.5 ? 1 : -1));
                    Float y = (float)(Math.random() * 5 * (Math.random() > 0.5 ? 1 : -1));
                    Float z = (float)(Math.random() * 5 * (Math.random() > 0.5 ? 1 : -1));
                    Point3F point = new Point3F(x,y,z);
                    enemy.move(point);
                }
            }
        }, 0, 1000);
    }
}
