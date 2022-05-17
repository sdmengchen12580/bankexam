package com.udit.bankexam.bean;

import java.io.Serializable;

/**
 * Created by zb on 2017/5/4.
 */

public class PurchBean  implements Serializable{

    private static final long serialVersionUID = 261738356542078077L;
    // 支付ID  ：
    private String pID;
    //支付类别：
    private String PType;
    //帐号ID  ：
    private String uid;
    // 订单日期：
    private String FeeDate;
    //关联ID  ：
    private String LinkID;
    // 支付费用：
    private String Fee;
    // 订单摘要：
    private String Abstract;
    // 支付说明：
    private String Intro;
    //支付状态：
    private String PState;

    public String getpID() {
        return pID;
    }

    public void setpID(String pID) {
        this.pID = pID;
    }

    public String getPType() {
        return PType;
    }

    public void setPType(String PType) {
        this.PType = PType;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getFeeDate() {
        return FeeDate;
    }

    public void setFeeDate(String feeDate) {
        FeeDate = feeDate;
    }

    public String getLinkID() {
        return LinkID;
    }

    public void setLinkID(String linkID) {
        LinkID = linkID;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String fee) {
        Fee = fee;
    }

    public String getAbstract() {
        return Abstract;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public String getPState() {
        return PState;
    }

    public void setPState(String PState) {
        this.PState = PState;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchBean purchBean = (PurchBean) o;

        if (uid != null ? !uid.equals(purchBean.uid) : purchBean.uid != null) return false;
        return LinkID != null ? LinkID.equals(purchBean.LinkID) : purchBean.LinkID == null;

    }

    @Override
    public int hashCode() {
        int result = uid != null ? uid.hashCode() : 0;
        result = 31 * result + (LinkID != null ? LinkID.hashCode() : 0);
        return result;
    }
}
