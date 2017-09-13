package com.melloskitten.hue;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

// Fragment that holds all the Intermediate Levels.
public class IntermediateMenuFragment extends Fragment {

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

        Button intermediate_lvl_1 = (Button) getView().findViewById(R.id.intermediate_1);
        Button intermediate_lvl_2 = (Button) getView().findViewById(R.id.intermediate_2);
        Button intermediate_lvl_3 = (Button) getView().findViewById(R.id.intermediate_3);


        // Set the Intermediate Strategies
        LevelStrategy lvl_1_strategy = new IntermediateLevelStrategy(new String[]{getString(R.string.intermediate_lvl_1_col1), getString(R.string.intermediate_lvl_1_col2),
                getString(R.string.intermediate_lvl_1_col3), getString(R.string.intermediate_lvl_1_col4)},2);
        LevelStrategy lvl_2_strategy = new IntermediateLevelStrategy(new String[]{getString(R.string.intermediate_lvl_2_col1), getString(R.string.intermediate_lvl_2_col2),
                getString(R.string.intermediate_lvl_2_col3), getString(R.string.intermediate_lvl_2_col4)});
        LevelStrategy lvl_3_strategy = new IntermediateLevelStrategy(new String[]{getString(R.string.intermediate_lvl_3_col1), getString(R.string.intermediate_lvl_3_col2),
                getString(R.string.intermediate_lvl_3_col3), getString(R.string.intermediate_lvl_3_col4)}, 1);


        // Add the event listeners
        intermediate_lvl_1.setOnClickListener(new OnClickCreateLevelListener(getActivity(), GameActivity.class, lvl_1_strategy));
        intermediate_lvl_2.setOnClickListener(new OnClickCreateLevelListener(getActivity(), GameActivity.class, lvl_2_strategy));
        intermediate_lvl_3.setOnClickListener(new OnClickCreateLevelListener(getActivity(), GameActivity.class, lvl_3_strategy));

    }


    // MARK: HELPER METHODS
    @NonNull
    private View setUpView(LayoutInflater inflater, ViewGroup container) {
        View view = inflater.inflate(R.layout.activity_intermediate_menu, container, false);
        TextView textView = (TextView) view.findViewById(R.id.intermediate_menu_header);
        Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "roboto-thin.ttf");
        textView.setTypeface(font);
        return view;
    }

}