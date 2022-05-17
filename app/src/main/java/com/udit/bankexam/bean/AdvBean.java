package com.udit.bankexam.bean;

import java.io.Serializable;

public class AdvBean implements Serializable
{
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 2133451725340155777L;

    private int id;
    
    private String title;
    
    private String img;
    
    private String path;

    
    
    public String getImg()
    {
        return img;
    }

    public void setImg(String img)
    {
        this.img = img;
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
