package com.udit.bankexam.view;

import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;


public interface MyWebView
{
    public interface View extends IBaseView
    {


    }
    
    abstract class  Presenter extends BasePresenter<View>
    {

        public Presenter(View mView)
        {
            super(mView);
        }

    }
}
