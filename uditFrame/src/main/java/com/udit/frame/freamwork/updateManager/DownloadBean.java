package com.udit.frame.freamwork.updateManager;

import java.io.Serializable;

/**
 * 
 *  
 * @author 曾宝
 * @version  [V1.00, 2016年2月14日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class DownloadBean implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8861699601238484405L;
    /**
     * 下载地址Id
     */
    private int id;
    /**
     * 下载url
     */
    private String url;
    /**
     * 下载名称
     */
    private String name;
    /**
     * 版本号
     */
    private String version;
    /**
     * 更新内容
     */
    private String remark;
    
    
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }
    public String getUrl()
    {
        return url;
    }
    public void setUrl(String url)
    {
        this.url = url;
    }
    public String getVersion()
    {
        return version;
    }
    public void setVersion(String version)
    {
        this.version = version;
    }
    public String getRemark()
    {
        return remark;
    }
    public void setRemark(String remark)
    {
        this.remark = remark;
    }
    
    
}
