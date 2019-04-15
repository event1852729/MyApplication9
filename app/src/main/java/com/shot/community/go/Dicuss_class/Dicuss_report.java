package com.shot.community.go.Dicuss_class;

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
 * Created by user on 2017/11/19.
 */

public class Dicuss_report extends Fragment{
    RecyclerView recyclerView;
    Dicuss_http dicuss_http;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public Dicuss_report() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dicuss_report, container, false);
        recyclerView =(RecyclerView)view.findViewById(R.id.dicuss_report_RecycleView);
        recyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        String commid = UserData.comunity_id;
        dicuss_http = new Dicuss_http(getActivity(),8,recyclerView);
        dicuss_http.execute("http://140.136.155.79/report/content.php",commid);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String commid = UserData.comunity_id;
                dicuss_http = new Dicuss_http(getActivity(),8,recyclerView);
                dicuss_http.execute("http://140.136.155.79/report/content.php",commid);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        return view;
    }
}
