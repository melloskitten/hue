package com.melloskitten.hue;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

// This activity holds the different difficulty screens so you can switch in between.
public class MenuCollectionActivity extends FragmentActivity {

    // MARK: VARS
    MenuCollectionPagerAdapter pagerAdapter;
    ViewPager viewPager;
    Fragment[] fragments;


    // MARK: ONCREATE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_collection);

        setUp();

    }


    // MARK: ONBACKPRESSED
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    // MARK: HELPER METHODS
    public void setUp() {
        fragments = new Fragment[]{new EasyMenuFragment(), new IntermediateMenuFragment(), new ExpertMenuFragment()};
        pagerAdapter = new MenuCollectionPagerAdapter(getSupportFragmentManager(), fragments);
        viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setPageTransformer(false, new FadePageTransformer());

    }
}



