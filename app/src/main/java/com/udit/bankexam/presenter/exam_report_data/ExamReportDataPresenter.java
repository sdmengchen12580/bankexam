package com.udit.bankexam.presenter.exam_report_data;

import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_report_data.ExamReportDataView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamReportDataPresenter extends ExamReportDataView.Presenter {
    public ExamReportDataPresenter(ExamReportDataView.View mView) {
        super(mView);
    }

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void getReport(String uid) {

    }

    @Override
    public void getReportDTK(String uid) {

    }

    @Override
    public void getSJ(String uid) {
        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETPURCH);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.PTYPE,Constant.DataType.TYPE_SHIJUAN);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<PurchBean> list = JsonUtil.jsonToListByArray(json,PurchBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setSJ(list);
                    }
                    else
                    {
                        mView.setSJ(null);
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.setSJ(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MyLogUtils.e(TAG,e.getMessage());
            mView.setSJ(null);
        }

    }
}
