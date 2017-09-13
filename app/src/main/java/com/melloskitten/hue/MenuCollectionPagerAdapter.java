package com.melloskitten.hue;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

// Adapter for the ViewPager including the different difficulty screens.
public class MenuCollectionPagerAdapter extends FragmentStatePagerAdapter {

    // MARK: VARS
    Fragment[] fragments;


    // MARK: CONSTRUCTOR
    public MenuCollectionPagerAdapter(FragmentManager fm, Fragment[] fragments) {
        super(fm);
        this.fragments = fragments;
    }

    // MARK: VIEWPAGER OVERRIDE METHODS
    @Override
    public Fragment getItem(int i) {
        return fragments[i];
    }

    @Override
    public int getCount() {
        return fragments.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "";
    }
}
