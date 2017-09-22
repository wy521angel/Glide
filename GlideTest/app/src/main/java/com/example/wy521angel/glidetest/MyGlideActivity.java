package com.example.wy521angel.glidetest;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by wy521angel on 2016/10/13.
 */
public class MyGlideActivity extends ListActivity {

    private String[] titles = new String[]{
            "提升Glide的图片质量",
            "接受自签名HTTPS证书",
            "自定义缓存",
            "请求特定尺寸图片",
            "动态使用 Model Loaders",
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, titles);
        setListAdapter(adapter);
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        switch (position) {
            case 0:
            case 2:
                Toast.makeText(this, "请查看SimpleGlideModule", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                Toast.makeText(this, "请查看UnsafeOkHttpGlideModule", Toast.LENGTH_SHORT).show();
                break;
            case 3:
            case 4:
                startActivity(new Intent(this, MyGlideForImgSizeActivity.class));
                break;
        }
    }
}