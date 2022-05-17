package com.udit.bankexam.view.video;

import com.udit.bankexam.bean.VideoBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface VideoView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(VideoView.View param1View) { super(param1View); }
    
    public abstract void getVideList(String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setVideo(List<VideoBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\video\VideoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */