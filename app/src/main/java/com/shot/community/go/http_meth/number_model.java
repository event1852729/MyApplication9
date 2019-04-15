package com.shot.community.go.http_meth;

/**
 * Created by god on 2017/10/22.
 */

public class number_model {
    int number_id;


    public number_model(int number_id, String number_name, String number_password) {
        this.number_id = number_id;
        this.number_name = number_name;
        this.number_password = number_password;
    }

    public int getNumber_id() {
        return number_id;
    }

    public void setNumber_id(int number_id) {
        this.number_id = number_id;
    }

    public String getNumber_name() {
        return number_name;
    }

    public void setNumber_name(String number_name) {
        this.number_name = number_name;
    }

    public String getNumber_password() {
        return number_password;
    }

    public void setNumber_password(String number_password) {
        this.number_password = number_password;
    }

    String number_name;
    String number_password;

    public number_model(int number_id, String number_name, String number_password, String manager) {
        this.number_id = number_id;
        this.number_name = number_name;
        this.number_password = number_password;
        this.manager = manager;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    String manager;
}
