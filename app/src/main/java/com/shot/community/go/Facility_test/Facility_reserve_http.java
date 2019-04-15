package com.shot.community.go.Facility_test;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

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
import java.util.ArrayList;
import java.util.List;
import java.net.URLEncoder;

/**
 * Created by user on 2017/11/2.
 */

public class Facility_reserve_http extends AsyncTask {

    ArrayList<Integer> nameid;
    ArrayList<String> usetime;
    ArrayList<String> limt;

    TextView nameID,fName;

    Spinner spinnerdate,spinnerpeople;

    private ArrayAdapter<String> listAdapter , maxperson;
    private Context context;
    int flag;
    String line="";
    int y = 0;

    public Facility_reserve_http(Context context, int flag , Spinner spinnerdate,Spinner spinnerpeople){
        this.context=context;
        this.flag=flag;
        this.spinnerdate = spinnerdate;
        this.spinnerpeople = spinnerpeople;
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
            nameid = new ArrayList<>();
            usetime = new ArrayList<>();
            limt = new ArrayList<>();
            int[] a = new int[25];
            int p = Facility_position.positionn;
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                    int ID =Integer.parseInt(json_data.getString("it_id"));
                    a[i] = Facility_position.idnumber[i];

                        if (ID==a[p]) {
                            int Starttime = Integer.parseInt(json_data.getString("it_stime"));
                            int Endtime = Integer.parseInt(json_data.getString("it_etime"));
                            int timeMid = Endtime - Starttime;
                            int maxpeople = Integer.parseInt(json_data.getString("it_limt"));
                            int s = maxpeople;


                            //如果時間為早上10點到隔天10點
                            if (timeMid == 0) {
                                timeMid = 24;
                            }
                            //假設開始時間為20點 結束時間為1點
                            if (timeMid < 0) {
                                Endtime = Endtime + 24;
                                timeMid = Endtime - Starttime;
                            }
                            if (timeMid==24)
                            {
                                for (int j = 1; j <= timeMid; j++) {
                                    usetime.add(j + " : 00");
                                }
                            }
                            else {
                                for (int j = 0; j < timeMid; j++) {
                                    usetime.add(Starttime + " : 00");
                                    if (Starttime==24)
                                    {
                                        Starttime=0;
                                    }
                                    Starttime++;
                                }
                            }

                            for (int k = 0; k < maxpeople; k++) {
                                limt.add(s + " 人");
                                s--;
                            }


                        }

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

    public List<String> getlibrarytime(){
        return usetime;
    }
    public List<String> getLimtpeople(){
        return limt;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (flag==0){
            listAdapter = new ArrayAdapter<String>(context, R.layout.library_spinner, getlibrarytime());
            listAdapter.setDropDownViewResource(R.layout.library_time);
            this.spinnerdate.setAdapter(listAdapter);

            maxperson = new ArrayAdapter<String>(context, R.layout.library_spinner, getLimtpeople());
            maxperson.setDropDownViewResource(R.layout.library_time);
            this.spinnerpeople.setAdapter(maxperson);
        }
    }
}
