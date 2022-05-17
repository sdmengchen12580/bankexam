package com.udit.bankexam.bean;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by zb on 2017/6/29.
 */

@Entity
public class ExamNoteBean {

    @Id(autoincrement = false)
    private String ID;
    @Property(nameInDb = "note")
    private String Note;
    @Property(nameInDb = "uid")
    private String uid;
    @Property(nameInDb = "LinkID")
    private String LinkID;
    @Property(nameInDb = "Date")
    private String Date;


    @Generated(hash = 1369286601)
    public ExamNoteBean(String ID, String Note, String uid, String LinkID,
            String Date) {
        this.ID = ID;
        this.Note = Note;
        this.uid = uid;
        this.LinkID = LinkID;
        this.Date = Date;
    }

    @Generated(hash = 721383882)
    public ExamNoteBean() {
    }


    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getNote() {
        return Note;
    }

    public void setNote(String note) {
        Note = note;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getLinkID() {
        return LinkID;
    }

    public void setLinkID(String linkID) {
        LinkID = linkID;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
