package com.udit.bankexam.bean_ok;

import java.io.Serializable;

public class ExamOptionBean implements Serializable {
  private static final long serialVersionUID = 8009095674903680582L;
  
  private String SList;
  
  private String _id;
  
  private String examid;
  
  private String orderID;
  
  private String single;
  
  private String titleListID;
  
  private String user_id;
  
  public ExamOptionBean() {}
  
  public ExamOptionBean(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, String paramString7) {
    this._id = paramString1;
    this.examid = paramString2;
    this.titleListID = paramString3;
    this.orderID = paramString4;
    this.single = paramString5;
    this.SList = paramString6;
    this.user_id = paramString7;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null) {
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = (ExamOptionBean)paramObject;
      if (this.titleListID != null) {
        if (!this.titleListID.equals(paramObject.titleListID))
          return false; 
      } else if (paramObject.titleListID != null) {
        return false;
      } 
      if (this.orderID != null) {
        if (!this.orderID.equals(paramObject.orderID))
          return false; 
      } else if (paramObject.orderID != null) {
        return false;
      } 
      if (this.single != null) {
        if (!this.single.equals(paramObject.single))
          return false; 
      } else if (paramObject.single != null) {
        return false;
      } 
      return (this.SList != null) ? this.SList.equals(paramObject.SList) : ((paramObject.SList == null) ? 1 : 0);
    } 
    return false;
  }
  
  public String getExamid() { return this.examid; }
  
  public String getOrderID() { return this.orderID; }
  
  public String getSList() { return this.SList; }
  
  public String getSingle() { return this.single; }
  
  public String getTitleListID() { return this.titleListID; }
  
  public String getUser_id() { return this.user_id; }
  
  public String get_id() { return this._id; }
  
  public int hashCode() {
    byte b2;
    byte b1;
    boolean bool;
    String str = this.titleListID;
    int i = 0;
    if (str != null) {
      bool = this.titleListID.hashCode();
    } else {
      bool = false;
    } 
    if (this.orderID != null) {
      b1 = this.orderID.hashCode();
    } else {
      b1 = 0;
    } 
    if (this.single != null) {
      b2 = this.single.hashCode();
    } else {
      b2 = 0;
    } 
    if (this.SList != null)
      i = this.SList.hashCode(); 
    return 31 * ((bool * 31 + b1) * 31 + b2) + i;
  }
  
  public void setExamid(String paramString) { this.examid = paramString; }
  
  public void setOrderID(String paramString) { this.orderID = paramString; }
  
  public void setSList(String paramString) { this.SList = paramString; }
  
  public void setSingle(String paramString) { this.single = paramString; }
  
  public void setTitleListID(String paramString) { this.titleListID = paramString; }
  
  public void setUser_id(String paramString) { this.user_id = paramString; }
  
  public void set_id(String paramString) { this._id = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\ExamOptionBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */