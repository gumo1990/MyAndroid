package com.example.administrator.myandroid.bean;

/**
 * Created by whq on 2018/5/29.
 * 定义消息事件类
 */

public class MessageEvent {
     String Message;

    public MessageEvent(String message) {
        Message = message;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        this.Message = message;
    }
}
