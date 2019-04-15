package com.shot.community.go.Message_class;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

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
 * Created by god on 2017/10/20.
 */

public class Message_http extends AsyncTask {
    List<Integer> listMessage_message_id = new ArrayList<>();
    List<String> mg_mb_id = new ArrayList<>();
    List<String> listMessage_content = new ArrayList<>();
    List<String> listMessage_date = new ArrayList<>();
    List<String> listMessage_Name = new ArrayList<>();
    List<String> listMessage_id = new ArrayList<>();
    List<String> listMessage_read = new ArrayList<>();

    List<Integer> listMessage_type = new ArrayList<>();
    List<String> listMessage_messageher_id = new ArrayList();
    Message_item_model message_item_mode;
    String messahe_id;
    String number_Hername;
    String message_herid;
    String messahe_content;

    String line;
    int listsize;
    int flag;
    Context context;
    RecyclerView messageRecycle;

    public List<Message_item_model> getMessage_item_modelsList() {
        return message_item_modelsList;
    }

    List<Message_item_model> message_item_modelsList = new ArrayList<>();
    public Message_http(Context context , int flag , RecyclerView messageRecy) {
        this.flag  = flag;
        this.context = context;
        this.messageRecycle = messageRecy;
    }
    Message_http message_http;
    public Message_http( int flag ) {
        this.flag  = flag;
    }
    public Message_http(Context context , int flag ) {
        this.flag  = flag;
        this.context = context;
    }
    public Message_http(Context context , int flag ,int size ) {
        this.flag  = flag;
        this.context = context;
        this.listsize = size;
    }
    public Message_http(Context context , int flag ,int size  , Message_http message_http) {
        this.flag  = flag;
        this.context = context;
        this.listsize = size;
        this.message_http = message_http;
    }

    public void catchMessageData(Object[] params){
        try{
            messahe_id = params[1].toString();
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
                Log.d("hell3o" , line);
                sb1.append(line);
            }
            String results = sb1.toString();
            listMessage_message_id = new ArrayList();
            listMessage_content = new ArrayList();
            listMessage_Name = new ArrayList();
            listMessage_date = new ArrayList();
            listMessage_type = new ArrayList();
            listMessage_id = new ArrayList<>();
            listMessage_read = new ArrayList<>();
            listMessage_messageher_id = new ArrayList<>();
            message_item_mode = new Message_item_model();
            message_item_modelsList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i <jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                listMessage_read.add(json_data.getString("have_look"));
                listMessage_id.add(json_data.getString("mg_id"));
                listMessage_messageher_id.add(json_data.getString("mg_mb_id"));
                listMessage_message_id.add(json_data.getInt("mg_id"));
                listMessage_Name.add(json_data.getString("mb_name"));
                listMessage_content.add((json_data.getString("mg_content")));
                listMessage_date.add(json_data.getString("mg_date"));
                listMessage_type.add(json_data.getInt("mg_type"));
                message_item_mode = new Message_item_model(messahe_id,listMessage_messageher_id.get(i),listMessage_Name.get(i),  listMessage_content.get(i),  listMessage_date.get(i) ,listMessage_type.get(i) , listMessage_id.get(i)
                        ,listMessage_read.get(i));
                message_item_modelsList.add(message_item_mode);
            }
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        }catch (Exception e){
        }
    }


    public void catchTalkabout(Object[] params){
        try{

            messahe_id = params[1].toString();
            number_Hername = params[2].toString();
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("r_mb_id","UTF-8")+"="+URLEncoder.encode(messahe_id,"UTF-8") + "&" +
                    URLEncoder.encode("mg_mb_id","UTF-8")+"="+URLEncoder.encode(number_Hername,"UTF-8");
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
            listMessage_message_id = new ArrayList();
            listMessage_content = new ArrayList();
            listMessage_Name = new ArrayList();
            listMessage_date = new ArrayList();
            listMessage_type = new ArrayList();
            message_item_mode = new Message_item_model();
            message_item_modelsList = new ArrayList<>();
            mg_mb_id = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);

            for (int i = 0; i <jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);


                listMessage_message_id.add(json_data.getInt("mg_id"));
                listMessage_Name.add(json_data.getString("mb_name"));
                listMessage_content.add((json_data.getString("mg_content")));
                listMessage_date.add(json_data.getString("mg_date"));
                listMessage_type.add(json_data.getInt("mg_type"));
                mg_mb_id.add(json_data.getString("r_mb_id"));

                if(messahe_id.equals(mg_mb_id.get(i)))
                    listMessage_type.set(i , 2);
                else
                    listMessage_type.set(i , 1);

                message_item_mode = new Message_item_model("","",listMessage_Name.get(i),  listMessage_content.get(i),  listMessage_date.get(i) ,listMessage_type.get(i),listMessage_message_id.get(i).toString() , "");
                message_item_modelsList.add(message_item_mode);

            }

            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        }catch (Exception e){

        }

    }

    public void catchrecord(Object[] params){
        try{
            messahe_id = params[1].toString();
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
            listMessage_message_id = new ArrayList();
            listMessage_content = new ArrayList();
            listMessage_Name = new ArrayList();
            listMessage_date = new ArrayList();
            listMessage_type = new ArrayList();
            message_item_mode = new Message_item_model();
            message_item_modelsList = new ArrayList<>();
            mg_mb_id = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);

            for (int i = 0; i <jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);

                listMessage_Name.add(json_data.getString("mb_name"));
                listMessage_content.add((json_data.getString("mg_content")));
                listMessage_date.add(json_data.getString("mg_date"));

                message_item_mode = new Message_item_model(listMessage_Name.get(i),  listMessage_content.get(i),  listMessage_date.get(i));
                message_item_modelsList.add(message_item_mode);
            }
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        }catch (Exception e){
        }

    }


    public void replymessage(Object[] params){
        try{
            messahe_id = params[1].toString();
            message_herid = params[2].toString();
            messahe_content = params[3].toString();

            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mg_mb_id","UTF-8")+"="+URLEncoder.encode(messahe_id,"UTF-8") +"&" +URLEncoder.encode("r_mb_id","UTF-8")+"="+URLEncoder.encode(message_herid,"UTF-8") +"&" + URLEncoder.encode("mg_content","UTF-8")+"="+URLEncoder.encode(messahe_content,"UTF-8") ;
            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));

            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();






        }catch (Exception e){
        }

    }

    @Override
    protected Object doInBackground(Object[] params) {

        if(flag==0)
        {
            catchMessageData(params);

        }
        if(flag==1)
        {
            catchTalkabout(params);
        }
        if(flag==2) {
            catchrecord(params);
        }
        if(flag==3)
        {
            replymessage(params);
        }
        if(flag==5)
        {
            catchMessageData(params);
        }
        if(flag==15)
        {
            sendtomanager(params);
        }
        if(flag==20)
        {
            readMessage(params);
        }
        if(flag==21)
        {
            httpmail_read(params);
        }
        if(flag==10)
        {
            catchsearchnumber(params);
        }
        if(flag==22)
        {
            catchTalkabout(params);
        }
        if(flag==23)
        {
            catchMessageData(params);

        }



        return null;
    }

    private void httpmail_read(Object[] params) {
        try {
            String ann_number_id = params[1].toString();
            String mg_id = params[2].toString();
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mg_id","UTF-8") + "=" + URLEncoder.encode(mg_id,"UTF-8")+"&"+
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

    private void sendtomanager(Object[] params) {

        try{
            String sendtocomid = params[1].toString();
            String sendcontent = params[2].toString();
            String sendnumberid = params[3].toString();


            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(sendtocomid,"UTF-8")
                    +"&" +URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(sendnumberid,"UTF-8")
                    +"&" + URLEncoder.encode("mg_content","UTF-8")+"="+URLEncoder.encode(sendcontent,"UTF-8") ;
            bufferedWriter.write(PostData);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream , "iso-8859-1"));

            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();







        }catch (Exception e){
        }

    }

    private void catchsearchnumber(Object[] params) {
        try{

            messahe_id = params[1].toString();

            String herid = params[2].toString();

            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mb_id","UTF-8")+"="+URLEncoder.encode(messahe_id,"UTF-8")+"&"+
                    URLEncoder.encode("select_mb_id","UTF-8")+"="+URLEncoder.encode(herid,"UTF-8");
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
            listMessage_message_id = new ArrayList();
            listMessage_content = new ArrayList();
            listMessage_Name = new ArrayList();
            listMessage_date = new ArrayList();
            listMessage_type = new ArrayList();
            listMessage_id = new ArrayList<>();
            listMessage_read = new ArrayList<>();
            listMessage_messageher_id = new ArrayList<>();
            message_item_mode = new Message_item_model();
            message_item_modelsList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i <jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                listMessage_read.add(json_data.getString("have_look"));
                listMessage_id.add(json_data.getString("mg_id"));
                listMessage_messageher_id.add(json_data.getString("mg_mb_id"));
                listMessage_message_id.add(json_data.getInt("mg_id"));
                listMessage_Name.add(json_data.getString("mb_name"));
                listMessage_content.add((json_data.getString("mg_content")));
                listMessage_date.add(json_data.getString("mg_date"));
                listMessage_type.add(json_data.getInt("mg_type"));
                message_item_mode = new Message_item_model(messahe_id,listMessage_messageher_id.get(i),listMessage_Name.get(i),  listMessage_content.get(i),  listMessage_date.get(i) ,listMessage_type.get(i) , listMessage_id.get(i)
                        ,listMessage_read.get(i));
                message_item_modelsList.add(message_item_mode);
            }
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        }catch (Exception e){
        }

    }

    private void readMessage(Object[] params) {

        try{
            String messageid = params[1].toString();
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("mg_id","UTF-8")+"="+URLEncoder.encode(messageid,"UTF-8");
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
            bufferedReader.close();inputStream.close();httpURLConnection.disconnect();

        }catch (Exception e){
        }

    }


    @Override
    protected void onPostExecute(Object o) {
        if(flag==0) {
            Message_recycleViewAdaptew messageRecycleViewAdaptew = new Message_recycleViewAdaptew(this.context, message_item_modelsList , messageRecycle);
            this.messageRecycle.setAdapter(messageRecycleViewAdaptew);
            messageRecycle.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            messageRecycle.setLayoutManager(gridLayoutManager);
        }
        if(flag==1)
        {
            Message_recycleadpter_talkabout messageRecycleViewAdaptew = new Message_recycleadpter_talkabout(messageRecycle,this.context, message_item_modelsList);
            this.messageRecycle.setAdapter(messageRecycleViewAdaptew);
            messageRecycle.setHasFixedSize(true);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.context , LinearLayoutManager.VERTICAL , false);
//            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
//            gridLayoutManager
////            gridLayoutManager.setStackFromEnd(true);
            messageRecycle.setLayoutManager(linearLayoutManager);
            linearLayoutManager.scrollToPosition(messageRecycleViewAdaptew.getItemCount()-1);
        }
        if (flag==2)
        {
            Message_Record_RecycleAdapter messageRecycleViewAdaptew = new Message_Record_RecycleAdapter( message_item_modelsList,this.context);
            this.messageRecycle.setAdapter(messageRecycleViewAdaptew);
            messageRecycle.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            messageRecycle.setLayoutManager(gridLayoutManager);
        }
        if(flag==3)
        {

        }
        if(flag==10)
        {
            Message_recycleViewAdaptew messageRecycleViewAdaptew = new Message_recycleViewAdaptew(this.context, message_item_modelsList);
            this.messageRecycle.setAdapter(messageRecycleViewAdaptew);
            messageRecycle.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 1);
            messageRecycle.setLayoutManager(gridLayoutManager);
        }
        if(flag==22)
        {
            if(message_item_modelsList.size()>listsize)
            {
                Message_onclick_intent_talkabout.update_recycle_data = 1;
            }
        }
        if(flag==23)
        {

            if(listsize!=message_item_modelsList.size())
            {
                ViewPager_Recycle1.update_recycle_data1=1;
            }else {
                for(int i = 0 ; i<listsize ; i++)
                {
                    if(!message_item_modelsList.get(i).getRead().equals(message_http.getMessage_item_modelsList().get(i).getRead()) ||
                            !message_item_modelsList.get(i).getMessage_item_content().equals(message_http.getMessage_item_modelsList().get(i).getMessage_item_content()) ){
                        ViewPager_Recycle1.update_recycle_data1=1;
                    }
                }
            }

        }

    }
}
