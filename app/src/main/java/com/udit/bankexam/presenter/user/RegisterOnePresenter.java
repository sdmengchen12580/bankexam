package com.udit.bankexam.presenter.user;

import android.content.Context;

import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.constant.Constant.Params;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.ui.home.HomeActivity;
import com.udit.bankexam.utils.JsonUtils;
import com.udit.bankexam.view.user.WxRegisterOneView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

public class RegisterOnePresenter extends WxRegisterOneView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();

    public RegisterOnePresenter(WxRegisterOneView.View mView)
    {
        super(mView);
    }

    @Override
    public void getDuanxin(Context mContext, String mobile)
    {
        try
        {
            ProgressUtils.showProgressDlg("获取短信中", mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Params.ACTION, IHTTP.DOIDENTIFYING);
            map_params.put(Params.MOBILE, mobile);
            map_params.put(Params.TYPE,"3");
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener()
            {
                
                @Override
                public void onError(String errStr)
                {
                    mView.setDuanxinErr(null);
                    ProgressUtils.stopProgressDlg();
                }
                
                @Override
                public void doHttpResponse(String json)
                {
                    MsgBean bean =   JsonUtils.parseDuanxin(json);
                    if(bean!=null)
                    {
                        mView.setDuanxinOk(bean);
                    }
                    else
                    {
                        List<MsgBean> list = JsonUtil.jsonToListByArrayNoSucess(json,MsgBean.class);
                        if(list!=null && list.size()>0)
                        {
                            mView.setDuanxinErr(list.get(0).getMsg());
                        }
                        else
                            mView.setDuanxinErr(null);
                    }
                    ProgressUtils.stopProgressDlg();
                }
            });
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setDuanxinErr(null);
            ProgressUtils.stopProgressDlg();
        }        
    }

    @Override
    public void checkPhoneIsRegister(Context mContext, String mobile,String uid) {
        try
        {
            ProgressUtils.showProgressDlg("验证中", mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Params.ACTION, IHTTP.doCheckWxRegisterPhone);
            map_params.put(Params.MOBILE, mobile);
            map_params.put(Params.UID,uid);


            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener()
            {

                @Override
                public void onError(String errStr)
                {
                    mView.setCheckPhoneIsRegisterErr(errStr);
                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void doHttpResponse(String json)
                {
                    if(JsonUtil.getJsonArrayOk(json)){
                        Object obj_type =  JsonUtil.getJsonArrayforKey(json,"type");
                        if(obj_type!=null)
                        {
                            String type = obj_type.toString();
                            mView.setCheckPhoneIsRegisterOk(type);

                        }
                        else{
                            mView.setCheckPhoneIsRegisterErr("验证失败");
                        }

                    }
                    else{
                        Object msg_obj = JsonUtil.getJsonArrayforKey(json,"msg");
                        if(msg_obj!=null)
                            mView.setCheckPhoneIsRegisterErr(""+msg_obj);
                        else
                            mView.setCheckPhoneIsRegisterErr("验证失败");
                    }


                    ProgressUtils.stopProgressDlg();
                }
            });
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            mView.setDuanxinErr(null);
            ProgressUtils.stopProgressDlg();
        }
    }

}
