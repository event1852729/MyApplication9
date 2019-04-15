package com.shot.community.go.Package_calss;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.shot.community.go.R;

/**
 * Created by god on 2017/10/16.
 */

public class Package_UPdata_package extends AppCompatActivity {
    Button updata_package_Button_;
    TextView package_Name;
    TextView package_Id;
    EditText package_status;
    TextView package_date;
    ImageView pacakge_image;
    TextView package_Catch_toDelete;
    String package_Catch_toDeleteString;
    ArrayAdapter arrayAdapter;
    Spinner spinner;
    String[] detail_app_string;
    int selectName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updata_package_content);
        Toolbar toolbar = (Toolbar)findViewById(R.id.upcpackage_item_contentToolbat);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        package_Catch_toDeleteString = intent.getStringExtra("package_Catch_toDelete");
        String Spackage_Name = intent.getStringExtra("package_Name");
        String Spackage_Id = intent.getStringExtra("package_Id");
        String Spackage_status = intent.getStringExtra("package_status");
        String Spackage_date = intent.getStringExtra("package_date");
        String Spackage_image = intent.getStringExtra("package_image");

        pacakge_image = (ImageView) findViewById(R.id.updata_package_imageview) ;
        pacakge_image.setScaleType(ImageView.ScaleType.FIT_CENTER);
        package_Catch_toDelete = (TextView)findViewById(R.id.holder_Catch_TO_delete);
        updata_package_Button_ = (Button)findViewById(R.id.updata_package_Button_);
        package_Name =(TextView) findViewById(R.id.upcpackage_recycle_item_numberName_text_Edit);
        package_Id =(TextView) findViewById(R.id.upcpackage_recycle_item_numberID_text_text);
        package_date =(TextView) findViewById(R.id.upcpackage_recycle_item_Time_text_text);
        spinner = (Spinner)findViewById(R.id.update_package_spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if("代領取".equals(parent.getSelectedItem().toString())){
                    selectName = 1;

                }
                if("代退貨".equals(parent.getSelectedItem().toString())){
                    selectName = 3;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        detail_app_string= new String[]{"代領取", "代退貨"};
        arrayAdapter = new ArrayAdapter<String>(Package_UPdata_package.this, R.layout.spinner_text, detail_app_string);
        arrayAdapter.setDropDownViewResource(R.layout.login_spinner_text);
        spinner.setAdapter(arrayAdapter);


        Glide.with(Package_UPdata_package.this)
                .load(Spackage_image)
                .centerCrop()
                .into(pacakge_image);

        package_Name.setText(Spackage_Name);
        package_Id.setText(Spackage_Id);
        package_date.setText(Spackage_date);

        updata_package_Button_.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Package_UPdata_package.this,R.style.MyAlertDialogStyle);
                real_delete.setTitle("確定修改?");
                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplication(),"修改成功",Toast.LENGTH_SHORT).show();
                        Package_http signinActivity = new Package_http(3);
                        signinActivity.execute("http://140.136.155.79/package/update_finish.php" , package_Catch_toDeleteString , selectName );
                        package_Name.setText(null);
                        finish();
                        dialog.dismiss();

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
        getMenuInflater().inflate(R.menu.update_package_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        switch (selectId){
            case android.R.id.home:
                finish();
                return true;
            case R.id.update_package_menu:
                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Package_UPdata_package.this,R.style.MyAlertDialogStyle);
                real_delete.setTitle("確定修改?");
                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplication(),"修改成功",Toast.LENGTH_SHORT).show();
                        Package_http signinActivity = new Package_http(3);
                        signinActivity.execute("http://140.136.155.79/package/update_finish.php" , package_Catch_toDeleteString , selectName );
                        package_Name.setText(null);
                        finish();
                        dialog.dismiss();

                    }
                });
                real_delete.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                real_delete.show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
