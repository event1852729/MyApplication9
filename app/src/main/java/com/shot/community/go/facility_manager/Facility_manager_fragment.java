package com.shot.community.go.facility_manager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shot.community.go.R;

/**
 * Created by user on 2017/11/3.
 */

public class Facility_manager_fragment extends Fragment {
    public Facility_manager_fragment() {
        // Required empty public constructor
    }
    ViewPager Viewger;
    Button messageButton1 , meessageButton2;
    Manager_viewpager manager_viewpager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.facility_manager, container, false);
        Viewger = (ViewPager)view.findViewById(R.id.facility_viewPager);

        manager_viewpager = new Manager_viewpager(getFragmentManager());
        Viewger.setAdapter(manager_viewpager);
        Viewger.setOffscreenPageLimit(10);//防止滑動fragment銷毀

        messageButton1 = (Button)view.findViewById(R.id.message_button_record);
        meessageButton2 = (Button)view.findViewById(R.id.message_button_send);

        messageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Viewger.setCurrentItem(0);
                messageButton1.setBackgroundResource(R.drawable.message_button_style);
                meessageButton2.setBackgroundResource(R.drawable.message_button);
            }
        });

        meessageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meessageButton2.setBackgroundResource(R.drawable.message_button_style);
                messageButton1.setBackgroundResource(R.drawable.message_button);
                Viewger.setCurrentItem(1);
            }
        });



        return view;
    }
}
