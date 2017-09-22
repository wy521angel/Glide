package com.example.wy521angel.glidetest;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.NotificationTarget;

/**
 * Created by wy521angel on 2016/10/8.
 */
public class NotificationActivity extends Activity {

    private static final int NOTIFICATION_FLAG = 1;
    private NotificationTarget notificationTarget;
    private String picUrl = "http://img.9ku.com/pic/gsxc/7/5571261.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ImageView img = new ImageButton(this);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        img.setLayoutParams(params);
        setContentView(img);

        final RemoteViews rv = new RemoteViews(this.getPackageName(), R.layout.custom_view_notification);
        rv.setImageViewResource(R.id.remoteview_notification_icon, R.mipmap.ic_launcher);
        rv.setTextViewText(R.id.remoteview_notification_headline, "Headline");
        rv.setTextViewText(R.id.remoteview_notification_short_message, "Short Message");

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker("有新短消息了")
                .setContentTitle("Content Title")
                .setContent(rv)
                .setPriority(NotificationCompat.PRIORITY_MIN);

        final Notification notification = mBuilder.build();
        if (Build.VERSION.SDK_INT >= 16) {
            notification.bigContentView = rv;
        }

        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_FLAG, notification);

        notificationTarget = new NotificationTarget(this, rv, R.id.remoteview_notification_icon, notification, NOTIFICATION_FLAG);
        Glide.with(getApplicationContext()).load(picUrl).asBitmap().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img);
    }
}