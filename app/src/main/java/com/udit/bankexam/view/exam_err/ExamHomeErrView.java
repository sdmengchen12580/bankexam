package com.udit.bankexam.view.exam_err;

import android.content.Context;

import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.ArrayList;
import java.util.List;

public interface ExamHomeErrView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamHomeErrView.View param1View) { super(param1View); }
    
    public abstract void getExamNode(Context param1Context, String param1String);
    
    public abstract void getExamNodeErr(Context param1Context, String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setExamNode(List<ExamNodeBean> param1List);
    
    void setExamNodeErr(ArrayList<ExamTitleBean> param1ArrayList);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_err\ExamHomeErrView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */