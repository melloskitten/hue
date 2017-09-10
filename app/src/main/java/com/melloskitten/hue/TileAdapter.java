package com.melloskitten.hue;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by sandra on 08.09.17.
 */

// Adapter for the ColorTile array for each level of the game.
public class TileAdapter extends BaseAdapter {

    // MARK: VARS
    private Context mContext;
    private ColorTile[] colorTiles;
    // Height of each cell. (Since GridViews are adjusted dynamically without my control)
    private int height;


    // MARK: Constructor
    public TileAdapter(Context c, ColorTile[] colorTiles, int height) {
        this.mContext = c;
        this.colorTiles = colorTiles;
        this.height = height;
    }


    // MARK: Overriden Methods from BaseAdapter
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

        // Set the maximum height for all tiles
        int maxHeight = colorTiles.length / height;
        tileView.setHeight(viewGroup.getHeight() / maxHeight);

        // In case there are not enough tiles for a level, just fill it default with black.
        if (colorTiles[position] == null) {
            setViewForTile(new Color(), tileView, null);
            return tileView;
        }

        ColorTile curTile = colorTiles[position];
        // Remember, we're only displaying the current color, not the real color
        // The real color is being used to check if the tile is in its correct
        // place.

        setViewForTile(curTile.getCurColor(), tileView, curTile);

        return tileView;
    }


    // MARK: Helper Methods
    private void setViewForTile(Color color, TextView tileView, ColorTile curTile) {

        // FIXME: Make Hint Tiles more prominent, e.g. with circle on them or smth.
        if (curTile.isHint() && curTile != null) {
            tileView.setText("X");
            tileView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tileView.setGravity(Gravity.CENTER);
        }

        tileView.setBackgroundColor(Color.rgb(color.red(), color.green(), color.blue()));
    }

    // Swap method for changing two Tiles
    public void swap(int oldPos, int newPos) {

        ColorTile temp = colorTiles[oldPos];
        colorTiles[oldPos] = colorTiles[newPos];
        colorTiles[newPos] = temp;

        this.notifyDataSetChanged();

    }

}


