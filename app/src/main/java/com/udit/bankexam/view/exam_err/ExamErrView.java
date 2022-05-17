package com.udit.bankexam.view.exam_err;

import android.content.Context;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.PurchBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamErrView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamErrView.View param1View) { super(param1View); }
    
    public abstract void getHomeSJDTK(Context param1Context, String param1String1, String param1String2);
    
    public abstract void getSJ(String param1String);
  }
  
  public static interface View extends IBaseView {
    void setExamTitle(List<ExamTitleBean> param1List);
    
    void setSJ(List<PurchBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_err\ExamErrView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */