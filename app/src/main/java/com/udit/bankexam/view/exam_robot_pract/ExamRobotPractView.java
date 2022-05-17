package com.udit.bankexam.view.exam_robot_pract;

import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.TypeBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamRobotPractView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamRobotPractView.View param1View) { super(param1View); }
    
    public abstract void getData();
    
    public abstract void getExamList(String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setData(List<TypeBean> param1List);
    
    void setExamList(List<ExamTitleBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_robot_pract\ExamRobotPractView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */