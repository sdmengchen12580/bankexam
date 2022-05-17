package com.udit.bankexam.presenter.exam_err;

import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamNoteBean;
import com.udit.bankexam.bean.NoteBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_err.ExamErrTitleView;
import com.udit.bankexam.view.exam_err.NoteView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyDateUtil;
import com.udit.frame.utils.MyLogUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/19.
 */

public class NotePresenter extends NoteView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public NotePresenter(NoteView.View mView) {
        super(mView);
    }


    @Override
    public void setBJ(String uid, String titleId, final String note) {

        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DOPUTNOTE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.IDS,titleId);
            map_params.put(Constant.Params.NDATE, MyDateUtil.getDate(MyDateUtil.DATE_FORMAT_1));
            map_params.put(Constant.Params.NOTE,note);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamNoteBean> list = JsonUtil.jsonToListByArray(json,ExamNoteBean.class);
                    if(list!=null &&list.size()>0)
                    {
                        mView.setBJ(list.get(0));
                    }
                    else
                    {
                        mView.setBJ(null);
                    }
                }

                @Override
                public void onError(String errStr) {
                    mView.setBJ(null);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            MyLogUtils.e(TAG,e.getMessage());
            mView.setBJ(null);
        }


    }
}


