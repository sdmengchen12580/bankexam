package com.udit.frame.common.imageview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

@SuppressLint("AppCompatCustomView")
public class MyImageView extends ImageView
{

    public MyImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width=getResources().getDisplayMetrics().widthPixels;
        int height=width / 2;
        setMeasuredDimension(width, height);
    }
    
    
}
