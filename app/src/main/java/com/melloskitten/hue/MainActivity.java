package com.melloskitten.hue;

import android.graphics.Color;
import android.graphics.Interpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static TileAdapter tileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridView = (GridView) findViewById(R.id.gridView);

        String[] LEVEL_1 = {"#e3e3e3", "#FFFFFF", "#000000", "#e300e3", "#e3e3e3", "#e3e3e3", "#FFFFFF", "#000000", "#e3e303", "#003000", "#e3e3e3", "#FFFFFF", "#e3e3e3", "#FFFFFF", "#003000"};


        gridView.setAdapter(new TileAdapter(this, LEVEL_1));
        gridView.setNumColumns(5);


        // Long Press
        gridView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                View.DragShadowBuilder myShadow = new View.DragShadowBuilder(view);

                view.startDrag(null, myShadow, null, 0);

                return true;
            }
        });

        gridView.setOnDragListener(new AdapterView.OnDragListener() {
            @Override
            public boolean onDrag(View view, DragEvent dragEvent) {

                final int action = dragEvent.getAction();

                // Handles each of the expected events:
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // this means a dragging has just started from that specific view
                        Toast.makeText(view.getContext(), "The drop has started.", Toast.LENGTH_LONG).show();
                        return true;

                    case DragEvent.ACTION_DRAG_ENTERED:
                        return true;

                    case DragEvent.ACTION_DRAG_LOCATION:
                        return true;

                    case DragEvent.ACTION_DROP:
                        return true;

                    case DragEvent.ACTION_DRAG_ENDED:
                        // Does a getResult(), and displays what happened.
                        if (dragEvent.getResult()) {
                            Toast.makeText(view.getContext(), "The drop was handled.", Toast.LENGTH_LONG).show();

                            int position = gridView.pointToPosition((int) dragEvent.getX(), (int) dragEvent.getY());


                            GridView gridView = (GridView) view;
                            TextView textView = (TextView) gridView.getChildAt(position);
                            textView.setBackgroundColor(Color.MAGENTA);


                        } else {
                            Toast.makeText(view.getContext(), "The drop didn't work.", Toast.LENGTH_LONG).show();

                        }

                        return true;


                }
                return false;
            }
        });

    }


    public void swap(int pos) {

        TileAdapter updater = tileAdapter;


    }
}
