package com.udit.bankexam.view.zhibo;

import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ZhiboZhongxinView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ZhiboZhongxinView.View param1View) { super(param1View); }
    
    public abstract void getZhiBoList(String param1String);
  }
  
  public static interface View extends IBaseView {
    void setZhiBo(List<ZhiBoBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\zhibo\ZhiboZhongxinView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */