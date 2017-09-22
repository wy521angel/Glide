package com.example.wy521angel.glidetest;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.bitmap_recycle.LruBitmapPool;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.load.engine.cache.MemorySizeCalculator;
import com.bumptech.glide.load.model.GlideUrl;
import com.bumptech.glide.module.GlideModule;

import java.io.InputStream;

/**
 * Created by wy521angel on 2016/10/13.
 */

public class SimpleGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        //提升Glide的图片质量
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);

        //自定义内存缓存,比默认多10%的缓存
        MemorySizeCalculator calculator = new MemorySizeCalculator(context);
        int defaultMemoryCacheSize = calculator.getMemoryCacheSize();
        int defaultBitmapPoolSize = calculator.getBitmapPoolSize();

        int customMemoryCacheSize = (int) (1.1 * defaultMemoryCacheSize);
        int customBitmapPoolSize = (int) (1.1 * defaultBitmapPoolSize);

        builder.setMemoryCache(new LruResourceCache(customMemoryCacheSize));
        builder.setBitmapPool(new LruBitmapPool(customBitmapPoolSize));

        //自定义磁盘缓存
        // set size & external vs. internal
//        int cacheSize100MegaBytes = 104857600;
//        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));//设置磁盘缓存到app的内部路径，并设置最大值到100M
//
//        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context, cacheSize100MegaBytes));//设置磁盘缓存到外部路径
//        String downloadDirectoryPath = Environment.getDownloadCacheDirectory().getPath();
//        builder.setDiskCache(new DiskLruCacheFactory(downloadDirectoryPath, cacheSize100MegaBytes));//移动磁盘缓存到某个特定的位置

        //In case you want to specify a cache sub folder (i.e. "glidecache"):
//        builder.setDiskCache(
//                new DiskLruCacheFactory(downloadDirectoryPath, "glidecache", cacheSize100MegaBytes)//创建并使用一个在你传递路径里的路径。通常，最后一个参数是byte级缓存的大小。
//        );
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
