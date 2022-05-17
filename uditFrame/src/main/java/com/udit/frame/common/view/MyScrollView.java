package com.udit.frame.common.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * 
 * <一句话功能简述>
 * 
 * @author 曾宝
 * @version [V1.00, 2016年1月5日]
 * @see [相关类/方法]
 * @since V1.00
 */
public class MyScrollView extends ScrollView
{
    private OnScrollListener onScrollListener;
    
    public MyScrollView(Context context)
    {
        this(context, null);
    }
    
    public MyScrollView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }
    
    public MyScrollView(Context context, AttributeSet attrs, int defStyle)
    {
        super(context, attrs, defStyle);
    }
    
 
    public void setOnScrollListener(OnScrollListener onScrollListener)
    {
        this.onScrollListener = onScrollListener;
    }
    
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        super.onScrollChanged(l, t, oldl, oldt);
        if (onScrollListener != null)
        {
            onScrollListener.onScroll(t);
        }
    }
    
  
    public interface OnScrollListener
    {
       
        public void onScroll(int scrollY);
    }
    
}
