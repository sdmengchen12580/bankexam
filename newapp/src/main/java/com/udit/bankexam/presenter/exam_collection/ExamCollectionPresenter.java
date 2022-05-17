package com.udit.bankexam.presenter.exam_collection;

import android.content.Context;
import com.udit.bankexam.view_ok.exam_collection.ExamCollectionView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;
import java.util.HashMap;
import java.util.List;

public class ExamCollectionPresenter extends ExamCollectionView.Presenter {
  public ExamCollectionPresenter(ExamCollectionView.View paramView) { super(paramView); }
  
  public void getCollection(Context paramContext, String paramString) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetFavorite");
      hashMap.put("uid", paramString);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              List list = JsonUtil.jsonToListByArray(param1String, com.udit.bankexam.bean_ok.FavoriteRecord.class);
              if (list != null && list.size() > 0) {
                ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setFavorite(list);
              } else {
                ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setFavorite(null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setFavorite(null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ExamCollectionView.View)this.mView).setFavorite(null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getExam(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetExaminTitles");
      hashMap.put("uid", paramString1);
      hashMap.put("idlist", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setExams(list, "试题");
                } else {
                  ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setExams(null, null);
                } 
              } else {
                ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setExams(null, null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setExams(null, null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ExamCollectionView.View)this.mView).setExams(null, null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
  
  public void getExamZhineng(Context paramContext, String paramString1, String paramString2) {
    try {
      ProgressUtils.showProgressDlg("", paramContext);
      HashMap hashMap = new HashMap();
      hashMap.put("action", "doGetPractTitles");
      hashMap.put("uid", paramString1);
      hashMap.put("idlist", paramString2);
      setHttp(hashMap, "http://yk.yinhangzhaopin.com/bshApp/AppAction?", new IHttpResponseListener() {
            public void doHttpResponse(String param1String) {
              if (JsonUtil.getJsonForOK(param1String)) {
                List list = JsonUtil.jsonToList(param1String, "list", com.udit.bankexam.bean_ok.ExamBean.class);
                if (list != null && list.size() > 0) {
                  ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setExams(list, "专项智能");
                } else {
                  ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setExams(null, null);
                } 
              } else {
                ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setExams(null, null);
              } 
              ProgressUtils.stopProgressDlg();
            }
            
            public void onError(String param1String) {
              ((ExamCollectionView.View)ExamCollectionPresenter.this.mView).setExams(null, null);
              ProgressUtils.stopProgressDlg();
            }
          });
      return;
    } catch (Exception paramContext) {
      ((ExamCollectionView.View)this.mView).setExams(null, null);
      ProgressUtils.stopProgressDlg();
      return;
    } 
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\presenter\exam_collection\ExamCollectionPresenter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */