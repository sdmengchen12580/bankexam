package com.udit.bankexam.bean;

import java.util.List;

public class CommentList {


    /**
     * code : 200
     * messages : []
     * data : {"response":{"count":2,"rows":[{"id":"768870817807597568","appId":"767348035122757632","ykQuestionId":"0","answerContent":"我是评论评论","isPraised":false,"praiseNum":5,"answerUserNickName":"13851930821","answerUserAvatar":"","isCanDel":false,"addTime":"2020-10-22 16:18:07","updateTime":"2020-10-25 13:56:31"},{"id":"769934073850626048","appId":"767348035122757632","ykQuestionId":"0","answerContent":"Xxxxx","isPraised":false,"praiseNum":2,"answerUserNickName":"17602502024","answerUserAvatar":"","isCanDel":false,"addTime":"2020-10-25 14:43:07","updateTime":"2020-10-26 23:13:04"}]},"time":"1603791822733"}
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
         * response : {"count":2,"rows":[{"id":"768870817807597568","appId":"767348035122757632","ykQuestionId":"0","answerContent":"我是评论评论","isPraised":false,"praiseNum":5,"answerUserNickName":"13851930821","answerUserAvatar":"","isCanDel":false,"addTime":"2020-10-22 16:18:07","updateTime":"2020-10-25 13:56:31"},{"id":"769934073850626048","appId":"767348035122757632","ykQuestionId":"0","answerContent":"Xxxxx","isPraised":false,"praiseNum":2,"answerUserNickName":"17602502024","answerUserAvatar":"","isCanDel":false,"addTime":"2020-10-25 14:43:07","updateTime":"2020-10-26 23:13:04"}]}
         * time : 1603791822733
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
             * count : 2
             * rows : [{"id":"768870817807597568","appId":"767348035122757632","ykQuestionId":"0","answerContent":"我是评论评论","isPraised":false,"praiseNum":5,"answerUserNickName":"13851930821","answerUserAvatar":"","isCanDel":false,"addTime":"2020-10-22 16:18:07","updateTime":"2020-10-25 13:56:31"},{"id":"769934073850626048","appId":"767348035122757632","ykQuestionId":"0","answerContent":"Xxxxx","isPraised":false,"praiseNum":2,"answerUserNickName":"17602502024","answerUserAvatar":"","isCanDel":false,"addTime":"2020-10-25 14:43:07","updateTime":"2020-10-26 23:13:04"}]
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
                 * id : 768870817807597568
                 * appId : 767348035122757632
                 * ykQuestionId : 0
                 * answerContent : 我是评论评论
                 * isPraised : false
                 * praiseNum : 5
                 * answerUserNickName : 13851930821
                 * answerUserAvatar :
                 * isCanDel : false
                 * addTime : 2020-10-22 16:18:07
                 * updateTime : 2020-10-25 13:56:31
                 */

                private String id;
                private String appId;
                private String ykQuestionId;
                private String answerContent;
                private boolean isPraised;
                private int praiseNum;
                private String answerUserNickName;
                private String answerUserAvatar;
                private boolean isCanDel;
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

                public String getYkQuestionId() {
                    return ykQuestionId;
                }

                public void setYkQuestionId(String ykQuestionId) {
                    this.ykQuestionId = ykQuestionId;
                }

                public String getAnswerContent() {
                    return answerContent;
                }

                public void setAnswerContent(String answerContent) {
                    this.answerContent = answerContent;
                }

                public boolean isIsPraised() {
                    return isPraised;
                }

                public void setIsPraised(boolean isPraised) {
                    this.isPraised = isPraised;
                }

                public int getPraiseNum() {
                    return praiseNum;
                }

                public void setPraiseNum(int praiseNum) {
                    this.praiseNum = praiseNum;
                }

                public String getAnswerUserNickName() {
                    return answerUserNickName;
                }

                public void setAnswerUserNickName(String answerUserNickName) {
                    this.answerUserNickName = answerUserNickName;
                }

                public String getAnswerUserAvatar() {
                    return answerUserAvatar;
                }

                public void setAnswerUserAvatar(String answerUserAvatar) {
                    this.answerUserAvatar = answerUserAvatar;
                }

                public boolean isIsCanDel() {
                    return isCanDel;
                }

                public void setIsCanDel(boolean isCanDel) {
                    this.isCanDel = isCanDel;
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
