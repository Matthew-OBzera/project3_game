package edu.noctrl.craig.generic;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


public class GameTimer extends Timer{
    protected double timeInSeconds;
    protected double timeRemaining;
    World world;

    public GameTimer(World world, double seconds)
    {
        this.world = world;
        timeInSeconds = seconds;
        timeRemaining = timeInSeconds;
        this.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(timeRemaining <= 0)
                {
                    this.cancel();
                    Stage.spawnTimer.cancel();
                }
                Log.i("TIMERTEST", "run: timer working" + timeRemaining);
                timeRemaining -= 0.1;
                updateDisplay();
            }
        }, 3000, 100);
    }

    private void updateDisplay() {
        //once status bar exsists update gui
        //will make a formatted time
    }

    //On enemy killed increase time remaining
    public void increaseTimeRemaining(double seconds)
    {
        timeRemaining += seconds;
    }

}
