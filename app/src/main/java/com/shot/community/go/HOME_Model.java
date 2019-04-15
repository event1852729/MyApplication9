package com.shot.community.go;

/**
 * Created by user on 2017/11/29.
 */

public class HOME_Model {
    public int getImageid() {
        return imageid;
    }

    public void setImageid(int imageid) {
        this.imageid = imageid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HOME_Model(int imageid, String name) {
        this.imageid = imageid;
        this.name = name;
    }

    int imageid;
    String name;
}
