package com.udit.bankexam.bean;

import java.io.Serializable;

/**
 * Created by zb on 2017/6/14.
 */

public class notiftyBean implements Serializable{


    private static final long serialVersionUID = 7348753736376151435L;
    private String notify_id;

    private String name;

    private String msg;

    private String mType;

    private String linkId;

    private String msgUrl;

    public String getMsgUrl() {
        return msgUrl;
    }

    public void setMsgUrl(String msgUrl) {
        this.msgUrl = msgUrl;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getLinkId() {
        return linkId;
    }

    public void setLinkId(String linkId) {
        this.linkId = linkId;
    }
}
