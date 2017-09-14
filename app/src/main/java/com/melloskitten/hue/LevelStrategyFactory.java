package com.melloskitten.hue;

import android.content.Context;

/**
 * Created by sandra on 14.09.17.
 */

// Factory That creates strategies for every difficulty level.
public class LevelStrategyFactory {

    public static LevelStrategy[] createEasyLevelStrategies(Context mContext) {

        // Set the Easy Strategies
        LevelStrategy lvl_1_strategy = new EasyLevelStrategy(new String[]{mContext.getString(R.string.easy_lvl_1_col1), mContext.getString(R.string.easy_lvl_1_col2),
                mContext.getString(R.string.easy_lvl_1_col3), mContext.getString(R.string.easy_lvl_1_col4)});

        LevelStrategy lvl_2_strategy = new EasyLevelStrategy(new String[]{mContext.getString(R.string.easy_lvl_2_col1), mContext.getString(R.string.easy_lvl_2_col2),
                mContext.getString(R.string.easy_lvl_2_col3), mContext.getString(R.string.easy_lvl_2_col4)});

        LevelStrategy lvl_3_strategy = new EasyLevelStrategy(new String[]{mContext.getString(R.string.easy_lvl_3_col1), mContext.getString(R.string.easy_lvl_3_col2),
                mContext.getString(R.string.easy_lvl_3_col3), mContext.getString(R.string.easy_lvl_3_col4)});


        return new LevelStrategy[]{lvl_1_strategy, lvl_2_strategy, lvl_3_strategy};

    }

    public static LevelStrategy[] createIntermediateLevelStrategies(Context mContext) {

        // Set the Intermediate Strategies
        LevelStrategy lvl_1_strategy = new IntermediateLevelStrategy(new String[]{mContext.getString(R.string.intermediate_lvl_1_col1), mContext.getString(R.string.intermediate_lvl_1_col2),
                mContext.getString(R.string.intermediate_lvl_1_col3), mContext.getString(R.string.intermediate_lvl_1_col4)}, 2);
        LevelStrategy lvl_2_strategy = new IntermediateLevelStrategy(new String[]{mContext.getString(R.string.intermediate_lvl_2_col1), mContext.getString(R.string.intermediate_lvl_2_col2),
                mContext.getString(R.string.intermediate_lvl_2_col3), mContext.getString(R.string.intermediate_lvl_2_col4)});
        LevelStrategy lvl_3_strategy = new IntermediateLevelStrategy(new String[]{mContext.getString(R.string.intermediate_lvl_3_col1), mContext.getString(R.string.intermediate_lvl_3_col2),
                mContext.getString(R.string.intermediate_lvl_3_col3), mContext.getString(R.string.intermediate_lvl_3_col4)}, 1);

        return new LevelStrategy[]{lvl_1_strategy, lvl_2_strategy, lvl_3_strategy};
    }

    public static LevelStrategy[] createHardLevelStrategies(Context mContext) {

        // Set the expert Strategies
        LevelStrategy lvl_1_strategy = new HardLevelStrategy(new String[]{mContext.getString(R.string.expert_lvl_1_col1), mContext.getString(R.string.expert_lvl_1_col2),
                mContext.getString(R.string.expert_lvl_1_col3), mContext.getString(R.string.expert_lvl_1_col4)}, 1);
        LevelStrategy lvl_2_strategy = new HardLevelStrategy(new String[]{mContext.getString(R.string.expert_lvl_2_col1), mContext.getString(R.string.expert_lvl_2_col2),
                mContext.getString(R.string.expert_lvl_2_col3), mContext.getString(R.string.expert_lvl_2_col4)}, 2);
        LevelStrategy lvl_3_strategy = new HardLevelStrategy(new String[]{mContext.getString(R.string.expert_lvl_3_col1), mContext.getString(R.string.expert_lvl_3_col2),
                mContext.getString(R.string.expert_lvl_3_col3), mContext.getString(R.string.expert_lvl_3_col4)}, 1);

        return new LevelStrategy[]{lvl_1_strategy, lvl_2_strategy, lvl_3_strategy};
    }
}
