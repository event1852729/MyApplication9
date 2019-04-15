package com.shot.community.go;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shot.community.go.PagerAdapt.SectionPagerAdapt;



/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Fragments extends Fragment {

    private ViewPager viewPager2;
    private SectionPagerAdapt sr;
    private Button newPackage;
    private Button newBox;
    private Button newAnnouncement;
    public Home_Fragments() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home__fragments, container, false);
        viewPager2 = (ViewPager) view.findViewById(R.id.container3);
        initdata();
        newPackage = (Button)view.findViewById(R.id.new_package);
        newBox = (Button)view.findViewById(R.id.new_mail);
        newAnnouncement = (Button)view.findViewById(R.id.new_announcement);
        newPackage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mViewPager.setCurrentItem(3);

        }
        });
        newBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mViewPager.setCurrentItem(2);
            }
        });
        newAnnouncement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mViewPager.setCurrentItem(1);
            }
        });
        viewPager2.setOffscreenPageLimit(8);
        return view;
    }
    private void initdata() {
        sr = new SectionPagerAdapt(getFragmentManager());
        viewPager2.setAdapter(sr);

    }


}
