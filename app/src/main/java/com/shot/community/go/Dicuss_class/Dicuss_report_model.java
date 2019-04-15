package com.shot.community.go.Dicuss_class;

/**
 * Created by user on 2017/11/19.
 */

public class Dicuss_report_model {
    public Dicuss_report_model(String name, int article_id, int status_id, String reson, String date, int rpid,
            String detail) {
        this.name = name;
        this.article_id = article_id;
        this.status_id = status_id;
        this.reson = reson;
        this.date = date;
        this.rpid = rpid;
        this.detail = detail;

        if (status_id == 0)
        {
            setStatusToString("未審核");
        }
        if (status_id == 1)
        {
            setStatusToString("已完成");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getStatus_id() {
        return status_id;
    }

    public void setStatus_id(int status_id) {
        this.status_id = status_id;
    }

    public String getStatusToString() {
        return statusToString;
    }

    public void setStatusToString(String statusToString) {
        this.statusToString = statusToString;
    }

    public String getReson() {
        return reson;
    }

    public void setReson(String reson) {
        this.reson = reson;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getRpid() {
        return rpid;
    }

    public void setRpid(int rpid) {
        this.rpid = rpid;
    }

    String name;
    int article_id;
    int status_id;
    String statusToString;
    String reson;
    String date;
    int rpid;



    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    String detail;


}
