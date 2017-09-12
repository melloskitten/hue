package com.melloskitten.hue;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        TextView header = (TextView) findViewById(R.id.main_menu_header);
        Button start_game = (Button) findViewById(R.id.main_menu_start_game);
        Button how_to = (Button) findViewById(R.id.main_menu_how_to);
        Button about = (Button) findViewById(R.id.main_menu_about_us);
        Typeface font = Typeface.createFromAsset(getAssets(), "roboto-thin.ttf");
        header.setTypeface(font);
        start_game.setTypeface(font);
        how_to.setTypeface(font);
        about.setTypeface(font);


        start_game.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent myIntent = new Intent(MainMenuActivity.this, MenuCollectionActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                MainMenuActivity.this.startActivity(myIntent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        how_to.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, HowToActivity.class);
                MainMenuActivity.this.startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

        about.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainMenuActivity.this, AboutActivity.class);
                MainMenuActivity.this.startActivity(intent);
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            }
        });

    }
}
