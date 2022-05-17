package com.udit.bankexam.bean_ok;

import java.io.Serializable;

public class WxUserInfo implements Serializable {
  private static final long serialVersionUID = 6049671027593574252L;
  
  private String city;
  
  private String headimgurl;
  
  private String nickname;
  
  private String openid;
  
  private String province;
  
  private String sex;
  
  private String unionid;
  
  public String getCity() { return this.city; }
  
  public String getHeadimgurl() { return this.headimgurl; }
  
  public String getNickname() { return this.nickname; }
  
  public String getOpenid() { return this.openid; }
  
  public String getProvince() { return this.province; }
  
  public String getSex() { return this.sex; }
  
  public String getUnionid() { return this.unionid; }
  
  public void setCity(String paramString) { this.city = paramString; }
  
  public void setHeadimgurl(String paramString) { this.headimgurl = paramString; }
  
  public void setNickname(String paramString) { this.nickname = paramString; }
  
  public void setOpenid(String paramString) { this.openid = paramString; }
  
  public void setProvince(String paramString) { this.province = paramString; }
  
  public void setSex(String paramString) { this.sex = paramString; }
  
  public void setUnionid(String paramString) { this.unionid = paramString; }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\WxUserInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */