package com.udit.bankexam.bean_ok;

import java.io.Serializable;

public class VideoFile implements Serializable {
  private static final long serialVersionUID = 4421851050662524227L;
  
  private String afile;
  
  private String cid;
  
  private String eid;
  
  private String id;
  
  private boolean isExpand = false;
  
  private boolean isLast = false;
  
  private boolean isfirst = false;
  
  private String name;
  
  private String ordid;
  
  private String points;
  
  private String vtime;
  
  public String getAfile() { return this.afile; }
  
  public String getCid() { return this.cid; }
  
  public String getEid() { return this.eid; }
  
  public String getId() { return this.id; }
  
  public String getName() { return this.name; }
  
  public String getOrdid() { return this.ordid; }
  
  public String getPoints() { return this.points; }
  
  public String getVtime() { return this.vtime; }
  
  public boolean isExpand() { return this.isExpand; }
  
  public boolean isLast() { return this.isLast; }
  
  public boolean isfirst() { return this.isfirst; }
  
  public void setAfile(String paramString) { this.afile = paramString; }
  
  public void setCid(String paramString) { this.cid = paramString; }
  
  public void setEid(String paramString) { this.eid = paramString; }
  
  public void setExpand(boolean paramBoolean) { this.isExpand = paramBoolean; }
  
  public void setId(String paramString) { this.id = paramString; }
  
  public void setIsfirst(boolean paramBoolean) { this.isfirst = paramBoolean; }
  
  public void setLast(boolean paramBoolean) { this.isLast = paramBoolean; }
  
  public void setName(String paramString) { this.name = paramString; }
  
  public void setOrdid(String paramString) { this.ordid = paramString; }
  
  public void setPoints(String paramString) { this.points = paramString; }
  
  public void setVtime(String paramString) { this.vtime = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\VideoFile.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */