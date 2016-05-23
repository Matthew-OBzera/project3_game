package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

/**
 * Created by gerardopaleo on 5/23/16.
 */
public class Stage2 extends World implements WorldFunctions {

    int totalScore;
    int enemiesKilled;
    int enemiesHit;

    public Stage2(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
    }

    @Override
    protected void initialize(){

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(event.getActionMasked() == MotionEvent.ACTION_DOWN)
        {

        }
        return true;
    }

    @Override
    public void shotFired() {

    }

    @Override
    public void recordEnemyKilled() {

    }

    @Override
    public void recordEnemyHit() {

    }
}
