package com.udit.bankexam.presenter.pay;

import android.content.Context;
import com.udit.bankexam.bean_ok.AddressBean;
import com.udit.bankexam.bean_ok.PayInfo;
import com.udit.bankexam.bean_ok.WeixinPayInfo;
import com.udit.bankexam.view_ok.pay.PaySelecteView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class PayPresenter extends PaySelecteView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public PayPresenter(PaySelecteView.View paramView) { super(paramView); }
  
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
                ((PaySelecteView.View)PayPresenter.this.mView).getAddr((AddressBean)list.get(0));
              } else {
                ((PaySelecteView.View)PayPresenter.this.mView).getAddr(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((PaySelecteView.View)PayPresenter.this.mView).getAddr(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((PaySelecteView.View)this.mView).getAddr(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void isPay(String paramString1, String paramString2, String paramString3) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doIsPay");
      hashMap.put("uid", paramString1);
      hashMap.put("id", paramString2);
      hashMap.put("type", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonArrayOk(param1String)) {
                ((PaySelecteView.View)PayPresenter.this.mView).payOk();
                return;
              } 
              ((PaySelecteView.View)PayPresenter.this.mView).payErr();
            }
            
            public void onError(String param1String) { ((PaySelecteView.View)PayPresenter.this.mView).payErr(); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((PaySelecteView.View)this.mView).payErr();
      return;
    } 
  }
  
  public void zhifu(String paramString1, String paramString2, final String type, final String type_zhifu) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetPayInfo");
      hashMap.put("uid", paramString1);
      hashMap.put("id", paramString2);
      hashMap.put("type", paramString3);
      hashMap.put("type_pay", paramString4);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                PayInfo payInfo;
                if (type_zhifu.equals("支付宝")) {
                  payInfo = (PayInfo)JsonUtil.jsonToObject(param1String, PayInfo.class, PayInfo.class.getSimpleName());
                  if (payInfo != null) {
                    ((PaySelecteView.View)PayPresenter.this.mView).getZhifuSign(payInfo);
                    return;
                  } 
                  ((PaySelecteView.View)PayPresenter.this.mView).getZhifuSign(null);
                  return;
                } 
                if (type_zhifu.equals("微信")) {
                  WeixinPayInfo weixinPayInfo = (WeixinPayInfo)JsonUtil.jsonToObject(payInfo, WeixinPayInfo.class, "PayInfo");
                  if (weixinPayInfo != null) {
                    ((PaySelecteView.View)PayPresenter.this.mView).getWeiXinSign(weixinPayInfo);
                    return;
                  } 
                  ((PaySelecteView.View)PayPresenter.this.mView).getWeiXinSign(null);
                } 
              } 
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(PayPresenter.this.TAG, param1String);
              if (type.equals("支付宝")) {
                ((PaySelecteView.View)PayPresenter.this.mView).getZhifuSign(null);
                return;
              } 
              if (type.equals("微信"))
                ((PaySelecteView.View)PayPresenter.this.mView).getWeiXinSign(null); 
            }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      if (paramString3.equals("支付宝")) {
        ((PaySelecteView.View)this.mView).getZhifuSign(null);
        return;
      } 
      if (paramString3.equals("微信"))
        ((PaySelecteView.View)this.mView).getWeiXinSign(null); 
      return;
    } 
  }
  
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
                ((PaySelecteView.View)PayPresenter.this.mView).zhifuOk();
                return;
              } 
              ((PaySelecteView.View)PayPresenter.this.mView).zhifuErr();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(PayPresenter.this.TAG, param1String);
              ((PaySelecteView.View)PayPresenter.this.mView).zhifuErr();
            }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((PaySelecteView.View)this.mView).zhifuErr();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\pay\PayPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */