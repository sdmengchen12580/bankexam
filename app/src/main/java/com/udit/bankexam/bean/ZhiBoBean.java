package com.udit.bankexam.bean;

import java.io.Serializable;

public class ZhiBoBean implements Serializable
{
    private static final long serialVersionUID = -6768330457000293729L;
    //直播课ID：
    private String LID;
    //小屏资讯：
    private String Screen;
    //全屏资讯：
    private String AllScreen;
    //直播课名：
    private String Name;
    //课时数  ：
    private String LCount;
    //课程费用:
    private String Price;
    //授课开始：
    private String BegDate;
    //授课结束：
    private String EndDate;
    //开售日期：
    private String BegSale;
    //停售日期：
    private String EndSale;
    //已购人数：
    private String PurchCount;
    //我是否订：
    private String IsGet;

    public String getPurchCount() {
        return PurchCount;
    }

    public void setPurchCount(String purchCount) {
        PurchCount = purchCount;
    }

    public String getIsGet() {
        return IsGet;
    }

    public void setIsGet(String isGet) {
        IsGet = isGet;
    }

    public String getLID() {
        return LID;
    }

    public void setLID(String LID) {
        this.LID = LID;
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

    public String getLCount() {
        return LCount;
    }

    public void setLCount(String LCount) {
        this.LCount = LCount;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
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

    public String getBegSale() {
        return BegSale;
    }

    public void setBegSale(String begSale) {
        BegSale = begSale;
    }

    public String getEndSale() {
        return EndSale;
    }

    public void setEndSale(String endSale) {
        EndSale = endSale;
    }
}
