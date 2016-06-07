package edu.noctrl.craig.generic;

import java.util.Timer;
import java.util.TimerTask;

public class EnemyMovementController extends Timer {

    final private Enemy enemy;

    public EnemyMovementController(final Enemy enemy, final Stage stage) {
        this.enemy = enemy;
        this.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (enemy.isDead() || enemy.offScreen) {
                    this.cancel();
                }
                else if(stage instanceof Stage2)
                {
                    Float x = (float)(Math.random() * 5 * (Math.random() > 0.5 ? 1 : -1));
                    Float y = (float)(Math.random() * 5 * (Math.random() > 0.5 ? 1 : -1));
                    Float z = (float)(Math.random() * 5 * (Math.random() > 0.5 ? 1 : -1));
                    Point3F point = new Point3F(x,y,z);
                    enemy.move(point);
                }else{
                    Float x = (float)-1;
                    Float y = (float)(Math.random() * 5 * (Math.random() > 0.5 ? 1 : -1));
                    Float z = (float)(Math.random() * 5 * (Math.random() > 0.5 ? 1 : -1));
                    Point3F point = new Point3F(x,y,z);
                    enemy.move(point);
                }
            }
        }, 0, 1000);
    }
}
