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
    private Color[] colors;

    public TileAdapter (Context c, Color[] colorCodes) {
        this.mContext = c;
        this.colors = colorCodes;
    }

    @Override
    public int getCount() {
        return colors.length;
    }

    @Override
    public Object getItem(int i) {
        return colors[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView tileView = new TextView(mContext);

        if (colors[position] == null) {
            tileView.setBackgroundColor(Color.BLACK);

            // FIXME: Fix this standardized height
            int maxHeight = colors.length / 5;
            tileView.setHeight(viewGroup.getHeight()/maxHeight);
            return tileView;
        }

        Color curCol = colors[position];
        tileView.setBackgroundColor(Color.rgb(curCol.red(), curCol.green(), curCol.blue()));

        // FIXME: Fix this standardized height
        int maxHeight = colors.length / 5;
        tileView.setHeight(viewGroup.getHeight()/maxHeight);
        return tileView;
    }

    // Swap method for changing two Tiles
    public void swap(int oldPos, int newPos) {
        Color temp = colors[oldPos];
        colors[oldPos] = colors[newPos];
        colors[newPos] = temp;

        this.notifyDataSetChanged();


    }

}


