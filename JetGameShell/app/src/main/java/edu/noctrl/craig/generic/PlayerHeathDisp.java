package edu.noctrl.craig.generic;

import android.graphics.Canvas;
import android.graphics.Color;
import android.text.TextPaint;

public class PlayerHeathDisp extends GameObject {

    private static String playerHealth;
    private TextPaint textPaint = new TextPaint();

    public PlayerHeathDisp(World theWorld, int health) {
        super(theWorld);
        playerHealth = "Health Remaining: " + Integer.toString(health);
    }

    @Override
    public void draw(Canvas canvas) {
        if( canvas == null) {
            return;
        }
        textPaint.setTextSize(50.0f);
        textPaint.setColor(Color.CYAN);

        canvas.drawText(playerHealth, 10, 50,textPaint);
    }

    public static void setString(int health)
    {
        playerHealth = "Health Remaining: " + String.valueOf(health);
    }

    @Override
    public void cull() {

    }
}
