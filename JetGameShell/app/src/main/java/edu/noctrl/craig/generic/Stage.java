package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

public class Stage extends World {
    protected static final float MAX_ON_ENEMIES_SCREEN = 30;
    public int enemy_count = 0;
    protected Timer spawnTimer;
    protected GameTimer gameTimer;
    protected StateListener listener;
    public Stage(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        this.listener = listener;
    }

    @Override
    protected void initialize(){

    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return false;
    }

    protected boolean gameWon()
    {
        return true;
    }
}
