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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final GridView gridView = (GridView) findViewById(R.id.gridView);

        String[] LEVEL_1 = {"#e3e3e3", "#FFFFFF", "#000000", "#e300e3", "#e3e3e3", "#e3e3e3", "#FFFFFF", "#000000", "#e3e303", "#003000", "#e3e3e3", "#FFFFFF", "#e3e3e3", "#FFFFFF", "#003000"};


        gridView.setAdapter(new TileAdapter(this, LEVEL_1));
        gridView.setNumColumns(5);



        // FIXME: Removing longpress for the moment cause it takes too long imo


        gridView.setOnTouchListener(new View.OnTouchListener() {
                                        @Override
                                        public boolean onTouch(View view, MotionEvent motionEvent) {

                                            switch (motionEvent.getAction()){
                                                case MotionEvent.ACTION_DOWN:
                                                    //Toast.makeText(view.getContext(), "The touch started...", Toast.LENGTH_SHORT).show();

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
                                    Toast.makeText(view.getContext(), "The drop was handled.", Toast.LENGTH_LONG).show();

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
