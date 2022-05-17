package com.udit.bankexam.bean_ok;

import java.io.Serializable;

public class ModuleBean implements Serializable {
  private static final long serialVersionUID = -2234476259049013256L;
  
  private int id;
  
  private int img;
  
  private String path;
  
  private String title;
  
  private String url;
  
  private String url_type;
  
  public int getId() { return this.id; }
  
  public int getImg() { return this.img; }
  
  public String getPath() { return this.path; }
  
  public String getTitle() { return this.title; }
  
  public String getUrl() { return this.url; }
  
  public String getUrl_type() { return this.url_type; }
  
  public void setId(int paramInt) { this.id = paramInt; }
  
  public void setImg(int paramInt) { this.img = paramInt; }
  
  public void setPath(String paramString) { this.path = paramString; }
  
  public void setTitle(String paramString) { this.title = paramString; }
  
  public void setUrl(String paramString) { this.url = paramString; }
  
  public void setUrl_type(String paramString) { this.url_type = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\ModuleBean.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */