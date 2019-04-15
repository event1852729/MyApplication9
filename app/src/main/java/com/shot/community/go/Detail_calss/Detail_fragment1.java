package com.shot.community.go.Detail_calss;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/21.
 */

public class Detail_fragment1 extends Fragment {
    FloatingActionButton floatingActionButton;
    ArrayList<Detail_record_item_model> detail_record_item_modelsList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.detail_viewpager_fragment, container, false);

        if(UserData.manger.equals("1")){

            recyclerView = (RecyclerView) view.findViewById(R.id.detail_recycle);
            recyclerView.setHasFixedSize(true);
            floatingActionButton = (FloatingActionButton)view.findViewById(R.id.detail_fab);
            mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);

            if(UserData.manger.equals("1"))
            {
                floatingActionButton = (FloatingActionButton)view.findViewById(R.id.detail_fab);
                floatingActionButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity() , Add_detail_record.class);
                        startActivity(intent);
                    }
                });
            }





            Detail_http detail_http = new Detail_http(getActivity() , 0 , recyclerView);
            detail_http.execute("http://140.136.155.79/record/content.php" , UserData.comunity_id);

            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Detail_http detail_http = new Detail_http(getActivity() , 0 , recyclerView);
                    detail_http.execute("http://140.136.155.79/record/content.php" , UserData.comunity_id);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });


        }else if(UserData.manger.equals("0") || UserData.manger.equals("2") || UserData.manger.equals("3")){

             view =  inflater.inflate(R.layout.detail_viewpager_fragment_number_0, container, false);
            recyclerView = (RecyclerView) view.findViewById(R.id.detail_recycle);
            recyclerView.setHasFixedSize(true);
            mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);





            Detail_http detail_http = new Detail_http(getActivity() , 0 , recyclerView);
            detail_http.execute("http://140.136.155.79/record/content.php" , UserData.comunity_id);

            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Detail_http detail_http = new Detail_http(getActivity() , 0 , recyclerView);
                    detail_http.execute("http://140.136.155.79/record/content.php" , UserData.comunity_id);
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });


        }





        return view;
    }
}
