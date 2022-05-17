package com.udit.bankexam.presenter.zhibo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.udit.bankexam.bean.VideoBean;
import com.udit.bankexam.bean.ZhiBoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.zhibo.ZhiboZhongxinView;
import com.udit.bankexam.view.zhibo.ZhiboZhongxinView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

public class ZhiboZhongxinPresenter extends ZhiboZhongxinView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();

    public ZhiboZhongxinPresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void getZhiBoList(String uid)
    {
        try {
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETLIVE);
            map_params.put(Constant.Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                   List<ZhiBoBean> list =  JsonUtil.jsonToListByArray(json, ZhiBoBean.class);
                   if(list!=null && list.size()>0)
                       mView.setZhiBo(list);
                    else
                        mView.setZhiBo(null);
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.setZhiBo(null);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setZhiBo(null);
        }

    }

  

}
