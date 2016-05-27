package edu.noctrl.craig.generic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;

public class TimerDisplay extends GameObject {
    private static String timeDisp = "Get Ready!";
    private TextPaint textPaint = new TextPaint();


    GameTimer gameTime;

    public TimerDisplay(World theWorld, GameTimer gameTimer) {
        super(theWorld);
        gameTime = gameTimer;
    }

    @Override
    public void draw(Canvas canvas) {
        if (canvas == null) {
            return;
        }
        textPaint.setTextSize(50.0f);
        textPaint.setColor(Color.CYAN);
        canvas.drawText(timeDisp, 10, 50, textPaint);
    }

    @Override
    public void cull() {

    }

    public static void setString(String timeRemaining) {
        timeDisp = "Time Remaining: " + timeRemaining;
    }

}