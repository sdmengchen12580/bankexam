package com.udit.bankexam.bean;

import java.io.Serializable;

/**
 * Created by zb on 2017/5/3.
 */

public class TeacherBean implements Serializable {

    private static final long serialVersionUID = 7493688906928357247L;
    //老师ID ：
    private String TecheID;
    //姓名   ：
    private String TecheName;
    //简历   ：
    private String Resume;
    //业务介绍：
    private String TecheIntro;
    //照片文件：
    private String TecheAFile;

    public String getTecheID() {
        return TecheID;
    }

    public void setTecheID(String techeID) {
        TecheID = techeID;
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
