package com.udit.bankexam.presenter;

import com.udit.bankexam.view_ok.WelcomeView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import java.util.HashMap;
import java.util.List;

public class WelcomePresenter extends WelcomeView.Presenter {
  public WelcomePresenter(WelcomeView.View paramView) { super(paramView); }
  
  public void getWelcome() {
    hashMap = new HashMap();
    try {
      hashMap.put("action", "getBootScrollPics");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByValue(param1String, "pic_url");
              if (list != null) {
                ((WelcomeView.View)WelcomePresenter.this.mView).setWelcome(list);
                return;
              } 
              ((WelcomeView.View)WelcomePresenter.this.mView).setWelcome(null);
            }
            
            public void onError(String param1String) { ((WelcomeView.View)WelcomePresenter.this.mView).setWelcome(null); }
          });
      return;
    } catch (Exception hashMap) {
      ((WelcomeView.View)this.mView).setWelcome(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\WelcomePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */