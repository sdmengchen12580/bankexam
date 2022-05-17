package com.udit.bankexam.bean;

import java.util.List;

public class NewUserBean {

    /**
     * code : 200
     * messages : []
     * data : {"response":{"flag":true,"sessionKey":"ce9c0128240d4df2fc4b7932df13da27","nickName":"15995724041","avatar":"","userId":"770585259419238400"},"time":"1603763441608"}
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
         * response : {"flag":true,"sessionKey":"ce9c0128240d4df2fc4b7932df13da27","nickName":"15995724041","avatar":"","userId":"770585259419238400"}
         * time : 1603763441608
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
             * flag : true
             * sessionKey : ce9c0128240d4df2fc4b7932df13da27
             * nickName : 15995724041
             * avatar :
             * userId : 770585259419238400
             */

            private boolean flag;
            private String sessionKey;
            private String nickName;
            private String avatar;
            private String userId;

            public boolean isFlag() {
                return flag;
            }

            public void setFlag(boolean flag) {
                this.flag = flag;
            }

            public String getSessionKey() {
                return sessionKey;
            }

            public void setSessionKey(String sessionKey) {
                this.sessionKey = sessionKey;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getAvatar() {
                return avatar;
            }

            public void setAvatar(String avatar) {
                this.avatar = avatar;
            }

            public String getUserId() {
                return userId;
            }

            public void setUserId(String userId) {
                this.userId = userId;
            }
        }
    }
}
