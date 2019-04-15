package com.shot.community.go.facility_manager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shot.community.go.Facility_test.Test;

/**
 * Created by user on 2017/11/3.
 */

public class Manager_viewpager extends FragmentPagerAdapter {
    public Manager_viewpager(FragmentManager fm) {
        super(fm);
    }

    /**
     * Return the Fragment associated with a specified position.
     *
     * @param position
     */
    @Override
    public Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                return new Test();
            case 1:
                return new Facility_manager();
        }
        return null;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 2;
    }
}

