package com.shot.community.go.Dicuss_class;

/**
 * Created by god on 2017/10/29.
 */

public class Dicuss_content_message_item  {
    String count;
    String date;
    String content;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Dicuss_content_message_item(String count, String date, String content, String name , String lm_id) {
        this.count = count;
        this.date = date;
        this.content = content;
        this.name = name;
        this.lm_id = lm_id;
    }

    String name;

    public Dicuss_content_message_item(String count, String date, String content) {
        this.count = count;
        this.date = date;
        this.content = content;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLm_id() {
        return lm_id;
    }

    public void setLm_id(String lm_id) {
        this.lm_id = lm_id;
    }

    String lm_id;
}
