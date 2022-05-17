package com.udit.bankexam.view.pay;

import android.content.Context;
import com.udit.bankexam.bean.AddressBean;
import com.udit.bankexam.bean.PayInfo;
import com.udit.bankexam.bean.WeixinPayInfo;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface PaySelecteView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(PaySelecteView.View param1View) { super(param1View); }
    
    public abstract void doGetAddr(Context param1Context, String param1String);
    
    public abstract void isPay(String param1String1, String param1String2, String param1String3);
    
    public abstract void zhifu(String param1String1, String param1String2, String param1String3, String param1String4);
    
    public abstract void zhifuOk(String param1String1, String param1String2, String param1String3);
  }
  
  public static interface View extends IBaseView {
    void getAddr(AddressBean param1AddressBean);
    
    void getWeiXinSign(WeixinPayInfo param1WeixinPayInfo);
    
    void getZhifuSign(PayInfo param1PayInfo);
    
    void payErr();
    
    void payOk();
    
    void zhifuErr();
    
    void zhifuOk();
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\pay\PaySelecteView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */