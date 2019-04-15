package com.shot.community.go.Facility_test;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by user on 2017/11/3.
 */

public class Facility_reserve_addhttp extends AsyncTask {

    int flag=0;
    String line="";

    public Facility_reserve_addhttp(int flag){

        this.flag = flag;
    }

    public Object httpTestAdd(Object[] params)
    {
        try {
            String Usetime = params[1].toString();
            String Useperson = params[2].toString();
            String Date = params[4].toString();
            int Itemid = (int) params[3];
            int status = 0;
            String messahe_id = params[5].toString();
            String commid = params[6].toString();
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("re_time","UTF-8")+"="+URLEncoder.encode(Usetime,"UTF-8") + "&" +
                    URLEncoder.encode("re_person","UTF-8") + "=" + URLEncoder.encode(Useperson,"UTF-8") + "&" +
                    URLEncoder.encode("it_id","UTF-8") + "=" + URLEncoder.encode(String.valueOf(Itemid),"UTF-8") + "&" +
                    URLEncoder.encode("re_status","UTF-8")+ "=" + URLEncoder.encode(String.valueOf(status),"UTF-8")+ "&" +
                    URLEncoder.encode("mb_id","UTF-8")+ "=" + URLEncoder.encode(messahe_id,"UTF-8")+ "&" +
                    URLEncoder.encode("community_id","UTF-8")+ "=" + URLEncoder.encode(commid,"UTF-8")+ "&" +
                    URLEncoder.encode("re_date","UTF-8")+ "=" + URLEncoder.encode(Date,"UTF-8");


            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));

            StringBuilder sb1 = new StringBuilder("");
            while ((line = bufferedReader.readLine()) != null) {
                sb1.append(line);
            }
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    @Override
    protected Object doInBackground(Object[] params) {
        if (flag == 0) {httpTestAdd(params);}
        return null;
    }
}
