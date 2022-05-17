package com.udit.bankexam.view.home;

import com.udit.bankexam.bean.AppParams;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import com.udit.frame.freamwork.updateManager.AppVersion;

public interface HomeView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(HomeView.View param1View) { super(param1View); }
    
    public abstract void getParams();
    
    public abstract void updateApp();
    
    public abstract void updateUserToken(String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void saveParams(AppParams param1AppParams);
    
    void updateApp(AppVersion param1AppVersion);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\home\HomeView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */