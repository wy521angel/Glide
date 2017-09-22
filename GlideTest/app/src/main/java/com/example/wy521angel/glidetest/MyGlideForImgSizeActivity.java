package com.example.wy521angel.glidetest;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by wy521angel on 2016/10/13.
 */
public class MyGlideForImgSizeActivity extends Activity {

    private ImageView img1, img2;
    private String imgUrl = "http://imgsrc.baidu.com/forum/w=580/sign=04681ea95cafa40f3cc6ced59b65038c/46353482b2b7d0a27b6cad06cdef76094a369a82.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_glide_img_size_rotate);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);

        CustomImageSizeModel customImageRequest = new CustomImageSizeModelFutureStudio(imgUrl);

        //使用下面静态的必须在AndroidManifest中将CustomImageSizeGlideModule打开
//        Glide.with(this).load(customImageRequest).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img1);

        Glide.with(this).using(new CustomImageSizeUrlLoader(this)).load(customImageRequest).into(img2);

    }

}