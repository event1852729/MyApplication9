package com.shot.community.go.Dicuss_class;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.SigninActivity;

/**
 * Created by god on 2017/10/13.
 */

public class Upadte_dicuss extends AppCompatActivity {
    SigninActivity mySignActivity2;
    EditText editText_title;
    EditText editText_content;
    String bd_id;
    TextView textViewTop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.update_dicuss);
        Toolbar toolbar = (Toolbar)findViewById(R.id.update_dicuss_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText_title = (EditText)findViewById(R.id.update_dicuss_title);
        editText_content = (EditText)findViewById(R.id.update_dicuss_content);
        textViewTop = (TextView)findViewById(R.id.update_dicuss_numbername);

        Intent intent = getIntent();
        String numbername = intent.getStringExtra("number_name");
        String title = intent.getStringExtra("title");
        String content = intent.getStringExtra("content");
        bd_id = intent.getStringExtra("bd_id");
        editText_title.setText(title);
        editText_content.setText(content);
        textViewTop.setText(numbername);









    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_dicuss_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        switch (selectId){
            case android.R.id.home:
                finish();
                return true;
            case R.id.add_dicuss_send:
                if(editText_content.getText().toString().trim().length()>0 && editText_title.getText().toString().trim().length()>0)
                {
                    Dicuss_http dicuss_http = new Dicuss_http(4);
                    dicuss_http.execute("http://140.136.155.79/board/update_finish.php" , bd_id , editText_title.getText().toString() , editText_content.getText().toString());
                    Toast.makeText(getApplication(),"修改成功",Toast.LENGTH_SHORT).show();
                    editText_title.setText(null);
                    editText_content.setText(null);
                    finish();
                }else
                {
                    Toast.makeText(getApplication(),"修改失敗",Toast.LENGTH_SHORT).show();
                }

        }
        return super.onOptionsItemSelected(item);
    }
}
