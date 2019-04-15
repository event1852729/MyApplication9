package com.shot.community.go.Fix;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shot.community.go.R;

/**
 * Created by user on 2017/11/6.
 */
public class Fix_manager extends Fragment {

    Button No;
    Button Now;
    ViewPager myViewger;
    Fix_viewpagerAdaoter fixViewpagerAdaoter;

    public Fix_manager() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.fix_fragment, container, false);
        No = (Button)view.findViewById(R.id.message_button_record);
        Now = (Button)view.findViewById(R.id.message_button_send);
        myViewger = (ViewPager)view.findViewById(R.id.fix_viewPager);


        fixViewpagerAdaoter = new Fix_viewpagerAdaoter(getFragmentManager());
        myViewger.setAdapter(fixViewpagerAdaoter);
        myViewger.setOffscreenPageLimit(10);//防止滑動fragment銷毀



        No.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewger.setCurrentItem(0);
                No.setBackgroundResource(R.drawable.message_button_style);
                Now.setBackgroundResource(R.drawable.message_button);
            }
        });


        Now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Now.setBackgroundResource(R.drawable.message_button_style);
                No.setBackgroundResource(R.drawable.message_button);
                myViewger.setCurrentItem(1);
            }
        });


        return view;
    }
}
