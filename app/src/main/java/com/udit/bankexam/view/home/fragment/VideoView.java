package com.udit.bankexam.view.home.fragment;

import com.udit.bankexam.bean.VideoTypeOneBean;
import com.udit.bankexam.bean.VideoTypeTwoBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface VideoView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(VideoView.View param1View) { super(param1View); }
    
    public abstract void getTypeList(String param1String1, String param1String2);
    
    public abstract void getTypeList(String param1String1, String param1String2, String param1String3);
    
    public abstract void getTypeOne(String param1String);
  }
  
  public static interface View extends IBaseView {
    void setTypeList(List<VideoTypeTwoBean> param1List);
    
    void setTypeOne(List<VideoTypeOneBean> param1List);
    
    void setTypeTwo(VideoTypeTwoBean param1VideoTypeTwoBean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\home\fragment\VideoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */