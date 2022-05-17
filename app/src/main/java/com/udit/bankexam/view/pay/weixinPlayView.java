package com.udit.bankexam.view.pay;

import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface weixinPlayView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(weixinPlayView.View param1View) { super(param1View); }
    
    public abstract void zhifuOk(String param1String1, String param1String2, String param1String3);
  }
  
  public static interface View extends IBaseView {
    void zhifuErr();
    
    void zhifuOk();
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\pay\weixinPlayView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */