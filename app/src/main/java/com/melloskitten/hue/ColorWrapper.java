package com.melloskitten.hue;

import android.graphics.Color;

/**
 * Created by sandra on 10.09.17.
 */
// Wrapper of the Color class that returns a given Color from a Hex String
public class ColorWrapper {

    public static Color prsCol(String hex) {
        return Color.valueOf(Color.parseColor(hex));
    }
}
