package com.shot.community.go.facility_manager;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shot.community.go.Facility_test.Facility_test_http;
import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by user on 2017/11/3.
 */

public class Facility_manager extends Fragment{

    Facility_test_http facility_test_http;
    RecyclerView recyclerView;
    FloatingActionButton floatingActionButton;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public Facility_manager() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.facility_manager_view1, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.facility_RecycleView);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);

        String commid = UserData.comunity_id;
        facility_test_http = new Facility_test_http( recyclerView , getActivity(),0);
        facility_test_http.execute("http://140.136.155.79/item/content.php",commid);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.facility_fab);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , Facility_add.class);
                startActivity(intent);
            }

        });


         mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String commid = UserData.comunity_id;
                facility_test_http = new Facility_test_http( recyclerView , getActivity(),0);
                facility_test_http.execute("http://140.136.155.79/item/content.php",commid);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });



        return view;
    }
}
