package com.udit.bankexam.presenter.video;

import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.video.VideoTypeOneView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/4/25.
 */

public class VideoTypeOnePresenter extends VideoTypeOneView.Presenter {
    private final String TAG = this.getClass().getSimpleName();

    public VideoTypeOnePresenter(VideoTypeOneView.View mView) {
        super(mView);
    }

    @Override
    public void getTypeOne(String uid) {
        try {
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETVIDEOTYPE);
            map_params.put(Constant.Params.UID,uid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {

                   List<String> list =  JsonUtil.jsonToListByValue(json,"VType");
                    if(list!=null &&list.size()>0)
                    {
                        mView.setTypeOne(list);
                    }
                    else
                        mView.setTypeOne(null);
                }

                @Override
                public void onError(String errStr) {
                    mView.setTypeOne(null);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setTypeOne(null);

        }

    }


}
