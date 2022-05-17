package com.udit.bankexam.presenter.pay;

import android.content.Context;

import com.udit.bankexam.bean.AddressBean;
import com.udit.bankexam.bean.PayInfo;
import com.udit.bankexam.bean.PurchBean;
import com.udit.bankexam.bean.WeixinPayInfo;
import com.udit.bankexam.constant.Constant;
import com.udit.bankexam.constant.IHTTP;
import com.udit.bankexam.view.pay.PaySelecteView;
import com.udit.frame.freamwork.http.IHttpResponseListener;
import com.udit.frame.utils.JsonUtil;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.ProgressUtils;

import java.util.HashMap;
import java.util.List;

/**
 * Created by zb on 2017/5/4.
 */

public class PayPresenter extends PaySelecteView.Presenter {

    private final String TAG = this.getClass().getSimpleName();

    public PayPresenter(PaySelecteView.View mView) {
        super(mView);
    }

    @Override
    public void zhifu(String uid, String id,final String type,final String type_zhifu) {
        HashMap<String,String> map_params = new HashMap<>();

        try {
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETPAYINFO);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.ID,id);
            map_params.put(Constant.Params.TYPE,type);
            map_params.put(Constant.Params.TYPE_PAY,type_zhifu);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonForOK(json))
                    {
                        if(type_zhifu.equals(Constant.PAY.PAY_ZHIFUBAO))
                        {
                            PayInfo info = JsonUtil.jsonToObject(json,PayInfo.class,PayInfo.class.getSimpleName());
                            if(info!=null)
                            {
                                mView.getZhifuSign(info);
                            }
                            else
                                mView.getZhifuSign(null);
                        }
                        else if(type_zhifu.equals(Constant.PAY.PAY_WEIXIN))
                        {
                            WeixinPayInfo weixinpay = JsonUtil.jsonToObject(json,WeixinPayInfo.class,"PayInfo");

                            if(weixinpay!=null)
                            {
                                mView.getWeiXinSign(weixinpay);
                            }
                            else
                            {
                                mView.getWeiXinSign(null);
                            }

                        }

                    }
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    if(type.equals(Constant.PAY.PAY_ZHIFUBAO)) {
                        mView.getZhifuSign(null);
                    }
                    else if(type.equals(Constant.PAY.PAY_WEIXIN))
                    {
                        mView.getWeiXinSign(null);
                    }
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            if(type.equals(Constant.PAY.PAY_ZHIFUBAO)) {
                mView.getZhifuSign(null);
            }
            else if(type.equals(Constant.PAY.PAY_WEIXIN))
            {
                mView.getWeiXinSign(null);
            }
        }

    }

    @Override
    public void zhifuOk(String id,String uid,String linkId) {
        try {
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION,IHTTP.DOPUTPURCH2);
            map_params.put(Constant.Params.ID,id);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.LINKID,linkId);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<PurchBean> list = JsonUtil.jsonToListByArray(json,PurchBean.class);
                    if(list!=null &&list.size()>0)
                    {
                        mView.zhifuOk();
                    }
                    else
                        mView.zhifuErr();
                }

                @Override
                public void onError(String errStr) {
                    MyLogUtils.e(TAG,errStr);
                    mView.zhifuErr();
                }
            });
        } catch (Exception e) {
            MyLogUtils.e(TAG,e.getMessage());
            mView.zhifuErr();
        }

    }

    @Override
    public void isPay(String uid, String id, String type) {
        HashMap<String,String> map_params = new HashMap<>();
        try {
            map_params.put(Constant.Params.ACTION,IHTTP.DOISPAY);
            map_params.put(Constant.Params.UID,uid);
            map_params.put(Constant.Params.ID,id);
            map_params.put(Constant.Params.TYPE,type);
            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    if(JsonUtil.getJsonArrayOk(json))
                    {
                        mView.payOk();
                    }
                    else
                        mView.payErr();
                }

                @Override
                public void onError(String errStr) {
                    mView.payErr();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            mView.payErr();
        }
    }

    @Override
    public void doGetAddr(Context context, String uid) {
        try {
            ProgressUtils.showProgressDlg("",context);
            HashMap<String,String> map_params = new HashMap<>();
            map_params.put(Constant.Params.ACTION, IHTTP.DOGETADDR);

            map_params.put(Constant.Params.UID,uid);

            setHttp(map_params, IHTTP.PROJECT, new IHttpResponseListener() {
                @Override
                public void doHttpResponse(String json) {
                    List<AddressBean> list = JsonUtil.jsonToListByArray(json,AddressBean.class);
                    if(list!=null && list.size()>0)
                    {
                        mView.getAddr(list.get(0));
                    }
                    else
                        mView.getAddr(null);

                    ProgressUtils.stopProgressDlg();
                }

                @Override
                public void onError(String errStr) {
                    mView.getAddr(null);
                    ProgressUtils.stopProgressDlg();
                }
            });
        } catch (Exception e) {
            mView.getAddr(null);

            ProgressUtils.stopProgressDlg();
        }
    }
}
