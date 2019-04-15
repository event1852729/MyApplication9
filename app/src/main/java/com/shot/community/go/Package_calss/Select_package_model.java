package com.shot.community.go.Package_calss;

/**
 * Created by god on 2017/11/25.
 */

public class Select_package_model {
    public String getName() {
        return name;
    }

    public String getName_id() {
        return name_id;
    }

    public Select_package_model(String name, String name_id) {

        this.name = name;
        this.name_id = name_id;
    }

    String name;
    String name_id;
}
