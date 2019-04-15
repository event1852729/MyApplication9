package com.shot.community.go.facility_manager;

import android.content.Intent;
import android.os.Bundle;
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

import com.shot.community.go.Facility_test.Facility_position;
import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

/**
 * Created by user on 2017/11/4.
 */

public class Facility_updata extends AppCompatActivity {

//    TextView up_day,up_opentime,up_close_time,up_status;
    EditText name,limit;
    Button button;
    String N,L;
    Spinner day,opentime,closetime,status;
    Facility_add_Http facilityAddHttp;

    String getday,getopentime,getclosetime,getstatus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facility_manager_updata);

        day = (Spinner)findViewById(R.id.spinner_day);
        opentime = (Spinner)findViewById(R.id.spinner_opentime);
        closetime = (Spinner)findViewById(R.id.spinner_closetime);
        status = (Spinner)findViewById(R.id.spinner_status);

        name = (EditText)findViewById(R.id.editText);
        limit = (EditText)findViewById(R.id.person);
        button = (Button)findViewById(R.id.button);

        Toolbar toolbar = (Toolbar)findViewById(R.id.message_record_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String Name = intent.getStringExtra("name");
        String Limit = intent.getStringExtra("limit");
        String dayid = intent.getStringExtra("day");
        String st = intent.getStringExtra("St");
        String et = intent.getStringExtra("Et");
        String Status = intent.getStringExtra("status");
        String fid = intent.getStringExtra("fid");

        int S = Integer.parseInt(st);
        int E = Integer.parseInt(et);
        int d = Integer.parseInt(dayid);
        int statusidfffff = Integer.parseInt(Status)+1;
        final int Fid = Integer.parseInt(fid);
        name.setText(Name);
        limit.setText(Limit);



        ArrayAdapter<CharSequence> dateList = ArrayAdapter.createFromResource(Facility_updata.this,
                R.array.date,
                R.layout.library_spinner);
        day.setAdapter(dateList);
        ArrayAdapter<CharSequence> timeList = ArrayAdapter.createFromResource(Facility_updata.this,
                R.array.time,
                R.layout.library_spinner);
        opentime.setAdapter(timeList);
        closetime.setAdapter(timeList);

        ArrayAdapter<CharSequence> statusList = ArrayAdapter.createFromResource(Facility_updata.this,
                R.array.status,
                R.layout.library_spinner);
        status.setAdapter(statusList);
        day.setSelection(d);
        opentime.setSelection(S);
        closetime.setSelection(E);
        status.setSelection(statusidfffff);
        String commid = UserData.comunity_id;

        facilityAddHttp = new Facility_add_Http(1,name,limit);
        facilityAddHttp.execute("http://140.136.155.79/item/content.php",commid);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                N = name.getText().toString();
                L = limit.getText().toString();
                getday = day.getSelectedItem().toString();
                getopentime = opentime.getSelectedItem().toString();
                getclosetime = closetime.getSelectedItem().toString();
                getstatus = status.getSelectedItem().toString();

                switch (getday)
                {
                    case "星期一":
                        getday = "1";
                        break;
                    case "星期二":
                        getday = "2";
                        break;
                    case "星期三":
                        getday = "3";
                        break;
                    case "星期四":
                        getday = "4";
                        break;
                    case "星期五":
                        getday = "5";
                        break;
                    case "星期六":
                        getday = "6";
                        break;
                    case "星期日":
                        getday = "7";
                    case "無公休":
                        getday = "8";
                        break;

                }

                switch (getstatus){
                    case "不開放":
                        getstatus ="0";
                        break;
                    case "開放中":
                        getstatus = "1";
                        break;

                }
                    int id = Facility_position.fid.get(Facility_position.fip);

                    String commid = UserData.comunity_id;
                    facilityAddHttp = new Facility_add_Http(2);
                    facilityAddHttp.execute("http://140.136.155.79/item/update_finish.php",Fid,N,L,getday,getopentime,getclosetime,getstatus);

                    Toast.makeText(Facility_updata.this,"修改成功",Toast.LENGTH_SHORT).show();

                    finish();
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
