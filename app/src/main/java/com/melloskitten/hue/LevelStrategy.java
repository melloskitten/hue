package com.melloskitten.hue;

import java.util.logging.Level;

/**
 * Created by sandra on 12.09.17.
 */

abstract class LevelStrategy {
    public String[] hexCodes;
    public int rowLength;
    public int columnLength;

    public LevelStrategy(String[] hexCodes) {
        this.hexCodes = hexCodes;
        this.rowLength = 0;
        this.columnLength = 0;
    }

}

// Easy Levels are at most 4 x 5 big.
class EasyLevelStrategy extends LevelStrategy {

    public EasyLevelStrategy(String[] hexCodes) {
        super(hexCodes);
        this.columnLength = 5;
        this.rowLength = 4;
    }
}

// Easy Levels are at most 5*5 big.
class IntermediateLevelStrategy extends LevelStrategy {


    public IntermediateLevelStrategy(String[] hexCodes) {
        super(hexCodes);
        this.columnLength = 5;
        this.rowLength = 5;
    }
}

class HardLevelStrategy extends LevelStrategy {


    public HardLevelStrategy(String[] hexCodes) {
        super(hexCodes);
    }
}

