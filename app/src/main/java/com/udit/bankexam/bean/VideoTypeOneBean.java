package com.udit.bankexam.bean;

import java.io.Serializable;

/**
 * Created by zb on 2018-06-25.
 */

public class VideoTypeOneBean implements Serializable{

    private static final long serialVersionUID = -7770713387787434156L;
    private String VType;

    private String type_num;

    private String video_num;

    private String picture;

    public String getVType() {
        return VType;
    }

    public void setVType(String VType) {
        this.VType = VType;
    }

    public String getType_num() {
        return type_num;
    }

    public void setType_num(String type_num) {
        this.type_num = type_num;
    }

    public String getVideo_num() {
        return video_num;
    }

    public void setVideo_num(String video_num) {
        this.video_num = video_num;
    }



    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
