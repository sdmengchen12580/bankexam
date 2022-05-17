package com.udit.bankexam.presenter.user;

import android.content.Context;
import com.udit.bankexam.bean_ok.MsgBean;
import com.udit.bankexam.utils_ok.JsonUtils;
import com.udit.bankexam.view_ok.user.FindPwdTwoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;

public class FindPwdTwoPresenter extends FindPwdTwoView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public FindPwdTwoPresenter(FindPwdTwoView.View paramView) { super(paramView); }
  
  public void doGetPass(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("重置密码中", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetPass");
      hashMap.put("mobile", paramString1);
      hashMap.put("pass", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              MsgBean msgBean = JsonUtils.parseDuanxin(param1String);
              if (msgBean != null) {
                ((FindPwdTwoView.View)FindPwdTwoPresenter.this.mView).setPassOk(msgBean);
              } else {
                ((FindPwdTwoView.View)FindPwdTwoPresenter.this.mView).setPassErr();
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((FindPwdTwoView.View)FindPwdTwoPresenter.this.mView).setPassErr();
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((FindPwdTwoView.View)this.mView).setPassErr();
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presente\\user\FindPwdTwoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */