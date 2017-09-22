package com.example.wy521angel.glidetest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.FitCenter;

import jp.wasabeef.glide.transformations.BlurTransformation;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import jp.wasabeef.glide.transformations.CropTransformation;
import jp.wasabeef.glide.transformations.GrayscaleTransformation;
import jp.wasabeef.glide.transformations.MaskTransformation;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import jp.wasabeef.glide.transformations.gpu.SepiaFilterTransformation;
import jp.wasabeef.glide.transformations.gpu.ToonFilterTransformation;

/**
 * Created by wy521angel on 2016/10/11.
 */
public class ChangeSetActivity extends Activity {


    private ImageView img1, img2, img3, img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_set);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);

        Glide.with(this).load(R.drawable.demo).bitmapTransform(new CenterCrop(this), new GrayscaleTransformation(this), new MaskTransformation(this, R.drawable.mask_starfish)).into(img1);
        Glide.with(this).load(R.drawable.demo).bitmapTransform(new FitCenter(this), new ToonFilterTransformation(this), new CropCircleTransformation(this)).into(img2);
        Glide.with(this).load(R.drawable.check).bitmapTransform(new RoundedCornersTransformation(this, 30, 0), new SepiaFilterTransformation(this)).into(img3);
        Glide.with(this).load(R.drawable.check).bitmapTransform(new CropTransformation(this, 300, 100, CropTransformation.CropType.BOTTOM), new BlurTransformation(this)).into(img4);
    }
}

















