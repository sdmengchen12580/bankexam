package com.udit.bankexam.view.home.fragment;

import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ZhiboView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ZhiboView.View param1View) { super(param1View); }
    
    public abstract void getTitle();
  }
  
  public static interface View extends IBaseView {
    void setTitle(List<String> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\home\fragment\ZhiboView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */