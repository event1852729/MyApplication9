package com.shot.community.go.Package_calss;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

import java.io.File;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;

import android.content.Intent;
import android.os.AsyncTask;
import android.provider.MediaStore;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.io.UnsupportedEncodingException;
import android.util.Base64;


/**
 * Created by god on 2017/10/16.
 */

public class Add_package extends AppCompatActivity {
    EditText add_package_Name;
    Spinner add_package_ID;
    EditText add_pacakge_date;
    EditText add_package_status;
    Package_http mySignActivity2;

   //以下為拍照變數
    Bitmap bitmap;

    private ArrayAdapter<String> listAdapter;
    boolean check = true;

    Button SelectImageGallery, UploadImageServer , takepicture;

    ImageView imageView;

    EditText imageName;
    String ConvertImage;



    ProgressDialog progressDialog ;

    String GetImageNameEditText;

    String ImageName = "image_name" ;

    String ImagePath = "image_path" ;

    String ServerUploadPath ="http://140.136.155.79/package/register_finish.php" ;

    ArrayAdapter arrayAdapter;
    private File file;
    private Uri fileurl;
    String imagename ;
    String name_Id;
    String pg_status;
    int flag1 =0;
    Button select_number;
   static int flag5=0;
    String s5;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_package);
        Toolbar toolbar = (Toolbar)findViewById(R.id.add_package_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        add_package_Name = (EditText) findViewById(R.id.add_package_title);
        select_number = (Button)findViewById(R.id.select_number_button);
        select_number.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Add_package.this, Select_number.class);
                startActivity(intent);
                Select_number.whoUsethisClassFlag=0;
                flag5=0;
                finish();
            }
        });



        if(flag5==1)
        {
            Intent intent = getIntent();
            String s = intent.getStringExtra("name");
            s5 = intent.getStringExtra("id");
            add_package_Name.setText("目前選擇住戶為 : \n\n"+ s);
        }
        String[] package_status = {"代領取"  , "代退貨" };
        add_package_ID = (Spinner)findViewById(R.id.add_package_content);
        arrayAdapter = new ArrayAdapter<String>(Add_package.this, R.layout.spinner_text, package_status);
        arrayAdapter.setDropDownViewResource(R.layout.login_spinner_text);
        add_package_ID.setAdapter(arrayAdapter);
        add_package_ID.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectName;
                selectName = parent.getSelectedItem().toString();
                if(selectName.equals("代領取"))
                {
                    pg_status="1";
                }
                if(selectName.equals("已領取"))
                {
                    pg_status="2";
                }
                if(selectName.equals("代退貨"))
                {
                    pg_status="3";
                }
                if(selectName.equals("已退貨"))
                {
                    pg_status="4";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });








        takepicture = (Button)findViewById(R.id.buttontakepicture);
        imageView = (ImageView)findViewById(R.id.imageView);
        imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imageView.setTag(0);
        SelectImageGallery = (Button)findViewById(R.id.buttonSelect);
        takepicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try{




                    if (ContextCompat.checkSelfPermission(Add_package.this,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(Add_package.this,
                                Manifest.permission.CAMERA)) {


                        } else {
                            ActivityCompat.requestPermissions(Add_package.this,
                                    new String[]{Manifest.permission.CAMERA ,Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE},
                                    10);
                        }
                    }else
                    {

                        Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        getFileUrl();
                        it.putExtra(MediaStore.EXTRA_OUTPUT, fileurl);

                        startActivityForResult(it, 100);


                    }



                }catch (Exception e)
                {
                }



            }
        });
        SelectImageGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                try{




                    if (ContextCompat.checkSelfPermission(Add_package.this,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(Add_package.this,
                                Manifest.permission.CAMERA)) {


                        } else {
                            ActivityCompat.requestPermissions(Add_package.this,
                                    new String[]{Manifest.permission.CAMERA ,Manifest.permission.WRITE_EXTERNAL_STORAGE , Manifest.permission.READ_EXTERNAL_STORAGE},
                                    10);
                        }
                    }else
                    {
                        Intent intent = new Intent();
                        intent.setType("image/*");

                        intent.setAction(Intent.ACTION_GET_CONTENT);
                        startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);


                    }



                }catch (Exception e)
                {
                }





            }
        });



    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {



                    // If request is cancelled, the result arrays are empty.
                    if(requestCode==10)
                    {

                        if ( grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                            Intent it = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                            getFileUrl();
                            it.putExtra(MediaStore.EXTRA_OUTPUT, fileurl);
                            startActivityForResult(it, 100);

                        } else {

                            // permission denied, boo! Disable the
                            // functionality that depends on this permission.
                        }
                    }else  {


                    }
    }





    @Override
    protected void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);
                imageView.setTag(1);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }else if(RC==100 && RQC == RESULT_OK )
        {
            imageView.setTag(1);
            bitmap = BitmapFactory.decodeFile(file.getPath());
            imageView.setImageURI(fileurl);

        }

    }


    public  void  getFileUrl()
    {



        imagename = "hellooo.jpg";

        file = new File(Environment.getExternalStorageDirectory() +File.separator + imagename);
        fileurl = FileProvider.getUriForFile(
                this,
                getPackageName() + ".fileprovider",
                file);




    }



    public void ImageUploadToServerFunction(){

        ByteArrayOutputStream byteArrayOutputStreamObject ;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStreamObject);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);

        class AsyncTaskUploadClass extends AsyncTask<Void,Void,String> {

            @Override
            protected void onPreExecute() {

                super.onPreExecute();
                String s= "處理中";
                String title="請稍後";
                SpannableString ss1=  new SpannableString(title);
                ss1.setSpan(new ForegroundColorSpan(Color.RED), 0, ss1.length(), 0);
                SpannableString ss2=  new SpannableString(s);
                ss2.setSpan(new ForegroundColorSpan(Color.RED), 0, ss2.length(), 0);
                progressDialog = new ProgressDialog(Add_package.this );
                progressDialog.setProgressDrawable(getDrawable(R.drawable.button_dilog));

                progressDialog = ProgressDialog.show(Add_package.this,ss1,ss2,false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();
                // Printing uploading success message coming from server on android app.
                Toast.makeText(Add_package.this,"新增成功",Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            protected String doInBackground(Void... params) {
                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();


                HashMapParams.put(ImagePath, ConvertImage);
                HashMapParams.put("mb_id", name_Id);
                HashMapParams.put("pg_status", pg_status);
                try {
                    String postString = URLEncoder.encode("image_path","UTF-8")+"="+URLEncoder.encode(ConvertImage,"UTF-8")+"&"+
                            URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(name_Id,"UTF-8")+"&"+
                            URLEncoder.encode("pg_status","UTF-8")+"="+URLEncoder.encode(pg_status,"UTF-8")+"&"+
                            URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(UserData.comunity_id,"UTF-8");
                    imageProcessClass.ImageHttpRequest(ServerUploadPath, postString);

                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                String FinalData = "ge";

                return FinalData;
            }
        }
        AsyncTaskUploadClass AsyncTaskUploadClassOBJ = new AsyncTaskUploadClass();
        AsyncTaskUploadClassOBJ.execute();
    }

    public  class ImageProcessClass{

        public String ImageHttpRequest(String requestURL, String PData) {

            StringBuilder stringBuilder = new StringBuilder();

            try {

                URL url;
                HttpURLConnection httpURLConnectionObject ;
                OutputStream OutPutStream;
                BufferedWriter bufferedWriterObject ;
                BufferedReader bufferedReaderObject ;
                int RC ;
                url = new URL(requestURL);

                httpURLConnectionObject = (HttpURLConnection) url.openConnection();

                httpURLConnectionObject.setReadTimeout(19000);

                httpURLConnectionObject.setConnectTimeout(19000);
                httpURLConnectionObject.setRequestMethod("POST");

                httpURLConnectionObject.setDoInput(true);

                httpURLConnectionObject.setDoOutput(true);

                OutPutStream = httpURLConnectionObject.getOutputStream();

                bufferedWriterObject = new BufferedWriter(

                        new OutputStreamWriter(OutPutStream, "UTF-8"));


                bufferedWriterObject.write(PData);

                bufferedWriterObject.flush();

                bufferedWriterObject.close();

                OutPutStream.close();

                RC = httpURLConnectionObject.getResponseCode();

                if (RC == HttpsURLConnection.HTTP_OK) {

                    bufferedReaderObject = new BufferedReader(new InputStreamReader(httpURLConnectionObject.getInputStream()));

                    stringBuilder = new StringBuilder();

                    String RC2;

                    while ((RC2 = bufferedReaderObject.readLine()) != null){

                        stringBuilder.append(RC2);
                    }
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        private String bufferedWriterDataFN(HashMap<String, String> HashMapParams) throws UnsupportedEncodingException {

            StringBuilder stringBuilderObject;

            stringBuilderObject = new StringBuilder();


            for (Map.Entry<String, String> KEY : HashMapParams.entrySet()) {


                if (check)

                    check = false;
                else
                    stringBuilderObject.append("&");

                stringBuilderObject.append(URLEncoder.encode(KEY.getKey(), "UTF-8"));

                stringBuilderObject.append("=");

                stringBuilderObject.append(URLEncoder.encode(KEY.getValue(), "UTF-8"));
            }

            return stringBuilderObject.toString();
        }

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        flag5=1;
    }

    @Override
    protected void onPause() {
        super.onPause();
        flag5=0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_pacakage_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        switch (selectId){
            case android.R.id.home:
                flag5=0;
                finish();
                return true;
            case R.id.add_pacakage_send:
                final  AlertDialog.Builder real_delete = new AlertDialog.Builder(Add_package.this,R.style.MyAlertDialogStyle);
                real_delete.setTitle("確定新增?");
                real_delete.setPositiveButton("確定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if(add_package_Name.getText().length()>0  &&imageView.getTag().equals(1))
                        {
                            if(flag5==1)
                            {
                                name_Id = s5;

                            }else if(flag5==0){
                                name_Id = add_package_Name.getText().toString();
                            }

                            ImageUploadToServerFunction();
                            //                    mySignActivity2 = new SigninActivity(5 , params);
                            //                    mySignActivity2 = new Package_http(1);
                            //                    mySignActivity2.execute("http://140.136.155.79/package/register_finish.php",  add_package_Name.getText().toString() ,add_package_ID.getText().toString() );
                            add_package_Name.setText(null);
                        }else
                        {
                            Toast.makeText(getApplication(),"新增失敗",Toast.LENGTH_SHORT).show();
                        }
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
                     return true;


        }
        return super.onOptionsItemSelected(item);
    }
}
