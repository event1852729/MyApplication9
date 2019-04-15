package com.shot.community.go.Creat_comunity;

/**
 * Created by god on 2017/11/17.
 */

public class Search_com_item {
    String name;
    String phone;
    String address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    String id;

    public String getNumbername() {
        return numbername;
    }

    public Search_com_item(String name, String phone, String address, String numbername,String id, String date) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.numbername = numbername;
        this.id = id;
        this.date = date;
    }

    public void setNumbername(String numbername) {
        this.numbername = numbername;
    }

    String numbername;

//    public Search_com_item(String name, String phone, String address, String email, String applyId ,String numbername) {
//        this.name = name;
//        this.phone = phone;
//        this.address = address;
//        this.email = email;
//        this.applyId = applyId;
//        this.numbername = numbername;
//
//    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;
}
