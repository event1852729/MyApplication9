package com.shot.community.go.Photo;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;


/**
 * A simple {@link Fragment} subclass.
 */
public class Picture_Fragments extends Fragment {
    RecyclerView recyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    Photo_http photoHttp;
    FloatingActionButton floatingActionButton;


    public Picture_Fragments() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_picture__fragments, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.picture_RecycleView);
        floatingActionButton = (FloatingActionButton)view.findViewById(R.id.add_photo);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        String commid = UserData.comunity_id;
        photoHttp = new Photo_http(recyclerView,getActivity(),0);
        photoHttp.execute("http://140.136.155.79/album/content.php",commid);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String commid = UserData.comunity_id;
                photoHttp = new Photo_http(recyclerView,getActivity(),0);
                photoHttp.execute("http://140.136.155.79/album/content.php",commid);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        int ma = Integer.valueOf(UserData.manger);
        if (ma==1 || ma==3){
            floatingActionButton.setVisibility(View.VISIBLE);
        }
        else {
            floatingActionButton.setVisibility(View.GONE);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                LayoutInflater layoutInflater = getActivity().getLayoutInflater();
                final View item = layoutInflater.inflate(R.layout.album_dialog,null);
                final AlertDialog.Builder myalerdualog = new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle);

//                myalerdualog.setView(layoutInflater.inflate(R.layout.album_dialog,null));
                myalerdualog.setView(item);
                myalerdualog.setTitle("新增相簿");



                myalerdualog.setPositiveButton("新增", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        EditText editText = (EditText)item.findViewById(R.id.editText);
                        String s = editText.getText().toString();
                        if (s.matches(""))
                        {
                            Toast.makeText(getActivity(),"不可為空白",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String commid = UserData.comunity_id;
                            photoHttp = new Photo_http(1);
                            photoHttp.execute("http://140.136.155.79/album/register_finish.php",s,commid);



                            Photo_http photoHttp1 = new Photo_http(recyclerView,getActivity(),0);
                            photoHttp1.execute("http://140.136.155.79/album/content.php",commid);
                        }

                        dialog.dismiss();
                    }
                });

                myalerdualog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                    }
                });
                myalerdualog.show();
            }
        });

        return view;
    }

}
