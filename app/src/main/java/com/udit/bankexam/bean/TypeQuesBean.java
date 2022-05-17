package com.udit.bankexam.bean;

import java.util.List;

public class TypeQuesBean {


    /**
     * code : 200
     * messages : []
     * data : {"response":{"rows":[{"questionCatCode":"01010101","questionCatCodeName":"新手"},{"questionCatCode":"01010102","questionCatCodeName":"网申"}]},"time":"1603766475655"}
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
         * response : {"rows":[{"questionCatCode":"01010101","questionCatCodeName":"新手"},{"questionCatCode":"01010102","questionCatCodeName":"网申"}]}
         * time : 1603766475655
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
            private List<RowsBean> rows;

            public List<RowsBean> getRows() {
                return rows;
            }

            public void setRows(List<RowsBean> rows) {
                this.rows = rows;
            }

            public static class RowsBean {
                /**
                 * questionCatCode : 01010101
                 * questionCatCodeName : 新手
                 */

                private String questionCatCode;
                private String questionCatCodeName;

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
            }
        }
    }
}
