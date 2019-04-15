package com.shot.community.go.Dicuss_class;

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
import android.widget.Toast;

import com.shot.community.go.R;

/**
 * Created by user on 2017/11/19.
 */

public class Dicuss_report_Audit extends AppCompatActivity {

    ImageView imageView;
    TextView name,detail;
    Button agree,nonagree;
    Dicuss_http dicuss_http;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dicuss_report_inside);
        imageView = (ImageView)findViewById(R.id.dicuss_content_im);
        name = (TextView)findViewById(R.id.dicuss_content_numbetname);
        detail = (TextView)findViewById(R.id.dicuss_content);
        agree = (Button)findViewById(R.id.agree);
        nonagree = (Button)findViewById(R.id.nonagree);
        String N,D,bd,rp;
        Toolbar toolbar = (Toolbar)findViewById(R.id.dicuss_content_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        N = intent.getStringExtra("name");
        D = intent.getStringExtra("detail");
        bd = intent.getStringExtra("bd_id");
        rp = intent.getStringExtra("rp_id");

        name.setText(N);
        detail.setText(D);



        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rp,bd;
                Intent intent2 = getIntent();
                bd = intent2.getStringExtra("bd_id");
                rp = intent2.getStringExtra("rp_id");
                dicuss_http = new Dicuss_http(9);
                dicuss_http.execute("http://140.136.155.79/report/update_pass_finish.php",bd,rp);
                Toast.makeText(Dicuss_report_Audit.this,"審核通過",Toast.LENGTH_SHORT).show();

                finish();
            }
        });


        nonagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String rp;
                Intent intent3 = getIntent();
                rp = intent3.getStringExtra("rp_id");
                dicuss_http = new Dicuss_http(10);
                dicuss_http.execute("http://140.136.155.79/report/update_nopass_finish.php",rp);
                Toast.makeText(Dicuss_report_Audit.this,"審核不通過",Toast.LENGTH_SHORT).show();

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
