package com.safaariman.slidingpuzzle.slidingpuzzle.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.safaariman.slidingpuzzle.slidingpuzzle.R;
import com.safaariman.slidingpuzzle.slidingpuzzle.configuration.Settings;

/**
 * Created by safa on 20.02.2015.
 */
public class DifficultyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.difficulty);
        Typeface tf = Typeface.createFromAsset(getAssets(), Settings.FONT);

        ((TextView) findViewById(R.id.appTitle)).setTypeface(tf);


        Button x3 = (Button) findViewById(R.id.x3);
        x3.setTypeface(tf);

        Button x4 = (Button) findViewById(R.id.x4);
        x4.setTypeface(tf);

        Button x5 = (Button) findViewById(R.id.x5);
        x5.setTypeface(tf);
    }

    public void onClick(View view) {
        if (view.getId() == R.id.x3) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            Bundle bundle = new Bundle();
            bundle.putInt("size", 3);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if(view.getId() == R.id.x4) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            Bundle bundle = new Bundle();
            bundle.putInt("size", 4);
            intent.putExtras(bundle);
            startActivity(intent);
        } else if(view.getId() == R.id.x5) {
            Intent intent = new Intent(this, GameActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            Bundle bundle = new Bundle();
            bundle.putInt("size", 5);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    @Override
    public void finish() {
        super.finish();

        overridePendingTransition(0, 0);
    }
}
