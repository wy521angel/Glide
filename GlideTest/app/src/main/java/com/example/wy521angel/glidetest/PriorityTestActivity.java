package com.example.wy521angel.glidetest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

/**
 * Created by wy521angel on 2016/10/8.
 */
public class PriorityTestActivity extends Activity {

    private ImageView img1, img2, img3;
    private String picUrl = "http://www.akjunshi.com/upload/20161006/14756902391153.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priority);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);

        Glide.with(this).load(picUrl).priority(Priority.HIGH).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(img1);
        Glide.with(this).load(picUrl).priority(Priority.LOW).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(img2);
        Glide.with(this).load(picUrl).priority(Priority.LOW).skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).into(img3);
    }
}
