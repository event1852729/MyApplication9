package com.shot.community.go.Creat_comunity;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.nostra13.universalimageloader.utils.L;
import com.shot.community.go.Dicuss_class.Dicuss_content_message_item;
import com.shot.community.go.Dicuss_class.Dicuss_item_model;
import com.shot.community.go.ForgetPassword.Forgetpassword_item;

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

public class Creat_comunity_http extends AsyncTask {
    Context context;
    String line;
    int flag=0;
    RecyclerView recyclerView;
    ArrayList<Search_com_item> search_com_itemsList;

    ArrayList<String> community_id;
    ArrayList<String> community_name;
    ArrayList<String> community_manager_name;
    ArrayList<String> community_manager_phone;
    ArrayList<String> community_manager_address;
    ArrayList<String> community_manager_date;
    ArrayList<String> community_number;

    ArrayList<String> community_name1;

    Seracch_recycleAdatpar seracch_recycleAdatpar;
    Apply_com_numbername_item appcom_item;
    ArrayList<Apply_com_numbername_item> applycomlist;
    ArrayList<String> applicant_id;
    ArrayList<String> applicant_name;
    ArrayList<String> applicant_phone;
    ArrayList<String> applicant_address;
    ArrayList<String> applicant_mail;
    ArrayList<String> applicant_date;
    ArrayList<String> applicant_community_id;
    ArrayList<Forgetpassword_item> forgetpasswordItemArrayList;
    ArrayList<String> a_name;
    ArrayList<String> a_phone;
    ArrayList<String> a_address;
    ArrayList<String> a_mail;


    public ArrayList<Dicuss_content_message_item> getList_dicuss_message_item_model() {
        return list_dicuss_message_item_model;
    }

    ArrayList<Dicuss_content_message_item> list_dicuss_message_item_model;
    Dicuss_content_message_item dicuss_content_message_item;
    Dicuss_item_model dicuss_item_model;

    public Creat_comunity_http(Context context, int flag , RecyclerView recyclerView ) {
        this.context = context;
        this.flag = flag;
        this.recyclerView = recyclerView;
    }
    public Creat_comunity_http(int flag) {
        this.flag = flag;
    }


    public Creat_comunity_http(Context context , int flag) {
        this.context = context;
        this.flag = flag;
    }

    public void catchStartcode(Object[] params)
    {
        try{
            URL url = new URL(params[0].toString());
            String name = params[1].toString();
            String phone = params[2].toString();
            String address = params[3].toString();
            String email = params[4].toString();
            String comunityname = params[5].toString();
            String startcode = params[6].toString();




            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            String PostData = URLEncoder.encode("community_manager_name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8") + "&" +
                    URLEncoder.encode("community_manager_phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+ "&" +
                    URLEncoder.encode("community_manager_address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+ "&" +
                    URLEncoder.encode("community_manager_mail","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+ "&" +
                    URLEncoder.encode("community_name","UTF-8")+"="+URLEncoder.encode(comunityname,"UTF-8")+ "&" +
                    URLEncoder.encode("community_number","UTF-8")+"="+URLEncoder.encode(startcode,"UTF-8");

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


    public void catchcom(Object[] params)
    {
        try{
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder sb1 = new StringBuilder("");
            while ((line = bufferedReader.readLine()) != null) {
                sb1.append(line);
            }

            String results = sb1.toString();

            community_name = new ArrayList<>();
            community_manager_name = new ArrayList<>();
            community_manager_phone = new ArrayList<>();
            community_manager_address = new ArrayList<>();
            community_id = new ArrayList<>();
            community_manager_date = new ArrayList<>();


            search_com_itemsList = new ArrayList<>();

            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                community_name.add(json_data.getString("community_name"));
                community_manager_name.add(json_data.getString("community_manager_name"));
                community_manager_phone.add(json_data.getString("community_manager_phone"));
                community_manager_address.add(json_data.getString("community_manager_address"));
                community_id.add(json_data.getString("community_id"));
                community_manager_date.add(json_data.getString("community_date"));
                Search_com_item search_com_item = new Search_com_item(community_name.get(i) , community_manager_phone.get(i) , community_manager_address.get(i) , community_manager_name.get(i),community_id.get(i),community_manager_date.get(i));
                search_com_itemsList.add(search_com_item);

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();


        }catch (Exception e){
        }

    }

    public void creatNumber(Object[] params) {
        try{
            URL url = new URL(params[0].toString());
            String community_name1 = params[1].toString();
            String applicant_name1 = params[2].toString();
            String applicant_phone = params[3].toString();
            String applicant_address = params[4].toString();
            String applicant_mail = params[5].toString();

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            String PostData = URLEncoder.encode("community_name","UTF-8")+"="+URLEncoder.encode(community_name1,"UTF-8") + "&" +
                    URLEncoder.encode("applicant_name","UTF-8")+"="+URLEncoder.encode(applicant_name1,"UTF-8")+ "&" +
                    URLEncoder.encode("applicant_phone","UTF-8")+"="+URLEncoder.encode(applicant_phone,"UTF-8")+ "&" +
                    URLEncoder.encode("applicant_address","UTF-8")+"="+URLEncoder.encode(applicant_address,"UTF-8")+ "&" +
                    URLEncoder.encode("applicant_mail","UTF-8")+"="+URLEncoder.encode(applicant_mail,"UTF-8");

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

    public void apply_nopass(Object[] params) {

        try{
            URL url = new URL(params[0].toString());
            String nopass_content = params[1].toString();
            String applicant_email = params[2].toString();
            String id = params[3].toString();

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            String PostData = URLEncoder.encode("msg_content","UTF-8")+"="+URLEncoder.encode(nopass_content,"UTF-8") + "&" +
                    URLEncoder.encode("applicant_id","UTF-8")+"="+URLEncoder.encode(id,"UTF-8") + "&" +
                    URLEncoder.encode("applicant_mail","UTF-8")+"="+URLEncoder.encode(applicant_email,"UTF-8");

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

    public void forgetpassword(Object[] params) {
        try{
            URL url = new URL(params[0].toString());
            String community_name1 = params[1].toString();
            String applicant_name1 = params[2].toString();
            String applicant_phone = params[3].toString();
            String applicant_address = params[4].toString();
            String applicant_mail = params[5].toString();
            String random = params[6].toString();

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            String PostData = URLEncoder.encode("community_name","UTF-8")+"="+URLEncoder.encode(community_name1,"UTF-8") + "&" +
                    URLEncoder.encode("applicant_name","UTF-8")+"="+URLEncoder.encode(applicant_name1,"UTF-8")+ "&" +
                    URLEncoder.encode("applicant_phone","UTF-8")+"="+URLEncoder.encode(applicant_phone,"UTF-8")+ "&" +
                    URLEncoder.encode("applicant_address","UTF-8")+"="+URLEncoder.encode(applicant_address,"UTF-8")+ "&" +
                    URLEncoder.encode("mb_pw","UTF-8")+"="+URLEncoder.encode(random,"UTF-8")+ "&" +
                    URLEncoder.encode("applicant_mail","UTF-8")+"="+URLEncoder.encode(applicant_mail,"UTF-8");



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

    private void check(Object[] params) {

        try{
            URL url = new URL(params[0].toString());
            String com_id = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(com_id,"UTF-8") ;
            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder sb1 = new StringBuilder("");
            while ((line = bufferedReader.readLine()) != null) {
                sb1.append(line);
            }
            String results = sb1.toString();
            a_name = new ArrayList<>();
            a_phone = new ArrayList<>();
            a_address = new ArrayList<>();
            a_mail = new ArrayList<>();
            forgetpasswordItemArrayList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                a_name.add(json_data.getString("real_name").toString());
                a_phone.add(json_data.getString("mb_phone").toString());
                a_address.add(json_data.getString("mb_address").toString());
                a_mail.add(json_data.getString("mb_mail").toString());

                Forgetpassword_item forgetpasswordItem = new Forgetpassword_item(a_name.get(i)
                ,a_phone.get(i),a_address.get(i),a_mail.get(i));

                Forget_check.N.add(a_name.get(i));
                Forget_check.D.add(a_address.get(i));
                Forget_check.M.add(a_mail.get(i));
                Forget_check.P.add(a_phone.get(i));


                forgetpasswordItemArrayList.add(forgetpasswordItem);

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();

        }catch (Exception e){
        }

    }


    public void addguard(Object[] params)
    {
        try{
            URL url = new URL(params[0].toString());
            String name = params[1].toString();
            String phone = params[2].toString();
            String address = params[3].toString();
            String email = params[4].toString();
            String comunityid = params[5].toString();
            String random = params[6].toString();
            int mgid = (int)params[7];




            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            String PostData = URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(comunityid,"UTF-8") + "&" +
                    URLEncoder.encode("real_name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+ "&" +
                    URLEncoder.encode("mb_phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+ "&" +
                    URLEncoder.encode("mb_address","UTF-8")+"="+URLEncoder.encode(address,"UTF-8")+ "&" +
                    URLEncoder.encode("mb_mail","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+ "&" +
                    URLEncoder.encode("mb_manager","UTF-8")+"="+URLEncoder.encode(String.valueOf(mgid),"UTF-8")+ "&" +
                    URLEncoder.encode("mb_pw","UTF-8")+"="+URLEncoder.encode(random,"UTF-8");

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


    public void repeat(Object[] params)
    {
        try{
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder sb1 = new StringBuilder("");
            while ((line = bufferedReader.readLine()) != null) {
                sb1.append(line);
            }

            String results = sb1.toString();

            community_name1 = new ArrayList<>();


            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                community_name1.add(json_data.getString("community_name"));

                Forget_check.C_name.add(community_name1.get(i));
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();


        }catch (Exception e){
        }

    }

    @Override
    protected Object doInBackground(Object[] params) {

        if(flag==0)
        {
            catchStartcode(params);
        }
        if(flag==1)
        {
            catchcom(params);
        }
        if(flag==2)
        {
            creatNumber(params);
        }
        if(flag==3)
        {
            catchApplycom(params);
        }
        if(flag==4)
        {
            apply_pass(params);
        }
        if (flag==5)
        {
            apply_nopass(params);
        }
        if (flag==6)
        {
            forgetpassword(params);
        }
        if (flag==7)
        {
            check(params);
        }
        if (flag==8){
            addguard(params);
        }
        if (flag==9){
            repeat(params);
        }

        return null;

    }

    private void apply_pass(Object[] params) {

        try{
            URL url = new URL(params[0].toString());
            String community_id = params[1].toString();
            String applicant_email = params[2].toString();
            String applicant_address = params[3].toString();
            String mb_pw = params[4].toString();
            String ap_id = params[5].toString();
            String name = params[6].toString();
            String phone = params[7].toString();

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));

            String PostData = URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(community_id,"UTF-8") + "&" +
                    URLEncoder.encode("applicant_mail","UTF-8")+"="+URLEncoder.encode(applicant_email,"UTF-8")+ "&" +
                    URLEncoder.encode("applicant_address","UTF-8")+"="+URLEncoder.encode(applicant_address,"UTF-8")+ "&" +
                    URLEncoder.encode("mb_pw","UTF-8")+"="+URLEncoder.encode(mb_pw,"UTF-8")+ "&" +
                    URLEncoder.encode("applicant_name","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+ "&" +
                    URLEncoder.encode("applicant_phone","UTF-8")+"="+URLEncoder.encode(phone,"UTF-8")+ "&" +
                    URLEncoder.encode("applicant_id","UTF-8")+"="+URLEncoder.encode(ap_id,"UTF-8");

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

    private void catchApplycom(Object[] params) {

        try{
            URL url = new URL(params[0].toString());
            String com_id = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);

            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(com_id,"UTF-8") ;
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();

            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            StringBuilder sb1 = new StringBuilder("");
            while ((line = bufferedReader.readLine()) != null) {
                sb1.append(line);
            }

            applicant_id = new ArrayList<>();
            applicant_name = new ArrayList<>();
            applicant_phone = new ArrayList<>();
            applicant_address = new ArrayList<>();
            applicant_mail = new ArrayList<>();
            applicant_date = new ArrayList<>();
            applicant_community_id = new ArrayList<>();
            String results = sb1.toString();
            applycomlist = new ArrayList<>();

            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                applicant_id.add(json_data.getString("applicant_id"));
                applicant_name.add(json_data.getString("applicant_name"));
                applicant_phone.add(json_data.getString("applicant_phone"));
                applicant_address.add(json_data.getString("applicant_address"));
                applicant_mail.add(json_data.getString("applicant_mail"));
                applicant_date.add(json_data.getString("applicant_date"));
                applicant_community_id.add(json_data.getString("community_id"));
                Apply_com_numbername_item appcom_item = new Apply_com_numbername_item(applicant_name.get(i) , applicant_phone.get(i),applicant_address.get(i),applicant_mail.get(i)
                ,applicant_community_id.get(i),applicant_date.get(i) ,applicant_id.get(i) );
                applycomlist.add(appcom_item);

            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();


        }catch (Exception e){
        }

    }
    public void serach_com(String s)
    {
        ArrayList<Search_com_item> arrayList = new ArrayList<>();
        for (Search_com_item search_com_item : search_com_itemsList)
        {
            String sera_com = search_com_item.getName().toLowerCase();
            if(sera_com.contains(s))
            {
                arrayList.add(search_com_item);
            }
        }
        seracch_recycleAdatpar.setFiter(arrayList);

    }

    @Override
    protected void onPostExecute(Object o) {
        if(flag==1)
        {
            seracch_recycleAdatpar = new Seracch_recycleAdatpar(context , search_com_itemsList);
            recyclerView.setAdapter(seracch_recycleAdatpar);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context , 1);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        if(flag==3)
        {
            for (int i=0;i<applycomlist.size();i++)
            {
               for(int j = 0 ; j < Forget_check.D.size() ; j++)
               {
                   if(Forget_check.D.get(j).equals(applycomlist.get(i).address))
                   {
                       Apply_com_numbername_item apply_com_numbername_item = new Apply_com_numbername_item(
                               applycomlist.get(i).getName() , applycomlist.get(i).getPhone(),applycomlist.get(i).getAddress()+"   (已 有 此 住 戶)",applycomlist.get(i).getEmail()
                               ,applycomlist.get(i).getApplyId(),applycomlist.get(i).getDate() ,applycomlist.get(i).getHolderId()
                       );
                       applycomlist.set( i ,apply_com_numbername_item);
                   }
               }
            }
            Apply_com_recycleAdatpar seracch_recycleAdatpar = new Apply_com_recycleAdatpar(context , applycomlist);
            recyclerView.setAdapter(seracch_recycleAdatpar);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(context , 1);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
    }
}
