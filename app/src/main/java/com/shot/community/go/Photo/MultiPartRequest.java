package com.shot.community.go.Photo;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.shot.community.go.Template.EndPointAPI;
import com.shot.community.go.Template.Template;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import cz.msebera.android.httpclient.HttpEntity;
import cz.msebera.android.httpclient.entity.mime.MultipartEntityBuilder;
import cz.msebera.android.httpclient.entity.mime.content.FileBody;

/**
 * Created by user on 2017/11/16.
 */

public class MultiPartRequest  extends Request<String>{

    private Response.Listener<String> mListener;
    private HttpEntity mHttpEntity;

    public MultiPartRequest(Response.ErrorListener errorListener, Response.Listener listener, ArrayList<File> file, int numberOfFiles , String ab_id) {
        super(Method.POST, EndPointAPI.PATH, errorListener);
        mListener = listener;
        mHttpEntity = buildMultipartEntity(file, numberOfFiles , ab_id);

    }

    private HttpEntity buildMultipartEntity(ArrayList<File> file, int numberOffiles , String ab_id) {
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        for(int i=0; i < file.size();i++){
            FileBody fileBody = new FileBody(file.get(i));
            builder.addPart(Template.Query.KEY_IMAGE.concat(String.valueOf(i)), fileBody);
        }

        builder.addTextBody(Template.Query.KEY_DIRECTORY, Template.Query.VALUE_DIRECTORY);
        builder.addTextBody("ab_id", ab_id);
        builder.addTextBody("numberOfFiles", String.valueOf(numberOffiles));
        return builder.build();
    }




    @Override
    public String getBodyContentType() {
        return mHttpEntity.getContentType().getValue();
    }

    @Override
    public byte[] getBody() throws AuthFailureError {

        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            mHttpEntity.writeTo(bos);
            return bos.toByteArray();
        } catch (IOException e) {
            VolleyLog.e("" + e);
            return null;
        } catch (OutOfMemoryError e){
            VolleyLog.e("" + e);
            return null;
        }

    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        try {
            return Response.success(new String(response.data, "UTF-8"),
                    getCacheEntry());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return Response.success(new String(response.data),
                    getCacheEntry());
        }
    }

    @Override
    protected void deliverResponse(String response) {
        mListener.onResponse(response);
    }

}