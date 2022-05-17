package com.udit.bankexam.presenter.exam_data_history;

import android.content.Context;

import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_data_history.ExamHomeHistoryView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/6/30.
 */

public class ExamHomeHistoryPresenter extends ExamHomeHistoryView.Presenter {
    public ExamHomeHistoryPresenter(ExamHomeHistoryView.View mView) {
        super(mView);
    }

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void getExamNode(Context mContext, String uid) {
        try
        {
            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETFIRSTHIS);
            map_params.put(Constant.Params.UID,uid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener()
            {

                @Override
                public void onError(String errStr)
                {
                    mView.setExamNode(null);
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void doHttpResponse(String json)
                {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        List<ExamNodeBean> list =
                                JsonUtil.jsonToList(json, "list", ExamNodeBean.class);
                        if(list!=null && list.size()>0)
                        {
                            mView.setExamNode(list);
                        }
                        else
                            mView.setExamNode(null);

                    }
                    else
                    {
                        mView.setExamNode(null);
                    }
                    ProgressUtils.stopProgressDlg();
                }
            });
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setExamNode(null);
            ProgressUtils.stopProgressDlg();
        }
    }

    @Override
    public void getExamNodeISOk(Context mContext, String uid, String oid) {
        try
        {
            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOOUTLINETITLEHIS);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.OID,oid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener()
            {

                @Override
                public void onError(String errStr)
                {
                    mView.setExamNodeIsOk(null);
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void doHttpResponse(String json)
                {

                        List<ExamTitleBean> list =
                                JsonUtil.jsonToListByArray(json,ExamTitleBean.class);
                        if(list!=null && list.size()>0)
                        {
                            mView.setExamNodeIsOk(list);
                        }
                        else
                            mView.setExamNodeIsOk(null);


                    ProgressUtils.stopProgressDlg();
                }
            });
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setExamNodeIsOk(null);
            ProgressUtils.stopProgressDlg();
        }
    }


}
