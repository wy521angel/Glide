package com.example.wy521angel.glidetest;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Bundle;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * Created by wy521angel on 2016/10/11.
 */
public class CustomChangeActivity extends Activity {


    private ImageView img1, img2, img3, img4;
    private String gifUrl = "http://imgsrc.baidu.com/forum/w%3D580/sign=4dda3791ea50352ab16125006343fb1a/43d29958d109b3deebabdc4ecfbf6c81800a4c1f.jpg";
    private String bitmapUrl = "http://image.xinmin.cn/2016/10/06/20161006015651414.jpg";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_change);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);

        Glide.with(this).load(R.drawable.gem0).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img1);
        Glide.with(this).load(gifUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).transform(new BlurTransformation(this)).into(img2);
        Glide.with(this).load(bitmapUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).bitmapTransform(new RoundTransform(this,6)).into(img3);
        Glide.with(this).load(bitmapUrl).diskCacheStrategy(DiskCacheStrategy.SOURCE).bitmapTransform(new CircleTransform(this), new BlurTransformation(this)).into(img4);
    }

    class BlurTransformation extends BitmapTransformation {

        private RenderScript rs;

        public BlurTransformation(Context context) {
            super(context);
            rs = RenderScript.create(context);
        }

        @SuppressLint("NewApi")
        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            Bitmap blurredBitmap = toTransform.copy(Bitmap.Config.ARGB_8888, true);

            // Allocate memory for Renderscript to work with
            Allocation input = Allocation.createFromBitmap(rs, blurredBitmap, Allocation.MipmapControl.MIPMAP_FULL, Allocation.USAGE_SHARED);
            Allocation output = Allocation.createTyped(rs, input.getType());

            // Load up an instance of the specific script that we want to use.
            ScriptIntrinsicBlur script = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));
            script.setInput(input);

            // Set the blur radius
            script.setRadius(10);

            // Start the ScriptIntrinisicBlur
            script.forEach(output);

            // Copy the output to the blurred bitmap
            output.copyTo(blurredBitmap);

            toTransform.recycle();

            return blurredBitmap;
        }

        @Override
        public String getId() {
            return "blur";
        }
    }

    class CircleTransform extends BitmapTransformation {

        public CircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            if (toTransform == null) return null;
            int size = Math.min(toTransform.getWidth(), toTransform.getHeight());
            int x = (toTransform.getWidth() - size) / 2;
            int y = (toTransform.getHeight() - size) / 2;

            Bitmap squaredBitmap = Bitmap.createBitmap(toTransform, x, y, size, size);

            //此处不要使用bitmap.getConfig()，而用Bitmap.Config.ARGB_8888，否则最后变成的圆形图片会有黑色背景
            Bitmap bitmap = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(squaredBitmap, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
//            if (x != 0 || y != 0) {
//                // source isn't square, move viewport to center
//                Matrix matrix = new Matrix();
//                matrix.setTranslate(-x, -y);
//                shader.setLocalMatrix(matrix);
//            }
            paint.setShader(shader);
            paint.setAntiAlias(true);

            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return bitmap;
        }

        @Override
        public String getId() {
            return "circle";
        }
    }

    class RoundTransform extends BitmapTransformation {

        private float radius = 0f;

        public RoundTransform(Context context) {
            this(context, 4);
        }

        public RoundTransform(Context context, int dp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * dp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            if (toTransform == null) return null;

            Bitmap bitmap = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
            if (bitmap == null) {
                bitmap = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            BitmapShader shader = new BitmapShader(toTransform, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP);
            paint.setShader(shader);
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, toTransform.getWidth(), toTransform.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return bitmap;
        }

        @Override
        public String getId() {
            return "round";
        }
    }
}















