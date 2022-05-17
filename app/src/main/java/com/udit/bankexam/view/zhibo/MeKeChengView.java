package com.udit.bankexam.view.zhibo;

import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface MeKeChengView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(MeKeChengView.View param1View) { super(param1View); }
    
    public abstract void getMyKeCheng(String param1String);
  }
  
  public static interface View extends IBaseView {
    void setKeCheng(List<ZhiBoBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\zhibo\MeKeChengView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */