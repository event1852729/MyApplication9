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
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.Package_calss.Select_number;
import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by god on 2017/10/21.
 */

public class Message_send extends AppCompatActivity {
    Button selectnumbetButton;
    EditText editText;
    TextView textView;
    String selectName;
    public static int flag=0;
    Intent intent3;
    String s5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_send);
        Toolbar toolbar = (Toolbar)findViewById(R.id.message_send_toolbar);
        setSupportActionBar(toolbar);
        selectnumbetButton = (Button) findViewById(R.id.message_sendButton);
        textView = (TextView)findViewById(R.id.message_sendname);
        editText = (EditText)findViewById(R.id.message_sendedittext_content);

        if(flag==1)
        {
            intent3 = getIntent();
            selectName = intent3.getStringExtra("name");
            s5 = intent3.getStringExtra("id");
            textView.setText("目前收信人為 : \n\n"+selectName);
        }


        selectnumbetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Message_send.this, Select_number.class);
                Select_number.whoUsethisClassFlag=1;
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.message_send_menu, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        flag=0;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        if(selectId == R.id.message_send_send)
        {
            final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Message_send.this,R.style.MyAlertDialogStyle);
            real_delete.setTitle("確定寄信?");
            real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    if(editText.getText().toString().trim().length()>0)
                    {
                        if(flag == 1)
                        {
                            String content = editText.getText().toString();
                            Message_http message_http = new Message_http(Message_send.this , 3 );
                            message_http.execute("http://140.136.155.79/message/register_finish.php" , UserData.id , s5, content );
                        }
                        Toast.makeText(getApplication(),"寫信成功",Toast.LENGTH_SHORT).show();
                        finish();
                    }else
                    {
                        Toast.makeText(getApplication(),"寫信失敗",Toast.LENGTH_SHORT).show();
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
