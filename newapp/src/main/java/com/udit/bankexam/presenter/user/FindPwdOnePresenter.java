package com.udit.bankexam.presenter.user;

import android.content.Context;
import com.udit.bankexam.bean_ok.MsgBean;
import com.udit.bankexam.utils_ok.JsonUtils;
import com.udit.bankexam.view_ok.user.FindPwdOneView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class FindPwdOnePresenter extends FindPwdOneView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public FindPwdOnePresenter(FindPwdOneView.View paramView) { super(paramView); }
  
  public void getDuanxin(Context paramContext, String paramString) {
    try {
      ProgressUtils.showProgressDlg("获取短信中", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doIdentifying");
      hashMap.put("mobile", paramString);
      hashMap.put("type", "1");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              MsgBean msgBean = JsonUtils.parseDuanxin(param1String);
              if (msgBean != null) {
                ((FindPwdOneView.View)FindPwdOnePresenter.this.mView).setDuanxinOk(msgBean);
              } else {
                List list = JsonUtil.jsonToListByArrayNoSucess(param1String, MsgBean.class);
                if (list != null && list.size() > 0) {
                  ((FindPwdOneView.View)FindPwdOnePresenter.this.mView).setDuanxinErr(((MsgBean)list.get(0)).getMsg());
                } else {
                  ((FindPwdOneView.View)FindPwdOnePresenter.this.mView).setDuanxinErr(null);
                } 
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((FindPwdOneView.View)FindPwdOnePresenter.this.mView).setDuanxinErr(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((FindPwdOneView.View)this.mView).setDuanxinErr(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presente\\user\FindPwdOnePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */