package com.shot.community.go.Package_calss;

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
import android.widget.ScrollView;
import android.widget.TextView;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/16.
 */

public class Package_content extends AppCompatActivity {
    ScrollView scrollView;
    TextView package_Name;
    TextView package_Id;
    TextView package_status;
    TextView package_date;
    TextView package_finishdate;
    String packgae_imageurl;
    ImageView imageView;
    ArrayList<String> image;
    Button signButton;
    RecyclerView recyclerView;
   String Spackage_Id;
    String pg_id;
    String httpurl;
    String Spackage_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.package_item_conteny);
        Toolbar toolbar = (Toolbar)findViewById(R.id.cpackage_item_contentToolbat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        scrollView = (ScrollView)findViewById(R.id.pacakge_scrollview);
        scrollView.smoothScrollTo(0 , 0);


        final Intent intent = getIntent();
        String finishdate = intent.getStringExtra("package_finish_date");
        httpurl = intent.getStringExtra("httpurl");
        pg_id = intent.getStringExtra("package_Id");
        packgae_imageurl = intent.getStringExtra("package_imageurl");
        String Spackage_Name = intent.getStringExtra("package_Name");
        image = intent.getStringArrayListExtra("package_image");
        Spackage_Id = intent.getStringExtra("package_Id");
      Spackage_status = intent.getStringExtra("package_status");
        String Spackage_date = intent.getStringExtra("package_date");



        pg_id = Spackage_Id;




        recyclerView = (RecyclerView) findViewById(R.id.package_contentrecycle);
//        imageView = (ImageView)findViewById(R.id.package_content_image);
        signButton = (Button)findViewById(R.id.package_sign_Button);
//        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        package_Name =(TextView) findViewById(R.id.cpackage_recycle_item_numberName_text_text);
        package_Id =(TextView) findViewById(R.id.cpackage_recycle_item_numberID_text_text);
        package_status =(TextView) findViewById(R.id.cpackage_recycle_item_staus_text);
        package_date =(TextView) findViewById(R.id.cpackage_recycle_item_Time_text_text);
        package_finishdate =(TextView) findViewById(R.id.cpackage_recycle_item_catchTime_text_text);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this , LinearLayoutManager.HORIZONTAL ,false);

        Package_content_image_recycleAdapter package_content_image_recycleAdapter = new Package_content_image_recycleAdapter(Package_content.this , image);
        recyclerView.setAdapter(package_content_image_recycleAdapter);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplication(), 1);
        recyclerView.setLayoutManager(linearLayoutManager);




        if(((UserData.manger.toString()).equals("3") || UserData.manger.equals("2")) &&( Spackage_status.equals("代領取") ||Spackage_status.equals("代退貨") )) {
            signButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent2 = new Intent(Package_content.this, Sign_package.class);
                    intent2.putExtra("pg_id", Spackage_Id);
                    startActivity(intent2);
                    finish();
                }
            });
        }else
        {
            signButton.setVisibility(View.INVISIBLE);
        }

        package_Name.setText(Spackage_Name);
        package_Id.setText(Spackage_Id);
        package_status.setText(Spackage_status);
        package_date.setText(Spackage_date);
        package_finishdate.setText(finishdate);
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
