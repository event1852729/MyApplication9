package com.shot.community.go;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.shot.community.go.http_meth.UserData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by god on 2017/12/3.
 */

public class Add_home_photo extends AppCompatActivity {


    EditText imageName;
    String ConvertImage;

    ProgressDialog progressDialog ;

    String GetImageNameEditText;

    String ImageName = "image_name" ;

    String ImagePath = "image_path" ;

    String ServerUploadPath ="http://140.136.155.79/image/register_finish.php" ;

    int flag=0;

    ImageView imageView;
    Bitmap bitmap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_home_photo);
        Toolbar toolbar = (Toolbar)findViewById(R.id.add_home_photo_toolbar_new);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button selectButton = (Button)findViewById(R.id.add_home_photo_button);
        Button clickButton = (Button)findViewById(R.id.add_home_photo_button2);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0)
                {
                    Toast.makeText(Add_home_photo.this , "請選擇照片" , Toast.LENGTH_LONG).show();
                }else if(flag==1)
                    ImageUploadToServerFunction();
            }
        });
        imageView = (ImageView)findViewById(R.id.add_home_photo);
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try{




                    if (ContextCompat.checkSelfPermission(Add_home_photo.this,
                            Manifest.permission.CAMERA)
                            != PackageManager.PERMISSION_GRANTED) {

                        if (ActivityCompat.shouldShowRequestPermissionRationale(Add_home_photo.this,
                                Manifest.permission.CAMERA)) {


                        } else {
                            ActivityCompat.requestPermissions(Add_home_photo.this,
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
                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);
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
            flag=1;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                imageView.setImageBitmap(bitmap);
                imageView.setTag(1);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }

    public void ImageUploadToServerFunction(){

        ByteArrayOutputStream byteArrayOutputStreamObject ;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 15, byteArrayOutputStreamObject);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
        Toast.makeText(Add_home_photo.this,"編碼中",Toast.LENGTH_LONG).show();

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
                progressDialog = new ProgressDialog(Add_home_photo.this );
                progressDialog.setProgressDrawable(getDrawable(R.drawable.button_dilog));

                progressDialog = ProgressDialog.show(Add_home_photo.this,ss1,ss2,false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();
                // Printing uploading success message coming from server on android app.
                Toast.makeText(Add_home_photo.this,"新增成功",Toast.LENGTH_LONG).show();
                finish();
            }

            @Override
            protected String doInBackground(Void... params) {
                ImageProcessClass imageProcessClass = new ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();


                HashMapParams.put(ImagePath, ConvertImage);

                try {
                    String postString = URLEncoder.encode("image_path","UTF-8")+"="+URLEncoder.encode(ConvertImage,"UTF-8")+"&"+
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

    public class ImageProcessClass{

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
