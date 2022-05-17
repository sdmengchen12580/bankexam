package com.udit.bankexam.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zb on 2017/6/21.
 */
@Entity
public class ExamHistoryBean {

    /**
     * 主键ID
     */
    @Id(autoincrement = false)
    private String _id;

    /**
     * 试卷 / 节点ID
     */
    @Property(nameInDb = "eid_oid")
    private String eid_oid;

    /**
     * 是/否 为智能
     */
    @Property(nameInDb = "flag_zhineng")
    private boolean flag_zhineng;

    /**
     * 是/否 首页
     */
    @Property(nameInDb = "flag_main")
    private boolean flag_main;

    /**
     * 是/否 节点
     */
    @Property(nameInDb = "flag_oid")
    private boolean flag_oid;

    /**
     * 做题的名称/试卷/节点
     */
    @Property(nameInDb = "name")
    private String name;

    /**
     * 是/否 重新 练习
     */
    @Property(nameInDb = "flag_cl")
    private boolean flag_cl;

    /**
     * 正在练习的当前题目
     */
    @Property(nameInDb = "selected_exam")
    private int selected_exam;
    /**
     * 用户ID
     */
    @Property(nameInDb = "user_id")
    private String user_id;

    @Generated(hash = 551014804)
    public ExamHistoryBean(String _id, String eid_oid, boolean flag_zhineng,
            boolean flag_main, boolean flag_oid, String name, boolean flag_cl,
            int selected_exam, String user_id) {
        this._id = _id;
        this.eid_oid = eid_oid;
        this.flag_zhineng = flag_zhineng;
        this.flag_main = flag_main;
        this.flag_oid = flag_oid;
        this.name = name;
        this.flag_cl = flag_cl;
        this.selected_exam = selected_exam;
        this.user_id = user_id;
    }

    @Generated(hash = 1795483683)
    public ExamHistoryBean() {
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

    public String getEid_oid() {
        return eid_oid;
    }

    public void setEid_oid(String eid_oid) {
        this.eid_oid = eid_oid;
    }

    public boolean isFlag_zhineng() {
        return flag_zhineng;
    }

    public void setFlag_zhineng(boolean flag_zhineng) {
        this.flag_zhineng = flag_zhineng;
    }

    public boolean isFlag_main() {
        return flag_main;
    }

    public void setFlag_main(boolean flag_main) {
        this.flag_main = flag_main;
    }

    public boolean isFlag_oid() {
        return flag_oid;
    }

    public void setFlag_oid(boolean flag_oid) {
        this.flag_oid = flag_oid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isFlag_cl() {
        return flag_cl;
    }

    public void setFlag_cl(boolean flag_cl) {
        this.flag_cl = flag_cl;
    }

    public int getSelected_exam() {
        return selected_exam;
    }

    public void setSelected_exam(int selected_exam) {
        this.selected_exam = selected_exam;
    }

    public boolean getFlag_zhineng() {
        return this.flag_zhineng;
    }

    public boolean getFlag_main() {
        return this.flag_main;
    }

    public boolean getFlag_oid() {
        return this.flag_oid;
    }

    public boolean getFlag_cl() {
        return this.flag_cl;
    }
}
