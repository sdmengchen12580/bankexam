package com.udit.bankexam.presenter.exam;



import android.content.Context;

import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam.ExamJiuCuoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;

public class ExamJiuCuoPresenter extends ExamJiuCuoView.Presenter {
    private final String TAG = this.getClass().getSimpleName();


    public ExamJiuCuoPresenter(ExamJiuCuoView.View mView) {
        super(mView);
    }

    @Override
    public void jiucuo(Context mContext, String uid, String id, String info) {

        try {

            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOPUTEXAMINTITLEERR);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.IDS,id);
            map_params.put(Constant.Params.INTRFO,info);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonArrayOk(json))
                    {
                        mView.setJiCuo(true);
                    }
                    else
                        mView.setJiCuo(false);

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setJiCuo(false);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
          //  e.printStackTrace();
            mView.setJiCuo(false);

            ProgressUtils.stopProgressDlg();
        }


    }
}
