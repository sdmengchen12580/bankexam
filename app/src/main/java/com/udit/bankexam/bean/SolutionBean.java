package com.udit.bankexam.bean;

/**
 * Created by zb on 2017/5/11.
 */

public class SolutionBean {
    //  答题时间 ：
    private String ATime;
    //  题目ID   ：
    private String ID;
    // 结果     :
    private String Answer;
    //是否答对 :
    private String isOK;
    //  得分     :
    private String GetScore;
    // 答题时长 :
    private String UserTime;

    @Override
    public String toString() {
        return "SolutionBean{" +
                "ATime='" + ATime + '\'' +
                ", ID='" + ID + '\'' +
                ", Answer='" + Answer + '\'' +
                ", isOK='" + isOK + '\'' +
                ", GetScore='" + GetScore + '\'' +
                ", UserTime='" + UserTime + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SolutionBean that = (SolutionBean) o;

        if (Answer != null ? !Answer.equals(that.Answer) : that.Answer != null) return false;
        return ID != null ? !ID.equals(that.ID) : that.ID != null;

    }

    @Override
    public int hashCode() {
        int result = ATime != null ? ATime.hashCode() : 0;
        result = 31 * result + (ID != null ? ID.hashCode() : 0);
        result = 31 * result + (Answer != null ? Answer.hashCode() : 0);
        result = 31 * result + (isOK != null ? isOK.hashCode() : 0);
        result = 31 * result + (GetScore != null ? GetScore.hashCode() : 0);
        result = 31 * result + (UserTime != null ? UserTime.hashCode() : 0);
        return result;
    }

    public String getATime() {
        return ATime;
    }

    public void setATime(String ATime) {
        this.ATime = ATime;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
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
