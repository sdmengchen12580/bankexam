package com.udit.bankexam.view;

import com.udit.bankexam.bean.UserBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;


public interface MainView 
{
    public interface View extends IBaseView
    {
        void doAnimator();
        
        void startActivity();

        void loginOk(UserBean bean);

        void loginErr(String msg);

    }
    
    abstract class  Presenter extends BasePresenter<View>
    {

        public Presenter(View mView)
        {
            super(mView);
        }
        public abstract void doAnimator();


        public abstract void login(String mobile,String pass);
    }
}
