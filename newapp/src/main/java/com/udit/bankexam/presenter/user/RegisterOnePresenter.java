package com.udit.bankexam.presenter.user;

import android.content.Context;
import com.udit.bankexam.bean_ok.MsgBean;
import com.udit.bankexam.utils_ok.JsonUtils;
import com.udit.bankexam.view_ok.user.WxRegisterOneView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class RegisterOnePresenter extends WxRegisterOneView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public RegisterOnePresenter(WxRegisterOneView.View paramView) { super(paramView); }
  
  public void checkPhoneIsRegister(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("验证中", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doCheckWxRegisterPhone");
      hashMap.put("mobile", paramString1);
      hashMap.put("uid", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              Object object;
              if (JsonUtil.getJsonArrayOk(param1String)) {
                object = JsonUtil.getJsonArrayforKey(param1String, "type");
                if (object != null) {
                  object = object.toString();
                  ((WxRegisterOneView.View)RegisterOnePresenter.this.mView).setCheckPhoneIsRegisterOk(object);
                } else {
                  ((WxRegisterOneView.View)RegisterOnePresenter.this.mView).setCheckPhoneIsRegisterErr("验证失败");
                } 
              } else {
                object = JsonUtil.getJsonArrayforKey(object, "msg");
                if (object != null) {
                  WxRegisterOneView.View view = (WxRegisterOneView.View)RegisterOnePresenter.this.mView;
                  StringBuilder stringBuilder = new StringBuilder();
                  stringBuilder.append("");
                  stringBuilder.append(object);
                  view.setCheckPhoneIsRegisterErr(stringBuilder.toString());
                } else {
                  ((WxRegisterOneView.View)RegisterOnePresenter.this.mView).setCheckPhoneIsRegisterErr("验证失败");
                } 
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((WxRegisterOneView.View)RegisterOnePresenter.this.mView).setCheckPhoneIsRegisterErr(param1String);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((WxRegisterOneView.View)this.mView).setDuanxinErr(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getDuanxin(Context paramContext, String paramString) {
    try {
      ProgressUtils.showProgressDlg("获取短信中", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doIdentifying");
      hashMap.put("mobile", paramString);
      hashMap.put("type", "3");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              MsgBean msgBean = JsonUtils.parseDuanxin(param1String);
              if (msgBean != null) {
                ((WxRegisterOneView.View)RegisterOnePresenter.this.mView).setDuanxinOk(msgBean);
              } else {
                List list = JsonUtil.jsonToListByArrayNoSucess(param1String, MsgBean.class);
                if (list != null && list.size() > 0) {
                  ((WxRegisterOneView.View)RegisterOnePresenter.this.mView).setDuanxinErr(((MsgBean)list.get(0)).getMsg());
                } else {
                  ((WxRegisterOneView.View)RegisterOnePresenter.this.mView).setDuanxinErr(null);
                } 
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((WxRegisterOneView.View)RegisterOnePresenter.this.mView).setDuanxinErr(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((WxRegisterOneView.View)this.mView).setDuanxinErr(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presente\\user\RegisterOnePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */