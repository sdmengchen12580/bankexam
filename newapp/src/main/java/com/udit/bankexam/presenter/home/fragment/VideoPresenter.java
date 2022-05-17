package com.udit.bankexam.presenter.home.fragment;

import com.udit.bankexam.bean_ok.VideoTypeTwoBean;
import com.udit.bankexam.view_ok.home.fragment.VideoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class VideoPresenter extends VideoView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public VideoPresenter(VideoView.View paramView) { super(paramView); }
  
  public void getTypeList(String paramString1, String paramString2) {
    try {
      ((VideoView.View)this.mView).showProgressDialog("");
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetVideoCatalog");
      hashMap.put("uid", paramString1);
      hashMap.put("VType", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, VideoTypeTwoBean.class);
              if (list != null && list.size() > 0) {
                ((VideoView.View)VideoPresenter.this.mView).setTypeList(list);
              } else {
                ((VideoView.View)VideoPresenter.this.mView).setTypeList(null);
              } 
              ((VideoView.View)VideoPresenter.this.mView).dismissProgressDialog();
            }
            
            public void onError(String param1String) {
              ((VideoView.View)VideoPresenter.this.mView).setTypeList(null);
              ((VideoView.View)VideoPresenter.this.mView).dismissProgressDialog();
            }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((VideoView.View)this.mView).setTypeList(null);
      ((VideoView.View)this.mView).dismissProgressDialog();
      return;
    } 
  }
  
  public void getTypeList(String paramString1, String paramString2, String paramString3) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetAdvID");
      hashMap.put("uid", paramString1);
      hashMap.put("path", paramString2);
      hashMap.put("title", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, VideoTypeTwoBean.class);
              if (list != null && list.size() > 0) {
                ((VideoView.View)VideoPresenter.this.mView).setTypeTwo((VideoTypeTwoBean)list.get(0));
                return;
              } 
              ((VideoView.View)VideoPresenter.this.mView).setTypeTwo(null);
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(VideoPresenter.this.TAG, param1String);
              ((VideoView.View)VideoPresenter.this.mView).setTypeTwo(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((VideoView.View)this.mView).setTypeTwo(null);
      return;
    } 
  }
  
  public void getTypeOne(String paramString) {
    try {
      ((VideoView.View)this.mView).showProgressDialog("");
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetVideoTypeNew");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.VideoTypeOneBean.class);
              if (list != null && list.size() > 0) {
                ((VideoView.View)VideoPresenter.this.mView).setTypeOne(list);
              } else {
                ((VideoView.View)VideoPresenter.this.mView).setTypeOne(null);
              } 
              ((VideoView.View)VideoPresenter.this.mView).dismissProgressDialog();
            }
            
            public void onError(String param1String) {
              ((VideoView.View)VideoPresenter.this.mView).setTypeOne(null);
              ((VideoView.View)VideoPresenter.this.mView).dismissProgressDialog();
            }
          });
      return;
    } catch (Exception paramString) {
      MyLogUtils.e(this.TAG, paramString.getMessage());
      ((VideoView.View)this.mView).setTypeOne(null);
      ((VideoView.View)this.mView).dismissProgressDialog();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\home\fragment\VideoPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */