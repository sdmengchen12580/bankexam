package com.udit.bankexam.view.video;

import android.content.Context;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface VideoInfoView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(VideoInfoView.View param1View) { super(param1View); }

    public abstract void getExamInfo(Context param1Context, String param1String1, String param1String2);

    public abstract void saveExam(String param1String1, String param1String2, String param1String3);

    public abstract void getVideoUrl(String videoIdAli);
  }

  public static interface View extends IBaseView {
    void getVidoeInfoNext(int param1Int);

    void playVideo(String param1String);

    void setExamInfo(List<ExamTitleBean> param1List);

    void playVideo(boolean isSuccess, String playUrl0rErrorMsg);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\video\VideoInfoView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */