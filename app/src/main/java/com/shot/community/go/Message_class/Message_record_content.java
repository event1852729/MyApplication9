package com.shot.community.go.Message_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.shot.community.go.R;

/**
 * Created by god on 2017/10/21.
 */

public class Message_record_content extends AppCompatActivity {
    TextView textView1,textView2,textView3;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_record_content);
        Toolbar toolbar = (Toolbar)findViewById(R.id.message_record_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView1 = (TextView)findViewById(R.id.message_record_text1);
        textView2 = (TextView)findViewById(R.id.message_record_text2);
        textView3 = (TextView)findViewById(R.id.message_record_text3);

        Intent intent = getIntent();
        String numbet_Name = intent.getStringExtra("number_NAME");
        String content = intent.getStringExtra("content");
        String date = intent.getStringExtra("date");

        textView1.setText(numbet_Name);
        textView2.setText(content);
        textView3.setText(date);



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
