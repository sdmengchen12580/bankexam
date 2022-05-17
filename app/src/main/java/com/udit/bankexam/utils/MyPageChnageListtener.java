package com.udit.bankexam.utils;

import android.support.v4.view.ViewPager;

public abstract class MyPageChnageListtener implements ViewPager.OnPageChangeListener {

    protected abstract void select(int i);

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }

    @Override
    public void onPageSelected(int i) {
        select(i);
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }
}
