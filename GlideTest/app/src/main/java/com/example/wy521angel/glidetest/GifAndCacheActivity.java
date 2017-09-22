package com.example.wy521angel.glidetest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.GlideDrawableImageViewTarget;

/**
 * Created by wy521angel on 2016/10/8.
 */
public class GifAndCacheActivity extends Activity {

    private ImageView img1, img2, img3, img4;
    private String gifUrl = "http://ww2.sinaimg.cn/mw690/60d02b59tw1ekqagnevolg209w05k1ky.gif";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gif_and_cache);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);

        Glide.with(this).load(gifUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img1);
        Glide.with(this).load(R.mipmap.ic_launcher).asGif().error(R.mipmap.gem).into(img2);
        Glide.with(this).load(gifUrl).asBitmap().error(R.mipmap.gem).into(img3);
        Glide.with(this).load(gifUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(new GlideDrawableImageViewTarget(img4, 2));
    }

}