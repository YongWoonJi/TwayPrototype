package com.example.jyw.twayprototype;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by JYW on 2016-08-19.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 4;
    }
}
