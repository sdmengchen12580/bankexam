package com.udit.bankexam.bean_ok;

public class PayWeiXinInfo {
  private String appid;
  
  private String noncestr;
  
  private String packageValue;
  
  private String partnerid;
  
  private String prepayid;
  
  private String sign;
  
  private String timestamp;
  
  public String getAppid() { return this.appid; }
  
  public String getNoncestr() { return this.noncestr; }
  
  public String getPackageValue() { return this.packageValue; }
  
  public String getPartnerid() { return this.partnerid; }
  
  public String getPrepayid() { return this.prepayid; }
  
  public String getSign() { return this.sign; }
  
  public String getTimestamp() { return this.timestamp; }
  
  public void setAppid(String paramString) { this.appid = paramString; }
  
  public void setNoncestr(String paramString) { this.noncestr = paramString; }
  
  public void setPackageValue(String paramString) { this.packageValue = paramString; }
  
  public void setPartnerid(String paramString) { this.partnerid = paramString; }
  
  public void setPrepayid(String paramString) { this.prepayid = paramString; }
  
  public void setSign(String paramString) { this.sign = paramString; }
  
  public void setTimestamp(String paramString) { this.timestamp = paramString; }
  
  public String toString() {
    StringBuilder stringBuilder = new StringBuilder();
    stringBuilder.append("PayWeiXinInfo{appid='");
    stringBuilder.append(this.appid);
    stringBuilder.append('\'');
    stringBuilder.append(", partnerid='");
    stringBuilder.append(this.partnerid);
    stringBuilder.append('\'');
    stringBuilder.append(", prepayid='");
    stringBuilder.append(this.prepayid);
    stringBuilder.append('\'');
    stringBuilder.append(", packageValue='");
    stringBuilder.append(this.packageValue);
    stringBuilder.append('\'');
    stringBuilder.append(", noncestr='");
    stringBuilder.append(this.noncestr);
    stringBuilder.append('\'');
    stringBuilder.append(", timestamp='");
    stringBuilder.append(this.timestamp);
    stringBuilder.append('\'');
    stringBuilder.append(", sign='");
    stringBuilder.append(this.sign);
    stringBuilder.append('\'');
    stringBuilder.append('}');
    return stringBuilder.toString();
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\PayWeiXinInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */