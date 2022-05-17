package com.udit.bankexam.bean;

import java.io.Serializable;

public class ModuleBean implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -2234476259049013256L;
    
    private int id;
    
    private String title;
    
    private String path;
    
    private int img;
    
    private String url;
    
    private String url_type;
    
    
    public int getImg()
    {
        return img;
    }

    public void setImg(int img)
    {
        this.img = img;
    }

    public String getUrl()
    {
        return url;
    }
    
    public void setUrl(String url)
    {
        this.url = url;
    }
    
    public String getUrl_type()
    {
        return url_type;
    }
    
    public void setUrl_type(String url_type)
    {
        this.url_type = url_type;
    }
    
  
    
    public int getId()
    {
        return id;
    }
    
    public void setId(int id)
    {
        this.id = id;
    }
    
    public String getTitle()
    {
        return title;
    }
    
    public void setTitle(String title)
    {
        this.title = title;
    }
    
    public String getPath()
    {
        return path;
    }
    
    public void setPath(String path)
    {
        this.path = path;
    }
    
}
