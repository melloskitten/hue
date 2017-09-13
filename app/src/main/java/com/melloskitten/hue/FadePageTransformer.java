package com.melloskitten.hue;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * Created by sandra on 13.09.17.
 */

// CodeSnippet taken from https://stackoverflow.com/a/32633721/7217195
class FadePageTransformer implements ViewPager.PageTransformer {
    public void transformPage(View view, float position) {
        if(position <= -1.0F || position >= 1.0F) {
            view.setTranslationX(view.getWidth() * position);
            view.setAlpha(0.0F);
        } else if( position == 0.0F ) {
            view.setTranslationX(view.getWidth() * position);
            view.setAlpha(1.0F);
        } else {
            view.setTranslationX(view.getWidth() * -position);
            view.setAlpha(1.0F - Math.abs(position));
        }
    }
}
