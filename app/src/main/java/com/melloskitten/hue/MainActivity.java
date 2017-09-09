package com.melloskitten.hue;

import android.graphics.Color;
import android.graphics.Interpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static TileAdapter tileAdapter;
    public static int newX = 0;
    public static int newY = 0;
    public static int oldPos = 0;

    Color COL_1 = Color.valueOf(Color.parseColor("#555b6e"));
    Color COL_2 = Color.valueOf(Color.parseColor("#ffd6ba"));
    Color COL_3 = Color.valueOf(Color.parseColor("#89b0ae"));
    Color COL_4 = Color.valueOf(Color.parseColor("#bee3db"));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridView = (GridView) findViewById(R.id.gridView);



        String[] LEVEL_1 = {"#e3e3e3", "#FFFFFF", "#000000", "#e300e3", "#e3e3e3", "#e3e3e3", "#FFFFFF", "#000000", "#e3e303", "#003000", "#e3e3e3", "#FFFFFF", "#e3e3e3", "#FFFFFF", "#003000"};

        ColorTileGenerator generator = new ColorTileGenerator(COL_1, COL_2, COL_3, COL_4, 5,3);
        //generator.generateColorRow(COL_1, COL_2, 5);
        //generator.generateColorColumn(COL_1, COL_2, 2);
        generator.generateColorTiles();

        gridView.setAdapter(new TileAdapter(this, generator.generatedColors));
        gridView.setNumColumns(5);



        gridView.setOnTouchListener(new View.OnTouchListener() {
                                        @Override
                                        public boolean onTouch(View view, MotionEvent motionEvent) {

                                            switch (motionEvent.getAction()){
                                                case MotionEvent.ACTION_DOWN:
                                                    int x = (int) motionEvent.getX();
                                                    int y = (int) motionEvent.getY();
                                                    oldPos = gridView.pointToPosition(x,y);
                                                    View tileView = gridView.getChildAt(oldPos);


                                                    View.DragShadowBuilder myShadow = new View.DragShadowBuilder(tileView);

                                                    tileView.startDrag(null, myShadow, null, 0);

                                            }



                                            return false;
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
                                //Toast.makeText(view.getContext(), "The drop has started.", Toast.LENGTH_LONG).show();

                                int oldX = (int) dragEvent.getX();
                                int oldY = (int) dragEvent.getY();

                                oldPos = gridView.pointToPosition(oldX, oldY);
                                //String oldPosString = Integer.toString(oldPos);
                                //Toast.makeText(view.getContext(), "The action started from  : " + oldPosString, Toast.LENGTH_LONG).show();
                                return true;

                            case DragEvent.ACTION_DRAG_ENTERED:
                                return true;

                            case DragEvent.ACTION_DRAG_LOCATION:
                                return true;

                            case DragEvent.ACTION_DROP:
                                newX = (int) dragEvent.getX();
                                newY = (int) dragEvent.getY();
                                return true;

                            case DragEvent.ACTION_DRAG_ENDED:
                                // Does a getResult(), and displays what happened.
                                if (dragEvent.getResult()) {
                                    //Toast.makeText(view.getContext(), "The drop was handled.", Toast.LENGTH_LONG).show();

                                    int position = gridView.pointToPosition(newX, newY);

                                    GridView gridView = (GridView) view;
                                    TextView textView = (TextView) gridView.getChildAt(position);
                                    int index = gridView.pointToPosition(newX, newY);

                                    TileAdapter tileAdapter = (TileAdapter) gridView.getAdapter();
                                    tileAdapter.swap(index, oldPos);

                                    //String indexText = String.valueOf(index);
                                    //textView.setText(indexText);
                                    //textView.setBackgroundColor(Color.RED);


                                } else {
                                    //Toast.makeText(view.getContext(), "The drop didn't work.", Toast.LENGTH_LONG).show();

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
