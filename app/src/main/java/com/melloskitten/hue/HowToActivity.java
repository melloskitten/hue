package com.melloskitten.hue;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class HowToActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);

        TextView how_to_desc = (TextView) findViewById(R.id.how_to_desc);
        TextView how_to_header = (TextView) findViewById(R.id.how_to_header);
        Typeface font = Typeface.createFromAsset(getAssets(), "roboto-thin.ttf");
        how_to_desc.setTypeface(font);
        how_to_header.setTypeface(font);


        how_to_desc.setText(R.string.how_to_desc);
    }
}
