package com.shot.community.go.Dicuss_class;

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
import com.shot.community.go.http_meth.SigninActivity;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by god on 2017/10/13.
 */

public class Report_dicuss extends AppCompatActivity {
    SigninActivity mySignActivity2;
    TextView textViewtitle;
    EditText editText_content;
    String bd_id;
    String title;
    TextView textViewTop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_dicuss);
        Toolbar toolbar = (Toolbar)findViewById(R.id.report_dicuss_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textViewtitle = (TextView)findViewById(R.id.Report_dicuss_edittext_title1);
        editText_content = (EditText)findViewById(R.id.Report_dicuss_content);
        textViewTop = (TextView)findViewById(R.id.Report_dicuss_numbername_Text);


        textViewTop.setText("自己"+ "(" + UserData.name + ")");
        Intent intent = getIntent();
        bd_id = intent.getStringExtra("bd_id");
        title = intent.getStringExtra("title");
        textViewtitle.setText(title);


//        Intent intent = getIntent();
//        String numbername = intent.getStringExtra("number_name");
//        String title = intent.getStringExtra("title");
//        String content = intent.getStringExtra("content");
//        bd_id = intent.getStringExtra("bd_id");
//        textViewtitle.setText(title);
//        editText_content.setText(content);
//        textViewTop.setText(numbername);
//








    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.reply_dicuss_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        switch (selectId){
            case android.R.id.home:
                finish();
                return true;
            case R.id.reply_dicuss_send:
                if(editText_content.getText().toString().trim().length()>0)
                {



                                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Report_dicuss.this,R.style.MyAlertDialogStyle);
                                real_delete.setTitle("確定檢舉?");
                                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        String commid = UserData.comunity_id;

                                        Dicuss_http dicuss_http = new Dicuss_http(7);
                                        dicuss_http.execute("http://140.136.155.79/report/register_finish.php" , bd_id , UserData.id , editText_content.getText().toString(),commid);
                                        Toast.makeText(Report_dicuss.this,"檢舉成功",Toast.LENGTH_SHORT).show();
                                        dialog.dismiss();
                                        finish();
                                    }
                                });

                                real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                                real_delete.show();



                }else
                {
                    editText_content.setText(null);
                    Toast.makeText(getApplication(),"檢舉失敗",Toast.LENGTH_SHORT).show();
                }

        }
        return super.onOptionsItemSelected(item);
    }
}
