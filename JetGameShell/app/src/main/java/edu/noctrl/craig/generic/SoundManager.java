package edu.noctrl.craig.generic;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.media.SoundPool;

import com.deitel.cannongame.R;

public class SoundManager {
	// constants and variables for managing sounds
	public static int FIRE_ID = 0;
	public static int JET_HIT = 1;
	public static int ALIEN_HIT = 2;
	public static int BATTLE_THEME = 3;
	public static int BOMB_THROW = 4;
	public static int BOSS_ROAR = 5;
	public static int CYBER_RELOAD = 6;
	public static int EXPLOSION = 7;
	public static int FIGHT = 8;
	public static int FIRE_BURN = 9;
	public static int FLAWLESS_VICTORY = 10;
	public static int IMPACT_ONE = 11;
	public static int LAUGH = 12;
	public static int MK_MUSIC_ONE = 13;
	public static int MK_MUSIC_TWO = 14;
	public static int ROUND_ONE = 15;
	public static int ROUND_TWO = 16;
	public static int ROUND_THREE = 17;
	public static int WELL_DONE = 18;
	public static int YOU_SUCK = 19;
    public static int RASENSHURIKEN = 20;


	protected SoundPool soundPool; // plays sound effects
	protected Context context;
	protected MediaPlayer mediaPlayer;
	public SoundManager(Context context){
		SoundPool.Builder builder = new SoundPool.Builder();
		AudioAttributes.Builder atts = new AudioAttributes.Builder();
		atts.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION);
		atts.setUsage(AudioAttributes.USAGE_GAME);
		atts.setFlags(AudioAttributes.FLAG_AUDIBILITY_ENFORCED);
		builder.setAudioAttributes(atts.build());
		builder.setMaxStreams(3);

		// initialize SoundPool to play the app's three sound effects
		soundPool = builder.build();
		this.context = context;
		initializeSounds();
	}

	public void releaseResources(){
		soundPool.release(); // release all resources used by the SoundPool
		soundPool = null;
	}

	protected void initializeSounds(){
		FIRE_ID = soundPool.load(context, R.raw.cannon_fire, 3);
		JET_HIT = soundPool.load(context, R.raw.blocker_hit, 3);
		ALIEN_HIT = soundPool.load(context, R.raw.target_hit, 3);
		BATTLE_THEME = soundPool.load(context, R.raw.battle_theme_smaller, 3);
		BOMB_THROW = soundPool.load(context, R.raw.bomb_throw, 2);
		//BOSS_ROAR = soundPool.load(context, R.raw.boss_roar, 2);
		CYBER_RELOAD = soundPool.load(context, R.raw.cyber_reload,2);
		EXPLOSION = soundPool.load(context, R.raw.explosion,2);
		FIGHT = soundPool.load(context, R.raw.fight, 99);
		FIRE_BURN = soundPool.load(context, R.raw.fire_burn, 3);
		FLAWLESS_VICTORY = soundPool.load(context, R.raw.flawless_victory, 99);
		IMPACT_ONE = soundPool.load(context, R.raw.impact_one, 1);
		LAUGH = soundPool.load(context, R.raw.laugh, 1);
		MK_MUSIC_ONE = soundPool.load(context, R.raw.mk_music_one, 100);
		MK_MUSIC_TWO = soundPool.load(context, R.raw.mk_music_two, 1);
		ROUND_ONE = soundPool.load(context, R.raw.round_one, 99);
		ROUND_TWO = soundPool.load(context, R.raw.round_two, 99);
		ROUND_THREE = soundPool.load(context, R.raw.round_three, 99);
		WELL_DONE = soundPool.load(context, R.raw.well_done, 99);
		YOU_SUCK = soundPool.load(context, R.raw.you_suck, 99);
		RASENSHURIKEN = soundPool.load(context, R.raw.rasenshuriken, 98);

	}

	public void playBgMusic()
	{
		mediaPlayer = MediaPlayer.create(context, R.raw.mk_music_one);
		mediaPlayer.setLooping(true);
		mediaPlayer.start();

	}

	public  void stopBgMusic()
	{
		mediaPlayer.stop();
	}

	public void playSound(int sound){
		soundPool.play(sound, 1, 1, 1, 0, 1f);
	}

}
