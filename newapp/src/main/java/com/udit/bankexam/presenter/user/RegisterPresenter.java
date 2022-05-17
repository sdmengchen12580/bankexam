package com.udit.bankexam.presenter.user;

import android.content.Context;
import com.udit.bankexam.bean_ok.MsgBean;
import com.udit.bankexam.utils_ok.JsonUtils;
import com.udit.bankexam.view_ok.user.RegisterView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class RegisterPresenter extends RegisterView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public RegisterPresenter(RegisterView.View paramView) { super(paramView); }
  
  public void getDuanxin(Context paramContext, String paramString) {
    try {
      ProgressUtils.showProgressDlg("获取短信中", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doIdentifying");
      hashMap.put("mobile", paramString);
      hashMap.put("type", "0");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              MsgBean msgBean = JsonUtils.parseDuanxin(param1String);
              if (msgBean != null) {
                ((RegisterView.View)RegisterPresenter.this.mView).setDuanxinOk(msgBean);
              } else {
                List list = JsonUtil.jsonToListByArrayNoSucess(param1String, MsgBean.class);
                if (list != null && list.size() > 0) {
                  ((RegisterView.View)RegisterPresenter.this.mView).setDuanxinErr(((MsgBean)list.get(0)).getMsg());
                } else {
                  ((RegisterView.View)RegisterPresenter.this.mView).setDuanxinErr(null);
                } 
              } 
              ProgressUtils.stopProgressDlgDelay();
            }
            
            public void onError(String param1String) {
              ((RegisterView.View)RegisterPresenter.this.mView).setDuanxinErr(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((RegisterView.View)this.mView).setDuanxinErr(null);
      ProgressUtils.stopProgressDlgDelay();
      return;
    } 
  }
  
  public void register(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("账号注册中", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doRegister");
      hashMap.put("mobile", paramString1);
      hashMap.put("pass", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              MsgBean msgBean = JsonUtils.parseDuanxin(param1String);
              if (msgBean != null) {
                ((RegisterView.View)RegisterPresenter.this.mView).registerOk(msgBean);
              } else {
                ((RegisterView.View)RegisterPresenter.this.mView).registerErr();
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((RegisterView.View)RegisterPresenter.this.mView).registerErr();
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((RegisterView.View)this.mView).registerErr();
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presente\\user\RegisterPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */