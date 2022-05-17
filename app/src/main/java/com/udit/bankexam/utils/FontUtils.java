package com.udit.bankexam.utils;

import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by zb on 2017/6/9.
 */

public class FontUtils {

    public static final String DEF_FONT = "Yahei.ttc";

    public static  Typeface typeface ;

    public static void  init(Context mContext)
    {
        typeface  =  Typeface.createFromAsset(mContext.getAssets(),
                DEF_FONT);
    }



    public static final void injectFont(View rootView) {
        if(typeface!=null)
            injectFont(rootView, typeface);
    }

    private static void injectFont(View rootView, Typeface typeface) {
        if (rootView instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) rootView;
            int childViewCount = viewGroup.getChildCount();
            for (int i = 0; i < childViewCount; i++) {
                injectFont(viewGroup.getChildAt(i), typeface);
            }
        } else if (rootView instanceof TextView) {
            ((TextView) rootView).setTypeface(typeface);
        }
    }


}
