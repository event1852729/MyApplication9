package com.shot.community.go.Dicuss_class;

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

import com.shot.community.go.R;
import com.shot.community.go.http_meth.SigninActivity;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by god on 2017/10/13.
 */

public class Add_dicuss extends AppCompatActivity {
    SigninActivity mySignActivity2;
    EditText editText_title;
    EditText editText_content;
    TextView textViewTop;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_dicuss);
        Toolbar toolbar = (Toolbar)findViewById(R.id.add_dicuss_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText_title = (EditText)findViewById(R.id.add_dicuss_edittext_title);
        editText_content = (EditText)findViewById(R.id.add_dicuss_content);
        textViewTop = (TextView)findViewById(R.id.add_dicuss_numbername_Text);
        textViewTop.setText(UserData.name);






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

                    final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Add_dicuss.this,R.style.MyAlertDialogStyle);
                    real_delete.setTitle("確定新增?");
                    real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Dicuss_http dicuss_http = new Dicuss_http(3);
                            dicuss_http.execute("http://140.136.155.79/board/register_finish.php" , UserData.id , editText_title.getText().toString() , editText_content.getText().toString() , UserData.comunity_id);
                            Toast.makeText(Add_dicuss.this,"新增成功",Toast.LENGTH_SHORT).show();
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
                    editText_title.setText(null);
                    editText_content.setText(null);
                    Toast.makeText(getApplication(),"新增失敗",Toast.LENGTH_SHORT).show();
                }

        }
        return super.onOptionsItemSelected(item);
    }
}
