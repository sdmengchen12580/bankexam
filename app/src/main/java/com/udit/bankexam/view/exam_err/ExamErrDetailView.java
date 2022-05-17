package com.udit.bankexam.view.exam_err;

import android.content.Context;
import com.udit.bankexam.bean.AdvBean;
import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamErrDetailView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamErrDetailView.View param1View) { super(param1View); }
    
    public abstract void getAdv();
    
    public abstract void getExamTitles(String param1String1, String param1String2);
    
    public abstract void getExamTtils(Context param1Context, String param1String1, String param1String2);
    
    public abstract void getHomeDetail(String param1String1, String param1String2);
    
    public abstract void getSJDetail(String param1String1, String param1String2);
    
    public abstract void getZhinengTitles(Context param1Context, String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setAdv(List<AdvBean> param1List);
    
    void setSJ(List<ExamBean> param1List);
    
    void setSJDetail(List<ExamTitleBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_err\ExamErrDetailView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */