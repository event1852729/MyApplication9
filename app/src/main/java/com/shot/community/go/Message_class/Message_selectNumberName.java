package com.shot.community.go.Message_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.SigninActivity;

/**
 * Created by god on 2017/10/21.
 */

public class Message_selectNumberName extends AppCompatActivity {
    SigninActivity signinActivity;
    Spinner spinner;
    Button button;
    String selectName;
    int selectID;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_select_numbername);
        Toolbar toolbar = (Toolbar)findViewById(R.id.message_reply_toolbar);
        setSupportActionBar(toolbar);
        spinner = (Spinner)findViewById(R.id.message_select_calss);

        signinActivity = new SigninActivity(getApplication(),2,spinner);
        signinActivity.execute("http://140.136.155.79/connect1.php");



        button = (Button)findViewById(R.id.message_select_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Message_selectNumberName.this , Message_send.class);
                Intent intent1 = getIntent();
                String number_myid;
                number_myid = intent1.getStringExtra("number_myid");

                for(int x = 0 ; x<signinActivity.getNumber_modelsList().size() ; x++)
                {
                    if(signinActivity.getNumber_modelsList().get(x).getNumber_name().equals(selectName))
                    {
                        selectID=signinActivity.getNumber_modelsList().get(x).getNumber_id();
                    }
                }
                intent.putExtra("number_herName" , selectName);
                intent.putExtra("number_herId" , selectID+"");
                intent.putExtra("number_myid",number_myid);
                Message_send.flag=1;
                startActivity(intent);
                finish();
            }
        });

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectName = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
}
