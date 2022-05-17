package com.udit.bankexam.bean;

import java.util.List;

public class Myques {


    /**
     * code : 200
     * messages : []
     * data : {"response":{"count":7,"rows":[{"id":"770614293867921408","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:04","updateTime":"2020-10-27 11:46:04"},{"id":"770614441083797504","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:40","updateTime":"2020-10-27 11:46:40"},{"id":"770614447568191488","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:41","updateTime":"2020-10-27 11:46:41"},{"id":"770614451833798656","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:42","updateTime":"2020-10-27 11:46:42"},{"id":"770614454610427904","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:43","updateTime":"2020-10-27 11:46:43"},{"id":"770615087304409088","appId":"767348035122757632","questionCatCode":"01010102","questionCatCodeName":"网申","title":"欧尼","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:49:14","updateTime":"2020-10-27 11:49:14"},{"id":"770619501830340608","appId":"767348035122757632","questionCatCode":"01010102","questionCatCodeName":"网申","title":"标题","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 12:06:46","updateTime":"2020-10-27 12:06:46"}]},"time":"1603772890503"}
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
         * response : {"count":7,"rows":[{"id":"770614293867921408","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:04","updateTime":"2020-10-27 11:46:04"},{"id":"770614441083797504","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:40","updateTime":"2020-10-27 11:46:40"},{"id":"770614447568191488","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:41","updateTime":"2020-10-27 11:46:41"},{"id":"770614451833798656","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:42","updateTime":"2020-10-27 11:46:42"},{"id":"770614454610427904","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:43","updateTime":"2020-10-27 11:46:43"},{"id":"770615087304409088","appId":"767348035122757632","questionCatCode":"01010102","questionCatCodeName":"网申","title":"欧尼","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:49:14","updateTime":"2020-10-27 11:49:14"},{"id":"770619501830340608","appId":"767348035122757632","questionCatCode":"01010102","questionCatCodeName":"网申","title":"标题","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 12:06:46","updateTime":"2020-10-27 12:06:46"}]}
         * time : 1603772890503
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
             * count : 7
             * rows : [{"id":"770614293867921408","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:04","updateTime":"2020-10-27 11:46:04"},{"id":"770614441083797504","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:40","updateTime":"2020-10-27 11:46:40"},{"id":"770614447568191488","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:41","updateTime":"2020-10-27 11:46:41"},{"id":"770614451833798656","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:42","updateTime":"2020-10-27 11:46:42"},{"id":"770614454610427904","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"哈哈","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:46:43","updateTime":"2020-10-27 11:46:43"},{"id":"770615087304409088","appId":"767348035122757632","questionCatCode":"01010102","questionCatCodeName":"网申","title":"欧尼","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 11:49:14","updateTime":"2020-10-27 11:49:14"},{"id":"770619501830340608","appId":"767348035122757632","questionCatCode":"01010102","questionCatCodeName":"网申","title":"标题","content":"","userId":"770585259419238400","userNickName":"15995724041","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-27 12:06:46","updateTime":"2020-10-27 12:06:46"}]
             */

            private int count;
            private List<RowsBean> rows;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * id : 770614293867921408
                 * appId : 767348035122757632
                 * questionCatCode : 01010101
                 * questionCatCodeName : 新手
                 * title : 哈哈
                 * content :
                 * userId : 770585259419238400
                 * userNickName : 15995724041
                 * userAvatar :
                 * answerNum : 0
                 * viewNum : 0
                 * addTime : 2020-10-27 11:46:04
                 * updateTime : 2020-10-27 11:46:04
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
