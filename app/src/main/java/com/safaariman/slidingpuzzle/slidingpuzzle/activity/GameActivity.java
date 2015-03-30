package com.safaariman.slidingpuzzle.slidingpuzzle.activity;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.Toast;

import com.safaariman.slidingpuzzle.slidingpuzzle.configuration.Settings;
import com.safaariman.slidingpuzzle.slidingpuzzle.tool.Chronometer;
//import android.widget.Chronometer;
import com.safaariman.slidingpuzzle.slidingpuzzle.adapter.PuzzlePieceAdapter;
import com.safaariman.slidingpuzzle.slidingpuzzle.R;

/**
 * Created by safa on 18.02.2015.
 */

public class GameActivity extends Activity {

    private PuzzlePieceAdapter adapter;
    private Chronometer ch;
    private GridView gridView;
    private int size = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_screen);

        init();
    }

    public void init() {
        windowConfiguration();
        contentInitialization();
        setContent();
    }

    public void windowConfiguration() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void contentInitialization() {
        Typeface tf = Typeface.createFromAsset(getAssets(), Settings.FONT);

        Bundle bundle = getIntent().getExtras();
        size = (int)bundle.get("size");
        //System.err.println(size + " sizeeee");
        gridView = (GridView) findViewById(R.id.gridView);
        ch = (Chronometer) findViewById(R.id.chronometer);
        ch.setTypeface(tf);
        adapter = new PuzzlePieceAdapter(this, size);
    }

    public void setContent() {
        gridView.setNumColumns(size);
        gridView.setAdapter(adapter);

        //ch.setFormat("Deneme %s");
        ch.start();
    }

    @Override
    public void finish() {
        super.finish();

        this.overridePendingTransition(0, 0);
    }
}
