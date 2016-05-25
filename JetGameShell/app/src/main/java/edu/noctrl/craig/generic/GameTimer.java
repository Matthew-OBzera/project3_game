package edu.noctrl.craig.generic;

import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;


public class GameTimer extends Timer{
    protected double timeInSeconds;
    protected double timeRemaining;
    final Stage stage;

    public GameTimer(final Stage stage, double seconds)
    {
        this.stage = stage;
        timeInSeconds = seconds;
        timeRemaining = timeInSeconds;
        this.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if(timeRemaining <= 0)
                {
                    this.cancel();
                    stage.spawnTimer.cancel();
                    stage.listener.onGameOver(stage.gameWon());
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
