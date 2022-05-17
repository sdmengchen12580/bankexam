package com.udit.bankexam.view.user;

import android.content.Context;
import com.udit.bankexam.bean.AddressBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface shouhuoView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(shouhuoView.View param1View) { super(param1View); }
    
    public abstract void doGetAddr(Context param1Context, String param1String);
    
    public abstract void doPutAddr(Context param1Context, String param1String1, String param1String2, String param1String3, String param1String4, String param1String5, String param1String6, String param1String7);
  }
  
  public static interface View extends IBaseView {
    void getAddr(AddressBean param1AddressBean);
    
    void putAddErr();
    
    void putAddrOk();
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\vie\\user\shouhuoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */