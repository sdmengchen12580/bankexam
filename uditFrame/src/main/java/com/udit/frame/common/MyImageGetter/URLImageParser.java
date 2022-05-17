package com.udit.frame.common.MyImageGetter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.Html;
import android.view.Display;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.udit.frame.utils.MyLogUtils;

/**
 * Created by zb on 2017/4/27.
 */

public class URLImageParser implements Html.ImageGetter {

    private final String TAG = this.getClass().getSimpleName();

    TextView mTextView;


    public URLImageParser(TextView textView) {
        this.mTextView = textView;
    }

    @Override
    public Drawable getDrawable(String source) {
        final URLDrawable urlDrawable = new URLDrawable();
        MyLogUtils.e(TAG,source);
        ImageLoader.getInstance().loadImage( source,
                new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                urlDrawable.bitmap = loadedImage;
                int width = 100;
                if(loadedImage.getWidth()>100)
                {
                    width = 100;
                }
                else
                    width = loadedImage.getWidth();

                int height = 50;
                if(loadedImage.getHeight()>50)
                {
                    height = 50;
                }
                else
                    height = loadedImage.getHeight();


                urlDrawable.setBounds(0, 0, width, height);
                mTextView.invalidate();
                mTextView.setText(mTextView.getText()); // 解决图文重叠
            }
        });


        return urlDrawable;

    }


    public class URLDrawable extends BitmapDrawable {
        protected Bitmap bitmap;

        @Override
        public void draw(Canvas canvas) {
            if (bitmap != null) {
                canvas.drawBitmap(bitmap, 0, 0, getPaint());
            }
        }
    }

}
