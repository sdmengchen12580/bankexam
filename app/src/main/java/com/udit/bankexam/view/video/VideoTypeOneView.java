package com.udit.bankexam.view.video;

import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface VideoTypeOneView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(VideoTypeOneView.View param1View) { super(param1View); }
    
    public abstract void getTypeOne(String param1String);
  }
  
  public static interface View extends IBaseView {
    void setTypeOne(List<String> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\video\VideoTypeOneView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */