package edu.noctrl.craig.generic;

import java.text.DecimalFormat;
import java.util.Timer;
import java.util.TimerTask;


public class GameTimer extends Timer{
    protected double timeInSeconds;
    protected double timeRemaining;
    private boolean initialStart = true;
    final Stage stage;
    public GameTimer(final Stage stage, double seconds)
    {
        initialStart = true;
        this.stage = stage;
        timeInSeconds = seconds;
        timeRemaining = timeInSeconds;
        this.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //Log.i("TIMERTEST", "run: timer working" + timeRemaining);
                if(initialStart)
                {
                    stage.sounds.playSound(SoundManager.FIGHT);
                    initialStart = false;
                }
                timeRemaining -= 0.1;
                if(timeRemaining <= 0)
                {
                    this.cancel();
                    stage.spawnTimer.cancel();
                    stage.listener.onGameOver(stage.gameWon());
                    //Ensures all enemys are removed along with their timers
                    for(int i = 0; i < stage.objects.size(); i++)
                    {
                        if(stage.objects.get(i) instanceof Enemy)
                        {
                            stage.objects.get(i).offScreen = true;
                        }
                    }
                }
                else
                {
                    updateDisplay();
                }
            }
        }, 3000, 100);
    }

    private void updateDisplay() {
        DecimalFormat df = new DecimalFormat("#.#");
        TimerDisplay.setString(df.format(timeRemaining).toString());
    }

    //On enemy killed increase time remaining
    public void increaseTimeRemaining(double seconds)
    {
        timeRemaining += seconds;
    }

}
