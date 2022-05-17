package com.udit.bankexam.view;

import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

import java.util.List;


public interface WelcomeView
{
    public interface View extends IBaseView
    {
        public abstract void setWelcome(List<String> list);
    }
    
    abstract class  Presenter extends BasePresenter<View>
    {

        public Presenter(View mView)
        {
            super(mView);
        }
        public abstract void getWelcome();
    }
}
