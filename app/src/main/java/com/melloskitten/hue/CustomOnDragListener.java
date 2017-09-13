package com.melloskitten.hue;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.view.DragEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

// MARK: Modified OnDrag Listener for the GridView.
public class CustomOnDragListener implements AdapterView.OnDragListener {

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


    // MARK: HELPER METHODS
    // Shows the AlertDialog when the user has successfully solved a level and navigates
    // them back to the Menu Screen.
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

    // Code Snippet taken from https://stackoverflow.com/a/28423385/7217195 for getting the
    // Activity ... needed for finishing the activity and navigating to the Main Menu.
    public static Activity getActivity() throws ClassNotFoundException, NoSuchMethodException,
            NoSuchFieldException, IllegalAccessException, InvocationTargetException {

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
