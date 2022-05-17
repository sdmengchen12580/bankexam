package com.udit.bankexam.bean;

import java.util.List;

public class Detail {


    /**
     * code : 200
     * messages : []
     * data : {"response":{"row":{"id":"768813347294806016","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"","title":"我是标题","content":"我是内容","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":4,"viewNum":1,"addTime":"2020-10-22 12:29:45","updateTime":"2020-10-25 14:43:07"}},"time":"1603779087234"}
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
         * response : {"row":{"id":"768813347294806016","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"","title":"我是标题","content":"我是内容","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":4,"viewNum":1,"addTime":"2020-10-22 12:29:45","updateTime":"2020-10-25 14:43:07"}}
         * time : 1603779087234
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
             * row : {"id":"768813347294806016","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"","title":"我是标题","content":"我是内容","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":4,"viewNum":1,"addTime":"2020-10-22 12:29:45","updateTime":"2020-10-25 14:43:07"}
             */

            private RowBean row;

            public RowBean getRow() {
                return row;
            }

            public void setRow(RowBean row) {
                this.row = row;
            }

            public static class RowBean {
                /**
                 * id : 768813347294806016
                 * appId : 767348035122757632
                 * questionCatCode : 01010101
                 * questionCatCodeName :
                 * title : 我是标题
                 * content : 我是内容
                 * userId : 768792568150753280
                 * userNickName : 13851930821
                 * userAvatar :
                 * answerNum : 4
                 * viewNum : 1
                 * addTime : 2020-10-22 12:29:45
                 * updateTime : 2020-10-25 14:43:07
                 */

                private String id;
                private String appId;
                private String questionCatCode;
                private String questionCatCodeName;
                private String title;
                private String content;
                private String userId;
                private String userNickName;
                private String userAvatar;
                private int answerNum;
                private int viewNum;
                private String addTime;
                private String updateTime;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getAppId() {
                    return appId;
                }

                public void setAppId(String appId) {
                    this.appId = appId;
                }

                public String getQuestionCatCode() {
                    return questionCatCode;
                }

                public void setQuestionCatCode(String questionCatCode) {
                    this.questionCatCode = questionCatCode;
                }

                public String getQuestionCatCodeName() {
                    return questionCatCodeName;
                }

                public void setQuestionCatCodeName(String questionCatCodeName) {
                    this.questionCatCodeName = questionCatCodeName;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getUserId() {
                    return userId;
                }

                public void setUserId(String userId) {
                    this.userId = userId;
                }

                public String getUserNickName() {
                    return userNickName;
                }

                public void setUserNickName(String userNickName) {
                    this.userNickName = userNickName;
                }

                public String getUserAvatar() {
                    return userAvatar;
                }

                public void setUserAvatar(String userAvatar) {
                    this.userAvatar = userAvatar;
                }

                public int getAnswerNum() {
                    return answerNum;
                }

                public void setAnswerNum(int answerNum) {
                    this.answerNum = answerNum;
                }

                public int getViewNum() {
                    return viewNum;
                }

                public void setViewNum(int viewNum) {
                    this.viewNum = viewNum;
                }

                public String getAddTime() {
                    return addTime;
                }

                public void setAddTime(String addTime) {
                    this.addTime = addTime;
                }

                public String getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                    this.updateTime = updateTime;
                }
            }
        }
    }
}
