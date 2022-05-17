package com.udit.bankexam.bean;

import java.util.List;

public class Del {


    /**
     * code : 500
     * messages : [{"id":"doAction","message":"未授权"}]
     * data : {"time":"1603796144047"}
     */

    private int code;
    private DataBean data;
    private List<MessagesBean> messages;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public List<MessagesBean> getMessages() {
        return messages;
    }

    public void setMessages(List<MessagesBean> messages) {
        this.messages = messages;
    }

    public static class DataBean {
        /**
         * time : 1603796144047
         */

        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class MessagesBean {
        /**
         * id : doAction
         * message : 未授权
         */

        private String id;
        private String message;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
