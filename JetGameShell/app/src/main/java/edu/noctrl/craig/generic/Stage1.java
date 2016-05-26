package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Stage1 extends Stage {
    protected StateListener listener;

    public Stage1(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        this.listener = listener;
    }

    @Override
    protected void initialize() {
        player = new Player(this);
        this.addObject(player);
        enemiesRemaining = 10;
        needsTimer = true;
        callsForEnemyMovement = false;
        callsForEnemyFire = false;

        spawnTimer = new Timer();
        spawnTimer.schedule(new Spawner(this), 3000);
        gameTimer = new GameTimer(this, 10.0);
        TimerDisplay timerDisplay = new TimerDisplay(this, gameTimer);
        this.addObject(timerDisplay);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
            if (v.getWidth() / 3.5 < event.getX()) {
                if (player.bulletCount > 0) {
                    player.setFirePose();
                    --player.bulletCount;
                    this.shotsFired++;
                    PlayerBullet pBullet = new PlayerBullet(this);
                    pBullet.position.X = player.position.X;
                    pBullet.position.Y = player.position.Y;
                    this.addObject(pBullet);
                    float dx = event.getX() - player.position.X;
                    float dy = event.getY() - player.position.Y;
                    float h = (float) Math.sqrt((dx * dx) + (dy * dy));
                    pBullet.baseVelocity.X = dx / h;
                    pBullet.baseVelocity.Y = dy / h;
                    pBullet.updateVelocity();
                } else {
                    player.reload();
                }
            }
        }
        if (event.getActionMasked() == MotionEvent.ACTION_UP && v.getWidth() / 3.5 < event.getX()) {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.setStationaryStanding();
        }
        return true;
    }

    @Override
    protected void recordPlayerHit(Bullet eBullet) {
        //not implemented in stage 1
    }

    @Override
    protected boolean gameWon() {
        if (enemiesRemaining == 0) {
            return false;
        }
        return true;
    }
    @Override
    protected void recordEnemyKilled(Enemy enemy) {
        enemy_count--;
        if(needsTimer){
            gameTimer.increaseTimeRemaining(enemy.timeToAdd);
        }
        score += enemy.pointWorth;
        kills++;
        if(enemiesRemaining > 0){
            enemiesRemaining--;
        }
    }
}
