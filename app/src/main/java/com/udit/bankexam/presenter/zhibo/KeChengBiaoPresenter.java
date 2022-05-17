package com.udit.bankexam.presenter.zhibo;


import com.udit.bankexam.bean.FavoriteRecord;
import com.udit.bankexam.bean.ZhiboKeChengBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.zhibo.KeChengBiaoView;
import com.udit.bankexam.view.zhibo.KeChengDetailView;
import com.udit.bankexam.view.zhibo.KeChengDetailView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;

public class KeChengBiaoPresenter extends KeChengBiaoView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();


    public KeChengBiaoPresenter(KeChengBiaoView.View mView) {
        super(mView);
    }

    @Override
    public void getKeChengBiao(String uid, String lid) {

        HashMap<String,String> map_params = new HashMap<>();
        map_params.put(Constant.Params.ACTION,IHTTP.DOGETLIVESCHEDULE);
        map_params.put(Constant.Params.UID,uid);
        map_params.put(Constant.Params.LID,lid);

        try {
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ZhiboKeChengBean> list = JsonUtil.jsonToListByArray(json,ZhiboKeChengBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setZhiboKeCheng(list);
                    }
                    else
                        mView.setZhiboKeCheng(null);
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());

        }


    }
}
