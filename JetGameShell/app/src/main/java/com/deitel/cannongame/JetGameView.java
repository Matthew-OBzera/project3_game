// CannonView.java
// Displays and controls the Cannon Game
package com.deitel.cannongame;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;
import java.io.InputStream;

import edu.noctrl.craig.generic.GameSprite;
import edu.noctrl.craig.generic.ScoresDBHelper;
import edu.noctrl.craig.generic.SoundManager;
import edu.noctrl.craig.generic.Stage1;
import edu.noctrl.craig.generic.Stage2;
import edu.noctrl.craig.generic.Stage3;
import edu.noctrl.craig.generic.World;

public class JetGameView extends SurfaceView implements SurfaceHolder.Callback, World.StateListener {
    private static final String TAG = "GameView"; // for logging errors
    private static final String SPRITE_FILE = "sprites.png";

    private GameThread gameThread; // controls the game loop
    private World world;
    private SoundManager soundManager;
    private ScoresDBHelper dbHelper;
    private Activity activity; // to display Game Over dialog in GUI thread
    private boolean dialogIsDisplayed = false;
    private static int stageLvl = 1;
    public static boolean stageMenuItemSelected = false;

    // variables for the game loop and tracking statistics
    private boolean gameOver; // is the game over?

    private int screenWidth;
    private int screenHeight;



    //http://stackoverflow.com/questions/18973550/load-images-from-assets-folder
    private Bitmap getBitmapFromAsset(String strName)
    {
        AssetManager assetManager = activity.getAssets();
        try {
            InputStream istr = assetManager.open(strName);
            Bitmap bitmap = BitmapFactory.decodeStream(istr);
            istr.close();
            return bitmap;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void loadSprites(){
        if(GameSprite.SPRITE_SOURCE == null){
            GameSprite.SPRITE_SOURCE = getBitmapFromAsset(SPRITE_FILE);
        }
        World.resources = getResources();
    }
    public void releaseSprites(){
        if(GameSprite.SPRITE_SOURCE != null){
            GameSprite.SPRITE_SOURCE.recycle();
            GameSprite.SPRITE_SOURCE = null;
        }
    }
    // public constructor
    public JetGameView(Context context, AttributeSet attrs) {
        super(context, attrs); // call superclass constructor
        activity = (Activity) context; // store reference to MainActivity
        // register SurfaceHolder.Callback listener
        getHolder().addCallback(this);
        soundManager = new SoundManager(context);
        dbHelper = new ScoresDBHelper(context);
        loadSprites();
    } // end CannonView constructor

    // called by surfaceChanged when the size of the SurfaceView changes,
    // such as when it's first added to the View hierarchy
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        screenWidth = w; // store CannonView's width
        screenHeight = h; // store CannonView's height
        newGame(getHolder()); // set up and start a new game
    } // end method onSizeChanged

    // reset all the screen elements and start a new game
    public void newGame(SurfaceHolder holder) {
        if (gameOver) // starting a new game after the last game ended
        {
            gameOver = false;

            switch (stageLvl)
            {
                case 1:world = new Stage1(this, soundManager); break;
                case 2: world = new Stage2(this, soundManager); break;
                case 3: world = new Stage3(this, soundManager); break;
                default: world = new Stage1(this,  soundManager); stageLvl = 1; break;
            }
            world.updateSize(screenWidth, screenHeight);
            this.setOnTouchListener(world);
            gameThread = new GameThread(holder, world); // create thread
            gameThread.start(); // start the game loop thread
        }
    } // end method newGame

    // display an AlertDialog when the game ends
    private void showGameOverDialog(final boolean lost) {
        // DialogFragment to display quiz stats and start new quiz
        final DialogFragment gameResult =
                new DialogFragment() {
                    // create an AlertDialog and return it
                    @Override
                    public Dialog onCreateDialog(Bundle bundle) {
                        // create dialog displaying String resource for messageId
                        AlertDialog.Builder builder =
                                new AlertDialog.Builder(getActivity());
                        //builder.setTitle(getResources().getString(messageId));

                        // display number of shots fired and total time elapsed
                        builder.setMessage(getResources().getString(
                                R.string.results_format,
                                world.shotsFired,//world.shotsFired,
                                world.kills,//world.kills,
                                world.enemiesRemaining,//world.remaining,
                                world.score,//world.score,
                                world.totalElapsedTime-3.0));
                        if(lost)
                        {
                            soundManager.playSound(SoundManager.YOU_SUCK);
                        }
                        if(!lost)
                        {
                            builder.setPositiveButton(R.string.next_stage,
                                    new DialogInterface.OnClickListener() {
                                        // called when "Reset Game" Button is pressed
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            dialogIsDisplayed = false;
                                            ++stageLvl;
                                            newGame(getHolder()); // set up and start a new game
                                        }
                                    } // end anonymous inner class
                            ); // end call to setPositiveButton
                        }
                        builder.setNegativeButton(R.string.reset_game,
                                new DialogInterface.OnClickListener() {
                                    // called when "Reset Game" Button is pressed
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialogIsDisplayed = false;
                                        newGame(getHolder()); // set up and start a new game
                                    }
                                } // end anonymous inner class
                        ); // end call to setPositiveButton

                        return builder.create(); // return the AlertDialog
                    } // end method onCreateDialog
                }; // end DialogFragment anonymous inner class

        // in GUI thread, use FragmentManager to display the DialogFragment
        activity.runOnUiThread(
                new Runnable() {
                    public void run() {
                        dialogIsDisplayed = true;
                        gameResult.setCancelable(false); // modal dialog
                        gameResult.show(activity.getFragmentManager(), "results");
                    }
                } // end Runnable
        ); // end call to runOnUiThread
    } // end method showGameOverDialog

    // stops the game; called by JetGameFragment's onPause method
    public void stopGame() {
        if (gameThread != null)
            gameThread.stopGame(); // tell thread to terminate
    }

    // releases resources; called by JetGameFragment's onDestroy method
    public void releaseResources() {
        soundManager.releaseResources();
    }

    // called when surface changes size
    @Override
    public void surfaceChanged(SurfaceHolder holder, int format,
                               int width, int height) {
    }

    // called when surface is first created
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        if (!dialogIsDisplayed) {
            gameOver = true;
            newGame(holder);
        }
    }

    // called when the surface is destroyed
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // ensure that thread terminates properly
        boolean retry = true;
        gameThread.stopGame(); // terminate cannonThread

        while (retry) {
            try {
                gameThread.join(); // wait for cannonThread to finish
                retry = false;
            } catch (InterruptedException e) {
                Log.e(TAG, "Thread interrupted", e);
            }
        }
    } // end method surfaceDestroyed


    @Override
    public void onGameOver(boolean lost) {
        gameOver = true; // the game is over
        gameThread.stopGame(); // terminate thread
        soundManager.stopBgMusic();
        showGameOverDialog(lost); // show the losing dialog
    }

    public static void setStageLvl(int stage)
    {
        stageLvl = stage;
    }
}
