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
 * Created by user on 2017/11/2.
 */

public class Facility_test_http extends AsyncTask {
    private List<Integer> statusid;
    private List<Integer> nameid;
    private List<String> status;
    private List<String> name;
    private List<String> time;
    private List<Integer> day;
    private List<Integer> St;
    private List<Integer> Et;

    private List<String> limit;
    private Facility_test_model facility_test_model;
    List<Facility_test_model> facility_test_modelList;
    private RecyclerView recyclerView1;
    private Context context;
    private int flag = 0;
    String line="";

    public Facility_test_http(RecyclerView recyclerView ,Context context, int flag  ){
        this.recyclerView1 = recyclerView;
        this.context=context;
        this.flag=flag;

    }

    public void httpTest(Object[] params)
    {

        try {
            //String messahe_id = params[1].toString();
            URL url = new URL(params[0].toString());
            String commid = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(commid,"UTF-8");

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
            statusid = new ArrayList();
            nameid = new ArrayList();
            status = new ArrayList();
            day = new ArrayList();
            time = new ArrayList();
            name = new ArrayList();
            limit =new ArrayList();
            St = new ArrayList<>();
            Et = new ArrayList<>();

            facility_test_modelList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i <= jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);


                statusid.add(Integer.parseInt(json_data.getString("it_stauts")));
                nameid.add(Integer.parseInt(json_data.getString("it_id")));
                day.add(Integer.parseInt(json_data.getString("it_day")));
                time.add(json_data.getString("it_stime") + " : 00" + " ~ " + json_data.getString("it_etime") + " : 00");
                limit.add(json_data.getString("it_limt"));
                name.add(json_data.getString("it_name"));
                St.add(Integer.valueOf(json_data.getString("it_stime")));
                Et.add(Integer.valueOf(json_data.getString("it_etime")));

//                if(Integer.parseInt(json_data.getString("it_id"))==0){
//                    Facility_position.nameid=1;
//                }
//                else
//                {
//                    Facility_position.nameid = Integer.parseInt(json_data.getString("it_id"));
//                }


                facility_test_model = new Facility_test_model(R.mipmap.gum,nameid.get(i),statusid.get(i),
                        day.get(i),time.get(i), limit.get(i),name.get(i),St.get(i),Et.get(i));

                Facility_position.fid.add(Integer.valueOf(nameid.get(i)));


                Facility_position.kk = nameid.get(i);
                Facility_position.nameid=nameid.get(i);



                facility_test_modelList.add(facility_test_model);


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
        return null;
    }


    @Override
    protected void onPostExecute(Object o) {
        if(flag==0) {
            Facility_test_Adapter facility_test_adapter = new Facility_test_Adapter(this.context, facility_test_modelList);
            recyclerView1.setAdapter(facility_test_adapter);
            recyclerView1.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            recyclerView1.setLayoutManager(gridLayoutManager);
        }
    }
}
