package com.shot.community.go.Detail_calss;

/**
 * Created by god on 2017/11/11.
 */

public class Detail_app_item_model {
    String fa_name;
    String fa_usercount;
    String fa_usetime;

    public String getFa_mb_name() {
        return fa_mb_name;
    }

    String fa_mb_name;

    public String getFa_name() {
        return fa_name;
    }

    public void setFa_name(String fa_name) {
        this.fa_name = fa_name;
    }

    public String getFa_usercount() {
        return fa_usercount;
    }

    public void setFa_usercount(String fa_usercount) {
        this.fa_usercount = fa_usercount;
    }

    public String getFa_usetime() {
        return fa_usetime;
    }

    public void setFa_usetime(String fa_usetime) {
        this.fa_usetime = fa_usetime;
    }

    public String getFa_status() {
        return fa_status;
    }

    public void setFa_status(String fa_status) {
        this.fa_status = fa_status;
    }

    public String getFa_apply_date() {
        return fa_apply_date;
    }

    public void setFa_apply_date(String fa_apply_date) {
        this.fa_apply_date = fa_apply_date;
    }

    String fa_status;

    public Detail_app_item_model(String fa_name, String fa_usercount, String fa_usetime, String fa_status, String fa_apply_date ,  String fa_apply_date2 , String fa_apply_finishdate) {
        this.fa_name = fa_name;
        this.fa_usercount = fa_usercount;
        this.fa_usetime = fa_usetime;
        this.fa_status = fa_status;
        this.fa_apply_date = fa_apply_date;
        this.fa_apply_date2 = fa_apply_date2;
        this.fa_apply_finishdate = fa_apply_finishdate;
    }
    public Detail_app_item_model(String fa_name, String fa_usercount, String fa_usetime, String fa_status, String fa_apply_date ,  String fa_apply_date2 , String fa_apply_finishdate , String fa_mb_name) {
        this.fa_name = fa_name;
        this.fa_usercount = fa_usercount;
        this.fa_usetime = fa_usetime;
        this.fa_status = fa_status;
        this.fa_apply_date = fa_apply_date;
        this.fa_apply_date2 = fa_apply_date2;
        this.fa_apply_finishdate = fa_apply_finishdate;
        this.fa_mb_name = fa_mb_name;
    }

    String fa_apply_date;

    public String getFa_apply_date2() {
        return fa_apply_date2;
    }

    public void setFa_apply_date2(String fa_apply_date2) {
        this.fa_apply_date2 = fa_apply_date2;
    }

    String fa_apply_date2;

    public String getFa_apply_finishdate() {
        return fa_apply_finishdate;
    }

    public void setFa_apply_finishdate(String fa_apply_finishdate) {
        this.fa_apply_finishdate = fa_apply_finishdate;
    }

    String fa_apply_finishdate;

}
