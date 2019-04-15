package com.shot.community.go.facility_manager;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by user on 2017/11/3.
 */

public class Facility_add extends AppCompatActivity {
    EditText name,person;
    Spinner date,OpenTime,CloseTime,Status;
    String getDate,getOpentime,getClosetime,getName,getPerson,getStatus;
    Facility_add_Http facilityAddHttp;
    Button button;
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facility_manager_add);
        name = (EditText)findViewById(R.id.editText);
        person = (EditText)findViewById(R.id.person);
        date  = (Spinner)findViewById(R.id.spinner_date);
        OpenTime = (Spinner)findViewById(R.id.spinner_opentime);
        CloseTime = (Spinner)findViewById(R.id.spinner_closetime);
        Status = (Spinner)findViewById(R.id.spinner_status);
        button = (Button)findViewById(R.id.button);

        Toolbar toolbar = (Toolbar)findViewById(R.id.message_record_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ArrayAdapter<CharSequence> timeList = ArrayAdapter.createFromResource(Facility_add.this,
                R.array.time,
                R.layout.library_spinner);

        OpenTime.setAdapter(timeList);
        CloseTime.setAdapter(timeList);

        ArrayAdapter<CharSequence> dateList = ArrayAdapter.createFromResource(Facility_add.this,
                R.array.date,
                R.layout.library_spinner);
        date.setAdapter(dateList);

        ArrayAdapter<CharSequence> statusList = ArrayAdapter.createFromResource(Facility_add.this,
                R.array.status,
                R.layout.library_spinner);
        Status.setAdapter(statusList);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getName = name.getText().toString().trim();
                getPerson = person.getText().toString().trim();
                getDate = date.getSelectedItem().toString();
                getOpentime = OpenTime.getSelectedItem().toString();
                getClosetime = CloseTime.getSelectedItem().toString();
                getStatus = Status.getSelectedItem().toString();

                switch (getDate)
                {
                    case "星期一":
                        getDate = "1";
                        break;
                    case "星期二":
                        getDate = "2";
                        break;
                    case "星期三":
                        getDate = "3";
                        break;
                    case "星期四":
                        getDate = "4";
                        break;
                    case "星期五":
                        getDate = "5";
                        break;
                    case "星期六":
                        getDate = "6";
                        break;
                    case "星期日":
                        getDate = "7";
                    case "無公休":
                        getDate = "8";
                        break;

                }

                switch (getStatus){
                    case "不開放":
                        getStatus ="0";
                        break;
                    case "開放中":
                        getStatus = "1";
                        break;

                }

                int d=date.getSelectedItemPosition();
                int o = OpenTime.getSelectedItemPosition();
                int c = CloseTime.getSelectedItemPosition();
                int s = Status.getSelectedItemPosition();


                if (d==0 || o==0 || c==0 || s==0 || getName.matches("") || getPerson.matches(""))
                {
                    Toast.makeText(Facility_add.this,"資料漏寫\n請重新確認資料是否已填寫",Toast.LENGTH_SHORT).show();
                }
                else {
                    String commid = UserData.comunity_id;
                    facilityAddHttp = new Facility_add_Http(0);
                    facilityAddHttp.execute("http://140.136.155.79/item/register_finish.php",getName,getDate,getOpentime,getClosetime,getPerson,getStatus,commid);
                    Toast.makeText(Facility_add.this,"新增成功",Toast.LENGTH_SHORT).show();

                    finish();

                }
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
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
