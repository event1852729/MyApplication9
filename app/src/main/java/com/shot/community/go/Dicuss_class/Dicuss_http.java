package com.shot.community.go.Dicuss_class;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * Created by god on 2017/10/26.
 */

public class Dicuss_http extends AsyncTask {
    Context context;
    String line;
    int flag=0;
    RecyclerView recyclerView;
    ArrayList<String> list_title;
    ArrayList<String> list_content;
    ArrayList<String> list_mb_name;
    ArrayList<String> list_message_content;
    ArrayList<String> list_message_date;
    ArrayList<String> list_message_conunt;
    ArrayList<String> list_dicuss_id;
    ArrayList<String> list_date;
    ArrayList<String> list_number_id;
    ArrayList<String> list_nessage_numbername;
    ArrayList<Dicuss_item_model> list_dicuss_item_model;
    ArrayList<String> list_lm_id;
    TextView textView;
    static  int flagNobodymessage=0;

    ArrayList<String> report_name;
    ArrayList<String> report_reson;
    ArrayList<String> report_date;
    ArrayList<String> report_detail;
    ArrayList<String> report_title;
    ArrayList<Integer> report_arid;
    ArrayList<Integer> report_status;
    ArrayList<Integer> report_rpid;

    private Dicuss_report_model dicussReportModel;
    List<Dicuss_report_model> dicussReportModelList;

    public ArrayList<Dicuss_content_message_item> getList_dicuss_message_item_model() {
        return list_dicuss_message_item_model;
    }

    ArrayList<Dicuss_content_message_item> list_dicuss_message_item_model;
    Dicuss_content_message_item dicuss_content_message_item;
    Dicuss_item_model dicuss_item_model;

    public Dicuss_http(Context context, int flag ,  RecyclerView recyclerView) {
        this.context = context;
        this.flag = flag;
        this.recyclerView = recyclerView;
    }

    public Dicuss_http(Context context, int flag , RecyclerView recyclerView , TextView textView) {
        this.context = context;
        this.flag = flag;
        this.recyclerView = recyclerView;
        this.textView = textView;
    }
    public Dicuss_http( int flag) {
        this.flag = flag;
    }



    public void DeleteDicussMessage(Object[] params){

        try{
            URL url = new URL(params[0].toString());
            String lm_id = params[1].toString();

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("lm_id","UTF-8")+"="+URLEncoder.encode(lm_id,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){

        }

    }

    public void ReportDicuss(Object[] params){

        try{
            URL url = new URL(params[0].toString());
            String bd_id = params[1].toString();
            String mb_id = params[2].toString();
            String rp_content = params[3].toString();
            String commid = params[4].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("bd_id","UTF-8")+"="+URLEncoder.encode(bd_id,"UTF-8") + "&" +
                    URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(mb_id,"UTF-8") + "&" +
                    URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(commid,"UTF-8") + "&" +
                    URLEncoder.encode("rp_content","UTF-8")+"="+URLEncoder.encode(rp_content,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){
        }

    }



    public void ReplyDicuss(Object[] params){

        try{
            URL url = new URL(params[0].toString());
            String bd_id = params[1].toString();
            String mb_id = params[2].toString();
            String lm_content = params[3].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("bd_id","UTF-8")+"="+URLEncoder.encode(bd_id,"UTF-8") + "&" +
                    URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(mb_id,"UTF-8") + "&" +
                    URLEncoder.encode("lm_content","UTF-8")+"="+URLEncoder.encode(lm_content,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){

        }

    }




    public void UpdateDicuss(Object[] params){

        try{
            URL url = new URL(params[0].toString());
            String bb_id = params[1].toString();
            String dicuss_title = params[2].toString();
            String dicuss_content = params[3].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("bd_id","UTF-8")+"="+URLEncoder.encode(bb_id,"UTF-8") + "&" +
                    URLEncoder.encode("bd_title","UTF-8")+"="+URLEncoder.encode(dicuss_title,"UTF-8") + "&" +
                    URLEncoder.encode("bd_content","UTF-8")+"="+URLEncoder.encode(dicuss_content,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){

        }

    }



    public void AddDicuss(Object[] params){

        try{
            URL url = new URL(params[0].toString());
            String mb_id = params[1].toString();
            String dicuss_title = params[2].toString();
            String dicuss_content = params[3].toString();
            String com_id = params[4].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(mb_id,"UTF-8") + "&" +
                    URLEncoder.encode("bd_title","UTF-8")+"="+URLEncoder.encode(dicuss_title,"UTF-8") + "&" +
                    URLEncoder.encode("bd_content","UTF-8")+"="+URLEncoder.encode(dicuss_content,"UTF-8")+"&"+
                    URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(com_id,"UTF-8");;
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){

        }

    }


    public void DeleteDicuss(Object[] params){

        try{
            URL url = new URL(params[0].toString());
            String dicuss_id = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();

            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("bd_id","UTF-8")+"="+URLEncoder.encode(dicuss_id,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){

        }

    }






    public void catchDicussDate(Object[] params){

        try{
            URL url = new URL(params[0].toString());
            String com_id = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(com_id,"UTF-8");
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
//            String list_title, String list_content, String list_dicuss_id, String list_date, String list_number_id, String list_number_name
            list_title = new ArrayList<>();
            list_content = new ArrayList<>();
            list_date = new ArrayList<>();
            list_dicuss_id = new ArrayList<>();
            list_number_id = new ArrayList<>();
            list_mb_name = new ArrayList<>();
            list_dicuss_item_model = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i <jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                list_mb_name.add(json_data.getString("mb_name"));
                list_title.add(json_data.getString("bd_title"));
                list_content.add(json_data.getString("bd_content"));
                list_date.add(json_data.getString("bd_date"));
                list_dicuss_id.add(json_data.getString("bd_id"));
                list_number_id.add(json_data.getString("bd_mb_id"));
                dicuss_item_model = new Dicuss_item_model(list_title.get(i) , list_content.get(i) , list_dicuss_id.get(i) , list_date.get(i) , list_number_id.get(i) , list_mb_name.get(i));
                list_dicuss_item_model.add(dicuss_item_model);

            }
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        }catch (Exception e){

        }

    }


    public void catchDicussMessage(Object[] params){

        try{
            URL url = new URL(params[0].toString());
            String dicuss_id = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("bd_id","UTF-8")+"="+URLEncoder.encode(dicuss_id,"UTF-8");
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
            list_lm_id = new ArrayList<>();
            list_message_conunt = new ArrayList<>();
            list_message_content = new ArrayList<>();
            list_message_date = new ArrayList<>();
            list_nessage_numbername = new ArrayList<>();
            list_dicuss_message_item_model = new ArrayList<>();

            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i <jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);

                list_lm_id.add(json_data.getString("lm_id"));
                list_nessage_numbername.add(json_data.getString("mb_name"));
                list_message_conunt.add((i+1)+"");
                list_message_date.add(json_data.getString("lm_time"));
                list_message_content.add(json_data.getString("lm_content"));

                dicuss_content_message_item = new Dicuss_content_message_item(list_message_conunt.get(i) , list_message_date.get(i) , list_message_content.get(i) , list_nessage_numbername.get(i) , list_lm_id.get(i));
                list_dicuss_message_item_model.add(dicuss_content_message_item);
            }

            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        }catch (Exception e){
        }

    }

    public void httpreport(Object[] params){

        try{
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
//            String list_title, String list_content, String list_dicuss_id, String list_date, String list_number_id, String list_number_name
            report_name = new ArrayList<>();
            report_arid = new ArrayList<>();
            report_date = new ArrayList<>();
            report_rpid = new ArrayList<>();
            report_detail = new ArrayList<>();
            report_reson = new ArrayList<>();
            report_title = new ArrayList<>();
            report_status = new ArrayList<>();

            dicussReportModelList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i <jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);

                report_name.add(json_data.getString("mb_name"));
                report_arid.add(Integer.parseInt(json_data.getString("bd_id")));
                report_date.add(json_data.getString("rp_time"));
                report_rpid.add(Integer.parseInt(json_data.getString("rp_id")));
                report_detail.add(json_data.getString("bd_content"));
                report_reson.add(json_data.getString("rp_content"));
                report_title.add(json_data.getString("rp_content"));
                report_status.add(Integer.parseInt(json_data.getString("rp_status")));

                dicussReportModel = new Dicuss_report_model(report_name.get(i),report_arid.get(i),report_status.get(i)
                        ,report_reson.get(i),report_date.get(i),report_rpid.get(i),report_detail.get(i));
                dicussReportModelList.add(dicussReportModel);

            }
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        }catch (Exception e){
        }

    }


    public void httpPass(Object[] params){

        try{
            URL url = new URL(params[0].toString());
            String bd = params[1].toString();
            String rp = params[2].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("bd_id","UTF-8")+"="+URLEncoder.encode(bd,"UTF-8")+"&"+
                    URLEncoder.encode("rp_id","UTF-8")+"="+URLEncoder.encode(rp,"UTF-8");

            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){
        }

    }

    public void httpNonpass(Object[] params){

        try{
            URL url = new URL(params[0].toString());
            String rp = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("rp_id","UTF-8")+"="+URLEncoder.encode(rp,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){

        }

    }

    @Override
    protected Object doInBackground(Object[] params) {
        if(flag==0)
        {
            catchDicussDate(params);
        }
        if (flag==1)
        {
            catchDicussMessage(params);
        }
        if(flag==2)
        {
            DeleteDicuss(params);
        }
        if(flag==3)
        {
            AddDicuss(params);
        }
        if(flag==4)
        {
            UpdateDicuss(params);
        }
        if(flag==5)
        {
            ReplyDicuss(params);
        }
        if(flag==6)
        {
            DeleteDicussMessage(params);
        }
        if(flag==7){
            ReportDicuss(params);
        }
        if (flag==8)
        {
            httpreport(params);
        }
        if (flag==9)
        {
            httpPass(params);
        }
        if (flag==10)
        {
            httpNonpass(params);
        }

        return null;
    }

    @Override
    protected void onPostExecute(Object o) {

            if(flag==0) {
                Dicuss_fragment_recycleadatper dicuss_fragment_recycleadatper = new Dicuss_fragment_recycleadatper(this.context, list_dicuss_item_model);
                recyclerView.setAdapter(dicuss_fragment_recycleadatper);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
                recyclerView.setLayoutManager(gridLayoutManager);
            }
        if(flag==1){
            if(list_dicuss_message_item_model.size()>0)
                textView.setText("全部回應");
            else
                textView.setText("沒有人回應");
            Dicuss_content_message_recycleadatper dicuss_fragment_recycleadatper = new Dicuss_content_message_recycleadatper(this.context, list_dicuss_message_item_model);
            recyclerView.setAdapter(dicuss_fragment_recycleadatper);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        if (flag==3)
        {

        }

        if (flag==8)
        {
            Dicuss_report_adapter dicuss_report_adapter = new Dicuss_report_adapter(this.context, dicussReportModelList);
            recyclerView.setAdapter(dicuss_report_adapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
            recyclerView.setLayoutManager(gridLayoutManager);
        }

    }
}
