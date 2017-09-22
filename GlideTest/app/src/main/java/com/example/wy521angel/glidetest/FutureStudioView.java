package com.example.wy521angel.glidetest;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wy521angel on 2016/10/8.
 */

public class FutureStudioView extends FrameLayout {
    private ImageView iv;
    private TextView tv;

    public void init(Context context) {
        inflate(context, R.layout.custom_view_futurestudio, this);

        iv = (ImageView) findViewById(R.id.custom_view_image);
        tv = (TextView) findViewById(R.id.custom_view_text);
    }

    public FutureStudioView(Context context) {
        this(context, null);
    }

    public FutureStudioView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FutureStudioView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setImage(Drawable drawable) {
        iv.setImageDrawable(drawable);
    }

    public void setAlpha(float alpha) {
        iv.setAlpha(alpha);
    }

    public void setRotation(float rotation) {
        iv.setRotation(rotation);
    }

    public View getImageView(){
        return ((ViewGroup)getChildAt(0)).getChildAt(0);
    }
}
