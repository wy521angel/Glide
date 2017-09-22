package com.example.wy521angel.glidetest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.Glide;

/**
 * Created by wy521angel on 2016/10/8.
 */
public class ThumbnailTestActivity extends Activity {

    private ImageView img1, img2;
    private String picUrl = "http://img1.imgtn.bdimg.com/it/u=2427962220,3230564531&fm=21&gp=0.jpg";
    private String picUrl2 = "http://www.86kx.com/uploads/allimg/140708/2291-140FR141070-L.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thumbnail);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);

        Glide.with(this).load(picUrl).thumbnail(0.1f).into(img1);

        DrawableRequestBuilder<String> thumbnailRequest = Glide.with(this).load(picUrl);
        Glide.with(this).load(picUrl2).thumbnail(thumbnailRequest).into(img2);
    }
}
