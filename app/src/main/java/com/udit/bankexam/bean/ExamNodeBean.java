package com.udit.bankexam.bean;

import java.io.Serializable;
import java.util.List;


public class ExamNodeBean implements Serializable
{

    private static final long serialVersionUID = -2844767070708155955L;
    private boolean isExpand = false;


    private boolean isLast = false;

    private boolean isfirst = false;

    private String ID;
    
    private String EID;
    
    private String superID;
    
    private String ordID;
    
    private int TCount;
    
    private String name;
    
    private String TTime;
    
    private String isOver;
    
    private String OType;
    


    private List<ExamNodeBean> list_outlineinfo;
    //我做的题数
    private int doCount;
    //作对的题数
    private int okCount;
   //做错的题数
    private int errCount;

    public boolean isIsfirst() {
        return isfirst;
    }

    public boolean isFirstDate() {
        return isFirstDate;
    }

    public void setFirstDate(boolean firstDate) {
        isFirstDate = firstDate;
    }

    //没做的题数
    private int noCount;
    private boolean isFirstDate = true;

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public boolean isLast() {
        return isLast;
    }

    public void setLast(boolean last) {
        isLast = last;
    }

    public boolean isfirst() {
        return isfirst;
    }

    public void setIsfirst(boolean isfirst) {
        this.isfirst = isfirst;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getEID() {
        return EID;
    }

    public void setEID(String EID) {
        this.EID = EID;
    }

    public String getSuperID() {
        return superID;
    }

    public void setSuperID(String superID) {
        this.superID = superID;
    }

    public String getOrdID() {
        return ordID;
    }

    public void setOrdID(String ordID) {
        this.ordID = ordID;
    }

    public int getTCount() {
        return TCount;
    }

    public void setTCount(int TCount) {
        this.TCount = TCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTTime() {
        return TTime;
    }

    public void setTTime(String TTime) {
        this.TTime = TTime;
    }

    public String getIsOver() {
        return isOver;
    }

    public void setIsOver(String isOver) {
        this.isOver = isOver;
    }

    public String getOType() {
        return OType;
    }

    public void setOType(String OType) {
        this.OType = OType;
    }

    public List<ExamNodeBean> getList_outlineinfo() {
        return list_outlineinfo;
    }

    public void setList_outlineinfo(List<ExamNodeBean> list_outlineinfo) {
        this.list_outlineinfo = list_outlineinfo;
    }

    public int getDoCount() {
        return doCount;
    }

    public void setDoCount(int doCount) {
        this.doCount = doCount;
    }

    public int getOkCount() {
        return okCount;
    }

    public void setOkCount(int okCount) {
        this.okCount = okCount;
    }

    public int getErrCount() {
        return errCount;
    }

    public void setErrCount(int errCount) {
        this.errCount = errCount;
    }

    public int getNoCount() {
        return noCount;
    }

    public void setNoCount(int noCount) {
        this.noCount = noCount;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamNodeBean that = (ExamNodeBean) o;

        if (!ID.equals(that.ID)) return false;
        if (!EID.equals(that.EID)) return false;
        if (!superID.equals(that.superID)) return false;
        return name.equals(that.name);

    }

    @Override
    public int hashCode() {
        int result = ID.hashCode();
        result = 31 * result + EID.hashCode();
        result = 31 * result + superID.hashCode();
        result = 31 * result + name.hashCode();
        return result;
    }
}
