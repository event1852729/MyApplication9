package com.shot.community.go.Facility_test;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

import java.util.Calendar;

/**
 * Created by user on 2017/11/2.
 */

public class Facility_reserve extends AppCompatActivity{

    Button date;
    Button reserve;
    Spinner spinner_date;
    Spinner spinner_people;
    TextView nameid,name;
    Calendar c = Calendar.getInstance();
    String date_count;
    Facility_reserve_http facility_reserve_http;
    String getSpinnerperson,getSpinnerdate;
    Facility_reserve_addhttp facilityReserveAddhttp;

    int Text = 0;

    DatePickerDialog datePickerDialog;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facility_test_reserve);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar_facility);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        date = (Button)findViewById(R.id.button);
        reserve = (Button)findViewById(R.id.button_reserve);
        spinner_date = (Spinner)findViewById(R.id.spinner_time);
        spinner_people = (Spinner)findViewById(R.id.spinner_people);
        nameid = (TextView)findViewById(R.id.nameid);
        name = (TextView)findViewById(R.id.name);



        Intent intent = getIntent();
        String Name = intent.getStringExtra("name");
        String Id = intent.getStringExtra("nameid");

        name.setText("設施名稱 : " + Name);
        nameid.setText("設施編號 : " + Id);
        String commid = UserData.comunity_id;

        facility_reserve_http = new Facility_reserve_http(getApplication(),0, spinner_date ,spinner_people);
        facility_reserve_http.execute("http://140.136.155.79/item/content.php",commid);


        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datePickerDialog = new DatePickerDialog(Facility_reserve.this,AlertDialog.THEME_HOLO_DARK,datePickerListener,c.get(Calendar.YEAR),c.get(Calendar.MONTH),c.get(Calendar.DAY_OF_MONTH)+1);
                datePickerDialog.setTitle("請選擇時間");
                datePickerDialog.show();
            }
        });

        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Text == 1){
                    AlertDialog.Builder real_delete = new AlertDialog.Builder(Facility_reserve.this, R.style.MyAlertDialogStyle3);

                getSpinnerdate = spinner_date.getSelectedItem().toString();
                getSpinnerperson = spinner_people.getSelectedItem().toString();

                real_delete.setTitle("是否確認預約 ? ");
                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getSpinnerdate = spinner_date.getSelectedItem().toString();
                        getSpinnerperson = spinner_people.getSelectedItem().toString();
                        int itemid = Facility_position.positionn;
                        int ID = Facility_position.idnumber[itemid];
                        String number_id = UserData.id;
                        String commid = UserData.comunity_id;
                        facilityReserveAddhttp = new Facility_reserve_addhttp(0);
                        facilityReserveAddhttp.execute("http://140.136.155.79/reserve/register_finish.php", getSpinnerdate, getSpinnerperson, ID, date_count, number_id,commid);
                        dialog.dismiss();
                        Toast.makeText(Facility_reserve.this,"恭喜您預約成功 ! \n\n管理員將會審核您的預約",Toast.LENGTH_LONG).show();
                        finish();
                    }
                });
                real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Toast.makeText(Facility_reserve.this,"您已經取消預約",Toast.LENGTH_LONG).show();
                    }
                });
                real_delete.show();
            }
                 else {
                    Toast.makeText(Facility_reserve.this,"資料有漏寫\n請重新選擇",Toast.LENGTH_LONG).show();
                }
        }
        });
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {

        // when dialog box is closed, below method will be called.
        public void onDateSet(DatePicker view, int Year,
                              int Month, int Day) {
            Month+=1;
            int cmonth = c.get(Calendar.MONTH)+1;

            int aa = Year*10000 + Month*100+Day;
            int aa2 =c.get(Calendar.YEAR)*10000 + cmonth*100+c.get(Calendar.DAY_OF_MONTH)+1;
            int localdate =c.get(Calendar.YEAR)*10000 + cmonth*100+c.get(Calendar.DAY_OF_MONTH);

            if (aa >= aa2){
                date.setText(Year +"/"+ Month +"/"+Day);
                date_count = Year +"/"+ Month +"/"+Day;
                Text=1;
            }
            else if (aa==localdate){
                date.setText("請選擇日期");
                Text=0;
                Toast.makeText(Facility_reserve.this,"今日已超過預約時間",Toast.LENGTH_LONG).show();
            }
            else
            {
                date.setText("請選擇日期");
                Text=0;
                Toast.makeText(Facility_reserve.this,"請勿選擇過往的日期",Toast.LENGTH_LONG).show();
            }

        }
    };



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_notice_menu, menu);
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
