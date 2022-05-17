package com.udit.bankexam.presenter.user;

import android.content.Context;
import com.udit.bankexam.view_ok.user.ModiftyPwdView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;

public class ModiftyPwdPresenter extends ModiftyPwdView.Presenter {
  public ModiftyPwdPresenter(ModiftyPwdView.View paramView) { super(paramView); }
  
  public void modiftyPwd(Context paramContext, String paramString1, final String pwd) {
    try {
      ProgressUtils.showProgressDlg("修改密码中", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doUpPass");
      hashMap.put("uid", paramString1);
      hashMap.put("pass", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonArrayOk(param1String)) {
                ((ModiftyPwdView.View)ModiftyPwdPresenter.this.mView).modiftySucess(pwd);
              } else {
                ((ModiftyPwdView.View)ModiftyPwdPresenter.this.mView).modiftyErr();
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ModiftyPwdView.View)ModiftyPwdPresenter.this.mView).modiftyErr();
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ModiftyPwdView.View)this.mView).modiftyErr();
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presente\\user\ModiftyPwdPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */