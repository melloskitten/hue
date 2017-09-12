package com.melloskitten.hue;


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

        // FIXME: logic for this fragment is created here

        Button easy_lvl_1 = (Button) getView().findViewById(R.id.easy_1);

        easy_lvl_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), GameActivity.class);


                String[] hexCodes = {getString(R.string.easy_lvl_1_col1), getString(R.string.easy_lvl_1_col2),
                                    getString(R.string.easy_lvl_1_col3), getString(R.string.easy_lvl_1_col4)};

                intent.putExtra("hexCodes", hexCodes);
                intent.putExtra("rowLength", 3);
                intent.putExtra("columnLength", 6);

                EasyMenuFragment.this.startActivity(intent);
                getActivity().overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });


    }

}