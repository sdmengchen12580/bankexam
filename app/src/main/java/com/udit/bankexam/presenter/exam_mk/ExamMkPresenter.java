package com.udit.bankexam.presenter.exam_mk;

import android.content.Context;
import android.preference.PreferenceGroup;

import com.udit.bankexam.bean.MKBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_mk.ExamMkView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamMkPresenter extends ExamMkView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public ExamMkPresenter(ExamMkView.View mView) {
        super(mView);
    }

    @Override
    public void getMK(Context mContext,String uid) {
        try {
            ProgressUtils.showProgressDlg("获取模考大赛信息",mContext);
            HashMap<String,String> map_params = new HashMap<>();

            map_params.put(Constant.Params.ACTION, IHTTP.DOGETALLMOCK);
            map_params.put(Constant.Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<MKBean> list = JsonUtil.jsonToListByArray(json,MKBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setMkList(list);
                    }
                    else
                        mView.setMkList(null);
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setMkList(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setMkList(null);
            mView.showLongToast("网络异常，请检查网络后操作");
            ProgressUtils.stopProgressDlg();

        }


    }
}
