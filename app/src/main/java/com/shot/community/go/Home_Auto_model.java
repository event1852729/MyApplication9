package com.shot.community.go;

/**
 * Created by user on 2017/11/30.
 */

public class Home_Auto_model {


    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Home_Auto_model(int imageid) {
        this.imageid = imageid;
    }

    int imageid;

    public Home_Auto_model(int imageid, String text) {
        this.imageid = imageid;
        this.text = text;
    }

    String text;
}
