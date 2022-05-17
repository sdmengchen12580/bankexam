package com.udit.bankexam.view.exam_robot;

import android.content.Context;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.HisPractBean;
import com.udit.bankexam.bean.TypeBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamRobotView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamRobotView.View param1View) { super(param1View); }
    
    public abstract void getData();
    
    public abstract void getExamHis(Context param1Context, String param1String);
    
    public abstract void getExamList(Context param1Context, String param1String1, String param1String2);
    
    public abstract void getHisExamList(Context param1Context, String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setData(List<TypeBean> param1List);
    
    void setExamList(List<ExamTitleBean> param1List);
    
    void setHisExam(List<HisPractBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_robot\ExamRobotView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */