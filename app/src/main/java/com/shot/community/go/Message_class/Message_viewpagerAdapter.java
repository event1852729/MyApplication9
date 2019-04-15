package com.shot.community.go.Message_class;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by god on 2017/10/21.
 */

public class Message_viewpagerAdapter extends FragmentPagerAdapter {
    public Message_viewpagerAdapter(FragmentManager fm) {
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
                return new ViewPager_Recycle1();
            case 1:
                return new ViewPager_Recycle2();
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
