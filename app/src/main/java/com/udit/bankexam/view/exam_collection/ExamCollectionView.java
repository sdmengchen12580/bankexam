package com.udit.bankexam.view.exam_collection;

import android.content.Context;

import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface ExamCollectionView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(ExamCollectionView.View param1View) { super(param1View); }
    
    public abstract void getCollection(Context param1Context, String param1String);
    
    public abstract void getExam(Context param1Context, String param1String1, String param1String2);
    
    public abstract void getExamZhineng(Context param1Context, String param1String1, String param1String2);
  }
  
  public static interface View extends IBaseView {
    void setExams(List<ExamBean> param1List, String param1String);
    
    void setFavorite(List<FavoriteRecord> param1List);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\exam_collection\ExamCollectionView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */