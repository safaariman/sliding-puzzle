package com.safaariman.slidingpuzzle.slidingpuzzle.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.safaariman.slidingpuzzle.slidingpuzzle.R;
import com.safaariman.slidingpuzzle.slidingpuzzle.configuration.Settings;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.main);


        Typeface tf = Typeface.createFromAsset(getAssets(), Settings.FONT);

        ((TextView) findViewById(R.id.appTitle)).setTypeface(tf);


        Button play = (Button) findViewById(R.id.play);
        play.setTypeface(tf);
        Button high = (Button) findViewById(R.id.highScores);
        high.setTypeface(tf);
        Button exit = (Button) findViewById(R.id.exit);
        exit.setTypeface(tf);
    }

    public void onClick(View v) {

        if(v.getId() == R.id.play) {
            Intent intent = new Intent(this, DifficultyActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            startActivity(intent);
        } else if(v.getId() == R.id.highScores) {

        } else if(v.getId() == R.id.exit) {
            this.finish();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
