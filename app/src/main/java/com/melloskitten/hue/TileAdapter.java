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


    // MARK: CONSTRUCTOR
    public TileAdapter(Context c, ColorTile[] colorTiles, int height) {
        this.mContext = c;
        this.colorTiles = colorTiles;
        this.height = height;
    }


    // MARK: OVERRIDE METHODS
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
        setMaxHeight(viewGroup, tileView);

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
            tileView.setText("\u25A0");
            tileView.setTextColor(Color.BLACK);
            tileView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tileView.setGravity(Gravity.CENTER);
        }

        tileView.setBackgroundColor(Color.rgb(color.red(), color.green(), color.blue()));
    }

    // Swap method for changing two tile's current colors.
    public void swap(int oldPos, int newPos) {

        Color temp = colorTiles[oldPos].getCurColor();
        colorTiles[oldPos].setCurColor(colorTiles[newPos].getCurColor());
        colorTiles[newPos].setCurColor(temp);

        this.notifyDataSetChanged();

    }

    // Checks if the puzzle is solved yet.
    public boolean isPuzzleSolved() {

        for (int i = 0; i < colorTiles.length; i++) {
            if (!colorTiles[i].isSolved()) {
                return false;
            }
        }
        return true;
    }

    // Set the maximal height for a color tile.
    private void setMaxHeight(ViewGroup viewGroup, TextView tileView) {
        int maxHeight = colorTiles.length / height;
        tileView.setHeight(viewGroup.getHeight() / maxHeight);
    }

}


