package com.shot.community.go.Fix;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by user on 2017/11/9.
 */

public class Fix_viewpagerAdaoter extends FragmentPagerAdapter {
    public Fix_viewpagerAdaoter(FragmentManager fm) {
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
                return new Fix_status0();
            case 1:
                return new Fix_status1();
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

