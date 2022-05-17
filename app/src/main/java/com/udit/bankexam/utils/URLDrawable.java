package com.udit.bankexam.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;

/**
 * Created by zb on 2017/5/31.
 */

public class URLDrawable extends BitmapDrawable {



    protected Bitmap bitmap;



    @Override
    public void draw(Canvas canvas) {

        if (bitmap != null) {

            canvas.drawBitmap(bitmap, 0, 0, getPaint());

        }

    }

}
