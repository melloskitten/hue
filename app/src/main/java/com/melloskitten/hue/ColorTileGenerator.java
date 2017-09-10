package com.melloskitten.hue;

import android.graphics.Color;

/**
 * Created by sandra on 09.09.17.
 */

// TODO: Fix the desc of this class its horrendous
// A Class for generating a Color Table based on color inputs in #XXXXXX String format and the
// number of columns and rows of the level.

public class ColorTileGenerator {

    public ColorTile[] generatedColorTiles;
    private final int columnLength;
    private final int rowLength;

    public ColorTile upperLeftTile;
    public ColorTile upperRightTile;
    public ColorTile lowerLeftTile;
    public ColorTile lowerRightTile;

    public Color UPPER_LEFT;
    public Color UPPER_RIGHT;
    public Color LOWER_LEFT;
    public Color LOWER_RIGHT;


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


    public void generateColorRow (ColorTile leftColorTile, ColorTile rightColorTile, int startIndex) {
        float standardWeight = 1 / ((float) (columnLength));

        for (int i = 0; i < columnLength; i++) {
            float weight = i * standardWeight;
            ColorTile interpolatedColorTile = interpolateColor(leftColorTile, rightColorTile, weight);
            generatedColorTiles[startIndex + i] = interpolatedColorTile;
        }

    }

    public void generateColorColumn (ColorTile topColorTile, ColorTile bottomColorTile, int startIndex) {
        float standardWeight = 1 / ((float) (rowLength));

        for (int i = 0; i < (rowLength); i++) {
            float weight = i * standardWeight;
            ColorTile interpolatedColorTile = interpolateColor(topColorTile, bottomColorTile, weight);
            generatedColorTiles[(i * columnLength) + startIndex ] = interpolatedColorTile;
        }
    }

    public void generateColorTiles () {

        // First generate first and last column
        generateColorColumn(upperLeftTile, lowerLeftTile, 0);
        generateColorColumn(upperRightTile, lowerRightTile, columnLength-1);

        // Generate the missing rows
        for (int i = 0; i < rowLength; i++) {

            ColorTile leftColorTile = generatedColorTiles[i * columnLength];
            ColorTile rightColorTile = generatedColorTiles[i * columnLength + (columnLength-1)];
            generateColorRow(leftColorTile, rightColorTile, i * columnLength);
        }
    }


}

