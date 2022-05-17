package com.udit.bankexam.presenter.zhibo;


import android.content.Context;

import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.zhibo.ZhiboLiebiaoView;
import com.udit.bankexam.view.zhibo.ZhiboLiebiaoView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

public class ZhiboLiebiaoPresenter extends ZhiboLiebiaoView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();

    public ZhiboLiebiaoPresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void getZhiboList(Context mContext, String uid, String lid) {

        try {

            ProgressUtils.showProgressDlg("",mContext);

            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETLIVESCHEDULE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.LID,lid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ZhiboKeChengBean> list = JsonUtil.jsonToListByArray(json,ZhiboKeChengBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setZhiboList(list);

                    }
                    else
                    {
                        mView.setZhiboList(null);
                    }
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setZhiboList(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.setZhiboList(null);
            ProgressUtils.stopProgressDlg();
        }


    }


}
