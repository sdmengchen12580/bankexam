package com.udit.bankexam.view.user;

import android.content.Context;
import com.udit.bankexam.bean.MsgBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface FindPwdTwoView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(FindPwdTwoView.View param1View) { super(param1View); }
    
    public abstract void doGetPass(Context param1Context, String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setPassErr();
    
    void setPassOk(MsgBean param1MsgBean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\vie\\user\FindPwdTwoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */