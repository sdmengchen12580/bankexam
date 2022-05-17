package com.udit.frame.common.myviewpager;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyViewPager extends ViewPager
{
    
    private boolean isScrollable = false;
    
    public boolean isScrollable()
    {
        return isScrollable;
    }
    
    public void setScrollable(boolean isScrollable)
    {
        this.isScrollable = isScrollable;
    }
    
    public MyViewPager(Context context)
    {
        super(context);
    }
    
    public MyViewPager(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }
    
    @Override
    public boolean onTouchEvent(MotionEvent arg0)
    {
        if (!isScrollable)
            return false;
        return super.onTouchEvent(arg0);
    }
    
    @Override
    public boolean onInterceptTouchEvent(MotionEvent arg0)
    {
        if (!isScrollable)
            return false;
        return super.onInterceptTouchEvent(arg0);
    }
    
}
