package com.udit.bankexam.presenter.exam;

import android.content.Context;
import com.udit.bankexam.bean_ok.ExamBean;
import com.udit.bankexam.utils_ok.ExamUtils;
import com.udit.bankexam.view_ok.exam.ExamView;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.MyNetUtils;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamPresenter extends ExamView.Presenter {
  private final String TAG = getClass().getSimpleName();

  public ExamPresenter(ExamView.View paramView) { super(paramView); }

  public void getExamList(Context paramContext, String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      ProgressUtils.showProgressDlg("加载试题中", paramContext);
      hashMap.put("action", "doGetExaminTitles");
      hashMap.put("idlist", paramString1);
      hashMap.put("uid", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", ExamBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamView.View)ExamPresenter.this.mView).setExamList(list);
                } else {
                  ((ExamView.View)ExamPresenter.this.mView).setExamList(null);
                } 
              } else {
                ((ExamView.View)ExamPresenter.this.mView).setExamList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamPresenter.this.TAG, param1String);
              ((ExamView.View)ExamPresenter.this.mView).setExamList(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamView.View)this.mView).setExamList(null);
      ((ExamView.View)this.mView).showLongToast("网络异常，请检查网络后操作");
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }

  public void getRobotExamList(Context paramContext, String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      ProgressUtils.showProgressDlg("加载试题中", paramContext);
      hashMap.put("action", "doGetPractTitles");
      hashMap.put("idlist", paramString1);
      hashMap.put("uid", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", ExamBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamView.View)ExamPresenter.this.mView).setExamList(list);
                } else {
                  ((ExamView.View)ExamPresenter.this.mView).setExamList(null);
                } 
              } else {
                ((ExamView.View)ExamPresenter.this.mView).setExamList(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamPresenter.this.TAG, param1String);
              ((ExamView.View)ExamPresenter.this.mView).setExamList(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamView.View)this.mView).setExamList(null);
      ((ExamView.View)this.mView).showLongToast("网络异常，请检查网络后操作");
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }

  public void quxiaoshoucang(Context paramContext, String paramString1, String paramString2, String paramString3) {
    ProgressUtils.showProgressDlg("取消收藏中", paramContext);
    hashMap = new HashMap();
    try {
      hashMap.put("action", "doDelFavorite");
      hashMap.put("uid", paramString1);
      hashMap.put("LinkID", paramString2);
      hashMap.put("FType", paramString3);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.FavoriteRecord.class);
              if (list != null && list.size() > 0) {
                ((ExamView.View)ExamPresenter.this.mView).shoucangOK(list, false);
              } else {
                ((ExamView.View)ExamPresenter.this.mView).shoucangErr();
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamView.View)ExamPresenter.this.mView).shoucangErr();
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception hashMap) {
      hashMap.printStackTrace();
      ((ExamView.View)this.mView).shoucangErr();
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }

  public void setSolution(Context paramContext, String paramString1, String paramString2) {
    ProgressUtils.showProgressDlg("正在提交中", paramContext);
    hashMap = new HashMap();
    try {
      hashMap.put("uid", paramString1);
      hashMap.put("slist", paramString2);
      hashMap.put("action", "doPutExaminScantron");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonArrayOk(param1String)) {
                ((ExamView.View)ExamPresenter.this.mView).setSolutionOK();
              } else {
                ((ExamView.View)ExamPresenter.this.mView).setSolutionErr();
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamPresenter.this.TAG, param1String);
              ((ExamView.View)ExamPresenter.this.mView).setSolutionErr();
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception hashMap) {
      MyLogUtils.e(this.TAG, hashMap.getMessage());
      ((ExamView.View)this.mView).setSolutionErr();
      ((ExamView.View)this.mView).showLongToast("网络异常，请检查网络后操作");
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }

  public void setSolutionList(Context paramContext, String paramString, List<ExamBean> paramList) {
    ProgressUtils.showProgressDlg("正在提交中", paramContext);
    if (!MyNetUtils.checkNetwork(BaseApplication.getInstance())) {
      ((ExamView.View)this.mView).showLongToast("网络异常，请检查网络");
      return;
    } 
    final List list_ids = ExamUtils.getExamBeanLists(paramList, 50);
    byte b = 0;
    while (true) {
      if (b < list.size()) {
        hashMap = new HashMap();
        try {
          hashMap.put("uid", paramString);
          hashMap.put("slist", list.get(b));
          hashMap.put("action", "doPutExaminScantron");
          setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
                public void doHttpResponse(String param1String) {
                  if (JsonUtil.getJsonArrayOk(param1String)) {
                    ((ExamView.View)ExamPresenter.this.mView).setSolutionOK(list_ids.size(), 1);
                    return;
                  } 
                  ((ExamView.View)ExamPresenter.this.mView).setSolutionErr();
                }
                
                public void onError(String param1String) { ((ExamView.View)ExamPresenter.this.mView).setSolutionErr(); }
              });
        } catch (Exception hashMap) {
          ((ExamView.View)this.mView).setSolutionErr();
        } 
        b++;
        continue;
      } 
      return;
    } 
  }

  public void setSolutionZhiNeng(Context paramContext, String paramString1, String paramString2) {
    HashMap hashMap = new HashMap();
    try {
      ProgressUtils.showProgressDlg("正在提交中", paramContext);
      hashMap.put("uid", paramString1);
      hashMap.put("slist", paramString2);
      hashMap.put("action", "doPutPractScantron");
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonArrayOk(param1String)) {
                ((ExamView.View)ExamPresenter.this.mView).setSolutionOK();
              } else {
                ((ExamView.View)ExamPresenter.this.mView).setSolutionErr();
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              MyLogUtils.e(ExamPresenter.this.TAG, param1String);
              ((ExamView.View)ExamPresenter.this.mView).setSolutionErr();
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      MyLogUtils.e(this.TAG, paramContext.getMessage());
      ((ExamView.View)this.mView).setSolutionErr();
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }

  public void setSolutionZhiNeng(Context paramContext, String paramString, List<ExamBean> paramList) {
    ProgressUtils.showProgressDlg("正在提交中", paramContext);
    if (!MyNetUtils.checkNetwork(BaseApplication.getInstance())) {
      ((ExamView.View)this.mView).showLongToast("网络异常，请检查网络");
      return;
    } 
    final List list_ids = ExamUtils.getExamBeanLists(paramList, 50);
    byte b = 0;
    while (true) {
      if (b < list.size()) {
        hashMap = new HashMap();
        try {
          hashMap.put("uid", paramString);
          hashMap.put("slist", list.get(b));
          hashMap.put("action", "doPutPractScantron");
          setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
                public void doHttpResponse(String param1String) {
                  if (JsonUtil.getJsonArrayOk(param1String)) {
                    ((ExamView.View)ExamPresenter.this.mView).setSolutionOK(list_ids.size(), 1);
                    return;
                  } 
                  ((ExamView.View)ExamPresenter.this.mView).setSolutionErr();
                }
                
                public void onError(String param1String) { ((ExamView.View)ExamPresenter.this.mView).setSolutionErr(); }
              });
        } catch (Exception hashMap) {
          ((ExamView.View)this.mView).setSolutionErr();
        } 
        b++;
        continue;
      } 
      return;
    } 
  }

  public void shoucang(Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4) {
    hashMap = new HashMap();
    try {
      hashMap.put("action", "doPutFavorite");
      hashMap.put("uid", paramString1);
      hashMap.put("ATime", MyDateUtil.getDate("yyyy-MM-dd HH:mm:ss"));
      hashMap.put("LinkID", paramString3);
      hashMap.put("FType", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.FavoriteRecord.class);
              if (list != null && list.size() > 0) {
                ((ExamView.View)ExamPresenter.this.mView).shoucangOK(list, true);
                return;
              } 
              ((ExamView.View)ExamPresenter.this.mView).shoucangErr();
            }
            
            public void onError(String param1String) { ((ExamView.View)ExamPresenter.this.mView).shoucangErr(); }
          });
      return;
    } catch (Exception hashMap) {
      hashMap.printStackTrace();
      ((ExamView.View)this.mView).showLongToast("网络异常，请检查网络后操作");
      ((ExamView.View)this.mView).shoucangErr();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam\ExamPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */