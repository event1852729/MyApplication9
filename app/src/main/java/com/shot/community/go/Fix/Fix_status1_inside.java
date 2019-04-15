package com.shot.community.go.Fix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shot.community.go.R;

/**
 * Created by user on 2017/12/3.
 */

public class Fix_status1_inside extends AppCompatActivity {

    TextView Place,Detail,ID,People,Phone,fxid;
    Button write;
    ImageView imageView;
    String status;
    String Fixid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fix_status1_inside);
        Toolbar toolbar = (Toolbar)findViewById(R.id.cpackage_item_contentToolbat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String  image = intent.getStringExtra("imageurl");
        String D = intent.getStringExtra("detail");
        String P = intent.getStringExtra("place");
        String I = intent.getStringExtra("id");
        String FN = intent.getStringExtra("fname");
        String FP = intent.getStringExtra("fphone");
        Fixid = intent.getStringExtra("fixid");
        status = intent.getStringExtra("status");

        Place = (TextView)findViewById(R.id.place_text);
        Detail = (TextView)findViewById(R.id.detail_text);
        ID = (TextView)findViewById(R.id.idnum);
        imageView = (ImageView)findViewById(R.id.image);
        People = (TextView)findViewById(R.id.people);
        Phone = (TextView)findViewById(R.id.phone);
        write = (Button)findViewById(R.id.write);
        fxid = (TextView)findViewById(R.id.fixid);

        Place.setText(P);
        Detail.setText(D);
        People.setText(FN);
        ID.setText(I);
        Phone.setText(FP);
        fxid.setText("NO . 0" + Fixid);



        Glide.with(Fix_status1_inside.this)
                .load(image)
                .into(imageView);

       write.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent2 = new Intent(Fix_status1_inside.this,Fix_Sign_package.class);
                    intent2.putExtra("FixID",Fixid);
                    startActivity(intent2);
                    Fix_http fixHttp = new Fix_http(5);
                    fixHttp.execute("http://140.136.155.79/fix/update_sign.php",Fixid,status);
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
