package com.example.wy521angel.glidetest;

/**
 * Created by wy521angel on 2016/10/13.
 */

public class CustomImageSizeModelFutureStudio implements CustomImageSizeModel {

    String baseImageUrl;

    public CustomImageSizeModelFutureStudio(String baseImageUrl) {
        this.baseImageUrl = baseImageUrl;
    }

    @Override
    public String requestCustomSizeUrl(int width, int height) {
        // previous way: we directly accessed the images
        // https://futurestud.io/images/logo.png

        // new way, server could handle additional parameter and provide the image in a specific size
        // in this case, the server would serve the image in 400x300 pixel size
        // https://futurestud.io/images/logo.png?w=400&h=300
        return baseImageUrl + "?w=" + width + "&h=" + height;
    }
}
