package com.shot.community.go.Facility_test;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shot.community.go.R;

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
 * Created by user on 2017/10/29.
 */

public class Test_http extends AsyncTask {

    private List<Integer> list_facility_status;
    private List<Integer> list_facility_facility_id;
    private List<Integer> list_facility_numberID;
    private List<String> list_facility_statusToString;
    private List<String> list_facility_Date;
    private List<String> list_facility_Time;
    private List<String> list_facility_finishtime;
    private List<String> list_facility_memberid;
    private List<String> list_people;
    private List<String> list_id;
    private Test_model test_model;
    List<Test_model> test_model_modelList;
    private RecyclerView recyclerView1;
    private Context context;
    private int flag = 0;
    String line="";

    public Test_http(RecyclerView recyclerView ,Context context, int flag  ){
        this.recyclerView1 = recyclerView;
        this.context=context;
        this.flag=flag;

    }
    public Test_http(int flag){
        this.flag = flag;
    }

    public void httpTest(Object[] params)
    {

        try {
            URL url = new URL(params[0].toString());
            String commid = params[1].toString();
            int s = 0;
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("re_status","UTF-8")+"="+URLEncoder.encode(String.valueOf(s),"UTF-8")+"&"+
                    URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(commid,"UTF-8");
            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));



            StringBuilder sb1 = new StringBuilder("");
            while ((line = bufferedReader.readLine()) != null) {
                sb1.append(line);
            }
            String results = sb1.toString();
            list_facility_status = new ArrayList();
            list_facility_numberID = new ArrayList();
            list_facility_statusToString = new ArrayList();
            list_facility_Date = new ArrayList();
            list_facility_Time = new ArrayList();
            list_facility_facility_id = new ArrayList();
            list_facility_memberid =new ArrayList();
            test_model_modelList = new ArrayList<>();
            list_facility_finishtime = new ArrayList();
            list_id = new ArrayList<>();
            list_facility_statusToString = new ArrayList<>();
            list_people = new ArrayList();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);

                list_facility_status.add(Integer.parseInt(json_data.getString("re_status")));
                list_facility_numberID.add(Integer.parseInt(json_data.getString("re_id")));
                list_facility_Date.add(json_data.getString("re_date"));
                list_facility_Time.add(json_data.getString("re_time"));
                list_facility_facility_id.add(Integer.parseInt(json_data.getString("it_id")));
                list_facility_memberid.add(json_data.getString("mb_name"));
                list_facility_finishtime.add(json_data.getString("re_ap"));
                list_facility_statusToString.add(json_data.getString("it_name"));
                list_people.add(json_data.getString("re_person"));
                list_id.add(json_data.getString("mb_id"));
                test_model = new Test_model(R.drawable.ggyy,list_facility_status.get(i),list_facility_numberID.get(i),
                            list_facility_facility_id.get(i),list_facility_memberid.get(i),
                            list_facility_Time.get(i),list_facility_Date.get(i),list_facility_finishtime.get(i),list_people.get(i),list_facility_statusToString.get(i),list_id.get(i));


                test_model_modelList.add(test_model);


            }
            bufferedReader.close();inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
        }

    }

    public void httpFixstatus0(Object[] params)
    {

        try {
            URL url = new URL(params[0].toString());
            String g = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("re_id","UTF-8")+"="+URLEncoder.encode(g,"UTF-8")+ "&" +
                    URLEncoder.encode("re_status","UTF-8")+"="+URLEncoder.encode(String.valueOf(1),"UTF-8");
            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));



            StringBuilder sb1 = new StringBuilder("");
            while ((line = bufferedReader.readLine()) != null) {
                sb1.append(line);
            }
            bufferedReader.close();inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {

        }

    }

    @Override
    protected Object doInBackground(Object[] params) {
        if (flag==0){
            httpTest(params);
        }
        if (flag==1){
            httpFixstatus0(params);
        }
        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        if(flag==0) {
            TestAdapter testAdapter = new TestAdapter(this.context, test_model_modelList);
            recyclerView1.setAdapter(testAdapter);
            recyclerView1.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            recyclerView1.setLayoutManager(gridLayoutManager);
        }
    }
}
