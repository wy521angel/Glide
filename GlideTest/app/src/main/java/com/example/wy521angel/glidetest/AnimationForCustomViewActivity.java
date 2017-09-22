package com.example.wy521angel.glidetest;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.animation.ViewPropertyAnimation;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.target.ViewTarget;

/**
 * Created by wy521angel on 2016/10/12.
 */
public class AnimationForCustomViewActivity extends Activity {

    private FutureStudioView customView1, customView2, customView3, customView4;
    private Context context;
    private float rotate = 0;
    private String bitmapUrl1 = "http://pic.baike.soso.com/p/20140106/20140106153048-268202982.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_for_customview);
        context = this;
        customView1 = (FutureStudioView) findViewById(R.id.custom_view1);
        customView2 = (FutureStudioView) findViewById(R.id.custom_view2);
        customView3 = (FutureStudioView) findViewById(R.id.custom_view3);
        customView4 = (FutureStudioView) findViewById(R.id.custom_view4);

        //TODO 使用ViewPropertyAnimation有问题，暂未解决 下面换另外的方法
        ViewPropertyAnimation.Animator animator1 = new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
                ImageView imgView;
                if ((((ViewGroup) ((ViewGroup) view).getChildAt(0))).getChildAt(0)
                        instanceof ImageView) {
                    imgView = (ImageView) ((ViewGroup) view).getChildAt(0);
                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                    return;
                }
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(imgView, "rotation", 0f, 360f);
                fadeAnim.setDuration(5000);
                fadeAnim.start();
            }
        };

        ViewTarget target = new ViewTarget<FutureStudioView, GlideDrawable>(customView1) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImage(resource.getCurrent());
            }
        };

        RequestListener listener = new RequestListener() {
            @Override
            public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
                e.printStackTrace();
                return false;
            }

            @Override
            public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
                return false;
            }
        };

        Glide.with(this).load(bitmapUrl1).diskCacheStrategy(DiskCacheStrategy.SOURCE).animate(animator1).listener(listener).into(target);


        //方法1：
        final ValueAnimator rotateAnim = ValueAnimator.ofFloat(0f, 360f);
        ViewTarget target2 = new ViewTarget<FutureStudioView, GlideDrawable>(customView2) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImage(resource.getCurrent());
                rotateAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        rotate = (float) animation.getAnimatedValue();
                        view.setRotation(rotate);
                    }
                });
                rotateAnim.setDuration(5000);
                rotateAnim.start();
            }
        };

        Glide.with(this).load(bitmapUrl1).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(listener).into(target2);

        //方法2：
        final ObjectAnimator rotateAnim2 = ObjectAnimator.ofFloat(customView3.findViewById(R.id.custom_view_image), "Rotation", 0f, 360f);
        rotateAnim2.setDuration(5000);
        ViewTarget target3 = new ViewTarget<FutureStudioView, GlideDrawable>(customView3) {
            @Override
            public void onResourceReady(GlideDrawable resource, GlideAnimation<? super GlideDrawable> glideAnimation) {
                this.view.setImage(resource.getCurrent());
                rotateAnim2.start();
            }
        };

        Glide.with(this).load(bitmapUrl1).diskCacheStrategy(DiskCacheStrategy.SOURCE).listener(listener).into(target3);

        //方法3：
        ViewPropertyAnimation.Animator animator2 = new ViewPropertyAnimation.Animator() {
            @Override
            public void animate(View view) {
                ImageView imgView;
                if (view instanceof ImageView) {
                    imgView = (ImageView) view;
                } else {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show();
                    return;
                }
                ObjectAnimator fadeAnim = ObjectAnimator.ofFloat(imgView, "rotation", 0f, 360f);
                fadeAnim.setDuration(5000);
                fadeAnim.start();
            }
        };
        Glide.with(this).load(bitmapUrl1).diskCacheStrategy(DiskCacheStrategy.SOURCE).animate(animator2).listener(listener).into((ImageView) customView4.findViewById(R.id.custom_view_image));
    }
}