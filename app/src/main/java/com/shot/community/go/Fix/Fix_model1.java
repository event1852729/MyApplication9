package com.shot.community.go.Fix;

public class Fix_model1 {


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

    public Fix_model1(String image, String detail, String place, String id, String time, int status,int fixid,String makeTime, String f_name, String f_phone) {
        this.image = image;
        this.detail = detail;
        this.place = place;
        this.id = id;
        this.time = time;
        this.status = status;
        this.fixid = fixid;
        this.makeTime = makeTime;
        this.f_name = f_name;
        this.f_phone = f_phone;

        if (status==1){
            setStatustoString("處理中");
        }
    }

    String detail;
    String place;
    String id;
    String time;
    int status;
    String f_name;

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getF_phone() {
        return f_phone;
    }

    public void setF_phone(String f_phone) {
        this.f_phone = f_phone;
    }

    String f_phone;

    public String getMakeTime() {
        return makeTime;
    }

    public void setMakeTime(String makeTime) {
        this.makeTime = makeTime;
    }

    String makeTime;

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
