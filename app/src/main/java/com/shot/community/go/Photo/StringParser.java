package com.shot.community.go.Photo;

import com.shot.community.go.Template.Template;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by user on 2017/11/16.
 */

public class StringParser {

    public static String getCode(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            return String.valueOf(jsonObject.getInt(Template.Query.KEY_CODE));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getMessage(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            return jsonObject.getString(Template.Query.KEY_MESSAGE);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";
    }
}
