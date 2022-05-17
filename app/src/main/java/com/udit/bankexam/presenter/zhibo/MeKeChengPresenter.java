package com.udit.bankexam.presenter.zhibo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.zhibo.MeKeChengView;
import com.udit.bankexam.view.zhibo.MeKeChengView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

public class MeKeChengPresenter extends MeKeChengView.Presenter
{
    private  final String TAG = this.getClass().getSimpleName();
    public MeKeChengPresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void getMyKeCheng(String uid)
    {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETSCHEDULEZHIBO);
            map_params.put(Constant.Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ZhiBoBean> list = JsonUtil.jsonToListByArray(json,ZhiBoBean.class);

                    if(list!=null &&list.size()>0)
                    {
                        mView.setKeCheng(list);
                    }
                    else
                        mView.setKeCheng(null);

                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setKeCheng(null);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setKeCheng(null);

        }

    }

  
}
