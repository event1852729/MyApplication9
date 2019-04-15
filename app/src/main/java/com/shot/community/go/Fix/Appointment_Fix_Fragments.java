package com.shot.community.go.Fix;


import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.shot.community.go.R;
import com.shot.community.go.http_meth.UserData;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class Appointment_Fix_Fragments extends Fragment {


    EditText Place,Detail;
    Calendar c = Calendar.getInstance();
    Button Send,Select;
    String getPlace,getDetail,getTime;
    Fix_http fixHttp;
    ImageView imageView;
    Bitmap bitmap;
    String imagename;
    private File file;
    private Uri fileurl;
    String name_Id;
    String pg_status;
    boolean check = true;
    String ImagePath = "image_path" ;
    String ConvertImage;
    ProgressDialog progressDialog;
    String ServerUploadPath ="http://140.136.155.79/fix/register_finish.php" ;
    String p,d;
    int pic=0;
    int pick_photo=0;
    public Appointment_Fix_Fragments() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_appointment__fix__fragments, container, false);

        Place = (EditText)view.findViewById(R.id.place);
        Detail = (EditText)view.findViewById(R.id.editText2);
        Send = (Button)view.findViewById(R.id.send);
        Select = (Button)view.findViewById(R.id.select);
        imageView = (ImageView)view.findViewById(R.id.picture);
        imageView.setTag(0);
        p = Place.getText().toString().trim();
        d = Detail.getText().toString().trim();



        Send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                p = Place.getText().toString().trim();
                d = Detail.getText().toString().trim();
                if (p.matches("") || pic==0  || d.matches("")){

                    Toast.makeText(getActivity(),"請確認所有資料(照片)均填寫",Toast.LENGTH_SHORT).show();

                }
                else {
                    if (pick_photo==1){
                        ImageUploadToServerFunction();
                        Place.setText(null);
                        Detail.setText(null);
                        imageView.setVisibility(View.INVISIBLE);
                    }
                    else
                    {
                        Toast.makeText(getActivity(),"請選擇照片",Toast.LENGTH_SHORT).show();

                    }

                }



//                fixHttp = new Fix_http(0);
//                fixHttp.execute("http://140.136.155.79/fix/register_finish.php",id,T,getPlace,getDetail,s);
            }
        });

        Select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {

                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                            Manifest.permission.CAMERA)) {


                    } else {
                        ActivityCompat.requestPermissions(getActivity(),
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






            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int RC, int RQC, Intent I) {

        super.onActivityResult(RC, RQC, I);

        if (RC == 1 && RQC == RESULT_OK && I != null && I.getData() != null) {

            Uri uri = I.getData();
            pic=1;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), uri);
                pick_photo=1;
                imageView.setVisibility(View.VISIBLE);
                imageView.setImageBitmap(bitmap);
                imageView.setTag(1);

            } catch (IOException e) {

                e.printStackTrace();
            }
        }
    }


    public  void  getFileUrl()
    {
        imagename = "hellooo.jpg";
        file = new File(Environment.getExternalStorageDirectory() +File.separator + imagename);
        fileurl = Uri.fromFile(file);
    }

    public void ImageUploadToServerFunction(){

        ByteArrayOutputStream byteArrayOutputStreamObject ;

        byteArrayOutputStreamObject = new ByteArrayOutputStream();

        bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStreamObject);

        byte[] byteArrayVar = byteArrayOutputStreamObject.toByteArray();

        ConvertImage = Base64.encodeToString(byteArrayVar, Base64.DEFAULT);
        Toast.makeText(getActivity(),"編碼中",Toast.LENGTH_LONG).show();

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
                progressDialog = new ProgressDialog(getActivity());


                progressDialog = ProgressDialog.show(getActivity(),ss1,ss2,false,false);
            }

            @Override
            protected void onPostExecute(String string1) {

                super.onPostExecute(string1);

                // Dismiss the progress dialog after done uploading.
                progressDialog.dismiss();
                // Printing uploading success message coming from server on android app.
                Toast.makeText(getActivity(),string1,Toast.LENGTH_LONG).show();
                pick_photo=0;
//                getActivity().finish();
            }

            @Override
            protected String doInBackground(Void... params) {
                Appointment_Fix_Fragments.ImageProcessClass imageProcessClass = new Appointment_Fix_Fragments.ImageProcessClass();

                HashMap<String,String> HashMapParams = new HashMap<String,String>();


                HashMapParams.put(ImagePath, ConvertImage);
                HashMapParams.put("mb_id", name_Id);
                HashMapParams.put("pg_status", pg_status);
                getPlace = Place.getText().toString().trim();
                getDetail = Detail.getText().toString().trim();
                final String T = c.get(Calendar.YEAR) + "/" +(c.get(Calendar.MONTH)+1) + "/" +
                        c.get(Calendar.DAY_OF_MONTH) +" " + c.get(Calendar.HOUR) + " : "
                        +c.get(Calendar.MINUTE);

                int id = Integer.valueOf(UserData.id);
                int s = 0;
                String comm_id = UserData.comunity_id;
                try {
                    String PostData = URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(id),"UTF-8") + "&" +
                            URLEncoder.encode("fix_find_time","UTF-8") + "=" + URLEncoder.encode(T,"UTF-8") + "&" +
                            URLEncoder.encode("fix_place","UTF-8") + "=" + URLEncoder.encode(p,"UTF-8") + "&" +
                            URLEncoder.encode("fix_content","UTF-8")+ "=" + URLEncoder.encode(d,"UTF-8")+ "&" +
                            URLEncoder.encode("fix_status_id","UTF-8") + "=" + URLEncoder.encode(String.valueOf(s),"UTF-8")+ "&" +
                            URLEncoder.encode("image_path","UTF-8")+"="+URLEncoder.encode(ConvertImage,"UTF-8")+ "&" +
                            URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(comm_id,"UTF-8");

                    imageProcessClass.ImageHttpRequest(ServerUploadPath, PostData);

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

    public boolean onCreateOptionsMenu(Menu menu) {
        getActivity().getMenuInflater().inflate(R.menu.package_add_send, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int selectId = item.getItemId();
        switch (selectId){
            case android.R.id.home:
                getActivity().finish();
                return true;
            case R.id.add_package_send_send:

        }
        return super.onOptionsItemSelected(item);
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
}

