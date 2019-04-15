package com.shot.community.go.Announcements_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.shot.community.go.Announcement_Fragments;
import com.shot.community.go.Announcements_class.Announcements_model.Announcements;
import com.shot.community.go.R;
import com.shot.community.go.http_meth.SigninActivity;

import java.util.ArrayList;

/**
 * Created by user on 2017/5/1.
 */

public class updata extends AppCompatActivity {
    String old_title;
    Spinner spinner;
    ArrayAdapter arrayAdapter;
    ArrayList<Announcements> announcementses;
    EditText Announcement_title ,Announcement_Content;
    Button button;
    String selectName;
    String[] detail_app_string;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_announcement);

        Toolbar toolbar = (Toolbar)findViewById(R.id.add_announcement_toolbar_new);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        button = (Button) findViewById(R.id.add_annoounce_button);
        Announcement_title = (EditText) findViewById(R.id.add_announcement_title_newtext);
        Announcement_Content = (EditText) findViewById(R.id.add_announcement_content_newtext);
        Intent intent = getIntent();
        String announcement_content = intent.getStringExtra("Announcement_title");
        String announcement_content2 = intent.getStringExtra("Announcement_content");
        String announcement_file = intent.getStringExtra("Announcement_file");
        Announcement_title.setText(announcement_content);
        old_title = intent.getStringExtra("an_id");
        Announcement_Content.setText(announcement_content2);
        if(announcement_file.equals(""))
        {
            button.setVisibility(View.INVISIBLE);
        }else {
            button.setText(announcement_file);
        }

        spinner = (Spinner)findViewById(R.id.add_announcement_class);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectName = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        detail_app_string= new String[]{"社區公告", "社區活動"};
        arrayAdapter = new ArrayAdapter<String>(updata.this, R.layout.spinner_text, detail_app_string);
        arrayAdapter.setDropDownViewResource(R.layout.login_spinner_text);
        spinner.setAdapter(arrayAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.message_reply_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        switch (selectId){
            case android.R.id.home:
                finish();
                return true;
            case R.id.message_reply_send:
                if(Announcement_title.getText().toString().trim().length()>0 && Announcement_Content.getText().toString().trim().length()>0)
                {
                    Toast.makeText(getApplication(),"修改成功"+Announcement_Content.getText().toString(),Toast.LENGTH_SHORT).show();
                    SigninActivity signinActivity = new SigninActivity(8);
                    signinActivity.execute("http://140.136.155.79/test01/yoinsert.php" , old_title.toString() , Announcement_title.getText().toString()  ,Announcement_Content.getText().toString() );
                    Announcement_title.setText(null);
                    Announcement_Content.setText(null);
                    Announcement_Fragments.update_recycle_data1=1;
                    finish();
                }else
                {
                    Toast.makeText(getApplication(),"修改失敗",Toast.LENGTH_SHORT).show();

                }
                return true;

        }
        return super.onOptionsItemSelected(item);
    }


}
