package com.udit.bankexam.bean;

import java.util.List;

public class QuesList {


    /**
     * code : 200
     * messages : []
     * data : {"response":{"count":7,"rows":[{"id":"768813347294806016","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":4,"viewNum":1,"addTime":"2020-10-22 12:29:45","updateTime":"2020-10-25 14:43:07"},{"id":"768868496717512704","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":3,"addTime":"2020-10-22 16:08:54","updateTime":"2020-10-22 16:14:20"},{"id":"768870636294897664","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-22 16:17:24","updateTime":"2020-10-22 16:17:24"},{"id":"768918875643707392","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题3","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-22 19:29:05","updateTime":"2020-10-22 19:29:05"},{"id":"769362359827693568","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-24 00:51:20","updateTime":"2020-10-24 00:51:20"},{"id":"769703221191376896","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"Kodak\u2019s","content":"","userId":"768966992334290944","userNickName":"17602502024","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-24 23:25:48","updateTime":"2020-10-24 23:25:48"},{"id":"770421182646583296","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"","content":"","userId":"770414046076076032","userNickName":"15151960330","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-26 22:58:43","updateTime":"2020-10-26 22:58:43"}]},"time":"1603767124264"}
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
         * response : {"count":7,"rows":[{"id":"768813347294806016","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":4,"viewNum":1,"addTime":"2020-10-22 12:29:45","updateTime":"2020-10-25 14:43:07"},{"id":"768868496717512704","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":3,"addTime":"2020-10-22 16:08:54","updateTime":"2020-10-22 16:14:20"},{"id":"768870636294897664","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-22 16:17:24","updateTime":"2020-10-22 16:17:24"},{"id":"768918875643707392","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题3","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-22 19:29:05","updateTime":"2020-10-22 19:29:05"},{"id":"769362359827693568","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-24 00:51:20","updateTime":"2020-10-24 00:51:20"},{"id":"769703221191376896","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"Kodak\u2019s","content":"","userId":"768966992334290944","userNickName":"17602502024","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-24 23:25:48","updateTime":"2020-10-24 23:25:48"},{"id":"770421182646583296","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"","content":"","userId":"770414046076076032","userNickName":"15151960330","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-26 22:58:43","updateTime":"2020-10-26 22:58:43"}]}
         * time : 1603767124264
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
             * rows : [{"id":"768813347294806016","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":4,"viewNum":1,"addTime":"2020-10-22 12:29:45","updateTime":"2020-10-25 14:43:07"},{"id":"768868496717512704","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":3,"addTime":"2020-10-22 16:08:54","updateTime":"2020-10-22 16:14:20"},{"id":"768870636294897664","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-22 16:17:24","updateTime":"2020-10-22 16:17:24"},{"id":"768918875643707392","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题3","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-22 19:29:05","updateTime":"2020-10-22 19:29:05"},{"id":"769362359827693568","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"我是标题","content":"","userId":"768792568150753280","userNickName":"13851930821","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-24 00:51:20","updateTime":"2020-10-24 00:51:20"},{"id":"769703221191376896","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"Kodak\u2019s","content":"","userId":"768966992334290944","userNickName":"17602502024","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-24 23:25:48","updateTime":"2020-10-24 23:25:48"},{"id":"770421182646583296","appId":"767348035122757632","questionCatCode":"01010101","questionCatCodeName":"新手","title":"","content":"","userId":"770414046076076032","userNickName":"15151960330","userAvatar":"","answerNum":0,"viewNum":0,"addTime":"2020-10-26 22:58:43","updateTime":"2020-10-26 22:58:43"}]
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
                 * id : 768813347294806016
                 * appId : 767348035122757632
                 * questionCatCode : 01010101
                 * questionCatCodeName : 新手
                 * title : 我是标题
                 * content :
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
