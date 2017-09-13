package com.melloskitten.hue;


/**
 * Created by sandra on 12.09.17.
 */

// Abstract class which defines a strategy which defines exactly how a level should be generated
// for a certain difficulty.
abstract class LevelStrategy {

    // MARK: VARS
    public String[] hexCodes;
    public int rowLength;
    public int columnLength;
    public int hintMode;

    // MARK: CONSTRUCTOR
    public LevelStrategy(String[] hexCodes) {
        this.hexCodes = hexCodes;
        this.rowLength = 0;
        this.columnLength = 0;
        this.hintMode = 0;
    }

}

// Easy Levels are at most 4 x 5 big, have the simplest hint mode.
class EasyLevelStrategy extends LevelStrategy {

    // MARK: CONSTRUCTOR
    public EasyLevelStrategy(String[] hexCodes) {
        super(hexCodes);
        this.columnLength = 5;
        this.rowLength = 4;
        this.hintMode = 0;
    }
}

// Intermediate Levels are at most 5*5 big, can have different hint modes.
class IntermediateLevelStrategy extends LevelStrategy {

    // MARK: CONSTRUCTORS
    public IntermediateLevelStrategy(String[] hexCodes) {
        super(hexCodes);
        this.columnLength = 5;
        this.rowLength = 5;
    }

    public IntermediateLevelStrategy(String[] hexCodes, int hintMode) {
        super(hexCodes);
        this.columnLength = 5;
        this.rowLength = 5;
        this.hintMode = hintMode;
    }
}

class HardLevelStrategy extends LevelStrategy {

    // MARK: CONSTRUCTORS
    public HardLevelStrategy(String[] hexCodes, int hintMode) {
        super(hexCodes);
        this.columnLength = 7;
        this.rowLength = 7;
        this.hintMode = hintMode;
    }
}

