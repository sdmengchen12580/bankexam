package com.udit.bankexam.bean_ok;

import java.io.Serializable;
import java.util.List;

public class ExamNodeBean implements Serializable {
  private static final long serialVersionUID = -2844767070708155955L;
  

  



  





  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null) {
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = (ExamNodeBean)paramObject;
      return !this.ID.equals(paramObject.ID) ? false : (!this.EID.equals(paramObject.EID) ? false : (!this.superID.equals(paramObject.superID) ? false : this.name.equals(paramObject.name)));
    } 
    return false;
  }
  
  public int getDoCount() { return this.doCount; }
  
  public String getEID() { return this.EID; }
  
  public int getErrCount() { return this.errCount; }
  
  public String getID() { return this.ID; }
  
  public String getIsOver() { return this.isOver; }
  
  public List<ExamNodeBean> getList_outlineinfo() { return this.list_outlineinfo; }
  
  public String getName() { return this.name; }
  
  public int getNoCount() { return this.noCount; }
  
  public String getOType() { return this.OType; }
  
  public int getOkCount() { return this.okCount; }
  
  public String getOrdID() { return this.ordID; }
  
  public String getSuperID() { return this.superID; }
  
  public int getTCount() { return this.TCount; }
  
  public String getTTime() { return this.TTime; }
  
  public int hashCode() { return 31 * ((this.ID.hashCode() * 31 + this.EID.hashCode()) * 31 + this.superID.hashCode()) + this.name.hashCode(); }
  
  public boolean isExpand() { return this.isExpand; }
  
  public boolean isFirstDate() { return this.isFirstDate; }
  
  public boolean isLast() { return this.isLast; }
  
  public boolean isfirst() { return this.isfirst; }
  
  public void setDoCount(int paramInt) { this.doCount = paramInt; }
  
  public void setEID(String paramString) { this.EID = paramString; }
  
  public void setErrCount(int paramInt) { this.errCount = paramInt; }
  
  public void setExpand(boolean paramBoolean) { this.isExpand = paramBoolean; }
  
  public void setFirstDate(boolean paramBoolean) { this.isFirstDate = paramBoolean; }
  
  public void setID(String paramString) { this.ID = paramString; }
  
  public void setIsOver(String paramString) { this.isOver = paramString; }
  
  public void setIsfirst(boolean paramBoolean) { this.isfirst = paramBoolean; }
  
  public void setLast(boolean paramBoolean) { this.isLast = paramBoolean; }
  
  public void setList_outlineinfo(List<ExamNodeBean> paramList) { this.list_outlineinfo = paramList; }
  
  public void setName(String paramString) { this.name = paramString; }
  
  public void setNoCount(int paramInt) { this.noCount = paramInt; }
  
  public void setOType(String paramString) { this.OType = paramString; }
  
  public void setOkCount(int paramInt) { this.okCount = paramInt; }
  
  public void setOrdID(String paramString) { this.ordID = paramString; }
  
  public void setSuperID(String paramString) { this.superID = paramString; }
  
  public void setTCount(int paramInt) { this.TCount = paramInt; }
  
  public void setTTime(String paramString) { this.TTime = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\ExamNodeBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */