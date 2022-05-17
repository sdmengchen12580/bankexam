package com.udit.bankexam.presenter.home.fragment;

import com.udit.bankexam.view_ok.home.fragment.ZhiboView;
import java.util.ArrayList;

public class ZhiboPresenter extends ZhiboView.Presenter {
  public ZhiboPresenter(ZhiboView.View paramView) { super(paramView); }
  
  public void getTitle() {
    ArrayList arrayList = new ArrayList();
    arrayList.add("直播中心");
    arrayList.add("我的课程");
    ((ZhiboView.View)this.mView).setTitle(arrayList);
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\home\fragment\ZhiboPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */