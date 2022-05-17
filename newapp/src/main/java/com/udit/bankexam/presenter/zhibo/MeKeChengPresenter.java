package com.udit.bankexam.presenter.zhibo;

import com.udit.bankexam.view_ok.zhibo.MeKeChengView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class MeKeChengPresenter extends MeKeChengView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public MeKeChengPresenter(MeKeChengView.View paramView) { super(paramView); }
  
  public void getMyKeCheng(String paramString) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetScheduleZhibo");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ZhiBoBean.class);
              if (list != null && list.size() > 0) {
                ((MeKeChengView.View)MeKeChengPresenter.this.mView).setKeCheng(list);
                return;
              } 
              ((MeKeChengView.View)MeKeChengPresenter.this.mView).setKeCheng(null);
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(MeKeChengPresenter.this.TAG, param1String);
              ((MeKeChengView.View)MeKeChengPresenter.this.mView).setKeCheng(null);
            }
          });
      return;
    } catch (Exception paramString) {
      MyLogUtils.e(this.TAG, paramString.getMessage());
      ((MeKeChengView.View)this.mView).setKeCheng(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\zhibo\MeKeChengPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */