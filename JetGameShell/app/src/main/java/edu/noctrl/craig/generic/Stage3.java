package edu.noctrl.craig.generic;


import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Timer;

public class Stage3 extends Stage {
    protected World.StateListener listener;
    private Boolean dragging = false;
    private PlayerHeathDisp healthDisp;

    public Stage3(World.StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        this.listener = listener;
    }

    @Override
    protected void initialize() {
        sounds.playSound(SoundManager.ROUND_THREE);
        player = new Player(this);
        this.addObject(player);
        needsTimer = false;
        callsForEnemyMovement = true;
        callsForEnemyFire = false;
        enemiesRemaining = 30;
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                sounds.playSound(SoundManager.FIGHT);
            }
        }, 3000);
        spawnTimer = new Timer();
        spawnTimer.schedule(new Spawner(this), 3000);
        healthDisp = new PlayerHeathDisp(this, player.health);
        this.addObject(healthDisp);
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
        //if(enemiesRemaining > 0){
        //    enemiesRemaining--;
        //}
    }

    @Override
    protected void recordPlayerHit(Bullet eBullet)
    {
        player.health -= eBullet.damage;
        PlayerHeathDisp.setString(player.health);
        if (player.health <= 0){
            spawnTimer.cancel();
            //Killed thirty but died killing the remaining on screen
            //account for that remaining they had to kill
            if(enemiesRemaining == 0)
            {
                enemiesRemaining += enemy_count;
            }
            sounds.playSound(SoundManager.YOU_SUCK);
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
    protected void recordPlayerHit(Enemy e){
        if (e instanceof Metroid){
            player.health -= 5;
        }else{
            player.health -=10;
        }

        PlayerHeathDisp.setString(player.health);
        if (player.health <= 0){
            spawnTimer.cancel();
            //Killed thirty but died killing the remaining on screen
            //account for that remaining they had to kill
            if(enemiesRemaining == 0)
            {
                enemiesRemaining += enemy_count;
            }
            sounds.playSound(SoundManager.YOU_SUCK);
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
            sounds.playSound(SoundManager.CYBER_RELOAD);
            player.reload();
        }
    }


    @Override
    public void draw(Canvas canvas){
        if(canvas!=null){
            canvas.drawBitmap(GameSprite.SPRITE_SOURCE, new Rect(11, 731, 518,920), new Rect(0,0, this.width, this.height), null);
            for(GameObject obj : objects){
                obj.draw(canvas);
            }
        }
    }

    @Override
    public void cullAndAdd(){
        CollisionDetection.checkCollisions(objects);
        GameObject go;
        ArrayList<GameObject> removed = new ArrayList<>(objects.size());
        synchronized (newObjects){
            for(int i = objects.size() - 1;i >= 0; i--){
                go = objects.get(i);
                if(go instanceof Enemy)
                {
                    if(go.position.X > width)
                    {
                        go.baseVelocity.X *= -1.0;
                        ((Enemy) go).move(go.baseVelocity);
                    }
                    if(go.position.Y > (height-64) || go.position.Y < 0)
                    {
                        go.baseVelocity.Y *= -1.0;
                        ((Enemy) go).move(go.baseVelocity);
                    }
                }
                if(go.offScreen){
                    objects.remove(i);
                    removed.add(go);
                }
            }
            for(GameObject obj : newObjects) {
                if (obj.drawOrder == GameObject.DrawOrder.Foreground)
                    objects.add(obj);
                else
                    objects.add(0, obj);
            }
            newObjects.clear();
        }
        for(GameObject obj : removed) {
            obj.cull();
        }

    }
}
