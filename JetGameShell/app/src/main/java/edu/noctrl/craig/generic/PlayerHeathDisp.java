package edu.noctrl.craig.generic;

import android.graphics.Canvas;
import android.text.TextPaint;

/**
 * Created by Kyle on 5/26/2016.
 */
public class PlayerHeathDisp extends GameObject {

    public String playerHealth;
    private TextPaint textPaint = new TextPaint();

    public PlayerHeathDisp(World theWorld, int health) {
        super(theWorld);
        playerHealth = Integer.toString(health);
    }

    @Override
    public void draw(Canvas canvas) {
        if( canvas == null) {
            return;
        }
        textPaint.setTextSize(50.0f);

        canvas.drawText(playerHealth, 50, 50,textPaint);
    }

    @Override
    public void cull() {

    }
}
