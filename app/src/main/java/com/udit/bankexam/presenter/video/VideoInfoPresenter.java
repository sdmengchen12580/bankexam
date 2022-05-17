package com.udit.bankexam.presenter.video;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;
import com.udit.bankexam.bean.ExamInfoBean;
import com.udit.bankexam.bean.ExamTitleBean;
import com.udit.bankexam.bean.VideoDetailsBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.video.VideoInfoView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/4/25.
 */

public class VideoInfoPresenter extends VideoInfoView.Presenter {
    private final String TAG = this.getClass().getSimpleName();

    public VideoInfoPresenter(VideoInfoView.View mView) {
        super(mView);
    }

    @Override
    public void getExamInfo(Context mContext, String uid, String eid) {
        try {
            ProgressUtils.showProgressDlg("获取试卷中", mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETEXAMIN);
            map_params.put(Constant.Params.UID, uid);
            map_params.put(Constant.Params.EID, eid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamTitleBean> list = JsonUtil.jsonToListByArray(json, ExamTitleBean.class);

                    if (list != null && list.size() > 0) {
                        mView.setExamInfo(list);
                    } else
                        mView.setExamInfo(null);

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setExamInfo(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.setExamInfo(null);
            ProgressUtils.stopProgressDlg();
        }

    }

    @Override
    public void saveExam(String uid, String linkid, String name) {
        HashMap<String, String> map_params = new HashMap<>();
        map_params.put(Constant.Params.ACTION, IHTTP.DOPUTPURCH);
        map_params.put(Constant.Params.UID, uid);
        map_params.put(Constant.Params.PTYPE, Constant.DataType.TYPE_SHIJUAN);
        map_params.put(Constant.Params.FEEDATE, MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
        map_params.put(Constant.Params.LINKID, linkid);
        map_params.put(Constant.Params.FEE, "0");
        map_params.put(Constant.Params.ABSTRACT, name);
        map_params.put(Constant.Params.INTRO, Constant.PAY.PAY_MIANFEI);
        map_params.put(Constant.Params.PSTATE, Constant.PAY.PAY_STATUS_OK);

        try {
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                   /* List<ExamInfoBean> list = JsonUtil.jsonToListByArray(json,ExamInfoBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.saveExamShouYe(linkid);
                    }*/
                }

                @Override
                public void onError(String errStr) {

                }
            });
        } catch (Exception e) {

        }
    }

    @Override
    public void getVideoUrl(String videoIdAli) {
        if (null == videoIdAli || videoIdAli.equals("")) {
            mView.playVideo(false, "网络异常,视频播放失败，请退出重试~");
            return;
        }
        HashMap<String, String> map_params = new HashMap<>();
//        map_params.put(Constant.Params.ACTION, IHTTP.DOPUTPURCH);
        map_params.put("videoIdAli", videoIdAli);
        try {
            setHttp(map_params, IHTTP.AGANYUN_IP + "yikao/ykVideo/detail", new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    Log.e("测试程序: ", "数据=json" + json);
                    try {
                        VideoDetailsBean videoDetailsBean = new Gson().fromJson(json, VideoDetailsBean.class);
                        mView.playVideo(true, videoDetailsBean.data.response.row.videoUrl);
                    }catch (Exception e){
                        mView.playVideo(false, "网络异常,视频播放失败，请退出重试~");
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.playVideo(false, "网络异常,视频播放失败，请退出重试~");
                }
            });
        } catch (Exception e) {
            mView.playVideo(false, "网络异常,视频播放失败，请退出重试~");
        }
    }

}
