package edu.noctrl.craig.generic;

import java.util.Random;
import java.util.TimerTask;

/**
 * Created by mobze on 5/22/2016.
 */
public class Spawner extends TimerTask {

    World world;

    public Spawner(World world)
    {
        this.world = world;
    }

    @Override
    public void run() {
        Enemy2 enemy = new Enemy2(world);
        int range = (world.width - (world.width/3)-64);
        enemy.position.X = (int)((Math.random()*range)+(world.width/3));
        enemy.position.Y = (int)(Math.random()*(world.height-64));
        world.addObject(enemy);
        int delay = 500 + (int)(Math.random() * 1500);
        Stage.spawnTimer.schedule(new Spawner(world), delay);
    }
}
