package com.melloskitten.hue;

import android.view.MotionEvent;
import android.view.View;
import android.widget.GridView;

// MARK: Modified onTouch Listener for the GridView.
public class CustomOnTouchListener implements View.OnTouchListener {

    // MARK: VARS
    int oldPos;
    GridView gridView;

    // MARK: CONSTRUCTOR
    public CustomOnTouchListener(int oldPos, GridView gridView) {
        this.oldPos = oldPos;
        this.gridView = gridView;
    }

    // MARK: ONTOUCH METHODS
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                oldPos = gridView.pointToPosition(x, y);

                View tileView = gridView.getChildAt(oldPos);
                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(tileView);

                ColorTile colorTile = (ColorTile) gridView.getAdapter().getItem(oldPos);

                if (colorTile.isHint()) {
                    return false;
                }

                tileView.startDrag(null, myShadow, null, 0);
        }
        return false;
    }
}
