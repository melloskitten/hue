package com.melloskitten.hue;

import android.app.Activity;
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
    // Color for the text
    private int hexColor;
    private Activity mActivity;

    // FIXME: For the moment this is simply a string.
    private LevelStrategy[] levelStrategies;


    // MARK: CONSTRUCTOR
    public LevelAdapter(Context c, int hexColor, Activity mActivity,
                        LevelStrategy[] levelStrategies) {
        this.mContext = c;
        this.hexColor = hexColor;
        this.mActivity = mActivity;
        this.levelStrategies = levelStrategies;
    }


    // MARK: OVERRIDE METHODS
    @Override
    public int getCount() {
        return levelStrategies.length;
    }

    @Override
    public Object getItem(int i) {
        return levelStrategies[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        // Make the buttons a square.
        GridView gridView = (GridView) viewGroup;
        int size = gridView.getRequestedColumnWidth();
        Button button = new Button(mContext);
        button.setLayoutParams(new GridView.LayoutParams(size, size));

        // Adjust the view.
        button.setBackground(mContext.getDrawable(R.drawable.circle));
        button.setText(String.valueOf(position+1));
        button.setTextColor(hexColor);

        // Add the onclick listener for every button.
        LevelStrategy levelStrategy = levelStrategies[position];
        button.setOnClickListener(new OnClickCreateLevelListener(mActivity, GameActivity.class, levelStrategy));

        return button;
    }
}


