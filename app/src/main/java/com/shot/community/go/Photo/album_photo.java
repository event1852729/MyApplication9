package com.shot.community.go.Photo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by user on 2017/11/17.
 */

public class album_photo extends AppCompatActivity {

    RecyclerView recyclerView;
    Photo_http photoHttp;
    FloatingActionButton floatingActionButton;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    protected void onCreate(Bundle savedInstanceState) {
        // Inflate the layout for this fragmen
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_recycler);

        recyclerView = (RecyclerView)findViewById(R.id.photo_RecycleView);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.photo_add);
        Intent intent1 = getIntent();
        final String abId = intent1.getStringExtra("id");
        final String Name = intent1.getStringExtra("name");
        Toolbar toolbar = (Toolbar)findViewById(R.id.photo_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setTitle(Name);

        photoHttp = new Photo_http(recyclerView,album_photo.this,2);
        photoHttp.execute("http://140.136.155.79/photo/content.php",abId);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                photoHttp = new Photo_http(recyclerView,album_photo.this,2);
                photoHttp.execute("http://140.136.155.79/photo/content.php",abId);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        int ma = Integer.valueOf(UserData.manger);

        if (ma==1 || ma==3){
            floatingActionButton.setVisibility(View.VISIBLE);
        }
        else {
            floatingActionButton.setVisibility(View.GONE);
        }

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(album_photo.this,add_photo.class);
                intent3.putExtra("Ab_id",abId);
                intent3.putExtra("name",Name);
                startActivity(intent3);
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
