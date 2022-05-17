package com.udit.bankexam.presenter.video;

import com.udit.bankexam.bean.VideoType;
import com.udit.bankexam.bean.VideoTypeTwoBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.video.VideoTypeOneView;
import com.udit.bankexam.view.video.VideoTypeTwoViewNew;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/4/25.
 */

public class VideoTypeTwoPresenter extends VideoTypeTwoViewNew.Presenter {
    private final String TAG = this.getClass().getSimpleName();

    public VideoTypeTwoPresenter(VideoTypeTwoViewNew.View mView) {
        super(mView);
    }


    @Override
    public void getTypeTwo(String uid,String typeOne) {
        try {
            mView.showProgressDialog("");
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETVIDEOCATALOGNEW);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.VTYPE,typeOne);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {

                    List<VideoType> list =  JsonUtil.jsonToList(json, "list", VideoType.class);
                    if(list!=null &&list.size()>0)
                    {
                        list.get(0).setIsfirst(true);
                        list.get(list.size()-1).setLast(true);
                        mView.setTypeTwo(list);
                    }
                    else
                        mView.setTypeTwo(null);

                    mView.dismissProgressDialog();
                }

                @Override
                public void onError(String errStr) {
                    mView.setTypeTwo(null);
                    mView.dismissProgressDialog();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setTypeTwo(null);
            mView.dismissProgressDialog();

        }
    }

    @Override
    public void getTypeTwoByID(String uid, String typeID) {
        try {
            mView.showProgressDialog("");
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETVIDEOCATALOGNEWByID);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.VTYPE,typeID);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {

                    List<VideoType> list =  JsonUtil.jsonToList(json, "list", VideoType.class);
                    if(list!=null &&list.size()>0)
                    {
                        list.get(0).setIsfirst(true);
                        list.get(list.size()-1).setLast(true);
                        mView.setTypeTwo(list);
                    }
                    else
                        mView.setTypeTwo(null);

                    mView.dismissProgressDialog();
                }

                @Override
                public void onError(String errStr) {
                    mView.setTypeTwo(null);
                    mView.dismissProgressDialog();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setTypeTwo(null);
            mView.dismissProgressDialog();

        }


    }


}
