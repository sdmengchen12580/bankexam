package com.udit.bankexam.presenter.video;


import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.util.Xml;

import com.google.zxing.qrcode.encoder.Encoder;
import com.udit.bankexam.bean.VideoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.video.VideoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;


import org.apache.commons.codec.EncoderException;
import org.apache.commons.codec.StringEncoder;
import org.apache.http.util.EncodingUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/4/25.
 */

public class VideoPresenter extends VideoView.Presenter {
    private final String TAG = this.getClass().getSimpleName();

    public VideoPresenter(VideoView.View mView) {
        super(mView);
    }


    @Override
    public void getVideList(String uid, String cid) {
        try {
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETVIDEO);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.CID,cid);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {

                   // json = Html.fromHtml(json).toString();

                    MyLogUtils.e(TAG,json);
                    List<VideoBean> list =  JsonUtil.jsonToListByArray(json, VideoBean.class);
                    if(list!=null &&list.size()>0)
                    {
                        mView.setVideo(list);
                    }
                    else
                        mView.setVideo(null);
                }

                @Override
                public void onError(String errStr) {
                    mView.setVideo(null);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setVideo(null);

        }
    }
}
