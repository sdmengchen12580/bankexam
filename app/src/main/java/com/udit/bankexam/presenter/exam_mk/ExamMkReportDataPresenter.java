package com.udit.bankexam.presenter.exam_mk;

import android.content.Context;

import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamNodeBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.ReportBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_mk.ExamMkReportDataView;
import com.udit.bankexam.view.exam_mk.ExamMkView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017-9-19.
 */

public class ExamMkReportDataPresenter extends ExamMkReportDataView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public ExamMkReportDataPresenter(ExamMkReportDataView.View mView) {
        super(mView);
    }

    @Override
    public void getMkRepPractice(String uid, String eid) {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETREPPRACTICE);

            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.EID,eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ReportBean> list = JsonUtil.jsonToListByArray(json, ReportBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setMkRep(list.get(0));
                    }
                    else
                    {
                        mView.setMkRep(null);
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.setMkRep(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setMkRep(null);
        }
    }

    @Override
    public void getMkExamNode(String uid, String eid) {
        try
        {
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETOUTLINE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.EID,eid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener()
            {

                @Override
                public void onError(String errStr)
                {
                    mView.setMkExamNode(null);
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
                            mView.setMkExamNode(list);
                        }
                        else
                            mView.setMkExamNode(null);

                    }
                    else
                    {
                        mView.setMkExamNode(null);
                    }
                }
            });
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setMkExamNode(null);
        }

    }

    @Override
    public void getMKDTK(String uid, String eid) {
        try {
           // ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOEXAMINTITLE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.EID,eid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {

                    List<ExamTitleBean> list = JsonUtil.jsonToListByArray(json,ExamTitleBean.class);
                    if(list!=null)
                    {
                        mView.setMkExamTdk(list);
                    }
                    else
                        mView.setMkExamTdk(null);


                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setMkExamTdk(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.setMkExamTdk(null);
            ProgressUtils.stopProgressDlg();
        }
    }
}
