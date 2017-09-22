package com.example.wy521angel.glidetest;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by wy521angel on 2016/10/8.
 */
public class SimpleAndViewActivity extends Activity {

    private ImageView img1;
    private FutureStudioView customView;
    private String picUrl = "http://a.hiphotos.baidu.com/zhidao/pic/item/72f082025aafa40fc31b9386aa64034f79f0198f.jpg";
    private String picUrl2 = "http://img3.duitang.com/uploads/item/201502/24/20150224100507_82En2.jpeg";

    private SimpleTarget target1;
    private ViewTarget target2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_and_view);

        img1 = (ImageView) findViewById(R.id.img1);
        customView = (FutureStudioView) findViewById(R.id.custom_view);
        target1 = new SimpleTarget<Bitmap>(300, 300) {

            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                img1.setImageBitmap(resource);
            }
        };

        Glide.with(this).load(picUrl).asBitmap().into(target1);

        target2 = new ViewTarget<FutureStudioView, GlideDrawable>(customView) {

            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImage(resource.getCurrent());
            }
        };

        Glide.with(getApplicationContext()).load(picUrl2).into(target2);
    }
}