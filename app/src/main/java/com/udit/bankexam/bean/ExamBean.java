package com.udit.bankexam.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Index;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;
import com.udit.bankexam.db.DaoSession;
import com.udit.bankexam.db.ExamOptionBeanDao;
import com.udit.bankexam.db.ExamBeanDao;

/**
 * 试卷 题目 内容
 */
@Entity
public class ExamBean implements Serializable
{
    private static final long serialVersionUID = 8910705303128675931L;




   // @Id(autoincrement = true)
    //private Long _id;
    /**
     * 答题卡ID
     */
    @Id(autoincrement = false)
    private String _id;

    @Property(nameInDb = "ID")
    private String ID;

    /**
     * 关联试卷详情ID
     */
    @Property(nameInDb = "examIds")
    private String examIds;

    /**
     * 分数
     */
    @Property(nameInDb = "score")
    private String score;
    /**
     * titleID
     */
    @Property(nameInDb = "titleID")
    private String titleID;
    /**
     * 关联银行
     */
    @Property(nameInDb = "bank")
    private String bank;
    /**
     * 年份
     */
    @Property(nameInDb = "TYear")
    private String TYear;
    /**
     * 题型
     */
    @Property(nameInDb = "QType")
    private String QType;
    /**
     * 考试内容
     */
    @Property(nameInDb = "content")
    private String content;
    /**
     * 考点
     */
    @Property(nameInDb = "QPoint")
    private String QPoint;
    
    /**
     * 银行强关联
     */
    @Property(nameInDb = "isBank")
    private String isBank;
    
    /**
     * 题目内容 
     */
    @Property(nameInDb = "title")
    private String title;
    /**
     * 资料题干号
     */
    @Property(nameInDb = "ID_Ord1")
    private String ID_Ord1;
    
    /**
     * 小题数
     */
    @Property(nameInDb = "CCount")
    private String CCount;
    
    /**
     * 小题序号
     */
    @Property(nameInDb = "ord")
    private String ord;
    
    /**
     * 变更日期
     */
    @Property(nameInDb = "CDate")
    private String CDate;
    
    /**
     * 标准答案
     */
    @Property(nameInDb = "solution")
    private String solution;
    
    /**
     * 分析
     */
    @Property(nameInDb = "analysis")
    private String analysis;
    
    /**
     * 资料题干
     */
    @Property(nameInDb = "material")
    private String material;

    /**
     * 试卷ID
     */
    @Property(nameInDb = "EID")
    private String EID;

    /**
     * 提纲ID
     */
    @Property(nameInDb = "OID")
    private String OID;

    /**
     *答案
     */
    @Property(nameInDb = "answer")
    private String answer;

    /**
     * 是/否 正确
     */
    @Property(nameInDb = "isOK")
    private String isOK;


    /**
     * 分数
     */
    @Property(nameInDb = "getScore")
    private String getScore;

    /**
     * 时长
     */
    @Property(nameInDb = "ATime")
    private String ATime;

    /**
     * 序号
     */
    @Property(nameInDb = "ordID")
    private String ordID;

    /**
     * 笔记
     */
    @Property(nameInDb = "note")
    private String note;


    /**
     * 是/否 为OID
     */
    @Property(nameInDb = "flag_oid")
    private boolean flag_oid;

    @Property(nameInDb = "flag_biaoji")
    private boolean flag_biaoji;

    /**
     * 是/否 为 智能
     */
    @Property(nameInDb = "flag_zhineng")
    private boolean flag_zhineng;

    @Property(nameInDb = "userTime")
    private String userTime;


    //全站多少人做
    @Transient
    private String totalCount;


    //全站做对数
    @Transient
    private String rightCount;


    //全站做错数
    @Transient
    private String errCount;

    //最易错答案：
    @Transient
    private String badAnswer;

    @Property(nameInDb = "user_id")
    private String user_id;

    @Transient
    private List<ExamOptionBean> list_TitleList;


    @Generated(hash = 846433471)
    public ExamBean(String _id, String ID, String examIds, String score,
            String titleID, String bank, String TYear, String QType, String content,
            String QPoint, String isBank, String title, String ID_Ord1,
            String CCount, String ord, String CDate, String solution,
            String analysis, String material, String EID, String OID, String answer,
            String isOK, String getScore, String ATime, String ordID, String note,
            boolean flag_oid, boolean flag_biaoji, boolean flag_zhineng,
            String userTime, String user_id) {
        this._id = _id;
        this.ID = ID;
        this.examIds = examIds;
        this.score = score;
        this.titleID = titleID;
        this.bank = bank;
        this.TYear = TYear;
        this.QType = QType;
        this.content = content;
        this.QPoint = QPoint;
        this.isBank = isBank;
        this.title = title;
        this.ID_Ord1 = ID_Ord1;
        this.CCount = CCount;
        this.ord = ord;
        this.CDate = CDate;
        this.solution = solution;
        this.analysis = analysis;
        this.material = material;
        this.EID = EID;
        this.OID = OID;
        this.answer = answer;
        this.isOK = isOK;
        this.getScore = getScore;
        this.ATime = ATime;
        this.ordID = ordID;
        this.note = note;
        this.flag_oid = flag_oid;
        this.flag_biaoji = flag_biaoji;
        this.flag_zhineng = flag_zhineng;
        this.userTime = userTime;
        this.user_id = user_id;
    }


    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    @Generated(hash = 1109070104)
    public ExamBean() {
    }


    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getRightCount() {
        return rightCount;
    }

    public void setRightCount(String rightCount) {
        this.rightCount = rightCount;
    }

    public String getErrCount() {
        return errCount;
    }

    public void setErrCount(String errCount) {
        this.errCount = errCount;
    }

    public String getBadAnswer() {
        return badAnswer;
    }

    public void setBadAnswer(String badAnswer) {
        this.badAnswer = badAnswer;
    }

    public boolean isFlag_biaoji() {
        return flag_biaoji;
    }

    public void setFlag_biaoji(boolean flag_biaoji) {
        this.flag_biaoji = flag_biaoji;
    }

    public String getUserTime() {
        return userTime;
    }

    public void setUserTime(String userTime) {
        this.userTime = userTime;
    }

    public String getExamIds() {
        return examIds;
    }

    public void setExamIds(String examIds) {
        this.examIds = examIds;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getTitleID() {
        return titleID;
    }

    public void setTitleID(String titleID) {
        this.titleID = titleID;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getTYear() {
        return TYear;
    }

    public void setTYear(String TYear) {
        this.TYear = TYear;
    }

    public String getQType() {
        return QType;
    }

    public void setQType(String QType) {
        this.QType = QType;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getQPoint() {
        return QPoint;
    }

    public void setQPoint(String QPoint) {
        this.QPoint = QPoint;
    }

    public String getIsBank() {
        return isBank;
    }

    public void setIsBank(String isBank) {
        this.isBank = isBank;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getID_Ord1() {
        return ID_Ord1;
    }

    public void setID_Ord1(String ID_Ord1) {
        this.ID_Ord1 = ID_Ord1;
    }

    public String getCCount() {
        return CCount;
    }

    public void setCCount(String CCount) {
        this.CCount = CCount;
    }

    public String getOrd() {
        return ord;
    }

    public void setOrd(String ord) {
        this.ord = ord;
    }

    public String getCDate() {
        return CDate;
    }

    public void setCDate(String CDate) {
        this.CDate = CDate;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getEID() {
        return EID;
    }

    public void setEID(String EID) {
        this.EID = EID;
    }

    public String getOID() {
        return OID;
    }

    public void setOID(String OID) {
        this.OID = OID;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getIsOK() {
        return isOK;
    }

    public void setIsOK(String isOK) {
        this.isOK = isOK;
    }

    public String getGetScore() {
        return getScore;
    }

    public void setGetScore(String getScore) {
        this.getScore = getScore;
    }

    public String getATime() {
        return ATime;
    }

    public void setATime(String ATime) {
        this.ATime = ATime;
    }

    public String getOrdID() {
        return ordID;
    }

    public void setOrdID(String ordID) {
        this.ordID = ordID;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public boolean isFlag_oid() {
        return flag_oid;
    }

    public void setFlag_oid(boolean flag_oid) {
        this.flag_oid = flag_oid;
    }

    public boolean isFlag_zhineng() {
        return flag_zhineng;
    }

    public void setFlag_zhineng(boolean flag_zhineng) {
        this.flag_zhineng = flag_zhineng;
    }

    @Keep
    public List<ExamOptionBean> getList_TitleList() {
        return list_TitleList;
    }

    public void setList_TitleList(List<ExamOptionBean> list_TitleList) {
        this.list_TitleList = list_TitleList;
    }

    public boolean getFlag_oid() {
        return this.flag_oid;
    }

    public boolean getFlag_zhineng() {
        return this.flag_zhineng;
    }

    @Override
    public String toString() {
        return "ExamBean{" +
                "ID="+ID + '\''+
                "标准答案="+solution + '\'' +
                "answer='" + answer + '\'' +
                ", isOK='" + isOK + '\'' +
                ", getScore='" + getScore + '\'' +
                ", ATime='" + ATime + '\'' +
                ", userTime='" + userTime + '\'' +
                '}';
    }

    public boolean getFlag_biaoji() {
        return this.flag_biaoji;
    }
}
