package com.udit.bankexam.presenter.zhibo;

import com.udit.bankexam.view_ok.zhibo.DateView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class DatelPresenter extends DateView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public DatelPresenter(DateView.View paramView) { super(paramView); }
  
  public void getZhiboDate(String paramString) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetSchedule");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ZhiboKeChengBean.class);
              if (list != null && list.size() > 0) {
                ((DateView.View)DatelPresenter.this.mView).setZhiboDate(list);
                return;
              } 
              ((DateView.View)DatelPresenter.this.mView).setZhiboDate(null);
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(DatelPresenter.this.TAG, param1String);
              ((DateView.View)DatelPresenter.this.mView).setZhiboDate(null);
            }
          });
      return;
    } catch (Exception paramString) {
      MyLogUtils.e(this.TAG, paramString.getMessage());
      ((DateView.View)this.mView).setZhiboDate(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\zhibo\DatelPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */