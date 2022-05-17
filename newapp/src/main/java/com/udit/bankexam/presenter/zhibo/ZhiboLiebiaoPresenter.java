package com.udit.bankexam.presenter.zhibo;

import android.content.Context;
import com.udit.bankexam.view_ok.zhibo.ZhiboLiebiaoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ZhiboLiebiaoPresenter extends ZhiboLiebiaoView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ZhiboLiebiaoPresenter(ZhiboLiebiaoView.View paramView) { super(paramView); }
  
  public void getZhiboList(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetLiveSchedule");
      hashMap.put("uid", paramString1);
      hashMap.put("LID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ZhiboKeChengBean.class);
              if (list != null && list.size() > 0) {
                ((ZhiboLiebiaoView.View)ZhiboLiebiaoPresenter.this.mView).setZhiboList(list);
              } else {
                ((ZhiboLiebiaoView.View)ZhiboLiebiaoPresenter.this.mView).setZhiboList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ZhiboLiebiaoView.View)ZhiboLiebiaoPresenter.this.mView).setZhiboList(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ZhiboLiebiaoView.View)this.mView).setZhiboList(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\zhibo\ZhiboLiebiaoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */