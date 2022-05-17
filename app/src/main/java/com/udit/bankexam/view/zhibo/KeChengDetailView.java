package com.udit.bankexam.view.zhibo;

import android.content.Context;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.frame.freamwork.activity.BasePresenter;
import com.udit.frame.freamwork.activity.IBaseView;
import java.util.List;

public interface KeChengDetailView {
  public static abstract class Presenter extends BasePresenter<View> {
    public Presenter(KeChengDetailView.View param1View) { super(param1View); }
    
    public abstract void doDelFavorite(String param1String1, String param1String2, String param1String3);
    
    public abstract void doPutFavorite(String param1String1, String param1String2, String param1String3, String param1String4);
    
    public abstract void getZhiboDetail(Context param1Context, String param1String1, String param1String2, String param1String3);
    
    public abstract void isPay(String param1String1, String param1String2, String param1String3);
    
    public abstract void pay(String param1String1, String param1String2, String param1String3);
  }
  
  public static interface View extends IBaseView {
    void baomingErr();
    
    void baomingOk();
    
    void payErr();
    
    void payOk();
    
    void setDelFavorite(List<FavoriteRecord> param1List);
    
    void setFavorite(List<FavoriteRecord> param1List);
    
    void setZhiboDetail(ZhiBoBean param1ZhiBoBean);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\view\zhibo\KeChengDetailView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */