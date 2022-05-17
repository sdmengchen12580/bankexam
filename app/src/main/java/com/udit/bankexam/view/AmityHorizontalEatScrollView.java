package com.udit.bankexam.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.HorizontalScrollView;

import com.udit.bankexam.utils.DensityUtil;

public class AmityHorizontalEatScrollView extends HorizontalScrollView {

    public interface ScrollViewListener {
        void onScrollChanged(AmityHorizontalEatScrollView scrollView, int x, int y, int oldx, int oldy);
    }

    private ScrollViewListener scrollViewListener = null;
    private Context context;
    private int dp55, dp70, dp72;
    private int screenWidth;

    public AmityHorizontalEatScrollView(Context context) {
        super(context);
        this.context = context;
    }

    public AmityHorizontalEatScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.context = context;
    }

    public AmityHorizontalEatScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public void setData() {
        dp55 = DensityUtil.dip2px(context, 55);
        screenWidth = getallWidth(((Activity) context));
    }

    //屏幕宽度
    public static int getallWidth(Activity mContext) {
        return mContext.getWindow().getWindowManager().getDefaultDisplay().getWidth();
    }

    //屏幕高度
    public static int getallHight(Activity mContext) {
        return mContext.getWindow().getWindowManager().getDefaultDisplay().getHeight();
    }

    public void onClicked(View view) {
        smoothScrollTo(view.getLeft() - (screenWidth / 2 - dp55 / 2), 0);
    }

    public void initLeft(int lon) {
        smoothScrollTo((lon + 1) * dp55 - (screenWidth / 2 - dp55 / 2), 0);
    }

    public void setHomeData() {
        screenWidth = getallWidth(((Activity) context));
//        dp72 = (screenWidth - DensityUtil.dip2px(context, 12 * 6)) * 2 / 9;
        dp72 = DensityUtil.dip2px(context, 85);
    }

    public void onHomeClicked(View view) {
        smoothScrollTo(view.getLeft() - (screenWidth / 2 - dp72 / 2), 0);
    }

    public void initHomeLeft(int lon) {
//        smoothScrollTo((lon+1)*dp72 - (screenWidth / 2 - dp72 / 2), 0);
        smoothScrollTo((lon) * (dp72 + DensityUtil.dip2px(context, 12)) - (screenWidth / 2 - dp72 / 2), 0);
    }

    public void setCateData() {
        dp70 = DensityUtil.dip2px(context, 70);
        screenWidth = getallWidth(((Activity) context));
    }

    public void onCateClicked(View view) {
        smoothScrollTo(view.getLeft() - (screenWidth / 2 - dp70 / 2), 0);
    }

    public void initCateLeft(int lon) {
        smoothScrollTo((lon) * dp70 - (screenWidth / 2 - dp70 / 2), 0);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }
}
