package com.udit.bankexam.view.user;

import android.content.Context;

import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.bean.UserBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface LoginView {
    public static abstract class Presenter extends BasePresenter<View> {
        public Presenter(LoginView.View param1View) {
            super(param1View);
        }

        public abstract void getDuanxin(Context param1Context, String param1String);

        public abstract void login(Context param1Context, String param1String1, String param1String2);

        public abstract void loginNew(Context param1Context, String param1String1, String param1String2);

        public abstract void register(Context param1Context, String param1String1, String param1String2);
    }

    public static interface View extends IBaseView {
        void loginErr(String param1String);

        void loginOk(UserBean param1UserBean);

        void registerErr();

        void registerOk(MsgBean param1MsgBean);

        void setDuanxinErr(String param1String);

        void setDuanxinOk(MsgBean param1MsgBean);
    }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\vie\\user\LoginView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */