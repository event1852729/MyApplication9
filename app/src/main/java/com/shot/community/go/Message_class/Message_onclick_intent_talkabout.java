package com.shot.community.go.Message_class;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
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
import android.widget.EditText;
import android.widget.Toast;

import com.shot.community.go.R;

/**
 * Created by god on 2017/10/20.
 */

public class Message_onclick_intent_talkabout extends AppCompatActivity implements Runnable  {
    FloatingActionButton floatingActionButton;
    RecyclerView message_talkabout_recycle;
    DividerItemDecoration mDivider;
    Context context =this;
    String number_Hername;
    String numbet_MyId;
    sendmessage_toupdate_recycle sendmessage_toupdate_recycle;
    String number_Cname;
    EditText editText;
    Button button;
    Message_http message_http;
    static int update_recycle_data = 0;
    Thread thread;
    int treadBoolean = 0;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_onclick_intent_talkabout_recycleview);
        Toolbar toolbar = (Toolbar)findViewById(R.id.message_onclick_intent_talkabout_toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refresh_layout);
        button = (Button) findViewById(R.id.message_talk_recycle_button) ;
        sendmessage_toupdate_recycle = new sendmessage_toupdate_recycle();
        editText = (EditText)findViewById(R.id.message_edit_recycle_edit) ;

        message_talkabout_recycle = (RecyclerView)findViewById(R.id.message_onclick_intent_talkabout_recycleview);
        message_talkabout_recycle.setHasFixedSize(true);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.message_onclick_intent_talkabout_fab);
        floatingActionButton.setVisibility(View.INVISIBLE);

        Intent intent = getIntent();
        number_Hername = intent.getStringExtra("number_name");
        number_Cname = intent.getStringExtra("number_Cname");
        numbet_MyId = intent.getStringExtra("number_myid");
        toolbar.setTitle(number_Cname);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        if(editText.getText().length()>0)
                        {

                            String content = editText.getText().toString();
                            Message_http message_http = new Message_http(Message_onclick_intent_talkabout.this , 3 );
                            message_http.execute("http://140.136.155.79/message/register_finish.php" , numbet_MyId , number_Hername , content );
                            editText.setText(null);
                        }
            }
        });


        message_http = new Message_http(this , 1 , message_talkabout_recycle );
        message_http.execute("http://140.136.155.79/message/message.php" , numbet_MyId ,number_Hername );



        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Intent intent = getIntent();
                number_Hername = intent.getStringExtra("number_name");
                number_Cname = intent.getStringExtra("number_Cname");
                numbet_MyId = intent.getStringExtra("number_myid");
                Message_http message_http = new Message_http(Message_onclick_intent_talkabout.this , 1 , message_talkabout_recycle );
                message_http.execute("http://140.136.155.79/message/message.php" , numbet_MyId ,number_Hername );
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, Message_reply.class);
                    intent.putExtra("number_Name" , number_Cname);
                    intent.putExtra("number_herName" , number_Hername);
                    intent.putExtra("number_myid" , numbet_MyId);
                    startActivity(intent);

            }
        });

        Thread thread = new Thread(this);
        thread.start();


    }

    class sendmessage_toupdate_recycle extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            message_http = new Message_http(Message_onclick_intent_talkabout.this , 1 , message_talkabout_recycle );
            message_http.execute("http://140.136.155.79/message/message.php" , numbet_MyId ,number_Hername );

            update_recycle_data=0;

        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.message_talkabout_intent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        switch (selectId){
            case android.R.id.home:
                treadBoolean = 1;
                finish();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
        treadBoolean = 1;
    }

    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        while (treadBoolean == 0) {
            try {
                Thread.sleep(300);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                Message_http message_http2 = new Message_http(Message_onclick_intent_talkabout.this, 22 , message_http.getMessage_item_modelsList().size());
                message_http2.execute("http://140.136.155.79/message/message.php", numbet_MyId, number_Hername);
                if(update_recycle_data==1)
                {
                    Message message = sendmessage_toupdate_recycle.obtainMessage();
                    message.what = 2;
                    sendmessage_toupdate_recycle.sendMessage(message);
                }
            }

        }
    }


    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */

}


