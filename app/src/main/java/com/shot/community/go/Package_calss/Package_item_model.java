package com.shot.community.go.Package_calss;

import java.util.ArrayList;

/**
 * Created by god on 2017/10/15.
 */

public class Package_item_model
{
    public String getImag_url() {
        return imag_url;
    }

    public void setImag_url(String imag_url) {
        this.imag_url = imag_url;
    }

    String imag_url;
    String have_sign_image;

    public ArrayList<String> getImage() {
        return image;
    }

    ArrayList<String> image;



    public String getSign_url() {
        return sign_url;
    }

    String sign_url;
    public String getPackage_time() {
        return package_time;
    }


    public Package_item_model(String package_time, int package_number_id, String numberName, int package_stauas , String imag_url , ArrayList<String> image
    ,String package_finish_date) {
        this.package_time = package_time;
        this.package_number_id = package_number_id;
        this.numberName = numberName;
        this.package_stauas = package_stauas;
        this.imag_url = imag_url;
        this.image = image;
        if(package_stauas==1)
        {
            setPackage_statusToString("代領取");
        }
        if(package_stauas==2)
        {
            setPackage_statusToString("已領取");
        }
        if(package_stauas==3)
        {
            setPackage_statusToString("代退貨");
        }
        if(package_stauas==4)
        {
            setPackage_statusToString("已退貨");
        }
        this.package_finish_date =  package_finish_date;
    }




    public Package_item_model(String package_time, int package_number_id, String numberName, int package_stauas , String imag_url) {
        this.package_time = package_time;
        this.package_number_id = package_number_id;
        this.numberName = numberName;
        this.package_stauas = package_stauas;
        this.imag_url = imag_url;
        if(package_stauas==1)
        {
            setPackage_statusToString("代領取");
        }
        if(package_stauas==2)
        {
            setPackage_statusToString("已領取");
        }
        if(package_stauas==3)
        {
            setPackage_statusToString("代退貨");
        }
        if(package_stauas==4)
        {
            setPackage_statusToString("已退貨");
        }
    }

    public String getHave_sign_image() {
        return have_sign_image;
    }

    public String getPackage_finish_date() {
        return package_finish_date;
    }

    String package_finish_date;
    public Package_item_model(String package_time, int package_number_id, String numberName, int package_stauas ) {
        this.package_time = package_time;
        this.package_number_id = package_number_id;
        this.numberName = numberName;
        this.package_stauas = package_stauas;
        if(package_stauas==1)
        {
            setPackage_statusToString("代領取");
        }
        if(package_stauas==2)
        {
            setPackage_statusToString("已領取");
        }
        if(package_stauas==3)
        {
            setPackage_statusToString("代退貨");
        }
        if(package_stauas==4)
        {
            setPackage_statusToString("已退貨");
        }
    }

    public void setPackage_time(String package_time) {
        this.package_time = package_time;
    }

    public int getPackage_number_id() {
        return package_number_id;
    }

    public void setPackage_number_id(int package_number_id) {
        this.package_number_id = package_number_id;
    }

    public String getNumberName() {
        return numberName;
    }

    public void setNumberName(String numberName) {
        this.numberName = numberName;
    }

    public int getPackage_stauas() {
        return package_stauas;
    }

    public void setPackage_stauas(int package_stauas) {
        this.package_stauas = package_stauas;
    }

    String package_time;
    int package_number_id;
    String numberName;
    int package_stauas;

    public String getPackage_statusToString() {
        return package_statusToString;
    }

    public void setPackage_statusToString(String package_statusToString) {
        this.package_statusToString = package_statusToString;
    }

    String package_statusToString;

}
