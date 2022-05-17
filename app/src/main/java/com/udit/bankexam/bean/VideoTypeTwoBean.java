package com.udit.bankexam.bean;

import java.io.Serializable;

/**
 * Created by zb on 2017/4/25.
 */

public class VideoTypeTwoBean implements Serializable{
    private static final long serialVersionUID = -1781062565736412414L;
    //视频目录：
    private String cID;
    // 序号    ：
    private String OrdID;
    // 小屏资讯：
    private String Screen;
    //全屏资讯：
    private String AllScreen;
    //视频标题：
    private String Name;
    //视频大类：
    private String VType;
    //价格    ：
    private String Price;

    //数量
    private String iCount;
    //购买
    private String IsGet;

    public String getiCount() {
        return iCount;
    }

    public void setiCount(String iCount) {
        this.iCount = iCount;
    }

    public String getIsGet() {
        return IsGet;
    }

    public void setIsGet(String isGet) {
        IsGet = isGet;
    }

    public String getcID() {
        return cID;
    }

    public void setcID(String cID) {
        this.cID = cID;
    }

    public String getOrdID() {
        return OrdID;
    }

    public void setOrdID(String ordID) {
        OrdID = ordID;
    }

    public String getScreen() {
        return Screen;
    }

    public void setScreen(String screen) {
        Screen = screen;
    }

    public String getAllScreen() {
        return AllScreen;
    }

    public void setAllScreen(String allScreen) {
        AllScreen = allScreen;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getVType() {
        return VType;
    }

    public void setVType(String VType) {
        this.VType = VType;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
