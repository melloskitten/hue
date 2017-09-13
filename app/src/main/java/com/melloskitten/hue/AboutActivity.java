package com.melloskitten.hue;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

// Activity that holds the About screen information.
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        setUp();
    }

    private void setUp() {
        TextView how_to_desc = (TextView) findViewById(R.id.about_desc);
        TextView how_to_header = (TextView) findViewById(R.id.about_header);
        Typeface font = Typeface.createFromAsset(getAssets(), "roboto-thin.ttf");
        how_to_desc.setTypeface(font);
        how_to_header.setTypeface(font);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
