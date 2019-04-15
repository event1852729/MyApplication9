package com.shot.community.go;


import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.shot.community.go.Package_calss.Select_number;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.getbase.floatingactionbutton.FloatingActionButton;

import android.os.Message;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.Toast;

import com.shot.community.go.Package_calss.Add_package;
import com.shot.community.go.Package_calss.Package_http;
import com.shot.community.go.http_meth.UserData;

//import com.example.user.myapplication.http_meth.Package_http;


/**
 * A simple {@link Fragment} subclass.
 */
public class Package_Fragements extends Fragment  implements Runnable {
    Package_http package_http_out;
    Package_http package_http;
    Package_http package_http2;

    private DividerItemDecoration mDivider;
    int buttonselectflag=1;
    FloatingActionsMenu floatingActionsMenu;
    public static RecyclerView recyclerView;
    public static View view2;
   public static EditText editText;
    FloatingActionButton package_floatingActionButton , search_button;
    RecyclerView package_recyclerView;
    HorizontalScrollView horizontalScrollView;
    FloatingActionButton floatingActionButton;
    Button button1 , button2 , button3 ,button4 ;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    String number_id;
    sendmessage_toupdate_recycle sendmessage_toupdate_recycle;
   public static int update_recycle_data1 = 0;
    Thread thread;
   public static int treadBoolean = 0;
    public Package_Fragements() {
        // Required empty public constructor
    }
   public static  int or_size=0;

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        View view =  inflater.inflate(R.layout.fragment_package__fragements, container, false);
        treadBoolean = 0;
        Log.d("geriobr" , "package");
        sendmessage_toupdate_recycle = new sendmessage_toupdate_recycle();
        if(UserData.manger.equals("3") || UserData.manger.equals("2"))
        {
                        view =  inflater.inflate(R.layout.fragment_package__fragements, container, false);
                        horizontalScrollView = (HorizontalScrollView)view.findViewById(R.id.package_hs);
                        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.package_fab);
                        floatingActionsMenu = (FloatingActionsMenu) view.findViewById(R.id.package_fabmenu) ;
                        package_floatingActionButton = (FloatingActionButton)view.findViewById(R.id.package_fab);
                        //先讓他跑 判斷
                        package_http = new Package_http( package_recyclerView , getActivity(),14, buttonselectflag);
                        package_http.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);

                        //先找好住戶 在後面判斷
                        package_http2 = new Package_http(getActivity()  , 13 );
                        package_http2.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);
                        search_button = (FloatingActionButton)view.findViewById(R.id.package_fab2);
                        search_button.setOnClickListener(new View.OnClickListener() {
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
                                            package_http_out = new Package_http(recyclerView,getActivity()  , 11  );
                                            package_http_out.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);
                                        }else {
                                            package_http_out.serach_number(s.toString().toLowerCase());
                                        }

                                    }

                                    @Override
                                    public void afterTextChanged(Editable s) {

                                    }
                                });
                                package_http_out = new Package_http(recyclerView,getActivity()  , 11 );
                                package_http_out.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);
                                Select_number.whoUsethisClassFlag=2;
                                real_delete.setView(view2);
                                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        update_recycle_data1 =0;
                                        String inputname = editText.getText().toString();

                                        int flag = 0;
                                        String numberid="";

                                        for(int i = 0 ; i<package_http.getPackage_item_modelsList().size() ; i++)
                                        {
                                            Log.d("hello1" , package_http.getPackage_item_modelsList().get(i).getNumberName()+"");
                                            if(package_http.getPackage_item_modelsList().get(i).getNumberName().equals(inputname)){
                                                flag = 1;
                                            }
                                        }


                                        if(flag==1)
                                        {
                                            for(int i = 0 ; i<package_http2.getSelectNumberList().size() ; i++)
                                            {
                                                update_recycle_data1 =0;
                                                if(package_http2.getSelectNumberList().get(i).getName().equals(inputname))
                                                {
                                                    numberid =  package_http2.getSelectNumberList().get(i).getId();
                                                }
                                            }
                                            package_http_out = new Package_http( package_recyclerView , getActivity(),18, buttonselectflag);
                                            package_http_out.execute("http://140.136.155.79/package/content.php" , numberid);
                                        }else {
                                            Toast.makeText(getActivity(),"沒有找到住戶" , Toast.LENGTH_LONG).show();
                                        }
            //                        package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
            //                        package_http_out.execute("http://140.136.155.79/package/content.php" , Rm_select_package_number.packagenumber_id);
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


                        package_recyclerView = (RecyclerView)view.findViewById(R.id.package_RecycleView);
                        package_recyclerView.setHasFixedSize(true);
                        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);

                        if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                        {
                            number_id = getActivity().getIntent().getStringExtra("number_id");
                            package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                            package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                        }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                            number_id = getActivity().getIntent().getStringExtra("number_id");
                            package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                            package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                        }


                        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                            @Override
                            public void onRefresh() {
                                if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                                {
                                    number_id = getActivity().getIntent().getStringExtra("number_id");
                                    package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                    package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                                }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                    number_id = getActivity().getIntent().getStringExtra("number_id");
                                    package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                    package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                                }
                                mSwipeRefreshLayout.setRefreshing(false);
                            }
                        });

                        button1 = (Button)view.findViewById(R.id.package_fragment_button1);
                        button2 = (Button)view.findViewById(R.id.package_fragment_button2);
                        button3 = (Button)view.findViewById(R.id.package_fragment_button3);
                        button4 = (Button)view.findViewById(R.id.package_fragment_button4);
                        button1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                horizontalScrollView.setScrollX(0);
                                buttonselectflag=1;
                                if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                                {
                                    number_id = getActivity().getIntent().getStringExtra("number_id");
                                    package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                    package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                                }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                    number_id = getActivity().getIntent().getStringExtra("number_id");
                                    package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                    package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                                }

                                mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                    @Override
                                    public void onRefresh() {
                                        if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                                        {
                                            number_id = getActivity().getIntent().getStringExtra("number_id");
                                            package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                            package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                                        }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                            number_id = getActivity().getIntent().getStringExtra("number_id");
                                            package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                            package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                                        }
                                        mSwipeRefreshLayout.setRefreshing(false);
                                    }
                                });
                                button1.setBackgroundResource(R.drawable.message_button_style);
                                button2.setBackgroundResource(R.drawable.message_button);
                                button3.setBackgroundResource(R.drawable.message_button);
                                button4.setBackgroundResource(R.drawable.message_button);



                            }
                        });
                        button2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                horizontalScrollView.setScrollX(40);
                                buttonselectflag=2;
                                if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                                {
                                    number_id = getActivity().getIntent().getStringExtra("number_id");
                                    package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                    package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                                }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                    number_id = getActivity().getIntent().getStringExtra("number_id");
                                    package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                    package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                                }

                                mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                    @Override
                                    public void onRefresh() {
                                        if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                                        {
                                            number_id = getActivity().getIntent().getStringExtra("number_id");
                                            package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                            package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                                        }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                            number_id = getActivity().getIntent().getStringExtra("number_id");
                                            package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                            package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                                        }
                                        mSwipeRefreshLayout.setRefreshing(false);
                                    }
                                });
                                button1.setBackgroundResource(R.drawable.message_button);
                                button2.setBackgroundResource(R.drawable.message_button_style);
                                button3.setBackgroundResource(R.drawable.message_button);
                                button4.setBackgroundResource(R.drawable.message_button);

                            }
                        });
                        button3.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                horizontalScrollView.setScrollX(horizontalScrollView.getWidth()-50);
                                buttonselectflag=3;
                                if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                                {
                                    number_id = getActivity().getIntent().getStringExtra("number_id");
                                    package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                    package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                                }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                    number_id = getActivity().getIntent().getStringExtra("number_id");
                                    package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                    package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                                }

                                mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                    @Override
                                    public void onRefresh() {
                                        if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                                        {
                                            number_id = getActivity().getIntent().getStringExtra("number_id");
                                            package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                            package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                                        }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                            number_id = getActivity().getIntent().getStringExtra("number_id");
                                            package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                            package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                                        }
                                        mSwipeRefreshLayout.setRefreshing(false);
                                    }
                                });
                                button1.setBackgroundResource(R.drawable.message_button);
                                button2.setBackgroundResource(R.drawable.message_button);
                                button3.setBackgroundResource(R.drawable.message_button_style);
                                button4.setBackgroundResource(R.drawable.message_button);

                            }
                        });
                        button4.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                horizontalScrollView.setScrollX(  horizontalScrollView.getWidth());
                                buttonselectflag=4;
                                    if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                                    {
                                        number_id = getActivity().getIntent().getStringExtra("number_id");
                                        package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                        package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                                    }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                        number_id = getActivity().getIntent().getStringExtra("number_id");
                                        package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                        package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                                    }

                                mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                                    @Override
                                    public void onRefresh() {
                                        if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                                        {
                                            number_id = getActivity().getIntent().getStringExtra("number_id");
                                            package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                            package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                                        }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                            number_id = getActivity().getIntent().getStringExtra("number_id");
                                            package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                            package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                                        }
                                        mSwipeRefreshLayout.setRefreshing(false);
                                    }
                                });
                                button1.setBackgroundResource(R.drawable.message_button);
                                button2.setBackgroundResource(R.drawable.message_button);
                                button3.setBackgroundResource(R.drawable.message_button);
                                button4.setBackgroundResource(R.drawable.message_button_style);

                            }
                        });

                        horizontalScrollView.setScrollBarSize(10);



                            floatingActionsMenu.setVisibility(View.VISIBLE);
                            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    floatingActionsMenu.collapse();
                                    Intent intent = new Intent();
                                    intent.setClass(getActivity(), Add_package.class);
                                    startActivity(intent);
                                }
                            });




            Thread thread = new Thread(this);
            thread.start();



        }else if(UserData.manger.equals("0") || UserData.manger.equals("1")){

            view =  inflater.inflate(R.layout.fragment_package__fragements_number_0, container, false);
            horizontalScrollView = (HorizontalScrollView)view.findViewById(R.id.package_hs);

            package_floatingActionButton = (FloatingActionButton)view.findViewById(R.id.package_fab);
            //先讓他跑 判斷
            package_http = new Package_http( package_recyclerView , getActivity(),14, buttonselectflag);
            package_http.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);

            //先找好住戶 在後面判斷
            package_http2 = new Package_http(getActivity()  , 13 );
            package_http2.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);


            package_recyclerView = (RecyclerView)view.findViewById(R.id.package_RecycleView);
            package_recyclerView.setHasFixedSize(true);
            mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.refresh_layout);

            if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
            {
                number_id = getActivity().getIntent().getStringExtra("number_id");
                package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
            }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                number_id = getActivity().getIntent().getStringExtra("number_id");
                package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

            }


            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                    {
                        number_id = getActivity().getIntent().getStringExtra("number_id");
                        package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                        package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                    }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                        number_id = getActivity().getIntent().getStringExtra("number_id");
                        package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                        package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                    }
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            });

            button1 = (Button)view.findViewById(R.id.package_fragment_button1);
            button2 = (Button)view.findViewById(R.id.package_fragment_button2);
            button3 = (Button)view.findViewById(R.id.package_fragment_button3);
            button4 = (Button)view.findViewById(R.id.package_fragment_button4);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    horizontalScrollView.setScrollX(0);
                    buttonselectflag=1;
                    if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                    {
                        number_id = getActivity().getIntent().getStringExtra("number_id");
                        package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                        package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                    }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                        number_id = getActivity().getIntent().getStringExtra("number_id");
                        package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                        package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                    }

                    mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                            {
                                number_id = getActivity().getIntent().getStringExtra("number_id");
                                package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                            }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                number_id = getActivity().getIntent().getStringExtra("number_id");
                                package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                            }
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    });
                    button1.setBackgroundResource(R.drawable.message_button_style);
                    button2.setBackgroundResource(R.drawable.message_button);
                    button3.setBackgroundResource(R.drawable.message_button);
                    button4.setBackgroundResource(R.drawable.message_button);



                }
            });
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    horizontalScrollView.setScrollX(40);
                    buttonselectflag=2;
                    if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                    {
                        number_id = getActivity().getIntent().getStringExtra("number_id");
                        package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                        package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                    }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                        number_id = getActivity().getIntent().getStringExtra("number_id");
                        package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                        package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                    }

                    mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                            {
                                number_id = getActivity().getIntent().getStringExtra("number_id");
                                package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                            }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                number_id = getActivity().getIntent().getStringExtra("number_id");
                                package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                            }
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    });
                    button1.setBackgroundResource(R.drawable.message_button);
                    button2.setBackgroundResource(R.drawable.message_button_style);
                    button3.setBackgroundResource(R.drawable.message_button);
                    button4.setBackgroundResource(R.drawable.message_button);

                }
            });
            button3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    horizontalScrollView.setScrollX(horizontalScrollView.getWidth()-50);
                    buttonselectflag=3;
                    if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                    {
                        number_id = getActivity().getIntent().getStringExtra("number_id");
                        package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                        package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                    }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                        number_id = getActivity().getIntent().getStringExtra("number_id");
                        package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                        package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                    }

                    mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                            {
                                number_id = getActivity().getIntent().getStringExtra("number_id");
                                package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                            }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                number_id = getActivity().getIntent().getStringExtra("number_id");
                                package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                            }
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    });
                    button1.setBackgroundResource(R.drawable.message_button);
                    button2.setBackgroundResource(R.drawable.message_button);
                    button3.setBackgroundResource(R.drawable.message_button_style);
                    button4.setBackgroundResource(R.drawable.message_button);

                }
            });
            button4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    horizontalScrollView.setScrollX(  horizontalScrollView.getWidth());
                    buttonselectflag=4;
                    if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                    {
                        number_id = getActivity().getIntent().getStringExtra("number_id");
                        package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                        package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                    }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                        number_id = getActivity().getIntent().getStringExtra("number_id");
                        package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                        package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                    }

                    mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                        @Override
                        public void onRefresh() {
                            if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                            {
                                number_id = getActivity().getIntent().getStringExtra("number_id");
                                package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                                package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                            }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                                number_id = getActivity().getIntent().getStringExtra("number_id");
                                package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                                package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                            }
                            mSwipeRefreshLayout.setRefreshing(false);
                        }
                    });
                    button1.setBackgroundResource(R.drawable.message_button);
                    button2.setBackgroundResource(R.drawable.message_button);
                    button3.setBackgroundResource(R.drawable.message_button);
                    button4.setBackgroundResource(R.drawable.message_button_style);

                }
            });

            horizontalScrollView.setScrollBarSize(10);


            Thread thread = new Thread(this);
            thread.start();



        }

//        or_size = package_http_out.getPackage_item_modelsList().size();
//

        return view;
    }

    class sendmessage_toupdate_recycle extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;

            if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
            {
                number_id = getActivity().getIntent().getStringExtra("number_id");
                package_http_out = new Package_http( package_recyclerView , getActivity(),12, buttonselectflag);
                package_http_out.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
            }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){
                number_id = getActivity().getIntent().getStringExtra("number_id");
                package_http_out = new Package_http( package_recyclerView , getActivity(),0, buttonselectflag);
                package_http_out.execute("http://140.136.155.79/package/content.php" , number_id , 1);

            }
            update_recycle_data1 =0;

        }
    }

    @Override
    public void onPause() {
        super.onPause();
        treadBoolean=1;
    }

    @Override
    public void onResume() {
        super.onResume();
        treadBoolean=0;
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {

        while (treadBoolean == 0) {
            try {
                Thread.sleep(300);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {

                if(UserData.manger.equals("3") ||UserData.manger.equals("2"))
                {
                    number_id = getActivity().getIntent().getStringExtra("number_id");
                    Package_http package_http_out2 = new Package_http( package_recyclerView , getActivity(),16, buttonselectflag , or_size );
                    package_http_out2.execute("http://140.136.155.79/package/manager_content.php" , UserData.comunity_id , 1);
                }else if(UserData.manger.equals("0") ||UserData.manger.equals("1")){

                    number_id = getActivity().getIntent().getStringExtra("number_id");
                    Package_http package_http_out2 = new Package_http( package_recyclerView , getActivity(),17, buttonselectflag ,or_size);
                    package_http_out2.execute("http://140.136.155.79/package/content.php" , number_id , 1);

                }

                if(update_recycle_data1 ==1)
                {

                    Message message = sendmessage_toupdate_recycle.obtainMessage();
                    message.what = 2;
                    sendmessage_toupdate_recycle.sendMessage(message);
                }
            }

        }
    }
}
