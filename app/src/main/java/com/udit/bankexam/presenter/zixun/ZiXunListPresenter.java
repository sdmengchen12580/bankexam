package com.udit.bankexam.presenter.zixun;

import android.content.Context;

import com.udit.bankexam.bean.NewBean;
import com.udit.bankexam.bean.ZiXunBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.zixun.ZiXunListView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2018-06-08.
 */

public class ZiXunListPresenter extends ZiXunListView.Presenter {
    public ZiXunListPresenter(ZiXunListView.View mView) {
        super(mView);
    }

    @Override
    public void getNews(Context mContext, String type, int page, int count) {
        try {
            ProgressUtils.showProgressDlg("",mContext);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.getNews);
            map_params.put("Type",type);
            map_params.put(Constant.Params.NPAGE,page+"");
            map_params.put(Constant.Params.TCOUNT,count+"");
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<NewBean> list = JsonUtil.jsonToListByArray(json,NewBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.setNews(list);
                    }
                    else
                        mView.setNews(null);

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setNews(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.setNews(null);
            ProgressUtils.stopProgressDlg();
        }


    }
}
