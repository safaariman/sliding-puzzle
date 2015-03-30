package com.safaariman.slidingpuzzle.slidingpuzzle.tool;

import android.graphics.Point;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.safaariman.slidingpuzzle.slidingpuzzle.adapter.PuzzlePieceAdapter;
import com.safaariman.slidingpuzzle.slidingpuzzle.configuration.Settings;
import com.safaariman.slidingpuzzle.slidingpuzzle.R;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by safa on 20.02.2015.
 */

public class PuzzleProcesses {
    public static int k;
    public static int t;
    private static boolean isCompleted = false;
    private static Point zero = null;
    private static Point positionOfChosenItem = null;

    private static int[][] convertArrayListToMatrix(ArrayList array) {
        int size = (int) Math.sqrt((double)array.size());

        int[][] tempArray = new int[size][size];
        int t = 0;

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                tempArray[i][j] = (int)array.get(t++);
            }
        }

        if (Settings.DEBUG) {
            System.err.println("Converted matrix. (Size = " + size + ")");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    System.err.print(tempArray[i][j] + " ");
                }
                System.err.println();
            }
        }

        return tempArray;
    }

    /*
    * Deneme bir şeyler falan filan
    *
    *
    */
    public static boolean isAllowed(ViewGroup parent, ArrayList array, View v) {
        int[][] matrix = convertArrayListToMatrix(array);
        int chosenItem = Integer.parseInt(((Button) v.findViewById(R.id.button)).getText().toString());

        positionOfChosenItem = getPosition(matrix, chosenItem);

        if (!isMovable(matrix, positionOfChosenItem)) {
            if (Settings.DEBUG)
                System.err.println("Changes are getting back in isAllowed method.");

            positionOfChosenItem = null;
            zero = null;
            isCompleted = false;

            return false;
        }

        move(matrix, array, parent);
        //adapter.notifyDataSetChanged();

        return true;
    }

    public static void move(int[][] matrix, ArrayList arrayList, ViewGroup parent) {
        k = arrayList.indexOf(matrix[positionOfChosenItem.x][positionOfChosenItem.y]);
        t = arrayList.indexOf(matrix[zero.x][zero.y]);


        /*
        parent.getChildAt(k).setTag(temp2);
        parent.getChildAt(t).setTag(temp1);
        int temp = (int) arrayList.get(k);
        arrayList.set(k, arrayList.get(t));
        arrayList.set(t, temp);
        swap(temp1, temp2);
        */
    }


    public static boolean isCompleted(ArrayList arrayList) {
        System.err.println(arrayList.size() + "");
        for (int i = 1; i < arrayList.size(); i++) {
            if (arrayList.get(i - 1) != i) {

                return false;
            }
        }

        return true;
    }

    private static Point getPosition(int[][] matrix, int item) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == item)
                    return new Point(i, j);
            }
        }
        return null;
    }

    private static boolean isMovable(int[][] matrix, Point position) {
        if (controlLeft(matrix, position)) {
            if (Settings.DEBUG)
                System.err.println(matrix[position.x][position.y] + " is movable to left.");
            zero = new Point(position.x, position.y - 1);
            return true;
        } else if (controlRight(matrix, position)) {
            if (Settings.DEBUG)
                System.err.println(matrix[position.x][position.y] + " is movable to right.");
            zero = new Point(position.x, position.y + 1);
            return true;
        } else if (controlUp(matrix, position)) {
            if (Settings.DEBUG)
                System.err.println(matrix[position.x][position.y] + " is movable to below.");
            zero = new Point(position.x - 1, position.y);
            return true;
        } else if (controlDown(matrix, position)) {
            if (Settings.DEBUG)
                System.err.println(matrix[position.x][position.y] + " is movable to above.");
            zero = new Point(position.x + 1, position.y);
            return true;
        }

        return false;
    }

    private static boolean controlLeft(int[][] matrix, Point position) {
        try {
            if (matrix[position.x][position.y - 1] == 0)
                return true;
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private static boolean controlRight(int[][] matrix, Point position) {
        try {
            if (matrix[position.x][position.y + 1] == 0)
                return true;
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private static boolean controlUp(int[][] matrix, Point position) {
        try {
            if (matrix[position.x - 1][position.y] == 0)
                return true;
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    private static boolean controlDown(int[][] matrix, Point position) {
        try {
            if (matrix[position.x + 1][position.y] == 0)
                return true;
        } catch (Exception e) {
            return false;
        }

        return false;
    }

    public static void shufflePuzzle(ArrayList arrayList) {
        int matrix[][] = convertArrayListToMatrix(arrayList);

        Random rand = new Random();

        for (int i = 0; i < 1000; i++) {
            int randMove = rand.nextInt(4) + 1;
            try {
                Point p = getPosition(matrix, 0);

                if (randMove == 1) {
                    int temp = matrix[p.x][p.y];
                    matrix[p.x][p.y] = matrix[p.x][p.y - 1];
                    matrix[p.x][p.y - 1] = temp;
                    //System.err.println("Random movement is valid.");
                } else if (randMove == 2) {
                    int temp = matrix[p.x][p.y];
                    matrix[p.x][p.y] = matrix[p.x][p.y + 1];
                    matrix[p.x][p.y + 1] = temp;
                    //System.err.println("Random movement is valid.");
                } else if (randMove == 3) {
                    int temp = matrix[p.x][p.y];
                    matrix[p.x][p.y] = matrix[p.x - 1][p.y];
                    matrix[p.x - 1][p.y] = temp;
                    //System.err.println("Random movement is valid.");
                } else if (randMove == 4) {
                    int temp = matrix[p.x][p.y];
                    matrix[p.x][p.y] = matrix[p.x + 1][p.y];
                    matrix[p.x + 1][p.y] = temp;
                    //System.err.println("Random movement is valid.");
                }
            } catch (Exception e) {
                //System.err.println("Random movement is not valid.");
            }
        }
        //System.err.println();

        arrayList.clear();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                arrayList.add(matrix[i][j]);
            }
        }
    }
}
