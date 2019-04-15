package com.shot.community.go.Package_calss;

/**
 * Created by god on 2017/11/20.
 */

public class Select_number_item_model {
    String name;

    public String getId() {
        return id;
    }

    public Select_number_item_model(String name, String id) {

        this.name = name;
        this.id = id;
    }

    String id;

    public String getName() {
        return name;
    }

    public Select_number_item_model(String name) {

        this.name = name;
    }
}
