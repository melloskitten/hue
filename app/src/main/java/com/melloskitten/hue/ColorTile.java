package com.melloskitten.hue;

import android.graphics.Color;
import android.support.annotation.NonNull;

/**
 * Created by sandra on 10.09.17.
 */

public class ColorTile {

    private Color realColor;
    private Color curColor;
    private boolean isLocked;
    private boolean isHint;

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

    public boolean isHint() {
        return isHint && isLocked;
    }

}
