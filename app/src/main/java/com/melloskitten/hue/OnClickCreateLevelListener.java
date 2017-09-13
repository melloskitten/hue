package com.melloskitten.hue;

import android.app.Activity;
import android.content.Intent;
import android.view.View;

// Listener for creating a new level, sets up an intent for the game screen using the
// given strategy.
public class OnClickCreateLevelListener implements View.OnClickListener {

    // MARK: VARS
    private Activity firstActivity;
    private Class<GameActivity> secondActivity;
    private LevelStrategy strategy;


    // MARK: CONSTRUCTOR
    public OnClickCreateLevelListener(Activity firstActivity, Class<GameActivity> secondActivity, LevelStrategy strategy) {
        this.firstActivity = firstActivity;
        this.secondActivity = secondActivity;
        this.strategy = strategy;
    }


    // MARK: ONCLICK
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(firstActivity, secondActivity);

        intent.putExtra("hexCodes", strategy.hexCodes);
        intent.putExtra("rowLength", strategy.rowLength);
        intent.putExtra("columnLength", strategy.columnLength);
        intent.putExtra("hintMode", strategy.hintMode);

        firstActivity.startActivity(intent);
        firstActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}
