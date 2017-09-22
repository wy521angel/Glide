package com.example.wy521angel.glidetest;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.File;

public class LoadAndShowActivity extends Activity {

    private ImageView img1, img2, img3, img4;
    private String picUrl = "http://imgsrc.baidu.com/forum/pic/item/15ddae19ebc4b7459a0e6065c8fc1e178b8215b7.jpg";
    public static final String ANDROID_RESOURCE = "android.resource://";
    public static final String FOREWARD_SLASH = "/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_and_show);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);

        Glide.with(this).load(picUrl).centerCrop().into(img1);
        Glide.with(this).load(R.mipmap.gem).fitCenter().into(img2);

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(Environment.getExternalStorageDirectory(), "gem.jpg");
            Glide.with(this).load(file).centerCrop().into(img3);
        }

        Uri uri = resourceIdToUri(this, R.mipmap.gem);
        Glide.with(this).load(uri).centerCrop().override(500, 500).into(img4);
    }

    private Uri resourceIdToUri(Context context, int resourceId) {
        return Uri.parse(ANDROID_RESOURCE + context.getPackageName() + FOREWARD_SLASH + resourceId);
    }
}
