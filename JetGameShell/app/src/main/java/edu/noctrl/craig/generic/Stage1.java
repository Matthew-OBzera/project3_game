package edu.noctrl.craig.generic;

import android.graphics.Canvas;
import android.graphics.Rect;
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
        sounds.playSound(SoundManager.ROUND_ONE);
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
                if (player.bulletCount > 0 && player.canFire) {
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
                    sounds.playSound(SoundManager.CYBER_RELOAD);
                    player.canFire = false;
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
    protected void recordPlayerHit(Enemy e) {
        //not implemented in stage 1
    }

    @Override
    protected boolean gameWon() {
        if (enemiesRemaining == 0) {
            sounds.playSound(SoundManager.FLAWLESS_VICTORY);
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

    @Override
    public void draw(Canvas canvas){
        if(canvas!=null){
            canvas.drawBitmap(GameSprite.SPRITE_SOURCE, new Rect(491, 459, 1003, 698), new Rect(0,0, this.width, this.height), null);
            for(GameObject obj : objects){
                obj.draw(canvas);
            }
        }

    }
}
