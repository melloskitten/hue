package com.melloskitten.hue;

import android.graphics.Color;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * Created by sandra on 10.09.17.
 */

// This class takes a given ColorTile array and scrambles it up, respecting Hints.
// Also, during the shuffling process not the actual ColorTile is scrambled but only the curColor
// (Hints are fixed and should not be shuffled to make the game easier for the player.)
public class ColorTileScrambler {


    public static ColorTile[] scramble(ColorTile[] colorTiles) {

        // Save all Hints and their index in the array
        // Create an ArrayList for easier shuffling of the non Hint tiles

        ColorTile[] result = new ColorTile[colorTiles.length];
        ArrayList temp = new ArrayList();

        for (int i = 0; i < result.length; i++) {
            if (colorTiles[i].isHint()) {
                result[i] = colorTiles[i];
            } else {
                result[i] = colorTiles[i];
                temp.add(colorTiles[i].getCurColor());
            }
        }

        // Scramble the rest of the array

        Collections.shuffle(temp);

        // Merge both the isolated Hints and the scrambled ColorTiles
        // into one result array

        for (int j = 0; j < result.length; j++) {

            if (!result[j].isHint()) {
                result[j].setCurColor((Color) temp.remove(0));
            }
        }

        return result;
    }

}
