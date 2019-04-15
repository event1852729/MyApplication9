package com.shot.community.go.http_meth;

/**
 * Created by user on 2017/4/30.
 */
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.shot.community.go.Announcement_Fragments;
import com.shot.community.go.Announcements_class.Announcement_RecycleViewAdaptere;
import com.shot.community.go.Announcements_class.Announcements_model.Announcements;
import com.shot.community.go.AutoPollAdapter2;
import com.shot.community.go.AutoPollRecyclerView;
import com.shot.community.go.HOME;
import com.shot.community.go.R;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
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

public class SigninActivity  extends  AsyncTask{
    static String smb_id;
    static String scommunity_id;
    static String smb_name;
    static String smb_manager;
    static String smb_self;
    public static ArrayList<String> home_photoList;
    private DividerItemDecoration mDivider;
    ArrayList<String> mb_id;
    ProgressDialog progressDialog ;

   public static String[] s;
    public ArrayList<String> getCommunity_id() {
        return community_id;
    }

    ArrayList<String> community_id;

    public ArrayList<String> getCommunity_name() {
        return community_name;
    }

    ArrayList<String> community_name;

    public ArrayList<String> getMb_name() {
        return mb_name;
    }

    public ArrayList<String> getMb_manager() {
        return mb_manager;
    }

    public ArrayList<String> getMb_self() {
        return mb_self;
    }

    ArrayList<String> mb_name;
    ArrayList<String> mb_manager;

    public ArrayList<String> getMb_id() {
        return mb_id;
    }

    ArrayList<String> mb_self;
    AutoPollRecyclerView autoPollRecyclerView;

    SigninActivity mSigninActivity;
    RecyclerView recyclerView;
    private ArrayAdapter<String> listAdapter;
    String logDTag="logDTag";
    HttpClient httpClient;
    HttpPost httpPost;
    ArrayList<NameValuePair> nameValuePairs;
    HttpResponse response;
    HttpEntity entity;
    InputStream is;
    String line="";
    HttpGet httpGet;
    Context context;
    ListView listView; //Fragment 裡listview
    JSONArray jArray;
    ArrayList<String> list_titles;
    ArrayList<String> list_n_id;

    ArrayList<String> list_date;
    ArrayList<String> list_content;
    ArrayList<String> list_announcement_class;
    ArrayList<String> list_announcement_file;
    ArrayList<Integer> list_id;
    ArrayList<String> list_idString;
    ArrayList<String> mb_pw;
    public int getFlag() {
        return flag;
    }

    int flag=0;//判斷使用哪個連線功能
    String AnnouncementtTitleConditionToFindContentAtFlag4;////在updata.java傳editText 並 傳從前一個從公告列表按鈕拿到tilte做判斷
    String pass="未通過";
    String[] user_name_String = {};
    List<String> list_user_name;
    ArrayList<String> list_user_password;
    ArrayList<Integer> list_user_Id;
    ArrayList<String> list_manager_Id;
    ArrayList<number_model> number_modelsList;
    EditText AnnounceUpdatseditText;////在updata.java傳editText 並 傳從前一個從公告列表按鈕拿到tilte做判斷

    public ArrayList<String> getList_manager_Id() {
        return list_manager_Id;
    }

    List<NameValuePair> HttpAddAnnouncementparams;
    BufferedReader reader;
    StringBuilder sb;
    Spinner LoginSpinner;

    String results2;//text
    int size;
    String[] home_new_string;
    ArrayList<Announcements>  announceList = new ArrayList(); //公告內容 每筆

    public SigninActivity(Context context , RecyclerView recyclerView , SigninActivity mySignActivity){
        this.mSigninActivity = mySignActivity;
        this.context = context;
        this.recyclerView = recyclerView;
    }
    public SigninActivity(Context context ,  AutoPollRecyclerView autoPollRecyclerView, SigninActivity mySignActivity , int flag){
        this.mSigninActivity = mySignActivity;
        this.context = context;
        this.autoPollRecyclerView = autoPollRecyclerView;
        this.flag=flag;
    }
//    測試listview
    public SigninActivity(int flag){
        this.flag = flag;
    }

    public SigninActivity(int flag , String[] s){
        this.flag = flag;
        this.home_new_string = s;
    }
    public SigninActivity(int flag , int size){
        this.size = size;
        this.flag = flag;
    }



    public SigninActivity(int flag , List<NameValuePair> params){
        this.flag = flag;
        this.HttpAddAnnouncementparams = params;
    }


    public SigninActivity(int flag , EditText editText , String AnnouncementtTitleConditionToFindContentAtFlag4){
        this.flag = flag;
        this.AnnounceUpdatseditText = editText;
        this.AnnouncementtTitleConditionToFindContentAtFlag4 = AnnouncementtTitleConditionToFindContentAtFlag4;
    }


    public SigninActivity(Context context , int flag ,     ProgressDialog progressDialog){
        this.context = context;
        this.flag = flag;
        this.progressDialog = progressDialog;
    }
    public SigninActivity(Context context , int flag){
        this.context = context;
        this.flag = flag;
    }
    public SigninActivity(Context context , int flag, Spinner spinner){
        this.LoginSpinner = spinner;
        this.context = context;
        this.flag = flag;
    }


    //http公告
    public Object httpConnectAnnoucement4(Object[] params)
    {
        try {
            httpClient = new DefaultHttpClient();
            httpGet = new HttpGet(params[0].toString());
//            httpPost = new HttpPost(params[0].toString());
//            nameValuePairs.add(new BasicNameValuePair("news_title","hello"));
//            nameValuePairs.add(new BasicNameValuePair("news_countent" , "hello"));
//            httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
            response = httpClient.execute(httpGet);
            //公告網路資料
            entity = response.getEntity();
            is = entity.getContent();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            sb = new StringBuilder("");
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String results = sb.toString();
            list_titles = new ArrayList();
            list_date = new ArrayList();
            list_content = new ArrayList();
//                 list_id = new ArrayList();
//            list_idString = new ArrayList<>();
            jArray = new JSONArray(results);


            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                list_titles.add(json_data.getString("n_title"));
                list_date.add(json_data.getString("n_date"));
                list_content.add(json_data.getString("n_content"));
//                    list_id.add(Integer.parseInt(json_data.getString("n_id")));
            }
            for (int i = 0; i <list_date.size(); i++) {

                Announcements announcements = new Announcements(R.mipmap.announcement_picture, list_titles.get(i), list_date.get(i));
                announceList.add(announcements);
            }
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    //http公告
    public Object httpConnectAnnoucement(Object[] params)
    {
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
                list_titles = new ArrayList();
            list_n_id = new ArrayList();
                list_date = new ArrayList();
                list_content = new ArrayList();
            list_announcement_file = new ArrayList<>();
                list_announcement_class = new ArrayList<>();
//                 list_id = new ArrayList();
//            list_idString = new ArrayList<>();
                jArray = new JSONArray(results);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    list_titles.add(json_data.getString("n_title"));
                    list_date.add(json_data.getString("n_date"));
                    list_content.add(json_data.getString("n_content"));
                    list_announcement_class.add(json_data.getString("n_type"));
                    list_announcement_file.add(json_data.getString("n_files"));
                    list_n_id.add(json_data.getString("n_id"));
                }
                for (int i = 0; i <list_date.size(); i++) {

                    Announcements announcements = new Announcements(R.mipmap.announcement_picture, list_titles.get(i), list_date.get(i) , list_announcement_class.get(i) , list_announcement_file.get(i) , list_n_id.get(i));
                    announceList.add(announcements);
                }
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }



    //http 登入
    public Object httpConnectLogin(Object[] params)
    {
        try {
            httpClient = new DefaultHttpClient();
            httpGet = new HttpGet(params[0].toString());
            response = httpClient.execute(httpGet);
            //公告網路資料
            entity = response.getEntity();
            is = entity.getContent();
//
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            sb = new StringBuilder("");
            while ((line = reader.readLine()) != null) {
                sb.append(line);

            }
            results2 = sb.toString();

            list_user_password = new ArrayList<>();
            list_user_name = new ArrayList<>();
            list_user_Id = new ArrayList<>();
            list_manager_Id = new ArrayList<>();
            number_modelsList = new ArrayList<>();
            jArray = new JSONArray(results2);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                list_user_name.add(json_data.getString("mb_name"));
                list_user_Id.add(json_data.getInt("mb_id"));
                list_user_password.add(json_data.getString("mb_pw"));
                list_manager_Id.add(json_data.getString("mb_manager"));
                number_model number_model = new number_model(list_user_Id.get(i), list_user_name.get(i) , list_user_password.get(i) , list_manager_Id.get(i));
                number_modelsList.add(number_model);
            }



            return list_titles;
        } catch (Exception e) {
            return e.getMessage();
        }
    }


    //http 登入
    public Object httpConnectDelete(Object[] params) {
        try {
            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(params[0].toString());
            httpPost.setEntity(new UrlEncodedFormEntity(HttpAddAnnouncementparams, HTTP.UTF_8));
            response = httpClient.execute(httpPost);
            if (flag == 0) {
                entity = response.getEntity();
                is = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder("");
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                String results = sb.toString();
                list_titles = new ArrayList();
                list_date = new ArrayList();
                list_content = new ArrayList();
                JSONArray jArray = new JSONArray(results);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    list_titles.add(json_data.getString("n_title"));
                    list_date.add(json_data.getString("n_date"));
                    list_content.add(json_data.getString("n_content"));
                }
                for (int i = 0; i <= list_date.size(); i++) {
                    Announcements announcements = new Announcements(R.mipmap.announcement_picture, list_titles.get(i), list_date.get(i));
                    announceList.add(announcements);
                }
            }
            return list_titles;
        } catch (Exception e) {

            return e.getMessage();
        }

    }



    //http 登入
    public Object httpConnectAdd(Object[] params) {
        try {

            httpClient = new DefaultHttpClient();
            httpPost = new HttpPost(params[0].toString());

            httpPost.setEntity(new UrlEncodedFormEntity(HttpAddAnnouncementparams, HTTP.UTF_8));

            response = httpClient.execute(httpPost);
            if (flag == 0) {
                entity = response.getEntity();
                is = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder("");
                while ((line = reader.readLine()) != null) {
                    sb.append(line);

                }
                String results = sb.toString();
                list_titles = new ArrayList();
                list_date = new ArrayList();
                list_content = new ArrayList();
                JSONArray jArray = new JSONArray(results);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    list_titles.add(json_data.getString("n_title"));
                    list_date.add(json_data.getString("n_date"));
                    list_content.add(json_data.getString("n_content"));
                }
                for (int i = 0; i <= list_date.size(); i++) {
                    Announcements announcements = new Announcements(R.mipmap.announcement_picture, list_titles.get(i), list_date.get(i));
                    announceList.add(announcements);
                }
            }
            return list_titles;
        } catch (Exception e) {
            return e.getMessage();
        }

    }

    public Object newGetStartCode(Object[] params) {
        try{
            int RC;
            URL url = new URL(params[0].toString());
            String startcode = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_pw","UTF-8")+"="+URLEncoder.encode(startcode,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            StringBuilder sb1 = new StringBuilder("");



            RC = httpURLConnection.getResponseCode();
            if(RC==200)
            {

                mb_id = new ArrayList<>();
                community_id= new ArrayList<>();
                community_name= new ArrayList<>();
                mb_name= new ArrayList<>();
                mb_manager= new ArrayList<>();
                mb_self= new ArrayList<>();

                while ((line = bufferedReader.readLine()) != null) {
                    sb1.append(line);

                }
                String results = sb1.toString();
                JSONArray jArray = new JSONArray(results);
                for (int i = 0; i <jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    mb_id.add(Integer.toString(json_data.getInt("mb_id")));
                    community_id.add(Integer.toString(json_data.getInt("community_id")));
                    mb_name.add(json_data.getString("mb_name"));
                    mb_manager.add(Integer.toString(json_data.getInt("mb_manager")));
                    mb_self.add(json_data.getString("mb_self"));
                    community_name.add(json_data.getString("community_name"));


                }

            }

            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
            return mb_id;
        }catch (Exception e){

        }
        return null;
    }


    //http 登入
    public Object NewhttpConnectAdd(Object[] params) {
        try {

            String AnnouncementTitle = params[1].toString();
            String AnnouncementContent = params[2].toString();
            URL url = new URL(params[0].toString());

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("n_title","UTF-8")+"="+URLEncoder.encode(AnnouncementTitle,"UTF-8") + "&" +
                    URLEncoder.encode("n_content","UTF-8") + "=" + URLEncoder.encode(AnnouncementContent,"UTF-8");
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

            if (flag == 0) {
                entity = response.getEntity();
                is = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder("");
                while ((line = reader.readLine()) != null) {
                    sb.append(line);
                }
                String results = sb.toString();
                list_titles = new ArrayList();
                list_date = new ArrayList();
                list_content = new ArrayList();
                JSONArray jArray = new JSONArray(results);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    list_titles.add(json_data.getString("n_title"));
                    list_date.add(json_data.getString("n_date"));
                    list_content.add(json_data.getString("n_content"));
                }
                for (int i = 0; i <= list_date.size(); i++) {
                    Announcements announcements = new Announcements(R.mipmap.announcement_picture, list_titles.get(i), list_date.get(i));
                    announceList.add(announcements);
                }
            }
            return list_titles;
        } catch (Exception e) {

            return e.getMessage();
        }

    }

    public Object NewhttpConnectupdata(Object[] params) {
        try {

            String OldAnnouncementtitle = params[1].toString();
            String AnnouncementTitle = params[2].toString();
            String AnnouncementContent = params[3].toString();
            URL url = new URL(params[0].toString());

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("n_id","UTF-8") + "=" + URLEncoder.encode(OldAnnouncementtitle,"UTF-8")+ "&"+
                    URLEncoder.encode("n_title","UTF-8")+"="+URLEncoder.encode(AnnouncementTitle,"UTF-8") + "&" +
                    URLEncoder.encode("n_content","UTF-8") + "=" + URLEncoder.encode(AnnouncementContent,"UTF-8");
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

            if (flag == 0) {
                entity = response.getEntity();
                is = entity.getContent();
                BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                StringBuilder sb = new StringBuilder("");
                while ((line = reader.readLine()) != null) {
                    sb.append(line);

                }
                String results = sb.toString();
                list_titles = new ArrayList();
                list_date = new ArrayList();
                list_content = new ArrayList();
                JSONArray jArray = new JSONArray(results);
                for (int i = 0; i < jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    list_titles.add(json_data.getString("n_title"));
                    list_date.add(json_data.getString("n_date"));
                    list_content.add(json_data.getString("n_content"));
                }
                for (int i = 0; i <= list_date.size(); i++) {
                    Announcements announcements = new Announcements(R.mipmap.announcement_picture, list_titles.get(i), list_date.get(i));
                    announceList.add(announcements);
                }
            }
            return list_titles;
        } catch (Exception e) {

            return e.getMessage();
        }

    }


    @Override
    protected Object doInBackground(Object[] params) {
        try {

            if(flag==8)
            {
                NewhttpConnectupdata(params);
            }
            //新增公告
            if (flag==7)
            {
                NewhttpConnectAdd(params);
            }
            if(flag==5)
            {
                httpConnectAdd(params);
            }

            //刪除公告
            if(flag==1)
            {
                httpConnectDelete(params);
            }
            //未實現
            if(flag==3)
            {
            }
            //拿到公告
            if(flag==0) {
                httpConnectAnnoucement(params);
             }
             ////在updata.java傳editText 並 傳從前一個從公告列表按鈕拿到tilte做判斷
            if(flag==4) {
                httpConnectAnnoucement4(params);
            }
             //登入網入資料
            if (flag ==2)
            {
                httpConnectLogin(params);
            }
            if(flag==11)
            {
                newGetStartCode(params);
            }
            if(flag==22)
            {
                httpConnectAnnoucement(params);
            }
            if(flag==33)
            {
                httpNewMail_package_ann(params);
            }
            if(flag==34)
            {
                httphome_photo(params);
            }
            if(flag==35)
            {
                httpann_read(params);
            }
            if(flag==36)
            {
                http_home_select_number_login(params);
            }
            if(flag==37)
            {
                http_home_select_number_login_getPw(params);
            }
            if(flag==38) {
                httpConnectAnnoucement(params);
            }
            if(flag==39)
            {
                httpNewMail_package_ann(params);
            }
            if(flag==40)
            {
                http_delete_mb(params);
            }
            if(flag==41)
            {
                http_change_mb(params);
            }
            return null;
        } catch (Exception e) {

            return e.getMessage();
        }
    }

    private void http_change_mb(Object[] params) {
        try{
            int RC;

            URL url = new URL(params[0].toString());
            String change_id = params[1].toString();
            String change_comid = params[2].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(change_comid,"UTF-8")+"&" +
                    URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(change_id,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            StringBuilder sb1 = new StringBuilder("");







            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){
        }
    }

    private void http_delete_mb(Object[] params) {
        try{
            int RC;

            URL url = new URL(params[0].toString());
            String deleteid = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(deleteid,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            StringBuilder sb1 = new StringBuilder("");







            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){
        }
    }

    private void http_home_select_number_login_getPw(Object[] params) {
        try{
            int RC;

            URL url = new URL(params[0].toString());
            String startcode = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_pw","UTF-8")+"="+URLEncoder.encode(startcode,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            StringBuilder sb1 = new StringBuilder("");



            RC = httpURLConnection.getResponseCode();
            if(RC==200)
            {

                mb_id = new ArrayList<>();
                community_id= new ArrayList<>();
                community_name= new ArrayList<>();
                mb_name= new ArrayList<>();
                mb_manager= new ArrayList<>();
                mb_self= new ArrayList<>();

                while ((line = bufferedReader.readLine()) != null) {
                    sb1.append(line);

                }
                String results = sb1.toString();
                JSONArray jArray = new JSONArray(results);
                for (int i = 0; i <jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    mb_id.add(Integer.toString(json_data.getInt("mb_id")));
                    community_id.add(Integer.toString(json_data.getInt("community_id")));
                    mb_name.add(json_data.getString("mb_name"));
                    mb_manager.add(Integer.toString(json_data.getInt("mb_manager")));
                    mb_self.add(json_data.getString("mb_self"));
                    community_name.add(json_data.getString("community_name"));



                }

            }

            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){
        }
    }

    private void http_home_select_number_login(Object[] params) {
        try{
            int RC;

            URL url = new URL(params[0].toString());
            String namecode = params[1].toString();
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_name","UTF-8")+"="+URLEncoder.encode(namecode,"UTF-8");
            bufferedWriter.write(PostData);

            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "UTF-8"));
            StringBuilder sb1 = new StringBuilder("");



            RC = httpURLConnection.getResponseCode();
            if(RC==200)
            {
                mb_pw = new ArrayList<>();

                mb_id = new ArrayList<>();
                community_id= new ArrayList<>();
                community_name= new ArrayList<>();
                mb_name= new ArrayList<>();
                mb_manager= new ArrayList<>();
                mb_self= new ArrayList<>();

                while ((line = bufferedReader.readLine()) != null) {
                    sb1.append(line);
                }
                String results = sb1.toString();
                JSONArray jArray = new JSONArray(results);
                for (int i = 0; i <jArray.length(); i++) {
                    JSONObject json_data = jArray.getJSONObject(i);
                    mb_id.add(Integer.toString(json_data.getInt("mb_id")));
                    community_id.add(Integer.toString(json_data.getInt("community_id")));
                    mb_pw.add(json_data.getString("mb_pw"));
                    mb_manager.add(Integer.toString(json_data.getInt("mb_manager")));
                    mb_self.add(json_data.getString("mb_self"));
                    community_name.add(json_data.getString("community_name"));


                }

            }

            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();
        }catch (Exception e){
        }

    }

    private void httpann_read(Object[] params) {
        try {

            String ann_number_id = params[1].toString();
            String ann_id = params[2].toString();
            URL url = new URL(params[0].toString());

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("n_id","UTF-8") + "=" + URLEncoder.encode(ann_number_id,"UTF-8")+"&"+
                    URLEncoder.encode("mb_id","UTF-8") + "=" + URLEncoder.encode(ann_id,"UTF-8");
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

    private void httphome_photo(Object[] params) {

        try {

            String photocomid = params[1].toString();
            URL url = new URL(params[0].toString());

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("community_id","UTF-8") + "=" + URLEncoder.encode(photocomid,"UTF-8");
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

            String results = sb1.toString();
            home_photoList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                home_photoList.add(json_data.getString("image_file"));
            }



            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        } catch (Exception e) {

        }
    }

    private void httpNewMail_package_ann(Object[] params) {
        try {

            String newcomid = params[1].toString();
            String newnumberid = params[2].toString();
            String newmanagerid = params[3].toString();
            URL url = new URL(params[0].toString());

            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");

            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("community_id","UTF-8") + "=" + URLEncoder.encode(newcomid,"UTF-8")+ "&"+
                    URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(newnumberid,"UTF-8")+ "&"+
                    URLEncoder.encode("mb_manager","UTF-8")+"="+URLEncoder.encode(newmanagerid,"UTF-8");
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
            s = sb1.toString().split(",");


            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        } catch (Exception e) {

        }
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(flag==11)
        {

        }
    }

    public ArrayList<String> getArryListAnnouncementContent()
    {
        return  list_content;
    }
    public ArrayList<Announcements> getArrListAnnouncement()
    {
        return announceList;
    }
    public ArrayList getPassWord()
    {
        return list_user_password;
    }
    public List<String> getUsername()
    {
        return list_user_name;
    }
    public List<Integer> getUseId()
    {
        return list_user_Id;
    }
    public ArrayList<number_model> getNumber_modelsList(){
        return number_modelsList;
    }

    @Override
    protected void onPostExecute(Object o) {
        //在updata.java傳editText 並 傳從前一個從公告列表按鈕拿到tilte做判斷
        if(flag==4)
        {
            for(int i=0 ; i<announceList.size(); i++)
            {

                Announcements announcements = announceList.get(i);
                if(announcements.getAnnouncements_titles().equals(AnnouncementtTitleConditionToFindContentAtFlag4))
                {
                    AnnounceUpdatseditText.setText(list_content.get(i));
                }

            }
        }
        if(flag==0) {
            Announcement_RecycleViewAdaptere announcementAdapter = new Announcement_RecycleViewAdaptere(this.context,announceList , getArryListAnnouncementContent());
            recyclerView.setAdapter(announcementAdapter);
            recyclerView.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager= new GridLayoutManager(this.context,1);
            recyclerView.setLayoutManager(gridLayoutManager);
            Announcement_Fragments.or_size = announceList.size();
//            AnnouncementAdapter announceAdapter_Fragment = new AnnouncementAdapter(this.context, announceList);
//            listView.setAdapter(announceAdapter_Fragment);
        }
        if (flag==2)
        {

            listAdapter = new ArrayAdapter<String>(context, android.R.layout.simple_spinner_item, getUsername());
            listAdapter.setDropDownViewResource(R.layout.login_spinner_text);
            this.LoginSpinner.setAdapter(listAdapter);

        }
        if(flag==11)
        {

        }
        if (flag==22)
        {
            if(announceList.size()>0)
            {
                AutoPollAdapter2 adapter = new AutoPollAdapter2(context, announceList , getArryListAnnouncementContent() , 1);
                autoPollRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL);
                dividerItemDecoration.setDrawable(context.getResources().getDrawable(R.drawable.recycle_drawable_line));
//            dividerItemDecoration.setDrawable(context.getResources()getDrawable(R.drawable.recycle_drawable_line));
                autoPollRecyclerView.addItemDecoration(dividerItemDecoration);

                autoPollRecyclerView.setAdapter(adapter);

                if (true) //保证itemCount的总个数宽度超过屏幕宽度->自己处理
                    autoPollRecyclerView.start();
            }else
            {
                ArrayList<Announcements> announcementslist = new ArrayList<>();


                Announcements announcements1 = new Announcements(R.mipmap.announcement_picture, "暫無訊息", "1" , "1" ,"1" , "1");
                announcementslist.add(announcements1);

                AutoPollAdapter2 adapter = new AutoPollAdapter2(context, announcementslist , 0);
                autoPollRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(context, DividerItemDecoration.HORIZONTAL);
                dividerItemDecoration.setDrawable(context.getResources().getDrawable(R.drawable.recycle_drawable_line));
//            dividerItemDecoration.setDrawable(context.getResources()getDrawable(R.drawable.recycle_drawable_line));
                autoPollRecyclerView.addItemDecoration(dividerItemDecoration);

                autoPollRecyclerView.setAdapter(adapter);

                if (true) //保证itemCount的总个数宽度超过屏幕宽度->自己处理
                    autoPollRecyclerView.start();
            }


        }
        if(flag==36)
        {
        }
        if(flag==38)
        {
            if(size!=announceList.size())
            {
                Announcement_Fragments.update_recycle_data1=1;

            }
        }
        if(flag==39)
        {
            if(!s.equals(home_new_string))
            {
                HOME.update_recycle_data1 = 1;
            }
        }
    }



}
