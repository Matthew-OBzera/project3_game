// CannonGame.java
// MainActivity displays the JetGameFragment
package com.deitel.cannongame;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;

import edu.noctrl.craig.generic.World;

/**
  _____                    _  _       _     ___  _
 |_   _|___  __ _  _ __   | \| | ___ | |_  / __|| |_   ___  _ _  ___
   | | / -_)/ _` || '  \  | .` |/ _ \|  _| \__ \| ' \ / _ \| '_|/ -_)
   |_| \___|\__,_||_|_|_| |_|\_|\___/ \__| |___/|_||_|\___/|_|  \___|

 Matt, GP, Justin, Kyle

 */

public class MainActivity extends Activity {
    // called when the app first launches
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // call super's onCreate method
        setContentView(R.layout.activity_main); // inflate the layout
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_stage_slection:
                final CharSequence[] stages = {"Stage 1", "Stage 2", "Stage 3"};
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Enter Zip Code");
                builder.setSingleChoiceItems(stages, -1, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {


                        switch(item)
                        {
                            case 0:
                                JetGameView.setStageLvl(1);
                                break;
                            case 1:
                                JetGameView.setStageLvl(2);
                                break;
                            case 2:
                                JetGameView.setStageLvl(3);
                                break;
                        }
                        dialog.dismiss();
                    }
                });
                builder.show();
                break;


            case R.id.action_about:
                final AlertDialog.Builder aboutBuilder = new AlertDialog.Builder(this);
                aboutBuilder.setMessage("Written by: Gerardo Paleo, Justin Long, Kyle Loveless, Matthew OBzera" +
                        " \n" + "Data from weather.gov" );
                aboutBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                aboutBuilder.show();
                break;
            default:
                break;
        }
        return true;
    }

} // end class MainActivity

/*********************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and * Pearson Education, *
 * Inc. All Rights Reserved. * * DISCLAIMER: The authors and publisher of this   *
 * book have used their * best efforts in preparing the book. These efforts      *
 * include the * development, research, and testing of the theories and programs *
 * * to determine their effectiveness. The authors and publisher make * no       *
 * warranty of any kind, expressed or implied, with regard to these * programs   *
 * or to the documentation contained in these books. The authors * and publisher *
 * shall not be liable in any event for incidental or * consequential damages in *
 * connection with, or arising out of, the * furnishing, performance, or use of  *
 * these programs.                                                               *
 *********************************************************************************/
