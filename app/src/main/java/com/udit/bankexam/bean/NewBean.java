package com.udit.bankexam.bean;

import java.io.Serializable;

/**
 * Created by zb on 2018-06-08.
 */

public class NewBean implements Serializable{

    private static final long serialVersionUID = 6231378635097859052L;
    private int ID;

    private String TypeName;

    private String Name;

    private String Screen;

    private String PCScreen;

    private String Info;

    private String CreateDate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        NewBean newBean = (NewBean) o;

        if (ID != newBean.ID) return false;
        if (TypeName != null ? !TypeName.equals(newBean.TypeName) : newBean.TypeName != null)
            return false;
        if (Name != null ? !Name.equals(newBean.Name) : newBean.Name != null) return false;
        if (Screen != null ? !Screen.equals(newBean.Screen) : newBean.Screen != null) return false;
        if (PCScreen != null ? !PCScreen.equals(newBean.PCScreen) : newBean.PCScreen != null)
            return false;
        if (Info != null ? !Info.equals(newBean.Info) : newBean.Info != null) return false;
        return CreateDate != null ? CreateDate.equals(newBean.CreateDate) : newBean.CreateDate == null;

    }

    @Override
    public int hashCode() {
        int result = ID;
        result = 31 * result + (TypeName != null ? TypeName.hashCode() : 0);
        result = 31 * result + (Name != null ? Name.hashCode() : 0);
        result = 31 * result + (Screen != null ? Screen.hashCode() : 0);
        result = 31 * result + (PCScreen != null ? PCScreen.hashCode() : 0);
        result = 31 * result + (Info != null ? Info.hashCode() : 0);
        result = 31 * result + (CreateDate != null ? CreateDate.hashCode() : 0);
        return result;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
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

    public String getInfo() {
        return Info;
    }

    public void setInfo(String info) {
        Info = info;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }
}
