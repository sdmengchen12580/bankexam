package com.udit.bankexam.presenter.exam_collection;

import android.content.Context;

import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_collection.ExamCollectionView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamCollectionPresenter extends ExamCollectionView.Presenter {
    public ExamCollectionPresenter(ExamCollectionView.View mView) {
        super(mView);
    }

    @Override
    public void getCollection(Context mContext, String uid) {

        try {

            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETFAVORITE);
            map_params.put(Constant.Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<FavoriteRecord> list = JsonUtil.jsonToListByArray(json,FavoriteRecord.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setFavorite(list);
                    }
                    else
                        mView.setFavorite(null);

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setFavorite(null);
                    ProgressUtils.stopProgressDlg();

                }
            });
        } catch (Exception e) {
            mView.setFavorite(null);
            ProgressUtils.stopProgressDlg();
        }

    }

    @Override
    public void getExam(Context mContext, String uid, String ids) {

        try {
            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETEXAMINTITLES);
            map_params.put(Constant.Params.UID,uid);

            map_params.put(Constant.Params.IDLIST,ids);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        List<ExamBean> list = JsonUtil.jsonToList(json,"list",ExamBean.class);
                        if(list!=null && list.size()>0)
                        {
                            mView.setExams(list,Constant.DataType.TYPE_SHITI);
                        }
                        else
                            mView.setExams(null,null);
                    }
                    else
                        mView.setExams(null,null);
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setExams(null,null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.setExams(null,null);
            ProgressUtils.stopProgressDlg();
        }


    }

    @Override
    public void getExamZhineng(Context mContext, String uid, String ids) {
        try {
            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETPRACTTITLES);
            map_params.put(Constant.Params.UID,uid);

            map_params.put(Constant.Params.IDLIST,ids);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        List<ExamBean> list = JsonUtil.jsonToList(json,"list",ExamBean.class);
                        if(list!=null && list.size()>0)
                        {
                            mView.setExams(list,Constant.DataType.TYPE_ZHINENG);
                        }
                        else
                            mView.setExams(null,null);
                    }
                    else
                        mView.setExams(null,null);
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setExams(null,null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.setExams(null,null);
            ProgressUtils.stopProgressDlg();
        }
    }
}
