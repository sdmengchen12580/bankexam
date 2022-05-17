package com.udit.bankexam.view.user;

import android.content.Context;
import com.udit.bankexam.bean.AddressBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface UserInfoView {
  public static abstract class UserInfoPresenter extends BasePresenter<View> {
    public UserInfoPresenter(UserInfoView.View param1View) { super(param1View); }
    
    public abstract void doGetAddr(Context param1Context, String param1String);
  }
  
  public static interface View extends IBaseView {
    void getAddr(AddressBean param1AddressBean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\vie\\user\UserInfoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */