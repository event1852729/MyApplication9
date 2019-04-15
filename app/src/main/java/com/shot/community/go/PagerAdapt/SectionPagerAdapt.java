package com.shot.community.go.PagerAdapt;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.shot.community.go.picture_Fragment_view.Tab1;
import com.shot.community.go.picture_Fragment_view.Tab2;
import com.shot.community.go.picture_Fragment_view.Tab3;

/**
 * Created by user on 2017/3/27.
 */

public class SectionPagerAdapt extends FragmentPagerAdapter {
    public SectionPagerAdapt(FragmentManager fm) {
        super(fm);
    }

    @Override
    public android.support.v4.app.Fragment getItem(int position) {
        switch (position)
        {
            case 0:
                Tab1 tab1 = new Tab1();
                return tab1;
            case 1:
                Tab2 tab2 = new Tab2();
                return tab2;
            case 2:
                Tab3 tab3 = new Tab3();
                return tab3;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
