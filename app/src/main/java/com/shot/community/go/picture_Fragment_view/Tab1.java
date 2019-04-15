package com.shot.community.go.picture_Fragment_view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shot.community.go.PagerAdapt.SectionPagerAdapt;
import com.shot.community.go.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Tab1 extends Fragment {
    private ViewPager viewPager2;
    private SectionPagerAdapt sr;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab1, container, false);
        return view;
    }



}
