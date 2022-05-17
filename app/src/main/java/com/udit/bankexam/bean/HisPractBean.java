package com.udit.bankexam.bean;

/**
 * Created by zb on 2017/6/5.
 * 智能练习、组卷
 */

public class HisPractBean {

    private String  PID;

    private String  KeyWord;

    private String  PDate;

    private String PType;

    public String getPType() {
        return PType;
    }

    public void setPType(String PType) {
        this.PType = PType;
    }

    public String getPID() {
        return PID;
    }

    public void setPID(String PID) {
        this.PID = PID;
    }

    public String getKeyWord() {
        return KeyWord;
    }

    public void setKeyWord(String keyWord) {
        KeyWord = keyWord;
    }

    public String getPDate() {
        return PDate;
    }

    public void setPDate(String PDate) {
        this.PDate = PDate;
    }



}
