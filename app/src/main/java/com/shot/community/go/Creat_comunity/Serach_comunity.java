package com.shot.community.go.Creat_comunity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.shot.community.go.R;

import java.util.ArrayList;

/**
 * Created by god on 2017/11/17.
 */

public class Serach_comunity extends AppCompatActivity {
    RecyclerView recyclerView;
    ArrayList<Search_com_item> search_com_itemsList;
    EditText editText;
    public static int flag_to_search_com = 0 ;
    Creat_comunity_http creat_comunity_http;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.serach_comunity);
        editText = (EditText) findViewById(R.id.et_address);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().equals(""))
                {
                    creat_comunity_http = new Creat_comunity_http(Serach_comunity.this , 1, recyclerView);
                    creat_comunity_http.execute("http://140.136.155.79/community/content.php" );
                }else {
                    creat_comunity_http.serach_com(s.toString().toLowerCase());

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.serach_comunity_RecycleView);
//        search_com_itemsList = new ArrayList<>();
//        Search_com_item search_com_item = new Search_com_item("精華社區" , "09707777" , "至善街33巷" , "event18527"  , "1" , "黃騰輝");
//        search_com_itemsList.add(search_com_item);
//        Search_com_item search_com_item1 = new Search_com_item("精華社區" , "09707777" , "至善街33巷" , "event18527"  , "1" ,"黃騰輝");
//        search_com_itemsList.add(search_com_item1);
//        Search_com_item search_com_item2 = new Search_com_item("精華社區" , "09707777" , "至善街33巷" , "event18527"  , "1","黃騰輝");
//        search_com_itemsList.add(search_com_item2);
        creat_comunity_http = new Creat_comunity_http(Serach_comunity.this , 1, recyclerView);
        creat_comunity_http.execute("http://140.136.155.79/community/content.php" );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.serach_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        switch (selectId){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
