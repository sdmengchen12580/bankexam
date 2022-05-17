package com.udit.bankexam.view.exam_data_history;

import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.HisPractBean;
import com.udit.bankexam.bean.PurchBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamDataHistoryView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamDataHistoryView.View param1View) { super(param1View); }
    
    public abstract void getExam(String param1String1, String param1String2);
    
    public abstract void getHisExPract(String param1String);
    
    public abstract void getHisExam(String param1String1, String param1String2);
    
    public abstract void getHisPract(String param1String);
    
    public abstract void getPurch(String param1String);
  }
  
  public static interface View extends IBaseView {
    void setExam(List<ExamTitleBean> param1List);
    
    void setHisExPract(List<HisPractBean> param1List);
    
    void setHisPract(List<HisPractBean> param1List);
    
    void setPurch(List<PurchBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_data_history\ExamDataHistoryView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */