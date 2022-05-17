package com.udit.bankexam.view;

import android.app.Activity;
import android.content.Context;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.udit.bankexam.R;

import java.lang.ref.WeakReference;

public class QianDaoPop {

    protected long lastClickTime;

    private View popView;

    private PopupWindow popWindow;

    private WeakReference<Context> weakReference;

    public QianDaoPop(Context paramContext) {
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

    public PopupWindow showPop(String day) {
        Context context = (Context) this.weakReference.get();
        if (context != null) {
            this.popView = LayoutInflater.from(context).inflate(R.layout.pop_layout_qiandao, null, false);
            ((RelativeLayout) this.popView.findViewById(R.id.rl_bg)).setOnClickListener(new View.OnClickListener() {
                public void onClick(View param1View) {
                    QianDaoPop.this.dismissPop();
                }
            });
            ((TextView) this.popView.findViewById(R.id.tv_day)).setText(day);
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
            changePopBlackBg(0.3F);
    }
}
