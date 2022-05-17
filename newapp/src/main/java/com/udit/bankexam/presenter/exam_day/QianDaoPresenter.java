package com.udit.bankexam.presenter.exam_day;

import com.udit.bankexam.bean_ok.UserBean;
import com.udit.bankexam.utils_ok.JsonUtils;
import com.udit.bankexam.view_ok.exam_day.QianDaoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import java.util.HashMap;

public class QianDaoPresenter extends QianDaoView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public QianDaoPresenter(QianDaoView.View paramView) { super(paramView); }
  
  public void doCard(String paramString) {
    HashMap hashMap = new HashMap();
    hashMap.put("action", "doCard");
    hashMap.put("uid", paramString);
    try {
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              UserBean userBean = JsonUtils.parseUser(param1String);
              if (userBean != null) {
                ((QianDaoView.View)QianDaoPresenter.this.mView).qiandao(userBean);
                return;
              } 
              ((QianDaoView.View)QianDaoPresenter.this.mView).qiandao(null);
            }
            
            public void onError(String param1String) { ((QianDaoView.View)QianDaoPresenter.this.mView).qiandao(null); }
          });
      return;
    } catch (Exception paramString) {
      ((QianDaoView.View)this.mView).qiandao(null);
      return;
    } 
  }
  
  public void login(String paramString1, String paramString2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doLogin");
      hashMap.put("mobile", paramString1);
      hashMap.put("pass", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              UserBean userBean = JsonUtils.parseUser(param1String);
              if (userBean != null)
                ((QianDaoView.View)QianDaoPresenter.this.mView).loginOk(userBean); 
            }
            
            public void onError(String param1String) {}
          });
      return;
    } catch (Exception paramString1) {
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_day\QianDaoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */