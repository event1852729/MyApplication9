package com.shot.community.go.Message_class;

import android.content.DialogInterface;
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

import com.shot.community.go.Package_calss.Package_http;
import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by god on 2017/10/21.
 */

public class Message_sendtomanager extends AppCompatActivity {
    String number_Name;
    EditText send_Content;
    TextView textViewnumber;
    Package_http package_http;
    String number_herid;
    String number_myid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_sendtomanager);
        Toolbar toolbar = (Toolbar)findViewById(R.id.message_sendtomanager_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        textViewnumber = (TextView) findViewById(R.id.message_sendtomanager_number_text);
        send_Content = (EditText) findViewById(R.id.message_sendtomanager_content_content_edit);
        textViewnumber.setText(UserData.comunity_name+"管理員");

//        Intent intent = getIntent();
//        number_Name = intent.getStringExtra("number_Name");
//        number_herid = intent.getStringExtra("number_herName");
//        number_myid = intent.getStringExtra("number_myid");
//        reply_Content = (EditText)findViewById(R.id.message_edittext_content);
//        TextView numberText = (TextView)findViewById(R.id.message_replyname);
//        numberText.setText(number_Name);

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
            final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Message_sendtomanager.this,R.style.MyAlertDialogStyle);
            real_delete.setTitle("確定回信?");
            real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(send_Content.getText().toString().trim().length()>0)
                    {


                        String content = send_Content.getText().toString();
                        Message_http message_http = new Message_http(Message_sendtomanager.this , 15 );
                        message_http.execute("http://140.136.155.79/message/manager_finish.php" , UserData.comunity_id , content ,UserData.id );
                        Toast.makeText(getApplication(),"回信成功",Toast.LENGTH_SHORT).show();
                        send_Content.setText(null);
                        finish();
                    }else
                    {
                        Toast.makeText(getApplication(),"寄信失敗,請輸入內容",Toast.LENGTH_SHORT).show();
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
        if(selectId==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
