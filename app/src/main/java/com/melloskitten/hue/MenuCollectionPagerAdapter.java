package com.melloskitten.hue;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

// FIXME: this can probably be rewritten nicer
public class MenuCollectionPagerAdapter extends FragmentStatePagerAdapter {

    public MenuCollectionPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        Fragment fragment = null;

        switch (i) {
            case 0:
                fragment = new EasyMenuFragment();
                break;
            case 1:
                fragment = new IntermediateMenuFragment();
                break;
            case 2:
                fragment = new ExpertMenuFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "OBJECT " + (position + 1);
    }
}
