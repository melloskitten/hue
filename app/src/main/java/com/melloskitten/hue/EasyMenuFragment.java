package com.melloskitten.hue;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


public class EasyMenuFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_easy_menu, container, false);
        TextView textView = (TextView) view.findViewById(R.id.easy_menu_header);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "roboto-thin.ttf");
        textView.setTypeface(font);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button easy_lvl_1 = (Button) getView().findViewById(R.id.easy_1);
        Button easy_lvl_2 = (Button) getView().findViewById(R.id.easy_2);
        Button easy_lvl_3 = (Button) getView().findViewById(R.id.easy_3);


        // Set the Easy Strategies
        LevelStrategy lvl_1_strategy = new EasyLevelStrategy(new String[]{getString(R.string.easy_lvl_1_col1), getString(R.string.easy_lvl_1_col2),
                getString(R.string.easy_lvl_1_col3), getString(R.string.easy_lvl_1_col4)});

        LevelStrategy lvl_2_strategy = new EasyLevelStrategy(new String[]{getString(R.string.easy_lvl_2_col1), getString(R.string.easy_lvl_2_col2),
                getString(R.string.easy_lvl_2_col3), getString(R.string.easy_lvl_2_col4)});

        LevelStrategy lvl_3_strategy = new EasyLevelStrategy(new String[]{getString(R.string.easy_lvl_3_col1), getString(R.string.easy_lvl_3_col2),
                getString(R.string.easy_lvl_3_col3), getString(R.string.easy_lvl_3_col4)});

        // Add the event listeners
        easy_lvl_1.setOnClickListener(new OnClickCreateLevelListener(getActivity(), GameActivity.class, lvl_1_strategy));
        easy_lvl_2.setOnClickListener(new OnClickCreateLevelListener(getActivity(), GameActivity.class, lvl_2_strategy));
        easy_lvl_3.setOnClickListener(new OnClickCreateLevelListener(getActivity(), GameActivity.class, lvl_3_strategy));




    }

}

class OnClickCreateLevelListener implements View.OnClickListener {

    private Activity firstActivity;
    private Class<GameActivity> secondActivity;
    private LevelStrategy strategy;

    public OnClickCreateLevelListener(Activity firstActivity, Class<GameActivity> secondActivity, LevelStrategy strategy) {
        this.firstActivity = firstActivity;
        this.secondActivity = secondActivity;
        this.strategy = strategy;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(firstActivity, secondActivity);

        intent.putExtra("hexCodes", strategy.hexCodes);
        intent.putExtra("rowLength", strategy.rowLength);
        intent.putExtra("columnLength", strategy.columnLength);

        firstActivity.startActivity(intent);
        firstActivity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

}