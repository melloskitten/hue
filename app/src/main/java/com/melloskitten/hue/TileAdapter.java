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
    private String[] colorCodes;

    public TileAdapter (Context c, String[] colorCodes) {
        this.mContext = c;
        this.colorCodes = colorCodes;
    }

    @Override
    public int getCount() {
        return colorCodes.length;
    }

    @Override
    public Object getItem(int i) {
        return colorCodes[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        TextView dummyTextView = new TextView(mContext);
        dummyTextView.setBackgroundColor(Color.parseColor(colorCodes[position]));
        int maxHeight = colorCodes.length/5; // divided by the number of columns
        dummyTextView.setHeight(viewGroup.getHeight()/maxHeight);
        return dummyTextView;
    }
}


