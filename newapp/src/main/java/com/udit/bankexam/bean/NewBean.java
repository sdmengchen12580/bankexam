package com.udit.bankexam.bean_ok;

import java.io.Serializable;

public class NewBean implements Serializable {
  private static final long serialVersionUID = 6231378635097859052L;
  
  private String CreateDate;
  
  private int ID;
  
  private String Info;
  
  private String Name;
  
  private String PCScreen;
  
  private String Screen;
  
  private String TypeName;
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null) {
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = (NewBean)paramObject;
      if (this.ID != paramObject.ID)
        return false; 
      if (this.TypeName != null) {
        if (!this.TypeName.equals(paramObject.TypeName))
          return false; 
      } else if (paramObject.TypeName != null) {
        return false;
      } 
      if (this.Name != null) {
        if (!this.Name.equals(paramObject.Name))
          return false; 
      } else if (paramObject.Name != null) {
        return false;
      } 
      if (this.Screen != null) {
        if (!this.Screen.equals(paramObject.Screen))
          return false; 
      } else if (paramObject.Screen != null) {
        return false;
      } 
      if (this.PCScreen != null) {
        if (!this.PCScreen.equals(paramObject.PCScreen))
          return false; 
      } else if (paramObject.PCScreen != null) {
        return false;
      } 
      if (this.Info != null) {
        if (!this.Info.equals(paramObject.Info))
          return false; 
      } else if (paramObject.Info != null) {
        return false;
      } 
      return (this.CreateDate != null) ? this.CreateDate.equals(paramObject.CreateDate) : ((paramObject.CreateDate == null) ? 1 : 0);
    } 
    return false;
  }
  
  public String getCreateDate() { return this.CreateDate; }
  
  public int getID() { return this.ID; }
  
  public String getInfo() { return this.Info; }
  
  public String getName() { return this.Name; }
  
  public String getPCScreen() { return this.PCScreen; }
  
  public String getScreen() { return this.Screen; }
  
  public String getTypeName() { return this.TypeName; }
  
  public int hashCode() {
    int n;
    int m;
    int k;
    int j;
    int i;
    int i2 = this.ID;
    String str = this.TypeName;
    int i1 = 0;
    if (str != null) {
      i = this.TypeName.hashCode();
    } else {
      i = 0;
    } 
    if (this.Name != null) {
      j = this.Name.hashCode();
    } else {
      j = 0;
    } 
    if (this.Screen != null) {
      k = this.Screen.hashCode();
    } else {
      k = 0;
    } 
    if (this.PCScreen != null) {
      m = this.PCScreen.hashCode();
    } else {
      m = 0;
    } 
    if (this.Info != null) {
      n = this.Info.hashCode();
    } else {
      n = 0;
    } 
    if (this.CreateDate != null)
      i1 = this.CreateDate.hashCode(); 
    return 31 * (((((i2 * 31 + i) * 31 + j) * 31 + k) * 31 + m) * 31 + n) + i1;
  }
  
  public void setCreateDate(String paramString) { this.CreateDate = paramString; }
  
  public void setID(int paramInt) { this.ID = paramInt; }
  
  public void setInfo(String paramString) { this.Info = paramString; }
  
  public void setName(String paramString) { this.Name = paramString; }
  
  public void setPCScreen(String paramString) { this.PCScreen = paramString; }
  
  public void setScreen(String paramString) { this.Screen = paramString; }
  
  public void setTypeName(String paramString) { this.TypeName = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\NewBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */