package com.shot.community.go.Message_class;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.R;

/**
 * Created by god on 2017/10/21.
 */

public class Message_reply extends AppCompatActivity {
    String number_Name;
    EditText reply_Content;
    String number_herid;
    String number_myid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_reply);
        Toolbar toolbar = (Toolbar)findViewById(R.id.message_reply_toolbar);
        setSupportActionBar(toolbar);
        Intent intent = getIntent();
        number_Name = intent.getStringExtra("number_Name");
        number_herid = intent.getStringExtra("number_herName");
        number_myid = intent.getStringExtra("number_myid");
        reply_Content = (EditText)findViewById(R.id.message_edittext_content);
        TextView numberText = (TextView)findViewById(R.id.message_replyname);
        numberText.setText("收件人 : " +number_Name);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.message_reply_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        if(selectId == R.id.message_reply_send)
        {
            final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Message_reply.this,R.style.MyAlertDialogStyle);
            real_delete.setTitle("確定回信?");
            real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(reply_Content.getText().toString().trim().length()>0)
                    {




                        String content = reply_Content.getText().toString();
                        Message_http message_http = new Message_http(Message_reply.this , 3 );
                        message_http.execute("http://140.136.155.79/message/register_finish.php" , number_myid , number_herid , content );
                        Toast.makeText(getApplication(),"回信成功",Toast.LENGTH_SHORT).show();
                        reply_Content.setText(null);
                        finish();
                    }else
                    {
                        Toast.makeText(getApplication(),"回信失敗,請輸入內容",Toast.LENGTH_SHORT).show();
                    }
                    dialog.dismiss();
                }
            });
            real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            real_delete.show();

        }
        return super.onOptionsItemSelected(item);
    }


}
