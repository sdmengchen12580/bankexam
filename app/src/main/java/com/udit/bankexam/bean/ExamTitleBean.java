package com.udit.bankexam.bean;

import java.io.Serializable;

/**
 * Created by zb on 2017/5/9.
 * 答题卡
 */

public class ExamTitleBean implements Serializable {

    private static final long serialVersionUID = 5767979668607646849L;
    //题目ID
    private String ID;
   // 试卷ID   :
    private String EID;
    //提纲ID   ：
    private String OID;
    //题目序号 :
    private String OrdID;
    //题库ID   :
    private String TitleID;
    //分数     ：
    private String Score;
    //答题时间 ：
    private String ATime;
    //结果     :
    private String Answer;
    //是否答对 :
    private String isOK;
    //得分     :
    private String GetScore;
   // 答题时长 :
    private String UserTime;

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

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getOrdID() {
        return OrdID;
    }

    public void setOrdID(String ordID) {
        OrdID = ordID;
    }

    public String getTitleID() {
        return TitleID;
    }

    public void setTitleID(String titleID) {
        TitleID = titleID;
    }

    public String getScore() {
        return Score;
    }

    public void setScore(String score) {
        Score = score;
    }

    public String getATime() {
        return ATime;
    }

    public void setATime(String ATime) {
        this.ATime = ATime;
    }

    public String getAnswer() {
        return Answer;
    }

    public void setAnswer(String answer) {
        Answer = answer;
    }

    public String getIsOK() {
        return isOK;
    }

    public void setIsOK(String isOK) {
        this.isOK = isOK;
    }

    public String getGetScore() {
        return GetScore;
    }

    public void setGetScore(String getScore) {
        GetScore = getScore;
    }

    public String getUserTime() {
        return UserTime;
    }

    public void setUserTime(String userTime) {
        UserTime = userTime;
    }
}
