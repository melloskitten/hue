package com.melloskitten.hue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static TileAdapter tileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridView);

        String[] LEVEL_1 = {"#e3e3e3", "#FFFFFF", "#000000", "#e300e3", "#e3e3e3","#e3e3e3", "#FFFFFF", "#000000", "#e3e303", "#003000", "#e3e3e3", "#FFFFFF", "#e3e3e3", "#FFFFFF", "#e3e3e3"};


        gridView.setAdapter(new TileAdapter(this, LEVEL_1));
        gridView.setNumColumns(5);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
                swap();
            }
        });


    }


    public void swap(int pos) {

        TileAdapter updater = tileAdapter;



    }
}
