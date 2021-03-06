package com.udit.bankexam.view;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.udit.bankexam.R;

import java.lang.ref.WeakReference;

/**
 * Created by 孟晨 on 2018/8/22.
 */

public class CameraPop {

    private View popView;
    private PopupWindow popWindow;
    private WeakReference<Context> weakReference;
    private ClickCallback clickCallback;
    protected long lastClickTime;

    public CameraPop(Context baseActivity) {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
        weakReference = new WeakReference(baseActivity);
    }

    //显示弹窗
    public PopupWindow showPop(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
        final Context baseActivity = weakReference.get();
        if (baseActivity != null) {
            popView = LayoutInflater.from(baseActivity)
                    .inflate(R.layout.layout_select_camera, null, false);

            TextView tv_bg = (TextView) popView.findViewById(R.id.tv_bg);
            tv_bg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(CameraPop.this.clickCallback!=null){
                        dismissPop();
                    }
                }
            });

            TextView tv_cancel = (TextView) popView.findViewById(R.id.tv_cancel);
            tv_cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(CameraPop.this.clickCallback!=null){
                        dismissPop();
                    }
                }
            });

            TextView tv_take_pic = (TextView) popView.findViewById(R.id.tv_take_pic);
            tv_take_pic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(CameraPop.this.clickCallback!=null){
                        CameraPop.this.clickCallback.clickType(1);
                        dismissPop();
                    }
                }
            });

            TextView tv_xc = (TextView) popView.findViewById(R.id.tv_xc);
            tv_xc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(CameraPop.this.clickCallback!=null){
                        CameraPop.this.clickCallback.clickType(2);
                        dismissPop();
                    }
                }
            });

            popWindow = new PopupWindow(popView,
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT,//(int) (WindowParamUtils.screenHeight(baseActivity) * 0.9
                    true);
            popWindow.setAnimationStyle(R.style.popwin_anim_style);
            //点击外部，popupwindow会消失
            popWindow.setFocusable(false);
            popWindow.setOutsideTouchable(false);
//            popWindow.setBackgroundDrawable(new ColorDrawable(0xffffffff));
            popWindow.showAtLocation(popView, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
            showPopBlackBg(baseActivity);
        }
        return popWindow;
    }

    //----------------------------------------------背景颜色的改变-------------------------
    //初始化弹窗的背景色
    public void showPopBlackBg(Context baseActivity) {
        if (baseActivity != null) {
            changePopBlackBg(0.8F);//越大越浅
        }
    }

    //恢复弹窗的背景色
    public void backNormalPopBg() {
        Context baseActivity = weakReference.get();
        if (baseActivity != null) {
            WindowManager.LayoutParams lp = ((Activity)baseActivity).getWindow().getAttributes();
            lp.alpha = 1f;
            ((Activity)baseActivity).getWindow().setAttributes(lp);
        }
    }

    //弹窗背景颜色
    public void changePopBlackBg(float blackBg) {
        Context baseActivity = weakReference.get();
        if (baseActivity != null) {
            WindowManager.LayoutParams lp = ((Activity)baseActivity).getWindow().getAttributes();
            lp.alpha = blackBg;
            ((Activity)baseActivity).getWindow().setAttributes(lp);
        }
    }

    public void dismissPop() {
        if (popWindow.isShowing()) {
            popWindow.dismiss();
            popWindow = null;
            backNormalPopBg();
        }
    }


    protected boolean isFastDoubleClick() {
        long time = SystemClock.uptimeMillis();
        if (time - lastClickTime < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


    //-----------------------------------回调-----------------------------------
    public interface ClickCallback {
        void clickType(int type);
    }
}

