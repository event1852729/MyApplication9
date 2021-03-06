package com.shot.community.go.Fix;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.Fragment;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by user on 2017/11/9.
 */

public class Fix_status0 extends Fragment {

    RecyclerView recyclerView;
    Fix_http fixHttp;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    public Fix_status0() {
        // Required empty public constructor
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view = inflater.inflate(R.layout.fix_manager_recycler, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.fix_RecycleView);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);

        String commid = UserData.comunity_id;
        fixHttp = new Fix_http(recyclerView, getActivity(),1);
        fixHttp.execute("http://140.136.155.79/fix/content_manager_status_0.php",commid);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String commid = UserData.comunity_id;
                fixHttp = new Fix_http(recyclerView, getActivity(),1);
                fixHttp.execute("http://140.136.155.79/fix/content_manager_status_0.php",commid);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });



        return view;
    }
}
