package com.melloskitten.hue;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

        /*Button easy_lvl_1 = (Button) getView().findViewById(R.id.easy_1);
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
        easy_lvl_3.setOnClickListener(new OnClickCreateLevelListener(getActivity(), GameActivity.class, lvl_3_strategy));*/
    }


    // MARK: HELPER METHODS
    @NonNull
    private View setUpView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.activity_easy_menu, container, false);
        TextView textView = (TextView) view.findViewById(R.id.easy_menu_header);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "roboto-thin.ttf");
        textView.setTypeface(font);


        GridView gridView = (GridView) view.findViewById(R.id.easy_menu_gridview);
        // FIXME: Hardcoded string here

        String [] dummy = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"};
        if(gridView != null){
            gridView.setAdapter(new LevelAdapter(getActivity(), dummy));
        }


        return view;
    }

}

