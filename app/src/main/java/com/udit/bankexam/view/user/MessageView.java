package com.udit.bankexam.view.user;

import com.udit.bankexam.bean.MessageBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface MessageView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(MessageView.View param1View) { super(param1View); }
    
    public abstract void getMessage(String param1String, int param1Int1, int param1Int2);
  }
  
  public static interface View extends IBaseView {
    void setMessage(List<MessageBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\vie\\user\MessageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */