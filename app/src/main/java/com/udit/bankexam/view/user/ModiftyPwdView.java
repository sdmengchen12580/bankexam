package com.udit.bankexam.view.user;

import android.content.Context;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface ModiftyPwdView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ModiftyPwdView.View param1View) { super(param1View); }
    
    public abstract void modiftyPwd(Context param1Context, String param1String1, String param1String2,final String oldpAS);
  }
  
  public static interface View extends IBaseView {
    void modiftyErr(String mes);
    
    void modiftySucess(String param1String);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\vie\\user\ModiftyPwdView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */