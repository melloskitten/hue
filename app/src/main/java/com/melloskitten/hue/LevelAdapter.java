package com.melloskitten.hue;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;

/**
 * Created by sandra on 14.09.17.
 */

public class LevelAdapter extends BaseAdapter {

    // MARK: VARS
    private Context mContext;

    // FIXME: For the moment this is simply a string.
    private String[] levelDescription;



    // MARK: CONSTRUCTOR
    public LevelAdapter(Context c, String[] levelDescription) {
        this.mContext = c;
        this.levelDescription = levelDescription;

    }


    // MARK: OVERRIDE METHODS
    @Override
    public int getCount() {
        return levelDescription.length;
    }

    @Override
    public Object getItem(int i) {
        return levelDescription[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {


        GridView gridView = (GridView) viewGroup;
        int size = gridView.getRequestedColumnWidth();

        Button button = new Button(mContext);
        button.setLayoutParams(new GridView.LayoutParams(size, size));
        button.setBackground(mContext.getDrawable(R.drawable.circle));
        button.setText(levelDescription[position]);
        button.setTextColor(Color.BLACK);



        return button;
    }
}


