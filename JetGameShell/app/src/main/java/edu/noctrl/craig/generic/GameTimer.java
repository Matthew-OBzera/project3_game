package edu.noctrl.craig.generic;

import java.util.Timer;
import java.util.TimerTask;


public class GameTimer extends Timer{
    protected double timeInSeconds;
    protected double timeRemaining;
    public GameTimer(double seconds)
    {
        timeInSeconds = seconds;
        timeRemaining = timeInSeconds;
        this.schedule(new TimerTask() {
            @Override
            public void run() {
                if(timeRemaining == 0)
                {
                    this.cancel();
                    //end game
                }
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
