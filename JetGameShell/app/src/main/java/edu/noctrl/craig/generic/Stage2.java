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
    private Player player;
    private Boolean dragging = false;


    public Stage2(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
    }

    @Override
    protected void initialize() {

        player = new Player(this);
        this.addObject(player);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

            switch (event.getActionMasked()) {
                case MotionEvent.ACTION_DOWN: {
                    if (player.bounds.contains(event.getX(), event.getY())) {
                        dragging = true;
                    } else {
                        shotFired();
                    }
                    break;
                }
                case MotionEvent.ACTION_MOVE: {
                    if (event.getX() < 100) {
                        if (dragging) {
                            player.position.X = event.getX();
                            player.position.Y = event.getY();
                        }
                    }
                    break;
                }
                case MotionEvent.ACTION_UP: {
                    dragging = false;
                    break;
                }

                case MotionEvent.ACTION_POINTER_DOWN: {
                    if (dragging) {
                        shotFired();
                    }
                    break;
                }
            }
        return true;
    }

    @Override
    public void shotFired() {
        PlayerBullet pBullet = new PlayerBullet(this);
        pBullet.position.X = player.position.X;
        pBullet.position.Y = player.position.Y;
        pBullet.baseVelocity.Y = 0;
        pBullet.updateVelocity();
        this.addObject(pBullet);
    }

    @Override
    public void recordEnemyKilled() {

    }

    @Override
    public void recordEnemyHit() {

    }
}
