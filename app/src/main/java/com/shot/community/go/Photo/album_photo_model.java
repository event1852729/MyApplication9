package com.shot.community.go.Photo;

/**
 * Created by user on 2017/11/17.
 */

public class album_photo_model {
    public album_photo_model(String image) {
        this.image = image;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    String image;
}
