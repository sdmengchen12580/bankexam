package com.udit.bankexam.bean;

/**
 * Created by zb on 2017/6/7.
 */

public class WeixinPayInfo {

    private String id;

    private PayWeiXinInfo wxPayInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PayWeiXinInfo getWxPayInfo() {
        return wxPayInfo;
    }

    public void setWxPayInfo(PayWeiXinInfo wxPayInfo) {
        this.wxPayInfo = wxPayInfo;
    }
}
