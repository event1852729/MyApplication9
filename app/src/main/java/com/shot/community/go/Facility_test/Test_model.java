package com.shot.community.go.Facility_test;

/**
 * Created by user on 2017/10/29.
 */

public class Test_model {
    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public int getStatus() {
        return Status;
    }

    public void setStatus(int status) {
        Status = status;
    }

    public int getNumberID() {
        return NumberID;
    }

    public void setNumberID(int numberID) {
        NumberID = numberID;
    }

    public String getFacility() {
        return Facility;
    }

    public void setFacility(String facility) {
        Facility = facility;
    }

    public String getMemberid() {
        return Memberid;
    }

    public void setMemberid(String memberid) {
        Memberid = memberid;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFinishtime() {
        return Finishtime;
    }

    public void setFinishtime(String finishtime) {
        Finishtime = finishtime;
    }


    public Test_model(int imageid, int status, int numberID,int facility_id ,String memberid,
                      String time, String date, String finishtime, String people,String Facility,String id) {
        this.imageid = imageid;
        this.facility_id = facility_id;
        this.people = people;
        Status = status;
        NumberID = numberID;
        Memberid = memberid;
        Time = time;
        Date = date;
        Finishtime = finishtime;
        this.Facility =Facility;
        this.id = id;

        if (status==0){
            setStatusToString("未處理");
        }
        if (status==1){
            setStatusToString("處理中");
        }
        if (status==2){
            setStatusToString("已完成");
        }

    }

    int imageid;
    int Status;
    int NumberID;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public int getFacility_id() {
        return facility_id;
    }

    public void setFacility_id(int facility_id) {
        this.facility_id = facility_id;
    }

    int facility_id;
    String Facility;
    String Memberid;
    String Time;
    String Date;
    String Finishtime;
    String statusToString;

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    String people;

    public String getStatusToString() {
        return statusToString;
    }

    public void setStatusToString(String statusToString) {
        this.statusToString = statusToString;
    }

}
