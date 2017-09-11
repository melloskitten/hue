package com.melloskitten.hue;


import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    }

}