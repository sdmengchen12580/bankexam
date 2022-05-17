package com.udit.bankexam.bean;

import java.io.Serializable;

/**
 * Created by zb on 2017/5/3.
 */

public class ZhiboKeChengBean implements Serializable {

    private static final long serialVersionUID = -8658499941332193495L;
    //课程ID  ：
    private String SID;
    // 直播课ID：
    private String LID;

    private String Name;
    // 课时说明：
    private String Intro;
    // 老师ID  ：
    private String TecheID;
    //授课开始：
    private String BegDate;
    // 授课结束：
    private String EndDate;
    // 视频文件：
    private String AFile;
    //讲义文件：
    private String materials;
    // 老师姓名：
    private String TecheName;
    // 老师简历：
    private String Resume;
    //老师介绍：
    private String TecheIntro;
    //老师照片：
    private String TecheAFile;

    private String  URL;

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSID() {
        return SID;
    }

    public void setSID(String SID) {
        this.SID = SID;
    }

    public String getLID() {
        return LID;
    }

    public void setLID(String LID) {
        this.LID = LID;
    }

    public String getIntro() {
        return Intro;
    }

    public void setIntro(String intro) {
        Intro = intro;
    }

    public String getTecheID() {
        return TecheID;
    }

    public void setTecheID(String techeID) {
        TecheID = techeID;
    }

    public String getBegDate() {
        return BegDate;
    }

    public void setBegDate(String begDate) {
        BegDate = begDate;
    }

    public String getEndDate() {
        return EndDate;
    }

    public void setEndDate(String endDate) {
        EndDate = endDate;
    }

    public String getAFile() {
        return AFile;
    }

    public void setAFile(String AFile) {
        this.AFile = AFile;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public String getTecheName() {
        return TecheName;
    }

    public void setTecheName(String techeName) {
        TecheName = techeName;
    }

    public String getResume() {
        return Resume;
    }

    public void setResume(String resume) {
        Resume = resume;
    }

    public String getTecheIntro() {
        return TecheIntro;
    }

    public void setTecheIntro(String techeIntro) {
        TecheIntro = techeIntro;
    }

    public String getTecheAFile() {
        return TecheAFile;
    }

    public void setTecheAFile(String techeAFile) {
        TecheAFile = techeAFile;
    }
}
