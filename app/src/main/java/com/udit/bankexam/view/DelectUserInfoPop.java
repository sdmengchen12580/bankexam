package com.udit.bankexam.view;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.utils.FastClickUtils;

import java.lang.ref.WeakReference;

public class DelectUserInfoPop {

    private View popView;
    private View shareView = null;
    private PopupWindow popWindow;
    private WeakReference<Context> weakReference;
    private ClickCallback clickCallback;

    public DelectUserInfoPop(Context baseActivity) {
        if (weakReference != null) {
            weakReference.clear();
            weakReference = null;
        }
        weakReference = new WeakReference(baseActivity);
    }

    //显示弹窗
    public PopupWindow showPop(ClickCallback clickCallback) {
        this.clickCallback = clickCallback;
        Context baseActivity = weakReference.get();
        if (baseActivity != null) {
            popView = LayoutInflater.from(baseActivity).inflate(R.layout.pop_delectuser_info, null, false);

            TextView tv_agree = popView.findViewById(R.id.tv_agree);
            tv_agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(FastClickUtils.isFastClick()){
                        return;
                    }
                    if (DelectUserInfoPop.this.clickCallback != null) {
                        DelectUserInfoPop.this.clickCallback.clickTrue();
                        dismissPop();
                    }
                }
            });

            TextView tv_not_agree = popView.findViewById(R.id.tv_not_agree);
            tv_not_agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(FastClickUtils.isFastClick()){
                        return;
                    }
                    if (DelectUserInfoPop.this.clickCallback != null) {
                        DelectUserInfoPop.this.clickCallback.clickFalse();
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

    //----------------------------------------------背景颜色的改变----------------------------------
    //初始化弹窗的背景色
    public void showPopBlackBg(Context baseActivity) {
        if (baseActivity != null) {
            changePopBlackBg(0.9F);//越大越浅
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


    //-----------------------------------回调-----------------------------------
    public interface ClickCallback {
        void clickTrue();

        void clickFalse();
    }
}
