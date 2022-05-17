package com.udit.bankexam.presenter.exam_err;

import android.content.Context;

import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_err.ExamErrView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamErrPresenter extends ExamErrView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public ExamErrPresenter(ExamErrView.View mView) {
        super(mView);
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

    @Override
    public void getHomeSJDTK(Context mContext, String uid, String eid) {

        try {
            ProgressUtils.showProgressDlg("",mContext);

            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOEXAMINTITLE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.EID,eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list = JsonUtil.jsonToListByArray(json,ExamTitleBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setExamTitle(list);
                    }
                    else
                    {
                        mView.setExamTitle(null);
                    }

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setExamTitle(null);


                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setExamTitle(null);

            ProgressUtils.stopProgressDlg();
        }
    }
}
