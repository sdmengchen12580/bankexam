package com.udit.bankexam.presenter.zixun;

import android.content.Context;
import com.udit.bankexam.view_ok.zixun.ZiXunView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ZiXunPresenter extends ZiXunView.Presenter {
  public ZiXunPresenter(ZiXunView.View paramView) { super(paramView); }
  
  public void getZiXun(Context paramContext, String paramString, int paramInt1, int paramInt2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetInfo");
      hashMap.put("KeyWord", paramString);
      StringBuilder stringBuilder = new StringBuilder();
      stringBuilder.append(paramInt1);
      stringBuilder.append("");
      hashMap.put("NPage", stringBuilder.toString());
      stringBuilder = new StringBuilder();
      stringBuilder.append(paramInt2);
      stringBuilder.append("");
      hashMap.put("tCount", stringBuilder.toString());
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ZiXunBean.class);
              if (list != null && list.size() > 0) {
                ((ZiXunView.View)ZiXunPresenter.this.mView).setZiXun(list);
              } else {
                ((ZiXunView.View)ZiXunPresenter.this.mView).setZiXun(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ZiXunView.View)ZiXunPresenter.this.mView).setZiXun(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ZiXunView.View)this.mView).setZiXun(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\zixun\ZiXunPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */