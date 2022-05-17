package com.udit.bankexam.presenter.exam_notebook;

import android.content.Context;

import com.udit.bankexam.bean.ExamBean;
import com.udit.bankexam.bean.ExamNoteBean;
import com.udit.bankexam.bean.NoteBean;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.exam_notebook.ExamNoteBookView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamNoteBookPresenter extends ExamNoteBookView.Presenter {
    public ExamNoteBookPresenter(ExamNoteBookView.View mView) {
        super(mView);
    }

    private final String TAG = this.getClass().getSimpleName();

    @Override
    public void getNotes(Context mContext, String uid) {

        try {

            ProgressUtils.showProgressDlg("",mContext);

            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETNOTES);
            map_params.put(Constant.Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamBean> list = JsonUtil.jsonToList(json,"list",ExamBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setNotes(list);
                    }
                    else
                        mView.setNotes(null);

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.setNotes(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.setNotes(null);
            ProgressUtils.stopProgressDlg();
        }


    }

    @Override
    public void getNote(Context mContext, String uid, String titleId) {
        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DOGETNOTE);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.ID_,titleId);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<ExamNoteBean> list = JsonUtil.jsonToListByArray(json,ExamNoteBean.class);

                    if(list!=null && list.size()>0)
                    {
                        mView.setNote(list.get(0));
                    }
                    else
                        mView.setNote(null);

                }

                @Override
                public void onError(String errStr) {
                    mView.setNote(null);
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.setNote(null);
        }

    }
}
