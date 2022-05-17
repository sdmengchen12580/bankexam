package com.udit.bankexam.presenter.home.fragment;

import java.util.ArrayList;
import java.util.List;

import com.udit.bankexam.view.home.fragment.ZhiboView;
import com.udit.bankexam.view.home.fragment.ZhiboView.View;

public class ZhiboPresenter extends ZhiboView.Presenter
{

    public ZhiboPresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void getTitle()
    {
        List<String> mlist = new ArrayList<>();
        
        mlist.add("直播中心");
        mlist.add("我的课程");
        
        mView.setTitle(mlist);
        
        
    }

   
}
