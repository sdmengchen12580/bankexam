package com.udit.frame.common.texthtml;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.annotation.DrawableRes;
import android.text.style.ImageSpan;
import android.view.View;

/**
 * Created by zb on 2017/6/19.
 */

public class FileImageSpan extends ImageSpan implements View.OnClickListener {


    public FileImageSpan(Bitmap b) {
        super(b);
    }

    public FileImageSpan(Bitmap b, int verticalAlignment) {
        super(b, verticalAlignment);
    }

    public FileImageSpan(Context context, Bitmap b) {
        super(context, b);
    }

    public FileImageSpan(Context context, Bitmap b, int verticalAlignment) {
        super(context, b, verticalAlignment);
    }

    public FileImageSpan(Drawable d) {
        super(d);
    }

    public FileImageSpan(Drawable d, int verticalAlignment) {
        super(d, verticalAlignment);
    }

    public FileImageSpan(Drawable d, String source) {
        super(d, source);
    }

    public FileImageSpan(Drawable d, String source, int verticalAlignment) {
        super(d, source, verticalAlignment);
    }

    public FileImageSpan(Context context, Uri uri) {
        super(context, uri);
    }

    public FileImageSpan(Context context, Uri uri, int verticalAlignment) {
        super(context, uri, verticalAlignment);
    }

    public FileImageSpan(Context context, @DrawableRes int resourceId) {
        super(context, resourceId);
    }

    public FileImageSpan(Context context, @DrawableRes int resourceId, int verticalAlignment) {
        super(context, resourceId, verticalAlignment);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        Drawable b = getDrawable();
        canvas.save();
        int transY = bottom - b.getBounds().bottom ;//- Utils.dip2px(WApplication.cFontLineSpacingExtra);
        if (mVerticalAlignment == ALIGN_BASELINE) {
            int textLength = text.length();
            for (int i = 0; i < textLength; i++) {
                if (Character.isLetterOrDigit(text.charAt(i))) {
                    transY -= paint.getFontMetricsInt().descent;
                    break;
                }
            }
        }
        canvas.translate(x, transY);
        canvas.restore();
    }
}
