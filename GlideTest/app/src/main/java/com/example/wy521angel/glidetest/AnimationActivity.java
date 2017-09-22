package com.example.wy521angel.glidetest;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;

/**
 * Created by wy521angel on 2016/10/12.
 */
public class AnimationActivity extends Activity {

    private ImageView img1, img2, img3;
    private String bitmapUrl1 = "http://pic.baike.soso.com/p/20140106/20140106153048-268202982.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);

        Glide.with(this).load(bitmapUrl1).diskCacheStrategy(DiskCacheStrategy.SOURCE).animate(android.R.anim.slide_in_left).into(img1);
        Glide.with(this).load(bitmapUrl1).diskCacheStrategy(DiskCacheStrategy.SOURCE).animate(R.anim.zoom_in).into(img2);

        ViewPropertyAnimation.Animator animator = new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
                // if it's a custom view class, cast it here
                // then find subviews and do the animations
                // here, we just use the entire view for the fade animation
//                view.setAlpha(0f);
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(view, "alpha", 0f, 1f);
                fadeAnim.setDuration(5000);
                fadeAnim.start();
            }
        };

        Glide.with(this).load(bitmapUrl1).diskCacheStrategy(DiskCacheStrategy.SOURCE).animate(animator).into(img3);

        findViewById(R.id.intoCustom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AnimationActivity.this, AnimationForCustomViewActivity.class));
            }
        });

    }
}