package com.udit.bankexam.view.zixun;

import com.udit.bankexam.bean.NewBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;

public interface ZiXunDetailView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ZiXunDetailView.View param1View) { super(param1View); }
  }
  
  public static interface View extends IBaseView {
    void setNewDetail(NewBean param1NewBean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\zixun\ZiXunDetailView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */