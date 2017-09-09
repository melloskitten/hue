package com.melloskitten.hue;

import android.graphics.Color;

/**
 * Created by sandra on 09.09.17.
 */

// TODO: Fix the desc of this class its horrendous
// A Class for generating a Color Table based on color inputs in #XXXXXX String format and the
// number of columns and rows of the level.

public class ColorTileGenerator {

    public Color[] generatedColors;
    private final int columnLength;
    private final int rowLength;
    public Color upperLeftColor;
    public Color upperRightColor;
    public Color lowerLeftColor;
    public Color lowerRightColor;

    public ColorTileGenerator (Color upperLeftColor, Color upperRightColor,
                               Color lowerLeftColor, Color lowerRightColor,
                               int columnLength, int rowLength) {
        this.upperLeftColor = upperLeftColor;
        this.upperRightColor = upperRightColor;
        this.lowerLeftColor = lowerLeftColor;
        this.lowerRightColor = lowerRightColor;
        this.columnLength = columnLength;
        this.rowLength = rowLength;
        this.generatedColors = new Color[rowLength*columnLength];

    }

    public Color interpolateColor (Color start, Color end, float weight) {

        float red = start.red() + (end.red()- start.red()) * weight;
        float green = start.green() + (end.green() - start.green()) * weight;
        float blue = start.blue() + (end.blue() - start.blue()) * weight;

        Color middle = Color.valueOf(Color.rgb(red, green, blue));
        return middle;
    }


    public void generateColorRow (Color leftColor, Color rightColor, int startIndex) {
        float standardWeight = 1 / ((float) (columnLength));

        for (int i = 0; i < columnLength; i++) {
            float weight = i * standardWeight;
            Color interpolatedColor = interpolateColor(leftColor, rightColor, weight);
            generatedColors[startIndex + i] = interpolatedColor;
        }

    }

    public void generateColorColumn (Color topColor, Color bottomColor, int startIndex) {
        float standardWeight = 1 / ((float) (rowLength));

        for (int i = 0; i < (rowLength); i++) {
            float weight = i * standardWeight;
            Color interpolatedColor = interpolateColor(topColor, bottomColor, weight);
            generatedColors[(i * columnLength) + startIndex ] = interpolatedColor;
        }
    }

    public void generateColorTiles () {

        // First generate first and last column
        generateColorColumn(upperLeftColor, lowerLeftColor, 0);
        generateColorColumn(upperRightColor, upperRightColor, columnLength-1);

        // Generate the missing rows
        for (int i = 0; i < rowLength; i++) {

            Color leftColor = generatedColors[i * columnLength];
            Color rightColor = generatedColors[i * columnLength + (columnLength-1)];
            generateColorRow(leftColor, rightColor, i * columnLength);
        }
    }


}

