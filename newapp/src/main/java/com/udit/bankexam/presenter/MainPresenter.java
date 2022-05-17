package com.udit.bankexam.presenter;

import com.udit.bankexam.bean_ok.MsgBean;
import com.udit.bankexam.bean_ok.UserBean;
import com.udit.bankexam.utils_ok.JsonUtils;
import com.udit.bankexam.view_ok.MainView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class MainPresenter extends MainView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public MainPresenter(MainView.View paramView) { super(paramView); }
  
  public void doAnimator() { ((MainView.View)this.mView).doAnimator(); }
  
  public void login(String paramString1, String paramString2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doLogin");
      hashMap.put("mobile", paramString1);
      hashMap.put("pass", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              UserBean userBean = JsonUtils.parseUser(param1String);
              if (userBean != null) {
                ((MainView.View)MainPresenter.this.mView).loginOk(userBean);
                return;
              } 
              List list = JsonUtil.jsonToListByArrayNoSucess(param1String, MsgBean.class);
              if (list != null && list.size() > 0) {
                ((MainView.View)MainPresenter.this.mView).loginErr(((MsgBean)list.get(0)).getMsg());
                return;
              } 
              ((MainView.View)MainPresenter.this.mView).loginErr(null);
            }
            
            public void onError(String param1String) { ((MainView.View)MainPresenter.this.mView).loginErr(null); }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((MainView.View)this.mView).loginErr(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\MainPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */