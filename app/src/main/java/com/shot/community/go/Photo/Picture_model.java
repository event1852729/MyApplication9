package com.shot.community.go.Photo;

/**
 * Created by user on 2017/11/16.
 */

public class Picture_model {
    public Picture_model(String time, String title,String id) {
        Time = time;
        Title = title;
        ID = id;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    String Time;
    String Title;

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        this.ID = id;
    }

    String ID;

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        Image = image;
    }

    String Image;
}
