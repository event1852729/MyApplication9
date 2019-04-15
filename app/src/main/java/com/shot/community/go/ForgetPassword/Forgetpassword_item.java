package com.shot.community.go.ForgetPassword;

/**
 * Created by user on 2017/12/3.
 */

public class Forgetpassword_item {

    public Forgetpassword_item(String name, String phone, String address, String mail) {
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.mail = mail;
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

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    String name;
    String phone;
    String address;
    String mail;
}
