package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

public class Stage extends World {

    private Player player;
    public Stage(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        player = new Player(this);
        this.addObject(player);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        float dx = event.getX()-player.position.X;
        float dy = event.getY()-player.position.Y;
        float h = (float) Math.sqrt((dx*dx) + (dy*dy));
        player.baseVelocity.X = dx/h;
        player.baseVelocity.Y = dy/h;
        player.updateVelocity();
        return true;
    }
}
