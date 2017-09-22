package com.example.wy521angel.glidetest;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.TransformationUtils;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

import static android.graphics.Bitmap.createBitmap;

/**
 * Created by wy521angel on 2016/10/13.
 */
public class RotateImgActivity extends Activity {

    private ImageView img1, img2, img3, img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_rotate);

        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);


        Glide.with(this).load(R.mipmap.gem).diskCacheStrategy(DiskCacheStrategy.SOURCE).error(R.mipmap.ic_launcher).into(img1);
        Glide.with(this).load(R.mipmap.gem).bitmapTransform(new RotateTransformation1(this, 80f), new ClearBackGroundTransform(this)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img2);
        Glide.with(this).load(R.mipmap.gem).transform(new RotateTransformation2(this, 80f)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img3);
        Glide.with(this).load(R.mipmap.gem).transform(new RotateTransformation3(this, 80f)).diskCacheStrategy(DiskCacheStrategy.SOURCE).into(img4);

    }

    //方法1，网上常用的方法（利用Matrix旋转），有问题，不是90度倍数的角度会出现黑色背景,此处配合ClearBackGroundTransform 使用,
    // ClearBackGroundTransform中通过Bitmap.Config.ARGB_8888消除了黑色背景
    class RotateTransformation1 extends BitmapTransformation {

        private float rotateRotationAngle = 0f;

        public RotateTransformation1(Context context, float rotateRotationAngle) {
            super(context);
            this.rotateRotationAngle = rotateRotationAngle;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {

            if (toTransform == null) return null;

            Matrix matrix = new Matrix();
//            matrix.postRotate(rotateRotationAngle);
            matrix.setRotate(rotateRotationAngle);
            Bitmap newBM = createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, false);
//            return createBitmap(toTransform, 0, 0, toTransform.getWidth(), toTransform.getHeight(), matrix, true);
            if (newBM.equals(toTransform)) {
                return newBM;
            }
            toTransform.recycle();
            return newBM;
        }

        @Override
        public String getId() {
            return "rotate1" + rotateRotationAngle;
        }
    }

    class ClearBackGroundTransform extends BitmapTransformation {

        public ClearBackGroundTransform(Context context) {
            super(context);
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
            canvas.drawRect(rectF, paint);
            return bitmap;
        }

        @Override
        public String getId() {
            return "ClearBackGround";
        }
    }


    //方法2，有问题
    class RotateTransformation2 extends BitmapTransformation {

        private float rotateRotationAngle = 0f;

        public RotateTransformation2(Context context, float rotateRotationAngle) {
            super(context);
            this.rotateRotationAngle = rotateRotationAngle;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {

            if (toTransform == null) return null;

            Bitmap bitmap = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
            if (bitmap == null) {
                bitmap = createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
            }

            Canvas canvas = new Canvas(bitmap);
            Paint paint = new Paint();
            paint.setAntiAlias(true);

            Matrix matrix = new Matrix();
            matrix.setScale(0.5f, 0.5f, toTransform.getWidth() / 2, toTransform.getHeight() / 2);//缩放比例应该是多少？
            matrix.postRotate(rotateRotationAngle, toTransform.getWidth() / 2, toTransform.getHeight() / 2);

            canvas.drawBitmap(toTransform, matrix, paint);
            return bitmap;
        }

        @Override
        public String getId() {
            return "rotate2" + rotateRotationAngle;
        }
    }

    //方法3,有问题
    class RotateTransformation3 extends BitmapTransformation {

        private float rotateRotationAngle = 0f;

        public RotateTransformation3(Context context, float rotateRotationAngle) {
            super(context);
            this.rotateRotationAngle = rotateRotationAngle;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            int exifOrientationDegrees = getExifOrientationDegrees((int) rotateRotationAngle);
            return TransformationUtils.rotateImageExif(toTransform, pool, exifOrientationDegrees);
        }

        @Override
        public String getId() {
            return "rotate3" + rotateRotationAngle;
        }
    }

    private int getExifOrientationDegrees(int orientation) {
        int exifInt;
        switch (orientation) {
            case 90:
                exifInt = ExifInterface.ORIENTATION_ROTATE_90;
                break;
            //more cases
            default:
                exifInt = ExifInterface.ORIENTATION_NORMAL;
                break;
        }
        return exifInt;
    }
}