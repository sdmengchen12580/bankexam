package com.udit.bankexam.presenter.home;

import com.udit.bankexam.bean_ok.AppParams;
import com.udit.bankexam.view_ok.home.HomeView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.freamwork.updateManager.AppVersion;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class HomePresenter extends HomeView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public HomePresenter(HomeView.View paramView) { super(paramView); }
  
  public void getParams() {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetParam");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, AppParams.class);
              if (list != null && list.size() > 0)
                ((HomeView.View)HomePresenter.this.mView).saveParams((AppParams)list.get(0)); 
            }
            
            public void onError(String param1String) {}
          });
      return;
    } catch (Exception exception) {
      MyLogUtils.e(this.TAG, exception.getMessage());
      return;
    } 
  }
  
  public void updateApp() {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "androidUpdate");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                AppVersion appVersion = (AppVersion)JsonUtil.jsonToObject(param1String, AppVersion.class, "AppVersion");
                if (appVersion != null)
                  ((HomeView.View)HomePresenter.this.mView).updateApp(appVersion); 
              } 
            }
            
            public void onError(String param1String) {}
          });
      return;
    } catch (Exception exception) {
      return;
    } 
  }
  
  public void updateUserToken(String paramString1, String paramString2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doPutMsgToken");
      hashMap.put("uid", paramString1);
      hashMap.put("token", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) { MyLogUtils.e(HomePresenter.this.TAG, param1String); }
            
            public void onError(String param1String) { MyLogUtils.e(HomePresenter.this.TAG, param1String); }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\home\HomePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */