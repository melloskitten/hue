package com.melloskitten.hue;

/**
 * Created by sandra on 12.09.17.
 */

// This class takes a field of ColorTiles, scrambles it up, then returns the tiles for the game.
public class ColorTileFactory {

    public static ColorTile[] getGameColorTiles(String[] hexCodes, int columnLength, int rowLength,
                                                int hintMode) {

        ColorTileGenerator tileGenerator = new ColorTileGenerator(hexCodes[0], hexCodes[1],
                hexCodes[2], hexCodes[3],
                columnLength, rowLength, hintMode);

        ColorTile[] generatedColorTiles = tileGenerator.generateColorTiles();

        return ColorTileScrambler.scramble(generatedColorTiles);
    }
}
