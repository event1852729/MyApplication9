package com.shot.community.go.Fix;

/**
 * Created by user on 2017/11/8.
 */

public class Fix_model {


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    String image;

    public Fix_model(String image, String detail, String place, String id, String time, int status, int fixid) {
        this.image = image;
        this.detail = detail;
        this.place = place;
        this.id = id;
        this.time = time;
        this.status = status;
        this.fixid = fixid;
        if (status==0){
            setStatustoString("未處理");
        }
    }

    String detail;
    String place;
    String id;
    String time;
    int status;



    public int getFixid() {
        return fixid;
    }

    public void setFixid(int fixid) {
        this.fixid = fixid;
    }

    int fixid;

    public String getStatustoString() {
        return statustoString;
    }

    public void setStatustoString(String statustoString) {
        this.statustoString = statustoString;
    }

    String statustoString;
}
