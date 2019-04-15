package com.shot.community.go.Dicuss_class;

/**
 * Created by god on 2017/10/26.
 */

public class Dicuss_item_model {
    String list_title;
    String list_content;
    String list_dicuss_id;
    String list_date;
    String list_number_id;

    public Dicuss_item_model(String list_title, String list_content, String list_dicuss_id, String list_date, String list_number_id, String list_number_name) {
        this.list_title = list_title;
        this.list_content = list_content;
        this.list_dicuss_id = list_dicuss_id;
        this.list_date = list_date;
        this.list_number_id = list_number_id;
        this.list_number_name = list_number_name;
    }

    public String getList_number_name() {
        return list_number_name;
    }

    public void setList_number_name(String list_number_name) {
        this.list_number_name = list_number_name;
    }

    String list_number_name;
    public Dicuss_item_model() {

    }

    public Dicuss_item_model(String list_title, String list_content, String list_dicuss_id, String list_date, String list_number_id) {
        this.list_title = list_title;
        this.list_content = list_content;
        this.list_dicuss_id = list_dicuss_id;
        this.list_date = list_date;
        this.list_number_id = list_number_id;
    }

    public String getList_title() {
        return list_title;
    }

    public void setList_title(String list_title) {
        this.list_title = list_title;
    }

    public String getList_content() {
        return list_content;
    }

    public void setList_content(String list_content) {
        this.list_content = list_content;
    }

    public String getList_dicuss_id() {
        return list_dicuss_id;
    }

    public void setList_dicuss_id(String list_dicuss_id) {
        this.list_dicuss_id = list_dicuss_id;
    }

    public String getList_date() {
        return list_date;
    }

    public void setList_date(String list_date) {
        this.list_date = list_date;
    }

    public String getList_number_id() {
        return list_number_id;
    }

    public void setList_number_id(String list_number_id) {
        this.list_number_id = list_number_id;
    }
}
