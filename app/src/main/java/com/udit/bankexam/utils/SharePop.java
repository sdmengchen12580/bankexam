package com.udit.bankexam.utils;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.udit.bankexam.R;

import java.lang.ref.WeakReference;

public class SharePop {
    private ClickCallback clickCallback;

    protected long lastClickTime;

    private View popView;

    private PopupWindow popWindow;

    private WeakReference<Context> weakReference;

    public SharePop(Context paramContext) {
        if (this.weakReference != null) {
            this.weakReference.clear();
            this.weakReference = null;
        }
        this.weakReference = new WeakReference(paramContext);
    }

    public void backNormalPopBg() {
        Context context = (Context) this.weakReference.get();
        if (context != null) {
            Activity activity = (Activity) context;
            WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
            layoutParams.alpha = 1.0F;
            activity.getWindow().setAttributes(layoutParams);
        }
    }

    public void changePopBlackBg(float paramFloat) {
        Context context = (Context) this.weakReference.get();
        if (context != null) {
            Activity activity = (Activity) context;
            WindowManager.LayoutParams layoutParams = activity.getWindow().getAttributes();
            layoutParams.alpha = paramFloat;
            activity.getWindow().setAttributes(layoutParams);
        }
    }

    public void dismissPop() {
        if (this.popWindow.isShowing()) {
            this.popWindow.dismiss();
            this.popWindow = null;
            backNormalPopBg();
        }
    }

    protected boolean isFastDoubleClick() {
        long l = SystemClock.uptimeMillis();
        if (l - this.lastClickTime < 500L)
            return true;
        this.lastClickTime = l;
        return false;
    }

    public PopupWindow showPop(ClickCallback paramClickCallback) {
        this.clickCallback = paramClickCallback;
        Context context = (Context) this.weakReference.get();
        if (context != null) {
            this.popView = LayoutInflater.from(context).inflate(R.layout.layout_share, null, false);
            TextView textView = (TextView) this.popView.findViewById(R.id.tv_bg);
            ImageView imageView = (ImageView) this.popView.findViewById(R.id.img_back);
            textView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    if (SharePop.this.clickCallback != null)
                        SharePop.this.dismissPop();
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    if (SharePop.this.clickCallback != null)
                        SharePop.this.dismissPop();
                }
            });
            LinearLayout linearLayout1 = (LinearLayout) this.popView.findViewById(R.id.ll_wx);
            LinearLayout linearLayout2 = (LinearLayout) this.popView.findViewById(R.id.ll_friend);
            LinearLayout linearLayout3 = (LinearLayout) this.popView.findViewById(R.id.ll_qq);
            LinearLayout linearLayout4 = (LinearLayout) this.popView.findViewById(R.id.ll_qq_zone);
            linearLayout1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    if (FastClickUtils.isFastClick())
                        return;
                    if (SharePop.this.clickCallback != null) {
                        SharePop.this.dismissPop();
                        SharePop.this.clickCallback.clickType(0);
                    }
                }
            });
            linearLayout2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    if (FastClickUtils.isFastClick())
                        return;
                    SharePop.this.clickCallback.clickType(1);
                }
            });
            linearLayout3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    if (FastClickUtils.isFastClick())
                        return;
                    SharePop.this.clickCallback.clickType(2);
                }
            });
            linearLayout4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    if (FastClickUtils.isFastClick())
                        return;
                    SharePop.this.clickCallback.clickType(3);
                }
            });
            this.popWindow = new PopupWindow(this.popView, -1, -1, true);
            this.popWindow.setAnimationStyle(R.style.popwin_anim_style);
            this.popWindow.setFocusable(false);
            this.popWindow.setOutsideTouchable(false);
            this.popWindow.showAtLocation(this.popView, 81, 0, 0);
            showPopBlackBg(context);
        }
        return this.popWindow;
    }

    public void showPopBlackBg(Context paramContext) {
        if (paramContext != null)
            changePopBlackBg(0.8F);
    }

    public static interface ClickCallback {
        void clickType(int param1Int);
    }
}
