package com.shot.community.go.Package_calss;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.shot.community.go.Package_Fragements;
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
import java.util.List;

/**
 * Created by god on 2017/10/15.
 */

public class Package_http extends AsyncTask {
    private List<String> list_package_finish_date;
    private List<String> list_package_image;
    private List<Integer> list_package_status;
    private List<Integer> list_package_numberID;
    private List<String> list_package_numberName;
    private List<String> list_package_Date;
    private Package_item_model Package_item_model;
    ArrayList<String> list_package_imageAndsign;
    ArrayList<String> list_package_sign;
    ArrayList<String> list_package_have_signimage;
    ArrayList<String> list_package_catchdate;
    List<Package_item_model> package_item_modelsList;

    public ArrayList<Select_number_item_model> getSelectNumberList() {
        return selectNumberList;
    }

    ArrayList<Select_number_item_model> selectNumberList;
    ArrayList<String> selectNumber;
    ArrayList<String> selectid;
    private RecyclerView package_recycle;
    private Context context;
    private int flag = 0;
    int buttonflag=0;
    Select_number_recycleAdapter select_nunber_recycle_Adapter;
    String line="";
    int size=0;


    public Package_http(RecyclerView package_recycle ,Context context, int flag  ){
        this.package_recycle = package_recycle;
        this.context=context;
        this.flag=flag;

    }
    public Package_http(Context context, int flag  ){
        this.package_recycle = package_recycle;
        this.context=context;
        this.flag=flag;

    }
    public Package_http(RecyclerView package_recycle ,Context context, int flag  ,int buttonflag){
        this.package_recycle = package_recycle;
        this.context=context;
        this.flag=flag;
        this.buttonflag=buttonflag;

    }
    public Package_http(RecyclerView package_recycle ,Context context, int flag  ,int buttonflag , int size){
        this.package_recycle = package_recycle;
        this.context=context;
        this.flag=flag;
        this.buttonflag=buttonflag;
        this.size = size;

    }
    public Package_http( int flag  ){
        this.flag=flag;

    }


    public void DeletePackageData(Object[] params)
    {

        try {
            String package_id = params[1].toString();

            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("pg_id","UTF-8")+"="+URLEncoder.encode(package_id,"UTF-8");

            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));

            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        } catch (Exception e) {
        }

    }

    public void UpdataPackageData(Object[] params)
    {

        try {
            String package_id = params[1].toString();
            String package_status = params[2].toString();
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("pg_id","UTF-8")+"="+URLEncoder.encode(package_id,"UTF-8")+"&" +
                    URLEncoder.encode("pg_status","UTF-8")+"="+URLEncoder.encode(package_status,"UTF-8");;
            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));

            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        } catch (Exception e) {
        }

    }




    public void AddPackageData(Object[] params)
    {

        try {
            String package_Name = params[1].toString();
            String package_status = params[2].toString();

            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(package_Name,"UTF-8") +"&" +URLEncoder.encode("pg_status","UTF-8")+"="+URLEncoder.encode(package_status,"UTF-8")  ;
            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));

            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        } catch (Exception e) {
        }

    }

    public void CatchPackageData(Object[] params)
    {

        try {
            String messahe_id = params[1].toString();
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(messahe_id,"UTF-8");
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
            Log.d("geriobr" , "package_content");
            list_package_status = new ArrayList();
            list_package_numberID = new ArrayList();
            list_package_numberName = new ArrayList();
            list_package_Date = new ArrayList();
            list_package_image = new ArrayList<>();
            list_package_have_signimage = new ArrayList<>();
            list_package_sign = new ArrayList<>();
            list_package_finish_date = new ArrayList<>();
            package_item_modelsList = new ArrayList<>();
            list_package_imageAndsign = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                list_package_imageAndsign = new ArrayList<>();
                JSONObject json_data = jArray.getJSONObject(i);
                list_package_sign.add(json_data.getString("pg_sign"));
                list_package_have_signimage.add(json_data.getString("have_sign"));
                list_package_image.add(json_data.getString("pg_pic"));
                list_package_status.add(Integer.parseInt(json_data.getString("pg_status")));
                list_package_numberID.add(Integer.parseInt(json_data.getString("pg_id")));
                list_package_numberName.add(json_data.getString("mb_name"));
                list_package_Date.add(json_data.getString("pg_arrival_date"));

                if(list_package_have_signimage.get(i).equals("1"))
                {
                    try {
                        list_package_finish_date.add(json_data.getString("pg_date"));
                        list_package_imageAndsign.add(list_package_sign.get(i));
                        list_package_imageAndsign.add(list_package_image.get(i));
                    }catch (Exception e)
                    {
                    }
                }else {
                    list_package_finish_date.add("");
                    list_package_imageAndsign.add(list_package_image.get(i));
                }

                Package_item_model = new Package_item_model(list_package_Date.get(i),  list_package_numberID.get(i),  list_package_numberName.get(i),  list_package_status.get(i) ,list_package_image.get(i), list_package_imageAndsign , list_package_finish_date.get(i) );
                if(list_package_status.get(i)==buttonflag)
                {
                    package_item_modelsList.add(Package_item_model);
                }
            }
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        } catch (Exception e) {

        }

    }




    @Override
    protected Object doInBackground(Object[] params) {

        if(flag==0)
        {
            CatchPackageData(params);
        }
        if (flag==1)
        {
            AddPackageData(params);
        }
        if(flag==2)
        {
            DeletePackageData(params);
        }
        if(flag==3)
        {
            UpdataPackageData(params);
        }
        if(flag==11)
        {
            catchNumberData(params);
        }
        if(flag==12)
        {
            catchComPackageData(params);
        }
        if(flag==13)
        {
            catchNumberData(params);
        }
        //純粹比對搜尋住戶
        if(flag==14)
        {
            catchComPackageData(params);
        }
        if(flag==15)
        {
            httppackage_read(params);
        }
        if(flag==16)
        {
            catchComPackageData(params);
        }
        if(flag==17)
        {
            CatchPackageData(params);
        }
        if(flag==18)
        {
            CatchPackageData(params);
        }

        return null;
    }

    private void httppackage_read(Object[] params) {

        try {

            String ann_number_id = params[1].toString();
            String pg_id = params[2].toString();
            URL url = new URL(params[0].toString());

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("pg_id","UTF-8") + "=" + URLEncoder.encode(pg_id,"UTF-8")+"&"+
                    URLEncoder.encode("mb_id","UTF-8") + "=" + URLEncoder.encode(ann_number_id,"UTF-8");
            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));



            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        } catch (Exception e) {

        }

    }

    public List<com.shot.community.go.Package_calss.Package_item_model> getPackage_item_modelsList() {
        return package_item_modelsList;
    }

    private void catchComPackageData(Object[] params) {


        try {
            String com_id = params[1].toString();
            URL url = new URL(params[0].toString());
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
            list_package_finish_date = new ArrayList<>();
            list_package_status = new ArrayList();
            list_package_numberID = new ArrayList();
            list_package_numberName = new ArrayList();
            list_package_Date = new ArrayList();
            list_package_image = new ArrayList<>();
            list_package_have_signimage = new ArrayList<>();
            list_package_sign = new ArrayList<>();
            package_item_modelsList = new ArrayList<>();
            list_package_imageAndsign = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                list_package_imageAndsign = new ArrayList<>();
                JSONObject json_data = jArray.getJSONObject(i);
                list_package_sign.add(json_data.getString("pg_sign"));
                list_package_have_signimage.add(json_data.getString("have_sign"));
                list_package_image.add(json_data.getString("pg_pic"));
                list_package_status.add(Integer.parseInt(json_data.getString("pg_status")));
                list_package_numberID.add(Integer.parseInt(json_data.getString("pg_id")));
                list_package_numberName.add(json_data.getString("mb_name"));
                list_package_Date.add(json_data.getString("pg_arrival_date"));


                if(list_package_have_signimage.get(i).equals("1"))
                {
                    try {
                        list_package_finish_date.add(json_data.getString("pg_date"));
                        list_package_imageAndsign.add(list_package_sign.get(i));
                        list_package_imageAndsign.add(list_package_image.get(i));
                    }catch (Exception e)
                    {
                    }
                }else {
                    list_package_finish_date.add("");
                    list_package_imageAndsign.add(list_package_image.get(i));
                }

                Package_item_model = new Package_item_model(list_package_Date.get(i),  list_package_numberID.get(i),  list_package_numberName.get(i),  list_package_status.get(i) ,list_package_image.get(i), list_package_imageAndsign ,list_package_finish_date.get(i));
                if(list_package_status.get(i)==buttonflag)
                {
                    package_item_modelsList.add(Package_item_model);
                }
            }
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        } catch (Exception e) {

        }

    }

    public void serach_number(String s)
    {
        ArrayList<Select_number_item_model> arrayList = new ArrayList<>();
        for (Select_number_item_model search_com_item : selectNumberList)
        {
            String sera_com = search_com_item.getName().toLowerCase();
            if(sera_com.contains(s))
            {
                arrayList.add(search_com_item);
            }
        }
        select_nunber_recycle_Adapter.setFiter(arrayList);

    }



    private void catchNumberData(Object[] params) {
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

            selectNumber = new ArrayList<>();
            selectid = new ArrayList<>();
            selectNumberList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                selectNumber.add(json_data.getString("mb_name"));
                selectid.add(json_data.getString("mb_id"));
                Select_number_item_model smode = new Select_number_item_model(selectNumber.get(i) , selectid.get(i));
                if(UserData.id.equals(selectid.get(i)))
                {

                }else {
                    selectNumberList.add(smode);
                }
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        }catch (Exception e){

        }
    }

    @Override
    protected void onPostExecute(Object o) {
        if(flag==0) {
            Package_recycleAdapter package_recycleViewAdaptere = new Package_recycleAdapter(this.context, package_item_modelsList);
            package_recycle.setAdapter(package_recycleViewAdaptere);
            package_recycle.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            package_recycle.setLayoutManager(gridLayoutManager);
            Package_Fragements.or_size  = package_item_modelsList.size();
        }
        if(flag==11)
        {
           select_nunber_recycle_Adapter = new Select_number_recycleAdapter(context , selectNumberList);
            package_recycle.setAdapter(select_nunber_recycle_Adapter);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            package_recycle.setLayoutManager(gridLayoutManager);
        }
        if(flag==12)
        {
            Package_recycleAdapter package_recycleViewAdaptere = new Package_recycleAdapter(this.context, package_item_modelsList);
            package_recycle.setAdapter(package_recycleViewAdaptere);
            package_recycle.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            package_recycle.setLayoutManager(gridLayoutManager);
            Package_Fragements.or_size  = package_item_modelsList.size();
        }
        if(flag==16)
        {

            if(package_item_modelsList.size()!=size) {
                Package_Fragements.update_recycle_data1 = 1;
            }

        }
        if(flag==17)
        {

            if(package_item_modelsList.size()!=size) {

                Package_Fragements.update_recycle_data1 = 1;
            }
        }
        if(flag==18) {
            Package_recycleAdapter package_recycleViewAdaptere = new Package_recycleAdapter(this.context, package_item_modelsList);
            package_recycle.setAdapter(package_recycleViewAdaptere);
            package_recycle.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            package_recycle.setLayoutManager(gridLayoutManager);
        }

    }
}
