package com.melloskitten.hue;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class GameActivity extends AppCompatActivity {


    // MARK: VARS
    private String[] hexCodes;
    private int rowLength;
    private int columnLength;
    private int hintMode;
    public static int oldPos = 0;
    public static int newPos = 0;
    GridView gridView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);


        // Set all needed parameters for generating the game field.
        setParamsForColGeneration();

        // Create the game field with given params.
        ColorTile[] generatedColorTiles = ColorTileFactory.getGameColorTiles(hexCodes,
                columnLength, rowLength, hintMode);


        // Set adapter and init gridView.
        gridView = setUpGridView(generatedColorTiles);


        // Init the onTouch Listener for starting to drag around the tile.
        gridView.setOnTouchListener(new CustomOnTouchListener(oldPos, gridView));

        // Init the onDrag Listener for dropping the tile.
        gridView.setOnDragListener(new CustomOnDragListener(oldPos, newPos, gridView));
    }

    // MARK: Helper Methods.
    private void setParamsForColGeneration() {
        Intent intent = getIntent();
        hexCodes = intent.getStringArrayExtra("hexCodes");
        rowLength = intent.getIntExtra("rowLength", 0);
        columnLength = intent.getIntExtra("columnLength", 0);
        hintMode = intent.getIntExtra("hintMode", 0);
    }

    private GridView setUpGridView(ColorTile[] generatedColorTiles) {
        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(new TileAdapter(this, generatedColorTiles, columnLength));
        gridView.setNumColumns(columnLength);
        return gridView;
    }

}


// MARK: Modified onTouch Listener for the GridView.
class CustomOnTouchListener implements View.OnTouchListener {

    // MARK: VARS
    int oldPos;
    GridView gridView;

    // MARK: CONSTRUCTOR
    public CustomOnTouchListener(int oldPos, GridView gridView) {
        this.oldPos = oldPos;
        this.gridView = gridView;
    }

    // MARK: ONTOUCH METHODS
    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {

        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) motionEvent.getX();
                int y = (int) motionEvent.getY();
                oldPos = gridView.pointToPosition(x, y);

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
}

// MARK: Modified OnDrag Listener for the GridView.
class CustomOnDragListener implements AdapterView.OnDragListener {

    // MARK: VARS
    int oldPos;
    int newPos;
    GridView gridView;

    // MARK: CONSTRUCTOR
    public CustomOnDragListener(int oldPos, int newPos, GridView gridView) {
        this.oldPos = oldPos;
        this.newPos = newPos;
        this.gridView = gridView;
    }

    // MARK: ONDRAG METHODS
    @Override
    public boolean onDrag(final View view, DragEvent dragEvent) {

        final int action = dragEvent.getAction();

        switch (action) {
            case DragEvent.ACTION_DRAG_STARTED:

                int oldX = (int) dragEvent.getX();
                int oldY = (int) dragEvent.getY();

                oldPos = gridView.pointToPosition(oldX, oldY);
                return true;

            case DragEvent.ACTION_DROP:

                int newX = (int) dragEvent.getX();
                int newY = (int) dragEvent.getY();

                newPos = gridView.pointToPosition(newX, newY);
                ColorTile colorTile = (ColorTile) gridView.getAdapter().getItem(newPos);

                if (colorTile.isHint()) {
                    return false;
                }

                return true;

            case DragEvent.ACTION_DRAG_ENDED:

                if (dragEvent.getResult()) {

                    TileAdapter tileAdapter = (TileAdapter) gridView.getAdapter();
                    tileAdapter.swap(newPos, oldPos);


                    if (tileAdapter.isPuzzleSolved()) {

                        showFinishedGamePrompt(view);

                    }
                }
                return true;
        }
        return false;
    }

    private void showFinishedGamePrompt(View view) {
        final AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(view.getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(view.getContext());
        }
        builder.setTitle("")
                .setMessage("Congratulations, you finished the level!")
                .setNeutralButton("Done", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            getActivity().finish();
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        } catch (NoSuchMethodException e) {
                            e.printStackTrace();
                        } catch (NoSuchFieldException e) {
                            e.printStackTrace();
                        } catch (IllegalAccessException e) {
                            e.printStackTrace();
                        } catch (InvocationTargetException e) {
                            e.printStackTrace();
                        }
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static Activity getActivity() throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException {
        Class activityThreadClass = Class.forName("android.app.ActivityThread");
        Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(null);
        Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
        activitiesField.setAccessible(true);

        Map<Object, Object> activities = (Map<Object, Object>) activitiesField.get(activityThread);
        if (activities == null)
            return null;

        for (Object activityRecord : activities.values()) {
            Class activityRecordClass = activityRecord.getClass();
            Field pausedField = activityRecordClass.getDeclaredField("paused");
            pausedField.setAccessible(true);
            if (!pausedField.getBoolean(activityRecord)) {
                Field activityField = activityRecordClass.getDeclaredField("activity");
                activityField.setAccessible(true);
                Activity activity = (Activity) activityField.get(activityRecord);
                return activity;
            }
        }

        return null;
    }
}