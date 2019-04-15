package com.shot.community.go.Detail_calss;

import java.util.ArrayList;

/**
 * Created by god on 2017/11/11.
 */

public class Detail_fixrecord_item_model {
    String fixrecord_id;
    String fixrecord_content;
    String fixrecord_replydate;
    String fixrecord_imageurl;
    String fixrecord_space;


    public ArrayList<String> getImage() {
        return image;
    }

    ArrayList<String> image;



    public String getFixrecord_make_date() {
        return fixrecord_make_date;
    }

    public void setFixrecord_make_date(String fixrecord_make_date) {
        this.fixrecord_make_date = fixrecord_make_date;
    }

    public String getFixrecord_finish_date() {
        return fixrecord_finish_date;
    }

    public void setFixrecord_finish_date(String fixrecord_finish_date) {
        this.fixrecord_finish_date = fixrecord_finish_date;
    }

    String fixrecord_make_date;
    String fixrecord_finish_date;
    public String getFixrecord_status() {
        return fixrecord_status;
    }

    public void setFixrecord_status(String fixrecord_status) {
        this.fixrecord_status = fixrecord_status;
    }

    String fixrecord_status;

    public String getFixrecord_id() {
        return fixrecord_id;
    }

    public void setFixrecord_id(String fixrecord_id) {
        this.fixrecord_id = fixrecord_id;
    }

    public String getFixrecord_content() {
        return fixrecord_content;
    }

    public void setFixrecord_content(String fixrecord_content) {
        this.fixrecord_content = fixrecord_content;
    }

    public String getFixrecord_replydate() {
        return fixrecord_replydate;
    }

    public void setFixrecord_replydate(String fixrecord_replydate) {
        this.fixrecord_replydate = fixrecord_replydate;
    }

    public String getFixrecord_imageurl() {
        return fixrecord_imageurl;
    }

    public void setFixrecord_imageurl(String fixrecord_imageurl) {
        this.fixrecord_imageurl = fixrecord_imageurl;
    }

    public String getFixrecord_space() {
        return fixrecord_space;
    }

    public void setFixrecord_space(String fixrecord_space) {
        this.fixrecord_space = fixrecord_space;
    }

    public String getFixrecord_name() {
        return fixrecord_name;
    }

    public void setFixrecord_name(String fixrecord_name) {
        this.fixrecord_name = fixrecord_name;
    }

    public Detail_fixrecord_item_model(String fixrecord_id, String fixrecord_content, String fixrecord_replydate, String fixrecord_imageurl, String fixrecord_space, String fixrecord_name , String fixrecord_status) {
        this.fixrecord_id = fixrecord_id;
        this.fixrecord_content = fixrecord_content;
        this.fixrecord_replydate = fixrecord_replydate;
        this.fixrecord_imageurl = fixrecord_imageurl;
        this.fixrecord_space = fixrecord_space;
        this.fixrecord_name = fixrecord_name;
        this.fixrecord_status = fixrecord_status;

    }
    public Detail_fixrecord_item_model(String fixrecord_id, String fixrecord_content, String fixrecord_replydate, String fixrecord_imageurl, String fixrecord_space, String fixrecord_name , String fixrecord_status , String fixrecord_make_date , String fixrecord_finish_date) {
        this.fixrecord_id = fixrecord_id;
        this.fixrecord_content = fixrecord_content;
        this.fixrecord_replydate = fixrecord_replydate;
        this.fixrecord_imageurl = fixrecord_imageurl;
        this.fixrecord_space = fixrecord_space;
        this.fixrecord_name = fixrecord_name;
        this.fixrecord_status = fixrecord_status;
        this.fixrecord_make_date = fixrecord_make_date;
        this.fixrecord_finish_date = fixrecord_finish_date;
    }

    public Detail_fixrecord_item_model(String fixrecord_id, String fixrecord_content, String fixrecord_replydate, String fixrecord_imageurl, String fixrecord_space, String fixrecord_name , String fixrecord_status , String fixrecord_make_date , String fixrecord_finish_date
    ,    ArrayList<String> image) {
        this.fixrecord_id = fixrecord_id;
        this.fixrecord_content = fixrecord_content;
        this.fixrecord_replydate = fixrecord_replydate;
        this.fixrecord_imageurl = fixrecord_imageurl;
        this.fixrecord_space = fixrecord_space;
        this.fixrecord_name = fixrecord_name;
        this.fixrecord_status = fixrecord_status;
        this.fixrecord_make_date = fixrecord_make_date;
        this.fixrecord_finish_date = fixrecord_finish_date;
        this.image = image;
    }



    String fixrecord_name;
}
