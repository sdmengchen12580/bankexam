package com.udit.bankexam.bean_ok;

import java.io.Serializable;

public class VideoTypeOneBean implements Serializable {
  private static final long serialVersionUID = -7770713387787434156L;
  
  private String VType;
  
  private String picture;
  
  private String type_num;
  
  private String video_num;
  
  public String getPicture() { return this.picture; }
  
  public String getType_num() { return this.type_num; }
  
  public String getVType() { return this.VType; }
  
  public String getVideo_num() { return this.video_num; }
  
  public void setPicture(String paramString) { this.picture = paramString; }
  
  public void setType_num(String paramString) { this.type_num = paramString; }
  
  public void setVType(String paramString) { this.VType = paramString; }
  
  public void setVideo_num(String paramString) { this.video_num = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\VideoTypeOneBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */