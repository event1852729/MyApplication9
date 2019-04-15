package com.shot.community.go.PagerAdapt;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.Log;

import com.shot.community.go.Announcement_Fragments;
import com.shot.community.go.DetailRecord_Fragments;
import com.shot.community.go.Dicuss_class.Dicuss_Fragments_new;
import com.shot.community.go.Dicuss_class.Discuss_Fragments;
import com.shot.community.go.Facility_test.Facility_test;
import com.shot.community.go.Fix.Appointment_Fix_Fragments;
import com.shot.community.go.Fix.Fix_manager;
import com.shot.community.go.HOME;
import com.shot.community.go.Mailbox_Fragments;
import com.shot.community.go.Package_Fragements;
import com.shot.community.go.Photo.Picture_Fragments;
import com.shot.community.go.facility_manager.Facility_manager_fragment;
import com.shot.community.go.http_meth.UserData;
import com.shot.community.go.number_apply_Fragments;

/**
 * Created by user on 2017/4/10.
 */

public class Home_SectionPagerAdapt extends FragmentPagerAdapter {

    public Home_SectionPagerAdapt(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        if (UserData.manger.toString().equals("1")){
            Log.d("geriobr" , "adap");
            switch (position)
            {
                case 0:
                    HOME home_fragments = new HOME();
                    return home_fragments;
                case 1:
                    Log.d("geriobr" , "annf");
                    Announcement_Fragments announcement_fragments = new Announcement_Fragments();
                    return announcement_fragments;
                case 2:
                    Package_Fragements package_fragements = new Package_Fragements();
                    return package_fragements;

                case 3:
                    Mailbox_Fragments mailbox_fragments = new Mailbox_Fragments();
                    return mailbox_fragments;
                case 4:
                    Appointment_Fix_Fragments fixManager = new Appointment_Fix_Fragments();
                    return fixManager;
                case 5:
                    Facility_test facility_fragments = new Facility_test();
                    return  facility_fragments;
                case 6:
                    Discuss_Fragments discuss_fragments = new Discuss_Fragments();
                    return discuss_fragments;
                case 7:
                    Picture_Fragments picture_fragments = new Picture_Fragments();
                    return picture_fragments;
                case 8:
                    DetailRecord_Fragments detailRecord_fragments = new DetailRecord_Fragments();
                    return detailRecord_fragments;
                case 9:
                    number_apply_Fragments calendar_fragments = new number_apply_Fragments();
                    return calendar_fragments;

            }
        }
        else if (UserData.manger.toString().equals("2")){
            switch (position)
            {
                case 0:
                    HOME home_fragments = new HOME();
                    return home_fragments;
                case 1:
                    Announcement_Fragments announcement_fragments = new Announcement_Fragments();
                    return announcement_fragments;
                case 2:
                    Package_Fragements package_fragements = new Package_Fragements();
                    return package_fragements;

                case 3:
                    Mailbox_Fragments mailbox_fragments = new Mailbox_Fragments();
                    return mailbox_fragments;
            }
        }
        else if (UserData.manger.toString().equals("3")){
            switch (position)
            {
                case 0:
                    HOME home_fragments = new HOME();
                    return home_fragments;
                case 1:
                    Announcement_Fragments announcement_fragments = new Announcement_Fragments();
                    return announcement_fragments;
                case 2:
                    Package_Fragements package_fragements = new Package_Fragements();
                    return package_fragements;

                case 3:
                    Mailbox_Fragments mailbox_fragments = new Mailbox_Fragments();
                    return mailbox_fragments;
                case 4:
                    Fix_manager fixManager = new Fix_manager();
                    return fixManager;
                case 5:
                    Facility_manager_fragment facility_fragments = new Facility_manager_fragment();
                    return  facility_fragments;
                case 6:
                    Dicuss_Fragments_new discuss_fragments = new Dicuss_Fragments_new();
                    return discuss_fragments;
                case 7:
                    Picture_Fragments picture_fragments = new Picture_Fragments();
                    return picture_fragments;
                case 8:
                    DetailRecord_Fragments detailRecord_fragments = new DetailRecord_Fragments();
                    return detailRecord_fragments;
            }
        }

        else {
            switch (position)
            {
                case 0:
                    HOME home_fragments = new HOME();
                    return home_fragments;
                case 1:
                    Announcement_Fragments announcement_fragments = new Announcement_Fragments();
                    return announcement_fragments;
                case 2:
                    Package_Fragements package_fragements = new Package_Fragements();
                    return package_fragements;

                case 3:
                    Mailbox_Fragments mailbox_fragments = new Mailbox_Fragments();
                    return mailbox_fragments;
                case 4:
                    Appointment_Fix_Fragments appointment_fix_fragments = new Appointment_Fix_Fragments();
                    return appointment_fix_fragments;
                case 5:
                    Facility_test facility_fragments = new Facility_test();
                    return  facility_fragments;
                case 6:
                    Discuss_Fragments discuss_fragments = new Discuss_Fragments();
                    return discuss_fragments;
                case 7:
                    Picture_Fragments picture_fragments = new Picture_Fragments();
                    return picture_fragments;
                case 8:
                    DetailRecord_Fragments detailRecord_fragments = new DetailRecord_Fragments();
                    return detailRecord_fragments;
            }
        }

        return null;
    }
    @Override
    public int getCount() {
        // Show 3 total pages.
        int i=9;
        if (UserData.manger.toString().equals("1"))
        {
            i=10;
        }
        if (UserData.manger.toString().equals("2"))
        {
            i=4;
        }
        if (UserData.manger.toString().equals("3"))
        {
            i=9;
        }
        return i;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (UserData.manger.toString().equals("1")){
            switch (position) {
                case 0:
                    return "首頁";
                case 1:
                    Log.d("geriobr" , "title_ann");
                    return "社區公告";
                case 2:
                    return "包裹管理";
                case 3:
                    return "我的訊息";
                case 4:
                    return " 維修申請";
                case 5:
                    return "預約公共設施";
                case 6:
                    return "社區討論區";
                case 7:
                    return "社區相簿";
                case 8:
                    return "各項紀錄";
                case 9:
                    return "住戶管理";
            }
        }

        else if (UserData.manger.toString().equals("2"))
        {
            switch (position) {
                case 0:
                    return "首頁";
                case 1:
                    return "社區公告";
                case 2:
                    return "包裹管理";
                case 3:
                    return "我的訊息";
            }
        }
        else if (UserData.manger.toString().equals("3")){
            switch (position) {
                case 0:
                    return "首頁";
                case 1:
                    return "社區公告";
                case 2:
                    return "包裹管理";
                case 3:
                    return "我的訊息";
                case 4:
                    return " 維修管理";
                case 5:
                    return "預約管理";
                case 6:
                    return "社區討論區";
                case 7:
                    return "社區相簿";
                case 8:
                    return "各項紀錄";

            }
        }

        else
        {
            switch (position) {
                case 0:
                    return "首頁";
                case 1:
                    return "社區公告";
                case 2:
                    return "我的包裹";
                case 3:
                    return "我的訊息";
                case 4:
                    return " 維修申請";
                case 5:
                    return "預約公共設施";
                case 6:
                    return "社區討論區";
                case 7:
                    return "社區相簿";
                case 8:
                    return "各項紀錄";
            }
        }

        return null;
    }
}