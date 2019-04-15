package com.shot.community.go;

import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.Package_calss.Package_http;
import com.shot.community.go.PagerAdapt.Home_SectionPagerAdapt;
import com.shot.community.go.http_meth.UserData;


public class MainActivity extends AppCompatActivity {
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    String logDTag="logDTag";
    public static Package_http package_http;//讀取管理者資訊 (預先) for 聯絡管理員
    Home_SectionPagerAdapt homeSectionPagerAdapt;
    private DrawerLayout drawerLayout;
    private NavigationView navigation_view;
    ActionBarDrawerToggle toggle;
    public static ViewPager mViewPager;//static 非本類別皆可調動 home新信件to信件fragment
    Context context = getApplication();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar實現
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(UserData.comunity_name);

        Log.d("geriobr" , "man");

        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);
        navigation_view = (NavigationView) findViewById(R.id.navigation_view);
        if(UserData.manger.equals("1"))
        {
            Menu drawer_menu = navigation_view.getMenu();
            MenuItem drawer_menu_item4 = drawer_menu.findItem(R.id.drawer_fux);
            drawer_menu_item4.setTitle("維修申請");
            MenuItem drawer_menu_item5 = drawer_menu.findItem(R.id.drawer_appoint);
            drawer_menu_item5.setTitle("預約公共設施");
            drawer_menu.add(0 ,navigation_view.getHeaderCount()+1 , navigation_view.getHeaderCount()+1
                    , "用戶管理").setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    MainActivity.mViewPager.setCurrentItem(9);
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true;
                }
            });
            MenuItem drawer_menu_item9 =  drawer_menu.getItem(9);
            drawer_menu_item9.setIcon(R.drawable.ic_announce_picture);
        }else if(UserData.manger.equals("2"))
        {
            Menu drawer_menu = navigation_view.getMenu();
            MenuItem drawer_menu_item4 = drawer_menu.findItem(R.id.drawer_fux);
            drawer_menu_item4.setVisible(false);
            MenuItem drawer_menu_item5 = drawer_menu.findItem(R.id.drawer_appoint);
            drawer_menu_item5.setVisible(false);
            MenuItem drawer_menu_item6 = drawer_menu.findItem(R.id.drawer_dicuss);
            drawer_menu_item6.setVisible(false);
            MenuItem drawer_menu_item7 = drawer_menu.findItem(R.id.drawer_photo);
            drawer_menu_item7.setVisible(false);
            MenuItem drawer_menu_item8 = drawer_menu.findItem(R.id.drawer_detail);
            drawer_menu_item8.setVisible(false);
        }else if(UserData.manger.equals("3"))
        {
            Menu drawer_menu = navigation_view.getMenu();
            MenuItem drawer_menu_item4 = drawer_menu.findItem(R.id.drawer_fux);
            drawer_menu_item4.setTitle("維修管理");
            MenuItem drawer_menu_item5 = drawer_menu.findItem(R.id.drawer_appoint);
            drawer_menu_item5.setTitle("預約管理");
        }

        Log.d("geriobr" , "man2");
        View head = navigation_view.getHeaderView(0);
        TextView textView =(TextView)head.findViewById(R.id.drawer_top_text);
        textView.setText(UserData.name);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.drawer_open, R.string.drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        //抽屜實現
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                drawerLayout.closeDrawer(GravityCompat.START);
                int selectDrawerID = item.getItemId();

                if(selectDrawerID == R.id.drawer_home)
                {
                    Toast.makeText(MainActivity.this, "首頁",Toast.LENGTH_SHORT).show();
                    MainActivity.mViewPager.setCurrentItem(0);
                    return true;
                }else if(selectDrawerID == R.id.drawer_announcement)
                {

                    MainActivity.mViewPager.setCurrentItem(1);
                    return true;
                }else if(selectDrawerID == R.id.drawer_package)
                {

                    MainActivity.mViewPager.setCurrentItem(2);
                    return true;
                }else if(selectDrawerID == R.id.drawer_mailbox)
                {

                    MainActivity.mViewPager.setCurrentItem(3);
                    return true;
                }else if(selectDrawerID == R.id.drawer_fux)
                {

                    MainActivity.mViewPager.setCurrentItem(4);
                    return true;
                }else if(selectDrawerID == R.id.drawer_appoint)
                {

                    MainActivity.mViewPager.setCurrentItem(5);
                    return true;
                }else if(selectDrawerID == R.id.drawer_dicuss)
                {

                    MainActivity.mViewPager.setCurrentItem(6);
                    return true;
                }else if(selectDrawerID == R.id.drawer_photo)
                {

                    MainActivity.mViewPager.setCurrentItem(7);
                    return true;
                }else if(selectDrawerID == R.id.drawer_detail)
                {

                    MainActivity.mViewPager.setCurrentItem(8);
                    return true;
                }

                if(UserData.manger.equals("3"))
                {
                    if(selectDrawerID == 9)
                    {

                        MainActivity.mViewPager.setCurrentItem(9);
                        return true;
                    }
                }

                return false;
            }
        });
        Log.d("geriobr" , "man3");
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        homeSectionPagerAdapt = new Home_SectionPagerAdapt(getSupportFragmentManager());
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(homeSectionPagerAdapt);
        Log.d("geriobr" , "man4");

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mViewPager.setOffscreenPageLimit(10);//防止滑動fragment銷毀

        Log.d("geriobr" , "man5");



    }

    private long firstPressedTime;

    // System.currentTimeMillis() 当前系统的时间
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() - firstPressedTime < 2000) {
            super.onBackPressed();
            finish();
        } else {
            Toast.makeText(MainActivity.this, "再按一次退出", Toast.LENGTH_SHORT).show();
            firstPressedTime = System.currentTimeMillis();
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
         getMenuInflater().inflate(R.menu.menu_main, menu);
        for(int i = 0; i < menu.size(); i++){
            Drawable drawable = menu.getItem(i).getIcon();
            if(drawable != null) {
                drawable.mutate();
                drawable.setColorFilter(getResources().getColor(R.color.text), PorterDuff.Mode.SRC_ATOP);
            }
        }


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == R.id.menu_home_notice)
        {
            Intent intent = new Intent(this , Use_dscript.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}

