package com.udit.bankexam.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Unique;

import java.io.Serializable;
import java.util.List;

/**
 * Created by zb on 2017/5/22.
 */


public class NoteBean {

    private String ATime;
    private String CCount;
    private String CDate;
    private String EID;
    private String ID;
    private String ID_Ord1;
    private String OID;
    private String QPoint;
    private String QType;
    private String TYear;
    private String analysis;
    private String answer;
    private String bank;
    private String content;
    private String getScore;
    private String isBank;
    private String isOK;
    private String linkID;
    private String material;
    private String nate;
    private String Note;
    private String ord;
    private String ordID;
    private String score;
    private String solution;
    private String title;
    private String titleID;
    private String uid;
    private String userTime;





    private List<ExamOptionBean> list_TitleList;

    public String getATime() {
        return ATime;
    }

    public void setATime(String ATime) {
        this.ATime = ATime;
    }

    public String getCCount() {
        return CCount;
    }

    public void setCCount(String CCount) {
        this.CCount = CCount;
    }

    public String getCDate() {
        return CDate;
    }

    public void setCDate(String CDate) {
        this.CDate = CDate;
    }

    public String getEID() {
        return EID;
    }

    public void setEID(String EID) {
        this.EID = EID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getID_Ord1() {
        return ID_Ord1;
    }

    public void setID_Ord1(String ID_Ord1) {
        this.ID_Ord1 = ID_Ord1;
    }

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getQPoint() {
        return QPoint;
    }

    public void setQPoint(String QPoint) {
        this.QPoint = QPoint;
    }

    public String getQType() {
        return QType;
    }

    public void setQType(String QType) {
        this.QType = QType;
    }

    public String getTYear() {
        return TYear;
    }

    public void setTYear(String TYear) {
        this.TYear = TYear;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getGetScore() {
        return getScore;
    }

    public void setGetScore(String getScore) {
        this.getScore = getScore;
    }

    public String getIsBank() {
        return isBank;
    }

    public void setIsBank(String isBank) {
        this.isBank = isBank;
    }

    public String getIsOK() {
        return isOK;
    }

    public void setIsOK(String isOK) {
        this.isOK = isOK;
    }

    public String getLinkID() {
        return linkID;
    }

    public void setLinkID(String linkID) {
        this.linkID = linkID;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getNate() {
        return nate;
    }

    public void setNate(String nate) {
        this.nate = nate;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        this.Note = note;
    }

    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public String getOrdID() {
        return ordID;
    }

    public void setOrdID(String ordID) {
        this.ordID = ordID;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleID() {
        return titleID;
    }

    public void setTitleID(String titleID) {
        this.titleID = titleID;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public List<ExamOptionBean> getList_TitleList() {
        return list_TitleList;
    }

    public void setList_TitleList(List<ExamOptionBean> list_TitleList) {
        this.list_TitleList = list_TitleList;
    }
}
