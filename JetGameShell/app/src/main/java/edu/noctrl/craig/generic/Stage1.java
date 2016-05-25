package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.concurrent.TimeUnit;

/**
 * Created by mobze on 5/24/2016.
 */
public class Stage1 extends Stage {
    private Player player;
    public static Timer spawnTimer;
    protected static GameTimer gameTimer;
    protected StateListener listener;
    public Stage1(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        player = new Player(this);
        this.addObject(player);
        this.listener = listener;
    }

    @Override
    protected void initialize(){
        spawnTimer = new Timer();
        spawnTimer.schedule(new Spawner(this), 3000);
        gameTimer = new GameTimer(this, 10.0);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN)
        {
            if(v.getWidth()/3.5 > event.getX())
            {
                //no movement for stage one
            }
            else
            {
                if(player.bulletCount > 0)
                {
                    player.fireBullet();
                    --player.bulletCount;
                    PlayerBullet pBullet = new PlayerBullet(this);
                    pBullet.position.X = player.position.X;
                    pBullet.position.Y = player.position.Y;
                    this.addObject(pBullet);
                    float dx = event.getX()-player.position.X;
                    float dy = event.getY()-player.position.Y;
                    float h = (float) Math.sqrt((dx*dx) + (dy*dy));
                    pBullet.baseVelocity.X = dx/h;
                    pBullet.baseVelocity.Y = dy/h;
                    pBullet.updateVelocity();
                    return true;
                }
                else
                {
                    player.reload();
                }
            }
        }
        if(event.getActionMasked() == MotionEvent.ACTION_UP && v.getWidth()/3.5 < event.getX())
        {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.setStationaryStanding();
        }
        return false;
    }

    protected boolean gameWon()
    {
        //add killcount
        return false;
    }
}
