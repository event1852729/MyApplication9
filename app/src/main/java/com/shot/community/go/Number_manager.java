package com.shot.community.go;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.shot.community.go.Package_calss.Package_http;
import com.shot.community.go.Package_calss.Select_number;
import com.shot.community.go.http_meth.SigninActivity;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by god on 2017/12/10.
 */

public class Number_manager extends AppCompatActivity {
    RecyclerView recyclerView;
    private DividerItemDecoration mDivider;
    Package_http package_http;
    public  static EditText editText;
    Button button1 , button2 ;
    public static String numid="0";
    private static final String SP_NAME = "recent_history";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_number_dio2);
        Toolbar toolbar = (Toolbar)findViewById(R.id.add_announcement_toolbar_new);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        recyclerView = (RecyclerView)findViewById(R.id.search_package_recycle_dio);
        recyclerView.setHasFixedSize(true);
        mDivider = new DividerItemDecoration(this,DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(mDivider);
        Select_number.whoUsethisClassFlag=4;
        package_http = new Package_http(recyclerView,this , 11  );
        package_http.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);
        button1 = (Button)findViewById(R.id.add_record_button);
        button2 = (Button)findViewById(R.id.add_record_button2) ;

        editText = (EditText)findViewById(R.id.serach_package_dio) ;
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.toString().equals(""))
                {
                    package_http = new Package_http(recyclerView,Number_manager.this , 11  );
                    package_http.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);
                }else {
                    package_http.serach_number(s.toString().toLowerCase());
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Number_manager.this,R.style.MyAlertDialogStyle);
                real_delete.setTitle("確定交接給此住戶?");
                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!numid.equals("0"))
                        {
                            SigninActivity signinActivity = new SigninActivity(41);
                            signinActivity.execute("http://140.136.155.79/member/member_manager.php" , numid , UserData.comunity_id);
                            clearHistoryInSharedPreferences();
                            editText.setText(null);
                            numid = "0";

                        }else {
                            Toast.makeText(Number_manager.this , "交接失敗" , Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
//                            ((Activity)context).finish();
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
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Number_manager.this,R.style.MyAlertDialogStyle);
                real_delete.setTitle("確定刪除此住戶?");
                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(!numid.equals("0"))
                        {
                            SigninActivity signinActivity = new SigninActivity(40);
                            signinActivity.execute("http://140.136.155.79/member/delete_finish.php" , numid);
                            editText.setText(null);
                            numid = "0";
                        }else {
                            Toast.makeText(Number_manager.this , "刪除失敗" , Toast.LENGTH_SHORT).show();
                        }
                        dialog.dismiss();
//                            ((Activity)context).finish();
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
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.announcement_cotent, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        switch (selectId){
            case android.R.id.home:
                numid = "0";
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void clearHistoryInSharedPreferences() {
        SharedPreferences sp = getSharedPreferences(SP_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        editor.apply();
    }

}
