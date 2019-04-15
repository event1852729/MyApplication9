package com.shot.community.go.Detail_calss;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Locale;

import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by god on 2017/11/10.
 */

public class Add_detail_record extends AppCompatActivity {
    Spinner spinner;
    ArrayAdapter listAdapter;
    TextView textView;
    String selectRecord;
    Button button1 , button2;
    String filedateurl;
    EditText editText;
    ArrayList<String> slist ;
    ArrayList<String> arrayList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_detail_recode);
        Toolbar toolbar = (Toolbar)findViewById(R.id.detail_recycle_item_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        spinner = (Spinner)findViewById(R.id.detail_record_spinner);
        textView = (TextView)findViewById(R.id.add_detail_text);
        button1 = (Button)findViewById(R.id.add_record_button) ;
        button2 = (Button)findViewById(R.id.add_record_button2) ;
        editText = (EditText)findViewById(R.id.detail_recycle_date) ;


        String[] record = {"收支紀錄" , "會議記錄"};

        listAdapter = new ArrayAdapter<String>(this, R.layout.spinner_text, record);
        listAdapter.setDropDownViewResource(R.layout.login_spinner_text);
        spinner.setAdapter(listAdapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectRecord = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    if (ContextCompat.checkSelfPermission(Add_detail_record.this,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(Add_detail_record.this,
                                Manifest.permission.CAMERA)) {


                        } else {
                            ActivityCompat.requestPermissions(Add_detail_record.this,
                                    new String[]{Manifest.permission.CAMERA ,Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE},
                                    10);
                        }
                    }else
                    {
                        FilePickerBuilder.getInstance().setMaxCount(1)
                                .setSelectedFiles(arrayList)
                                .pickFile(Add_detail_record.this);

                    }








                }catch (Exception e)
                {

                }


            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(editText.getText().toString().trim().length()>0 && textView.getText().toString().trim().length()>0)
                {
//                    progress = new ProgressDialog(Add_detail_record.this);
//                    progress.setTitle("Uploading");
//                    progress.setMessage("Please wait...");
//                    progress.show();


                    final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Add_detail_record.this,R.style.MyAlertDialogStyle);
                    real_delete.setTitle("確定新增?");
                    real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {





                            String s= "處理中";
                            String title="請稍後";
                            SpannableString ss1=  new SpannableString(title);
                            ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, ss1.length(), 0);
                            SpannableString ss2=  new SpannableString(s);
                            ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, ss2.length(), 0);
                            progress = new ProgressDialog(Add_detail_record.this );
                            progress.setProgressDrawable(getDrawable(R.drawable.button_dilog));
                            progress = ProgressDialog.show(Add_detail_record.this,ss1,ss2,false,false);




                            Thread t = new Thread(new Runnable() {
                                @Override
                                public void run() {

                                    File f  = new File(arrayList.get(0));
                                    String content_type  = getMimeType(f);



                                    String file_path = f.getAbsolutePath();

                                    OkHttpClient client = new OkHttpClient();
                                    RequestBody file_body = RequestBody.create(MediaType.parse(content_type),f);

                                    RequestBody request_body = new MultipartBody.Builder()
                                            .setType(MultipartBody.FORM)
                                            .addFormDataPart("record_title",selectRecord)
                                            .addFormDataPart("record_content",editText.getText().toString())
                                            .addFormDataPart("type",content_type)
                                            .addFormDataPart("community_id", UserData.comunity_id)
                                            .addFormDataPart("uploaded_file",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
                                            .build();

                                    Request request = new Request.Builder()
                                            .url("http://140.136.155.79/record/register_finish.php")
                                            .post(request_body)
                                            .build();

                                    try {
                                        Response response = client.newCall(request).execute();

                                        if(!response.isSuccessful()){
                                            throw new IOException("Error : "+response);
                                        }

                                        progress.dismiss();
                                        finish();

                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }


                                }
                            });

                            t.start();

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


                } else {

                Toast.makeText(Add_detail_record.this , "請輸入標題或選擇檔案" ,Toast.LENGTH_LONG).show();
            }



            }
        });

    }


    ProgressDialog progress;



    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {



        // If request is cancelled, the result arrays are empty.
        if(requestCode==10)
        {

            if ( grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                FilePickerBuilder.getInstance().setMaxCount(1)
                        .setSelectedFiles(arrayList)
                        .pickFile(Add_detail_record.this);

            } else {

                // permission denied, boo! Disable the
                // functionality that depends on this permission.
            }
        }else  {


        }
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case FilePickerConst.REQUEST_CODE_DOC:
                if(resultCode==RESULT_OK && data!=null)
                {

                    arrayList = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS);
                    if(arrayList.size()>0) {
                        textView.setText("檔案 : " + selectRecord + ".." + arrayList.get(0).substring(arrayList.get(0).lastIndexOf("/") + 1));
                        textView.setVisibility(View.VISIBLE);
                    }
                }
        }
    }


    private static String getSuffix(File file) {
        if (file == null || !file.exists() || file.isDirectory()) {
            return null;
        }
        String fileName = file.getName();
        if (fileName.equals("") || fileName.endsWith(".")) {
            return null;
        }
        int index = fileName.lastIndexOf(".");
        if (index != -1) {
            return fileName.substring(index + 1).toLowerCase(Locale.US);
        } else {
            return null;
        }
    }

    public static String getMimeType(File file){
        String suffix = getSuffix(file);
        if (suffix == null) {
            return "file/*";
        }
        String type = MimeTypeMap.getSingleton().getMimeTypeFromExtension(suffix);
        if (type != null || !type.isEmpty()) {
            return type;
        }
        return "file/*";
    }


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

