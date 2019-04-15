package com.shot.community.go;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shot.community.go.Message_class.Message_viewpagerAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mailbox_Fragments extends Fragment {
    public Mailbox_Fragments() {
        // Required empty public constructor
    }
    ViewPager myViewger;
    private DividerItemDecoration mDivider;
    FloatingActionButton message_floatingActionButton;
    RecyclerView message__recyclerView;
    FloatingActionButton floatingActionButton;
    Button messageButton1 , meessageButton2;
    Message_viewpagerAdapter message_viewpagerAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("geriobr" , "mailbox0.1");
        final View view = inflater.inflate(R.layout.fragment_mailbox__fragments, container, false);
        myViewger = (ViewPager)view.findViewById(R.id.message_viewPager);
        Log.d("geriobr" , "mailbox0");
//        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.message_fab);
//        message__recyclerView = (RecyclerView)view.findViewById(R.id.message_RecycleView);
//        mDivider = new DividerItemDecoration(getActivity() , DividerItemDecoration.VERTICAL);
//        message__recyclerView.addItemDecoration(mDivider);
//
//        Message_http message_http = new Message_http(getActivity() , 0 , message__recyclerView );
//        message_http.execute("hello");
        Log.d("geriobr" , "mailbox1");

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
        Log.d("geriobr" , "mailbox2");
        message_viewpagerAdapter = new Message_viewpagerAdapter(getFragmentManager());
        myViewger.setAdapter(message_viewpagerAdapter);
        myViewger.setOffscreenPageLimit(10);//防止滑動fragment銷毀

        messageButton1 = (Button)view.findViewById(R.id.message_button_record);
        meessageButton2 = (Button)view.findViewById(R.id.message_button_send);

        messageButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                meessageButton2.setBackgroundResource(R.drawable.message_button);
                messageButton1.setBackgroundResource(R.drawable.message_button_style);
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
