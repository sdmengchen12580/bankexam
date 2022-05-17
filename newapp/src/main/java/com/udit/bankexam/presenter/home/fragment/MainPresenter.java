package com.udit.bankexam.presenter.home.fragment;

import android.content.Context;
import android.util.Log;
import com.google.gson.Gson;
import com.udit.bankexam.bean_ok.ExamInfoBean;
import com.udit.bankexam.bean_ok.ModuleBean;
import com.udit.bankexam.bean_ok.ZhaoPinInfo;
import com.udit.bankexam.view_ok.home.fragment.MainView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainPresenter extends MainView.Presenter {
  private final String TAG = getClass().getSimpleName();
  
  public MainPresenter(MainView.View paramView) { super(paramView); }
  
  public void checkHomeNode(String paramString1, String paramString2) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doFreOutline");
      hashMap.put("uid", paramString1);
      hashMap.put("OID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamNodeBean.class);
                if (list != null && list.size() > 0)
                  ((MainView.View)MainPresenter.this.mView).checkHomeNode(list); 
              } 
            }
            
            public void onError(String param1String) {}
          });
      return;
    } catch (Exception paramString1) {
      paramString1.printStackTrace();
      return;
    } 
  }
  
  public void getAdv() {
    hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetAdv");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.AdvBean.class);
                if (list != null && list.size() > 0) {
                  ((MainView.View)MainPresenter.this.mView).setAdv(list);
                  return;
                } 
                ((MainView.View)MainPresenter.this.mView).setAdv(null);
                return;
              } 
              ((MainView.View)MainPresenter.this.mView).setAdv(null);
            }
            
            public void onError(String param1String) { ((MainView.View)MainPresenter.this.mView).setAdv(null); }
          });
      return;
    } catch (Exception hashMap) {
      ((MainView.View)this.mView).setAdv(null);
      return;
    } 
  }
  
  public void getExamNode(String paramString) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetFirstHis");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamNodeBean.class);
                if (list != null && list.size() > 0) {
                  ((MainView.View)MainPresenter.this.mView).setExamNode(list);
                  return;
                } 
                ((MainView.View)MainPresenter.this.mView).setExamNode(null);
                return;
              } 
              ((MainView.View)MainPresenter.this.mView).setExamNode(null);
            }
            
            public void onError(String param1String) { ((MainView.View)MainPresenter.this.mView).setExamNode(null); }
          });
      return;
    } catch (Exception paramString) {
      MyLogUtils.e(this.TAG, paramString.getMessage());
      ((MainView.View)this.mView).setExamNode(null);
      return;
    } 
  }
  
  public void getHomeExam(Context paramContext, String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      hashMap.put("action", "doOutlineTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("OID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((MainView.View)MainPresenter.this.mView).setHomeExam(list);
              } else {
                ((MainView.View)MainPresenter.this.mView).setHomeExam(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(MainPresenter.this.TAG, param1String);
              ((MainView.View)MainPresenter.this.mView).setHomeExam(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((MainView.View)this.mView).setHomeExam(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getHomeSJ(String paramString) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doGetExamin");
      hashMap.put("uid", paramString);
      hashMap.put("TypeInfo", "首页");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, ExamInfoBean.class);
              if (list != null && list.size() > 0) {
                ((MainView.View)MainPresenter.this.mView).setHomeExmaSJ((ExamInfoBean)list.get(0));
                return;
              } 
              ((MainView.View)MainPresenter.this.mView).setHomeExmaSJ(null);
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(MainPresenter.this.TAG, param1String);
              ((MainView.View)MainPresenter.this.mView).setHomeExmaSJ(null);
            }
          });
      return;
    } catch (Exception paramString) {
      MyLogUtils.e(this.TAG, paramString.getMessage());
      ((MainView.View)this.mView).setHomeExmaSJ(null);
      return;
    } 
  }
  
  public void getHomeSJDTK(String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      hashMap.put("action", "doExaminTitle");
      hashMap.put("uid", paramString1);
      hashMap.put("EID", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.ExamTitleBean.class);
              if (list != null && list.size() > 0) {
                ((MainView.View)MainPresenter.this.mView).setHomeExamDTK(list);
                return;
              } 
              ((MainView.View)MainPresenter.this.mView).setHomeExamDTK(null);
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(MainPresenter.this.TAG, param1String);
              ((MainView.View)MainPresenter.this.mView).setHomeExamDTK(null);
            }
          });
      return;
    } catch (Exception paramString1) {
      MyLogUtils.e(this.TAG, paramString1.getMessage());
      ((MainView.View)this.mView).setHomeExamDTK(null);
      return;
    } 
  }
  
  public void getModule() {
    ModuleBean moduleBean1 = new ModuleBean();
    ModuleBean moduleBean2 = new ModuleBean();
    ModuleBean moduleBean3 = new ModuleBean();
    ModuleBean moduleBean4 = new ModuleBean();
    ModuleBean moduleBean5 = new ModuleBean();
    ArrayList arrayList = new ArrayList();
    arrayList.add(moduleBean1);
    arrayList.add(moduleBean2);
    arrayList.add(moduleBean3);
    arrayList.add(moduleBean4);
    arrayList.add(moduleBean5);
    (new ArrayList()).add(arrayList);
    moduleBean1.setTitle("最新招聘");
    moduleBean1.setImg(2131493080);
    moduleBean2.setTitle("网申模拟");
    moduleBean2.setImg(2131493068);
    moduleBean3.setTitle("历年真题");
    moduleBean3.setImg(2131493075);
    moduleBean4.setTitle("独家密卷");
    moduleBean4.setImg(2131493069);
    moduleBean5.setTitle("模考大赛");
    moduleBean5.setImg(2131493070);
  }
  
  public void getModule_net() {
    hashMap = new HashMap();
    try {
      hashMap.put("action", "getModule");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", ModuleBean.class);
                if (list != null && list.size() > 0) {
                  ((MainView.View)MainPresenter.this.mView).setModule(list);
                  return;
                } 
                ((MainView.View)MainPresenter.this.mView).setModule(null);
                return;
              } 
              ((MainView.View)MainPresenter.this.mView).setModule(null);
            }
            
            public void onError(String param1String) { ((MainView.View)MainPresenter.this.mView).setModule(null); }
          });
      return;
    } catch (Exception hashMap) {
      ((MainView.View)this.mView).setModule(null);
      return;
    } 
  }
  
  public void getTypeOne(String paramString) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetVideoTypeNew");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.VideoTypeOneBean.class);
              if (list != null && list.size() > 0) {
                ((MainView.View)MainPresenter.this.mView).setTypeOne(list);
                return;
              } 
              ((MainView.View)MainPresenter.this.mView).setTypeOne(null);
            }
            
            public void onError(String param1String) { ((MainView.View)MainPresenter.this.mView).setTypeOne(null); }
          });
      return;
    } catch (Exception paramString) {
      MyLogUtils.e(this.TAG, paramString.getMessage());
      ((MainView.View)this.mView).setTypeOne(null);
      return;
    } 
  }
  
  public void getZpList() {
    hashMap = new HashMap();
    try {
      setHttp(hashMap, "http://yhyk.project.njagan.org//dw.yikao/api/yikao/index/getIndex", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              StringBuilder stringBuilder = new StringBuilder();
              stringBuilder.append("date=");
              stringBuilder.append(param1String);
              Log.e("doHttpResponse: ", stringBuilder.toString());
              ZhaoPinInfo zhaoPinInfo = (ZhaoPinInfo)(new Gson()).fromJson(param1String, ZhaoPinInfo.class);
              if (zhaoPinInfo.getCode() == 200) {
                ((MainView.View)MainPresenter.this.mView).setWork(zhaoPinInfo.getData().getResponse().getZhaopins());
                return;
              } 
              ((MainView.View)MainPresenter.this.mView).setWork(null);
            }
            
            public void onError(String param1String) { ((MainView.View)MainPresenter.this.mView).setWork(null); }
          });
      return;
    } catch (Exception hashMap) {
      hashMap.printStackTrace();
      ((MainView.View)this.mView).setWork(null);
      return;
    } 
  }
  
  public void saveShouYe(String paramString1, final String linkid, String paramString3) {
    HashMap hashMap = new HashMap();
    hashMap.put("action", "doPutPurch");
    hashMap.put("uid", paramString1);
    hashMap.put("PType", "试卷");
    hashMap.put("FeeDate", MyDateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
    hashMap.put("LinkID", paramString2);
    hashMap.put("Fee", "0");
    hashMap.put("Abstract", paramString3);
    hashMap.put("Intro", "免费");
    hashMap.put("PState", "已支付");
    try {
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, ExamInfoBean.class);
              if (list != null && list.size() > 0)
                ((MainView.View)MainPresenter.this.mView).saveExamShouYe(linkid); 
            }
            
            public void onError(String param1String) {}
          });
      return;
    } catch (Exception paramString1) {
      return;
    } 
  }
  
  public void shoucang(String paramString) {
    try {
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetFavorite");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.FavoriteRecord.class);
              if (list != null && list.size() > 0) {
                ((MainView.View)MainPresenter.this.mView).saveShoucang(list);
                return;
              } 
              ((MainView.View)MainPresenter.this.mView).saveShoucang(null);
            }
            
            public void onError(String param1String) { ((MainView.View)MainPresenter.this.mView).saveShoucang(null); }
          });
      return;
    } catch (Exception paramString) {
      paramString.printStackTrace();
      ((MainView.View)this.mView).saveShoucang(null);
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\home\fragment\MainPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */