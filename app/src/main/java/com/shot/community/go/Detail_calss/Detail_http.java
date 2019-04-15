package com.shot.community.go.Detail_calss;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.shot.community.go.Dicuss_class.Dicuss_content_message_item;
import com.shot.community.go.Dicuss_class.Dicuss_item_model;
import com.shot.community.go.http_meth.UserData;

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

/**
 * Created by god on 2017/10/26.
 */

public class Detail_http extends AsyncTask {
    Context context;
    String line;
    int flag=0;
    RecyclerView recyclerView;
    ArrayList<String> record_date;
    ArrayList<String> record_urlname;
    ArrayList<String> record_id;
    ArrayList<String> record_title;
    ArrayList<String> record_contentfile;
    ArrayList<Detail_record_item_model> detail_record_item_modelslist;
    Detail_record_item_model detail_record_item_model;

    ArrayList<String> re_id;
    ArrayList<String> re_status;
    ArrayList<String> it_name;
    ArrayList<String> re_time;
    ArrayList<String> re_date;
    ArrayList<String> re_ap;
    ArrayList<String> re_person;
    ArrayList<String> mb_name;
    ArrayList<String> re_ft;

    ArrayList<Detail_app_item_model> app_item_modelsList;
    Detail_app_item_model detail_app_item_model;

    ArrayList<String> fix_imaglist;
    ArrayList<String> fix_hava_sign;
    ArrayList<String> fix_id;
    ArrayList<String> fix_status;
    ArrayList<String> fixmb_id;
    ArrayList<String> fix_content;
    ArrayList<String> fix_submit_date;
    ArrayList<String> fix_make_time;
    ArrayList<String> fix_finished_date;
    ArrayList<String> fix_place;
    ArrayList<String> fix_pic;
    ArrayList<String> fix_name;
    ArrayList<String> fix_phone;
    ArrayList<String> fix_sign;
    ArrayList<String>fix_mb_name;
    Detail_fixrecord_item_model detail_fixrecord_item_model;
    ArrayList<Detail_fixrecord_item_model> detail_fixrecord_item_modelslist;
    static  int flagNobodymessage=0;

    public ArrayList<Dicuss_content_message_item> getList_dicuss_message_item_model() {
        return list_dicuss_message_item_model;
    }

    ArrayList<Dicuss_content_message_item> list_dicuss_message_item_model;
    Dicuss_content_message_item dicuss_content_message_item;
    Dicuss_item_model dicuss_item_model;


    public Detail_http(Context context, int flag ,     RecyclerView recyclerView ) {
        this.context = context;
        this.flag = flag;
        this.recyclerView = recyclerView;
    }
    public Detail_http(int flag) {
        this.flag = flag;
    }


    public void catchfixData(Object[] params) {

        try {
            URL url = new URL(params[0].toString());
            String mb_id = params[1].toString();
            String mb_manager = params[2].toString();
            String fix_com_id = params[3].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(mb_id,"UTF-8")+"&"+
                    URLEncoder.encode("mb_manager","UTF-8")+"="+URLEncoder.encode(mb_manager,"UTF-8")+"&"+
                    URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(fix_com_id,"UTF-8");
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
            fix_phone = new ArrayList<>();
            fix_id = new ArrayList<>();
            fix_status= new ArrayList<>() ;
            fixmb_id= new ArrayList<>();
            fix_content= new ArrayList<>();
            fix_submit_date= new ArrayList<>();
            fix_make_time= new ArrayList<>();
            fix_finished_date= new ArrayList<>();
            fix_place= new ArrayList<>();
            fix_pic= new ArrayList<>();
            fix_name= new ArrayList<>();
            fix_sign= new ArrayList<>();
            fix_mb_name= new ArrayList<>();


           fix_hava_sign = new ArrayList<>();


            detail_fixrecord_item_modelslist= new ArrayList<>();

            String results = sb1.toString();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                fix_imaglist = new ArrayList<>();
                fix_hava_sign.add(json_data.getString("have_sign"));
                fix_id.add(json_data.getString("fix_id"));
                fix_status.add(json_data.getString("fix_status"));
                fixmb_id.add(json_data.getString("mb_id"));
                fix_content.add(json_data.getString("fix_content"));
                fix_submit_date.add(json_data.getString("fix_submit_date"));
                fix_place.add(json_data.getString("fix_place"));
                fix_pic.add(json_data.getString("fix_pic"));
                fix_name.add(json_data.getString("fix_name"));
                fix_phone.add(json_data.getString("fix_phone"));
                fix_sign.add(json_data.getString("fix_sign"));
                fix_mb_name.add(json_data.getString("mb_name"));

                if(fix_hava_sign.get(i).equals("1"))
                {
                    try {
                        fix_imaglist.add(fix_sign.get(i));
                        fix_imaglist.add(fix_pic.get(i));
                    }catch (Exception e)
                    {
                    }
                }else {
                    fix_imaglist.add(fix_pic.get(i));
                }


                if (fix_status.get(i).equals("0")) {
                    fix_finished_date.add("");
                    fix_make_time.add("");
                }else if (fix_status.get(i).equals("1"))
                {
                    fix_finished_date.add("");
                    fix_make_time.add(json_data.getString("fix_make_time"));
                }else if(fix_status.get(i).equals("2"))
                {
                    fix_make_time.add(json_data.getString("fix_make_time"));
                    fix_finished_date.add(json_data.getString("fix_finished_date"));
                }


                detail_fixrecord_item_model = new Detail_fixrecord_item_model(fix_id.get(i), fix_content.get(i), fix_submit_date.get(i),fix_pic.get(i) , fix_place.get(i), fix_mb_name.get(i),fix_status.get(i)
                ,fix_make_time.get(i) , fix_finished_date.get(i) , fix_imaglist);
                detail_fixrecord_item_modelslist.add(detail_fixrecord_item_model);


            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        }catch(Exception e){
        }

    }


    public void catchappData(Object[] params) {

        try {
            URL url = new URL(params[0].toString());
            String mb_id = params[1].toString();
            String mb_manager = params[2].toString();
            String app_com_id = params[3].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(mb_id,"UTF-8")+"&"+
                    URLEncoder.encode("mb_manager","UTF-8")+"="+URLEncoder.encode(mb_manager,"UTF-8")+"&"+
                    URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(app_com_id,"UTF-8");
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
            re_id = new ArrayList<>();
            re_status = new ArrayList<>();
            it_name = new ArrayList<>();
            re_time = new ArrayList<>();
            re_date = new ArrayList<>();
            re_ap = new ArrayList<>();
            re_person = new ArrayList<>();
            mb_name = new ArrayList<>();
            re_ft = new ArrayList<>();
            app_item_modelsList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                re_id.add(json_data.getString("re_id"));
                re_status.add(json_data.getString("re_status"));
                it_name.add(json_data.getString("it_name"));
                re_time.add(json_data.getString("re_time"));
                re_date.add(json_data.getString("re_date"));
                re_ap.add(json_data.getString("re_ap"));
                re_person.add(json_data.getString("re_person"));
                mb_name.add(json_data.getString("mb_name"));
                if(re_status.get(i).equals("0"))
                {
                    re_ft.add("");
                }else if(re_status.get(i).equals("1"))
                {
                    re_ft.add(json_data.getString("re_ft"));
                }
                detail_app_item_model = new Detail_app_item_model(it_name.get(i), re_person.get(i), re_date.get(i) , re_status.get(i), re_time.get(i),re_ap.get(i) , re_ft.get(i) , mb_name.get(i));
                app_item_modelsList.add(detail_app_item_model);


            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        }catch(Exception e){
        }

    }






    public void catchrecordData(Object[] params) {

        try {
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
            record_title = new ArrayList<>();
            record_date = new ArrayList<>();
            record_id = new ArrayList<>();
            record_urlname = new ArrayList<>();
            record_contentfile = new ArrayList<>();
            detail_record_item_modelslist = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                record_title.add(json_data.getString("record_title"));
                record_date.add(json_data.getString("record_date"));
                record_id.add(json_data.getString("record_id"));
                record_urlname.add(json_data.getString("record_content_file"));
                record_contentfile.add(json_data.getString("record_content"));
                {
                    detail_record_item_model = new Detail_record_item_model(record_date.get(i), record_urlname.get(i), record_title.get(i) , record_id.get(i) , record_contentfile.get(i));
                    detail_record_item_modelslist.add(detail_record_item_model);

                }
            }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();

            }catch(Exception e){

            }

        }




    @Override
    protected Object doInBackground(Object[] params) {
        if(flag==0)
        {
            catchrecordData(params);
        }
        if(flag==1)
        {
            catchappData(params);
        }
        if(flag==2)
        {
            catchfixData(params);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if (flag==0){
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 1);
            Detail_fragment_recycleAdatpar detail_fragment_recycleAdatpar = new Detail_fragment_recycleAdatpar (context,detail_record_item_modelslist);
            recyclerView.setAdapter(detail_fragment_recycleAdatpar);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        if(flag==1)
        {
            if(UserData.manger.equals("0") || UserData.manger.equals("1"))
            {
                Detail_app_recycle detail_app_recycle = new Detail_app_recycle(context , app_item_modelsList);
                recyclerView.setAdapter(detail_app_recycle);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context , 1);
                recyclerView.setLayoutManager(gridLayoutManager);
            }else if(UserData.manger.equals("3"))
            {
                Detail_app_managerrecycle detail_app_recycle = new Detail_app_managerrecycle(context , app_item_modelsList);
                recyclerView.setAdapter(detail_app_recycle);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context , 1);
                recyclerView.setLayoutManager(gridLayoutManager);
            }

    }
        if(flag==2)
        {
            if(UserData.manger.equals("0") || UserData.manger.equals("1"))
            {
                Detail_fixrecord_recycle detail_app_recycle = new Detail_fixrecord_recycle(context , detail_fixrecord_item_modelslist);
                recyclerView.setAdapter(detail_app_recycle);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context , 1);
                recyclerView.setLayoutManager(gridLayoutManager);
            }else if(UserData.manger.equals("3"))
            {
                Detail_fixrecord_managerrecycle detail_app_recycle = new Detail_fixrecord_managerrecycle(context , detail_fixrecord_item_modelslist);
                recyclerView.setAdapter(detail_app_recycle);
                GridLayoutManager gridLayoutManager = new GridLayoutManager(context , 1);
                recyclerView.setLayoutManager(gridLayoutManager);
            }
        }


    }
}
