package com.shot.community.go.facility_manager;

import android.os.AsyncTask;
import android.widget.EditText;
import android.widget.TextView;

import com.shot.community.go.Facility_test.Facility_position;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 2017/11/3.
 */

public class Facility_add_Http extends AsyncTask {

    String line="";
    int flag=0;

    HttpClient httpClient;
    HttpResponse response;
    HttpEntity entity;
    HttpGet httpGet;
    HttpPost httpPost;
    InputStream is;
    JSONArray jArray;
    BufferedReader reader;
    StringBuilder sb;
    EditText Name,Limit;
    TextView Opentime,Closetime,Status,Day;
    List<NameValuePair> HttpAddFacilityparams;

    String[] st = {"不開放" , "開放中"};

    ArrayList<Integer> nameid;
    ArrayList<String> list_name;
    ArrayList<String> list_limit;
    ArrayList<String> list_opentime;
    ArrayList<String> list_closetime;
    ArrayList<String> list_status;
    ArrayList<String> list_day;

    int iid;

    public Facility_add_Http(int flag){

        this.flag = flag;
    }

    public Facility_add_Http(int flag,EditText name,EditText limit)
    {
        this.flag = flag;
        this.Name = name;
        this.Limit = limit;
//        this.Opentime = opentime;
//        this.Closetime = closetime;
//        this.Status = status;
//        this.Day = day;
    }
    public Facility_add_Http(int flag , List<NameValuePair> params){
        this.flag = flag;
        this.HttpAddFacilityparams = params;
    }


    public Object httpmanagerAdd(Object[] params)
    {
        try {
            String optime = params[3].toString();
            String cltime = params[4].toString();
            String Useperson = params[5].toString();
            String status = params[6].toString();
            String Date = params[2].toString();
            String name = params[1].toString();
            String commid = params[7].toString();
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("it_name","UTF-8") + "=" + URLEncoder.encode(name,"UTF-8") + "&" +
                    URLEncoder.encode("it_day","UTF-8") + "=" + URLEncoder.encode(Date,"UTF-8") + "&" +
                    URLEncoder.encode("it_stime","UTF-8")+ "=" + URLEncoder.encode(optime,"UTF-8")+ "&" +
                    URLEncoder.encode("it_etime","UTF-8") + "=" + URLEncoder.encode(cltime,"UTF-8") + "&" +
                    URLEncoder.encode("it_limt","UTF-8") + "=" + URLEncoder.encode(Useperson,"UTF-8")+ "&" +
                    URLEncoder.encode("community_id","UTF-8") + "=" + URLEncoder.encode(commid,"UTF-8")+ "&" +
                    URLEncoder.encode("it_stauts","UTF-8") + "=" + URLEncoder.encode(status,"UTF-8");


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

    public Object httpmanagerupdata(Object[] params)
    {
        try {
            httpClient = new DefaultHttpClient();
            httpGet = new HttpGet(params[0].toString());
            response = httpClient.execute(httpGet);
            entity = response.getEntity();
            is = entity.getContent();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            sb = new StringBuilder("");
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String results = sb.toString();
            nameid = new ArrayList();
            list_name = new ArrayList();
            list_limit = new ArrayList();
            jArray = new JSONArray(results);
            int[] a = new int[25];
            int p = Facility_position.positionn;

            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                int ID = Integer.parseInt(json_data.getString("it_id"));
                a[i] = Facility_position.idnumber[i];
                if (ID == a[p])
                {
                    nameid.add(Integer.parseInt(json_data.getString("it_id")));
                    list_name.add(json_data.getString("it_name"));
                    list_limit.add(json_data.getString("it_limt"));
                    Name.setText(json_data.getString("it_name"));
                    Limit.setText(json_data.getString("it_limt"));

                }
            }


            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    public Object httpmanaagersetUpdata(Object[] params){
        try {
            String limit = params[3].toString();
            String name = params[2].toString();
            String day = params[4].toString();
            String optime = params[5].toString();
            String cltime = params[6].toString();
            String status = params[7].toString();
            int itemid = (int)params[1];


            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("it_id","UTF-8") + "=" + URLEncoder.encode(String.valueOf(itemid),"UTF-8")+ "&" +
                    URLEncoder.encode("it_name","UTF-8") + "=" + URLEncoder.encode(name,"UTF-8")+ "&" +
                    URLEncoder.encode("it_limt","UTF-8") + "=" + URLEncoder.encode(limit,"UTF-8")+ "&" +
                    URLEncoder.encode("it_day","UTF-8") + "=" + URLEncoder.encode(day,"UTF-8")+ "&" +
                    URLEncoder.encode("it_stime","UTF-8") + "=" + URLEncoder.encode(optime,"UTF-8")+ "&" +
                    URLEncoder.encode("it_etime","UTF-8") + "=" + URLEncoder.encode(cltime,"UTF-8")+ "&" +
                    URLEncoder.encode("it_stauts","UTF-8") + "=" + URLEncoder.encode(status,"UTF-8");



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

    public Object httpManagerDelete(Object[] params) {
        try {

            String itemid = params[1].toString();
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("it_id","UTF-8") + "=" + URLEncoder.encode(String.valueOf(itemid),"UTF-8");




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
        try {
            if (flag == 0)
            {
                httpmanagerAdd(params);
            }
            if (flag == 1)
            {
                httpmanagerupdata(params);
            }
            if (flag == 2){
                httpmanaagersetUpdata(params);
            }
            if (flag == 3){
                httpManagerDelete(params);
            }
            return null;
        }catch (Exception e) {
            return e.getMessage();
        }
    }




    @Override
    protected void onPostExecute(Object o) {

    }

}
