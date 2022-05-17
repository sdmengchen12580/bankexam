package com.udit.bankexam.bean;

import java.io.Serializable;
import java.util.List;

public class VideoDetailsBean implements Serializable {

    public int code;
    public DataDTO data;
    public List<?> messages;

    public static class DataDTO implements Serializable {
        public ResponseDTO response;
        public String time;

        public static class ResponseDTO implements Serializable {
            public RowDTO row;

            public static class RowDTO implements Serializable {
                public String id;
                public String appId;
                public String blwVid;
                public String videoIdAli;
                public String blwContent;
                public String coverUrl;
                public String thumbnailUrl;
                public String blwVideoUrlLd;
                public String blwVideoUrlSd;
                public String blwVideoUrlHd;
                public String videoUrlLd;
                public String videoUrlSd;
                public String videoUrlHd;
                public String videoUrl;
                public String updateTime;
            }
        }
    }
}
