package com.shot.community.go.Detail_calss;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/21.
 */

public class Detail_fragment3 extends Fragment {
    RecyclerView recyclerView;
    Detail_app_recycle detail_app_recycle;
    ArrayList<Detail_app_item_model> detail_app_item_modelsList;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    Spinner spinner;
    ArrayAdapter arrayAdapter;
    String[] detail_app_string;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.detail_viewpager_fragment3, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.detail_appointment_recycle);
        recyclerView.setHasFixedSize(true);
        String[] detail_app_string = {"未審核" , "已審核"};
        spinner = (Spinner)view.findViewById(R.id.detail_appointment_spinner);
        arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_text, detail_app_string);
        arrayAdapter.setDropDownViewResource(R.layout.login_spinner_text);

        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);


        if(UserData.manger.equals("0") || UserData.manger.equals("1"))
        {
            detail_app_string = new String[]{"未審核", "已審核"};
            spinner = (Spinner)view.findViewById(R.id.detail_appointment_spinner);
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_text, detail_app_string);
            arrayAdapter.setDropDownViewResource(R.layout.login_spinner_text);
            Detail_http detail_http0 = new Detail_http(getActivity() , 1 , recyclerView);
            detail_http0.execute("http://140.136.155.79/reserve/user_manager_content_status_0.php" , UserData.id , UserData.manger ,  UserData.comunity_id);

            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Detail_http detail_http0 = new Detail_http(getActivity() , 1 , recyclerView);
                    detail_http0.execute("http://140.136.155.79/reserve/user_manager_content_status_0.php" , UserData.id , UserData.manger ,  UserData.comunity_id);

                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });

            spinner.setAdapter(arrayAdapter);
            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    int selectid = parent.getSelectedItemPosition();
                    switch (selectid){
                        case 0:

                            Detail_http detail_http = new Detail_http(getActivity() , 1 , recyclerView);
                            detail_http.execute("http://140.136.155.79/reserve/user_manager_content_status_0.php" , UserData.id , UserData.manger , UserData.comunity_id);

                            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                @Override
                                public void onRefresh() {
                                    Detail_http detail_http = new Detail_http(getActivity() , 1 , recyclerView);
                                    detail_http.execute("http://140.136.155.79/reserve/user_manager_content_status_0.php" , UserData.id , UserData.manger , UserData.comunity_id);

                                    mSwipeRefreshLayout.setRefreshing(false);
                                }
                            });
                            break;
                        case 1:
                            Detail_http detail_http2 = new Detail_http(getActivity() , 1 , recyclerView);
                            detail_http2.execute("http://140.136.155.79/reserve/user_manager_content_status_1.php" , UserData.id , UserData.manger , UserData.comunity_id);

                            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                @Override
                                public void onRefresh() {
                                    Detail_http detail_http2 = new Detail_http(getActivity() , 1 , recyclerView);
                                    detail_http2.execute("http://140.136.155.79/reserve/user_manager_content_status_1.php" , UserData.id , UserData.manger , UserData.comunity_id);

                                    mSwipeRefreshLayout.setRefreshing(false);
                                }
                            });
                            break;


                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }else if(UserData.manger.equals("3"))
        {
            detail_app_string = new String[]{"已審核"};
            spinner = (Spinner)view.findViewById(R.id.detail_appointment_spinner);
            arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.spinner_text, detail_app_string);
            arrayAdapter.setDropDownViewResource(R.layout.login_spinner_text);
            spinner.setAdapter(arrayAdapter);
            Detail_http detail_http2 = new Detail_http(getActivity() , 1 , recyclerView);
            detail_http2.execute("http://140.136.155.79/reserve/user_manager_content_status_1.php" , UserData.id , UserData.manger , UserData.comunity_id);

            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    Detail_http detail_http2 = new Detail_http(getActivity() , 1 , recyclerView);
                    detail_http2.execute("http://140.136.155.79/reserve/user_manager_content_status_1.php" , UserData.id , UserData.manger , UserData.comunity_id);

                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });




        }










        return view;
    }
}
