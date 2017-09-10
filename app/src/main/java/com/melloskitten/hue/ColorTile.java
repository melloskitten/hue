package com.melloskitten.hue;

import android.graphics.Color;
import android.support.annotation.NonNull;

/**
 * Created by sandra on 10.09.17.
 */

// Color Tile class for handling the game elements. Every ColorTile has a realColor (aka the correct
// Color that should be in that one specific spot) and a curColor (the color that the user drags
// around on the screen, if he finishes the puzzle successfully realColor == curColor, otherwise
// the ColorTile is not in the correct place)
public class ColorTile {

    // MARK: VARS
    private Color realColor;
    private Color curColor;
    private boolean isLocked;
    private boolean isHint;


    // MARK: CONSTRUCTORS
    public ColorTile (Color realColor, Color curColor, boolean isLocked, boolean isHint) {
        this.realColor = realColor;
        this.curColor = curColor;
        this.isLocked = isLocked;
        this.isHint = isHint;
    }

    public ColorTile (Color generatedColor) {
        this.realColor = generatedColor;
        this.curColor = generatedColor;
        this.isLocked = false;
        this.isHint = false;
    }


    // MARK: GETTER & SETTER
    public void setCurColor(Color curColor) {
        this.curColor = curColor;
    }

    public void setRealColor(Color realColor) {
        this.realColor = realColor;
    }

    public void setHint(boolean hint) {
        isHint = hint;
        isLocked = hint;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public Color getCurColor() {
        return curColor;
    }

    public Color getRealColor() {
        return realColor;
    }

    public boolean isLocked() {
        return isLocked;
    }

    // A Hint is always locked, and always has some sort of indicator that it is
    // a Hint. (like a circle or something.)
    public boolean isHint() {
        return isHint && isLocked;
    }

    // Checks if a ColorTile is in the right position to be solved.
    public boolean isSolved() {
        return realColor == curColor;
    }

}
