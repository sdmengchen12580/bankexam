package com.udit.bankexam.bean_ok;

import java.util.List;

public class ZhaoPinInfo {
  private int code;
  
  private DataBean data;
  
  private List<?> messages;
  
  public int getCode() { return this.code; }
  
  public DataBean getData() { return this.data; }
  
  public List<?> getMessages() { return this.messages; }
  
  public void setCode(int paramInt) { this.code = paramInt; }
  
  public void setData(DataBean paramDataBean) { this.data = paramDataBean; }
  
  public void setMessages(List<?> paramList) { this.messages = paramList; }
  
  public static class DataBean {
    private ResponseBean response;
    
    private String time;
    
    public ResponseBean getResponse() { return this.response; }
    
    public String getTime() { return this.time; }
    
    public void setResponse(ResponseBean param1ResponseBean) { this.response = param1ResponseBean; }
    
    public void setTime(String param1String) { this.time = param1String; }
    
    public static class ResponseBean {
      private List<ZhaopinsBean> zhaopins;
      
      public List<ZhaopinsBean> getZhaopins() { return this.zhaopins; }
      
      public void setZhaopins(List<ZhaopinsBean> param2List) { this.zhaopins = param2List; }
      
      public static class ZhaopinsBean {
        private String h5Url;
        
        private String title;
        
        public String getH5Url() { return this.h5Url; }
        
        public String getTitle() { return this.title; }
        
        public void setH5Url(String param3String) { this.h5Url = param3String; }
        
        public void setTitle(String param3String) { this.title = param3String; }
      }
    }
    
    public static class ZhaopinsBean {
      private String h5Url;
      
      private String title;
      
      public String getH5Url() { return this.h5Url; }
      
      public String getTitle() { return this.title; }
      
      public void setH5Url(String param2String) { this.h5Url = param2String; }
      
      public void setTitle(String param2String) { this.title = param2String; }
    }
  }
  
  public static class ResponseBean {
    private List<ZhaopinsBean> zhaopins;
    
    public List<ZhaopinsBean> getZhaopins() { return this.zhaopins; }
    
    public void setZhaopins(List<ZhaopinsBean> param1List) { this.zhaopins = param1List; }
    
    public static class ZhaopinsBean {
      private String h5Url;
      
      private String title;
      
      public String getH5Url() { return this.h5Url; }
      
      public String getTitle() { return this.title; }
      
      public void setH5Url(String param3String) { this.h5Url = param3String; }
      
      public void setTitle(String param3String) { this.title = param3String; }
    }
  }
  
  public static class ZhaopinsBean {
    private String h5Url;
    
    private String title;
    
    public String getH5Url() { return this.h5Url; }
    
    public String getTitle() { return this.title; }
    
    public void setH5Url(String param1String) { this.h5Url = param1String; }
    
    public void setTitle(String param1String) { this.title = param1String; }
  }
}


/* Location:              C:\Users\Administrator\Desktop\classes-dex2jar.jar!\co\\udit\bankexam\bean\ZhaoPinInfo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.0.7
 */