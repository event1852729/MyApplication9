package com.shot.community.go.Photo;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;

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


public class Photo_http extends AsyncTask {

    int flag;
    private Picture_model pictureModel;
    List<Picture_model> pictureModelList;
    private album_photo_model albumPhotoModel;
    List<album_photo_model> albumPhotoModelList;
    private RecyclerView recyclerView;
    private Context context;
    String line="";
    EditText editText;
    private List<String> Time;
    private List<String> Title;
    private List<String> ID;
    private List<String> Image;



    public Photo_http(RecyclerView recyclerView ,Context context, int flag  ){
        this.recyclerView = recyclerView;
        this.context=context;
        this.flag=flag;

    }
    public Photo_http(int flag){
        this.flag = flag;
    }



    public void httpAlbum(Object[] params)
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
            String PostData =URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(commid,"UTF-8");

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
            Time = new ArrayList();
            Title = new ArrayList();
            ID = new ArrayList();
            Image = new ArrayList();

            pictureModelList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                Time.add(json_data.getString("ab_date"));
                Title.add(json_data.getString("ab_name"));
                ID.add(json_data.getString("ab_id"));
                //Image.add(json_data.getString("ph_pic"));

                    pictureModel = new Picture_model(Time.get(i),Title.get(i),ID.get(i));
                    pictureModelList.add(pictureModel);


            }
            bufferedReader.close();inputStream.close();
            httpURLConnection.disconnect();
        } catch (Exception e) {
        }

    }

    public Object httpaddalbum(Object[] params)
    {
        try {
            String Name = params[1].toString();
            String commid = params[2].toString();
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("ab_name","UTF-8")+"="+URLEncoder.encode(Name,"UTF-8")+"&"+
                    URLEncoder.encode("community_id","UTF-8")+"="+URLEncoder.encode(commid,"UTF-8");

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

    public Object httpPhoto(Object[] params)
    {
        try {
            String abid = params[1].toString();
            URL url = new URL(params[0].toString());
            HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoInput(true);
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream,"UTF-8"));
            String PostData = URLEncoder.encode("ab_id","UTF-8")+"="+URLEncoder.encode(abid,"UTF-8");

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
            Image = new ArrayList();

            albumPhotoModelList = new ArrayList<>();
            JSONArray jArray = new JSONArray(results);
            for (int i = 0; i < jArray.length(); i++) {
                JSONObject json_data = jArray.getJSONObject(i);
                Image.add(json_data.getString("ph_pic"));

                albumPhotoModel = new album_photo_model(Image.get(i));
                albumPhotoModelList.add(albumPhotoModel);

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
            httpAlbum(params);
        }
        if (flag==1)
        {
            httpaddalbum(params);
        }
        if (flag==2)
        {
            httpPhoto(params);
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        if(flag==0) {
            Picture_adapter pictureAdapter = new Picture_adapter(this.context, pictureModelList);
            recyclerView.setAdapter(pictureAdapter);
            recyclerView.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 2);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
        if(flag==2) {
            album_photo_adapter albumPhotoAdapter = new album_photo_adapter(this.context, albumPhotoModelList);
            recyclerView.setAdapter(albumPhotoAdapter);
            recyclerView.setHasFixedSize(true);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this.context, 3);
            recyclerView.setLayoutManager(gridLayoutManager);
        }
    }
}
