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
                    Log.i("TIMERTEST", "run: ENEMY DEAD");
                }
                else
                {
                    Log.i("TIMERTEST", "run: ENEMY MOVING");
                    //enemy.move();
                }
            }
        }, 3000, 100);
    }
}
