package edu.noctrl.craig.generic;

import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;

/**
 * Created by gerardopaleo on 5/23/16.
 */
public class Stage2 extends Stage {
    protected StateListener listener;
    private Boolean dragging = false;


    public Stage2(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        this.listener = listener;
    }

    @Override
    protected void initialize() {

        player = new Player(this);
        this.addObject(player);

        needsTimer = false;
        callsForEnemyMovement = true;
        callsForEnemyFire = true;
        enemiesRemaining = 30;
        spawnTimer = new Timer();
        spawnTimer.schedule(new Spawner(this), 3000);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN: {
                    if (player.bounds.contains(event.getX(), event.getY())) {
                        dragging = true;
                    } else {
                        shotFired();
                    }
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    if (v.getWidth()/3.5 > event.getX()) {
                        if (dragging) {
                            player.position.X = event.getX();
                            player.position.Y = event.getY();
                        }
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    dragging = false;
                    break;
                }

                case MotionEvent.ACTION_POINTER_DOWN: {
                    if (dragging) {
                        shotFired();
                    }
                    break;
                }
            }
        return true;
    }

    @Override
    protected void recordEnemyKilled(Enemy enemy) {
        if(needsTimer)
        {
            gameTimer.increaseTimeRemaining(enemy.timeToAdd);
        }
        enemy_count--;
        score += enemy.pointWorth;
        kills++;
        if(enemiesRemaining > 0){
            enemiesRemaining--;
        }
        if(enemiesRemaining <= 0){
            spawnTimer.cancel();
            Log.i("END STAGE 2", "TEST");
            if(enemy_count <= 0)
            {
                listener.onGameOver(false);
                for(int i = 0; i < objects.size(); i++)
                {
                    if(objects.get(i) instanceof Enemy)
                    {
                        objects.get(i).offScreen = true;
                    }
                }
            }
        }
    }

    @Override
    protected void recordPlayerHit(Bullet eBullet)
    {
        player.health -= eBullet.damage;
        if (player.health <= 0){
            spawnTimer.cancel();
            listener.onGameOver(true);
            for(int i = 0; i < objects.size(); i++)
            {
                if(objects.get(i) instanceof Enemy)
                {
                    objects.get(i).offScreen = true;
                }
            }
        }
    }

    @Override
    protected boolean gameWon() {
        return false;
    }

    public void shotFired() {
        if (player.bulletCount > 0 && player.canFire == true) {
            player.setFirePose();
            --player.bulletCount;
            PlayerBullet pBullet = new PlayerBullet(this);
            pBullet.position.X = player.position.X;
            pBullet.position.Y = player.position.Y;
            pBullet.baseVelocity.X = 1;
            pBullet.baseVelocity.Y = 0;
            pBullet.updateVelocity();
            this.shotsFired++;
            this.addObject(pBullet);
        }
        else
        {
            player.canFire = false;
            player.reload();
        }
    }


    @Override
    public void draw(Canvas canvas){
        if(canvas!=null){
            canvas.drawBitmap(GameSprite.SPRITE_SOURCE, new Rect(525, 727, 986,919), new Rect(0,0, this.width, this.height), null);
            for(GameObject obj : objects){
                obj.draw(canvas);
            }
        }

    }
}
