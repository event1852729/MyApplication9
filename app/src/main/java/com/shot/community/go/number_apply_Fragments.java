package com.shot.community.go;


import android.content.Intent;
import android.os.Bundle;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.getbase.floatingactionbutton.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shot.community.go.Creat_comunity.Creat_comunity_http;
import com.shot.community.go.http_meth.UserData;


/**
 * A simple {@link Fragment} subclass.
 */
public class number_apply_Fragments extends Fragment{
    private DividerItemDecoration mDivider;
    public  static RecyclerView recyclerView;
    public static SwipeRefreshLayout mSwipeRefreshLayout;
    FloatingActionsMenu floatingActionsMenu;
    int flaggg=0;
    FloatingActionButton floatingActionButton1 , floatingActionButton2,floatingActionButton3;
    public number_apply_Fragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar__fragments, container ,false);
        recyclerView = (RecyclerView)view.findViewById(R.id.apply_comunity_RecycleView);
        floatingActionsMenu = (FloatingActionsMenu)view.findViewById(R.id.apply_com_fab1);
        floatingActionButton1 = (FloatingActionButton)view.findViewById(R.id.apply_com_fab2);
        floatingActionButton2 = (FloatingActionButton)view.findViewById(R.id.apply_com_fab3);
        floatingActionButton3 = (FloatingActionButton)view.findViewById(R.id.apply_com_fab4);
        mDivider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);

//        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.package_fab2);
//        floatingActionsMenu = (FloatingActionsMenu) view.findViewById(R.id.package_fabmenu);
        Creat_comunity_http creat_comunity_http = new Creat_comunity_http(getActivity() , 3 , recyclerView);
        creat_comunity_http.execute("http://140.136.155.79/applicant/content.php" , UserData.comunity_id);

        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(mDivider);


        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Creat_comunity_http creat_comunity_http = new Creat_comunity_http(getActivity() , 3 , recyclerView);
                creat_comunity_http.execute("http://140.136.155.79/applicant/content.php" , UserData.comunity_id);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                flaggg=1;
                Intent intent = new Intent(getActivity(),Add_guard.class);
                intent.putExtra("id",Integer.toString(flaggg));
                startActivity(intent);
            }
        });

        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                flaggg=2;
                Intent intent = new Intent(getActivity(),Add_guard.class);
                intent.putExtra("id",Integer.toString(flaggg));
                startActivity(intent);
            }
        });

        floatingActionButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),Number_manager.class);
                startActivity(intent);
            }
        });
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                floatingActionsMenu.collapse();
//                flaggg=2;
//                Intent intent = new Intent(getActivity(),Add_guard.class);
//                startActivity(intent);
//
//            }
//        });
//        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                flaggg=1;
//                Intent intent = new Intent(getActivity(),Add_guard.class);
//                startActivity(intent);
//
//            }
//        });

        return view;
    }


}
