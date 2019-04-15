package com.shot.community.go.Dicuss_class;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


/**
 * Created by user on 2017/11/19.
 */

public class Dicuss_viewpager extends FragmentPagerAdapter {
    public Dicuss_viewpager(FragmentManager fm) {
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
                return new Discuss_Fragments();
            case 1:
                return new Dicuss_report();
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
