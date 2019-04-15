package com.shot.community.go.Facility_test;

/**
 * Created by user on 2017/11/2.
 */

public class Facility_test_model {



    public Facility_test_model(int imageid,int nameid, int statusid, int day, String time, String limit,String name,int st,int et) {
        this.imageid = imageid;
        this.nameid = nameid;
        this.statusid = statusid;
        this.day = day;
        this.time = time;
        this.limit = limit;
        this.name = name;
        this.et =et;
        this.st = st;
        if (statusid==0){setStatus("不開放");}
        if (statusid==1){setStatus("開放中");}

       switch (day){
           case 1:
               setDate("星期一");
               break;
           case 2:
               setDate("星期二");
               break;
           case 3:
               setDate("星期三");
               break;
           case 4:
               setDate("星期四");
               break;
           case 5:
               setDate("星期五");
               break;
           case 6:
               setDate("星期六");
               break;
           case 7:
               setDate("星期日");
               break;
           default:
               setDate("無公休日");
       }


    }

    int imageid;

    public int getNameid() {
        return nameid;
    }

    public void setNameid(int nameid) {
        this.nameid = nameid;
    }

    int nameid;

    public int getStatusid() {
        return statusid;
    }

    public void setStatusid(int statusid) {
        this.statusid = statusid;
    }

    int statusid;
    String name;
    String date;
    String time;
    String limit;
    String status;

    public int getSt() {
        return st;
    }

    public void setSt(int st) {
        this.st = st;
    }

    public int getEt() {
        return et;
    }

    public void setEt(int et) {
        this.et = et;
    }

    int st;
    int et;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    int day;

    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
