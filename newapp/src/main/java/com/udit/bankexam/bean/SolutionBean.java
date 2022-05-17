package com.udit.bankexam.bean_ok;

public class SolutionBean {
  private String ATime;
  
  private String Answer;
  
  private String GetScore;
  
  private String ID;
  
  private String UserTime;
  
  private String isOK;
  
  public boolean equals(Object paramObject) {
    if (this == paramObject)
      return true; 
    if (paramObject != null) {
      if (getClass() != paramObject.getClass())
        return false; 
      paramObject = (SolutionBean)paramObject;
      if (this.Answer != null) {
        if (!this.Answer.equals(paramObject.Answer))
          return false; 
      } else if (paramObject.Answer != null) {
        return false;
      } 
      return (this.ID != null) ? (!this.ID.equals(paramObject.ID)) : ((paramObject.ID != null));
    } 
    return false;
  }
  
  public String getATime() { return this.ATime; }
  
  public String getAnswer() { return this.Answer; }
  
  public String getGetScore() { return this.GetScore; }
  
  public String getID() { return this.ID; }
  
  public String getIsOK() { return this.isOK; }
  
  public String getUserTime() { return this.UserTime; }
  
  public int hashCode() {
    byte b4;
    byte b3;
    byte b2;
    byte b1;
    boolean bool;
    String str = this.ATime;
    int i = 0;
    if (str != null) {
      bool = this.ATime.hashCode();
    } else {
      bool = false;
    } 
    if (this.ID != null) {
      b1 = this.ID.hashCode();
    } else {
      b1 = 0;
    } 
    if (this.Answer != null) {
      b2 = this.Answer.hashCode();
    } else {
      b2 = 0;
    } 
    if (this.isOK != null) {
      b3 = this.isOK.hashCode();
    } else {
      b3 = 0;
    } 
    if (this.GetScore != null) {
      b4 = this.GetScore.hashCode();
    } else {
      b4 = 0;
    } 
    if (this.UserTime != null)
      i = this.UserTime.hashCode(); 
    return 31 * ((((bool * 31 + b1) * 31 + b2) * 31 + b3) * 31 + b4) + i;
  }
  
  public void setATime(String paramString) { this.ATime = paramString; }
  
  public void setAnswer(String paramString) { this.Answer = paramString; }
  
  public void setGetScore(String paramString) { this.GetScore = paramString; }
  
  public void setID(String paramString) { this.ID = paramString; }
  
  public void setIsOK(String paramString) { this.isOK = paramString; }
  
  public void setUserTime(String paramString) { this.UserTime = paramString; }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("SolutionBean{ATime='");
    stringBuilder.append(this.ATime);
    stringBuilder.append('\'');
    stringBuilder.append(", ID='");
    stringBuilder.append(this.ID);
    stringBuilder.append('\'');
    stringBuilder.append(", Answer='");
    stringBuilder.append(this.Answer);
    stringBuilder.append('\'');
    stringBuilder.append(", isOK='");
    stringBuilder.append(this.isOK);
    stringBuilder.append('\'');
    stringBuilder.append(", GetScore='");
    stringBuilder.append(this.GetScore);
    stringBuilder.append('\'');
    stringBuilder.append(", UserTime='");
    stringBuilder.append(this.UserTime);
    stringBuilder.append('\'');
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\SolutionBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */