package com.udit.bankexam.presenter.user;

import android.content.Context;
import com.udit.bankexam.bean_ok.MsgBean;
import com.udit.bankexam.utils_ok.JsonUtils;
import com.udit.bankexam.view_ok.user.WxRegisterTwoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;

public class RegisterTwoPresenter extends WxRegisterTwoView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public RegisterTwoPresenter(WxRegisterTwoView.View paramView) { super(paramView); }
  
  public void doGetPass(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("设置密码中", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetPass");
      hashMap.put("mobile", paramString1);
      hashMap.put("pass", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              MsgBean msgBean = JsonUtils.parseDuanxin(param1String);
              if (msgBean != null) {
                ((WxRegisterTwoView.View)RegisterTwoPresenter.this.mView).setPassOk(msgBean);
              } else {
                ((WxRegisterTwoView.View)RegisterTwoPresenter.this.mView).setPassErr();
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((WxRegisterTwoView.View)RegisterTwoPresenter.this.mView).setPassErr();
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((WxRegisterTwoView.View)this.mView).setPassErr();
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presente\\user\RegisterTwoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */