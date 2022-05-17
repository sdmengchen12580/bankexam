package com.udit.bankexam.presenter.zhibo;

import android.content.Context;
import com.udit.bankexam.bean_ok.ZhiBoBean;
import com.udit.bankexam.view_ok.zhibo.KeChengDetailView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class KeChengDetailPresenter extends KeChengDetailView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public KeChengDetailPresenter(KeChengDetailView.View paramView) { super(paramView); }
  
  public void doDelFavorite(String paramString1, String paramString2, String paramString3) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doDelFavorite");
      hashMap.put("uid", paramString1);
      hashMap.put("FType", paramString2);
      hashMap.put("LinkID", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.FavoriteRecord.class);
              if (list != null && list.size() > 0) {
                ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).setDelFavorite(list);
                return;
              } 
              ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).setDelFavorite(null);
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(KeChengDetailPresenter.this.TAG, param1String);
              ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).setDelFavorite(null);
            }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((KeChengDetailView.View)this.mView).setDelFavorite(null);
      return;
    } 
  }
  
  public void doPutFavorite(String paramString1, String paramString2, String paramString3, String paramString4) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doPutFavorite");
      hashMap.put("uid", paramString1);
      hashMap.put("FType", paramString2);
      hashMap.put("ATime", paramString4);
      hashMap.put("LinkID", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.FavoriteRecord.class);
              if (list != null && list.size() > 0) {
                ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).setFavorite(list);
                return;
              } 
              ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).setFavorite(null);
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(KeChengDetailPresenter.this.TAG, param1String);
              ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).setFavorite(null);
            }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((KeChengDetailView.View)this.mView).setFavorite(null);
      return;
    } 
  }
  
  public void getZhiboDetail(Context paramContext, String paramString1, String paramString2, String paramString3) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetAdvID");
      hashMap.put("uid", paramString1);
      hashMap.put("path", paramString2);
      hashMap.put("title", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, ZhiBoBean.class);
              if (list != null && list.size() > 0) {
                ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).setZhiboDetail((ZhiBoBean)list.get(0));
              } else {
                ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).setZhiboDetail(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(KeChengDetailPresenter.this.TAG, param1String);
              ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).setZhiboDetail(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((KeChengDetailView.View)this.mView).setZhiboDetail(null);
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
                ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).payOk();
                return;
              } 
              ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).payErr();
            }
            
            public void onError(String param1String) { ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).payErr(); }
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      ((KeChengDetailView.View)this.mView).payErr();
      return;
    } 
  }
  
  public void pay(String paramString1, String paramString2, String paramString3) {
    HashMap hashMap = new HashMap();
    hashMap.put("action", "doPutPurch");
    hashMap.put("uid", paramString1);
    hashMap.put("PType", "直播课");
    hashMap.put("FeeDate", MyDateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
    hashMap.put("LinkID", paramString2);
    hashMap.put("Fee", "0");
    hashMap.put("Abstract", paramString3);
    hashMap.put("Intro", "免费");
    hashMap.put("PState", "已支付");
    try {
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) { ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).baomingOk(); }
            
            public void onError(String param1String) { ((KeChengDetailView.View)KeChengDetailPresenter.this.mView).baomingErr(); }
          });
      return;
    } catch (Exception paramString1) {
      ((KeChengDetailView.View)this.mView).baomingErr();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\zhibo\KeChengDetailPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */