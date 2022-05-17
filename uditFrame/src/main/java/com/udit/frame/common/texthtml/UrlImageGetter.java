package com.udit.frame.common.texthtml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Message;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.utils.MyCommonUtils;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.Utils;

public class UrlImageGetter implements Html.ImageGetter {

    Context c;
    TextView container;
    int width ;
    private final String TAG = this.getClass().getSimpleName();
    /**
     *
     * @param t
     * @param c
     */
    public UrlImageGetter(TextView t, Context c) {
        this.c = c;
        this.container = t;
//        width = c.getResources().getDisplayMetrics().getW;
        width = MyCommonUtils.getscreen(c).widthPixels;
      //  width =Utils.px2dip(c,width);
    }

    Handler myHandler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            container.invalidate();

            container.setText(container.getText()); // 解决图文重叠

        }
    };

    private class MyThread extends  Thread
    {
        private Bitmap loadedImage;

        private  UrlDrawable urlDrawable;
        @Override
        public void run() {
            float scaleWidth = 0;
            // 计算缩放比例
            if(loadedImage.getWidth()>width)
            {
                MyLogUtils.e(TAG,"  缩略：1 _"+width+" :"+loadedImage.getWidth());
                scaleWidth =((float) width)/loadedImage.getWidth();
            }
            else if(loadedImage.getWidth()<150)
            {
                MyLogUtils.e(TAG,"  缩略：2 _"+width+" :"+loadedImage.getWidth());
                scaleWidth = ((float)150)/loadedImage.getWidth();
            }
            else
            {
                scaleWidth = ((float) width)/loadedImage.getWidth();
                if(scaleWidth>3.0f)
                {
                    scaleWidth = 3f;
                }
                else if(scaleWidth>2.5f)
                {
                    scaleWidth = 2.5f;
                }
                else if(scaleWidth>2.0f)
                {
                    scaleWidth = 2.0f;
                }
                else if(scaleWidth>1.5f)
                {
                    scaleWidth = 1.5f;
                }
               /* {
                    scaleWidth = 1;
                }
                else
                {
                    MyLogUtils.e(TAG,"  缩略：3 _"+width+" :"+loadedImage.getWidth());
                    scaleWidth = 2;
                }*/

            }

            MyLogUtils.e(TAG,"widthPixels:"+width+"  img-w:"+loadedImage.getWidth()+" h:"+loadedImage.getHeight()+"  scale:"+scaleWidth);
            // 取得想要缩放的matrix参数
            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth, scaleWidth);

            urlDrawable.bitmap  = Bitmap.createBitmap(loadedImage, 0, 0, loadedImage.getWidth(), loadedImage.getHeight(),
                    matrix,
                    true);

            //   urlDrawable.bitmap = loadedImage;
            urlDrawable.setBounds(0, 0, urlDrawable.bitmap.getWidth(), urlDrawable.bitmap.getHeight());
            myHandler.sendEmptyMessage(0);
            //          urlDrawable.bitmap = loadedImage;
//                urlDrawable.rect_old = rect;
            //    urlDrawable.widthscale = scaleWidth;

//                urlDrawable =

//            container.invalidate();
//            container.setText(container.getText()); // 解决图文重叠
        }
    };

    @Override
    public Drawable getDrawable(String source) {
        final UrlDrawable urlDrawable = new UrlDrawable();

        ImageLoader.getInstance().loadImage(source, BaseApplication.list_options,
                new SimpleImageLoadingListener() {
            @Override
            public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {

               MyThread myThread =  new MyThread();
                myThread.loadedImage = loadedImage;
                myThread.urlDrawable = urlDrawable;
                myThread.start();
              /*  if(loadedImage.isRecycled()==false)
                {
                    loadedImage.recycle();
                }*/
                //TODO 图片 挂掉
               // loadedImage.recycle();
            }
        });
        return urlDrawable;
    }



    @SuppressWarnings("deprecation")
    public class UrlDrawable extends BitmapDrawable {
        protected Bitmap bitmap;

       // public float widthscale;
        @Override
        public void draw(Canvas canvas) {
            // override the draw to facilitate refresh function later
            if (bitmap != null) {
              /*  Rect rect = new Rect (0,0,bitmap.getWidth(),bitmap.getHeight());
                Rect rect2 = new Rect (0,0,(int)(bitmap.getWidth()*widthscale),
                        (int)(bitmap.getHeight()*widthscale));*/
              canvas.drawBitmap(bitmap, 0, 0, getPaint());
              /*  canvas.drawBitmap(bitmap,rect,rect2,null);
                if(bitmap.isRecycled()==false)
                    bitmap.recycle();*/
            }
        }


    }
}
