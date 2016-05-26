package edu.noctrl.craig.generic;

import android.util.Log;

import java.util.TimerTask;

/**
 * Created by mobze on 5/22/2016.
 */
public class Spawner extends TimerTask {

    final Stage stage;

    public Spawner(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void run() {
        if (stage.enemy_count < stage.MAX_ON_ENEMIES_SCREEN) {
            Enemy3 enemy = new Enemy3(stage);
            int range = (stage.width - (stage.width / 3) - 64);
            enemy.position.X = (int) ((Math.random() * range) + (stage.width / 3));
            enemy.position.Y = (int) (Math.random() * (stage.height - 64));
            boolean collide = false;
            boolean finished = false;

            while (!finished) {
                for (GameObject object : stage.objects) {
                    //Log.v("Current Object X, Y: ", )
                    float dx = object.position.X - enemy.position.X;
                    float dy = object.position.Y - enemy.position.Y;
                    if ((dx * dx + dy * dy) < 20000) {
                        collide = true;
                        break;
                    }
                }
                if (!collide) {
                    finished = true;
                    Log.v("Test1", "Placed");
                } else {
                    enemy.position.X = (int) ((Math.random() * range) + (stage.width / 3));
                    enemy.position.Y = (int) (Math.random() * (stage.height - 64));
                    collide = false;
                    Log.v("Test1", "Not Placed");
                }
            }
            stage.addObject(enemy);
            enemy.shoot();
            int delay = (int) (Math.random() * 1500);
            stage.spawnTimer.schedule(new Spawner(stage), delay);

            stage.enemy_count++;
        }
    }
}
