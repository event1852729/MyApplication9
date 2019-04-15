package com.shot.community.go;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.Creat_comunity.Creat_comunity_http;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by user on 2017/12/3.
 */

public class Add_guard  extends AppCompatActivity {

    EditText name,phone,adress,email;
    Button send;
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.guard);

        name = (EditText)findViewById(R.id.et_name);
        phone = (EditText)findViewById(R.id.et_cellphone);
        adress = (EditText)findViewById(R.id.et_address);
        email = (EditText)findViewById(R.id.et_email);
        textView = (TextView)findViewById(R.id.tv_newaccount);

        send = (Button)findViewById(R.id.creat_com_button);

        Intent intent = getIntent();
        final String id = intent.getStringExtra("id");
        if (id.equals("1")){
            textView.setText("加入警衛");
            send.setText("新增警衛");
        }
        else if (id.equals("2")) {
            textView.setText("加入物業管理員");
            send.setText("新增物業管理員");
        }

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.equals("1"))
                {
                    Creat_comunity_http creat_comunity_http = new Creat_comunity_http(8);
                    creat_comunity_http.execute("http://140.136.155.79/member/register_guard_finish.php"
                            ,name.getText().toString(),phone.getText().toString() ,adress.getText().toString(),
                            email.getText().toString(),UserData.comunity_id,randomString(20),2);

                    Toast.makeText(Add_guard.this,"新增成功",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else if (id.equals("2"))
                {
                    Creat_comunity_http creat_comunity_http = new Creat_comunity_http(8);
                    creat_comunity_http.execute("http://140.136.155.79/member/register_guard_finish.php"
                            ,name.getText().toString(),phone.getText().toString() ,adress.getText().toString(),
                            email.getText().toString(),UserData.comunity_id,randomString(20),3);
                    Toast.makeText(Add_guard.this,"新增成功",Toast.LENGTH_SHORT).show();
                    finish();
                }

            }
        });

    }

    @NonNull
    public static String randomString(int len) {
        String str = "0123456789abcdefghijklmnopqrstuvwxyz";
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < len; i++) {
            int idx = (int)(Math.random() * str.length());
            sb.append(str.charAt(idx));
        }
        return sb.toString();
    }
}
