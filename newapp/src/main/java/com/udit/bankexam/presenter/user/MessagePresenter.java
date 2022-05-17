package com.udit.bankexam.presenter.user;

import com.udit.bankexam.view_ok.user.MessageView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import java.util.HashMap;
import java.util.List;

public class MessagePresenter extends MessageView.Presenter {
  public MessagePresenter(MessageView.View paramView) { super(paramView); }
  
  public void getMessage(String paramString, int paramInt1, int paramInt2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetMsg");
      hashMap.put("uid", paramString);
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
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.MessageBean.class);
              if (list != null && list.size() > 0) {
                ((MessageView.View)MessagePresenter.this.mView).setMessage(list);
                return;
              } 
              ((MessageView.View)MessagePresenter.this.mView).setMessage(null);
            }
            
            public void onError(String param1String) { ((MessageView.View)MessagePresenter.this.mView).setMessage(null); }
          });
      return;
    } catch (Exception paramString) {
      ((MessageView.View)this.mView).setMessage(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presente\\user\MessagePresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */