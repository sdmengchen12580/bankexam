package com.udit.bankexam.bean;

/**
 * Created by zb on 2017/5/18.
 * 数据报告
 */

public class ReportBean {

    // 信息ID  ：
    private String ID;
    //小屏资讯：
    private String Screen;
    //  PC广告：
    private String PCScreen;
    //  全屏资讯：
    private String AllScreen;
    // 信息主题：
    private String Name;
    // 订价    :
    private String Price;
    // 信息开始：
    private String BegDate;
    // 信息结束：
    private String EndDate;
    //订购总数：
    private String AllCount;
    // 我是否订：//是、否
    private String IsGet;
    // 有多少答题人：
    private String ManCount;
    // 最高分：
    private String MaxScore;
    // 最低分：
    private String MinScore;
    // 我分数：
    private String MyScore;
    // 答题总时长：
    private String AllTime;
    // 试卷题数  ：
    private String TitleCount;
    // 试卷总分：
    private String AllScore;
    //答题数    ：
    private String AnswerCount;
    // 答题对个数：
    private String OkCount;
    // 答题错个数：
    private String ErrCount;
    //未答个数 ：
    private String NoCount;
    //平均成绩
    private String AvgScore;
    //打败人数
    private String WinCount;


    public String getAvgScore() {
        return AvgScore;
    }

    public void setAvgScore(String avgScore) {
        AvgScore = avgScore;
    }

    public String getWinCount() {
        return WinCount;
    }

    public void setWinCount(String winCount) {
        WinCount = winCount;
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

    public String getPCScreen() {
        return PCScreen;
    }

    public void setPCScreen(String PCScreen) {
        this.PCScreen = PCScreen;
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

    public String getAllCount() {
        return AllCount;
    }

    public void setAllCount(String allCount) {
        AllCount = allCount;
    }

    public String getIsGet() {
        return IsGet;
    }

    public void setIsGet(String isGet) {
        IsGet = isGet;
    }

    public String getManCount() {
        return ManCount;
    }

    public void setManCount(String manCount) {
        ManCount = manCount;
    }

    public String getMaxScore() {
        return MaxScore;
    }

    public void setMaxScore(String maxScore) {
        MaxScore = maxScore;
    }

    public String getMinScore() {
        return MinScore;
    }

    public void setMinScore(String minScore) {
        MinScore = minScore;
    }

    public String getMyScore() {
        return MyScore;
    }

    public void setMyScore(String myScore) {
        MyScore = myScore;
    }

    public String getAllTime() {
        return AllTime;
    }

    public void setAllTime(String allTime) {
        AllTime = allTime;
    }

    public String getTitleCount() {
        return TitleCount;
    }

    public void setTitleCount(String titleCount) {
        TitleCount = titleCount;
    }

    public String getAllScore() {
        return AllScore;
    }

    public void setAllScore(String allScore) {
        AllScore = allScore;
    }

    public String getAnswerCount() {
        return AnswerCount;
    }

    public void setAnswerCount(String answerCount) {
        AnswerCount = answerCount;
    }

    public String getOkCount() {
        return OkCount;
    }

    public void setOkCount(String okCount) {
        OkCount = okCount;
    }

    public String getErrCount() {
        return ErrCount;
    }

    public void setErrCount(String errCount) {
        ErrCount = errCount;
    }

    public String getNoCount() {
        return NoCount;
    }

    public void setNoCount(String noCount) {
        NoCount = noCount;
    }
}
