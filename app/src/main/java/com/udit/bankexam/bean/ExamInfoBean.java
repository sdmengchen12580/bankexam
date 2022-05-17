package com.udit.bankexam.bean;

import java.io.Serializable;

/**
 * Created by zb on 2017/5/12.
 */

public class ExamInfoBean implements Serializable{

    private static final long serialVersionUID = 17172583221440340L;
    //信息ID  ：
    private String ID;
    //小屏资讯：
    private String  Screen;
    //全屏资讯：
    private String  AllScreen;
    //信息主题：
    private String  Name;
    //订价    :
    private String  Price;

    //订购人数
    private String iCount;

    //是/否 订购
    private String IsGet;
    //信息开始：
    private String  BegDate;
    //信息结束：
    private String  EndDate;

    private String TypeInfo;

    private String Bank;

    private String TYear;


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

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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

    public String getTypeInfo() {
        return TypeInfo;
    }

    public void setTypeInfo(String typeInfo) {
        TypeInfo = typeInfo;
    }

    public String getBank() {
        return Bank;
    }

    public void setBank(String bank) {
        Bank = bank;
    }

    public String getTYear() {
        return TYear;
    }

    public void setTYear(String TYear) {
        this.TYear = TYear;
    }
}
