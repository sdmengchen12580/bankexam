package com.udit.bankexam.bean_ok;

import java.io.Serializable;

public class PurchBean implements Serializable {
  private static final long serialVersionUID = 261738356542078077L;
  
  private String Abstract;
  
  private String Fee;
  
  private String FeeDate;
  
  private String Intro;
  
  private String LinkID;
  
  private String PState;
  
  private String PType;
  
  private String pID;
  
  private String uid;
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null) {
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = (PurchBean)paramObject;
      if (this.uid != null) {
        if (!this.uid.equals(paramObject.uid))
          return false; 
      } else if (paramObject.uid != null) {
        return false;
      } 
      return (this.LinkID != null) ? this.LinkID.equals(paramObject.LinkID) : ((paramObject.LinkID == null) ? 1 : 0);
    } 
    return false;
  }
  
  public String getAbstract() { return this.Abstract; }
  
  public String getFee() { return this.Fee; }
  
  public String getFeeDate() { return this.FeeDate; }
  
  public String getIntro() { return this.Intro; }
  
  public String getLinkID() { return this.LinkID; }
  
  public String getPState() { return this.PState; }
  
  public String getPType() { return this.PType; }
  
  public String getUid() { return this.uid; }
  
  public String getpID() { return this.pID; }
  
  public int hashCode() {
    boolean bool;
    String str = this.uid;
    int i = 0;
    if (str != null) {
      bool = this.uid.hashCode();
    } else {
      bool = false;
    } 
    if (this.LinkID != null)
      i = this.LinkID.hashCode(); 
    return 31 * bool + i;
  }
  
  public void setAbstract(String paramString) { this.Abstract = paramString; }
  
  public void setFee(String paramString) { this.Fee = paramString; }
  
  public void setFeeDate(String paramString) { this.FeeDate = paramString; }
  
  public void setIntro(String paramString) { this.Intro = paramString; }
  
  public void setLinkID(String paramString) { this.LinkID = paramString; }
  
  public void setPState(String paramString) { this.PState = paramString; }
  
  public void setPType(String paramString) { this.PType = paramString; }
  
  public void setUid(String paramString) { this.uid = paramString; }
  
  public void setpID(String paramString) { this.pID = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\PurchBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */