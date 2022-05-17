package com.udit.bankexam.bean;

import java.util.List;

public class UpImgBean {

    /**
     * code : 200
     * messages : []
     * data : {"response":{"heightPerWidth":1,"url":"http://ykv2-test.project.njagan.org/oss/yikao/20201104/20653e790e24809f025af0f3572727d3.jpg"},"time":"1604477243322"}
     */

    private int code;
    private DataBean data;
    private List<?> messages;

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

    public List<?> getMessages() {
        return messages;
    }

    public void setMessages(List<?> messages) {
        this.messages = messages;
    }

    public static class DataBean {
        /**
         * response : {"heightPerWidth":1,"url":"http://ykv2-test.project.njagan.org/oss/yikao/20201104/20653e790e24809f025af0f3572727d3.jpg"}
         * time : 1604477243322
         */

        private ResponseBean response;
        private String time;

        public ResponseBean getResponse() {
            return response;
        }

        public void setResponse(ResponseBean response) {
            this.response = response;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public static class ResponseBean {
            /**
             * heightPerWidth : 1.0
             * url : http://ykv2-test.project.njagan.org/oss/yikao/20201104/20653e790e24809f025af0f3572727d3.jpg
             */

            private double heightPerWidth;
            private String url;

            public double getHeightPerWidth() {
                return heightPerWidth;
            }

            public void setHeightPerWidth(double heightPerWidth) {
                this.heightPerWidth = heightPerWidth;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
