package com.udit.bankexam.view.exam_mk;

import android.content.Context;

import com.udit.bankexam.bean.MKBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamMkView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamMkView.View param1View) { super(param1View); }
    
    public abstract void getMK(Context param1Context, String param1String);
  }
  
  public static interface View extends IBaseView {
    void setMkList(List<MKBean> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_mk\ExamMkView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */