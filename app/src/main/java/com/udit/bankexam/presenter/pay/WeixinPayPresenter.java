package com.udit.bankexam.presenter.pay;

import com.udit.bankexam.bean.PayInfo;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.WeixinPayInfo;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.pay.weixinPlayView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/4.
 */

public class WeixinPayPresenter extends weixinPlayView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public WeixinPayPresenter(weixinPlayView.View mView) {
        super(mView);
    }


    @Override
    public void zhifuOk(String id,String uid,String linkId) {
        try {
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOPUTPURCH2);
            map_params.put(Constant.Params.ID,id);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.LINKID,linkId);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<PurchBean> list = JsonUtil.jsonToListByArray(json,PurchBean.class);
                    if(list!=null &&list.size()>0)
                    {
                        mView.zhifuOk();
                    }
                    else
                        mView.zhifuErr();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.zhifuErr();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.zhifuErr();
        }

    }
}
