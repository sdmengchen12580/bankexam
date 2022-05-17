package com.udit.bankexam.bean;

import java.io.Serializable;

public class VideoBean implements Serializable {
    private static final long serialVersionUID = -7891390601055312582L;
    //视频目录：
    private String cID;
    //视频ID  ：
    private String ID;
    //序号    ：
    private String ordID;
    //标题    ：
    private String Name;
    //知识介绍：
    private String Points;
    //视频文件:
    private String AFile;
    //視頻文件新地址
    private String videoIdAli;
    //练习试题：
    private String EID;
    //时长    ：
    private String VTime;

    public String getVideoIdAli() {
        return videoIdAli;
    }

    public void setVideoIdAli(String videoIdAli) {
        this.videoIdAli = videoIdAli;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getOrdID() {
        return ordID;
    }

    public void setOrdID(String ordID) {
        this.ordID = ordID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPoints() {
        return Points;
    }

    public void setPoints(String points) {
        Points = points;
    }

    public String getAFile() {
        return AFile;
    }

    public void setAFile(String AFile) {
        this.AFile = AFile;
    }

    public String getEID() {
        return EID;
    }

    public void setEID(String EID) {
        this.EID = EID;
    }

    public String getVTime() {
        return VTime;
    }

    public void setVTime(String VTime) {
        this.VTime = VTime;
    }
}
