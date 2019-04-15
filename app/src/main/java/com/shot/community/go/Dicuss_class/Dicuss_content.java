package com.shot.community.go.Dicuss_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by god on 2017/10/26.
 */

public class Dicuss_content extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    TextView title;
    TextView content;
    TextView Topdate;
    TextView nobodymessage;
    TextView titlenumbername;
    String dicuss_id;
    String titletoolbar;
    Dicuss_http dicuss_http;
    FloatingActionButton floatingActionButton;
    private DividerItemDecoration mDivider;
    Button buttonupdate;
    String numbername;
    String dicuss_content;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dicuss_content);
        Toolbar toolbar = (Toolbar)findViewById(R.id.dicuss_content_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.dicuss_content_RecycleView);
        nobodymessage = (TextView)findViewById(R.id.dicuss_content_count) ;
        titlenumbername = (TextView)findViewById(R.id.dicuss_content_numbetname) ;
        buttonupdate = (Button)findViewById(R.id.update_diciss_content_manager_button);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        Topdate = (TextView)findViewById(R.id.dicuss_content_date);
        content = (TextView)findViewById(R.id.dicuss_content);
//        floatingActionButton = (FloatingActionButton)findViewById(R.id.dicuss_cotent_fab_fab);
        mDivider = new DividerItemDecoration(Dicuss_content.this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDivider);
        title = (TextView) findViewById(R.id.dicuss_content_title);

        Intent intent = getIntent();
        titletoolbar = intent.getStringExtra("title");
        dicuss_id = intent.getStringExtra("dicuss_id");
        numbername = intent.getStringExtra("number_name");
        String topdate = intent.getStringExtra("date");
        dicuss_content = intent.getStringExtra("content");
        toolbar.setTitle(titletoolbar);
        title.setText(titletoolbar);
        content.setText(dicuss_content);
        titlenumbername.setText(numbername);
        Topdate.setText(topdate);

        if(numbername.equals(UserData.name))
        {
            buttonupdate.setVisibility(View.VISIBLE);
        }else {
            buttonupdate.setVisibility(View.INVISIBLE);
        }

        buttonupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Dicuss_content.this , Upadte_dicuss.class);
                intent.putExtra("number_name" , numbername);
                intent.putExtra("title" , titletoolbar);
                intent.putExtra("content" , dicuss_content);
                intent.putExtra("bd_id" , dicuss_id);
                startActivity(intent);
                finish();

            }
        });



        dicuss_http= new Dicuss_http(Dicuss_content.this , 1, recyclerView ,nobodymessage );
        dicuss_http.execute("http://140.136.155.79/lm/content.php" , dicuss_id);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                dicuss_http= new Dicuss_http(Dicuss_content.this , 1, recyclerView ,nobodymessage );
                dicuss_http.execute("http://140.136.155.79/lm/content.php" , dicuss_id);
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });



//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation_content_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dicuss_content_menu, menu);
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



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.Totop:
                finish();
                break;
            case R.id.favourites:
                Intent intent = new Intent(Dicuss_content.this , Reply_dicuss.class);
                intent.putExtra("bd_id" , dicuss_id);
                intent.putExtra("title" , titletoolbar);
                finish();
                startActivity(intent);
                break;
            case R.id.nearby:
                Intent intent1 = new Intent(Dicuss_content.this , Report_dicuss.class);
                intent1.putExtra("bd_id" , dicuss_id);
                intent1.putExtra("title" , titletoolbar);
                startActivity(intent1);
                finish();
                break;
            default:
        }
        return false;
    }
}
