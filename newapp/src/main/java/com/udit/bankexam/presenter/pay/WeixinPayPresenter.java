package com.udit.bankexam.presenter.pay;

import com.udit.bankexam.view_ok.pay.weixinPlayView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import java.util.HashMap;
import java.util.List;

public class WeixinPayPresenter extends weixinPlayView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public WeixinPayPresenter(weixinPlayView.View paramView) { super(paramView); }
  
  public void zhifuOk(String paramString1, String paramString2, String paramString3) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doPutPurch2");
      hashMap.put("id", paramString1);
      hashMap.put("uid", paramString2);
      hashMap.put("LinkID", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.PurchBean.class);
              if (list != null && list.size() > 0) {
                ((weixinPlayView.View)WeixinPayPresenter.this.mView).zhifuOk();
                return;
              } 
              ((weixinPlayView.View)WeixinPayPresenter.this.mView).zhifuErr();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(WeixinPayPresenter.this.TAG, param1String);
              ((weixinPlayView.View)WeixinPayPresenter.this.mView).zhifuErr();
            }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((weixinPlayView.View)this.mView).zhifuErr();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\pay\WeixinPayPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */