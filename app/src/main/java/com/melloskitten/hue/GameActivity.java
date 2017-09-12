package com.melloskitten.hue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {


    // MARK: VARS
    private static TileAdapter tileAdapter;
    private static ColorTileGenerator tileGenerator;
    private String[] hexCodes;
    private int rowLength;
    private int columnLength;



    public static int newX = 0;
    public static int newY = 0;
    public static int oldPos = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // Set all needed parameters
        setParamsForColGeneration();



        final GridView gridView = (GridView) findViewById(R.id.gridView);


        tileGenerator = new ColorTileGenerator(hexCodes[0], hexCodes[1], hexCodes[2], hexCodes[3], 5,3);

        //ColorTileGenerator generator = new ColorTileGenerator(COL_1, COL_2, COL_3, COL_4, 5,3);



        tileGenerator.generateColorTiles();
        ColorTile[] generatedColorTiles = tileGenerator.generatedColorTiles;
        generatedColorTiles = ColorTileScrambler.scramble(generatedColorTiles);

        gridView.setAdapter(new TileAdapter(this, generatedColorTiles, 5));
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

                                                    ColorTile colorTile = (ColorTile) gridView.getAdapter().getItem(oldPos);

                                                    if (colorTile.isHint()) {
                                                    return false;
                                                }

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

                                int oldX = (int) dragEvent.getX();
                                int oldY = (int) dragEvent.getY();

                                oldPos = gridView.pointToPosition(oldX, oldY);

                                return true;

                            case DragEvent.ACTION_DRAG_ENTERED:
                                return true;

                            case DragEvent.ACTION_DRAG_LOCATION:
                                return true;

                            case DragEvent.ACTION_DROP:
                                newX = (int) dragEvent.getX();
                                newY = (int) dragEvent.getY();

                                int pos = gridView.pointToPosition(newX, newY);
                                ColorTile colorTile = (ColorTile) gridView.getAdapter().getItem(pos);
                                if (colorTile.isHint()) {
                                    return false;
                                }

                                return true;

                            case DragEvent.ACTION_DRAG_ENDED:
                                // Does a getResult(), and displays what happened.
                                if (dragEvent.getResult()) {

                                    int position = gridView.pointToPosition(newX, newY);

                                    GridView gridView = (GridView) view;
                                    TextView textView = (TextView) gridView.getChildAt(position);
                                    int index = gridView.pointToPosition(newX, newY);

                                    TileAdapter tileAdapter = (TileAdapter) gridView.getAdapter();

                                    tileAdapter.swap(index, oldPos);


                                    if (tileAdapter.isPuzzleSolved()) {
                                        Toast.makeText(view.getContext(),
                                                "The game is finished!", Toast.LENGTH_SHORT).show();
                                    }


                                } else {

                                }

                                return true;


                        }
                        return false;
                    }
                });

    }

    private void setParamsForColGeneration() {
        Intent intent = getIntent();
        hexCodes = intent.getStringArrayExtra("hexCodes");
        rowLength = intent.getIntExtra("rowLength", 0);
        columnLength = intent.getIntExtra("columnLength", 0);
    }


    public void swap(int pos) {

        TileAdapter updater = tileAdapter;


    }
}
