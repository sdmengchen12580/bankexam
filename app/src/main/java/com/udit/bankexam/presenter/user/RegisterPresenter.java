package com.udit.bankexam.presenter.user;

import java.util.HashMap;
import java.util.List;

import com.udit.bankexam.constant.Constant.Params;
import com.udit.bankexam.bean.MsgBean;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.utils.JsonUtils;
import com.udit.bankexam.view.user.RegisterView;
import com.udit.bankexam.view.user.RegisterView.View;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;
import com.udit.frame.utils.ToastUtil;

import android.content.Context;

public class RegisterPresenter extends RegisterView.Presenter
{
    private final String TAG = this.getClass().getSimpleName();
    
    public RegisterPresenter(View mView)
    {
        super(mView);
    }

    @Override
    public void getDuanxin(Context mContext,String mobile)
    {
        try
        {
            ProgressUtils.showProgressDlg("获取短信中", mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Params.ACTION, IHTTP.DOIDENTIFYING);
            map_params.put(Params.MOBILE, mobile);
            map_params.put(Params.TYPE,"0");
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
    public void register(Context mContext, String mobile, String pwd)
    {
        try
        {
            ProgressUtils.showProgressDlg("账号注册中", mContext);
            HashMap<String, String> map_params = new HashMap<>();
            map_params.put(Params.ACTION, IHTTP.DOREGISTER);
            map_params.put(Params.MOBILE, mobile);
            map_params.put(Params.PASS, pwd);
            
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener()
            {
                
                @Override
                public void onError(String errStr)
                {
                    mView.registerErr();
                    ProgressUtils.stopProgressDlg();
                }
                
                @Override
                public void doHttpResponse(String json)
                {
                    MsgBean bean =   JsonUtils.parseDuanxin(json);
                    if(bean!=null)
                    {
                        mView.registerOk(bean);
                    }
                    else
                    {
                        mView.registerErr();
                    }
                    ProgressUtils.stopProgressDlg();
                }
            });
        }
        catch (Exception e)
        {
            MyLogUtils.e(TAG, e.getMessage());
            mView.registerErr();
            ProgressUtils.stopProgressDlg();
        }
        
    }

}
