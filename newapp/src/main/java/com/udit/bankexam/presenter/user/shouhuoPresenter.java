package com.udit.bankexam.presenter.user;

import android.content.Context;
import com.udit.bankexam.bean_ok.AddressBean;
import com.udit.bankexam.view_ok.user.shouhuoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class shouhuoPresenter extends shouhuoView.Presenter {
  public shouhuoPresenter(shouhuoView.View paramView) { super(paramView); }
  
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
                ((shouhuoView.View)shouhuoPresenter.this.mView).getAddr((AddressBean)list.get(0));
              } else {
                ((shouhuoView.View)shouhuoPresenter.this.mView).getAddr(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((shouhuoView.View)shouhuoPresenter.this.mView).getAddr(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((shouhuoView.View)this.mView).getAddr(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void doPutAddr(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doPutAddr");
      hashMap.put("uid", paramString1);
      hashMap.put("Name", paramString2);
      hashMap.put("Tel", paramString3);
      hashMap.put("Province", paramString4);
      hashMap.put("City", paramString5);
      hashMap.put("District", paramString6);
      hashMap.put("Addr", paramString7);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonArrayOk(param1String)) {
                ((shouhuoView.View)shouhuoPresenter.this.mView).putAddrOk();
              } else {
                ((shouhuoView.View)shouhuoPresenter.this.mView).putAddErr();
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((shouhuoView.View)shouhuoPresenter.this.mView).putAddErr();
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((shouhuoView.View)this.mView).putAddErr();
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presente\\user\shouhuoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */