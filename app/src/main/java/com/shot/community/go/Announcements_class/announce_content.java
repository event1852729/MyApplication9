package com.shot.community.go.Announcements_class;

import android.app.DownloadManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shot.community.go.R;

/**
 * Created by user on 2017/5/1.
 */

public class announce_content extends AppCompatActivity {

    TextView Announcement_content;
    TextView Announcement_title;
    Button Announcement_file;
    String announcement_file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announce_content_file);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_Announcement_cotent_Id);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        Announcement_content = (TextView)findViewById(R.id.announce_content);
        Announcement_title = (TextView)findViewById(R.id.announce_content_title);
        Announcement_file = (Button) findViewById(R.id.announce_content_file);
        Intent intent = getIntent();
        final String announcement_content = intent.getStringExtra("Announcement_content");
        String announcement_title = intent.getStringExtra("Announcement_title");
        announcement_file = intent.getStringExtra("Announcement_file");
        Announcement_content.setText(announcement_content);
        Announcement_title.setText(announcement_title);
        Announcement_file.setText(announcement_file);
        Announcement_file.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadManager.Request req = new DownloadManager.Request(Uri.parse("http://140.136.155.79/news/uploads/"+announcement_file));

                req.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                req.setTitle(announcement_file);
                DownloadManager dm = (DownloadManager)announce_content.this.getSystemService(announce_content.this.DOWNLOAD_SERVICE);
                long downloadId = dm.enqueue(req);
            }
        });
        if(Announcement_file.getText().toString().equals(""))
        {
            Announcement_file.setVisibility(View.INVISIBLE);
        }
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
