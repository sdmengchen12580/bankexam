package com.udit.bankexam.view.exam_day;

import com.udit.bankexam.bean.UserBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface QianDaoView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(QianDaoView.View param1View) { super(param1View); }
    
    public abstract void doCard(String param1String);
    
    public abstract void login(String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void loginOk(UserBean param1UserBean);
    
    void qiandao(UserBean param1UserBean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_day\QianDaoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */