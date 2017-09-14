package com.melloskitten.hue;



import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.GridView;
import android.widget.TextView;

// Fragment that holds all the Easy Levels.
public class EasyMenuFragment extends Fragment {

    // MARK: ONCREATEVIEW
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return setUpView(inflater, container);
    }


    // MARK: ONACTIVITYCREATED
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    // MARK: HELPER METHODS
    @NonNull
    private View setUpView(LayoutInflater inflater, ViewGroup container) {

        View view = setFont(inflater, container);

        // Fill the gridview with the correct levels.
        GridView gridView = (GridView) view.findViewById(R.id.easy_menu_gridview);

        // Get the Easy Levels
        LevelStrategy[] strategies = LevelStrategyFactory.createEasyLevelStrategies(getActivity());

        // Set the Adapter.
        gridView.setAdapter(new LevelAdapter(getActivity(), getResources().getColor(R.color.easy_level_text), getActivity(), strategies));

        return view;
    }

    @NonNull
    private View setFont(LayoutInflater inflater, ViewGroup container) {
        // Set the font correctly.
        View view = inflater.inflate(R.layout.activity_easy_menu, container, false);
        TextView textView = (TextView) view.findViewById(R.id.easy_menu_header);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "roboto-thin.ttf");
        textView.setTypeface(font);
        return view;
    }

}

