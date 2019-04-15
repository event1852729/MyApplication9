package com.shot.community.go.Detail_calss;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by god on 2017/10/21.
 */

public class Detail_fragment_adpater extends FragmentPagerAdapter {
    public Detail_fragment_adpater(FragmentManager fm) {
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
                return new Detail_fragment1();
            case 1:
                return new Detail_fragment2();
            case 2:
                return new Detail_fragment3();
            case 3:
                return new Detail_fragment4();
        }
        return null;
    }

    /**
     * Return the number of views available.
     */
    @Override
    public int getCount() {
        return 4;
    }
}
