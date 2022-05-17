package com.udit.bankexam.presenter.user;

import com.udit.bankexam.bean.MessageBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.user.MessageView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/4.
 */

public class MessagePresenter extends MessageView.Presenter {
    public MessagePresenter(MessageView.View mView) {
        super(mView);
    }

    @Override
    public void getMessage(String uid, int page, int count) {

        try {
            HashMap<String,String> map_params = new HashMap<>();

            map_params.put(Constant.Params.ACTION, IHTTP.DOGETMSG);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.NPAGE,page+"");
            map_params.put(Constant.Params.TCOUNT,count+"");

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<MessageBean> list = JsonUtil.jsonToListByArray(json,MessageBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setMessage(list);
                    }
                    else
                    {
                        mView.setMessage(null);
                    }


                }

                @Override
                public void onError(String errStr) {
                    mView.setMessage(null);
                }
            });
        } catch (Exception e) {
            mView.setMessage(null);
        }


    }
}
