package com.shot.community.go;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.DividerItemDecoration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shot.community.go.Message_class.Message_sendtomanager;
import com.shot.community.go.http_meth.SigninActivity;
import com.shot.community.go.http_meth.UserData;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.loonggg.rvbanner.lib.RecyclerViewBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/11/27.
 */

public class HOME extends Fragment implements Runnable{
    private DividerItemDecoration mDivider;
    AutoPollRecyclerView recyclerView;
    AutoPollRecyclerView mRecyclerView ;
    ArrayList<Home_Auto_model> list = new ArrayList<>();

    FloatingActionsMenu floatingActionButton;
    FloatingActionButton floatingActionButton1,floatingActionButton2;
    ListView listView;
    Context context = getActivity();
    SigninActivity mySignActivity,mySignActivity2;



    private int[] image = {R.drawable.ic_detail1,R.drawable.ic_page
    ,R.drawable.ic_mgg,R.drawable.ic_detail3,R.drawable.ic_photo,R.drawable.ic_announce_picture
    ,R.drawable.ic_aplica,R.drawable.ic_detail2,R.drawable.ic_addperson};

    private int[] image2 = {R.drawable.ic_detail1,R.drawable.ic_page
            ,R.drawable.ic_mgg,R.drawable.ic_detail2,R.drawable.ic_photo,R.drawable.ic_detail3
    ,R.drawable.ic_aplica,R.drawable.ic_addperson};

    private String[] itemqqq = {"社區公告   ","包裹管理   ","我的訊息  ","各項紀錄   ","社區相簿   ","住戶管理   ","預約管理   "
    ,"維修管理  ","社區討論區  "};

    private String[] item2 = {"社區公告   ","我的包裹   ","我的訊息   ","維修申請  > ","社區相簿   ","各項紀錄   ","預約設施   "
            ,"社區討論區   "};
    sendmessage_toupdate_recycle sendmessage_toupdate_recycle;
    sendmessage_toupdate_recycle2 sendmessage_toupdate_recycle2;
    public static int update_recycle_data1 = 0;
    public static int update_recycle_data2 = 0;
    Thread thread;
    int threadboolean = 0;
    public static int treadBoolean = 0;
    SigninActivity signinActivity5;
    TextView textViewnewmail;
    TextView textViewnewann;
    TextView textViewnewpackage;
    SigninActivity signinActivity6;
    public HOME() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.home_new_manager, container, false);
        
//        recyclerView = (RecyclerView)view.findViewById(R.id.home_RecycleView);
//        TextView textView = (TextView)view.findViewById(R.id.home_picture_more);
//        textView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.mViewPager.setCurrentItem(7);
//            }
//        });

        Button buttonmore = (Button) view.findViewById(R.id.home_classbutton3);
        buttonmore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.mViewPager.setCurrentItem(1);
            }
        });
        buttonmore.setBackgroundColor(Color.DKGRAY);
        Log.d("geriobr" , "home0");
//        textViewnewmail = (TextView)view.findViewById(R.id.home_new_mail);
//        textViewnewmail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.mViewPager.setCurrentItem(3);
//            }
//        });
//        textViewnewmail.setText("3日內未讀訊息 : "+login_index.signinActivity3.s[0]);
//        textViewnewann = (TextView)view.findViewById(R.id.home_ann_mail);
//        textViewnewann.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.mViewPager.setCurrentItem(1);
//            }
//        });
//        textViewnewann.setText("3日內未讀公告 : "+login_index.signinActivity3.s[1]);
//        textViewnewpackage = (TextView)view.findViewById(R.id.home_package_mail);
//        textViewnewpackage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                MainActivity.mViewPager.setCurrentItem(2);
//            }
//        });
//        textViewnewpackage.setText("3日內未讀包裹 : "+login_index.signinActivity3.s[2]);



        Log.d("geriobr" , "home10");

        //imgaeColorchange

        ImageView imagemail = (ImageView)view.findViewById(R.id.home_mail_image);
        ImageView imageann = (ImageView)view.findViewById(R.id.home_ann_image);
        ImageView imagepackage = (ImageView)view.findViewById(R.id.home_package_image);
        int color = 0xff9f0800;
        int color1 = 0xffE1AD1F;
        imagemail.setColorFilter(color);
        imageann.setColorFilter(Color.DKGRAY);
        imagepackage.setColorFilter(Color.DKGRAY);

        //sendtomanager
        TextView home_sendtomanagerText = (TextView)view.findViewById(R.id.home_send_to_manager);
        ImageView imageViewsendtomanager = (ImageView)view.findViewById(R.id.home_send_to_manager_image);
        imageViewsendtomanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(UserData.manger.equals("0") || UserData.manger.equals("2"))
                {
                    Intent intent = new Intent(getActivity() , Message_sendtomanager.class);
                    startActivity(intent);
                }else if(UserData.manger.equals("1") || UserData.manger.equals("3"))
                {
                    Intent intent = new Intent(getActivity() , Add_home_photo.class);
                    startActivity(intent);
                }

            }
        });
        if(UserData.manger.equals("0") || UserData.manger.equals("2"))
        {

        }else if(UserData.manger.equals("1") || UserData.manger.equals("3"))
        {
            imageViewsendtomanager.setImageDrawable(getResources().getDrawable(R.drawable.ic_home_add_picture));
            home_sendtomanagerText.setText("新\n增\n首\n頁\n照\n片");
        }
        Log.d("geriobr" , "home11");

        Button button = (Button)view.findViewById(R.id.home_classbutton);
        button.setBackgroundColor(Color.BLUE);
        ScrollView scrollView = (ScrollView)view.findViewById(R.id.home_manager_scro);
        scrollView.smoothScrollTo(0 , 0);

//        int mgid = Integer.parseInt(UserData.manger);
//
//        if (mgid==1)
//        {
//            HOME_Model[] homeModels = {new HOME_Model(image[0],itemqqq[0]),
//                    new HOME_Model(image[1],itemqqq[1]),new HOME_Model(image[2],itemqqq[2]),
//                    new HOME_Model(image[3],itemqqq[3]),new HOME_Model(image[4],itemqqq[4]),
//                    new HOME_Model(image[5],itemqqq[5]),new HOME_Model(image[6],itemqqq[6]),
//                    new HOME_Model(image[7],itemqqq[7]),new HOME_Model(image[8],itemqqq[8])};
//            HOME_Adapter homeAdapter = new HOME_Adapter(getActivity(),homeModels);
//            recyclerView.setAdapter(homeAdapter);
//            recyclerView.setHasFixedSize(true);
//            mDivider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
//            recyclerView.addItemDecoration(mDivider);
//            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
//            recyclerView.setLayoutManager(gridLayoutManager);
//            gridLayoutManager.scrollToPosition(0);
//        }
//        else
//        {
//            HOME_Model[] homeModels = {new HOME_Model(image2[0],item2[0]),
//                    new HOME_Model(image2[1],item2[1]),new HOME_Model(image2[2],item2[2]),
//                    new HOME_Model(image2[3],item2[3]),new HOME_Model(image2[4],item2[4]),
//                    new HOME_Model(image2[5],item2[5]),new HOME_Model(image2[6],item2[6]),
//                    new HOME_Model(image2[7],item2[7])};
//            HOME_Adapter homeAdapter = new HOME_Adapter(getActivity(),homeModels);
//            recyclerView.setAdapter(homeAdapter);
//            recyclerView.setHasFixedSize(true);
//            mDivider = new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL);
//            recyclerView.addItemDecoration(mDivider);
//            GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
//            recyclerView.setLayoutManager(gridLayoutManager);
//            gridLayoutManager.scrollToPosition(0);
//        }
        Log.d("geriobr" , "home12");

        final SwipeRefreshLayout mSwipeRefreshLayout;
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.home_refresh_layout);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               login_index.signinActivity4 = new SigninActivity(34);
                login_index.signinActivity4.execute("http://140.136.155.79/image/image_content.php",UserData.comunity_id);
                RecyclerViewBanner recyclerViewBanner1 = (RecyclerViewBanner) view.findViewById(R.id.rv_rv_recycleView);
                final List<Banner> banners = new ArrayList<>();
                if(login_index.signinActivity4.home_photoList.size()>0)
                {
                    for(int i = 0 ; i<login_index.signinActivity4.home_photoList.size() ; i++)
                    {
                        if(i<5)
                        {
                            banners.add(new Banner("http://140.136.155.79/image/uploads/" + login_index.signinActivity4.home_photoList.get(i)));
                        }
                    }
                }else {
                    banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1487221110004&di=d6043e4b0c90ddf3ea5096c3d8eb8f58&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2014%2F067%2F5116EPAUD762_1000x500.jpg"));
                    banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490438881557&di=e61065ccc8d7b44591e1c4ba8df672ee&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fzhidao%2Fpic%2Fitem%2F18d8bc3eb13533fa00428309a0d3fd1f41345b24.jpg"));
                    banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490440243430&di=6f8d7c608a4e3fbe4130c93fc0f20850&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201509%2F09%2F20150909184342_mkrWc.jpeg"));
                    banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490440556037&di=ade75ba29126922124b063a2a57873f7&imgtype=0&src=http%3A%2F%2Fi2.download.fd.pchome.net%2Ft_960x600%2Fg1%2FM00%2F0E%2F05%2FooYBAFTbGOmIDPSLAAXPs6l7AQMAACSVgDyBqkABc_L421.jpg"));
                }
                recyclerViewBanner1.setRvBannerData(banners);
                recyclerViewBanner1.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
                    @Override
                    public void switchBanner(int position, AppCompatImageView bannerView) {

                        Glide.with(getActivity())
                                .load(banners.get(position).getUrl())
                                .into(bannerView);
                    }
                });
                recyclerViewBanner1.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
                    @Override
                    public void onClick(int position) {
                    }
                });
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        Log.d("geriobr" , "home1");

        recyclerView = (AutoPollRecyclerView)view.findViewById(R.id.rv_rv_recycleView2);
        recyclerView.setHasFixedSize(true);
        mySignActivity = new SigninActivity(getActivity() , recyclerView , mySignActivity , 22);
        mySignActivity.execute("http://140.136.155.79/news/countent.php" , UserData.comunity_id);




        RecyclerViewBanner recyclerViewBanner1 = (RecyclerViewBanner) view.findViewById(R.id.rv_rv_recycleView);
        final List<Banner> banners = new ArrayList<>();

            banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490440243430&di=6f8d7c608a4e3fbe4130c93fc0f20850&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201509%2F09%2F20150909184342_mkrWc.jpeg"));
            banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490440243430&di=6f8d7c608a4e3fbe4130c93fc0f20850&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201509%2F09%2F20150909184342_mkrWc.jpeg"));
            banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490440243430&di=6f8d7c608a4e3fbe4130c93fc0f20850&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201509%2F09%2F20150909184342_mkrWc.jpeg"));
            banners.add(new Banner("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1490440243430&di=6f8d7c608a4e3fbe4130c93fc0f20850&imgtype=0&src=http%3A%2F%2Fimg3.duitang.com%2Fuploads%2Fitem%2F201509%2F09%2F20150909184342_mkrWc.jpeg"));

        recyclerViewBanner1.setRvBannerData(banners);
        recyclerViewBanner1.setOnSwitchRvBannerListener(new RecyclerViewBanner.OnSwitchRvBannerListener() {
            @Override
            public void switchBanner(int position, AppCompatImageView bannerView) {
                Glide.with(getActivity())
                        .load(banners.get(position).getUrl())
                        .into(bannerView);
            }
        });
        recyclerViewBanner1.setOnRvBannerClickListener(new RecyclerViewBanner.OnRvBannerClickListener() {
            @Override
            public void onClick(int position) {
            }
        });
//        mRecyclerView = (AutoPollRecyclerView)view.findViewById(R.id.rv_rv_recycleView);
//        AutoPollAdapter adapter = new AutoPollAdapter(getActivity(), list);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.HORIZONTAL));
//        mRecyclerView.setAdapter(adapter);
//        if (true) //保证itemCount的总个数宽度超过屏幕宽度->自己处理
//            mRecyclerView.start();
//

        sendmessage_toupdate_recycle = new sendmessage_toupdate_recycle();
        sendmessage_toupdate_recycle2 = new sendmessage_toupdate_recycle2();
        Log.d("geriobr" , "home4");
//        signinActivity5 = new SigninActivity(33);
//        signinActivity5.execute("http://140.136.155.79/message/sevent.php", UserData.comunity_id, UserData.id, UserData.manger);

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

//        Thread thread = new Thread(this);
//        thread.start();
        threadboolean=0;
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
        while (threadboolean == 0){
            try {
                Thread.sleep(300);

                signinActivity6 = new SigninActivity(39 , signinActivity5.s  );
                signinActivity6.execute("http://140.136.155.79/message/sevent.php", UserData.comunity_id, UserData.id, UserData.manger);
                if(update_recycle_data1==1)
                {
                    Message message = sendmessage_toupdate_recycle.obtainMessage();
                    message.what = 2;
                    sendmessage_toupdate_recycle.sendMessage(message);
                }
                if(update_recycle_data2==1)
                {
                    Message message = sendmessage_toupdate_recycle.obtainMessage();
                    message.what = 3;
                    sendmessage_toupdate_recycle.sendMessage(message);
                }


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    class  cat extends Thread{
        @Override
        public void run() {
            super.run();
            while (true)
            {
                try {
                    Thread.sleep(1000);
                    if(update_recycle_data2==1)
                    {
                        Message message = sendmessage_toupdate_recycle2.obtainMessage();
                        message.what = 3;
                        sendmessage_toupdate_recycle2.sendMessage(message);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    class sendmessage_toupdate_recycle extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if(what==2) {
                textViewnewmail.setText("3日內未讀訊息 : " + signinActivity6.s[0]);

                textViewnewann.setText("3日內未讀公告 : " + signinActivity6.s[1]);

                textViewnewpackage.setText("3日內未讀包裹 : " + signinActivity6.s[2]);
                update_recycle_data1 =0;
            }
            if(what==3)
            {
                recyclerView.setHasFixedSize(true);
                mySignActivity = new SigninActivity(getActivity() , recyclerView , mySignActivity , 22);
                mySignActivity.execute("http://140.136.155.79/news/countent.php" , UserData.comunity_id);
                update_recycle_data2 = 0;
            }


        }
    }
    class sendmessage_toupdate_recycle2 extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            if(what==3)
            {
                recyclerView.setHasFixedSize(true);
                mySignActivity = new SigninActivity(getActivity() , recyclerView , mySignActivity , 22);
                mySignActivity.execute("http://140.136.155.79/news/countent.php" , UserData.comunity_id);
                update_recycle_data2 = 0;
            }

        }
    }

    private class Banner {

        String url;

        public Banner(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }
    }


}
