package com.udit.bankexam.bean;

public class ErrorBean {

    /**
     * result : fail
     * msg : 您输入的密码错误，请重新输入
     */

    private String result;
    private String msg;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
