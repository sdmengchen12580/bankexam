package com.udit.bankexam.ui.home.fragment.adapter;

import java.util.ArrayList;

import com.udit.frame.freamwork.activity.BaseFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.view.ViewGroup;

public class MyFragmentPagerAdapter extends FragmentPagerAdapter
{
    private ArrayList<BaseFragment<?>> fragments;
    
    private FragmentManager fm;
    
    public MyFragmentPagerAdapter(FragmentManager fm)
    {
        super(fm);
        this.fm = fm;
    }
    
    public MyFragmentPagerAdapter(FragmentManager fragmentManager, ArrayList<BaseFragment<?>> fragments)
    {
        super(fragmentManager);
        this.fm = fragmentManager;
        this.fragments = fragments;
    }
    
    @Override
    public int getCount()
    {
        return fragments.size();
    }
    
    @Override
    public Fragment getItem(int position)
    {
        return fragments.get(position);
    }
    
    @Override
    public int getItemPosition(Object object)
    {
        return POSITION_NONE;
    }
    
    public void setFragments(ArrayList<BaseFragment<?>> fragments)
    {
        if (this.fragments != null)
        {
            FragmentTransaction ft = fm.beginTransaction();
            for (Fragment f : this.fragments)
            {
                ft.remove(f);
            }
            ft.commit();
            ft = null;
            fm.executePendingTransactions();
        }
        this.fragments = fragments;
        notifyDataSetChanged();
    }
    
    @Override
    public Object instantiateItem(ViewGroup container, final int position)
    {
        Object obj = super.instantiateItem(container, position);
        return obj;
    }
    
}
