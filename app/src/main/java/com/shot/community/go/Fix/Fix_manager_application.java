package com.shot.community.go.Fix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by user on 2017/11/9.
 */

public class Fix_manager_application extends AppCompatActivity {

    TextView Place,Detail,Id,fixid;
    EditText fixname,fixphone;
    Button ok,no;
    ImageView imageView;
    Fix_http fixHttp;
    String getname,getphone;
    String packgae_imageurl;
    RecyclerView recyclerView;
    String d,p,i;
    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fix_application);
        Toolbar toolbar = (Toolbar)findViewById(R.id.cpackage_item_contentToolbat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Place = (TextView)findViewById(R.id.place_text);
        Detail = (TextView)findViewById(R.id.detail_text);
        Id = (TextView)findViewById(R.id.idnum);
        fixid = (TextView)findViewById(R.id.fixid);
        imageView = (ImageView)findViewById(R.id.image);


        Intent intent = getIntent();
        String packgae_imageurl = intent.getStringExtra("imageurl");
        String con = intent.getStringExtra("con");
        String name = intent.getStringExtra("name");
        String iddd = intent.getStringExtra("id");
        String FX = intent.getStringExtra("fixid");


        Place.setText(name);
        Detail.setText(con);
        fixid.setText("NO . "+FX);
        Id.setText(iddd);
        Glide.with(Fix_manager_application.this)
                .load(packgae_imageurl)
                .into(imageView);


        fixname = (EditText)findViewById(R.id.people);
        fixphone = (EditText)findViewById(R.id.phone);

        ok = (Button)findViewById(R.id.agree);
        no = (Button)findViewById(R.id.nonagree);


        fixHttp = new Fix_http(2,Id,fixid);
        fixHttp.execute("http://140.136.155.79/fix/content_manager_status_0.php");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getname = fixname.getText().toString().trim();
                getphone = fixphone.getText().toString().trim();
                if (getname.matches("") || getphone.matches("")){
                    Toast.makeText(Fix_manager_application.this,"維修人員姓名和電話為\"必填選項\"\n資料中不可以有空白",Toast.LENGTH_SHORT).show();
                }
                else if (getphone.length()<10){
                    Toast.makeText(Fix_manager_application.this,"電話輸入錯誤",Toast.LENGTH_SHORT).show();
                }
                else {
                    int p = Fix_item.pos;
                    int s = 1;
                    getname = fixname.getText().toString();
                    getphone = fixphone.getText().toString();

                    int fix = Fix_item.idnumber[p];
                    fixHttp = new Fix_http(4);
                    fixHttp.execute("http://140.136.155.79/fix/update_status_finish.php",fix,getname,getphone,s);


                    finish();
                }

            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int p = Fix_item.pos;
                int s = 2;
                getname = fixname.getText().toString();
                getphone = fixphone.getText().toString();
                int fix = Fix_item.idnumber[p];
                fixHttp = new Fix_http(4);
                fixHttp.execute("http://140.136.155.79/fix/update_status_finish.php",fix,getname,getphone,s);

                finish();
            }
        });


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
