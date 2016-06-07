package edu.noctrl.craig.generic;

import java.util.TimerTask;

public class Spawner extends TimerTask {

    final Stage stage;

    public Spawner(Stage stage) {
        this.stage = stage;
    }

    @Override
    public void run() {
        if (stage.enemy_count < stage.MAX_ON_ENEMIES_SCREEN) {

            int rand = 1 + (int)(Math.random() * 20);
            Enemy enemy;
            if(rand > 15){
                enemy = new Orochimaru(stage);
            }
            else{
                enemy = new Metroid(stage);
            }


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
                } else {
                    enemy.position.X = (int) ((Math.random() * range) + (stage.width / 3));
                    enemy.position.Y = (int) (Math.random() * (stage.height - 64));
                    collide = false;
                }
            }
            stage.addObject(enemy);
            int delay = (int) (Math.random() * 1500);
            stage.spawnTimer.schedule(new Spawner(stage), delay);
            stage.enemy_count++;
        }
    }
}
