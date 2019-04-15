package com.shot.community.go.Detail_calss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.shot.community.go.R;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/16.
 */

public class Fix_recotd_content extends AppCompatActivity {

    TextView package_Name;
    TextView package_Id;
    TextView package_status;
    TextView package_date;
    String packgae_imageurl;
    ImageView imageView;
    ArrayList<String> image;
    Button signButton;
    RecyclerView recyclerView;
   String Spackage_Id;
    String pg_id;
    String httpurl;
    String Spackage_status;

    String Isplace;
    String Inumbar;
    String Istatus;
    String Icontent;
    String Isystemdate;
    String Isystemdate1;

    TextView tIsplace;
    TextView tInumbar;
    TextView tIstatus;
    TextView tIcontent;
    TextView tIsystemdate;
    TextView tIsystemdate1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fixrecorde_item_conteny);
        Toolbar toolbar = (Toolbar)findViewById(R.id.cpackage_item_contentToolbat);
        recyclerView = (RecyclerView) findViewById(R.id.package_contentrecycle);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        ArrayList<String> image = intent.getStringArrayListExtra("image");

        TextView tIsplace = (TextView) findViewById(R.id.cpackage_recycle_item_numberID_text_text);
        TextView tInumbar = (TextView) findViewById(R.id.cpackage_recycle_item_staus_text11);
        TextView tIstatus = (TextView) findViewById(R.id.cpackage_recycle_item_staus_text);
        TextView tIcontent = (TextView) findViewById(R.id.cpackage_recycle_item_numberName_text_text);
        TextView tIsystemdate = (TextView) findViewById(R.id.cpackage_recycle_item_catchTime_text_text);;
        TextView tIsystemdate1 = (TextView) findViewById(R.id.cpackage_recycle_item_Time_text_text);;


        Inumbar = intent.getStringExtra("name");
        Istatus = intent.getStringExtra("status");
        Isplace = intent.getStringExtra("splace");
        Icontent = intent.getStringExtra("content");
        Isystemdate = intent.getStringExtra("systemdate");
        Isystemdate1 = intent.getStringExtra("systemdate1");


        tInumbar.setText(Inumbar);
        tIcontent.setText(Icontent);
        if(Istatus.equals("0"))
        {
            tIstatus.setText("未處理");
        }else if(Istatus.equals("1"))
        {
            tIstatus.setText("處理中");
        }
        else if(Istatus.equals("2"))
        {
            tIstatus.setText("已處理");
        }
        tIsplace.setText(Isplace);
        tIsystemdate.setText(Isystemdate);
        tIsystemdate1.setText(Isystemdate1);



        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL ,false);
        detail_fix_record_content_image_recycleAdapter package_content_image_recycleAdapter = new detail_fix_record_content_image_recycleAdapter(Fix_recotd_content.this , image);
        recyclerView.setAdapter(package_content_image_recycleAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication(), 1);
        recyclerView.setLayoutManager(linearLayoutManager);




//        Intent intent = getIntent();
//        httpurl = intent.getStringExtra("httpurl");
//        pg_id = intent.getStringExtra("package_Id");
//        packgae_imageurl = intent.getStringExtra("package_imageurl");
//        String Spackage_Name = intent.getStringExtra("package_Name");
//        image = intent.getStringArrayListExtra("package_image");
//        Spackage_Id = intent.getStringExtra("package_Id");
//      Spackage_status = intent.getStringExtra("package_status");
//        String Spackage_date = intent.getStringExtra("package_date");
//
//
//
//        pg_id = Spackage_Id;
//
//
//
//
        signButton = (Button)findViewById(R.id.package_sign_Button);
        signButton.setVisibility(View.INVISIBLE);
        package_Name =(TextView) findViewById(R.id.cpackage_recycle_item_numberName_text_text);
        package_Id =(TextView) findViewById(R.id.cpackage_recycle_item_numberID_text_text);
        package_status =(TextView) findViewById(R.id.cpackage_recycle_item_staus_text);
        package_date =(TextView) findViewById(R.id.cpackage_recycle_item_Time_text_text);


//
//        package_Name.setText(Spackage_Name);
//        package_Id.setText(Spackage_Id);
//        package_status.setText(Spackage_status);
//        package_date.setText(Spackage_date);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.announcement_cotent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();

        switch (selectId){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
