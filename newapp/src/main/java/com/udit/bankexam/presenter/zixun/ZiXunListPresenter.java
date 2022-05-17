package com.udit.bankexam.presenter.zixun;

import android.content.Context;
import com.udit.bankexam.view_ok.zixun.ZiXunListView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ZiXunListPresenter extends ZiXunListView.Presenter {
  public ZiXunListPresenter(ZiXunListView.View paramView) { super(paramView); }
  
  public void getNews(Context paramContext, String paramString, int paramInt1, int paramInt2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "getNews");
      hashMap.put("Type", paramString);
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
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.NewBean.class);
              if (list != null && list.size() > 0) {
                ((ZiXunListView.View)ZiXunListPresenter.this.mView).setNews(list);
              } else {
                ((ZiXunListView.View)ZiXunListPresenter.this.mView).setNews(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ZiXunListView.View)ZiXunListPresenter.this.mView).setNews(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ZiXunListView.View)this.mView).setNews(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\zixun\ZiXunListPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */