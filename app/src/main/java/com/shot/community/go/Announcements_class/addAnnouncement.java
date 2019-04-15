package com.shot.community.go.Announcements_class;

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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.SigninActivity;
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
 * Created by god on 2017/10/13.
 */

public class addAnnouncement extends AppCompatActivity {
    SigninActivity mySignActivity2;
    EditText editText_title;
    EditText editText_content;
    ArrayAdapter arrayAdapter;
    Spinner spinner;
    String[] detail_app_string;
    Button button;
    String filedateurl;
    String selectRecord;
    String selectName;
    ArrayList<String> arrayList = new ArrayList<>();
    int flag=0;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_announcementnew);
        Toolbar toolbar = (Toolbar)findViewById(R.id.add_announcement_toolbar_new);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        editText_title = (EditText)findViewById(R.id.add_announcement_title_newtext);
        editText_content = (EditText)findViewById(R.id.add_announcement_content_newtext);
        spinner = (Spinner)findViewById(R.id.add_announcement_class);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectName = parent.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        button = (Button)findViewById(R.id.add_annoounce_button);

        detail_app_string= new String[]{"社區公告", "社區活動"};
        arrayAdapter = new ArrayAdapter<String>(addAnnouncement.this, R.layout.spinner_text, detail_app_string);
        arrayAdapter.setDropDownViewResource(R.layout.login_spinner_text);
        spinner.setAdapter(arrayAdapter);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {


                    if (ContextCompat.checkSelfPermission(addAnnouncement.this,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(addAnnouncement.this,
                                Manifest.permission.CAMERA)) {


                        } else {
                            ActivityCompat.requestPermissions(addAnnouncement.this,
                                    new String[]{Manifest.permission.CAMERA ,Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE},
                                    10);
                        }
                    }else
                    {
                        FilePickerBuilder.getInstance().setMaxCount(1)
                                .setSelectedFiles(arrayList)
                                .pickFile(addAnnouncement.this);

                    }








                }catch (Exception e)
                {
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
                        .pickFile(addAnnouncement.this);

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
                    flag=1;

                    arrayList = data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_DOCS);
                    if(arrayList.size()>0) {
                        button.setText(arrayList.get(0).substring(arrayList.get(0).lastIndexOf("/") + 1));
                    }else if(arrayList.size()==0)
                    {
                        flag=0;
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
        getMenuInflater().inflate(R.menu.message_reply_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        if(selectId == R.id.message_reply_send)
        {
            if(editText_content.getText().toString().trim().length()>0 && editText_title.getText().toString().trim().length()>0)
            {
//                mySignActivity2 = new SigninActivity(7);
//                mySignActivity2.execute("http://140.136.155.79/news/register_finish.php",  editText_title.getText().toString() ,editText_content.getText().toString() );


                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(addAnnouncement.this,R.style.MyAlertDialogStyle);
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
                        progress = new ProgressDialog(addAnnouncement.this );
                        progress.setProgressDrawable(getDrawable(R.drawable.button_dilog));
                        progress = ProgressDialog.show(addAnnouncement.this,ss1,ss2,false,false);
                        Thread t = new Thread(new Runnable() {
                            @Override
                            public void run() {

                                if(flag==1)
                                {


                                    File f  = new File(arrayList.get(0));

                                    String content_type  = getMimeType(f);


                                    String file_path = f.getAbsolutePath();
                                    OkHttpClient client = new OkHttpClient();
                                    RequestBody file_body = RequestBody.create(MediaType.parse(content_type),f);
                                    RequestBody request_body = new MultipartBody.Builder()
                                            .setType(MultipartBody.FORM)
                                            .addFormDataPart("n_content",editText_content.getText().toString())
                                            .addFormDataPart("n_title",editText_title.getText().toString())
                                            .addFormDataPart("n_type",selectName)
                                            .addFormDataPart("community_id", UserData.comunity_id)
                                            .addFormDataPart("type",content_type)
                                            .addFormDataPart("uploaded_file",file_path.substring(file_path.lastIndexOf("/")+1), file_body)
                                            .build();

                                    Request request = new Request.Builder()
                                            .url("http://140.136.155.79/news/register_test.php")
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
                                }else if(flag==0)
                                {

                                    OkHttpClient client = new OkHttpClient();
                                    RequestBody request_body = new MultipartBody.Builder()
                                            .setType(MultipartBody.FORM)
                                            .addFormDataPart("n_content",editText_content.getText().toString())
                                            .addFormDataPart("n_title",editText_title.getText().toString())
                                            .addFormDataPart("community_id", UserData.comunity_id)
                                            .addFormDataPart("n_type",selectName)
                                            .build();

                                    Request request = new Request.Builder()
                                            .url("http://140.136.155.79/news/register_test.php")
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






                            }
                        });

                        t.start();
                        Toast.makeText(getApplication(),"新增成功",Toast.LENGTH_SHORT).show();
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



            }else
            {
                Toast.makeText(getApplication(),"新增請輸入內容",Toast.LENGTH_SHORT).show();
            }
        }
        if(selectId==android.R.id.home)
        {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
