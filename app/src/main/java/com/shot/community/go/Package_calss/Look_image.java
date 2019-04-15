package com.shot.community.go.Package_calss;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shot.community.go.R;

/**
 * Created by god on 2017/11/4.
 */

public class Look_image extends AppCompatActivity {
    ImageView imageView;
    String imageurl3;
    Intent intent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.packge_image_look);
        Toolbar toolbar = (Toolbar)findViewById(R.id.packge_look_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_packge_look_menu);


        imageView  = (ImageView)findViewById(R.id.package_look_image1);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);

        Intent intent = getIntent();
        String string = intent.getStringExtra("imageurl");

        Glide.with(Look_image.this)
                .load(string)
                .into(imageView);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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
