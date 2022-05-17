package com.udit.bankexam.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.NonViewAware;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.udit.frame.utils.ImageFactory;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;

/**
 * Created by zb on 2017/5/31.
 */

public class URLImageGetter implements Html.ImageGetter {

    private final String TAG = this.getClass().getSimpleName();

    private String shopDeString;

    private TextView textView;

    Context context;

    private DisplayImageOptions options;

    public URLImageGetter(String shopDeString, Context context, TextView textView, DisplayImageOptions options) {

        this.shopDeString = shopDeString;

        this.context = context;

        this.textView = textView;

        this.options = options;

    }

    @Override

    public Drawable getDrawable(String source) {


        final URLDrawable urlDrawable = new URLDrawable();

       ImageSize imageSize = new ImageSize(ViewGroup.MarginLayoutParams.WRAP_CONTENT,ViewGroup.MarginLayoutParams.WRAP_CONTENT);

        NonViewAware nonViewAware = new NonViewAware(imageSize, ViewScaleType.CROP);

        ImageLoader.getInstance().displayImage(source, nonViewAware, options,
                new SimpleImageLoadingListener() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

                super.onLoadingComplete(imageUri, view, loadedImage);

               // urlDrawable.bitmap = loadedImage;
                urlDrawable.bitmap =  ImageFactory.ratioBig(loadedImage,2);
                MyLogUtils.e(TAG,"img:"+loadedImage.getWidth() +"  "+loadedImage.getHeight());
               /* if(loadedImage.getWidth()<500)
                {
                    loadedImage.setWidth(500);
                }
                if(loadedImage.getHeight()<200)
                {
                    loadedImage.setHeight(200);
                }*/

                urlDrawable.setBounds(0, 0, loadedImage.getWidth(), loadedImage.getHeight());
               // textView.getViewTreeObserver().addOnGlobalLayoutListener();
                textView.invalidate();

                textView.setText(textView.getText()); // 解决图文重叠

            }

        });

        /*ImageLoader.getInstance().loadImage(source,options, new SimpleImageLoadingListener() {

            @Override

            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {



            }

        });*/

        return urlDrawable;

    }

}
