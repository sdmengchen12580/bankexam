package com.udit.bankexam.view.exam_mk;

import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.ReportBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamMkReportDataView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamMkReportDataView.View param1View) { super(param1View); }
    
    public abstract void getMKDTK(String param1String1, String param1String2);
    
    public abstract void getMkExamNode(String param1String1, String param1String2);
    
    public abstract void getMkRepPractice(String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setMkExamNode(List<ExamNodeBean> param1List);
    
    void setMkExamTdk(List<ExamTitleBean> param1List);
    
    void setMkRep(ReportBean param1ReportBean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_mk\ExamMkReportDataView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */