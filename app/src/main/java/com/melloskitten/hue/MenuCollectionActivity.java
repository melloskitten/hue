package com.melloskitten.hue;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

public class MenuCollectionActivity extends FragmentActivity {

    MenuCollectionPagerAdapter pagerAdapter;
    ViewPager viewPager;
    Fragment[] fragments = {new EasyMenuFragment(), new IntermediateMenuFragment(), new ExpertMenuFragment()};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_collection);

        pagerAdapter = new MenuCollectionPagerAdapter(
                getSupportFragmentManager(), fragments
        );


        viewPager = (ViewPager) findViewById(R.id.pager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabDots);
        tabLayout.setupWithViewPager(viewPager, true);
        viewPager.setAdapter(pagerAdapter);

        // FIXME: For intents
        //@Override
        //protected void onCreate(Bundle savedInstanceState) {
        //    Intent intent = getIntent();
        //    String value = intent.getStringExtra("key"); //if it's a string you stored.
        //}

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}



