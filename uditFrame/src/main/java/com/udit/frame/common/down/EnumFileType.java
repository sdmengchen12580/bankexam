package com.udit.frame.common.down;


public enum EnumFileType
{
    TYPE_3GP(".3gp","video/3gpp"),
    
    TYPE_APK(".apk","application/vnd.android.package-archive"),
    
    TYPE_ASF(".asf","video/x-ms-asf"),
    
    TYPE_AVI(".avi","video/x-msvideo"),
    
    TYPE_BIN(".bin","application/octet-stream"),
    
    TYPE_BMP(".bmp","image/bmp"),
    
    TYPE_C(".c",        "text/plain"),
    TYPE_CLASS(".class",    "application/octet-stream"), 
    TYPE_CONF(".conf",    "text/plain"),
    TYPE_CPP(".cpp",    "text/plain"), 
    TYPE_DOC(".doc",    "application/msword"), 
    TYPE_EXE(".exe",    "application/octet-stream"), 
    TYPE_GIF(".gif",    "image/gif"),
    TYPE_GTAR(".gtar",    "application/x-gtar"), 
    TYPE_GZ(".gz",        "application/x-gzip"),
    TYPE_H(".h",        "text/plain"),
    TYPE_HTM(".htm",    "text/html"), 
    TYPE_HTML(".html",    "text/html"), 
    TYPE_JAR(".jar",    "application/java-archive"),
    TYPE_JAVA(".java",    "text/plain"), 
    TYPE_JPEG(".jpeg",    "image/jpeg"),
    TYPE_JPG(".jpg",    "image/jpeg"),
    TYPE_JS(".js",        "application/x-javascript"),
    TYPE_LOG(".log",    "text/plain"),
    TYPE_M3U(".m3u",    "audio/x-mpegurl"), 
    TYPE_M4A(".m4a",    "audio/mp4a-latm"), 
    TYPE_M4B(".m4b",    "audio/mp4a-latm"), 
    TYPE_M4P(".m4p",    "audio/mp4a-latm"), 
    TYPE_M4U(".m4u",    "video/vnd.mpegurl"), 
    TYPE_M4V(".m4v",    "video/x-m4v"), 
    TYPE_MOV(".mov",    "video/quicktime"), 
    TYPE_MP2(".mp2",    "audio/x-mpeg"),
    TYPE_MP3(".mp3",    "audio/x-mpeg"), 
    TYPE_MP4(".mp4",    "video/mp4"), 
    TYPE_MPC(".mpc",    "application/vnd.mpohun.certificate"),
    TYPE_MPE(".mpe",    "video/mpeg"), 
    TYPE_MPEG(".mpeg",    "video/mpeg"), 
    TYPE_MPG(".mpg",    "video/mpeg"), 
    TYPE_MPG4(".mpg4",    "video/mp4"),
    TYPE_MPGA(".mpga",    "audio/mpeg"), 
    TYPE_MSG(".msg",    "application/vnd.ms-outlook"), 
    TYPE_OGG(".ogg",    "audio/ogg"),
    TYPE_PDF(".pdf",    "application/pdf"), 
    TYPE_PNG(".png",    "image/png"), 
    TYPE_PPS(".pps",    "application/vnd.ms-powerpoint"), 
    TYPE_PPT(".ppt",    "application/vnd.ms-powerpoint"), 
    TYPE_PROP(".prop",    "text/plain"),
    TYPE_RAR(".rar",    "application/x-rar-compressed"),
    TYPE_RC(".rc",        "text/plain"),
    TYPE_RMVB(".rmvb",    "audio/x-pn-realaudio"),  
    TYPE_RTF(".rtf",    "application/rtf"), 
    TYPE_SH(".sh",        "text/plain"),
    TYPE_TAR(".tar",    "application/x-tar"), 
    TYPE_TGZ(".tgz",    "application/x-compressed"),
    TYPE_TXT(".txt",    "text/plain"), 
    TYPE_WAV(".wav",    "audio/x-wav"), 
    TYPE_WMA(".wma",    "audio/x-ms-wma"), 
    TYPE_WMV(".wmv",    "audio/x-ms-wmv"), 
    TYPE_WPS(".wps",    "application/vnd.ms-works"),
    TYPE_XML(".xml",    "text/plain"),
    TYPE_Z(".z",        "application/x-compress"), 
    TYPE_ZIP(".zip",    "application/zip"), 
    TYPE_NULL("",        "*/*"),
    TYPE_XLS(".xls","application/vnd.ms-excel");
   
    private String name;
    
    private String mime;
    
    private EnumFileType(String name, String mime)
    {
        this.name = name;
        this.mime = mime;
    }
    
    // 普通方法
    public static String getName(String mime)
    {
        for (EnumFileType c : EnumFileType.values())
        {
            if (c.getMime().equals(mime))
            {
                return c.name;
            }
        }
        return null;
    }
    
    public static String getMime(String name)
    {
        for (EnumFileType c : EnumFileType.values())
        {
            if (c.getName().equals(name))
            {
                return c.mime;
            }
        }
        return "";
    }
    
    public String getName()
    {
        return name;
    }
    
    public void setName(String name)
    {
        this.name = name;
    }
    
    public String getMime()
    {
        return mime;
    }
    
    public void setMime(String mime)
    {
        this.mime = mime;
    }
    
}
