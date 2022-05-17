package com.udit.bankexam.bean_ok;

public class FavoriteRecord {
  private String FDate;
  
  private String FType;
  
  private String ID;
  
  private String LinkID;
  
  private String UID;
  
  public FavoriteRecord() {}
  
  public FavoriteRecord(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    this.ID = paramString1;
    this.FType = paramString2;
    this.FDate = paramString3;
    this.LinkID = paramString4;
    this.UID = paramString5;
  }
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null) {
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = (FavoriteRecord)paramObject;
      if (this.FType != null) {
        if (!this.FType.equals(paramObject.FType))
          return false; 
      } else if (paramObject.FType != null) {
        return false;
      } 
      if (this.LinkID != null) {
        if (!this.LinkID.equals(paramObject.LinkID))
          return false; 
      } else if (paramObject.LinkID != null) {
        return false;
      } 
      return (this.UID != null) ? this.UID.equals(paramObject.UID) : ((paramObject.UID == null) ? 1 : 0);
    } 
    return false;
  }
  
  public String getFDate() { return this.FDate; }
  
  public String getFType() { return this.FType; }
  
  public String getID() { return this.ID; }
  
  public String getLinkID() { return this.LinkID; }
  
  public String getUID() { return this.UID; }
  
  public int hashCode() {
    byte b;
    boolean bool;
    String str = this.FType;
    int i = 0;
    if (str != null) {
      bool = this.FType.hashCode();
    } else {
      bool = false;
    } 
    if (this.LinkID != null) {
      b = this.LinkID.hashCode();
    } else {
      b = 0;
    } 
    if (this.UID != null)
      i = this.UID.hashCode(); 
    return 31 * (bool * 31 + b) + i;
  }
  
  public void setFDate(String paramString) { this.FDate = paramString; }
  
  public void setFType(String paramString) { this.FType = paramString; }
  
  public void setID(String paramString) { this.ID = paramString; }
  
  public void setLinkID(String paramString) { this.LinkID = paramString; }
  
  public void setUID(String paramString) { this.UID = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\FavoriteRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */