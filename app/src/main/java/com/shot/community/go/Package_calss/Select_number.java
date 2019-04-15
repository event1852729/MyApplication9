package com.shot.community.go.Package_calss;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by god on 2017/11/20.
 */

public class Select_number extends AppCompatActivity {
   RecyclerView recyclerView;
    EditText editText;
    Package_http package_http;
    public static  int whoUsethisClassFlag=0;
    private DividerItemDecoration mDivider;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serach_number);
        recyclerView = (RecyclerView) findViewById(R.id.select_number_recycle);
        mDivider = new DividerItemDecoration(Select_number.this,DividerItemDecoration.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.addItemDecoration(mDivider);
        editText = (EditText) findViewById(R.id.serach_number_edittext);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                    if(s.toString().equals(""))
                    {
                        package_http = new Package_http(recyclerView,Select_number.this  , 11  );
                        package_http.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);
                    }else {
                        package_http.serach_number(s.toString().toLowerCase());
                    }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        package_http = new Package_http(recyclerView,Select_number.this  , 11 );
        package_http.execute("http://140.136.155.79/member/sp_content.php" , UserData.comunity_id);

    }
}

