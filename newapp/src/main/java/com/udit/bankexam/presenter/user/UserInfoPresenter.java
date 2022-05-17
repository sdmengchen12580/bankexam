package com.udit.bankexam.presenter.user;

import android.content.Context;
import com.udit.bankexam.bean_ok.AddressBean;
import com.udit.bankexam.view_ok.user.UserInfoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class UserInfoPresenter extends UserInfoView.UserInfoPresenter {
  public UserInfoPresenter(UserInfoView.View paramView) { super(paramView); }
  
  public void doGetAddr(Context paramContext, String paramString) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetAddr");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, AddressBean.class);
              if (list != null && list.size() > 0) {
                ((UserInfoView.View)UserInfoPresenter.this.mView).getAddr((AddressBean)list.get(0));
              } else {
                ((UserInfoView.View)UserInfoPresenter.this.mView).getAddr(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((UserInfoView.View)UserInfoPresenter.this.mView).getAddr(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((UserInfoView.View)this.mView).getAddr(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presente\\user\UserInfoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */