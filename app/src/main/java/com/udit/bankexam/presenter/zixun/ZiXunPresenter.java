package com.udit.bankexam.presenter.zixun;

import android.content.Context;

import com.udit.bankexam.bean.ZiXunBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.home.fragment.ZixunView;
import com.udit.bankexam.view.zixun.ZiXunView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/6/24.
 */

public class ZiXunPresenter extends ZiXunView.Presenter {
    public ZiXunPresenter(ZiXunView.View mView) {
        super(mView);
    }

    @Override
    public void getZiXun(Context mContext, String keyword, int page, int count) {

        try {
            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETINFO);
            map_params.put(Constant.Params.KEYWORD,keyword);
            map_params.put(Constant.Params.NPAGE,page+"");
            map_params.put(Constant.Params.TCOUNT,count+"");
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ZiXunBean> list = JsonUtil.jsonToListByArray(json,ZiXunBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setZiXun(list);
                    }
                    else
                        mView.setZiXun(null);

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setZiXun(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.setZiXun(null);
            ProgressUtils.stopProgressDlg();
        }


    }
}
