package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

//Matthew OBzera

public class Stage extends World {

    private Player player;
    public static Timer timer;
    public Stage(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        player = new Player(this);
        this.addObject(player);
    }

    @Override
    protected void initialize(){
        timer = new Timer();
        int delay = (5 + new Random().nextInt(5)) * 10;
        timer.schedule(new SpawnTimer(this),delay);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
       if(event.getActionMasked() == MotionEvent.ACTION_DOWN)
       {
           if(v.getWidth()/3.5 > event.getX())
           {
            /*float dx = event.getX()-player.position.X;
            float dy = event.getY()-player.position.Y;
            float h = (float) Math.sqrt((dx*dx) + (dy*dy));
            player.baseVelocity.X = dx/h;
            player.baseVelocity.Y = dy/h;
            player.updateVelocity();
            return true;*/
           }
           else
           {
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
       }
        return false;
    }


}
