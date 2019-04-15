package com.shot.community.go.Detail_calss;

/**
 * Created by god on 2017/11/10.
 */

public class Detail_record_item_model {
    String record_date;

    public String getRecord_date() {
        return record_date;
    }

    public void setRecord_date(String record_date) {
        this.record_date = record_date;
    }

    public String getRecord_urlname() {
        return record_urlname;
    }

    public void setRecord_urlname(String record_urlname) {
        this.record_urlname = record_urlname;
    }

    public String getRecord_record_name() {
        return record_record_name;
    }

    public void setRecord_record_name(String record_record_name) {
        this.record_record_name = record_record_name;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    String record_urlname;

    public Detail_record_item_model(String record_date, String record_urlname, String record_record_name, String record_id ,String record_content ) {
        this.record_date = record_date;
        this.record_urlname = record_urlname;
        this.record_record_name = record_record_name;
        this.record_id = record_id;
        this.record_content = record_content;
    }

    String record_record_name;
    String record_id;

    public String getRecord_content() {
        return record_content;
    }

    public void setRecord_content(String record_content) {
        this.record_content = record_content;
    }

    String record_content;
}
