package com.udit.bankexam.presenter.exam_report_data;


import android.content.Context;

import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.ReportBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_report_data.ExamReportDataDetailView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamReportDataDetailPresenter extends ExamReportDataDetailView.Presenter {

    public ExamReportDataDetailPresenter(ExamReportDataDetailView.View mView) {
        super(mView);
    }

    @Override
    public void getRep(String uid, String eid, Context context) {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            ProgressUtils.showProgressDlg("", context);
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETREPPRACTICE);

            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.EID,eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ReportBean> list = JsonUtil.jsonToListByArray(json, ReportBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setReport(list.get(0));
                    }
                    else
                    {
                        mView.setReport(null);
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setReport(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.setReport(null);
            ProgressUtils.stopProgressDlg();
        }

    }
}
