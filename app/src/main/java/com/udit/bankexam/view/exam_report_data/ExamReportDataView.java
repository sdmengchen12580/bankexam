package com.udit.bankexam.view.exam_report_data;

import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.ReportBean;
import com.udit.bankexam.bean.SolutionBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamReportDataView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamReportDataView.View param1View) { super(param1View); }
    
    public abstract void getReport(String param1String);
    
    public abstract void getReportDTK(String param1String);
    
    public abstract void getSJ(String param1String);
  }
  
  public static interface View extends IBaseView {
    void setDTK(List<SolutionBean> param1List);
    
    void setReport(List<ReportBean> param1List);
    
    void setSJ(List<PurchBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_report_data\ExamReportDataView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */