package com.melloskitten.hue;

import android.graphics.Color;

/**
 * Created by sandra on 09.09.17.
 */

// This class creates an array of ColorTiles, which are used as the basis of the level.
public class ColorTileGenerator {

    // MARK: VARS
    // This array holds the generated Tiles.
    public ColorTile[] generatedColorTiles;

    // Needed for properly generating interpolated colors.
    private final int columnLength;
    private final int rowLength;

    // There are always 4 Colors needed to generate a Tile set for a rectangular game background.
    // These colors are located in the corners.
    public ColorTile upperLeftTile;
    public ColorTile upperRightTile;
    public ColorTile lowerLeftTile;
    public ColorTile lowerRightTile;


    // MARK: CONSTRUCTORS
    public ColorTileGenerator (ColorTile upperLeftTile, ColorTile upperRightTile,
                               ColorTile lowerLeftTile, ColorTile lowerRightTile,
                               int columnLength, int rowLength) {
        this.upperLeftTile = upperLeftTile;
        this.upperRightTile = upperRightTile;
        this.lowerLeftTile = lowerLeftTile;
        this.lowerRightTile = lowerRightTile;
        this.columnLength = columnLength;
        this.rowLength = rowLength;
        this.generatedColorTiles = new ColorTile[rowLength*columnLength];
    }

    public ColorTileGenerator(Color UPPER_LEFT, Color UPPER_RIGHT,
                              Color LOWER_LEFT, Color LOWER_RIGHT,
                              int columnLength, int rowLength) {

        this.upperLeftTile = new ColorTile(UPPER_LEFT, UPPER_LEFT, false, false);
        this.upperRightTile = new ColorTile(UPPER_RIGHT, UPPER_RIGHT, false, false);
        this.lowerLeftTile = new ColorTile(LOWER_LEFT, LOWER_LEFT, false, false);
        this.lowerRightTile = new ColorTile(LOWER_RIGHT, LOWER_RIGHT, false, false);
        this.rowLength = rowLength;
        this.columnLength = columnLength;
        this.generatedColorTiles = new ColorTile[rowLength*columnLength];
    }

    public ColorTileGenerator(String upper_left, String upper_right,
                              String lower_left, String lower_right,
                              int columnLength, int rowLength) {

        Color UPPER_LEFT = ColorWrapper.prsCol(upper_left);
        Color UPPER_RIGHT = ColorWrapper.prsCol(upper_right);
        Color LOWER_LEFT = ColorWrapper.prsCol(lower_left);
        Color LOWER_RIGHT = ColorWrapper.prsCol(lower_right);

        this.upperLeftTile = new ColorTile(UPPER_LEFT, UPPER_LEFT, false, false);
        this.upperRightTile = new ColorTile(UPPER_RIGHT, UPPER_RIGHT, false, false);
        this.lowerLeftTile = new ColorTile(LOWER_LEFT, LOWER_LEFT, false, false);
        this.lowerRightTile = new ColorTile(LOWER_RIGHT, LOWER_RIGHT, false, false);
        this.rowLength = rowLength;
        this.columnLength = columnLength;
        this.generatedColorTiles = new ColorTile[rowLength*columnLength];

    }


    // MARK: HELPER FUNCTIONS
    // Creates a new ColorTile through the interpolated colors of two ColorTiles
    // with a specific weight.
    public ColorTile interpolateColor (ColorTile start, ColorTile end, float weight) {

        Color startCol = start.getRealColor();
        Color endCol = end.getRealColor();

        float red = startCol.red() + (endCol.red()- startCol.red()) * weight;
        float green = startCol.green() + (endCol.green() - startCol.green()) * weight;
        float blue = startCol.blue() + (endCol.blue() - startCol.blue()) * weight;

        Color middleCol = Color.valueOf(Color.rgb(red, green, blue));
        ColorTile middleTile = new ColorTile(middleCol);
        return middleTile;
    }

    // Generates ColorTiles for one row. (first and last ColorTile in the row are given.)
    public void generateColorRow (ColorTile leftColorTile, ColorTile rightColorTile, int startIndex) {
        float standardWeight = 1 / ((float) (columnLength));

        for (int i = 0; i < columnLength; i++) {
            float weight = i * standardWeight;
            ColorTile interpolatedColorTile = interpolateColor(leftColorTile, rightColorTile, weight);
            generatedColorTiles[startIndex + i] = interpolatedColorTile;
        }

    }

    // Generates ColorTiles for one column. (top and bottom ColorTile in column is given.)
    public void generateColorColumn (ColorTile topColorTile, ColorTile bottomColorTile, int startIndex) {
        float standardWeight = 1 / ((float) (rowLength));

        for (int i = 0; i < (rowLength); i++) {
            float weight = i * standardWeight;
            ColorTile interpolatedColorTile = interpolateColor(topColorTile, bottomColorTile, weight);
            generatedColorTiles[(i * columnLength) + startIndex ] = interpolatedColorTile;
        }
    }

    // Generates the game field based on the respective corner colors.
    // Also adds in the specified Hint ColorTiles (if there are any)
    public ColorTile[] generateColorTiles () {

        // First generate first and last column
        generateColorColumn(upperLeftTile, lowerLeftTile, 0);
        generateColorColumn(upperRightTile, lowerRightTile, columnLength-1);

        // Generate the missing rows
        for (int i = 0; i < rowLength; i++) {

            ColorTile leftColorTile = generatedColorTiles[i * columnLength];
            ColorTile rightColorTile = generatedColorTiles[i * columnLength + (columnLength-1)];
            generateColorRow(leftColorTile, rightColorTile, i * columnLength);
        }
        setHintTiles();

        return generatedColorTiles;
    }


    // FIXME: For now, the hinted ColorTiles are always going to be in the corners. (SEMI HARD)
    // Locks ColorTiles and makes hints that are supposed to help the user and make the game easier.
    public void setHintTiles() {

        generatedColorTiles[0].setHint(true);
        generatedColorTiles[columnLength-1].setHint(true);

        generatedColorTiles[generatedColorTiles.length-1].setHint(true);
        generatedColorTiles[generatedColorTiles.length - columnLength].setHint(true);

    }



}

