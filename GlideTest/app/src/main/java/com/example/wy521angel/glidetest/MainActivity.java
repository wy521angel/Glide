package com.example.wy521angel.glidetest;

import android.Manifest;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends ListActivity {

    private String[] titles = new String[]{
            "图片的加载显示与缩放",
            "播放Gif与图片缓存",
            "请求优先级",
            "缩略图（网络状态不是很好的情况下效果显著）",
            "获取Bitmap和在自定义View中加载图片（SimpleTarget和ViewTarget的使用）",
            "加载图片到通知栏（NotificationTarget的使用）",
            "窗口小部件（AppWidgetTarget的使用）",
            "自定义变换",
            "用animate()定制动画",
            "整合网络协议栈",
            "定制Glide",
            "旋转图片"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PermissionUtil.chekPermissions(this, Build.VERSION_CODES.M, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
            case 0:
                startActivity(new Intent(this, LoadAndShowActivity.class));
                break;
            case 1:
                startActivity(new Intent(this, GifAndCacheActivity.class));
                break;
            case 2:
                startActivity(new Intent(this, PriorityTestActivity.class));
                break;
            case 3:
                startActivity(new Intent(this, ThumbnailTestActivity.class));
                break;
            case 4:
                startActivity(new Intent(this, SimpleAndViewActivity.class));
                break;
            case 5:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case 6:
                Toast.makeText(this, "请查看手机的窗口小部件", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                startActivity(new Intent(this, ChangeActivity.class));
                break;
            case 8:
                startActivity(new Intent(this, AnimationActivity.class));
                break;
            case 9:
                Toast.makeText(this, "已经整合 OkHttp 2", Toast.LENGTH_SHORT).show();
                break;
            case 10:
                startActivity(new Intent(this, MyGlideActivity.class));
                break;
            case 11:
                startActivity(new Intent(this, RotateImgActivity.class));
                break;
        }
    }
}
