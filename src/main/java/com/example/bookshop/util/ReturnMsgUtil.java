package com.example.bookshop.util;

public class ReturnMsgUtil {
    private int state;   //状态：0：成功，1：失败
    private String message;  //相关信息

    public int getState() {
        return state;
    }

    public void setState(int stateCode) {
        this.state = stateCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ReturnMsgUtil(int state, String message) {
        this.state = state;
        this.message = message;
    }

    @Override
    public String toString() {
        return "ReturnMsgUtil{" +
                "state=" + state +
                ", message='" + message + '\'' +
                '}';
    }
}
