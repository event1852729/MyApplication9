package com.shot.community.go.Fix;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

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
 * Created by user on 2017/11/6.
 */

public class Fix_http extends AsyncTask {
    int flag;
    TextView time;
    EditText place,detail;
    private Fix_model fix_model;
    List<Fix_model> fixModelList;
    private Fix_model1 fix_model1;
    List<Fix_model1> fixModelList1;
    private RecyclerView recyclerView;
    private Context context;
    private List<String> Detail;
    private List<String> Place;
    private List<String> Id;
    private List<String> Time;
    private List<Integer> Status;
    private List<Integer> fixid;
    private List<String> list_package_image;

    private List<String> Detail1;
    private List<String> Place1;
    private List<String> Id1;
    private List<String> Time1;
    private List<Integer> Status1;
    private List<Integer> fixid1;
    private List<String> Make;
    private List<String> list_package_image1;
    private List<String> fix_name;
    private List<String> fix_phone;

    TextView Place_text;
    TextView Detail_text;
    TextView Id_text;
    TextView Fixid_text;
    ImageView imageView;

    String line="";

    public Fix_http (int flag){
        this.flag = flag;
    }

    public Fix_http(RecyclerView recyclerView ,Context context, int flag  ){
        this.recyclerView = recyclerView;
        this.context=context;
        this.flag=flag;

    }

    public Fix_http(int flag,TextView Id,TextView Fixid){
        this.flag = flag;
        this.Id_text = Id;
        this.Fixid_text = Fixid;
    }



    public Object httpfixAdd(Object[] params)
    {
        try {
            String Time = params[2].toString();
            String Place = params[3].toString();
            String Detail = params[4].toString();
            int id = (int) params[1];
            int s = (int)params[5];
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(id),"UTF-8") + "&" +
                    URLEncoder.encode("fix_find_time","UTF-8") + "=" + URLEncoder.encode(Time,"UTF-8") + "&" +
                    URLEncoder.encode("fix_place","UTF-8") + "=" + URLEncoder.encode(Place,"UTF-8") + "&" +
                    URLEncoder.encode("fix_content","UTF-8")+ "=" + URLEncoder.encode(Detail,"UTF-8")+ "&" +
                    URLEncoder.encode("fix_status_id","UTF-8") + "=" + URLEncoder.encode(String.valueOf(s),"UTF-8");

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

    public void httpFixstatus0(Object[] params)
    {

        try {
            URL url = new URL(params[0].toString());
            String commid = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("fix_status_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(0),"UTF-8")+ "&" +
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
            Detail = new ArrayList();
            Id = new ArrayList();
            Status = new ArrayList();
            Time = new ArrayList();
            Place = new ArrayList();
            fixid = new ArrayList();
            list_package_image = new ArrayList<>();
            fixModelList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                fixid.add(Integer.valueOf(json_data.getString("fix_id")));
                list_package_image.add(json_data.getString("fix_pic"));
                Detail.add(json_data.getString("fix_content"));
                Id.add(json_data.getString("mb_name"));
                Status.add(Integer.parseInt(json_data.getString("fix_status")));
                Time.add(json_data.getString("fix_submit_date"));
                Place.add(json_data.getString("fix_place"));

                Fix_item.idnumber[i] = Integer.valueOf(json_data.getString("fix_id"));



                fix_model = new Fix_model(list_package_image.get(i),Detail.get(i),Place.get(i),
                        Id.get(i),Time.get(i), Status.get(i),fixid.get(i));

                fixModelList.add(fix_model);


            }
            bufferedReader.close();inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
        }

    }


    public void httpFixapplication0(Object[] params)
    {

        try {
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("fix_status_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(0),"UTF-8");
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


            fixModelList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            int[] a = new int[500];
            int p = Fix_item.pos;
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                int fid = Integer.parseInt(json_data.getString("fix_id"));
                a[i] = Fix_item.idnumber[i];
                if (fid == a[p]){
                    Id_text.setText(json_data.getString("mb_name"));
                    Fixid_text.setText("NO." + json_data.getString("fix_id"));
                }

            }
            bufferedReader.close();inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
        }
    }



    public void httpFixstatus1(Object[] params)
    {

        try {
            URL url = new URL(params[0].toString());
            String commid = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("fix_status_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(1),"UTF-8")+ "&" +
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
            Detail1 = new ArrayList();
            Id1 = new ArrayList();
            Status1 = new ArrayList();
            Time1 = new ArrayList();
            Place1 = new ArrayList();
            fixid1 = new ArrayList();
            Make = new ArrayList();
            fix_name = new ArrayList();
            fix_phone = new ArrayList();
            list_package_image1 = new ArrayList<>();

            fixModelList1 = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                fixid1.add(Integer.valueOf(json_data.getString("fix_id")));
                Detail1.add(json_data.getString("fix_content"));
                Id1.add(json_data.getString("mb_name"));
                Status1.add(Integer.parseInt(json_data.getString("fix_status")));
                Time1.add(json_data.getString("fix_submit_date"));
                Place1.add(json_data.getString("fix_place"));
                Make.add(json_data.getString("fix_make_time"));
                list_package_image1.add(json_data.getString("fix_pic"));
                fix_name.add(json_data.getString("fix_name"));
                fix_phone.add(json_data.getString("fix_phone"));



                fix_model1 = new Fix_model1(list_package_image1.get(i),Detail1.get(i),Place1.get(i),
                        Id1.get(i),Time1.get(i), Status1.get(i),fixid1.get(i),Make.get(i),fix_name.get(i),fix_phone.get(i));

                fixModelList1.add(fix_model1);

            }
            bufferedReader.close();inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
        }

    }



    public Object httpfixupdata(Object[] params)
    {
        try {
            String Name = params[2].toString();
            String Phone = params[3].toString();
            int id = (int) params[1];
            int s = (int) params[4];
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("fix_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(id),"UTF-8") + "&" +
                    URLEncoder.encode("fix_status_id","UTF-8") + "=" + URLEncoder.encode(String.valueOf(s),"UTF-8") + "&" +
                    URLEncoder.encode("fix_name","UTF-8") + "=" + URLEncoder.encode(Name,"UTF-8") + "&" +
                    URLEncoder.encode("fix_phone","UTF-8")+ "=" + URLEncoder.encode(Phone,"UTF-8");

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

    public Object httpfixfinish(Object[] params)
    {
        try {
            int id = (int) params[1];
            int s = (int) params[2];
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("fix_id","UTF-8")+"="+URLEncoder.encode(String.valueOf(id),"UTF-8") + "&" +
                    URLEncoder.encode("fix_status_id","UTF-8") + "=" + URLEncoder.encode(String.valueOf(s),"UTF-8");

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
        if (flag==0)
        {
            httpfixAdd(params);
        }
        if (flag==1)
        {
            httpFixstatus0(params);
        }
        if (flag==2){
            httpFixapplication0(params);
        }
        if (flag==3){
            httpFixstatus1(params);
        }
        if (flag==4){
            httpfixupdata(params);
        }
        if (flag==5){
            httpfixfinish(params);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if(flag==1) {

            Fix_Adapter fix_adapter = new Fix_Adapter(this.context, fixModelList);
            recyclerView.setAdapter(fix_adapter);
            recyclerView.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        if (flag==3){
            Fix_Adapter1 fix_adapter1 = new Fix_Adapter1(this.context, fixModelList1);
            recyclerView.setAdapter(fix_adapter1);
            recyclerView.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            recyclerView.setLayoutManager(gridLayoutManager);

        }
    }
}
