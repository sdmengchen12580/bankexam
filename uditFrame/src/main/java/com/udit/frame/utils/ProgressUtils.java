package com.udit.frame.utils;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.udit.frame.R;

/**
 * 
 * 进度条工具类
 * 
 * @author 曾宝
 * @version [V1.00, 2015年10月13日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class ProgressUtils
{
    public static ProgressDialog dialog = null;
    
    private static Handler MyHandle = new Handler()
    {
        public void handleMessage(android.os.Message msg)
        {
                stopProgressDlg();
        };
    };
    
    public static void showProgressDlg(String strMessage, Context context)
    {
        if (null == dialog)
        {
            dialog = new ProgressDialog(context);
            dialog.show();
            dialog.getWindow().setContentView(R.layout.layout_progress);
            dialog.setCancelable(false);
            ImageView img = (ImageView)dialog.findViewById(R.id.layout_progress_img);
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.animation_load);
            img.setAnimation(animation);
            animation.start();
            TextView text = (TextView)dialog.findViewById(R.id.layout_progress_text);
            text.setText(strMessage);
            MyHandle.sendEmptyMessageDelayed(-1, 30000);
        }
    }
    
    public static void showProgressDlg(int textId, Context context)
    {
        if (null == dialog)
        {
            dialog = new ProgressDialog(context);
            dialog.show();
            dialog.getWindow().setContentView(R.layout.layout_progress);
            dialog.setCancelable(false);
            ImageView img = (ImageView)dialog.findViewById(R.id.layout_progress_img);
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.animation_load);
            img.setAnimation(animation);
            animation.start();
            TextView text = (TextView)dialog.findViewById(R.id.layout_progress_text);
            text.setText(textId);
            MyHandle.sendEmptyMessageDelayed(-1, 10000);
        }
    }
    
    public static void stopProgressDlg()
    {
        if (null != dialog)
        {
            dialog.dismiss();
            dialog = null;
        }
    }

    public static void stopProgressDlgDelay() { MyHandle.postDelayed(new Runnable() {
        public void run() {
            if (ProgressUtils.dialog != null) {
                ProgressUtils.dialog.dismiss();
                ProgressUtils.dialog = null;
            }
        }
    },  500L); }
}
