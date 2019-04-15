package com.shot.community.go.Dicuss_class;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shot.community.go.R;

/**
 * Created by user on 2017/11/19.
 */

public class Dicuss_Fragments_new extends Fragment {

    public Dicuss_Fragments_new() {
    }

    ViewPager myViewger;
    Button messageButton1, meessageButton2;
    Dicuss_viewpager dicussViewpager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dicuss_viewpager, container, false);
        myViewger = (ViewPager)view.findViewById(R.id.dicuss_viewpager);

        myViewger.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if(position==0)
                {
                    messageButton1.setBackgroundResource(R.drawable.message_button_style);
                    meessageButton2.setBackgroundResource(R.drawable.message_button);

                }else if(position==1)
                {
                    meessageButton2.setBackgroundResource(R.drawable.message_button_style);
                    messageButton1.setBackgroundResource(R.drawable.message_button);
                }
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        dicussViewpager = new Dicuss_viewpager(getFragmentManager());
        myViewger.setAdapter(dicussViewpager);
        myViewger.setOffscreenPageLimit(10);//防止滑動fragment銷毀

        messageButton1 = (Button)view.findViewById(R.id.message_button_record);
        meessageButton2 = (Button)view.findViewById(R.id.message_button_send);

        messageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageButton1.setBackgroundResource(R.drawable.message_button_style);
                meessageButton2.setBackgroundResource(R.drawable.message_button);
                myViewger.setCurrentItem(0);
            }
        });

        meessageButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meessageButton2.setBackgroundResource(R.drawable.message_button_style);
                messageButton1.setBackgroundResource(R.drawable.message_button);
                myViewger.setCurrentItem(1);
            }
        });

        return view;
    }
}

