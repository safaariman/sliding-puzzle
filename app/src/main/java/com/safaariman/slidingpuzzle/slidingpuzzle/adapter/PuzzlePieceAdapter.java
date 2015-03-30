package com.safaariman.slidingpuzzle.slidingpuzzle.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;
import com.safaariman.slidingpuzzle.slidingpuzzle.R;
import com.safaariman.slidingpuzzle.slidingpuzzle.configuration.Settings;
import com.safaariman.slidingpuzzle.slidingpuzzle.tool.Chronometer;
import com.safaariman.slidingpuzzle.slidingpuzzle.tool.PuzzleProcesses;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by safa on 18.02.2015.
 */

public class PuzzlePieceAdapter extends BaseAdapter {
    PuzzlePieceAdapter adapt = this;
    private Context context;
    private int size;
    private ArrayList array;

    public PuzzlePieceAdapter(Context context, int size){
        this.context = context;
        this.size = size;
        array = new ArrayList();
        fillArray();

    }

    private void fillArray() {
        for (int i = 0; i < size * size - 1; i++) {
            array.add(i + 1);
        }

        array.add(0);

        if (Settings.DEBUG) {
            System.err.println("Array List first creation.");
            for (int i = 0; i < size * size; i++)
                System.err.print(array.get(i));
            System.err.println();
        }

        PuzzleProcesses.shufflePuzzle(array);
    }

    public int getCount() {
        return array.size();
    }

    public Object getItem(int position) {
        return array.get(position);
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, final ViewGroup parent){

        PuzzlePieceHolder holder;

        if (convertView == null) {
            LayoutInflater mInflater = ((Activity) context).getLayoutInflater();
            convertView = mInflater.inflate(R.layout.puzzle_piece, parent, false);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), Settings.FONT);

            holder = new PuzzlePieceHolder();
            holder.button = (Button) convertView.findViewById(R.id.button);

            holder.button.setText(String.valueOf(array.get(position)));

            holder.button.setTypeface(tf);

            if (array.get(position) == 0){
                //holder.button.setVisibility(View.INVISIBLE);
                holder.button.setBackgroundColor(Color.TRANSPARENT);
                holder.button.setTextColor(Color.TRANSPARENT);
            } else {
                holder.color = randomColorGenerator();
                holder.button.setBackgroundColor(holder.color);

            }

            convertView.setTag(holder);
        } else {
            holder = (PuzzlePieceHolder) convertView.getTag();
            holder.button.setBackgroundColor(holder.color);
        }

        DisplayMetrics dm = convertView.getResources().getDisplayMetrics();

        final int dpInPx = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, (300 - (size-1)*2) / size, dm);

        holder.button.setHeight(dpInPx);
        holder.button.setWidth(dpInPx);


        if (array.get(position) != 0)
            holder.button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*
                    int a = Integer.parseInt(((Button) v.findViewById(R.id.button)).getText().toString());

                    System.err.println(((Button) v.findViewById(R.id.button)).getText() + "");
                    */
                    if (PuzzleProcesses.isAllowed(parent, array, v)) {
                        //System.err.println(PuzzleProcesses.k + " " + PuzzleProcesses.t);

                        PuzzlePieceHolder temp1 = (PuzzlePieceHolder) parent.getChildAt(PuzzleProcesses.k).getTag();
                        PuzzlePieceHolder temp2 = (PuzzlePieceHolder) parent.getChildAt(PuzzleProcesses.t).getTag();

                        /*
                        Button tempButton = temp1.button;
                        temp1.button = temp2.button;
                        temp2.button = tempButton;
                        */

                        int temp = (int) array.get(PuzzleProcesses.k);
                        array.set(PuzzleProcesses.k, array.get(PuzzleProcesses.t));
                        array.set(PuzzleProcesses.t, temp);

                        int textColor = temp1.button.getCurrentTextColor();
                        int color = temp1.color;
                        String text = temp1.button.getText().toString();

                        temp1.button.setText(temp2.button.getText());
                        temp1.button.setBackgroundColor(temp2.color);
                        temp1.color = temp2.color;
                        temp1.button.setTextColor(temp2.button.getCurrentTextColor());

                        temp2.button.setText(text);
                        temp2.button.setBackgroundColor(color);
                        temp2.button.setTextColor(textColor);
                        temp2.color = color;
                        //temp1.updateButton();
                        //temp2.updateButton();

                        //parent.getChildAt(PuzzleProcesses.k).setTag(temp1);
                        //parent.getChildAt(PuzzleProcesses.t).setTag(temp2);
                        notifyDataSetChanged();

                        //notifyDataSetInvalidated();
                    }

                    //notifyDataSetChanged();
                    if (PuzzleProcesses.isCompleted(array)) {
                        Toast.makeText(context, ((Chronometer)((Activity) context).findViewById(R.id.chronometer)).getText(), Toast.LENGTH_LONG).show();

                        ((Activity) context).finish();
                    }
                }
            });
/*
        holder.button.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    System.out.println("sola çekti hacı");
                return true;
            }

        });
*/

        return convertView;
    }

    public static void swap(PuzzlePieceHolder temp1, PuzzlePieceHolder temp2) {
        int textColor = temp1.button.getCurrentTextColor();
        int color = temp1.color;
        String text = temp1.button.getText().toString();

        temp1.button.setText(temp2.button.getText());
        temp1.button.setBackgroundColor(temp2.color);
        temp1.button.setTextColor(temp2.button.getCurrentTextColor());

        temp2.button.setText(text);
        temp2.button.setBackgroundColor(color);
        temp2.button.setTextColor(textColor);
    }

    int randomColorGenerator() {
        Random rand = new Random();

        int r = rand.nextInt(100) + 110;
        int g = rand.nextInt(100) + 110;
        int b = rand.nextInt(100) + 110;

        return Color.rgb(r, g, b);
    }

    static class PuzzlePieceHolder {
        Button button;
        int color;

        void updateButton() {
            button.setBackgroundColor(color);
        }
    }

}
