package com.udit.bankexam.presenter.zhibo;

import com.udit.bankexam.view_ok.zhibo.ZhiboZhongxinView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class ZhiboZhongxinPresenter extends ZhiboZhongxinView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public ZhiboZhongxinPresenter(ZhiboZhongxinView.View paramView) { super(paramView); }
  
  public void getZhiBoList(String paramString) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetLive");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ZhiBoBean.class);
              if (list != null && list.size() > 0) {
                ((ZhiboZhongxinView.View)ZhiboZhongxinPresenter.this.mView).setZhiBo(list);
                return;
              } 
              ((ZhiboZhongxinView.View)ZhiboZhongxinPresenter.this.mView).setZhiBo(null);
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ZhiboZhongxinPresenter.this.TAG, param1String);
              ((ZhiboZhongxinView.View)ZhiboZhongxinPresenter.this.mView).setZhiBo(null);
            }
          });
      return;
    } catch (Exception paramString) {
      MyLogUtils.e(this.TAG, paramString.getMessage());
      ((ZhiboZhongxinView.View)this.mView).setZhiBo(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\zhibo\ZhiboZhongxinPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */