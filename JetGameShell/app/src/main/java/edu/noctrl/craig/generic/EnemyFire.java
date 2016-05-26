package edu.noctrl.craig.generic;

import java.util.Timer;
import java.util.TimerTask;

public class EnemyFire extends Timer {

    final private Enemy enemy;

    public EnemyFire(final Enemy enemy) {
        this.enemy = enemy;
        this.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (enemy.isDead() || enemy.offScreen) {
                    this.cancel();
                }
                else
                {
                    enemy.fire();
                }
            }
        }, 3000, 100);
    }
}
