package com.melloskitten.hue;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

/**
 * Created by sandra on 08.09.17.
 */


public class TileAdapter extends BaseAdapter {
    private Context mContext;
    private ColorTile[] colorTiles;

    public TileAdapter (Context c, ColorTile[] colorTiles) {
        this.mContext = c;
        this.colorTiles = colorTiles;
    }

    @Override
    public int getCount() {
        return colorTiles.length;
    }

    @Override
    public Object getItem(int i) {
        return colorTiles[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView tileView = new TextView(mContext);

        if (colorTiles[position] == null) {
            tileView.setBackgroundColor(Color.BLACK);

            // FIXME: Fix this standardized height
            int maxHeight = colorTiles.length / 5;
            tileView.setHeight(viewGroup.getHeight()/maxHeight);
            return tileView;
        }

        ColorTile curTile = colorTiles[position];
        // Remember, we're only displaying the current color, not the real color
        // The real color is being used to check if the tile is in its correct
        // place.
        Color curCol = curTile.getCurColor();
        tileView.setBackgroundColor(Color.rgb(curCol.red(), curCol.green(), curCol.blue()));

        if (curTile.isHint()) {
            // just display it black or smth
            tileView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tileView.setText("X");
        }

        // FIXME: Fix this standardized height
        int maxHeight = colorTiles.length / 5;
        tileView.setHeight(viewGroup.getHeight()/maxHeight);
        return tileView;
    }

    // Swap method for changing two Tiles
    public void swap(int oldPos, int newPos) {
        ColorTile temp = colorTiles[oldPos];
        colorTiles[oldPos] = colorTiles[newPos];
        colorTiles[newPos] = temp;

        this.notifyDataSetChanged();

    }

}


