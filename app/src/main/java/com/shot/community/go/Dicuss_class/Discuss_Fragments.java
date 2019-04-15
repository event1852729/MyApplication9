package com.shot.community.go.Dicuss_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by god on 2017/10/26.
 */

public class Discuss_Fragments extends Fragment {

    RecyclerView dicuss_recycle;
    TextView number_fragment_name;
    FloatingActionButton floatingActionButton;
    private DividerItemDecoration mDivider;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    public Discuss_Fragments() {

        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_discuss__fragments , container ,false);
        dicuss_recycle = (RecyclerView)view.findViewById(R.id.dicuss_fragment_recycleView);
        dicuss_recycle.setHasFixedSize(true);
        mDivider = new DividerItemDecoration(getActivity() , DividerItemDecoration.VERTICAL);
        dicuss_recycle.addItemDecoration(mDivider);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.dicuss_fragmen_fab);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        Dicuss_http dicuss_http = new Dicuss_http(getActivity(), 0 , dicuss_recycle);
        dicuss_http.execute("http://140.136.155.79/board/content.php" , UserData.comunity_id);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Dicuss_http dicuss_http = new Dicuss_http(getActivity(), 0 , dicuss_recycle);
                dicuss_http.execute("http://140.136.155.79/board/content.php" , UserData.comunity_id);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });



        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , Add_dicuss.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
