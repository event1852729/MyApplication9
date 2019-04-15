package com.shot.community.go;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.shot.community.go.Announcements_class.addAnnouncement;
import com.shot.community.go.http_meth.SigninActivity;
import com.shot.community.go.http_meth.UserData;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.getbase.floatingactionbutton.FloatingActionButton;


/**
 * A simple {@link Fragment} subclass.
 */
public class Announcement_Fragments extends Fragment implements Runnable {
//    ArrayList<Announcements>  announceList = new ArrayList();//測試
    FloatingActionsMenu floatingActionButton;
    FloatingActionButton floatingActionButton1,floatingActionButton2;
    ListView listView;
    RecyclerView recyclerView;
    private DividerItemDecoration mDivider;
    Context context = getActivity();
    SigninActivity mySignActivity,mySignActivity2;
    sendmessage_toupdate_recycle sendmessage_toupdate_recycle;
    public static int update_recycle_data1 = 0;
    Thread thread;
    int threadboolean = 0;
    public static int treadBoolean = 0;
    public static  int or_size=0;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String news_title="新增的項目名稱";
        final String news_countent="新增的內容唷";
        Log.d("geriobr" , "ann0.1");
        View  view =  inflater.inflate(R.layout.fragment_announcement__fragments, container, false);;
        Log.d("geriobr" , "ann0.2");
        sendmessage_toupdate_recycle = new sendmessage_toupdate_recycle();
        Log.d("geriobr" , "ann0");
        if(UserData.manger.equals("1") || UserData.manger.equals("3"))
        {
             view =  inflater.inflate(R.layout.fragment_announcement__fragments, container, false);
            floatingActionButton = (FloatingActionsMenu)view.findViewById(R.id.Annouce_fab);
            mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
            mDivider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
            floatingActionButton1 = (FloatingActionButton)view.findViewById(R.id.fab_2);
            floatingActionButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    floatingActionButton.collapse();
                    Intent intent = new Intent();
                    intent.setClass(getActivity(), addAnnouncement.class);
                    startActivity(intent);
                }
            });

            recyclerView = (RecyclerView)view.findViewById(R.id.Anouncement_RecycleView);
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(mDivider);


            mySignActivity = new SigninActivity(getActivity() , recyclerView , mySignActivity);
            mySignActivity.execute("http://140.136.155.79/news/countent.php" , UserData.comunity_id);

            Log.d("geriobr" , "ann1");






        }else if(UserData.manger.equals("0") || UserData.manger.equals("2"))
        {
            view =  inflater.inflate(R.layout.fragment_announcement__fragments_number_0, container, false);
            mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
            mDivider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);

            recyclerView = (RecyclerView)view.findViewById(R.id.Anouncement_RecycleView);
            recyclerView.setHasFixedSize(true);
            recyclerView.addItemDecoration(mDivider);

            mySignActivity = new SigninActivity(getActivity() , recyclerView , mySignActivity);
            mySignActivity.execute("http://140.136.155.79/news/countent.php" , UserData.comunity_id);




        }
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mySignActivity = new SigninActivity(getActivity() , recyclerView , mySignActivity);
                mySignActivity.execute("http://140.136.155.79/news/countent.php" , UserData.comunity_id);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        Log.d("geriobr" , "ann4");
        return view;

    }

    @Override
    public void onPause() {
        super.onPause();
        threadboolean = 1;
    }

    @Override
    public void onResume() {
        super.onResume();

        Thread thread = new Thread(this);
        thread.start();
        threadboolean=0;
    }

    class sendmessage_toupdate_recycle extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            mySignActivity = new SigninActivity(getActivity() , recyclerView , mySignActivity);
            mySignActivity.execute("http://140.136.155.79/news/countent.php" , UserData.comunity_id);
            HOME.update_recycle_data2=1;
            update_recycle_data1 =0;

        }
    }

    @Override
    public void run() {
        while (threadboolean == 0){
            try {
                Thread.sleep(300);
                Log.d("hello" , mySignActivity.getArrListAnnouncement().size()+"");
                SigninActivity mySignActivity2 = new SigninActivity(38 , or_size);
                mySignActivity2.execute("http://140.136.155.79/news/countent.php" , UserData.comunity_id);

                if(update_recycle_data1==1)
                {
                    Message message = sendmessage_toupdate_recycle.obtainMessage();
                    message.what = 2;
                    sendmessage_toupdate_recycle.sendMessage(message);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

