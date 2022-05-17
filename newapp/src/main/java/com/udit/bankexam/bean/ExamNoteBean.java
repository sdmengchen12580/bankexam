package com.udit.bankexam.bean_ok;

public class ExamNoteBean {
  private String Date;
  
  private String ID;
  
  private String LinkID;
  
  private String Note;
  
  private String uid;
  
  public ExamNoteBean() {}
  
  public ExamNoteBean(String paramString1, String paramString2, String paramString3, String paramString4, String paramString5) {
    this.ID = paramString1;
    this.Note = paramString2;
    this.uid = paramString3;
    this.LinkID = paramString4;
    this.Date = paramString5;
  }
  
  public String getDate() { return this.Date; }
  
  public String getID() { return this.ID; }
  
  public String getLinkID() { return this.LinkID; }
  
  public String getNote() { return this.Note; }
  
  public String getUid() { return this.uid; }
  
  public void setDate(String paramString) { this.Date = paramString; }
  
  public void setID(String paramString) { this.ID = paramString; }
  
  public void setLinkID(String paramString) { this.LinkID = paramString; }
  
  public void setNote(String paramString) { this.Note = paramString; }
  
  public void setUid(String paramString) { this.uid = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\ExamNoteBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */