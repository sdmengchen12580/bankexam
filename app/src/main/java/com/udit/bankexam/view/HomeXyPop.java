package com.udit.bankexam.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.udit.bankexam.R;
import com.udit.bankexam.ui.newui.UserXyActivity;
import com.udit.bankexam.ui.newui.YinSiActivity;
import com.udit.bankexam.utils.FastClickUtils;

import java.lang.ref.WeakReference;

public class HomeXyPop {

    private View popView;
    private View shareView = null;
    private PopupWindow popWindow;
    private WeakReference<Context> weakReference;
    private ClickCallback clickCallback;

    public HomeXyPop(Context baseActivity) {
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
            popView = LayoutInflater.from(baseActivity).inflate(R.layout.pop_xy_home, null, false);

            //协议内容
            TextView tv_xy_content = popView.findViewById(R.id.tv_xy_content);

            SpannableString spanString1 = new SpannableString("《银行易考隐私保护指导》");
            spanString1.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(baseActivity, YinSiActivity.class);
                    baseActivity.startActivity(intent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setUnderlineText(false);
                    ds.setColor(baseActivity.getResources().getColor(R.color.center_bottom_sb_pg_color_blue));//设置颜色
                }
            }, 0, spanString1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);


            SpannableString spanString2 = new SpannableString("《用户协议》");
            spanString2.setSpan(new ClickableSpan() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.setClass(baseActivity, UserXyActivity.class);
                    baseActivity.startActivity(intent);
                }

                @Override
                public void updateDrawState(TextPaint ds) {
                    ds.setUnderlineText(false);
                    ds.setColor(baseActivity.getResources().getColor(R.color.center_bottom_sb_pg_color_blue));//设置颜色
                }
            }, 0, spanString2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

            tv_xy_content.setText("亲爱的用户：\n     感谢您使用银行易考APP！为了更好的保障您的个人权益，在使用我们的产品之前，请您务必认真阅读、充分理解");
            tv_xy_content.append(spanString1);
            tv_xy_content.append(new SpannableString("和"));
            tv_xy_content.append(spanString2);
            tv_xy_content.append(new SpannableString("各种条款。" +
                    "\n     电话：在您注册本产品帐号时，我们会收集您的手机号作为唯一登录ID" +
                    "\n     存储：用户读写文件，以便您保存和发布图片、视频等内容。" +
                    "\n     为了更好的向您提供服务，在本产品自动接收并记录的您手机上的信息,包括但不限于您的IP地址、手机类型、使用的语言、访问日期和时间、软硬件特征信息及您需求的网页记录等数据;" +
                    "我们尊重您的选择权，您可以访问、更正、删除您的个人信息，我们也会为提供注销、投诉渠道！" +
                    "如您同意，请点击“同意”开始接受我们的服务。"
            ));
            tv_xy_content.setMovementMethod(LinkMovementMethod.getInstance());

            TextView tv_agree = popView.findViewById(R.id.tv_agree);
            tv_agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (FastClickUtils.isFastClick()) {
                        return;
                    }
                    if (HomeXyPop.this.clickCallback != null) {
                        HomeXyPop.this.clickCallback.clickTrue();
                        dismissPop();
                    }
                }
            });

            TextView tv_not_agree = popView.findViewById(R.id.tv_not_agree);
            tv_not_agree.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (FastClickUtils.isFastClick()) {
                        return;
                    }
                    if (HomeXyPop.this.clickCallback != null) {
                        HomeXyPop.this.clickCallback.clickFalse();
                        if (popWindow.isShowing()) {
                            popWindow.dismiss();
                            popWindow = null;
                        }
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
            WindowManager.LayoutParams lp = ((Activity) baseActivity).getWindow().getAttributes();
            lp.alpha = 1f;
            ((Activity) baseActivity).getWindow().setAttributes(lp);
        }
    }

    //弹窗背景颜色
    public void changePopBlackBg(float blackBg) {
        Context baseActivity = weakReference.get();
        if (baseActivity != null) {
            WindowManager.LayoutParams lp = ((Activity) baseActivity).getWindow().getAttributes();
            lp.alpha = blackBg;
            ((Activity) baseActivity).getWindow().setAttributes(lp);
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
