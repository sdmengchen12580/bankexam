package com.udit.bankexam.bean_ok;

import java.io.Serializable;

public class notiftyBean implements Serializable {
  private static final long serialVersionUID = 7348753736376151435L;
  
  private String linkId;
  
  private String mType;
  
  private String msg;
  
  private String msgUrl;
  
  private String name;
  
  private String notify_id;
  
  public String getLinkId() { return this.linkId; }
  
  public String getMsg() { return this.msg; }
  
  public String getMsgUrl() { return this.msgUrl; }
  
  public String getName() { return this.name; }
  
  public String getNotify_id() { return this.notify_id; }
  
  public String getmType() { return this.mType; }
  
  public void setLinkId(String paramString) { this.linkId = paramString; }
  
  public void setMsg(String paramString) { this.msg = paramString; }
  
  public void setMsgUrl(String paramString) { this.msgUrl = paramString; }
  
  public void setName(String paramString) { this.name = paramString; }
  
  public void setNotify_id(String paramString) { this.notify_id = paramString; }
  
  public void setmType(String paramString) { this.mType = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\notiftyBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */