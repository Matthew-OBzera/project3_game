package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;

/**
 * Created by gerardopaleo on 5/23/16.
 */
public class Stage2 extends Stage implements WorldFunctions {
    protected StateListener listener;
    private Boolean dragging = false;


    public Stage2(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        this.listener = listener;
    }

    @Override
    protected void initialize() {

        player = new Player(this);
        this.addObject(player);

        spawnTimer = new Timer();
        spawnTimer.schedule(new Spawner(this), 3000);
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
                    if (v.getWidth()/3.5 > event.getX()) {
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
        pBullet.baseVelocity.X = 1;
        pBullet.baseVelocity.Y = 0;
        pBullet.updateVelocity();
        this.shotsFired++;
        this.addObject(pBullet);
    }

    @Override
    public void recordEnemyKilled() {

    }

    @Override
    public void recordEnemyHit() {

    }
}
