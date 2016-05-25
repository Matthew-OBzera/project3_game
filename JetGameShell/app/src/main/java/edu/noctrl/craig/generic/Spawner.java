package edu.noctrl.craig.generic;

import android.graphics.RectF;
import android.util.Log;

import java.util.Random;
import java.util.TimerTask;

/**
 * Created by mobze on 5/22/2016.
 */
public class Spawner extends TimerTask {

    World world;

    public Spawner(World world) {
        this.world = world;
    }

    @Override
    public void run() {
        if (world.enemy_count < world.MAX_ON_ENEMIES_SCREEN) {
            Enemy2 enemy = new Enemy2(world);
            int range = (world.width - (world.width / 3) - 64);
            enemy.position.X = (int) ((Math.random() * range) + (world.width / 3));
            enemy.position.Y = (int) (Math.random() * (world.height - 64));
            boolean collide = false;
            boolean finished = false;

            while (!finished) {
                for (GameObject object : world.objects) {
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
                    enemy.position.X = (int) ((Math.random() * range) + (world.width / 3));
                    enemy.position.Y = (int) (Math.random() * (world.height - 64));
                    collide = false;
                    Log.v("Test1", "Not Placed");
                }
            }


            world.addObject(enemy);
            int delay = (int) (Math.random() * 1500);
            Stage.spawnTimer.schedule(new Spawner(world), delay);
            world.enemy_count++;
        }
    }
}
