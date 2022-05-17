package com.udit.bankexam.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class ExamOptionBean implements Serializable
{
    @Transient
    private static final long serialVersionUID = 8009095674903680582L;

    @Id(autoincrement = false)
    private String _id;

    @Property(nameInDb = "examid")
    private String examid;
    /**
     * 选项ID 
     */
    @Property(nameInDb = "titleListID")
    private String titleListID;
    
    /**
     * 选项顺序
     */
    @Property(nameInDb = "orderID")
    private String orderID;
    
    /**
     * 选项标识
     */
    @Property(nameInDb = "single")
    private String single;
    
    /**
     * 选项内容
     */
    @Property(nameInDb = "SList")
    private String SList;

    /**
     * 用户ID
     */
    @Property(nameInDb = "user_id")
    private String user_id;

    @Generated(hash = 1821295665)
    public ExamOptionBean(String _id, String examid, String titleListID, String orderID, String single,
            String SList, String user_id) {
        this._id = _id;
        this.examid = examid;
        this.titleListID = titleListID;
        this.orderID = orderID;
        this.single = single;
        this.SList = SList;
        this.user_id = user_id;
    }

    @Generated(hash = 1538901113)
    public ExamOptionBean() {
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getExamid() {
        return examid;
    }

    public void setExamid(String examid) {
        this.examid = examid;
    }

    public String getTitleListID() {
        return titleListID;
    }

    public void setTitleListID(String titleListID) {
        this.titleListID = titleListID;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getSingle() {
        return single;
    }

    public void setSingle(String single) {
        this.single = single;
    }

    public String getSList() {
        return SList;
    }

    public void setSList(String SList) {
        this.SList = SList;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamOptionBean that = (ExamOptionBean) o;

        if (titleListID != null ? !titleListID.equals(that.titleListID) : that.titleListID != null)
            return false;
        if (orderID != null ? !orderID.equals(that.orderID) : that.orderID != null) return false;
        if (single != null ? !single.equals(that.single) : that.single != null) return false;
        return SList != null ? SList.equals(that.SList) : that.SList == null;

    }

    @Override
    public int hashCode() {
        int result = titleListID != null ? titleListID.hashCode() : 0;
        result = 31 * result + (orderID != null ? orderID.hashCode() : 0);
        result = 31 * result + (single != null ? single.hashCode() : 0);
        result = 31 * result + (SList != null ? SList.hashCode() : 0);
        return result;
    }
}
