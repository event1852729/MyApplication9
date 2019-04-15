package com.shot.community.go.Creat_comunity;

/**
 * Created by god on 2017/11/17.
 */

public class Apply_com_numbername_item {
    String name;
    String phone;
    String address;

    public String getHolderId() {
        return holderId;
    }

    String holderId;

    public String getDate() {
        return date;
    }

    String date;

    public String getComname() {
        return comname;
    }

    public void setComname(String comname) {
        this.comname = comname;
    }

    String comname;
    public Apply_com_numbername_item(String name, String phone, String address, String email, String applyId , String date ,   String holderId) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.applyId = applyId;
        this.date = date;
        this.holderId=holderId;
    }

    public Apply_com_numbername_item(String name, String phone, String address, String email, String applyId , String date) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.email = email;
        this.applyId = applyId;
        this.date = date;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    String email;
    String applyId;

}
