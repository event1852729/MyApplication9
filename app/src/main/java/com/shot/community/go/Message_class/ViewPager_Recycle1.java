package com.shot.community.go.Message_class;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;

import com.shot.community.go.Package_calss.Package_http;
import com.shot.community.go.Package_calss.Select_number;
import com.shot.community.go.http_meth.UserData;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.getbase.floatingactionbutton.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.shot.community.go.R;

/**
 * Created by god on 2017/10/21.
 */

public class ViewPager_Recycle1 extends Fragment  implements Runnable{
    private DividerItemDecoration mDivider;
    FloatingActionsMenu floatingActionsMenu;
    RecyclerView message__recyclerView;
    Package_http package_http;
    Package_http package_http2;
    public static RecyclerView recyclerView;
    sendmessage_toupdate_recycle sendmessage_toupdate_recycle;
    public static View view2;
    int buttonselectflag=1;
    public static int update_recycle_data1 = 0;
    Message_http message_http;
    public static EditText editText;
    FloatingActionButton floatingActionButton ,floatingActionButton1;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    String number_id;
    int threadboolean = 0;
    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view =  inflater.inflate(R.layout.message_recycle1, container, false);

        sendmessage_toupdate_recycle = new sendmessage_toupdate_recycle();
        floatingActionsMenu = (FloatingActionsMenu) view.findViewById(R.id.message_fabmenu) ;
        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.messageView_fab);
        floatingActionButton1 = (FloatingActionButton) view.findViewById(R.id.message_fab_1);
        message__recyclerView = (RecyclerView)view.findViewById(R.id.message_RecycleView);
        message__recyclerView.setHasFixedSize(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);
        mDivider = new DividerItemDecoration(getActivity() , DividerItemDecoration.VERTICAL);
        message__recyclerView.addItemDecoration(mDivider);
        number_id = getActivity().getIntent().getStringExtra("number_id");
        //先找好住戶 在後面判斷
        package_http2 = new Package_http(getActivity()  , 13 );
        package_http2.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);

         message_http = new Message_http(getActivity() , 0 , message__recyclerView );
        message_http.execute("http://140.136.155.79/message/content.php" , number_id);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                number_id = getActivity().getIntent().getStringExtra("number_id");
                Message_http message_http = new Message_http(getActivity() , 0 , message__recyclerView );
                message_http.execute("http://140.136.155.79/message/content.php" , number_id);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                Intent intent = new Intent(getActivity() , Message_send.class);
                intent.putExtra("number_Myid" , number_id);
                startActivity(intent);
            }
        });
        floatingActionButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floatingActionsMenu.collapse();
                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(getActivity(),R.style.MyAlertDialogStyle);
                view2 = inflater.inflate(R.layout.search_package_dio, container , false);
                recyclerView = (RecyclerView)view2.findViewById(R.id.search_package_recycle_dio);
                recyclerView.setHasFixedSize(true);
                mDivider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
                recyclerView.addItemDecoration(mDivider);

                editText = (EditText)view2.findViewById(R.id.serach_package_dio) ;
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        if(s.toString().equals(""))
                        {
                            package_http = new Package_http(recyclerView,getActivity()  , 11  );
                            package_http.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);
                        }else {
                            package_http.serach_number(s.toString().toLowerCase());
                        }

                    }

                    @Override
                    public void afterTextChanged(Editable s) {

                    }
                });
                package_http = new Package_http(recyclerView,getActivity()  , 11 );
                package_http.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);
                Select_number.whoUsethisClassFlag=3;
                real_delete.setView(view2);
                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String inputname = editText.getText().toString();
                        int flag = 0;
                        String numberid="";
                        for(int i = 0 ; i<message_http.getMessage_item_modelsList().size() ; i++)
                        {

                            if(message_http.getMessage_item_modelsList().get(i).getMessage_item_numberName().equals(inputname)){
                                flag = 1;
                            }
                        }
                        if(flag==1)
                        {
                            for(int i = 0 ; i<package_http2.getSelectNumberList().size() ; i++)
                            {
                                if(package_http2.getSelectNumberList().get(i).getName().equals(inputname))
                                {
                                    numberid =  package_http2.getSelectNumberList().get(i).getId();
                                }
                            }
                            Message_http message_http = new Message_http(getActivity() , 10 , message__recyclerView );
                            message_http.execute("http://140.136.155.79/message/content_select.php" , number_id , numberid);
                        }else {
                            Toast.makeText(getActivity(),"沒有找到住戶" , Toast.LENGTH_LONG).show();
                        }

                        dialog.dismiss();
                    }
                });
                real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                real_delete.show();
            }
        });


        return view;
    }

    class sendmessage_toupdate_recycle extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            message_http = new Message_http(getActivity() , 0 , message__recyclerView );
            message_http.execute("http://140.136.155.79/message/content.php" , number_id);
            update_recycle_data1 =0;

        }
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
    @Override
    public void run() {
        while (threadboolean == 0){
            try {
                Thread.sleep(300);
                Message_http message_http2 = new Message_http(getActivity() , 23 , message_http.getMessage_item_modelsList().size() , message_http );
                message_http2.execute("http://140.136.155.79/message/content.php" , number_id );
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
