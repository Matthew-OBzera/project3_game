package edu.noctrl.craig.generic;

import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;

public abstract class Stage extends World {
    protected static final float MAX_ON_ENEMIES_SCREEN = 30;
    public int enemy_count = 0;
    protected boolean needsTimer;
    protected boolean callsForEnemyMovement;
    protected boolean callsForEnemyFire;
    protected Timer spawnTimer;
    protected GameTimer gameTimer;
    protected StateListener listener;
    public Player player;
    protected SoundManager sounds;

    public Stage(StateListener listener, SoundManager sounds) {
        super(listener, sounds);
        this.listener = listener;
        this.sounds = sounds;
        this.sounds.playBgMusic();
    }

    @Override
    protected void initialize(){

    }

    @Override
    public abstract boolean onTouch(View v, MotionEvent event);

    protected abstract void recordPlayerHit(Bullet eBullet);

    protected abstract void recordPlayerHit(Enemy e);

    protected abstract void recordEnemyKilled(Enemy enemy);

    protected void recordEnemyHit(){
        hits++;
        sounds.playSound(SoundManager.ALIEN_HIT);
    };

    protected abstract boolean gameWon();
}
