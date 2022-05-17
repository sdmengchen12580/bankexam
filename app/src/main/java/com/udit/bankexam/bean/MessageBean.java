package com.udit.bankexam.bean;

/**
 * Created by zb on 2017/6/7.
 */

public class MessageBean {

    //消息ID  ：
    private String ID;
    //关联ID  ：
    private String LinkID;
    //消息标题：
    private String Name;
    //消息体  ：
    private String Msg;
    //推送类别：
    private String mType;
    //发送时间：
    private String BegTime;
    //截止时间：
    private String EndTime;
    //创建时间：
    private String CreateTime;
    //消息方式：
    private String sType;
    //会员ID  ：
    private String uid;
    //发送状态 ：
    private String SendState;

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getLinkID() {
        return LinkID;
    }

    public void setLinkID(String linkID) {
        LinkID = linkID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMsg() {
        return Msg;
    }

    public void setMsg(String msg) {
        Msg = msg;
    }

    public String getmType() {
        return mType;
    }

    public void setmType(String mType) {
        this.mType = mType;
    }

    public String getBegTime() {
        return BegTime;
    }

    public void setBegTime(String begTime) {
        BegTime = begTime;
    }

    public String getEndTime() {
        return EndTime;
    }

    public void setEndTime(String endTime) {
        EndTime = endTime;
    }

    public String getCreateTime() {
        return CreateTime;
    }

    public void setCreateTime(String createTime) {
        CreateTime = createTime;
    }

    public String getsType() {
        return sType;
    }

    public void setsType(String sType) {
        this.sType = sType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSendState() {
        return SendState;
    }

    public void setSendState(String sendState) {
        SendState = sendState;
    }
}
