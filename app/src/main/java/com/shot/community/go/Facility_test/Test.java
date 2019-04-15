package com.shot.community.go.Facility_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by user on 2017/10/29.
 */

public class Test extends Fragment {
    Test_http test_http;
    public static RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public Test() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.test_recycler, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.Facility_RecycleView);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        String commid = UserData.comunity_id;
        test_http = new Test_http( recyclerView , getActivity(),0);
        test_http.execute("http://140.136.155.79/reserve/manager_content.php",commid);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String commid = UserData.comunity_id;
                test_http = new Test_http( recyclerView , getActivity(),0);
                test_http.execute("http://140.136.155.79/reserve/manager_content.php",commid);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });



        return view;
    }
}
