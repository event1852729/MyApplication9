package com.shot.community.go.Message_class;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shot.community.go.R;

/**
 * Created by god on 2017/10/21.
 */

public class ViewPager_Recycle2 extends Fragment{
    private DividerItemDecoration mDivider;
    RecyclerView message__recyclerView2;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.message_recycle2, container, false);
        message__recyclerView2 = (RecyclerView)view.findViewById(R.id.message_RecycleView2);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);


        mDivider = new DividerItemDecoration(getActivity() , DividerItemDecoration.VERTICAL);
        message__recyclerView2.addItemDecoration(mDivider);
        message__recyclerView2.setHasFixedSize(true);

        String number_id = getActivity().getIntent().getStringExtra("number_id");

        Message_http message_http = new Message_http(getActivity() , 2 , message__recyclerView2 );
        message_http.execute("http://140.136.155.79/message/content01.php" , number_id);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String number_id = getActivity().getIntent().getStringExtra("number_id");
                Message_http message_http = new Message_http(getActivity() , 2 , message__recyclerView2 );
                message_http.execute("http://140.136.155.79/message/content01.php" , number_id);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });


        return view;
    }
}
